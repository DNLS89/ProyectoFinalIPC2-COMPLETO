import { Component } from '@angular/core';
import { Revista } from '../../../entities/Revista';
import { ReportesAutorService } from '../../../services/reportes-autor.service';
import { DatePipe, NgFor, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-reporte-pagos',
  standalone: true,
  imports: [NgFor, DatePipe, NgIf, FormsModule],
  templateUrl: './reporte-pagos.component.html',
  styleUrl: './reporte-pagos.component.css'
})
export class ReportePagosComponent {
  constructor(private reportesAutorService: ReportesAutorService) {}

  pagosList: Revista[] = [];
  

  ngOnInit(): void {
    // la llamada al servicio
    this.reportesAutorService
    .obtenerPagos(localStorage.getItem("nombreUsuario")!)
    .subscribe({
      next: (listado: Revista[]) => {
        console.log("Todo fue bien, procesando response...");
        this.pagosList = listado;
        /* console.log(this.suscripcionesList); */
        
        console.log("TODOS LOS PAGOS: ")
        console.log(this.pagosList);
        this.extractUniqueEntities();
        this.pagosFiltradosList = this.valorUnicoList;
      },
      error: (error: any) => {
        console.log(error);
      }
    });
  }

  valorUnicoList: Revista[] = [];

  extractUniqueEntities(): void {
    const seenIds = new Set<String>(); // Lleva un registro de las ID'S
    this.valorUnicoList = this.pagosList.filter(entity => {
      if (!seenIds.has(entity.numeroRevista)) {
        seenIds.add(entity.numeroRevista); // AÑADE UNA ID AL SET
        return true; // INCLUYE LA ENTIDAD
      }
      return false; // EXCLUYE DUPLICADOS
    });

    /* console.log(this.valorUnicoList); */
  }

  onSelectedNumeroRevista(value : string): void {
    this._filteredNumeroRevista = value;
    console.log("Valor seleccionado: " + this._filteredNumeroRevista);
  }

  


  //LO SIGUIENTE FILTRA EN EL EXPLORADOR DE REVISTAS HAY QUE CAMBIARLO

    //LO SIGUIENTE CUMPLE LA FUNCIÓN DE FILTRAR SEGÚN TAGS Y CATEGORIAS
    _filteredNumeroRevista : string = "";
    fecha!: Date;
    /* _fechaInicio: Date | string = "mm/dd/yyyy"; */
    _fechaInicio: string = "undefined";
    fechaInicio !: Date;
    /* _fechaFin: Date | string = "mm/dd/yyyy"; */
    _fechaFin: string = "undefined";
    fechaFin!: Date;
    pagosFiltradosList: Revista[] = [];

    filtrar () {
      this.pagosFiltradosList = this.filterRevistas();
    }
  
  
    filterRevistas() {
      if (this.pagosList.length === 0 || this._filteredNumeroRevista == "") {
        //No filtra
        return this.valorUnicoList;
  
      } else if (this._filteredNumeroRevista != "") {
        return this.valorUnicoList.filter((revista) =>
          {
                     

            return revista.numeroRevista == this._filteredNumeroRevista;
          })
      } else {
        
        return this.valorUnicoList.filter((revista) =>
        {
          //Cuando no cumple con ningún elemento

          return this.valorUnicoList;
            /* return revista.categoria === this._filteredNumeroRevista && revista.tagsString === this._filteredTags; */
        })
      }
    }

}
