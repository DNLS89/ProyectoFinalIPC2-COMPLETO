import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Revista } from '../entities/Revista';
import { Observable } from 'rxjs';
import { RestConstants } from '../app/rest-constants';

@Injectable({
  providedIn: 'root'
})
export class ReportesAdminService {
  restConstants: RestConstants;

  constructor(private httpClient: HttpClient) {
    this.restConstants = new RestConstants();
   }



  public obtenerSuscripciones(nombreUsuario : string): Observable<Revista[]> {
    return this.httpClient.get<Revista[]>(this.restConstants.getApiURL() + 'reportesAutor/extraerMeGustas/' + nombreUsuario );
  }

  public obtenerRecurrenciasSuscripciones(nombreUsuario : string, fechaInicio : string, fechaFin : string): Observable<Revista[]> {
    return this.httpClient.get<Revista[]>(this.restConstants.getApiURL() + 'reportesAutor/extraerMeGustas/recurrencias/' + nombreUsuario 
    + "/" + fechaInicio + "/" + fechaFin);
  }

}
