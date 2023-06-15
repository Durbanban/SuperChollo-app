import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { CategoriaResponse } from "../interfaces/categoria.interface";

@Injectable({providedIn: "root"})
export class CategoriaService {

    constructor(
        private http: HttpClient
    ) { }

    public getAllCategorias(): Observable<CategoriaResponse> {
        return this.http.get<CategoriaResponse>(`${environment.API_BASE_URL}/categoria/`);
    }

    public getTotalCategorias(size: number): Observable<CategoriaResponse> {
        return this.http.get<CategoriaResponse>(`${environment.API_BASE_URL}/categoria/?size=${size}`)
    }

    
}