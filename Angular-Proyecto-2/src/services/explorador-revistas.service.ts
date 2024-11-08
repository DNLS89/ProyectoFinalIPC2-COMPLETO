import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Revista } from '../entities/Revista';
import { Observable } from 'rxjs';
import { RestConstants } from '../app/rest-constants';

@Injectable({
  providedIn: 'root'
})
export class ExploradorRevistasService {
  restConstants: RestConstants;

  constructor(private httpClient: HttpClient) {
    this.restConstants = new RestConstants();
   }

   public obtenerTodasRevistas(nombreUsuario : string): Observable<Revista[]> {
    return this.httpClient.get<Revista[]>(this.restConstants.getApiURL() + 'explorarRevistas/' + nombreUsuario );
  }

  public obtenerTodasRevistasSinPrecio(nombreUsuario : string): Observable<Revista[]> {
    return this.httpClient.get<Revista[]>(this.restConstants.getApiURL() + 'explorarRevistas/sinPrecio/' + nombreUsuario );
  }

  public obtenerRevistasPublicadas(): Observable<Revista[]> {
    return this.httpClient.get<Revista[]>(this.restConstants.getApiURL() + 'explorarRevistas/revistasPublicadas/' + localStorage.getItem("nombreUsuario"));
  }

  public obtenerRevistasSuscritas(): Observable<Revista[]> {
    return this.httpClient.get<Revista[]>(this.restConstants.getApiURL() + 'explorarRevistas/revistasSuscritas/' + localStorage.getItem("nombreUsuario"));
  }

  public obtenerRevistaIndividual(numeroRevista : number): Observable<Revista> {
    return this.httpClient.get<Revista>(this.restConstants.getApiURL() + 'explorarRevistas/revistaIndividual/' + 
    localStorage.getItem("nombreUsuario") + "/" + numeroRevista);
  }

}
