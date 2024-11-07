import { Component, Input } from '@angular/core';
import { Anuncio } from '../../../../entities/Anuncio';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { AnunciosService } from '../../../../services/anuncios-service';

@Component({
  selector: 'app-anuncio',
  standalone: true,
  imports: [],
  templateUrl: './anuncio.component.html',
  styleUrl: './anuncio.component.css'
})
export class AnuncioComponent {
  @Input({required: true})
  anuncio!: Anuncio;

  constructor(
    private formBuilder: FormBuilder,
    private anunciosService: AnunciosService, 
    private router: Router) 
    {}

  cambiarEstadoAnuncio(idAnuncio: number) {

    this.anunciosService
    .modificarEstadoAnuncio
    (this.anuncio.idAnuncio, this.anuncio.estado)
    .subscribe({
      next: () => {
        console.log("Todo fue bien, procesando response...");
        //this.router.navigate(['/menu/gestorAnuncios/modificarAnuncios']);
        this.cambiarEstado();
        alert("Se cambió el estado del anuncio " + idAnuncio);

        

      },
      error: (error: any) => {
        console.log(error);
        //this.router.navigate(['/menu/gestorAnuncios/modificarAnuncios']);
        alert("NO se cambió el estado del anuncio " + idAnuncio);
      }
    });
  }

  cambiarEstado() {
    if (this.anuncio.estado == 1) {
      this.anuncio.estado = 0;
    } else {
      this.anuncio.estado = 1;
    }
  }
  

}
