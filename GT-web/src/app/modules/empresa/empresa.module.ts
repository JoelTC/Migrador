import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

import { SharedModule } from 'src/app/shared/shared.module';

import { EmpresaRoutingModule } from './empresa-routing.module';
import { MigrarEmpresaComponent } from './migrar-empresa/migrar-empresa.component';
import { EmpresaImportarTemplateComponent } from './migrar-empresa/empresa-importar-template/empresa-importar-template.component';
import { CifradoComponent } from './cifrado/cifrado.component';
import { FiltrarAplicacionComponent } from './filtrar-aplicacion/filtrar-aplicacion.component';
import { EmpresaFiltrarAplicacionComponent } from './migrar-empresa/empresa-filtrar-aplicacion/empresa-filtrar-aplicacion.component';
import { EmpresaCifradoComponent } from './migrar-empresa/empresa-cifrado/empresa-cifrado.component';
import { RolAgrupadorComponent } from './rol-agrupador/rol-agrupador.component';
import { EmpresaRolAgrupadorComponent } from './migrar-empresa/empresa-rol-agrupador/empresa-rol-agrupador.component';

@NgModule({
  declarations: [
    MigrarEmpresaComponent,
    EmpresaImportarTemplateComponent,
    CifradoComponent,
    FiltrarAplicacionComponent,
    EmpresaFiltrarAplicacionComponent,
    EmpresaCifradoComponent,
    RolAgrupadorComponent,
    EmpresaRolAgrupadorComponent,
  ],
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    SharedModule,
    EmpresaRoutingModule,
  ]
})
export class EmpresaModule { }
