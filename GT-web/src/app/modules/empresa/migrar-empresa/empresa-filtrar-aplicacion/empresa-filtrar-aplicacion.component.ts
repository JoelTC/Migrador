import { Component, OnInit } from '@angular/core';
import { AplicacionDTO } from 'src/app/models/entities/AplicacionDTO';
import { EmpresaService } from 'src/app/services/empresa/empresa.service';
import { GlobalVariableService } from 'src/app/shared/servicio/global-variable.service';

@Component({
  selector: 'app-empresa-filtrar-aplicacion',
  templateUrl: './empresa-filtrar-aplicacion.component.html',
  styleUrls: ['./empresa-filtrar-aplicacion.component.scss']
})
export class EmpresaFiltrarAplicacionComponent implements OnInit {

  aplicacionDTO: AplicacionDTO[] = [];
  selectedAplicacion: AplicacionDTO[] = [];

  constructor(private serviceEmpresa: EmpresaService, private serviceGlobal: GlobalVariableService) { }

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
      this.serviceGlobal.mEmpresa.lAplicacion = [];
    } else {
      this.serviceGlobal.mEmpresa.lAplicacion = this.selectedAplicacion;
    }
    console.log(this.serviceGlobal.mEmpresa);
  }
}
