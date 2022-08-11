import { Component, OnInit } from '@angular/core';
import { AplicacionService } from 'src/app/services/aplicacion/aplicacion.service';
import { FileService } from 'src/app/services/file.service';
import { FileUploadComponent } from 'src/app/shared/componentes/file-upload/file-upload.component';

import Swal from 'sweetalert2';

@Component({
  selector: 'app-migrar-aplicacion',
  templateUrl: './migrar-aplicacion.component.html',
  styleUrls: ['./migrar-aplicacion.component.scss']
})
export class MigrarAplicacionComponent implements OnInit {
  selectedTipo: string;
  selectedVersionO: string;
  selectedVersionD: string;
  txtPath: string;

  file: FileUploadComponent = new FileUploadComponent(this.serviceFile);

  constructor(private serviceAplicacion: AplicacionService, private serviceFile: FileService) {
    this.selectedTipo = "";
    this.selectedVersionO = "";
    this.selectedVersionD = "3.03";
  }

  ngOnInit(): void {
  }

  async migrarAplicacion() {
    this.file.upload();
    await this.delay(300);
    this.serviceAplicacion.migrarAplicacion(this.selectedVersionO, this.selectedTipo).subscribe({
      next: (result: any) => {
        if (result.data != null) {
          this.save();
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
            title: 'Migración exitosa'
          })
        }
      },
      error: (error) => { "Error: " + console.log(error) }
    })
  }


  save() {
    this.serviceFile.getFile(this.serviceFile.nombreArchivo);
  }

  delay(ms: number) {
    return new Promise(resolve => setTimeout(resolve, ms));
  }
}