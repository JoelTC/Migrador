import { Injectable } from '@angular/core';
import { MigradorEmpresaRequest } from 'src/app/models/request/MigradorEmpresaRequest';

@Injectable({
  providedIn: 'root'
})
export class GlobalVariableService {
  mEmpresa = new MigradorEmpresaRequest;
  constructor() { }
}
