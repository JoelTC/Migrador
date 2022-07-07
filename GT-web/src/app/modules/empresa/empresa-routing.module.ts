import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CifradoComponent } from './cifrado/cifrado.component';
import { FiltrarAplicacionComponent } from './filtrar-aplicacion/filtrar-aplicacion.component';
import { MigrarEmpresaComponent } from './migrar-empresa/migrar-empresa.component';
import { RolAgrupadorComponent } from './rol-agrupador/rol-agrupador.component';

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
    path: 'filtrar',
    component: FiltrarAplicacionComponent,
  },
  {
    path: 'rolAgrupador',
    component: RolAgrupadorComponent,
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
