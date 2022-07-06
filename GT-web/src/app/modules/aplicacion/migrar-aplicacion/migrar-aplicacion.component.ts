import { Component, OnInit } from '@angular/core';
import { AplicacionService } from 'src/app/services/aplicacion/aplicacion.service';
import { FileService } from 'src/app/services/file.service';
import { FileUploadComponent } from 'src/app/shared/componentes/file-upload/file-upload.component';

@Component({
  selector: 'app-migrar-aplicacion',
  templateUrl: './migrar-aplicacion.component.html',
  styleUrls: ['./migrar-aplicacion.component.scss']
})
export class MigrarAplicacionComponent implements OnInit {
  selectedTipo: string;
  selectedVersion: string;

  file: FileUploadComponent = new FileUploadComponent(this.serviceFile);

  constructor(private serviceAplicacion: AplicacionService, private serviceFile: FileService) {
    this.selectedTipo = "";
    this.selectedVersion = "";
  }

  ngOnInit(): void {
  }

  async migrarAplicacion() {
    this.file.upload();
    await this.delay(300);
    this.serviceAplicacion.migrarAplicacion(this.selectedVersion, this.selectedTipo).subscribe({
      next: (result: any) => {
        console.log('Doc: ', result);
      },
      error: (error) => { "Error: " + console.log(error) }
    })
  }

  delay(ms: number) {
    return new Promise(resolve => setTimeout(resolve, ms));
  }

  //And call it

}