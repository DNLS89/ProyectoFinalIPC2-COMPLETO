import { Component, Input } from '@angular/core';
import { Revista } from '../../../entities/Revista';
import { ActivatedRoute, Router } from '@angular/router';
import { PerfilAutorService } from '../../../services/Servicios Cuenta/perfil-autor-service';
import { Usuario } from '../../../entities/Usuario';
import { PerfilAutorComponent } from '../../Cuenta/perfil-autor/perfil-autor.component';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-revista-individual-completa',
  standalone: true,
  imports: [PerfilAutorComponent, NgIf],
  templateUrl: './revista-individual-completa.component.html',
  styleUrl: './revista-individual-completa.component.css'
})
export class RevistaIndividualCompletaComponent {
  
  @Input({required: true})
  revista!: Revista;
  autor!: Usuario;
  mostrarPerfilAutor: boolean = false;

  constructor(private perfilAutorService: PerfilAutorService, private router: Router) {}

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

  booleanMostrarPerfil(): void {
    if (this.mostrarPerfilAutor == true) {
      this.mostrarPerfilAutor = false;
    } else {
      this.mostrarPerfilAutor = true;
    }
  }

}
