import { Component, inject, ViewChild } from '@angular/core';
import { FooterComponent } from '../footer/footer.component';
import { Router, RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { EditarPerfilComponent } from '../../Cuenta/editar-perfil/editar-perfil.component';
import { EditarPerfilService } from '../../../services/Servicios Cuenta/editar-perfil-service';
import { RolesDirective } from '../../roles.directive';
import { ExplorarRevistasComponent } from '../../Explorador Revistas/explorar-revistas/explorar-revistas.component';
import { AnunciosMediaComponent } from "../../Anuncios/anuncios-media/anuncios-media.component";

@Component({
  selector: 'app-menu-principal',
  standalone: true,
  imports: [RouterLink, RouterLinkActive, RouterOutlet, FooterComponent, EditarPerfilComponent, RolesDirective, AnunciosMediaComponent],
  templateUrl: './menu-principal.component.html',
  styleUrl: './menu-principal.component.css'
})
export class MenuPrincipalComponent {
  @ViewChild(EditarPerfilComponent) editarPerfilComponent?: EditarPerfilComponent
  
  

  nombreUsuarioCuenta = localStorage.getItem("nombreUsuario"); 

  myFunc() {
    localStorage.clear();
  }

}
