import { NgIf } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { Anuncio } from '../../../entities/Anuncio';

@Component({
  selector: 'app-anuncios-media',
  standalone: true,
  imports: [NgIf],
  templateUrl: './anuncios-media.component.html',
  styleUrl: './anuncios-media.component.css'
})
export class AnunciosMediaComponent implements OnInit{

  hayAnuncioTexto: boolean = false;
  hayAnuncioTextoImagen: boolean = false;
  hayAnuncioVideo: boolean = false;


  ngOnInit(): void {
    this.comprobarAnuncios();
  }
  @Input({required: true})  anunciosList!: Anuncio[];

  comprobarAnuncios() {
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

  randomMath() { return Math.random() }

}
