export interface Credito {
    numeroCredito: string;
    numeroNfse: string;
    dataConstituicao: string | Date;  
    valorIssqn: number;
    tipoCredito: string;
    simplesNacional: string;
    aliquota: number;
    valorFaturado: number;
    valorDeducao: number;
    baseCalculo: number;
  }
