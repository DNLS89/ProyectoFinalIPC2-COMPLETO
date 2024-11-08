import { Component } from '@angular/core';
import { Anuncio } from '../../../entities/Anuncio';
import { ReportesAdminService } from '../../../services/reportes-admin.service';
import { DatePipe, NgFor, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-reporte-ganacias-por-anunciante',
  standalone: true,
  imports: [NgFor, DatePipe, NgIf, FormsModule],
  templateUrl: './reporte-ganacias-por-anunciante.component.html',
  styleUrl: './reporte-ganacias-por-anunciante.component.css'
})
export class ReporteGanaciasPorAnuncianteComponent {
  constructor(private reportesAdminService: ReportesAdminService) {}

  anunciosList: Anuncio[] = [];

  tipo: string = "";
  vigencia: string = "";

  onSelectedType(value:string): void {
    this.tipo = value;    
  }

  onSelectedVigencia(value:string): void {
    this.vigencia = value;    
  }
  

  ngOnInit(): void {
    // la llamada al servicio
    this.reportesAdminService
    .obtenerAnuncios(localStorage.getItem("nombreUsuario")!)
    .subscribe({
      next: (listado: Anuncio[]) => {
        console.log("1Todo fue bien, procesando response...");
        this.anunciosList = listado;
        console.log(this.anunciosList);
        /* console.log(this.suscripcionesList); */
        this.anuncioFiltradosList = listado;
        this.extractUniqueNombres();
        this.anuncioFiltradosList = this.nombreAnunciadoresUnicoList;
      },
      error: (error: any) => {
        console.log(error);
      }
    });
  }

  nombreAnunciadoresUnicoList: Anuncio[] = [];

  extractUniqueNombres(): void {
    const seenIds = new Set<string>(); // Lleva un registro de las ID'S
    this.nombreAnunciadoresUnicoList = this.anunciosList.filter(entity => {
      if (!seenIds.has(entity.usuarioQueComproAnuncio)) {
        seenIds.add(entity.usuarioQueComproAnuncio); // AÑADE UNA ID AL SET
        return true; // INCLUYE LA ENTIDAD
      }
      return false; // EXCLUYE DUPLICADOS
    });
    console.log("ARREGLO CON NOMBRE ÚNICOS")
    console.log(this.nombreAnunciadoresUnicoList);
  }

  _filteredNombre : string = "";

  onSelectedNombre(value : string): void {
    this._filteredNombre = value;
    console.log("Valor seleccionado: " + this._filteredNombre);
  }



  //LO SIGUIENTE FILTRA EN EL EXPLORADOR DE REVISTAS HAY QUE CAMBIARLO

    //LO SIGUIENTE CUMPLE LA FUNCIÓN DE FILTRAR SEGÚN TAGS Y CATEGORIAS
    /* _filteredNumeroRevista : string = ""; */
    anuncioFiltradosList: Anuncio[] = [];
  
    /* onSelectedNumeroRevista(value : string): void {
      this._filteredNumeroRevista = value;
      console.log("Valor seleccionado: " + this._filteredNumeroRevista);
    } */
  
    filtrar () {


      this.anuncioFiltradosList = this.filterRevistas();
    }
  
  
    filterRevistas() {
      if (this.nombreAnunciadoresUnicoList.length === 0 || this._filteredNombre === "") {
        //No filtra
        return this.nombreAnunciadoresUnicoList;
  
      } else if (this._filteredNombre !== "") {
        console.log(this.anunciosList);

        return this.nombreAnunciadoresUnicoList.filter((anuncio) =>
        {
          //Cuando no cumple con ningún elemento
          /* return this.anunciosList ; */
            return anuncio.usuarioQueComproAnuncio === this._filteredNombre;
        })
        
      } else {
        //Filtra en BASE A FECHAS Y NUMERO DE REVISTA
          return this.nombreAnunciadoresUnicoList;
      }

    }

}
