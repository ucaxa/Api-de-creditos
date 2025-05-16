import { Routes } from '@angular/router';
import { ConsultaCreditosComponent } from './components/consulta-creditos/consulta-creditos.component';

export const routes: Routes = [
  { 
    path: '',
    component: ConsultaCreditosComponent,
    title: 'Consulta de Créditos'
  },
  { 
    path: '**', 
    redirectTo: '' 
  }
];
