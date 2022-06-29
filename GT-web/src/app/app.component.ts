import { Component } from '@angular/core';
import { DocAplicacion } from './models/esquema/DocAplicacion';
import { AplicacionService } from './services/aplicacion/aplicacion.service';
import { FileService } from './services/file.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'GT-web';
  public docAplacion: DocAplicacion[];
  fileToUpload: File;

  constructor(private serviceAplicacion: AplicacionService, private serviceFile: FileService) {
  }

  ngOnInit() {
    //this.migrarAplicacion();
  }

  handleFileInput(event: Event) {
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

  abrirReplica(){
    
  }

  public migrarAplicacion() {
    this.serviceAplicacion.migrarAplicacion("MENU", "2.3").subscribe({
      next: (result: any) => {
        console.log('Doc: ', result);
        this.docAplacion = result.data;
      },
      error: (error) => { "Error: " + console.log(error) }
    })
  }
}