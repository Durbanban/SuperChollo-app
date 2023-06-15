import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Usuario } from 'src/app/interfaces/usuario.interface';
import { CategoriaService } from 'src/app/services/categoria.service';
import { ProductoService } from 'src/app/services/producto.service';
import { SupermercadoService } from 'src/app/services/supermercado.service';
import { UsuarioService } from 'src/app/services/usuario.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {


  usuarioLogueado: Usuario = {} as Usuario;
  totalProductos: number = 0;
  totalSupermercados: number = 0;
  totalUsuarios: number = 0;
  totalUsuariosPremium: number = 0;
  drawerAbierto: boolean = false;
  mensaje: string = "";
  
  constructor(
    private usuarioService: UsuarioService,
    private productoService: ProductoService,
    private supermercadoService: SupermercadoService,
    private categoriaService: CategoriaService
  ) { }

  ngOnInit(): void {
    this.usuarioService.getMe().subscribe(respuesta => {
      this.usuarioLogueado = respuesta;
      this.productoService.getAllProductos().subscribe(respuesta => {
        this.totalProductos = respuesta.elementosTotales;
      });
      this.supermercadoService.getAllSupermercados().subscribe(respuesta => {
        this.totalSupermercados = respuesta.elementosTotales;
      });
      this.usuarioService.getAllUsers().subscribe(respuesta => {
        this.totalUsuarios = respuesta.length;
        this.totalUsuariosPremium = respuesta.filter(u => u.roles.includes("PREMIUM")).length;
      });
    });
    
  }
  capturarEventoSidenav(event: boolean) {
    this.drawerAbierto = event;
  }

}


