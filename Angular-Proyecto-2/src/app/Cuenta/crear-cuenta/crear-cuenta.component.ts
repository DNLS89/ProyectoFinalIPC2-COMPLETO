import { Component, NgModule } from '@angular/core';
import { FooterComponent } from '../../Ventanas/footer/footer.component';
import { Router } from '@angular/router';
import { CrearCuentaService } from '../../../services/Servicios Cuenta/crear-cuenta-service';
import { NgIf } from '@angular/common';
import { FormsModule, NgModel } from '@angular/forms';

@Component({
  selector: 'app-crear-cuenta',
  standalone: true,
  imports: [FooterComponent, NgIf, FormsModule],
  templateUrl: './crear-cuenta.component.html',
  styleUrl: './crear-cuenta.component.css'
})
export class CrearCuentaComponent {

  
  nombreUsuario!: string;
  contrasena!: string;
  rol = "USUARIO";
  foto!: string;
  hobbies!: string;
  descripcion!: string;
  gustos!: string;
  mensaje!: string;
  

  constructor(private crearCuentaService: CrearCuentaService, private router: Router) {}

  onSelected(value:string): void {
    this.rol = value;
	}

  crearCuenta() {
    this.crearCuentaService
    .crearCuenta(this.nombreUsuario, this.contrasena, this.rol, this.foto, this.hobbies, this.descripcion, this.gustos)
    .subscribe({
      next: () => {
        console.log("Todo fue bien, procesando response...");
        localStorage.setItem('nombreUsuario', this.nombreUsuario);
        this.router.navigate(['/menu']);
      },
      error: (error: any) => {
        console.log(error);
        this.mensaje = 'USUARIO INGRESADO YA SE ENCUENTRA EN USO';
        this.router.navigate(['/crearCuenta']);
      }
    });
  }

}
