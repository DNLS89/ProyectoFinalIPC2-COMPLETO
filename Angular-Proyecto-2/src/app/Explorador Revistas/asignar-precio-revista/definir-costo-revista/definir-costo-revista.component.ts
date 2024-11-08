import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Revista } from '../../../../entities/Revista';
import { ExploradorRevistasService } from '../../../../services/explorador-revistas.service';
import { GestionRevistaService } from '../../../../services/gestion-revista-service';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-definir-costo-revista',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule, FormsModule, NgIf],
  templateUrl: './definir-costo-revista.component.html',
  styleUrl: './definir-costo-revista.component.css'
})
export class DefinirCostoRevistaComponent implements OnInit{
  @Input({required: true})
  revista!: Revista;
  definirPrecioForm!: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private gestionRevistaService: GestionRevistaService) 
    {}

  ngOnInit(): void {
    this.definirPrecioForm = this.formBuilder.group(
      {
        costoRevista: [null, [Validators.required, Validators.min(0)]],
      }
    );
  }

  mostrar: boolean = true; // Initially visible


  registrarCosto() {
    this.gestionRevistaService
    .definirPrecioRevista(this.revista.numeroRevista, this.definirPrecioForm.get('costoRevista')?.value)
    .subscribe({
      next: () => {
        console.log("Todo fue bien, procesando response...");
        this.definirPrecioForm.reset({
          costoRevista: '',
        });
        alert("Se definió el costo correctamente");
        this.mostrar = false;
      },
      error: (error: any) => {
        console.log(error);
      }
    });
  }

}
