import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialImportsModule } from './modules/material-imports.module';
import { LoginComponent } from './components/public/login/login.component';
import { NotFoundComponent } from './components/public/not-found/not-found.component';
import { HomeComponent } from './components/private/home/home.component';
import { NavbarComponent } from './components/layout/navbar/navbar.component';
import { GuardService } from './services/guard.service';
import { authInterceptorProviders } from './interceptor/http.interceptor.service';
import { SidenavComponent } from './components/layout/sidenav/sidenav.component';
import { ManagementComponent } from './components/private/management/management.component';
import { PaginatorComponent } from './components/layout/paginator/paginator.component';
import { CrearProductoComponent } from './components/layout/dialog/crear-producto/crear-producto.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    NotFoundComponent,
    HomeComponent,
    NavbarComponent,
    SidenavComponent,
    ManagementComponent,
    PaginatorComponent,
    CrearProductoComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialImportsModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule
  ],
  providers: [
    GuardService,
    authInterceptorProviders,
    ],
  bootstrap: [AppComponent]
})
export class AppModule { }
