import { Component, OnInit } from '@angular/core';
import { AplicacionDTO } from 'src/app/models/entities/AplicacionDTO';
import { EmpresaService } from 'src/app/services/empresa/empresa.service';
import { FileService } from 'src/app/services/file.service';
import { FileUploadComponent } from 'src/app/shared/componentes/file-upload/file-upload.component';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-filtraraplicacion',
  templateUrl: './filtrar-aplicacion.component.html',
  styleUrls: ['./filtrar-aplicacion.component.scss']
})
export class FiltrarAplicacionComponent implements OnInit {
  aplicacionDTO: AplicacionDTO[] = [];
  selectedAplicacion: AplicacionDTO[] = [];

  file = new FileUploadComponent(this.serviceFile);

  constructor(private serviceEmpresa: EmpresaService, private serviceFile: FileService) { }

  dropdownSettings = {};

  ngOnInit(): void {

    this.dropdownSettings = {
      singleSelection: false,
      idField: 'mnemonico',
      textField: 'nombre',
      selectAllText: 'Seleccionar todo',
      unSelectAllText: 'Seleccionar todo',
      itemsShowLimit: 3,
      noDataAvailablePlaceholderText: 'No hay aplicaciones disponibles'
    };
  }

  async listarAplicacion() {
    this.file.upload();
    await this.delay(300);
    this.serviceEmpresa.listarAplicacion().subscribe({
      next: (result: any) => {
        this.aplicacionDTO = result.data;
      },
      error: (error) => { "Error: " + console.log(error) }
    })
  }

  filtrarAplicacion() {
    this.serviceEmpresa.filtrarAplicacion(this.selectedAplicacion).subscribe({
      next: (result: any) => {
        //console.log(result)
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
            title: 'Filtrado exitoso'
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

  delay(ms: number) {
    return new Promise(resolve => setTimeout(resolve, ms));
  }
}
