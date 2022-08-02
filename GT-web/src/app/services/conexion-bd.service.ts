import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ConexionBD } from '../models/entities/ConexionBD';

@Injectable({
  providedIn: 'root'
})
export class ConexionBDService {
  private url: string;
  constructor(private http: HttpClient) {
    this.url = 'http://localhost:8080/gt-webcore/api/v1/bd/';
  }

  conexionBD(conexion: ConexionBD) {
    return this.http.post<any>(this.url + "conexion", conexion);
  }
}
