import { Component } from '@angular/core';
import { Anuncio } from '../../../entities/Anuncio';
import { ReportesAdminService } from '../../../services/reportes-admin.service';
import { DatePipe, NgFor, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-reporte-efectividad',
  standalone: true,
  imports: [NgFor, DatePipe, NgIf, FormsModule],
  templateUrl: './reporte-efectividad.component.html',
  styleUrl: './reporte-efectividad.component.css'
})
export class ReporteEfectividadComponent {


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
    .obtenerAnunciosMostrados(localStorage.getItem("nombreUsuario")!)
    .subscribe({
      next: (listado: Anuncio[]) => {
        console.log("Todo fue bien, procesando response...");
        this.anunciosList = listado;
        console.log(this.anunciosList);
        /* console.log(this.suscripcionesList); */
        this.anuncioFiltradosList = listado;
        this.extractUniqueNombres();
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
      if (!seenIds.has(entity.nombreUsuario)) {
        seenIds.add(entity.nombreUsuario); // AÑADE UNA ID AL SET
        return true; // INCLUYE LA ENTIDAD
      }
      return false; // EXCLUYE DUPLICADOS
    });

    /* console.log(this.valorUnicoList); */
  }




  //LO SIGUIENTE FILTRA EN EL EXPLORADOR DE REVISTAS HAY QUE CAMBIARLO

    //LO SIGUIENTE CUMPLE LA FUNCIÓN DE FILTRAR SEGÚN TAGS Y CATEGORIAS
    /* _filteredNumeroRevista : string = ""; */
    fecha!: Date;
    _fechaInicio: Date | string = "undefined";
    fechaInicio !: Date;
    _fechaFin: Date | string = "undefined";
    fechaFin!: Date;
    anuncioFiltradosList: Anuncio[] = [];
  
    /* onSelectedNumeroRevista(value : string): void {
      this._filteredNumeroRevista = value;
      console.log("Valor seleccionado: " + this._filteredNumeroRevista);
    } */
  
    filtrar () {

      if (this._fechaInicio == "" && this._fechaFin == "") {
        this._fechaInicio = "undefined";
        this._fechaFin = "undefined";
      }

      this.anuncioFiltradosList = this.filterRevistas();
    }
  
  
    filterRevistas() {
      if (this.anunciosList.length === 0 || (this._fechaInicio === "undefined" && this._fechaFin === "undefined")) {
        //No filtra
        console.log("PRUEBA 1")
        return this.anunciosList;
  
      } else if (this._fechaInicio == "" && this._fechaFin == "") {
        console.log("PRUEBA 2")
        console.log(this.anunciosList);
        return this.anunciosList;
        
      } else if (this._fechaInicio != "undefined" && this._fechaFin != "undefined") {
        console.log("PRUEBA 3")
        return this.anunciosList.filter((anuncio) =>
          {
            //FILTRA SOLO EN BASE A LAS FECHAS
                        
            const fecha = new Date(anuncio.fechaInicio);
            const fechaInicio = new Date (this._fechaInicio);
            const fechaFin = new Date (this._fechaFin);

            return fecha >= fechaInicio && fecha <= fechaFin;
          })
      } else {
        console.log("PRUEBA 4")
        //Filtra en BASE A FECHAS Y NUMERO DE REVISTA
        return this.anunciosList.filter((revista) =>
        {
          //Cuando no cumple con ningún elemento
          return this.anunciosList;
            /* return revista.categoria === this._filteredNumeroRevista && revista.tagsString === this._filteredTags; */
        })
      }

    }

}
