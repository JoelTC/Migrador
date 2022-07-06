import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

import { SharedModule } from 'src/app/shared/shared.module';

import { EmpresaRoutingModule } from './empresa-routing.module';
import { MigrarEmpresaComponent } from './migrar-empresa/migrar-empresa.component';
import { ImportarTemplateComponent } from './migrar-empresa/importar-template/importar-template.component';
import { CifradoComponent } from './cifrado/cifrado.component';
import { FiltraraplicacionComponent } from './filtraraplicacion/filtraraplicacion.component';

@NgModule({
  declarations: [
    MigrarEmpresaComponent,
    ImportarTemplateComponent,
    CifradoComponent,
    FiltraraplicacionComponent
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
