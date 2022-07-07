import { Component, OnInit } from '@angular/core';
import { CifradoRequest } from 'src/app/models/request/CifradoRequest';
import { EmpresaService } from 'src/app/services/empresa/empresa.service';
import { GlobalVariableService } from 'src/app/shared/servicio/global-variable.service';

@Component({
  selector: 'app-empresa-cifrado',
  templateUrl: './empresa-cifrado.component.html',
  styleUrls: ['./empresa-cifrado.component.scss']
})
export class EmpresaCifradoComponent implements OnInit {

  cifradoRequest = new CifradoRequest;

  //Variables auxiliares para gestionar los eventos
  selectedCifradoO: string;
  selectedCifradoD: string;
  tipMensaje1: string;
  tipMensaje2: string;

  constructor(private serviceEmpresa: EmpresaService, private serviceGlobal: GlobalVariableService) {
    this.selectedCifradoO = "";
    this.selectedCifradoD = "";
    this.tipMensaje1 = "El cambio de contraseña se hará en la secuencia de cifrado hardware → software"
    this.tipMensaje2 = "Al cambiar de cifrado por hardware a software se asignará una contraseña a los usuarios"
  }

  ngOnInit(): void {
  }

  setCifradoRequest() {
    this.cifradoRequest.cifradoOrigen = this.selectedCifradoO;
    this.cifradoRequest.cifradoDestino = this.selectedCifradoD;
  }

  migrarTemplate() {
    this.serviceEmpresa.migrar(this.serviceGlobal.mEmpresa).subscribe({
      next: (result: any) => {
        console.log(result.message);
      },
      error: (error) => { 'Error: ' + console.log(error) }
    })
  }

  listarCifrado() {
    this.serviceEmpresa.listarCifrado().subscribe({
      next: (result: any) => {
        this.selectedCifradoO = result.data;
      },
      error: (error) => { "Error: " + console.log(error) }
    })
  }

  setCifrado() {
    this.serviceGlobal.mEmpresa.cifradoOrigen = this.selectedCifradoO;
    this.serviceGlobal.mEmpresa.cifradoDestino = this.selectedCifradoD;
    console.log(this.serviceGlobal.mEmpresa);
  }
}
