import { NgModule, APP_INITIALIZER, LOCALE_ID } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppComponent } from './app.component';

//Enrutador
import { AppRoutingModule } from './app-routing.module';
import { RouterModule } from '@angular/router';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';

//Módulos de la aplicación
import { SharedModule } from './shared/shared.module';
import { LoginModule } from './modules/login/login.module';
import { InicioModule } from './modules/inicio/inicio.module';
import { EmpresaModule } from './modules/empresa/empresa.module';
import { AplicacionModule } from './modules/aplicacion/aplicacion.module';

//Componentes de la aplicacion
import { ContainerComponent } from './components/container/container.component';
import { MenuComponent } from './components/container/menu/menu.component';
import { FileComponent } from './components/file/file.component';

//Manejo de hora en español
import { registerLocaleData } from '@angular/common';
import localePe from '@angular/common/locales/es-PE';
registerLocaleData(localePe, 'es');

@NgModule({
  declarations: [
    AppComponent,
    ContainerComponent,
    MenuComponent,
    FileComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    RouterModule,
    HttpClientModule,
    LoginModule,
    InicioModule,
    EmpresaModule,
    AplicacionModule,
    SharedModule
  ],
  providers:[
    { provide: LOCALE_ID, useValue: 'es-PE' },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
