import { Component, input, Input } from '@angular/core';
import { HeaderComponent } from '../../POR ELIMINAR/header/header.component';
import { FooterComponent } from '../../Ventanas/footer/footer.component';
import { Usuario } from '../../../entities/Usuario';

@Component({
  selector: 'app-perfil-autor',
  standalone: true,
  imports: [HeaderComponent, FooterComponent],
  templateUrl: './perfil-autor.component.html',
  styleUrl: './perfil-autor.component.css'
})
export class PerfilAutorComponent {

  perfilAutor !: Usuario;
   nombreUsuario!: string;
   foto!: string;
   hobbies!: string;
   descripcion!: string;
   gustos!: string;
   

  @Input({required: true})  autor!: Usuario;



   constructor() {
      
  } 
}
