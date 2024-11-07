import { Component, OnInit} from '@angular/core';
import { AnunciosService } from '../../../services/anuncios-service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { min } from 'rxjs';

@Component({
  selector: 'app-crear-anuncio',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './crear-anuncio.component.html',
  styleUrl: './crear-anuncio.component.css'
})
export class CrearAnuncioComponent implements OnInit {
  crearAnuncioForm!: FormGroup;
  mensajeCreacionAnuncio!: string;

  constructor(
    private formBuilder: FormBuilder,
    private anunciosService: AnunciosService, 
    private router: Router) 
    {}

  ngOnInit(): void {
    this.crearAnuncioForm = this.formBuilder.group(
      {
        tipoAnuncio: ['TEXTO'],
        vigenciaAnuncio: ['UNDIA'],
        costoAnuncio: [null, [Validators.required, Validators.min(0)]],
        costoOcultacion: [null, [Validators.required, Validators.min(0)]],
      }
    );
  }
  
  crearAnuncio() {

    
 
  this.anunciosService
    .crearAnuncio
    (this.crearAnuncioForm.get('tipoAnuncio')?.value, this.crearAnuncioForm.get('vigenciaAnuncio')?.value,
     this.crearAnuncioForm.get('costoAnuncio')?.value, this.crearAnuncioForm.get('costoOcultacion')?.value)
    .subscribe({
      next: () => {
        console.log("Todo fue bien, procesando response...");
        //this.router.navigate(['/menu/gestorAnuncios']);
        this.mensajeCreacionAnuncio = 'CREACIÓN EXITOSA';
        //Reinicia los valores del formulario
        this.crearAnuncioForm.reset({
          tipoAnuncio: 'TEXTO',
          vigenciaAnuncio: 'UNDIA',
        });
      },
      error: (error: any) => {
        console.log(error);
        this.mensajeCreacionAnuncio = 'Anuncio NO creado';
        //this.router.navigate(['/menu/gestorAnuncios']);
      }
    });
}
}
