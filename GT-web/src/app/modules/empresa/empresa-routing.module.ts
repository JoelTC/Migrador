import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CifradoComponent } from './cifrado/cifrado.component';
import { MigrarEmpresaComponent } from './migrar-empresa/migrar-empresa.component';

const routes: Routes = [
  {
    path: '',
    component: MigrarEmpresaComponent,
  },
  {
    path: 'cifrado',
    component: CifradoComponent,
  },
  {
    path: '**',
    redirectTo: '',
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EmpresaRoutingModule { }
