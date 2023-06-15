import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { LoginRequest, LoginResponse } from "../interfaces/login.interface";

@Injectable({providedIn: "root"})
export class AuthService {
    
    constructor(
        private http: HttpClient
    ) { }

    public doLogin(loginRequest: LoginRequest): Observable<LoginResponse> {
        return this.http.post<LoginResponse>(`${environment.API_BASE_URL}/auth/login/`, loginRequest);
    }

    public doLogout() {
        return this.http.get(`${environment.API_BASE_URL}/auth/logout/`);
    }


}