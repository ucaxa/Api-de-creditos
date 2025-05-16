import { Component } from '@angular/core';
import { CommonModule, CurrencyPipe, DatePipe } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CreditoService } from '../../services/credito.service';
import { Credito } from '../../models/credito';

@Component({
  selector: 'app-consulta-creditos',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    DatePipe,
    CurrencyPipe
  ],
  templateUrl: './consulta-creditos.component.html',
  styleUrls: ['./consulta-creditos.component.scss']
})
export class ConsultaCreditosComponent {
  tipoConsulta: string = 'nfse';
  valorConsulta: string = '';
  creditos: Credito[] = [];
  creditoDetalhe: Credito | null = null;
  loading: boolean = false;
  errorMessage: string = '';

  constructor(private creditoService: CreditoService) { }

  consultar(): void {
    this.errorMessage = '';
    this.creditoDetalhe = null;
    this.loading = true;

    if (!this.valorConsulta) {
      this.errorMessage = 'Por favor, informe um valor para consulta';
      this.loading = false;
      return;
    }

    if (this.tipoConsulta === 'nfse') {
      this.consultarPorNfse();
    } else {
      this.consultarPorCredito();
    }
  }

  private consultarPorNfse(): void {
    this.creditoService.getByNfse(this.valorConsulta).subscribe({
      next: (data) => {
        this.creditos = this.mapearDados(data);
        if (data.length === 0) {
          this.errorMessage = 'Nenhum crédito encontrado para esta NFS-e';
        }
        this.loading = false;
      },
      error: (err) => {
        this.errorMessage = 'Erro ao consultar créditos';
        console.error(err);
        this.loading = false;
      }
    });
  }

  
  private consultarPorCredito(): void {
    this.creditoService.getByCredito(this.valorConsulta).subscribe({
      next: (data) => {
        // Usa a mesma função de mapeamento que a consulta por NFS-e
        const resultado = this.mapearDados([data]); // Envolve em array para compatibilidade
        this.creditoDetalhe = resultado[0];
        this.creditos = resultado;
        this.loading = false;
      },
      error: (err) => {
        this.errorMessage = 'Crédito não encontrado';
        console.error(err);
        this.loading = false;
      }
    });
  }

  
 private mapearDados(dados: any[]): Credito[] {
  return dados.map(item => ({
    numeroCredito: item.numeroCredito || '',
    numeroNfse: item.numeroNfse || '',
    dataConstituicao: this.converterData(item.dataConstituicao),
    valorIssqn: item.valorIssqn || 0,
    tipoCredito: item.tipoCredito || '',
    simplesNacional: this.formatarSimplesNacional(item.simplesNacional),
    aliquota: item.aliquota || 0,
    valorFaturado: item.valorFaturado || 0,
    valorDeducao: item.valorDeducao || 0,
    baseCalculo: item.baseCalculo || 0
  }));
}

private converterData(data: any): Date {
  if (!data) return new Date(); // Fallback para data atual
  
  // Se já for um objeto Date
  if (data instanceof Date) return data;
  
  // Se for string ISO (2023-12-31 ou 2023-12-31T00:00:00)
  if (typeof data === 'string') {
    const date = new Date(data);
    return isNaN(date.getTime()) ? new Date() : date;
  }
  
  // Se for timestamp numérico
  if (typeof data === 'number') return new Date(data);
  
  return new Date(); // Fallback padrão
}

    private formatarSimplesNacional(simplesNacional: any): string {
    if (typeof simplesNacional === 'boolean') {
      return simplesNacional ? 'Sim' : 'Não';
    }
    return simplesNacional || 'Não';
  }

  limparConsulta(): void {
    this.valorConsulta = '';
    this.creditos = [];
    this.creditoDetalhe = null;
    this.errorMessage = '';
  }

  // Método auxiliar para formatação de data (opcional)
  formatarDataParaExibicao(data: Date): string {
    return data.toLocaleDateString('pt-BR');
  }



}