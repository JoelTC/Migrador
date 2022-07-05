import { Component, OnInit } from '@angular/core';
import { MigrarEmpresaComponent } from '../migrar-empresa.component';

@Component({
  selector: 'app-importar-template',
  templateUrl: './importar-template.component.html',
  styleUrls: ['./importar-template.component.scss']
})
export class ImportarTemplateComponent implements OnInit {
  selectedRol: string;
  selectedVersion: string;

  constructor() {
    this.selectedRol = "";
    this.selectedVersion = "";
  }

  ngOnInit(): void {
  }

  setVersion() {
  }

  setTipo() {
  }

}
