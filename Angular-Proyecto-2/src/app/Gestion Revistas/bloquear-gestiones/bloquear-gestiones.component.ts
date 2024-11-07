import { Component, Input } from '@angular/core';
import { Revista } from '../../../entities/Revista';
import { Router } from '@angular/router';
import { GestionRevistaService } from '../../../services/gestion-revista-service';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-bloquear-gestiones',
  standalone: true,
  imports: [NgIf],
  templateUrl: './bloquear-gestiones.component.html',
  styleUrl: './bloquear-gestiones.component.css'
})
export class BloquearGestionesComponent {
  @Input({required: true})
  revista!: Revista;
  mensaje!: string;

  constructor(
    private gestionRevistaService: GestionRevistaService, 
    private router: Router) 
    {}

  cambiarEstado(estado : boolean, proceso : string) {
    this.gestionRevistaService
    .cambiarEstado(this.revista.numeroRevista, estado, proceso)
    .subscribe({
      next: () => {
        console.log("Todo fue bien, procesando response...");
        this.router.navigate(['/menu/revistasPublicadas']);

        if (proceso == "Suscripciones") {
          if (this.revista.estadoSuscripcion == true) {
            this.revista.estadoSuscripcion = false;
            this.mensaje = 'El estado de "' + proceso + '" se cambió CORRECTAMENTE a "DESHABILITADO"';
            
          } else {
            this.revista.estadoSuscripcion = true;
            this.mensaje = 'El estado de "' + proceso + '" se cambió CORRECTAMENTE a "HABILITADO"';
          }
        } else if (proceso == "Comentarios") {
          if (this.revista.estadoComentarios == true) {
            this.revista.estadoComentarios = false;
            this.mensaje = 'El estado de "' + proceso + '" se cambió CORRECTAMENTE a "DESHABILITADO"';
            
          } else {
            this.revista.estadoComentarios = true;
            this.mensaje = 'El estado de "' + proceso + '" se cambió CORRECTAMENTE a "HABILITADO"';
          }
        } else if (proceso == "MeGustas") {
          if (this.revista.estadoMeGustas == true) {
            this.revista.estadoMeGustas = false;
            this.mensaje = 'El estado de "' + proceso + '" se cambió CORRECTAMENTE a "DESHABILITADO"';
            
          } else {
            this.revista.estadoMeGustas = true;
            this.mensaje = 'El estado de "' + proceso + '" se cambió CORRECTAMENTE a "HABILITADO"';
          }
        }

      },
      error: (error: any) => {
        console.log(error);
        this.mensaje = 'El estado NO pudo cambiarse';
        this.router.navigate(['/menu/revistasPublicadas']);
      }
    });
  }

}
