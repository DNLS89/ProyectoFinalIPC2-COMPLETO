import { Injectable } from '@angular/core';
import { RestConstants } from '../app/rest-constants';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Anuncio } from '../entities/Anuncio';

@Injectable({
  providedIn: 'root'
})
export class AnunciosService {
  restConstants: RestConstants;
  response: any;

  constructor(private httpClient: HttpClient) {
    this.restConstants = new RestConstants();
   }


   public obtenerTodosAnuncios(): Observable<Anuncio[]> {
    return this.httpClient.get<Anuncio[]>(this.restConstants.getApiURL() + 'anuncio/explorarAnuncios/' 
    + localStorage.getItem("nombreUsuario"));
  } 

  public obtenerAnunciosComprados(): Observable<Anuncio[]> {
    return this.httpClient.get<Anuncio[]>(this.restConstants.getApiURL() + 'anuncio/explorarAnunciosComprados/' 
    + localStorage.getItem("nombreUsuario"));
  } 

  public obtenerAnunciosPorMostrar(): Observable<Anuncio[]> {
    return this.httpClient.get<Anuncio[]>(this.restConstants.getApiURL() + 'anuncio/anunciosPorMostrar/' 
    + localStorage.getItem("nombreUsuario"));
  } 

  public crearAnuncio(tipoAnuncio: string, vigenciaAnuncio: string, costoAnuncio: number, costoOcultacion: number): Observable<any> {
    return this.httpClient.post<any>(this.restConstants.getApiURL() + 'anuncio/crear/' 
    + localStorage.getItem("nombreUsuario") + "/" + tipoAnuncio + "/" + vigenciaAnuncio + "/" + costoAnuncio + "/" + costoOcultacion, {});
  }

  public modificarAnuncio(idAnuncio: number, tipoAnuncio: string, vigenciaAnuncio: string, costoAnuncio: number, costoOcultacion: number): Observable<any> {
    return this.httpClient.post<any>(this.restConstants.getApiURL() + 'anuncio/modificar/' 
    + localStorage.getItem("nombreUsuario") + "/" + idAnuncio + "/" + tipoAnuncio + "/" + vigenciaAnuncio + "/" + costoAnuncio + "/" + costoOcultacion, {});
  }

  public eliminarAnuncio(idAnuncio: number): Observable<any> {
    return this.httpClient.post<any>(this.restConstants.getApiURL() + 'anuncio/eliminar/' 
    + localStorage.getItem("nombreUsuario") + "/" + idAnuncio, {});
  }

  public modificarEstadoAnuncio(idAnuncio: number, estadoAnuncio: number): Observable<any> {
    return this.httpClient.post<any>(this.restConstants.getApiURL() + 'anuncio/cambiarEstado/' 
    + localStorage.getItem("nombreUsuario") + "/" + idAnuncio + "/" + estadoAnuncio, {});
  }

  public comprarAnuncio(idAnuncio: number, fechaCompra: Date): Observable<any> {
    return this.httpClient.post<any>(this.restConstants.getApiURL() + 'anuncio/comprar/' 
    + localStorage.getItem("nombreUsuario") + "/" + idAnuncio + "/" + fechaCompra, {});
  }
}
