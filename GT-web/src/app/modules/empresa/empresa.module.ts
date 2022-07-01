import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

import { SharedModule } from 'src/app/shared/shared.module';

import { EmpresaRoutingModule } from './empresa-routing.module';
import { MigrarEmpresaComponent } from './migrar-empresa/migrar-empresa.component';
import { FileUploadComponent } from 'src/app/components/file-upload/file-upload.component';

@NgModule({
  declarations: [
    MigrarEmpresaComponent,
    FileUploadComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    SharedModule,
    EmpresaRoutingModule
  ]
})
export class EmpresaModule { }
