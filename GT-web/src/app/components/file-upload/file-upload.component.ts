import { Component, OnInit } from '@angular/core';
import { FileService } from 'src/app/services/file.service';

@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrls: ['./file-upload.component.scss']
})
export class FileUploadComponent implements OnInit {
  txtPath: string = '';
  file: FileList | null = null;

  constructor(private serviceFile: FileService) { }

  ngOnInit(): void {
  }

  cargarArchivo(event: Event) {
    const element = event.currentTarget as HTMLInputElement;
    this.file = element.files;
  }

  iniciar() {
    console.log("txt: ", this.txtPath);
    console.log("File: ", this.file![0]);
    this.serviceFile.uploadFile(this.file![0], this.txtPath).subscribe({
      next: (result: any) => {
        console.log("Mensaje: ", result);
      },
      error: (error) => { "Error: " + console.log(error) }
    })
  }
}
