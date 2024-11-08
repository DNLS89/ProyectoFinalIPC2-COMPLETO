import { NgIf } from '@angular/common';
import { ChangeDetectionStrategy, Component, Input, OnInit } from '@angular/core';
import { Anuncio } from '../../../entities/Anuncio';
import { Router } from '@angular/router';
import { AnunciosService } from '../../../services/anuncios-service';

@Component({
  selector: 'app-anuncios-media',
  standalone: true,
  imports: [NgIf],
  templateUrl: './anuncios-media.component.html',
  styleUrl: './anuncios-media.component.css',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class AnunciosMediaComponent implements OnInit{

  hayAnuncioTexto: boolean = false;
  hayAnuncioTextoImagen: boolean = false;
  hayAnuncioVideo: boolean = false;

  constructor(
    private router: Router,
    private anunciosService: AnunciosService) {}


  ngOnInit(): void {
    this.comprobarAnuncios();
  }
  @Input({required: true})  anunciosList!: Anuncio[];

  comprobarAnuncios() {
    console.log("Anuncios");
    console.log(this.anunciosList);
    for (let anuncio of this.anunciosList) {
      if (anuncio.tipo == "TEXTO") {
        this.hayAnuncioTexto = true;
      } else if (anuncio.tipo == "TEXTOeIMAGEN") {
        this.hayAnuncioTextoImagen = true;
      } else if (anuncio.tipo == "VIDEO") {
        this.hayAnuncioVideo = true;
      }
      
    }
  }

  valorAleatorio !: number;

  randomMath(numeroRevista : number, nombreAnunciador : string) { 
    console.log("Numeor revista")
    console.log(numeroRevista)
    this.valorAleatorio = Math.random();
    if (this.valorAleatorio  < 0.5) {

       this.publicitar(numeroRevista, nombreAnunciador);

      console.log(this.router.url);
    }
    return this.valorAleatorio; 
  }

  ruta: string = "";


  publicitar(numeroRevista : number, nombreAnunciador : string) {

    this.ruta = this.router.url;
    this.ruta = this.ruta.replace(/\//g, '-');
 
    this.anunciosService
      .publicitar
      (numeroRevista, nombreAnunciador, this.ruta)
      .subscribe({
        next: () => {
          console.log("Todo fue bien, procesando response...");
          //this.router.navigate(['/menu/gestorAnuncios']);
          
          //Reinicia los valores del formulario
          
        },
        error: (error: any) => {
          console.log(error);
          
          //this.router.navigate(['/menu/gestorAnuncios']);
        }
      });
  
  }


}
