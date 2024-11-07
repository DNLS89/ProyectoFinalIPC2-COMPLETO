import { Component } from '@angular/core';
import { AnunciosService } from '../../../services/anuncios-service';
import { Anuncio } from '../../../entities/Anuncio';
import { Router } from '@angular/router';
import { ModificarAnuncioComponent } from './modificar-anuncio/modificar-anuncio.component';


@Component({
  selector: 'app-modificar',
  standalone: true,
  imports: [ModificarAnuncioComponent],
  templateUrl: './modificar.component.html',
  styleUrl: './modificar.component.css'
})
export class ModificarComponent {
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
