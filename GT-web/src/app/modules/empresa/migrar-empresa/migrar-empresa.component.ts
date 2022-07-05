import { Component, OnInit } from '@angular/core';
import { MatStepper } from '@angular/material/stepper';
import { MigradorEmpresaRequest } from 'src/app/models/request/MigradorEmpresaRequest';
@Component({
  selector: 'app-migrar-empresa',
  templateUrl: './migrar-empresa.component.html',
  styleUrls: ['./migrar-empresa.component.scss']
})
export class MigrarEmpresaComponent implements OnInit {
  public static mEmpresa: MigradorEmpresaRequest;
  
  constructor() { }

  ngOnInit(): void {
  }

  goBack(stepper: MatStepper){
    stepper.previous();
}

goForward(stepper: MatStepper){
    stepper.next();
}

}
