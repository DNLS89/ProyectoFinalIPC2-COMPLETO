import { Component, Input } from '@angular/core';
import { Revista } from '../../../entities/Revista';
import { NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { GestionRevistaService } from '../../../services/gestion-revista-service';

@Component({
  selector: 'app-me-gusta',
  standalone: true,
  imports: [NgIf, FormsModule],
  templateUrl: './me-gusta.component.html',
  styleUrl: './me-gusta.component.css'
})
export class MeGustaComponent {

  mensaje!: string;
  dioMeGusta: boolean = false;

  @Input({required: true})
  revista!: Revista;

  constructor(
    private gestionRevistaService: GestionRevistaService, 
    private router: Router) 
    {}

    darMeGustaRevista() {

      this.gestionRevistaService
      .meGusta(this.revista.numeroRevista)
      .subscribe({
        next: () => {
          console.log("Todo fue bien, procesando response...");
          this.router.navigate(['/menu/revistasSuscritas']);
          this.mensaje = 'Se dió "Me Gusta"';
          this.dioMeGusta = true;
          this.revista.meGustas+=1;
          
          
        },
        error: (error: any) => {
          console.log(error);
          this.mensaje = '"Me Gusta" NO completado';
          //this.router.navigate(['/menu/publicarRevista']);
        }
      });
    }

}
