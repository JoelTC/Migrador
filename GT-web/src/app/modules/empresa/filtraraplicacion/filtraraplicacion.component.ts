import { Component, OnInit } from '@angular/core';
import { AplicacionDTO } from 'src/app/models/entities/AplicacionDTO';
import { EmpresaService } from 'src/app/services/empresa/empresa.service';
import { FileService } from 'src/app/services/file.service';
import { FileUploadComponent } from 'src/app/shared/componentes/file-upload/file-upload.component';

@Component({
  selector: 'app-filtraraplicacion',
  templateUrl: './filtraraplicacion.component.html',
  styleUrls: ['./filtraraplicacion.component.scss']
})
export class FiltraraplicacionComponent implements OnInit {
  aplicacionDTO: AplicacionDTO[] = [];
  selectedAplicacion: AplicacionDTO[] = [];

  file = new FileUploadComponent(this.serviceFile);

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
        console.log(result)
      },
      error: (error) => { "Error: " + console.log(error) }
    })
  }

  delay(ms: number) {
    return new Promise(resolve => setTimeout(resolve, ms));
  }
}
