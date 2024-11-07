import { Component, Input } from '@angular/core';
import { Revista } from '../../../entities/Revista';
import { Router } from '@angular/router';
import { CommonModule, NgClass, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { GestionRevistaService } from '../../../services/gestion-revista-service';

@Component({
  selector: 'app-suscribir',
  standalone: true,
  imports: [NgIf, FormsModule, CommonModule],
  templateUrl: './suscribir.component.html',
  styleUrl: './suscribir.component.css'
})
export class SuscribirComponent {

  fechaSuscripcion!: Date;
  mensaje!: string;
  isVisible: boolean = true;

  @Input({required: true})
  revista!: Revista;

  constructor(
    private gestionRevistaService: GestionRevistaService, 
    private router: Router) 
    {}
  

  suscribir() : void {
    this.gestionRevistaService
    .suscribir
    (this.revista.numeroRevista, this.fechaSuscripcion)
    .subscribe({
      next: () => {
        console.log("Todo fue bien, procesando response...");
        
        this.router.navigate(['/menu/explorarRevistas']);
        this.mensaje = 'SUSCRIPCION EXITOSA';
        this.ocultar();
      },
      error: (error: any) => {
        console.log(error);
        this.mensaje = 'ERROR AL SUSCRIBIR';
        this.router.navigate(['/menu/explorarRevistas']);
      }
    });

  }

  ocultar() : void {
    this.isVisible = false;
  }


}
