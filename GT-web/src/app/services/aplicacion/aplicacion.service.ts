import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { DocAplicacion } from 'src/app/models/esquema/DocAplicacion';

@Injectable({
  providedIn: 'root'
})
export class AplicacionService {
  private url: string;

  constructor(private http: HttpClient) {
    this.url = 'http://localhost:8080/gt-webcore/api/v1/aplicacion/';
  }

  migrarAplicacion(version: string, tipo: string) {
    let params = new HttpParams()
      .set('version', version)
      .set('tipo', tipo);

    return this.http.get<DocAplicacion>(this.url + 'migrar', { params });
  }
}
