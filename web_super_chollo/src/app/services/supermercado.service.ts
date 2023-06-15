import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { SupermercadoResponse } from "../interfaces/supermercado.interface";

@Injectable({providedIn: "root"})
export class SupermercadoService {

    constructor(
        private http: HttpClient
    ) { }

    public getAllSupermercados(): Observable<SupermercadoResponse> {
        return this.http.get<SupermercadoResponse>(`${environment.API_BASE_URL}/supermercado/`);
    }

    public getTotalSupermercados(size: number): Observable<SupermercadoResponse> {
        return this.http.get<SupermercadoResponse>(`${environment.API_BASE_URL}/supermercado/?size=${size}`)
    }

    
}