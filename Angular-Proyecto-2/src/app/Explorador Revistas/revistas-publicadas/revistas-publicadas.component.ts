import { Component } from '@angular/core';
import { RevistaIndividualComponent } from '../revista-individual/revista-individual.component';
import { Revista } from '../../../entities/Revista';
import { ExploradorRevistasService } from '../../../services/explorador-revistas.service';
import { BloquearGestionesComponent } from '../../Gestion Revistas/bloquear-gestiones/bloquear-gestiones.component';

@Component({
  selector: 'app-revistas-publicadas',
  standalone: true,
  imports: [RevistaIndividualComponent, BloquearGestionesComponent],
  templateUrl: './revistas-publicadas.component.html',
  styleUrl: './revistas-publicadas.component.css'
})
export class RevistasPublicadasComponent {

  revistasPublicadasList: Revista[] = [];

  constructor(private exploradorRevistasService: ExploradorRevistasService) {}

  ngOnInit(): void {
    // la llamada al servicio
    this.exploradorRevistasService
    .obtenerRevistasPublicadas()
    .subscribe({
      next: (listado: Revista[]) => {
        console.log("Todo fue bien, procesando response...");
        this.revistasPublicadasList = listado;
      },
      error: (error: any) => {
        console.log(error);
      }
    });
  }

}
