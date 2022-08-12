import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

//Componentes
import { MenuComponent } from './components/container/menu/menu.component';
import { ContainerComponent } from './components/container/container.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full',
  },
  {
    path: 'login',
    loadChildren: () =>
      import('./modules/login/login.module').then((m) => m.LoginModule),
  },
  {
    path: '',
    component: ContainerComponent,
    children: [
      {
        path: '',
        redirectTo: 'inicio',
        pathMatch: 'full',
      },
      {
        path: 'inicio',
        loadChildren: () =>
          import('./modules/inicio/inicio.module').then((m) => m.InicioModule)
      },
      {
        path: 'migrarEmpresa',
        loadChildren: () =>
          import('./modules/empresa/empresa.module').then((m) => m.EmpresaModule)
      },
      {
        path: 'migrarAplicacion',
        loadChildren: () =>
          import('./modules/aplicacion/aplicacion.module').then((m) => m.AplicacionModule)
      }
    ]
  },
  {
    path: '**',
    redirectTo: '',
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]

})
export class AppRoutingModule { }
