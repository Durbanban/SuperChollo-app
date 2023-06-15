import { AfterViewInit, Component, ElementRef, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { createPopper } from '@popperjs/core';
import { FileService } from 'src/app/services/file.service';
import { environment } from 'src/environments/environment';
import { Usuario } from 'src/app/interfaces/usuario.interface';


@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {


  @Input() usuario: Usuario = {} as Usuario;
  @Output() abrirSidenav = new EventEmitter<boolean>();
  @Input() estadoDrawer: boolean = {} as boolean;

  constructor(
    private authService: AuthService,
    private router: Router
    ) { }

  ngOnInit(): void {
  }



  logOut() {
    localStorage.removeItem("token");
    localStorage.removeItem("refresh_token");
    localStorage.removeItem("user_rol");
    this.authService.doLogout();
    this.router.navigate(["login"]);
  }

  getAvatar() {
    return environment.API_BASE_URL+"/file/download/"+this.usuario.avatar;
  }

  emitEventoSideNav() {
    this.abrirSidenav.emit(!this.estadoDrawer);
  }

}
