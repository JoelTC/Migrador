import { Component, OnInit } from '@angular/core';
import { RolPadre } from 'src/app/models/esquema/RolPadre';
import { RolPorRol } from 'src/app/models/esquema/RolPorRol';
import { RolPadreRequest } from 'src/app/models/request/RolPadreRequest';
import { EmpresaService } from 'src/app/services/empresa/empresa.service';
import { FileService } from 'src/app/services/file.service';
import { FileUploadComponent } from 'src/app/shared/componentes/file-upload/file-upload.component';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-empresa-rol-agrupador',
  templateUrl: './empresa-rol-agrupador.component.html',
  styleUrls: ['./empresa-rol-agrupador.component.scss']
})
export class EmpresaRolAgrupadorComponent implements OnInit {

  lRolPadre: RolPadre[] = [];
  selectedRolPadre: string;
  lRol: RolPorRol[];
  mnemonicoDestino: string;
  lRolPadreReq: RolPadreRequest[] = [];

  constructor(private serviceEmpresa: EmpresaService, private serviceFile: FileService) { }

  ngOnInit(): void {
  }

  listarRolPadre() {
    this.serviceEmpresa.listarRolPadre().subscribe({
      next: (result: any) => {
        this.lRolPadre = result.data;
      },
      error: (error) => { "Error: " + console.log(error) }
    })
  }

  listRol() {
    for (let rol of this.lRolPadre) {
      if (rol.mnemonico == this.selectedRolPadre) {
        this.lRol = rol.lRolPorRol;
      }
    }
  }

  setMnemonico() {
    let val: boolean = false;
    let rolPadreReq: RolPadreRequest = {
      mnemonicoOrigen: '',
      mnemonicoDestino: ''
    };

    rolPadreReq.mnemonicoOrigen = this.selectedRolPadre;
    rolPadreReq.mnemonicoDestino = this.mnemonicoDestino;

    for (let rPadre of this.lRolPadreReq) {
      if (rPadre.mnemonicoDestino == this.mnemonicoDestino) {
        val = true;
        break
      }
      if (rPadre.mnemonicoOrigen == this.selectedRolPadre) {
        rPadre.mnemonicoDestino = this.mnemonicoDestino;
        val = true;
        break;
      }
    }

    if (!val) {
      this.lRolPadreReq.push(rolPadreReq);
    }

    console.log(this.lRolPadreReq);
  }

  renombrarRolPadre() {
    this.serviceEmpresa.renombrarRolPadre(this.lRolPadreReq).subscribe({
      next: (result: any) => {
        //console.log(result.data);
        if (result.data != null) {
          this.serviceFile.getFile(this.serviceFile.nombreArchivo);
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
            title: 'Renombrado exitoso'
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

}
