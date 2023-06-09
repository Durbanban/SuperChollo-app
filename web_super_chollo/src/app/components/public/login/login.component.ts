import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginRequest } from 'src/app/interfaces/login.interface';
import { AuthService } from 'src/app/services/auth.service';
import { CommonService } from 'src/app/services/common.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm = new FormGroup({
    username: new FormControl(),
    password: new FormControl()
  })

  icono: string = "visibility_off";
  visible: boolean = false;

  constructor(
    private authService: AuthService,
    private router: Router,
    private commonService: CommonService
  ) { }

  ngOnInit(): void {
  }

  onSubmit() {
    this.authService.doLogin(new LoginRequest(this.loginForm.value.username, this.loginForm.value.password)).subscribe(respuesta => {
      if(respuesta) {
        localStorage.setItem("token", respuesta.token);
        localStorage.setItem("refresh_token", respuesta.refreshToken);
        localStorage.setItem("user_rol", respuesta.roles);
        this.router.navigate(["home"]);
      }
    },
    error => {
      this.commonService.mostrarAlerta("Usuario y/o contraseña incorrectos", "Error");
    });
  }

  changePasswordVisibility() {
    if(this.visible) {
      this.icono = "visibility_off";
    }else {
      this.icono = "visibility";
    }
    this.visible = !this.visible;
  }

}
