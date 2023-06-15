import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { Producto, ProductoCreateRequest, ProductoDetails, ProductoResponse } from "../interfaces/producto.interface";

@Injectable({providedIn: "root"})
export class ProductoService {

    constructor(
        private http: HttpClient
    ) { }

    public getAllProductos(): Observable<ProductoResponse> {
        return this.http.get<ProductoResponse>(`${environment.API_BASE_URL}/producto/`);
    }

    public createProducto(productoRequest: ProductoCreateRequest, file: File): Observable<any> {

        const formData = new FormData();
    
        const blobBody = new Blob([JSON.stringify(productoRequest)], {
          type: "application/vnd.api+json",
        });
    
        formData.append('file', file);
        formData.append('producto', blobBody);
    
        return this.http.post(`${environment.API_BASE_URL}/producto/`, formData);
    }

    public getTotalProductos(size: number): Observable<ProductoResponse> {
        return this.http.get<ProductoResponse>(`${environment.API_BASE_URL}/producto/?size=${size}`);
    }

    
}