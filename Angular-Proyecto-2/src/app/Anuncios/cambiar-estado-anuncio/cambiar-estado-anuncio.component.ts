import { Component } from '@angular/core';
import { AnunciosService } from '../../../services/anuncios-service';
import { Router } from '@angular/router';
import { Anuncio } from '../../../entities/Anuncio';
import { AnuncioComponent } from "./anuncio/anuncio.component";

@Component({
  selector: 'app-cambiar-estado-anuncio',
  standalone: true,
  imports: [AnuncioComponent],
  templateUrl: './cambiar-estado-anuncio.component.html',
  styleUrl: './cambiar-estado-anuncio.component.css'
})
export class CambiarEstadoAnuncioComponent {

  anunciosList: Anuncio[] = [];
  
  
  constructor(
    private anunciosService: AnunciosService, 
    private router: Router) 
    {}


  ngOnInit(): void {
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
  }
}
