import { Component, OnInit } from '@angular/core';
import { AplicacionDTO } from 'src/app/models/entities/AplicacionDTO';
import { EmpresaService } from 'src/app/services/empresa/empresa.service';
import { FileService } from 'src/app/services/file.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-empresa-filtrar-aplicacion',
  templateUrl: './empresa-filtrar-aplicacion.component.html',
  styleUrls: ['./empresa-filtrar-aplicacion.component.scss']
})
export class EmpresaFiltrarAplicacionComponent implements OnInit {

  aplicacionDTO: AplicacionDTO[] = [];
  selectedAplicacion: AplicacionDTO[] = [];

  constructor(private serviceEmpresa: EmpresaService, private serviceFile: FileService) { }

  dropdownSettings = {};

  ngOnInit() {

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

  listarAplicacion() {
    this.serviceEmpresa.listarAplicacion().subscribe({
      next: (result: any) => {
        this.aplicacionDTO = result.data;
      },
      error: (error) => { "Error: " + console.log(error) }
    })
  }

  filtrarAplicacion() {
    if (this.aplicacionDTO.length == this.selectedAplicacion.length) {
      this.serviceFile.mEmpresa.lAplicacion = [];
    } else {
      this.serviceFile.mEmpresa.lAplicacion = this.selectedAplicacion;
    }
    //console.log(this.serviceGlobal.mEmpresa);
 
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
}
