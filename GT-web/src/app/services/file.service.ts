import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class FileService {
  private url: string;
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
    return this.http.post<string>(this.url + 'upload', formData, { headers: headers });
  }
}
