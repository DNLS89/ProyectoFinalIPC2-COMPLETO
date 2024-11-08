import { Component } from '@angular/core';
import { Anuncio } from '../../../entities/Anuncio';
import { ReportesAdminService } from '../../../services/reportes-admin.service';
import { DatePipe, NgFor, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-reporte-ganancias',
  standalone: true,
  imports: [NgFor, DatePipe, NgIf, FormsModule],
  templateUrl: './reporte-ganancias.component.html',
  styleUrl: './reporte-ganancias.component.css'
})
export class ReporteGananciasComponent {
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
        console.log("Todo fue bien, procesando response...");
        this.anunciosList = listado;
        console.log(this.anunciosList);
        /* console.log(this.suscripcionesList); */
        this.anuncioFiltradosList = listado;
        this.extractUniqueEntities();
      },
      error: (error: any) => {
        console.log(error);
      }
    });
  }

  valorUnicoList: Anuncio[] = [];

  extractUniqueEntities(): void {
    const seenIds = new Set<number>(); // Lleva un registro de las ID'S
    this.valorUnicoList = this.anunciosList.filter(entity => {
      if (!seenIds.has(entity.idAnuncio)) {
        seenIds.add(entity.idAnuncio); // AÑADE UNA ID AL SET
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
      if (this.anunciosList.length === 0 || (this.tipo === "" && this.vigencia === "" && this._fechaInicio === "undefined" && this._fechaFin === "undefined")) {
        //No filtra
        return this.anunciosList;
  
      } else if ((this._fechaInicio != null && this._fechaFin != null) && this.tipo ==="" && this.vigencia === "") {
        return this.anunciosList.filter((anuncio) =>
          {
            //FILTRA SOLO EN BASE A LAS FECHAS
                        
            const fecha = new Date(anuncio.fechaInicio);
            const fechaInicio = new Date (this._fechaInicio);
            const fechaFin = new Date (this._fechaFin);

            return fecha >= fechaInicio && fecha <= fechaFin;
          })
      } else if (this.tipo !== "" && this._fechaInicio === "undefined" && this._fechaFin === "undefined" && this.vigencia === "") {
        //Filtra SOLO en base al tipo
        return this.anunciosList.filter((anuncio) =>
          {
            return anuncio.tipo === (this.tipo);
          })
  
      } else if (this.vigencia !== "" && this._fechaInicio === "undefined" && this._fechaFin === "undefined" && this.tipo === "") {
        //Filtra SOLO en base a la vigencia
        return this.anunciosList.filter((anuncio) =>
          {
            return anuncio.vigenciaString === (this.vigencia);
          })
  
      } else if ((this._fechaInicio != null && this._fechaFin != null) && this.tipo !=="" && this.vigencia === "") {
        //Filtra en base FECHAS y TIPO
        return this.anunciosList.filter((anuncio) =>
          {
            const fecha = new Date(anuncio.fechaInicio);
            const fechaInicio = new Date (this._fechaInicio);
            const fechaFin = new Date (this._fechaFin);

            return anuncio.tipo === (this.tipo) && (fecha >= fechaInicio && fecha <= fechaFin);
          })
  
      } else if ((this._fechaInicio != null && this._fechaFin != null) && this.tipo ==="" && this.vigencia !== "") {
        //Filtra en base FECHAS y VIGENCIA
        return this.anunciosList.filter((anuncio) =>
          {
            const fecha = new Date(anuncio.fechaInicio);
            const fechaInicio = new Date (this._fechaInicio);
            const fechaFin = new Date (this._fechaFin);

            return anuncio.vigenciaString === (this.vigencia) && (fecha >= fechaInicio && fecha <= fechaFin);
          })
  
      } else if (this._fechaInicio === "undefined" && this._fechaFin === "undefined" && this.tipo !== "" && this.vigencia !== "") {
        //Filtra en base TIPO y VIGENCIA
        return this.anunciosList.filter((anuncio) =>
          {
            return anuncio.vigenciaString === (this.vigencia) && anuncio.tipo === (this.tipo);
          })
  
      } else if (this._fechaInicio != null && this._fechaFin != null && this.tipo !== "" && this.vigencia !== "") {
        //Filtra en base a TODO
        return this.anunciosList.filter((anuncio) =>
          {
            const fecha = new Date(anuncio.fechaInicio);
            const fechaInicio = new Date (this._fechaInicio);
            const fechaFin = new Date (this._fechaFin);

            return anuncio.vigenciaString === (this.vigencia) && anuncio.tipo === (this.tipo) && (fecha >= fechaInicio && fecha <= fechaFin);
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
