import { Component, Input } from '@angular/core';
import { Revista } from '../../../entities/Revista';
import { GestionRevistaService } from '../../../services/gestion-revista-service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-comentar',
  standalone: true,
  imports: [FormsModule, NgIf],
  templateUrl: './comentar.component.html',
  styleUrl: './comentar.component.css'
})
export class ComentarComponent {

  comentario!: string;
  mensaje!: string;
  

  @Input({required: true})
  revista!: Revista;

  constructor(
    private gestionRevistaService: GestionRevistaService, 
    private router: Router) 
    {}


  comentarRevista() {

    this.gestionRevistaService
    .comentar(this.revista.numeroRevista, this.comentario)
    .subscribe({
      next: () => {
        console.log("Todo fue bien, procesando response...");
        //this.router.navigate(['/menu/publicarRevista']);
        this.mensaje = 'Comentario hecho SATISFACTORIAMENTE';
        this.comentario = "";
      },
      error: (error: any) => {
        console.log(error);
        this.mensaje = 'Comentario hecho INSATISFACTORIAMENTE';
        //this.router.navigate(['/menu/publicarRevista']);
      }
    });
  }

}
