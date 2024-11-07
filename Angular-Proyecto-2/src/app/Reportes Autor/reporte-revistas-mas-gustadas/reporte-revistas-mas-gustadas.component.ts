import { Component } from '@angular/core';
import { Revista } from '../../../entities/Revista';
import { ReportesAutorService } from '../../../services/reportes-autor.service';
import { DatePipe, NgFor, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-reporte-revistas-mas-gustadas',
  standalone: true,
  imports: [NgFor, DatePipe, NgIf, FormsModule],
  templateUrl: './reporte-revistas-mas-gustadas.component.html',
  styleUrl: './reporte-revistas-mas-gustadas.component.css'
})
export class ReporteRevistasMasGustadasComponent {

  constructor(private reportesAutorService: ReportesAutorService) {}

  meGustasList: Revista[] = [];
  recurrenciaList: Revista[] = [];
  

  ngOnInit(): void {
    // la llamada al servicio
    this.reportesAutorService
    .obtenerRevistasConMeGustas(localStorage.getItem("nombreUsuario")!)
    .subscribe({
      next: (listado: Revista[]) => {
        console.log("Todo fue bien, procesando response...");
        this.meGustasList = listado;
        /* console.log(this.suscripcionesList); */
        this.meGustasFiltradasList = listado;
        console.log("TODOS LOS ME GUSTAS: ")
        console.log(this.meGustasList);
        this.extractUniqueEntities();
        this.obtenerRecurrencia();
      },
      error: (error: any) => {
        console.log(error);
      }
    });
  }


  obtenerRecurrencia() {
    console.log("Valores elementos mandados a ocurrence:");
    console.log("Fecha Inicio: " + this._fechaInicio + " fecha fin: " + this._fechaFin + " numero revsita:" + this._filteredNumeroRevista);


    this.reportesAutorService
    .obtenerRecurrenciasRevistasConMeGustas(localStorage.getItem("nombreUsuario")!, this._fechaInicio, this._fechaFin, this._filteredNumeroRevista)
    .subscribe({
      next: (listado: Revista[]) => {
        console.log("Todo fue bien, procesando response...");
        this.recurrenciaList = listado;
        console.log("Recurrencia:")
        console.log(this.recurrenciaList)
        this.extractUniqueEntities();

        console.log("Valor Unico");
        console.log(this.valorUnicoList);

        
        
      },
      error: (error: any) => {
        console.log(error);
      }
    });
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
  
    onSelectedNumeroRevista(value : string): void {
      this._filteredNumeroRevista = value;
      console.log("Valor seleccionado: " + this._filteredNumeroRevista);
    }
  
}
