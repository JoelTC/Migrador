import { Component, OnInit } from '@angular/core';
import { FileService } from 'src/app/services/file.service';

@Component({
  selector: 'app-file',
  templateUrl: './file.component.html',
  styleUrls: ['./file.component.scss']
})
export class FileComponent implements OnInit {

  constructor( private serviceFile: FileService) { }

  ngOnInit(): void {
  }

  cargarArchivo(event: Event) {
    const element = event.currentTarget as HTMLInputElement;
    let fileList: FileList | null = element.files;
    if (fileList) {
      console.log("FileUpload -> files", fileList);
      console.log("FileUpload -> files", fileList[0]);

      this.serviceFile.uploadFile(fileList[0], 'C:\\Users\\jtrujillo\\Desktop\\templates\\templates').subscribe({
        next: (result: any) => {
          console.log("Mensaje: ", result);
        },
        error: (error) => { "Error: " + console.log(error) }
      })
    }
  }

}
