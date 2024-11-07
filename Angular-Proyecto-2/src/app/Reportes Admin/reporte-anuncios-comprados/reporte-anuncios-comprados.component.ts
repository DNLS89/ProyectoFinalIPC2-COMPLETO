import { Component } from '@angular/core';
import { Revista } from '../../../entities/Revista';
import { ReportesAutorService } from '../../../services/reportes-autor.service';
import { DatePipe, NgFor, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ReportesAdminService } from '../../../services/reportes-admin.service';
import { Anuncio } from '../../../entities/Anuncio';

@Component({
  selector: 'app-reporte-anuncios-comprados',
  standalone: true,
  imports: [NgFor, DatePipe, NgIf, FormsModule],
  templateUrl: './reporte-anuncios-comprados.component.html',
  styleUrl: './reporte-anuncios-comprados.component.css'
})
export class ReporteAnunciosCompradosComponent {

  constructor(private reportesAdminService: ReportesAdminService) {}

  anunciosList: Anuncio[] = [];
  

  ngOnInit(): void {
    // la llamada al servicio
    this.reportesAdminService
    .obtenerAnuncios(localStorage.getItem("nombreUsuario")!)
    .subscribe({
      next: (listado: Anuncio[]) => {
        console.log("Todo fue bien, procesando response...");
        this.anunciosList = listado;
        /* console.log(this.suscripcionesList); */
        this.suscripcionesFiltradasList = listado;
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
    _filteredNumeroRevista : string = "";
    fecha!: Date;
    _fechaInicio: Date | string = "mm/dd/yyyy";
    fechaInicio !: Date;
    _fechaFin: Date | string = "mm/dd/yyyy";
    fechaFin!: Date;
    suscripcionesFiltradasList: Anuncio[] = [];
  
    onSelectedNumeroRevista(value : string): void {
      this._filteredNumeroRevista = value;
      console.log("Valor seleccionado: " + this._filteredNumeroRevista);
    }
  
    filtrar () {
      this.suscripcionesFiltradasList = this.filterRevistas();
    }
  
  
    filterRevistas() {
      if (this.anunciosList.length === 0 || (this._filteredNumeroRevista === "" && this._fechaInicio === "mm/dd/yyyy" && this._fechaFin === "mm/dd/yyyy")) {
        //No filtra
        return this.anunciosList;
  
      } else if (this._filteredNumeroRevista !== "" && this._fechaInicio === "mm/dd/yyyy" && this._fechaFin === "mm/dd/yyyy") {
        //Filtra SOLO en base numero de revista
        return this.anunciosList.filter((anuncio) =>
          {
            return anuncio.idAnuncioString === (this._filteredNumeroRevista);
          })
  
      } else if ((this._fechaInicio != null && this._fechaFin != null) && this._filteredNumeroRevista ==="") {
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
          const fecha = new Date(anuncio.fechaInicio);
          const fechaInicio = new Date (this._fechaInicio);
          const fechaFin = new Date (this._fechaFin);

          return (fecha >= fechaInicio && fecha <= fechaFin) && anuncio.idAnuncioString === (this._filteredNumeroRevista);
            /* return anuncio.categoria === this._filteredNumeroRevista && anuncio.tagsString === this._filteredTags; */
        })
      }


      
    }

}
