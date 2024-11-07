import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { RestConstants } from '../app/rest-constants';
import { Usuario } from '../entities/Usuario';

@Injectable({
  providedIn: 'root'
})
export class TiendaService {
  restConstants: RestConstants;
  response: any;

  

  constructor(private httpClient: HttpClient) {
    this.restConstants = new RestConstants();
   }

   public obtenerCreditos(): Observable<Usuario[]> {
    return this.httpClient.get<Usuario[]>(this.restConstants.getApiURL() + 'tienda/' + localStorage.getItem('nombreUsuario'));
  }

  public comprarCreditos(creditos: any): Observable<any> {

    return this.httpClient.post<any>(this.restConstants.getApiURL() + 'tienda/' 
    + localStorage.getItem('nombreUsuario') + "/" + creditos, {});
  }


  //Comprar Anuncios y demás


}
