import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Revista } from '../entities/Revista';
import { Observable } from 'rxjs';
import { RestConstants } from '../app/rest-constants';

@Injectable({
  providedIn: 'root'
})
export class ReportesAutorService {
  restConstants: RestConstants;

  constructor(private httpClient: HttpClient) {
    this.restConstants = new RestConstants();
   }




   public obtenerRevistasConComentarios(nombreUsuario : string): Observable<Revista[]> {
    return this.httpClient.get<Revista[]>(this.restConstants.getApiURL() + 'reportesAutor/extraerComentarios/' + nombreUsuario );
  }

  public obtenerSuscripciones(nombreUsuario : string): Observable<Revista[]> {
    return this.httpClient.get<Revista[]>(this.restConstants.getApiURL() + 'reportesAutor/extraerSuscripciones/' + nombreUsuario);
  }

  public obtenerRevistasConMeGustas(nombreUsuario : string): Observable<Revista[]> {
    return this.httpClient.get<Revista[]>(this.restConstants.getApiURL() + 'reportesAutor/extraerMeGustas/' + nombreUsuario );
  }

  public obtenerRecurrenciasRevistasConMeGustas(nombreUsuario : string, fechaInicio : string, fechaFin : string, numeroRevista : String): Observable<Revista[]> {
    return this.httpClient.get<Revista[]>(this.restConstants.getApiURL() + 'reportesAutor/extraerMeGustas/recurrencias/' + nombreUsuario 
    + "/" + fechaInicio + "/" + fechaFin + "/" + numeroRevista );
  }

}
