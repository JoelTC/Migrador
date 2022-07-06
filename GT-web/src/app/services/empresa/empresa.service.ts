import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AplicacionDTO } from 'src/app/models/entities/AplicacionDTO';
import { DocEmpresa } from 'src/app/models/esquema/DocEmpresa';
import { CifradoRequest } from 'src/app/models/request/CifradoRequest';

@Injectable({
  providedIn: 'root'
})
export class EmpresaService {
  private url: string;
  constructor(private http: HttpClient) {
    this.url = 'http://localhost:8080/gt-webcore/api/v1/empresa/';
  }

  listarAplicacion() {
    return this.http.get<AplicacionDTO[]>(this.url + 'listarAplicacion');
  }

  filtrarAplicacion(lAplicacionDTO: AplicacionDTO[]) {
    return this.http.post<DocEmpresa>(this.url + 'filtrarAplicacion', lAplicacionDTO);
  }

  listarCifrado() {
    return this.http.get<string>(this.url + 'listarCifrado');
  }

  migrarCifrado(cifradoRequest: CifradoRequest) {
    cifradoRequest.contrasena = '6F85FE7C028DC97789ED443CDF37A762'

    return this.http.post<string>(this.url + 'migrarCifrado', cifradoRequest);
  }
}
