import { Component, OnInit } from '@angular/core';
import { EmpresaService } from 'src/app/services/empresa/empresa.service';
import { FileService } from 'src/app/services/file.service';
import { FileUploadComponent } from 'src/app/shared/componentes/file-upload/file-upload.component';
import { GlobalVariableService } from 'src/app/shared/servicio/global-variable.service';
import { MatDialog } from '@angular/material/dialog';
import { ConnectionBDComponent } from 'src/app/shared/componentes/connection-bd/connection-bd.component';

@Component({
  selector: 'app-empresa-importar-template',
  templateUrl: './empresa-importar-template.component.html',
  styleUrls: ['./empresa-importar-template.component.scss']
})
export class EmpresaImportarTemplateComponent implements OnInit {
  selectedTipo: string;
  selectedVersionO: string;
  selectedVersionD: string;
  txtPath:string;

  file = new FileUploadComponent(this.serviceFile);

  constructor(private dialogRef: MatDialog, private serviceEmpresa: EmpresaService, private serviceFile: FileService, private serviceGlobal: GlobalVariableService) {
    this.selectedTipo = "";
    this.selectedVersionO = "";
    this.selectedVersionD = "3.03";
  }

  ngOnInit(): void {
  }

  async iniciar() {
    this.file.upload(this.txtPath);
    await this.delay(300);
    this.serviceGlobal.mEmpresa.version = this.selectedVersionO;
    this.serviceGlobal.mEmpresa.tipo = this.selectedTipo;
    console.log(this.serviceGlobal.mEmpresa);
  }

 openDialog(){
  this.dialogRef.open(ConnectionBDComponent);
 }

  delay(ms: number) {
    return new Promise(resolve => setTimeout(resolve, ms));
  }

}
