import { Component, Input } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { AnunciosService } from '../../../../services/anuncios-service';
import { Router } from '@angular/router';
import { Anuncio } from '../../../../entities/Anuncio';
import { AnuncioComponent } from "../../cambiar-estado-anuncio/anuncio/anuncio.component";
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-modificar-anuncio',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule, AnuncioComponent, NgIf],
  templateUrl: './modificar-anuncio.component.html',
  styleUrl: './modificar-anuncio.component.css'
})
export class ModificarAnuncioComponent {
  
  @Input({required: true})
  anuncio!: Anuncio;
  tipoAnuncio!: string;
  vigenciaAnuncio!: number;
  costoAnuncio!: number;
  costoOcultacion!: number;

  modificarAnuncioForm!: FormGroup;
  mensajeModificarAnuncio!: string;
  mensajeEliminacionAnuncio!: string;
  mostrarModificarAnuncio: boolean = false;
  eliminacionAnuncio: boolean = true;
  

  constructor(
    private formBuilder: FormBuilder,
    private anunciosService: AnunciosService, 
    private router: Router) 
    {}

  ngOnInit(): void {
    
    //CREAR LOS ELEMENTOS DEL FORMULARIO
    this.modificarAnuncioForm = this.formBuilder.group(
      {
        tipoAnuncio: [this.anuncio.tipo],
        vigenciaAnuncio: [this.anuncio.vigenciaString],
        costoAnuncio: [null, [Validators.required, Validators.min(0)]],
        costoOcultacion: [null, [Validators.required, Validators.min(0)]],
      }
    );
    this.tipoAnuncio = this.anuncio.tipo;
    this.vigenciaAnuncio = this.anuncio.vigenciaInt;
    this.costoAnuncio = this.anuncio.costoAnuncio;
    this.costoOcultacion = this.anuncio.costoOcultacion;
    
  }
  
  modificarAnuncio() {

  this.anunciosService
    .modificarAnuncio
    (this.anuncio.idAnuncio, this.modificarAnuncioForm.get('tipoAnuncio')?.value, this.modificarAnuncioForm.get('vigenciaAnuncio')?.value,
    this.modificarAnuncioForm.get('costoAnuncio')?.value, this.modificarAnuncioForm.get('costoOcultacion')?.value)
    .subscribe({
      next: () => {
        console.log("Todo fue bien, procesando response...");
        //this.router.navigate(['/menu/gestorAnuncios/modificarAnuncios']);
        this.mensajeModificarAnuncio = 'MODIFICACIÓN EXITOSA';

        //Cambia los valores a los nuevos
        this.cambiarValores();

        //Oculta las opciones de modificar
        this.mostrarModificarAnuncio = false;

      },
      error: (error: any) => {
        console.log(error);
        this.mensajeModificarAnuncio = 'Anuncio NO modificado';
        //this.router.navigate(['/menu/gestorAnuncios/modificarAnuncios']);
      }
    });
}

cambiarValores() {
  //Cambiar los valores por los nuevos
  this.tipoAnuncio = this.modificarAnuncioForm.get('tipoAnuncio')?.value;
  this.vigenciaAnuncio = this.modificarAnuncioForm.get('vigenciaAnuncio')?.value;
  this.costoAnuncio = this.modificarAnuncioForm.get('costoAnuncio')?.value;
  this.costoOcultacion = this.modificarAnuncioForm.get('costoOcultacion')?.value;
  this.anuncio.costoAnuncio = this.costoAnuncio;
  this.anuncio.costoOcultacion = this.costoOcultacion;
  //Reinicia los valores del formulario
  this.modificarAnuncioForm.reset({
    tipoAnuncio: this.tipoAnuncio,
    vigenciaAnuncio: this.vigenciaAnuncio,
    costoAnuncio: (this.costoAnuncio),
    costoOcultacion: (this.costoOcultacion),
  });
}

eliminarAnuncio() {
  this.anunciosService
    .eliminarAnuncio
    (this.anuncio.idAnuncio)
    .subscribe({
      next: () => {
        console.log("Todo fue bien, procesando response...");
        //this.router.navigate(['/menu/gestorAnuncios/modificarAnuncios']);
        this.mensajeEliminacionAnuncio = 'El anuncio fue ELIMINADO';

        this.ocultarAnuncio();

      },
      error: (error: any) => {
        console.log(error);
        this.mensajeEliminacionAnuncio = 'El anuncio NO fue ELIMINADO';
        //this.router.navigate(['/menu/gestorAnuncios/modificarAnuncios']);
      }
    });
}

ocultarAnuncio() {
  if (this.eliminacionAnuncio == true) {
    this.eliminacionAnuncio = false;
  } else {
    this.eliminacionAnuncio = true;
  }
}

booleanMostrarModificarAnuncio() {
  if (this.mostrarModificarAnuncio == true) {
    this.mostrarModificarAnuncio = false;
  } else {
    this.mostrarModificarAnuncio = true;
  }
}

}
