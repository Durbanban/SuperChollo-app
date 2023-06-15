import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { Categoria } from 'src/app/interfaces/categoria.interface';
import { Producto } from 'src/app/interfaces/producto.interface';
import { Supermercado } from 'src/app/interfaces/supermercado.interface';
import { Usuario } from 'src/app/interfaces/usuario.interface';
import { CategoriaService } from 'src/app/services/categoria.service';
import { CommonService } from 'src/app/services/common.service';
import { ProductoService } from 'src/app/services/producto.service';
import { SupermercadoService } from 'src/app/services/supermercado.service';
import { UsuarioService } from 'src/app/services/usuario.service';
import { environment } from 'src/environments/environment';
import { CrearProductoComponent } from '../../layout/dialog/crear-producto/crear-producto.component';

@Component({
  selector: 'app-management',
  templateUrl: './management.component.html',
  styleUrls: ['./management.component.css']
})
export class ManagementComponent implements OnInit {

  usuarioLogueado: Usuario = {} as Usuario;
  drawerAbierto: boolean = false;
  showTable: string = "";
  datos = new MatTableDataSource<any>();
  usuario: Usuario = {} as Usuario;
  producto: Producto = {} as Producto;
  supermercado: Supermercado = {} as Supermercado;
  categoria: Categoria = {} as Categoria;
  displayedColumns: string[] = [];
  pruebaColumns: string[] = ["id", "username", "avatar", "fullName", "fechaCreado", "roles"];


  @ViewChild(MatPaginator) paginator: MatPaginator = {} as MatPaginator;
  @ViewChild(MatSort) sort: MatSort = {} as MatSort;

  constructor(
    private usuarioService: UsuarioService,
    private productoService: ProductoService,
    private supermercadoService: SupermercadoService,
    private categoriaService: CategoriaService,
    private commonService: CommonService,
    private dialog: MatDialog,
    private router: Router) { }

  ngOnInit(): void {
    this.usuarioService.getMe().subscribe(respuesta => {
      this.usuarioLogueado = respuesta;
      const currentUrl = this.router.routerState.snapshot.url;
      this.capturarDatosTabla(this.getSectionFromUrl(currentUrl));
    });


  }

  capturarEventoSidenav(event: boolean) {
    this.drawerAbierto = event;
  }

  capturarDatosTabla(event: string) {
    this.showTable = event;
    switch(event) {
      case 'usuarios':
        let avatar = "";
        this.usuarioService.getAllUsers().subscribe(respuesta => {
          this.usuario = respuesta[0];
          this.datos.data = respuesta;
          this.datos.paginator = this.paginator;
          this.datos.sort = this.sort;
          this.displayedColumns = [];
          for(let key of Object.keys(this.usuario)) {
            let atributo = key;
            if(key == "avatar") {
              avatar = key;
            }else {
              this.displayedColumns.push(atributo.toString());

            }
          }
          this.displayedColumns.push(avatar);
        });
        break;
      case 'supermercados':
        this.supermercadoService.getAllSupermercados().subscribe(respuesta => {
          let totalSupermercados = respuesta.elementosTotales;
          this.supermercadoService.getTotalSupermercados(totalSupermercados).subscribe(response => {
            this.datos.data = response.contenido;
            this.supermercado = respuesta.contenido[0];
            this.datos.paginator = this.paginator;
            this.datos.sort = this.sort;
            this.displayedColumns = [];
            for(let key of Object.keys(this.supermercado)) {
              let atributo = key;
              this.displayedColumns.push(atributo.toString());
            }
          });
        });
        break;
      case 'productos':
        this.productoService.getAllProductos().subscribe(respuesta => {
          let totalProductos = respuesta.elementosTotales;
          this.productoService.getTotalProductos(totalProductos).subscribe(response => {
            this.producto = respuesta.contenido[0];
            this.datos.data = response.contenido;
            this.datos.paginator = this.paginator;
            this.datos.sort = this.sort;
            this.displayedColumns = [];
            for(let key of Object.keys(this.producto)) {
              let atributo = key;
              this.displayedColumns.push(atributo.toString());
            }
          });
        });
        break;
      case 'categorias':
        this.categoriaService.getAllCategorias().subscribe(respuesta => {
          let totalCategorias = respuesta.elementosTotales;
          this.categoriaService.getTotalCategorias(totalCategorias).subscribe(response => {
            this.datos.data = response.contenido;
          });
          this.categoria = respuesta.contenido[0];
          this.datos.paginator = this.paginator;
          this.datos.sort = this.sort;
          this.displayedColumns = [];
          for(let key of Object.keys(this.categoria)) {
            let atributo = key;
            this.displayedColumns.push(atributo.toString());
          }
        });
        break;
    }
  }

  getSectionFromUrl(url: string) {
    return url.split("/")[2];
  }
  
  abrirDialogNuevoProducto() {
    const dialogRef = this.dialog.open(CrearProductoComponent, {
      width: '500px',
      disableClose: true
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        const nuevoProducto = result.producto;
        const selectedFile = result.file;
        console.log(selectedFile);
        this.productoService.createProducto(nuevoProducto, selectedFile).subscribe(
          response => {
            this.commonService.mostrarSuccess(`${response.nombre} añadido`);
            this.ngOnInit();
          },
          error => {
            this.commonService.mostrarError(`${error}Selecciona una foto`);
          }
        )
      }
    },
    error => {
      this.commonService.mostrarError(`${error.error.message}`);
    });
  }

  getFoto(nombre: string) {
    return `${environment.API_BASE_URL}/file/download/${nombre}`
  }


}