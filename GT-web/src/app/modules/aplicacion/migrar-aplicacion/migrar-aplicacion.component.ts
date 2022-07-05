import { Component, OnInit } from '@angular/core';
import { AplicacionService } from 'src/app/services/aplicacion/aplicacion.service';

@Component({
  selector: 'app-migrar-aplicacion',
  templateUrl: './migrar-aplicacion.component.html',
  styleUrls: ['./migrar-aplicacion.component.scss']
})
export class MigrarAplicacionComponent implements OnInit {
  selectedTipo: string;
  selectedVersion: string;

  constructor(private serviceAplicacion: AplicacionService) {
    this.selectedTipo = "";
    this.selectedVersion = "";
  }

  ngOnInit(): void {
  }

  migrarAplicacion() {
    this.serviceAplicacion.migrarAplicacion(this.selectedVersion, this.selectedTipo).subscribe({
      next: (result: any) => {
        console.log('Doc: ', result);
      },
      error: (error) => { "Error: " + console.log(error) }
    })
  }
}