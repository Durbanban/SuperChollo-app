import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class FileService {

  constructor(
    private http: HttpClient
  ) { }

  public getFile(avatar: string){
    return this.http.get(`${environment.API_BASE_URL}/file/download/${avatar}`);
  }
}
