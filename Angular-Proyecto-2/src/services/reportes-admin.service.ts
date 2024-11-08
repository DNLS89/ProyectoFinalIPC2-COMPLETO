import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Revista } from '../entities/Revista';
import { Observable } from 'rxjs';
import { RestConstants } from '../app/rest-constants';
import { Anuncio } from '../entities/Anuncio';

@Injectable({
  providedIn: 'root'
})
export class ReportesAdminService {
  restConstants: RestConstants;

  constructor(private httpClient: HttpClient) {
    this.restConstants = new RestConstants();
   }



  public obtenerSuscripciones(nombreUsuario : string): Observable<Revista[]> {
    return this.httpClient.get<Revista[]>(this.restConstants.getApiURL() + 'reportesAdmin/extraerSuscripciones/' + nombreUsuario );
  }

  public obtenerRecurrenciasSuscripciones(nombreUsuario : string, fechaInicio : string, fechaFin : string): Observable<Revista[]> {
    return this.httpClient.get<Revista[]>(this.restConstants.getApiURL() + 'reportesAdmin/extraerSuscripciones/recurrencias/' + nombreUsuario 
    + "/" + fechaInicio + "/" + fechaFin);
  }

  public obtenerComentarios(nombreUsuario : string): Observable<Revista[]> {
    return this.httpClient.get<Revista[]>(this.restConstants.getApiURL() + 'reportesAdmin/extraerComentarios/' + nombreUsuario );
  }

  public obtenerRecurrenciasComentarios(nombreUsuario : string, fechaInicio : string, fechaFin : string): Observable<Revista[]> {
    return this.httpClient.get<Revista[]>(this.restConstants.getApiURL() + 'reportesAdmin/extraerComentarios/recurrencias/' + nombreUsuario 
    + "/" + fechaInicio + "/" + fechaFin);
  }

  public obtenerAnuncios(nombreUsuario : string): Observable<Anuncio[]> {
    return this.httpClient.get<Anuncio[]>(this.restConstants.getApiURL() + 'reportesAdmin/extraerAnuncios/' + nombreUsuario );
  }

  public obtenerAnunciosMostrados(nombreUsuario : string): Observable<Anuncio[]> {
    return this.httpClient.get<Anuncio[]>(this.restConstants.getApiURL() + 'reportesAdmin/extraerAnunciosMostrados/' + nombreUsuario );
  }



}
