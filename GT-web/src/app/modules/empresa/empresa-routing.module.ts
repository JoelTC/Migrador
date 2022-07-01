import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MigrarEmpresaComponent } from './migrar-empresa/migrar-empresa.component';

const routes: Routes = [ {
  path: '',
  component: MigrarEmpresaComponent,
},
{
  path: '**',
  redirectTo: '',
},];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EmpresaRoutingModule { }
