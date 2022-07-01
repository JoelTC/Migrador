import { Component } from '@angular/core';
import { DocAplicacion } from './models/esquema/DocAplicacion';
import { AplicacionService } from './services/aplicacion/aplicacion.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'GT-web';
 /* public docAplacion: DocAplicacion[];
  fileToUpload: File;*/

  constructor(/*private serviceAplicacion: AplicacionService*/) {
  }

  ngOnInit() {
    //this.migrarAplicacion();
  }

  /*public migrarAplicacion() {
    this.serviceAplicacion.migrarAplicacion("MENU", "2.3").subscribe({
      next: (result: any) => {
        console.log('Doc: ', result);
        this.docAplacion = result.data;
      },
      error: (error) => { "Error: " + console.log(error) }
    })
  }*/
}