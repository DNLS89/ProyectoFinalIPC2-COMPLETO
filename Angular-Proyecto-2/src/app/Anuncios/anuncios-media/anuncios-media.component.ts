import { NgIf } from '@angular/common';
import { ChangeDetectionStrategy, Component, Input, OnInit } from '@angular/core';
import { Anuncio } from '../../../entities/Anuncio';
import { Router } from '@angular/router';

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

  constructor(private router: Router) {}


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

  randomMath() { 
    

    this.valorAleatorio = Math.random();
    if (this.valorAleatorio  < 0.5) {
      console.log(this.router.url);
    }
    return this.valorAleatorio; 
  }

}
