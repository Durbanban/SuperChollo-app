import { HTTP_INTERCEPTORS, HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, catchError, throwError, switchMap } from 'rxjs';
import { environment } from 'src/environments/environment';
import { RefreshTokenRequest, RefreshTokenResponse } from '../interfaces/refresh-token.interface';

@Injectable({
  providedIn: 'root'
})
export class HttpinterceptorService implements HttpInterceptor {

  constructor(
    private router: Router,
    private http: HttpClient) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    if (!req.url.endsWith('login/') && req.url != `${environment.API_BASE_URL}/auth/refreshtoken/`) {
      const token: string | null = localStorage.getItem('token');
      const refreshToken: string | null = localStorage.getItem("refresh_token");

      let request = req;

      if (token) {
        request = req.clone({
          setHeaders: {
            authorization: `Bearer ${token}`
          }
        });
      }

      return next.handle(request).pipe(
        catchError((err: HttpErrorResponse) => {

          if (err.status === 401) {
            if (refreshToken) {
              return this.doRefreshToken(new RefreshTokenRequest(refreshToken)).pipe(
                switchMap((response: RefreshTokenResponse) => {
                  localStorage.setItem("token", response.token);
                  localStorage.setItem("refresh_token", response.refreshToken);

                  const newRequest = request.clone({
                    setHeaders: {
                      authorization: `Bearer ${response.token}`
                    }
                  });

                  return next.handle(newRequest);
                }),
                catchError(() => {
                  this.router.navigateByUrl('/login');
                  return throwError(err);
                })
              );
            } else {
              this.router.navigateByUrl('/login');
            }
          }

          return throwError(err);

        })
      );
    }
    return next.handle(req).pipe(
      catchError((err: HttpErrorResponse) => {

        if (err.status === 401) {
          this.router.navigateByUrl('/login');
        }

        return throwError(err);

      })
    );

  }

  private doRefreshToken(refreshToken: RefreshTokenRequest): Observable<RefreshTokenResponse> {
    return this.http.post<RefreshTokenResponse>(`${environment.API_BASE_URL}/auth/refreshtoken/`, refreshToken);
  }
}

export const authInterceptorProviders = [
  { provide: HTTP_INTERCEPTORS, useClass: HttpinterceptorService, multi: true }
];
