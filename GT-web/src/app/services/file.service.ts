import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MigradorEmpresaRequest } from '../models/request/MigradorEmpresaRequest';

@Injectable({
  providedIn: 'root'
})
export class FileService {
  private url: string;
  mEmpresa = new MigradorEmpresaRequest;
  public nombreArchivo: string;

  constructor(private http: HttpClient) {
    this.url = 'http://localhost:8080/gt-webcore/api/v1/file/';
  }

  uploadFile(file: File) {
    let formData: FormData = new FormData();
    formData.append('files', file, file.name);

    let headers = new HttpHeaders();
    /** In Angular 5, including the header Content-Type can invalidate your request */
    headers.append('Content-Type', 'multipart/form-data');
    headers.append('Accept', 'application/json');

    /* let params = new HttpParams()
       .set('path', path);*/

    return this.http.post<string>(this.url + 'upload', formData, { headers: headers });
  }

  getFile(fileName: string) {
    return this.http.get(this.url + fileName, { responseType: 'arraybuffer' }).subscribe({
      next: (result: any) => {
        this.descargarArchivo(result, fileName);
      },
      error: (error) => { "Error: " + console.log(error) }
    })

  }

  private descargarArchivo(response: any, fileName: string): void {
    const dataType = response.type;
    const binaryData = [];
    binaryData.push(response);


    const filtePath = window.URL.createObjectURL(
      new Blob(binaryData, { type: dataType })
    );
    const downloadLink = document.createElement('a');
    downloadLink.href = filtePath;
    downloadLink.setAttribute('download', fileName);
    document.body.appendChild(downloadLink);
    downloadLink.click();
  }
}

