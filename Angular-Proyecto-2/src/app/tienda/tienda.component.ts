import { Component } from '@angular/core';
import { TiendaService } from '../../services/tienda-service';
import { Router } from '@angular/router';
import { Usuario } from '../../entities/Usuario';
import { NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-tienda',
  standalone: true,
  imports: [NgIf, FormsModule],
  templateUrl: './tienda.component.html',
  styleUrl: './tienda.component.css'
})
export class TiendaComponent {

  perfilUsuario!: Usuario;
  creditosCuenta!: number;
  creditosPorComprar!: number;
  mensaje!: string;

  constructor(
    private tiendaService: TiendaService, 
    private router: Router) 
    {}


    ngOnInit(): void {

      this.tiendaService
      .obtenerCreditos()
     .subscribe({
       next: (usuario: any) => {
         console.log("Todo fue bien, procesando response...");
         this.perfilUsuario = usuario;
         console.log(this.perfilUsuario);
         this.creditosCuenta = this.perfilUsuario.cartera;
 
       },
       error: (error: any) => {
         console.log(error);
       }
     }); 
     
   }
 
   comprarCreditos() {
     this.tiendaService
     .comprarCreditos(this.creditosPorComprar)
     .subscribe({
       next: () => {
         console.log("Todo fue bien, procesando response...");
         this.mensaje = 'RECARGA EXITOSA';
         this.router.navigate(['/menu/tienda']);
         this.creditosCuenta = this.creditosCuenta + this.creditosPorComprar;
       },
       error: (error: any) => {
         console.log(error);
         this.mensaje = 'RECARGA NO COMPLETADA';
         this.router.navigate(['/menu/tienda']);
       }
     });
   }

}
