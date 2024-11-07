import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Revista } from '../../entities/Revista';
import { Observable } from 'rxjs';

import { RestConstants } from '../../app/rest-constants';

@Injectable({
  providedIn: 'root'
})
export class PerfilAutorService {
  restConstants: RestConstants;

  constructor(private httpClient: HttpClient) {
    this.restConstants = new RestConstants();
   }

   public extraerPerfilAutor(nombreAutor: string): Observable<any> {
    return this.httpClient.get<any>(this.restConstants.getApiURL() + 'perfilAutor' + '/' + localStorage.getItem("nombreUsuario") + "/" + nombreAutor);
  }
}