import { Component, OnInit } from '@angular/core';
import { CifradoRequest } from 'src/app/models/request/CifradoRequest';
import { EmpresaService } from 'src/app/services/empresa/empresa.service';
import { FileService } from 'src/app/services/file.service';
import { FileUploadComponent } from 'src/app/shared/componentes/file-upload/file-upload.component';

@Component({
  selector: 'app-cifrado',
  templateUrl: './cifrado.component.html',
  styleUrls: ['./cifrado.component.scss']
})
export class CifradoComponent implements OnInit {
  cifradoRequest = new CifradoRequest;

  //Variables auxiliares para gestionar los eventos
  selectedCifradoO: string;
  selectedCifradoD: string;
  tipMensaje1: string;
  tipMensaje2: string;

  file = new FileUploadComponent(this.serviceFile);

  constructor(private serviceEmpresa: EmpresaService, private serviceFile: FileService) {
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

  migrarCifrado() {
    this.setCifradoRequest();
    this.serviceEmpresa.migrarCifrado(this.cifradoRequest).subscribe({
      next: (result: any) => {
        console.log(result.data);
      },
      error: (error) => { "Error: " + console.log(error) }
    })
  }

  async listarCifrado() {
    this.file.upload();
    await this.delay(300);

    this.serviceEmpresa.listarCifrado().subscribe({
      next: (result: any) => {
        this.selectedCifradoO = result.data;
      },
      error: (error) => { "Error: " + console.log(error) }
    })
  }

  delay(ms: number) {
    return new Promise(resolve => setTimeout(resolve, ms));
  }

}
