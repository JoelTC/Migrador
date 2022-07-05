import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EmpresaService {
  private url: string;
  constructor(private http: HttpClient) {
    this.url = 'http://localhost:8080/gt-webcore/api/v1/empresa/';
  }

  listarCifrado() {
    return this.http.get<string>(this.url + 'listarCifrado');
  }

  migrarCifrado(cifradoO: string, cigradoD: string) {
    let params = new HttpParams()
      .set('cifradoOrigen', cifradoO)
      .set('cifradoDestino', cigradoD)
      .set('contrasena', '6F85FE7C028DC97789ED443CDF37A762');
      
    return this.http.get<string>(this.url + 'migrarCifrado', { params });
  }
}
