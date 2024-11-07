import { Component } from '@angular/core';
import { ComentarComponent } from '../../Gestion Revistas/comentar/comentar.component';
import { MeGustaComponent } from '../../Gestion Revistas/me-gusta/me-gusta.component';
import { RevistaIndividualCompletaComponent } from '../revista-individual-completa/revista-individual-completa.component';
import { Revista } from '../../../entities/Revista';
import { ExploradorRevistasService } from '../../../services/explorador-revistas.service';
import { NgIf } from '@angular/common';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-revistas-suscritas',
  standalone: true,
  imports: [ComentarComponent, MeGustaComponent, RevistaIndividualCompletaComponent, NgIf, RouterLink],
  templateUrl: './revistas-suscritas.component.html',
  styleUrl: './revistas-suscritas.component.css'
})
export class RevistasSuscritasComponent {

  revistasList: Revista[] = [];
  mostrarPrevisualizacion: boolean = false;

  constructor(private exploradorRevistasService: ExploradorRevistasService) {}


  ngOnInit(): void {
    // la llamada al servicio
    this.exploradorRevistasService
    .obtenerRevistasSuscritas()
    .subscribe({
      next: (listado: Revista[]) => {
        console.log(listado);
        console.log("Todo fue bien, procesando response...");
        this.revistasList = listado;
      },
      error: (error: any) => {
        console.log(error);
      }
    });
  }

  booleanMostrarRevista() {
    if (this.mostrarPrevisualizacion == true) {
      this.mostrarPrevisualizacion = false;
    } else {
      this.mostrarPrevisualizacion = true;
    }
  }


}
