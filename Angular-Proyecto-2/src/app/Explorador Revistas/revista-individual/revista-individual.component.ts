import { Component, Input, NgModule, ViewChild } from '@angular/core';
import { Revista } from '../../../entities/Revista';
import { Router, RouterLink, RouterLinkActive, RouterModule, RouterOutlet } from '@angular/router';
import { PerfilAutorService } from '../../../services/Servicios Cuenta/perfil-autor-service';
import { PerfilAutorComponent } from '../../Cuenta/perfil-autor/perfil-autor.component';
import { Usuario } from '../../../entities/Usuario';
import { NgIf } from '@angular/common';
import { NgModel } from '@angular/forms';

@Component({
  selector: 'app-revista-individual',
  standalone: true,
  imports: [RouterLink, RouterLinkActive, RouterOutlet, PerfilAutorComponent, NgIf],
  templateUrl: './revista-individual.component.html',
  styleUrl: './revista-individual.component.css'
})
export class RevistaIndividualComponent {
  @ViewChild(PerfilAutorComponent) perfilAutorComponent?: PerfilAutorComponent
  
  @Input({required: true})
  revista!: Revista;

  autor!: Usuario;
  mostrarPerfilAutor: boolean = false;
  mostrarPrevisualizacion: boolean = false;

  constructor(private perfilAutorService: PerfilAutorService, private router: Router) {}

  booleanMostrarPerfil() {
    if (this.mostrarPerfilAutor == true) {
      this.mostrarPerfilAutor = false;
    } else {
      this.mostrarPerfilAutor = true;
    }
  }

  booleanMostrarPrevisualizacion() {
    if (this.mostrarPrevisualizacion == true) {
      this.mostrarPrevisualizacion = false;
    } else {
      this.mostrarPrevisualizacion = true;
    }
  }


  ngOnInit(): void {
    // la llamada al servicio
    this.perfilAutorService
    .extraerPerfilAutor(this.revista.nombreAutor)
    .subscribe({
      next: (usuario: Usuario) => {
        console.log("Todo fue bien, procesando response...");
        this.autor = usuario;
        
      },
    });
  }


}
