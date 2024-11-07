import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Revista } from '../../entities/Revista';
import { Observable } from 'rxjs';

import { RestConstants } from '../../app/rest-constants';
import { Usuario } from '../../entities/Usuario';

@Injectable({
  providedIn: 'root'
})
export class InicioSesionService {
  restConstants: RestConstants;

  constructor(private httpClient: HttpClient) {
    this.restConstants = new RestConstants();
   }

   public verificarCredenciales(nombreUsuario: string, contraseña: string): Observable<Usuario> {
    return this.httpClient.get<Usuario>(this.restConstants.getApiURL() + 'iniciarSesion' + '/' + nombreUsuario + '/' + contraseña);
  }
}
