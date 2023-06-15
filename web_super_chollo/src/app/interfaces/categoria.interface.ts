export interface Categoria {
    id: string;
    nombre: string;
}

export interface CategoriaResponse {
    contenido: Categoria[];
    paginasTotales: number;
    elementosTotales: number;
    paginaAnterior: number;
    paginaSiguiente: number;
    paginaActual: number;
}

export interface Catalogo {
    id: string;
    generico: string;
    nombre: string;
    precio: number;
}

export interface CategoriaDetails {
    id: string;
    nombre: string;
    productos: Catalogo[];
}