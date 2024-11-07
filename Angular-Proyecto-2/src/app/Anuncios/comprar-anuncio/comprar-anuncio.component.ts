import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { Anuncio } from '../../../entities/Anuncio';
import { AnunciosService } from '../../../services/anuncios-service';
import { AnuncioComprarComponent } from "./anuncio-comprar/anuncio-comprar.component";
import { TiendaService } from '../../../services/tienda-service';
import { Usuario } from '../../../entities/Usuario';

@Component({
  selector: 'app-comprar-anuncio',
  standalone: true,
  imports: [FormsModule, AnuncioComprarComponent],
  templateUrl: './comprar-anuncio.component.html',
  styleUrl: './comprar-anuncio.component.css'
})
export class ComprarAnuncioComponent {
  fechaCompraAnuncio!: Date;
  anunciosList: Anuncio[] = [];
  perfilUsuario!: Usuario;
  creditosCuenta!: number;

  constructor(
    private anunciosService: AnunciosService, 
    private tiendaService: TiendaService, 
    private router: Router) 
    {}


  ngOnInit(): void {
    // la llamada al servicio
    this.anunciosService
    .obtenerTodosAnuncios()
    .subscribe({
      next: (listado: Anuncio[]) => {
        console.log(listado);
        console.log("Todo fue bien, procesando response...");
        this.anunciosList = listado;
        
      },
      error: (error: any) => {
        console.log(error);
      }
    });

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

  //Abajo es para cambiar el valor de los créditos desde el child component
  updateParentValue(newValue: number) {
    this.creditosCuenta = newValue;
  }
}
