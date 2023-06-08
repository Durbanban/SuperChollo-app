import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginRequest } from 'src/app/interfaces/login.interface';
import { AuthService } from 'src/app/services/auth.service';

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
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  onSubmit() {
    this.authService.doLogin(new LoginRequest(this.loginForm.value.username, this.loginForm.value.password)).subscribe(respuesta => {
      if(respuesta) {
        localStorage.setItem("token", respuesta.token);
        localStorage.setItem("refresh_token", respuesta.refreshToken);
        this.router.navigate(["home"]);
      }
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
