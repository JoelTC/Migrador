import { Component, OnInit } from '@angular/core';
import { FileService } from 'src/app/services/file.service';

@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrls: ['./file-upload.component.scss']
})
export class FileUploadComponent implements OnInit {
  file: FileList | null = null;

  //Variables auxiliares para controlar los eventos
  public file_xml: string;
  public load: boolean;
  public nombre_file: string;

  constructor(private serviceFile: FileService) {
    this.file_xml = 'assets/recursos/file-xml.png';
    this.load = false;
  }

  ngOnInit(): void {
  }

  cargarArchivo(event: Event) {
    const element = event.currentTarget as HTMLInputElement;
    this.file = element.files;
    if (this.file) {
      this.nombre_file = this.file![0].name;
      this.load = true;
      this.iniciar();
    }
  }

  iniciar() {
    this.serviceFile.uploadFile(this.file![0]).subscribe({
      next: (result: any) => {
        console.log("Mensaje: ", result);
      },
      error: (error) => { "Error: " + console.log(error) }
    })
  }
}
