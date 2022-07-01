import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

//Componentes
import { MenuComponent } from './components/container/menu/menu.component';
import { ContainerComponent } from './components/container/container.component';

const routes: Routes = [
  {
    path: '',
    component: ContainerComponent,
    children: [
      {
        path: 'migrarEmpresa',
        loadChildren: () =>
          import('./modules/empresa/empresa.module').then((m) => m.EmpresaModule)
      }
    ]
  },
  {
    path: '**',
    redirectTo: 'file',
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]

})
export class AppRoutingModule { }
