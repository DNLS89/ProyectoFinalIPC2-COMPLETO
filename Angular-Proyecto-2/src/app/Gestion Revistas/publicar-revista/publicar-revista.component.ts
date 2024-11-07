import { Component, NgModule } from '@angular/core';
import { GestionRevistaService } from '../../../services/gestion-revista-service';
import { Router } from '@angular/router';
import { DatePipe, NgIf } from '@angular/common';
import { FormsModule, NgModel } from '@angular/forms';


@Component({
  selector: 'app-publicar-revista',
  standalone: true,
  imports: [NgIf, FormsModule],
  templateUrl: './publicar-revista.component.html',
  styleUrl: './publicar-revista.component.css'
})
export class PublicarRevistaComponent {

  archivoRevista!: string;
  descripcion!: string;
  categoria: string = 'CIENCIA';
  etiquetas!: string;
  fechaPublicacion!: Date;
  mensaje!: string;

  constructor(
    private gestionRevistaService: GestionRevistaService, 
    private router: Router) 
    {}

    onSelected(value:string): void {
      this.categoria = value;
      
    }

    publicarRevista() {

      this.gestionRevistaService
      .publicarRevista
      (this.archivoRevista, this.descripcion, this.categoria, this.etiquetas, this.fechaPublicacion)
      .subscribe({
        next: () => {
          console.log("Todo fue bien, procesando response...");
          this.router.navigate(['/menu/publicarRevista']);
          this.mensaje = 'PUBLICACIÓN EXITOSA';
        },
        error: (error: any) => {
          console.log(error);
          this.mensaje = 'Revista NO Publicada';
          this.router.navigate(['/menu/publicarRevista']);
        }
      });
    }

}
