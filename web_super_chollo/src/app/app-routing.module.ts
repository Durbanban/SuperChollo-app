import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/private/home/home.component';
import { ManagementComponent } from './components/private/management/management.component';
import { LoginComponent } from './components/public/login/login.component';
import { NotFoundComponent } from './components/public/not-found/not-found.component';
import { GuardService } from './services/guard.service';

const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'home', component: HomeComponent, canActivate: [GuardService]},
  {path: 'management/usuarios', component: ManagementComponent, canActivate: [GuardService]},
  {path: 'management/productos', component: ManagementComponent, canActivate: [GuardService]},
  {path: 'management/supermercados', component: ManagementComponent, canActivate: [GuardService]},
  {path: 'management/categorias', component: ManagementComponent, canActivate: [GuardService]},
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: '**', component: NotFoundComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
