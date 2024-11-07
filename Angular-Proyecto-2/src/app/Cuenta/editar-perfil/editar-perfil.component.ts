import { Component, NgModule } from '@angular/core';
import { HeaderComponent } from '../../POR ELIMINAR/header/header.component';
import { FooterComponent } from '../../Ventanas/footer/footer.component';
import { Usuario } from '../../../entities/Usuario';
import { EditarPerfilService } from '../../../services/Servicios Cuenta/editar-perfil-service';
import { NgIf } from '@angular/common';
import { FormsModule, NgModel, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterLink, RouterModule } from '@angular/router';
import {FormControl, FormGroup} from '@angular/forms';
import { interval, Subject, Subscription, takeUntil } from 'rxjs';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';

@Component({
  selector: 'app-editar-perfil',
  standalone: true,
  imports: [HeaderComponent, FooterComponent, NgIf, FormsModule, RouterLink, RouterModule],
  templateUrl: './editar-perfil.component.html',
  styleUrl: './editar-perfil.component.css'
})
export class EditarPerfilComponent {


  perfilUsuario!: Usuario;
  nombreUsuario!: string;
  contrasena!: string;
  rol = "USUARIO";
  foto!: string;
  hobbies!: string;
  descripcion!: string;
  gustos!: string;
  mensaje!: string;

  notifier = new Subject();

  sub!: Subscription;

  constructor(
    private editarPerfilService: EditarPerfilService, 
    private router: Router) 
    {}
  
	onSelected(value:string): void {
    this.rol = value;
		
	}

  ngOnInit(): void {

     this.sub = this.editarPerfilService
    .obtenerPerfilEditar()
    .subscribe({
      next: (usuario: any) => {
        console.log("Todo fue bien, procesando response...");

        this.perfilUsuario = usuario;
        this.nombreUsuario = this.perfilUsuario.nombreUsuario;
        this.hobbies = this.perfilUsuario.hobbies;
        this.descripcion = this.perfilUsuario.descripcion;
        this.gustos = this.perfilUsuario.gustos;

      },
      error: (error: any) => {
        console.log(error);
      }
    }); 
    
  }

  editarPerfil() {
    this.editarPerfilService
    .modificarPerfil(this.nombreUsuario, this.contrasena, this.rol, this.foto, this.hobbies, this.descripcion, this.gustos)
    .subscribe({
      next: () => {
        console.log("Todo fue bien, procesando response...");
        localStorage.setItem('nombreUsuario', this.nombreUsuario);
        this.router.navigate(['/menu']);
      },
      error: (error: any) => {
        console.log(error);
        this.mensaje = 'USUARIO INGRESADO YA SE ENCUENTRA EN USO';
        this.router.navigate(['/menu/editarPerfil']);
      }
    });
  }
}
