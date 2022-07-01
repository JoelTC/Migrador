import { Component, OnInit } from '@angular/core';
import { MatStepper } from '@angular/material/stepper';
@Component({
  selector: 'app-migrar-empresa',
  templateUrl: './migrar-empresa.component.html',
  styleUrls: ['./migrar-empresa.component.scss']
})
export class MigrarEmpresaComponent implements OnInit {

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
