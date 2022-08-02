import { Component, OnInit } from '@angular/core';
import { MatStepper } from '@angular/material/stepper';
import { MigradorEmpresaRequest } from 'src/app/models/request/MigradorEmpresaRequest';
import { EmpresaService } from 'src/app/services/empresa/empresa.service';
import { FileService } from 'src/app/services/file.service';
import { EmpresaImportarTemplateComponent } from './empresa-importar-template/empresa-importar-template.component';
import { GlobalVariableService } from 'src/app/shared/servicio/global-variable.service';
import { EmpresaFiltrarAplicacionComponent } from './empresa-filtrar-aplicacion/empresa-filtrar-aplicacion.component';

@Component({
  selector: 'app-migrar-empresa',
  templateUrl: './migrar-empresa.component.html',
  styleUrls: ['./migrar-empresa.component.scss']
})
export class MigrarEmpresaComponent implements OnInit {
  mEmpresa = new MigradorEmpresaRequest;

  //template = new EmpresaImportarTemplateComponent(this.serviceEmpresa, this.serviceFile, this.serviceGlobal);
  //filtro = new EmpresaFiltrarAplicacionComponent(this.serviceEmpresa, this.serviceGlobal);

  constructor(private serviceEmpresa: EmpresaService, private serviceFile: FileService, private serviceGlobal: GlobalVariableService) { }

  ngOnInit(): void {
  }

  anterior(stepper: MatStepper) {
    stepper.previous();
  }

  siguiente(stepper: MatStepper) {
    stepper.next();
  }

}
