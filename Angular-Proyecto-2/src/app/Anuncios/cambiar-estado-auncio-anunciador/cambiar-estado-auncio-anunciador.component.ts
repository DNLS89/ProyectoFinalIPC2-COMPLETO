import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Anuncio } from '../../../entities/Anuncio';
import { AnunciosService } from '../../../services/anuncios-service';
import { AnuncioAnunciadorComponent } from "./anuncio-anunciador/anuncio-anunciador.component";

@Component({
  selector: 'app-cambiar-estado-auncio-anunciador',
  standalone: true,
  imports: [AnuncioAnunciadorComponent],
  templateUrl: './cambiar-estado-auncio-anunciador.component.html',
  styleUrl: './cambiar-estado-auncio-anunciador.component.css'
})
export class CambiarEstadoAuncioAnunciadorComponent {
  anunciosList: Anuncio[] = [];
  
  
  constructor(
    private anunciosService: AnunciosService, 
    private router: Router) 
    {}


  ngOnInit(): void {
    // la llamada al servicio
    
    this.anunciosService
    .obtenerAnunciosComprados()
    .subscribe({
      next: (listado: Anuncio[]) => {
        console.log(listado);
        console.log("Todo fue bien, procesando response...");
        this.anunciosList = listado;
        
      },
      error: (error: any) => {
        console.log(error);
        alert("NO HAY ANUNCIOS COMPRADOS");
      }
    });
  }

}
