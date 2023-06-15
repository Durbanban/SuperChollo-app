import { Catalogo } from "./categoria.interface"

export interface Supermercado{
    id: string;
    nombre: string;
    address: string;
}

export interface SupermercadoResponse {
    contenido: Supermercado[];
    paginasTotales: number;
    elementosTotales: number;
    paginaAnterior: number;
    paginaSiguiente: number;
    paginaActual: number;
}

export interface Seguidor {
    username: string;
    avatar: string;
    fullName: string;
}

export interface SupermercadoDetails {
    id: string;
    nombre: string;
    address: string;
    seguidores: Seguidor[];
    productos: Catalogo[];
}