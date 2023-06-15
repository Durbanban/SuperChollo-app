export interface Producto {
    id: string;
    generico: string;
    nombre: string;
    precio: number;
    imagen: string;
}

export interface ProductoResponse {
    contenido: Producto[];
    paginasTotales: number;
    elementosTotales: number;
    paginaAnterior: number;
    paginaSiguiente: number;
    paginaActual: number;
}

export interface Supermarket {
    id: string;
    nombre: string;
    address: string;
}

export interface Rating {
    usuario: string;
    fecha: string;
    nota: number;
}

export interface ProductoDetails {
    id: string;
    generico: string;
    nombre: string;
    precio: number;
    imagen: string;
    categoria: string;
    autor: string;
    supermercados: Supermarket[];
    valoraciones: Rating[];
}

export class ProductoCreateRequest {
    generico: string;
    nombre: string;
    precio: number;
    imagen: string;
    categoria: string;
    supermercados: string;

    constructor(generico: string, nombre: string, precio: number, imagen: string, categoria: string, supermercados: string,) {
        this.generico = generico;
        this.nombre = nombre;
        this.precio = precio;
        this.imagen = imagen;
        this.categoria = categoria;
        this.supermercados = supermercados;
    }
}

