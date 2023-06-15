import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Usuario } from '../interfaces/usuario.interface';

@Injectable({providedIn: 'root'})

export class UsuarioService {

    constructor(
        private http: HttpClient
    ) { }

    public getMe(): Observable<Usuario> {
        return this.http.get<Usuario>(`${environment.API_BASE_URL}/auth/me/`);
    }

    public getAllUsers(): Observable<Usuario[]> {
        return this.http.get<Usuario[]>(`${environment.API_BASE_URL}/auth/user/`);
    }
    
}