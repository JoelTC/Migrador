import { Component, OnInit } from '@angular/core';
import { FileService } from 'src/app/services/file.service';

@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrls: ['./file-upload.component.scss']
})
export class FileUploadComponent implements OnInit {
  static file: FileList | null = null;

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
    FileUploadComponent.file = element.files;
    if (FileUploadComponent.file) {
      this.nombre_file = FileUploadComponent.file![0].name;
      this.load = true;
    }
  }

  upload() {
    this.serviceFile.uploadFile(FileUploadComponent.file![0]).subscribe({
      next: (result: any) => {
        console.log("Mensaje: ", result);
      },
      error: (error) => { "Error: " + console.log(error) }
    })
  }

}
