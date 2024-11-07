import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RestConstants } from '../../app/rest-constants';
import { Usuario } from '../../entities/Usuario';

@Injectable({
  providedIn: 'root'
})
export class CrearCuentaService {
  restConstants: RestConstants;


  constructor(private httpClient: HttpClient) {
    this.restConstants = new RestConstants();
   }

  public crearCuenta(nombreUsuario: string, contraseña: string, rol: string, foto: string, hobbies: string, descripcion: string, gustos: string): Observable<any> {

    return this.httpClient.post<any>(this.restConstants.getApiURL() + 'crearCuenta/' 
    + nombreUsuario + "/" + contraseña + "/" + rol + "/" + foto + "/" + hobbies + "/" + descripcion + "/" + gustos, {});
  }
}
