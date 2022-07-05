import { Component, OnInit } from '@angular/core';
import { EmpresaService } from 'src/app/services/empresa/empresa.service';
import { FileService } from 'src/app/services/file.service';
import { FileUploadComponent } from 'src/app/shared/componentes/file-upload/file-upload.component';

@Component({
  selector: 'app-cifrado',
  templateUrl: './cifrado.component.html',
  styleUrls: ['./cifrado.component.scss']
})
export class CifradoComponent implements OnInit {
  selectedCifradoO: string;
  selectedCifradoD: string;

  checked: boolean;

  file = new FileUploadComponent(this.serviceFile);

  constructor(private serviceEmpresa: EmpresaService, private serviceFile: FileService) {
    this.selectedCifradoO = "";
    this.selectedCifradoD = "";
    if (this.file.load) {
      this.listarCifrado();
    }
  }

  ngOnInit(): void {

  }

  listarCifrado() {
    this.serviceEmpresa.listarCifrado().subscribe({
      next: (result: any) => {
        this.selectedCifradoO = result.data;
      },
      error: (error) => { "Error: " + console.log(error) }
    })
  }

  migrarCifrado() {
    this.serviceEmpresa.migrarCifrado(this.selectedCifradoO, this.selectedCifradoD).subscribe({
      next: (result: any) => {
        console.log(result.data);
      },
      error: (error) => { "Error: " + console.log(error) }
    })
  }

}
