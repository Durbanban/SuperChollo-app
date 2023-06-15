import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Categoria, CategoriaResponse } from 'src/app/interfaces/categoria.interface';
import { ProductoCreateRequest } from 'src/app/interfaces/producto.interface';
import { Supermercado, SupermercadoResponse } from 'src/app/interfaces/supermercado.interface';
import { CategoriaService } from 'src/app/services/categoria.service';
import { ProductoService } from 'src/app/services/producto.service';
import { SupermercadoService } from 'src/app/services/supermercado.service';

@Component({
  selector: 'app-crear-producto',
  templateUrl: './crear-producto.component.html',
  styleUrls: ['./crear-producto.component.css']
})
export class CrearProductoComponent implements OnInit {


  producto: ProductoCreateRequest = {
    generico: "",
    nombre: "",
    precio: 0,
    imagen: "",
    categoria: "",
    supermercados: "",
  };

  pictureSelected: boolean = false;

  supermercadosSendList = [];

  categoriaList: Categoria[] = [];

  supermercadoList: Supermercado[] = [];

  selectedFile: File | undefined = undefined;

  constructor(
    public dialogRef: MatDialogRef<CrearProductoComponent>,
    private productoService: ProductoService,
    private categoriaService: CategoriaService,
    private supermercadoService: SupermercadoService,
    @Inject(MAT_DIALOG_DATA) public datosProducto: ProductoCreateRequest
  ) {}

  ngOnInit(): void {
    this.pictureSelected = false;
    this.categoriaService.getAllCategorias().subscribe(respuesta => {
      let totalCategorias = respuesta.elementosTotales;
      this.categoriaService.getTotalCategorias(totalCategorias).subscribe(response => {
        this.categoriaList = response.contenido;
      });
      this.supermercadoService.getAllSupermercados().subscribe(respuesta => {
        let totalSupermercados = respuesta.elementosTotales;
        this.supermercadoService.getTotalSupermercados(totalSupermercados).subscribe(response => {
          this.supermercadoList = response.contenido;
        });
      });
    });
  }

  onFileSelected(event: any) {
    if(this.selectedFile == undefined && event.target.files.length == 0) {
      this.selectedFile = undefined;
      this.pictureSelected = false;
    }else if(this.selectedFile != undefined && event.target.files.length == 0) {
      this.selectedFile = undefined;
      this.pictureSelected = false;
    }else {
      this.selectedFile = event.target.files[0];
      this.pictureSelected = true;

    }
  }

  createProducto() {
    const fileName = this.selectedFile!.name;


    let supermercados = "";
    let lastSupermercado = this.supermercadosSendList.pop();
    this.supermercadosSendList.forEach(supermerc => {
      supermercados += supermerc + ", ";
    });
    supermercados += lastSupermercado;

    const nuevoProducto: ProductoCreateRequest = {
      generico: this.producto.generico,
      nombre: this.producto.nombre,
      precio: this.producto.precio,
      imagen: fileName,
      categoria: this.producto.categoria,
      supermercados: supermercados
    };

    
      
    this.dialogRef.close({ producto: nuevoProducto, file: this.selectedFile });
  }

  cancel() {
    this.dialogRef.close();
  }

}
