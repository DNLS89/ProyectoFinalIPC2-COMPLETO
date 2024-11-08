export interface Anuncio {


    estado: number,
    tipo: string;
    vigenciaString: string;
    vigenciaInt: number;
    fechaInicio: Date,
    fechaFin: Date,
    nombreUsuario: string,
    usuarioQueComproAnuncio: string;
    idAnuncio: number,
    idAnuncioString: string,
    costoAnuncio: number;
    costoOcultacion: number
    
}