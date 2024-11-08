import { Component, ViewChild } from '@angular/core';
import { Revista } from '../../../entities/Revista';
import { ExploradorRevistasService } from '../../../services/explorador-revistas.service';
import { PerfilAutorComponent } from '../../Cuenta/perfil-autor/perfil-autor.component';
import { RevistaIndividualComponent } from '../revista-individual/revista-individual.component';
import { FormsModule } from '@angular/forms';
import { DefinirCostoRevistaComponent } from './definir-costo-revista/definir-costo-revista.component';

@Component({
  selector: 'app-asignar-precio-revista',
  standalone: true,
  imports: [RevistaIndividualComponent, FormsModule, DefinirCostoRevistaComponent],
  templateUrl: './asignar-precio-revista.component.html',
  styleUrl: './asignar-precio-revista.component.css'
})
export class AsignarPrecioRevistaComponent {

  @ViewChild(PerfilAutorComponent) perfilAutorComponent?: PerfilAutorComponent

  revistasList: Revista[] = [];
  
  

  constructor(private exploradorRevistasService: ExploradorRevistasService) {}

  ngOnInit(): void {
    // la llamada al servicio
    this.exploradorRevistasService
    .obtenerTodasRevistasSinPrecio(localStorage.getItem("nombreUsuario")!)
    .subscribe({
      next: (listado: Revista[]) => {
        console.log(listado);
        console.log("Todo fue bien, procesando response...");
        this.revistasList = listado;
        this.revistasFiltradasList = listado;
      },
      error: (error: any) => {
        console.log(error);
      }
    });
  }

  //LO SIGUIENTE CUMPLE LA FUNCIÓN DE FILTRAR SEGÚN TAGS Y CATEGORIAS
  _filteredCategory : string = "";
  _filteredTags : string = "";
  revistasFiltradasList: Revista[] = [];

  onSelectedCategory(value : string): void {
    this._filteredCategory = value;
	}

  onSelectedTag(value:string): void {
    this._filteredTags = value;
	}

  filtrar () {
    this.revistasFiltradasList = this.filterRevistas();
  }


  filterRevistas() {
    if (this.revistasList.length === 0 || (this._filteredCategory === "" && this._filteredTags === "")) {
      //Doesn't filters
      return this.revistasList;

    } else if (this._filteredCategory === "" && this._filteredTags !== "") {
      //Filters based on tags
      return this.revistasList.filter((revista) =>
        {
            return revista.tagsString === (this._filteredTags);
        })

    } else if (this._filteredTags=== "" && this._filteredCategory !== "") {
      //Filters based on categories
      return this.revistasList.filter((revista) =>
        {
            return revista.categoria === (this._filteredCategory);
        })

    } else {
      //Filtra en base a etiquetas y tags
      return this.revistasList.filter((revista) =>
      {
          return revista.categoria === this._filteredCategory && revista.tagsString === this._filteredTags;
      })
    }
  }

}
