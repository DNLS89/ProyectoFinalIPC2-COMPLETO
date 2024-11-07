import { Component } from '@angular/core';
import { ReportesAutorService } from '../../../services/reportes-autor.service';
import { DatePipe, NgFor, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Revista } from '../../../entities/Revista';

@Component({
  selector: 'app-reporte-suscripciones',
  standalone: true,
  imports: [NgFor, DatePipe, NgIf, FormsModule],
  templateUrl: './reporte-suscripciones.component.html',
  styleUrl: './reporte-suscripciones.component.css'
})
export class ReporteSuscripcionesComponent {

  constructor(private reportesAutorService: ReportesAutorService) {}

  suscripcionesList: Revista[] = [];
  

  ngOnInit(): void {
    // la llamada al servicio
    this.reportesAutorService
    .obtenerSuscripciones(localStorage.getItem("nombreUsuario")!)
    .subscribe({
      next: (listado: Revista[]) => {
        console.log("Todo fue bien, procesando response...");
        this.suscripcionesList = listado;
        /* console.log(this.suscripcionesList); */
        this.suscripcionesFiltradasList = listado;
        this.extractUniqueEntities();
      },
      error: (error: any) => {
        console.log(error);
      }
    });
  }

  valorUnicoList: Revista[] = [];

  extractUniqueEntities(): void {
    const seenIds = new Set<String>(); // Lleva un registro de las ID'S
    this.valorUnicoList = this.suscripcionesList.filter(entity => {
      if (!seenIds.has(entity.numeroRevista)) {
        seenIds.add(entity.numeroRevista); // AÑADE UNA ID AL SET
        return true; // INCLUYE LA ENTIDAD
      }
      return false; // EXCLUYE DUPLICADOS
    });

    /* console.log(this.valorUnicoList); */
  }



  //LO SIGUIENTE FILTRA EN EL EXPLORADOR DE REVISTAS HAY QUE CAMBIARLO

    //LO SIGUIENTE CUMPLE LA FUNCIÓN DE FILTRAR SEGÚN TAGS Y CATEGORIAS
    _filteredNumeroRevista : string = "";
    fecha!: Date;
    _fechaInicio: Date | string = "mm/dd/yyyy";
    fechaInicio !: Date;
    _fechaFin: Date | string = "mm/dd/yyyy";
    fechaFin!: Date;
    suscripcionesFiltradasList: Revista[] = [];
  
    onSelectedNumeroRevista(value : string): void {
      this._filteredNumeroRevista = value;
      console.log("Valor seleccionado: " + this._filteredNumeroRevista);
    }
  
    filtrar () {
      this.suscripcionesFiltradasList = this.filterRevistas();
    }
  
  
    filterRevistas() {
      if (this.suscripcionesList.length === 0 || (this._filteredNumeroRevista === "" && this._fechaInicio === "mm/dd/yyyy" && this._fechaFin === "mm/dd/yyyy")) {
        //No filtra
        return this.suscripcionesList;
  
      } else if (this._filteredNumeroRevista !== "" && this._fechaInicio === "mm/dd/yyyy" && this._fechaFin === "mm/dd/yyyy") {
        //Filtra SOLO en base numero de revista
        return this.suscripcionesList.filter((revista) =>
          {
            return revista.numeroRevistaString === (this._filteredNumeroRevista);
          })
  
      } else if ((this._fechaInicio != null && this._fechaFin != null) && this._filteredNumeroRevista ==="") {
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
          const fecha = new Date(revista.fechaProceso);
          const fechaInicio = new Date (this._fechaInicio);
          const fechaFin = new Date (this._fechaFin);

          return (fecha >= fechaInicio && fecha <= fechaFin) && revista.numeroRevistaString === (this._filteredNumeroRevista);
            /* return revista.categoria === this._filteredNumeroRevista && revista.tagsString === this._filteredTags; */
        })
      }


      
    }


}
