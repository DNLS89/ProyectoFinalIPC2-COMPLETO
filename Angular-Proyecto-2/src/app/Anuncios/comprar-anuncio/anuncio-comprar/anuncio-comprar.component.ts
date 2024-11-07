import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Anuncio } from '../../../../entities/Anuncio';
import { NgIf } from '@angular/common';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AnunciosService } from '../../../../services/anuncios-service';
import { TiendaService } from '../../../../services/tienda-service';
import { Usuario } from '../../../../entities/Usuario';

@Component({
  selector: 'app-anuncio-comprar',
  standalone: true,
  imports: [NgIf, FormsModule, ReactiveFormsModule],
  templateUrl: './anuncio-comprar.component.html',
  styleUrl: './anuncio-comprar.component.css'
})
export class AnuncioComprarComponent {
  @Input({required: true}) anuncio!: Anuncio;
  @Input() creditos!: number;
  @Output() valueChange = new EventEmitter<number>();
  eliminacionAnuncio: boolean = true;
  comprarAnuncioForm!: FormGroup;
  

  constructor(
    private formBuilder: FormBuilder,
    private anunciosService: AnunciosService,
    private router: Router) 
    {}

    ngOnInit(): void {
    
      //CREAR LOS ELEMENTOS DEL FORMULARIO
      this.comprarAnuncioForm = this.formBuilder.group(
        {
          fechaCompra: [null, [Validators.required]],
        }
      );

      //EXTRAE LOS CREDITOS
      
    }  

    comprarAnuncio() {
      //Abajo evalua si tienen suficientes créditos
      if (this.creditos < this.anuncio.costoAnuncio) {
        alert("No se tienen suficientes créditos");
      } else {
        //Hay suficientes créditos entonces lo pasa al BE
        this.anunciosService
        .comprarAnuncio
        (this.anuncio.idAnuncio, this.comprarAnuncioForm.get('fechaCompra')?.value)
        .subscribe({
          next: () => {
            console.log("Todo fue bien, procesando response...");
            alert("Se compró el anuncio " + this.anuncio.idAnuncio);
            //Cambia el valor del parent component
            this.valueChange.emit(this.creditos - this.anuncio.costoAnuncio); 
            //Oculta el anuncio
            this.ocultarAnuncio();
    
          },
          error: (error: any) => {
            console.log(error);
            alert("NO se compró el anuncio " + this.anuncio.idAnuncio);
          }
        });
      }      
      
    }

    ocultarAnuncio() {
      if (this.eliminacionAnuncio == true) {
        this.eliminacionAnuncio = false;
      } else {
        this.eliminacionAnuncio = true;
      }
    }
    

}
