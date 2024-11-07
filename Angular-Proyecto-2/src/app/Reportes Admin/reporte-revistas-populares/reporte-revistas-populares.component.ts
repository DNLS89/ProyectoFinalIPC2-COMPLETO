import { Component } from '@angular/core';
import { Revista } from '../../../entities/Revista';
import { ReportesAutorService } from '../../../services/reportes-autor.service';
import { DatePipe, NgFor, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ReportesAdminService } from '../../../services/reportes-admin.service';

@Component({
  selector: 'app-reporte-revistas-populares',
  standalone: true,
  imports: [NgFor, DatePipe, NgIf, FormsModule],
  templateUrl: './reporte-revistas-populares.component.html',
  styleUrl: './reporte-revistas-populares.component.css'
})
export class ReporteRevistasPopularesComponent {

  constructor(private reportesAdminService: ReportesAdminService) {}

  suscripcionesList: Revista[] = [];
  recurrenciaList: Revista[] = [];
  

  ngOnInit(): void {
    // la llamada al servicio
    this.reportesAdminService
    .obtenerSuscripciones(localStorage.getItem("nombreUsuario")!)
    .subscribe({
      next: (listado: Revista[]) => {
        console.log("Todo fue bien, procesando response...");
        this.suscripcionesList = listado;
        /* console.log(this.suscripcionesList); */
        this.meGustasFiltradasList = listado;
        console.log("TODOS LOS ME GUSTAS: ")
        console.log(this.suscripcionesList);
        /* this.extractUniqueEntities(); */
        this.obtenerRecurrencia();
      },
      error: (error: any) => {
        console.log(error);
      }
    });
  }


  obtenerRecurrencia() {

    /* console.log("Valores")
    console.log("fehcaini: " + this._fechaInicio + " fehcafin" + this._fechaFin) */

    this.reportesAdminService
    .obtenerRecurrenciasSuscripciones(localStorage.getItem("nombreUsuario")!, this._fechaInicio, this._fechaFin)
    .subscribe({
      next: (listado: Revista[]) => {
        console.log("Todo fue bien, procesando response...");
        this.recurrenciaList = listado;
        console.log("Recurrencia:")
        console.log(this.recurrenciaList)


        /* this.extractUniqueEntities(); */

        this.filtrar();       
        
      },
      error: (error: any) => {
        console.log(error);
      }
    });
  }


  //LO SIGUIENTE FILTRA EN EL EXPLORADOR DE REVISTAS HAY QUE CAMBIARLO

    //LO SIGUIENTE CUMPLE LA FUNCIÓN DE FILTRAR SEGÚN TAGS Y CATEGORIAS
    _filteredNumeroRevista : string = "undefined";
    fecha!: Date;
    /* _fechaInicio: Date | string = "mm/dd/yyyy"; */
    _fechaInicio: string = "undefined";
    fechaInicio !: Date;
    /* _fechaFin: Date | string = "mm/dd/yyyy"; */
    _fechaFin: string = "undefined";
    fechaFin!: Date;
    meGustasFiltradasList: Revista[] = [];

    filtrar () {
      this.meGustasFiltradasList = this.filterRevistas();
    }
  
  
    filterRevistas() {
      if (this.suscripcionesList.length === 0 || (this._fechaInicio === "undefined" && this._fechaFin === "undefined")) {
        //No filtra
        return this.suscripcionesList;
  
      } else if (this._fechaInicio != "undefined" && this._fechaFin != "undefined") {
        return this.suscripcionesList.filter((revista) =>
          {
            //FILTRA SOLO EN BASE A LAS FECHAS
                        
            const fecha = new Date(revista.fechaProceso);
            const fechaInicio = new Date (this._fechaInicio);
            const fechaFin = new Date (this._fechaFin);

            return fecha >= fechaInicio && fecha <= fechaFin;
          })
      } else {
        //Filtra en BASE A FECHAS Y NUMERO DE REVISTA
        return this.suscripcionesList.filter((revista) =>
        {
          //Cuando no cumple con ningún elemento

          return this.suscripcionesList;
            /* return revista.categoria === this._filteredNumeroRevista && revista.tagsString === this._filteredTags; */
        })
      }


      
    }




  valorUnicoList: Revista[] = [];

  extractUniqueEntities(): void {
    const seenIds = new Set<String>(); // Lleva un registro de las ID'S
    this.valorUnicoList = this.recurrenciaList.filter(entity => {
      if (!seenIds.has(entity.numeroRevista)) {
        seenIds.add(entity.numeroRevista); // AÑADE UNA ID AL SET
        return true; // INCLUYE LA ENTIDAD
      }
      return false; // EXCLUYE DUPLICADOS
    });

    /* console.log(this.valorUnicoList); */
  }


}
