import { Component, ViewChild } from '@angular/core';
import { FormsModule, NgModel } from '@angular/forms';
import { Router, RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { AnunciosService } from '../../../services/anuncios-service';
import { Anuncio } from '../../../entities/Anuncio';
import { AnuncioComponent } from '../cambiar-estado-anuncio/anuncio/anuncio.component';
import { CrearAnuncioComponent } from '../crear-anuncio/crear-anuncio.component';
import { ComprarAnuncioComponent } from '../comprar-anuncio/comprar-anuncio.component';
import { CambiarEstadoAnuncioComponent } from '../cambiar-estado-anuncio/cambiar-estado-anuncio.component';
import { ModificarAnuncioComponent } from "../modificar/modificar-anuncio/modificar-anuncio.component";
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-gestor-anuncios',
  standalone: true,
  imports: [FormsModule, AnuncioComponent, CrearAnuncioComponent, ComprarAnuncioComponent, CambiarEstadoAnuncioComponent, ModificarAnuncioComponent, NgIf, RouterLink, RouterLinkActive, RouterOutlet],
  templateUrl: './gestor-anuncios.component.html',
  styleUrl: './gestor-anuncios.component.css'
})
export class GestorAnunciosComponent {

  /* anunciosList: Anuncio[] = []; */
  
  
  constructor(
    private anunciosService: AnunciosService, 
    private router: Router) 
    {}
  
    /* ngOnInit(): void {
      // la llamada al servicio
      this.anunciosService
      .obtenerTodosAnuncios()
      .subscribe({
        next: (listado: Anuncio[]) => {
          console.log(listado);
          console.log("Todo fue bien, procesando response...");
          this.anunciosList = listado;
          
        },
        error: (error: any) => {
          console.log(error);
        }
      });
    } */
}
