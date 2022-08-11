import { Component, OnInit } from '@angular/core';
import { MatStepper } from '@angular/material/stepper';
import { MigradorEmpresaRequest } from 'src/app/models/request/MigradorEmpresaRequest';
import { EmpresaService } from 'src/app/services/empresa/empresa.service';
import { FileService } from 'src/app/services/file.service';

@Component({
  selector: 'app-migrar-empresa',
  templateUrl: './migrar-empresa.component.html',
  styleUrls: ['./migrar-empresa.component.scss']
})
export class MigrarEmpresaComponent implements OnInit {
  mEmpresa = new MigradorEmpresaRequest;

  //template = new EmpresaImportarTemplateComponent(this.serviceEmpresa, this.serviceFile, this.serviceGlobal);
  //filtro = new EmpresaFiltrarAplicacionComponent(this.serviceEmpresa, this.serviceGlobal);

  constructor(private serviceEmpresa: EmpresaService, private serviceFile: FileService) { }

  ngOnInit(): void {
  }

  anterior(stepper: MatStepper) {
    stepper.previous();
  }

  siguiente(stepper: MatStepper) {
    stepper.next();
  }

}
