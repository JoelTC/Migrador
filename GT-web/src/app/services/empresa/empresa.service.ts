import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AplicacionDTO } from 'src/app/models/entities/AplicacionDTO';
import { DocEmpresa } from 'src/app/models/esquema/DocEmpresa';
import { RolPadre } from 'src/app/models/esquema/RolPadre';
import { CifradoRequest } from 'src/app/models/request/CifradoRequest';
import { MigradorEmpresaRequest } from 'src/app/models/request/MigradorEmpresaRequest';
import { RolPadreRequest } from 'src/app/models/request/RolPadreRequest';

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
    return this.http.post<any>(this.url + 'filtrarAplicacion', lAplicacionDTO);
  }

  listarCifrado() {
    return this.http.get<string>(this.url + 'listarCifrado');
  }

  migrarCifrado(cifradoRequest: CifradoRequest) {
    cifradoRequest.contrasena = '6F85FE7C028DC97789ED443CDF37A762'

    return this.http.post<string>(this.url + 'migrarCifrado', cifradoRequest);
  }

  listarRolPadre() {
    return this.http.get<RolPadre[]>(this.url + 'listarRolPadre');
  }

  renombrarRolPadre(lRolPadre: RolPadreRequest[]) {
    return this.http.post<any>(this.url + 'renombrarRolPadre', lRolPadre);
  }

  migrar(mEmpresa: MigradorEmpresaRequest) {
    mEmpresa.contrasena = '6F85FE7C028DC97789ED443CDF37A762';
    mEmpresa.fechaNacimiento = '01/01/2000';
    return this.http.post<any>(this.url + 'migrar', mEmpresa);
  }
}
