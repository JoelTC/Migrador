import { Component, OnInit } from '@angular/core';
import { CifradoRequest } from 'src/app/models/request/CifradoRequest';
import { EmpresaService } from 'src/app/services/empresa/empresa.service';
import { FileService } from 'src/app/services/file.service';
import Swal from 'sweetalert2';

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

  migrarTemplate() {
    this.serviceEmpresa.migrar(this.serviceFile.mEmpresa).subscribe({
      next: (result: any) => {
        console.log(result.message);
        if (result.data != null) {
          Swal.mixin({
            toast: true,
            position: 'top-end',
            showConfirmButton: false,
            timer: 3000,
            timerProgressBar: true,
            didOpen: (toast) => {
              toast.addEventListener('mouseenter', Swal.stopTimer)
              toast.addEventListener('mouseleave', Swal.resumeTimer)
            }
          }).fire({
            icon: 'success',
            title: 'Cifrado exitoso'
          })
        }
      },
      error: (error) => {
        "Error: " + console.log(error);
        Swal.mixin({
          toast: true,
          position: 'top-end',
          showConfirmButton: false,
          timer: 3000,
          timerProgressBar: true,
          didOpen: (toast) => {
            toast.addEventListener('mouseenter', Swal.stopTimer)
            toast.addEventListener('mouseleave', Swal.resumeTimer)
          }
        }).fire({
          icon: 'error',
          title: 'Migración fallida'
        })
      }
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
    this.serviceFile.mEmpresa.cifradoOrigen = this.selectedCifradoO;
    this.serviceFile.mEmpresa.cifradoDestino = this.selectedCifradoD;
    console.log(this.serviceFile.mEmpresa);
  }
}
