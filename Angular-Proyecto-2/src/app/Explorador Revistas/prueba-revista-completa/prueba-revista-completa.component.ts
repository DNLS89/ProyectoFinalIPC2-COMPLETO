import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PerfilAutorService } from '../../../services/Servicios Cuenta/perfil-autor-service';
import { Revista } from '../../../entities/Revista';
import { Usuario } from '../../../entities/Usuario';
import { NgIf } from '@angular/common';
import { ExploradorRevistasService } from '../../../services/explorador-revistas.service';
import { PerfilAutorComponent } from '../../Cuenta/perfil-autor/perfil-autor.component';
import { MeGustaComponent } from '../../Gestion Revistas/me-gusta/me-gusta.component';
import { ComentarComponent } from '../../Gestion Revistas/comentar/comentar.component';
import { AnunciosMediaComponent } from '../../Anuncios/anuncios-media/anuncios-media.component';
import { Anuncio } from '../../../entities/Anuncio';
import { AnunciosService } from '../../../services/anuncios-service';

@Component({
  selector: 'app-prueba-revista-completa',
  standalone: true,
  imports: [NgIf, PerfilAutorComponent, MeGustaComponent, ComentarComponent, AnunciosMediaComponent],
  templateUrl: './prueba-revista-completa.component.html',
  styleUrl: './prueba-revista-completa.component.css'
})
export class PruebaRevistaCompletaComponent implements OnInit{

  revista!: Revista;
  numeroRevista!: number;
  autor!: Usuario;
  mostrarPerfilAutor: boolean = false;
  anunciosList: Anuncio[] = [];

  constructor(
    private perfilAutorService: PerfilAutorService, 
    private router: Router, 
    private route: ActivatedRoute,
    private exploradorRevistasService: ExploradorRevistasService,
    private anunciosService: AnunciosService
  ) {}

  ngOnInit(): void {
    this.numeroRevista = this.route.snapshot.params['numero'];
    //Primero extraer revista y ya con eso se puede tener el nombre del autor
    this.exploradorRevistasService
    .obtenerRevistaIndividual(this.numeroRevista)
    .subscribe({
      next: (revista: Revista) => {
        console.log(revista);
        console.log("Todo fue bien, procesando response...");
        this.revista = revista;

        //Extrae el perfil del autor
        this.extraerDatosAutor();
        this.extraerAnuncios();
        
      },
      error: (error: any) => {
        console.log(error);
      }
    });
    
  }

  extraerDatosAutor() {
    this.perfilAutorService
    .extraerPerfilAutor(this.revista.nombreAutor)
    .subscribe({
      next: (usuario: Usuario) => {
        
        console.log("Todo fue bien, procesando response...");
        this.autor = usuario;
        console.log(usuario);
        
      },
      error: (error: any) => {
        console.log(error);
      }
    });
  }

  extraerAnuncios() {
    this.anunciosService
      .obtenerAnunciosPorMostrar()
      .subscribe({
        next: (listado: Anuncio[]) => {
          console.log(listado);
          console.log("Todo fue bien, procesando response...");
          this.anunciosList = listado;
          this.comprobarAnuncios();
          
        },
        error: (error: any) => {
          console.log(error);
        }
      });
  }

  comprobarAnuncios() {
    for (let anuncio of this.anunciosList) {
      console.log(anuncio.tipo);
    }
  }

  booleanMostrarPerfil(): void {
    if (this.mostrarPerfilAutor == true) {
      this.mostrarPerfilAutor = false;
    } else {
      this.mostrarPerfilAutor = true;
    }
  }
}
