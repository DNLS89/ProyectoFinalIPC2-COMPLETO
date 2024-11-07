import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { RestConstants } from '../app/rest-constants';
import { Usuario } from '../entities/Usuario';

@Injectable({
  providedIn: 'root'
})
export class GestionRevistaService {
  restConstants: RestConstants;
  response: any;

  

  constructor(private httpClient: HttpClient) {
    this.restConstants = new RestConstants();
   }


  public publicarRevista(archivoRevista: string, descripcion: string, categoria: string, etiquetas: string, fechaPublicacion: Date): Observable<any> {

    return this.httpClient.post<any>(this.restConstants.getApiURL() + 'gestionRevista/publicar/' 
    + localStorage.getItem('nombreUsuario') + "/" + archivoRevista + "/" + descripcion + "/" + categoria + "/" + etiquetas + "/" + fechaPublicacion, {});
  }


  public suscribir(numeroRevista: string, fechaSuscripcion: Date): Observable<any> {

    return this.httpClient.post<any>(this.restConstants.getApiURL() + 'gestionRevista/suscribir/' 
    + localStorage.getItem("nombreUsuario") + "/" + numeroRevista + "/" + fechaSuscripcion, {});
  }

  public comentar(numeroRevista: string, comentario: string) {
    return this.httpClient.post<any>(this.restConstants.getApiURL() + 'gestionRevista/comentar/' 
    + localStorage.getItem("nombreUsuario") + "/" + numeroRevista + "/" + comentario, {});
  }

  public meGusta(numeroRevista: string) {
    return this.httpClient.post<any>(this.restConstants.getApiURL() + 'gestionRevista/darMeGusta/' 
    + localStorage.getItem("nombreUsuario") + "/" + numeroRevista, {});
  }

  //Abajo es para bloquear o desactivar suscripciones, comentarios y me gustas
  public cambiarEstado(numeroRevista: string, estadoSuscripcion: boolean, proceso : string) {
    return this.httpClient.post<any>(this.restConstants.getApiURL() + 'gestionRevista/cambiarEstado/'
    + proceso + "/" + localStorage.getItem("nombreUsuario") + "/" + numeroRevista + "/" + estadoSuscripcion, {});
  }
}
