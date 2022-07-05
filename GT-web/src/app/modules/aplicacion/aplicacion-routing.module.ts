import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MigrarAplicacionComponent } from './migrar-aplicacion/migrar-aplicacion.component';

const routes: Routes = [
  {
    path: '',
    component: MigrarAplicacionComponent,
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
export class AplicacionRoutingModule { }
