import { Component, EventEmitter, Input, Output } from '@angular/core';
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
  @Input() creditos!: number;
  @Output() valueChange = new EventEmitter<number>();

  constructor(
    private gestionRevistaService: GestionRevistaService, 
    private router: Router) 
    {}
  

  suscribir() : void {

    if (this.creditos < this.revista.costoSuscripcion) {
      alert("No se tienen suficientes créditos");
    } else {
      alert("SUFICIENTES CRÉDITOS")
      this.valueChange.emit(this.creditos - this.revista.costoSuscripcion); 
      this.ocultar();
    this.gestionRevistaService
    .suscribir
    (this.revista.numeroRevista, this.fechaSuscripcion, this.revista.costoSuscripcion)
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

  }

  ocultar() : void {
    this.isVisible = false;
  }


}
