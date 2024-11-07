import { Component, ViewChild } from '@angular/core';
import { HeaderComponent } from '../../POR ELIMINAR/header/header.component';
import { FooterComponent } from '../../Ventanas/footer/footer.component';
import { FiltradorRevistasComponent } from "../filtrador-revistas/filtrador-revistas.component";
import { RevistaIndividualComponent } from "../revista-individual/revista-individual.component";
import { NgFor } from '@angular/common';
import { Revista } from '../../../entities/Revista';
import { ExploradorRevistasService } from '../../../services/explorador-revistas.service';
import { PerfilAutorComponent } from '../../Cuenta/perfil-autor/perfil-autor.component';
import { SuscribirComponent } from '../../Gestion Revistas/suscribir/suscribir.component';


@Component({
  selector: 'app-explorar-revistas',
  standalone: true,
  imports: [NgFor, HeaderComponent, FooterComponent, FiltradorRevistasComponent, RevistaIndividualComponent, SuscribirComponent],
  templateUrl: './explorar-revistas.component.html',
  styleUrl: './explorar-revistas.component.css'
})
export class ExplorarRevistasComponent {

  @ViewChild(PerfilAutorComponent) perfilAutorComponent?: PerfilAutorComponent

  revistasList: Revista[] = [];
  

  constructor(private exploradorRevistasService: ExploradorRevistasService) {}

  ngOnInit(): void {
    // la llamada al servicio
    this.exploradorRevistasService
    .obtenerTodasRevistas(localStorage.getItem("nombreUsuario")!)
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
