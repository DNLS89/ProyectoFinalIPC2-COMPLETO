import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { RestConstants } from '../../app/rest-constants';
import { Usuario } from '../../entities/Usuario';

@Injectable({
  providedIn: 'root'
})
export class EditarPerfilService {
  restConstants: RestConstants;
  response: any;

  

  constructor(private httpClient: HttpClient) {
    this.restConstants = new RestConstants();
   }

   public obtenerPerfilEditar(): Observable<Usuario[]> {
    return this.httpClient.get<Usuario[]>(this.restConstants.getApiURL() + 'editarPerfil/' + localStorage.getItem('nombreUsuario'));
  }

  public modificarPerfil(nombreUsuario: string, contraseña: string, rol: string, foto: string, hobbies: string, descripcion: string, gustos: string): Observable<any> {

    return this.httpClient.post<any>(this.restConstants.getApiURL() + 'editarPerfil/' 
    + localStorage.getItem('nombreUsuario') + "/" +  nombreUsuario + "/" + contraseña + "/" + rol + "/" + foto + "/" + hobbies + "/" + descripcion + "/" + gustos, {});
  }
}
