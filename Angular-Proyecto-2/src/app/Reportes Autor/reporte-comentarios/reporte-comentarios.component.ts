import { Component } from '@angular/core';
import { ReportesAutorService } from '../../../services/reportes-autor.service';
import { Revista } from '../../../entities/Revista';
import { DatePipe, NgFor, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-reporte-comentarios',
  standalone: true,
  imports: [NgFor, DatePipe, NgIf, FormsModule],
  templateUrl: './reporte-comentarios.component.html',
  styleUrl: './reporte-comentarios.component.css'
})
export class ReporteComentariosComponent {


  constructor(private reportesAutorService: ReportesAutorService) {}

  revistasList: Revista[] = [];
  

  ngOnInit(): void {
    // la llamada al servicio
    this.reportesAutorService
    .obtenerRevistasConComentarios(localStorage.getItem("nombreUsuario")!)
    .subscribe({
      next: (listado: Revista[]) => {
        console.log("Todo fue bien, procesando response...");
        this.revistasList = listado;
        /* console.log(this.revistasList); */
        this.revistasFiltradasList = listado;
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
    this.valorUnicoList = this.revistasList.filter(entity => {
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
    revistasFiltradasList: Revista[] = [];
  
    onSelectedNumeroRevista(value : string): void {
      this._filteredNumeroRevista = value;
      console.log("Valor seleccionado: " + this._filteredNumeroRevista);
    }
  
    filtrar () {
      this.revistasFiltradasList = this.filterRevistas();
    }
  
  
    filterRevistas() {
      if (this.revistasList.length === 0 || (this._filteredNumeroRevista === "" && this._fechaInicio === "mm/dd/yyyy" && this._fechaFin === "mm/dd/yyyy")) {
        //No filtra
        return this.revistasList;
  
      } else if (this._filteredNumeroRevista !== "" && this._fechaInicio === "mm/dd/yyyy" && this._fechaFin === "mm/dd/yyyy") {
        //Filtra SOLO en base numero de revista
        return this.revistasList.filter((revista) =>
          {
            return revista.numeroRevistaString === (this._filteredNumeroRevista);
          })
  
      } else if ((this._fechaInicio != null && this._fechaFin != null) && this._filteredNumeroRevista ==="") {
        return this.revistasList.filter((revista) =>
          {
            //FILTRA SOLO EN BASE A LAS FECHAS
                        
            const fecha = new Date(revista.fechaProceso);
            const fechaInicio = new Date (this._fechaInicio);
            const fechaFin = new Date (this._fechaFin);

            return fecha >= fechaInicio && fecha <= fechaFin;
          })
      } else {
        //Filtra en BASE A FECHAS Y NUMERO DE REVISTA
        return this.revistasList.filter((revista) =>
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
