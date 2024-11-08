import { Component } from '@angular/core';
import { Anuncio } from '../../../entities/Anuncio';
import { ReportesAdminService } from '../../../services/reportes-admin.service';
import { DatePipe, NgFor, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ReportesAutorService } from '../../../services/reportes-autor.service';
import { Revista } from '../../../entities/Revista';

@Component({
  selector: 'app-reporte-ganancias',
  standalone: true,
  imports: [NgFor, DatePipe, NgIf, FormsModule],
  templateUrl: './reporte-ganancias.component.html',
  styleUrl: './reporte-ganancias.component.css'
})
export class ReporteGananciasComponent {
  constructor(private reportesAdminService: ReportesAdminService, private reportesAutorService: ReportesAutorService) {}

  anunciosList: Anuncio[] = [];
  pagosList: Revista[] = [];
  anuncioFiltradosList: Anuncio[] = [];
  pagosFiltradosList: Revista[] = [];

   

  ngOnInit(): void {
    //Obtiene ANUNCIOS
    this.reportesAdminService
    .obtenerAnuncios(localStorage.getItem("nombreUsuario")!)
    .subscribe({
      next: (listado: Anuncio[]) => {
        console.log("Todo fue bien, procesando response...");
        this.anunciosList = listado;
        console.log("ANUNCIOS")
        console.log(this.anunciosList);
        /* console.log(this.suscripcionesList); */
        this.anuncioFiltradosList = listado;
        this.extractUniqueEntitiesAnuncio();
        this.calcularCostosTotales(this.anunciosList);
        this.obtenerPagos();
      },
      error: (error: any) => {
        console.log(error);
      }
    });
   
  }

  obtenerPagos() {
    //Obtener Pagos
    this.reportesAutorService
    .obtenerPagos(localStorage.getItem("nombreUsuario")!)
    .subscribe({
      next: (listado: Revista[]) => {
        console.log("Todo fue bien, procesando response...");
        this.pagosList = listado;
        this.pagosFiltradosList = listado;
        /* console.log(this.suscripcionesList); */
        console.log("PAGOS")
        console.log(this.pagosList);
        
        this.extractUniqueEntitiesRevista();
        this.calcularIngresosTotales(this.pagosList);
      },
      error: (error: any) => {
        console.log(error);
      }
    });
    
    
   

  }

  costosAnuncios : number = 0;

  calcularCostosTotales(anunciosList : Anuncio[]) {
    this.costosAnuncios = 0;
    for (let i = 0; i < anunciosList.length; i++) {
      this.costosAnuncios += anunciosList[i].costoAnuncioDecimal;
    }
  }

  ingresosRevistas : number = 0;

  calcularIngresosTotales(pagosList: Revista[]) {
    this.ingresosRevistas = 0;
    for (let i = 0; i < pagosList.length; i++) {
      this.ingresosRevistas += pagosList[i].costoSuscripcion;
    }
  }

  valorUnicoRevistaList: Revista[] = [];

  extractUniqueEntitiesRevista(): void {
    const seenIds = new Set<String>(); // Lleva un registro de las ID'S
    this.valorUnicoRevistaList = this.pagosList.filter(entity => {
      if (!seenIds.has(entity.numeroRevista)) {
        seenIds.add(entity.numeroRevista); // AÑADE UNA ID AL SET
        return true; // INCLUYE LA ENTIDAD
      }
      return false; // EXCLUYE DUPLICADOS
    });

    /* console.log(this.valorUnicoList); */
  }

  valorUnicoAnuncioList: Anuncio[] = [];

  extractUniqueEntitiesAnuncio(): void {
    const seenIds = new Set<number>(); // Lleva un registro de las ID'S
    this.valorUnicoAnuncioList = this.anunciosList.filter(entity => {
      if (!seenIds.has(entity.idAnuncio)) {
        seenIds.add(entity.idAnuncio); // AÑADE UNA ID AL SET
        return true; // INCLUYE LA ENTIDAD
      }
      return false; // EXCLUYE DUPLICADOS
    });

    /* console.log(this.valorUnicoList); */
  }


    fecha!: Date;
    _fechaInicio: Date | string = "undefined";
    fechaInicio !: Date;
    _fechaFin: Date | string = "undefined";
    fechaFin!: Date;
   
  
    /* onSelectedNumeroRevista(value : string): void {
      this._filteredNumeroRevista = value;
      console.log("Valor seleccionado: " + this._filteredNumeroRevista);
    } */
  

    filtrar() {
      this.filtrar1();
      this.filtrar2();
    }

    filtrar2() {

      if (this._fechaInicio == "" && this._fechaFin == "") {
        this._fechaInicio = "undefined";
        this._fechaFin = "undefined";
      }

      this.pagosFiltradosList = this.filterRevistas2();
      this.calcularIngresosTotales(this.pagosFiltradosList);

    }
  
  
    filterRevistas2() {
      if (this.pagosList.length === 0 || (this._fechaInicio === "undefined" && this._fechaFin === "undefined")) {
        //No filtra
        return this.pagosList;
  
      } else if (this._fechaInicio != "undefined" && this._fechaFin != "undefined") {
        return this.pagosList.filter((revista) =>
          {
            //FILTRA SOLO EN BASE A LAS FECHAS
                        
            const fecha = new Date(revista.fechaProceso);
            const fechaInicio = new Date (this._fechaInicio);
            const fechaFin = new Date (this._fechaFin);

            return fecha >= fechaInicio && fecha <= fechaFin;
          })
      } else {
        //Filtra en BASE A FECHAS Y NUMERO DE REVISTA
        return this.pagosList.filter((revista) =>
        {
          //Cuando no cumple con ningún elemento

          return this.pagosList;
            /* return revista.categoria === this._filteredNumeroRevista && revista.tagsString === this._filteredTags; */
        })
      }
      
    }

    filtrar1 () {

      if (this._fechaInicio == "" && this._fechaFin == "") {
        this._fechaInicio = "undefined";
        this._fechaFin = "undefined";
      }

      this.anuncioFiltradosList = this.filterRevistas1();
      this.calcularCostosTotales(this.anuncioFiltradosList);
    }
    

    filterRevistas1() {
      if (this.anunciosList.length === 0 || (this._fechaInicio === "undefined" && this._fechaFin === "undefined")) {
        //No filtra
        return this.anunciosList;
  
      } else if ((this._fechaInicio != null && this._fechaFin != null)) {
        return this.anunciosList.filter((anuncio) =>
          {
            //FILTRA SOLO EN BASE A LAS FECHAS
                        
            const fecha = new Date(anuncio.fechaInicio);
            const fechaInicio = new Date (this._fechaInicio);
            const fechaFin = new Date (this._fechaFin);

            return fecha >= fechaInicio && fecha <= fechaFin;
          })
      } else {
        //Filtra en BASE A FECHAS Y NUMERO DE REVISTA
        return this.anunciosList.filter((anuncio) =>
        {
          return this.anunciosList;
        })
      }
    }


    

}
