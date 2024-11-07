import { NgIf } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-gestor-reportes-autor',
  standalone: true,
  imports: [RouterLink, RouterLinkActive, NgIf, RouterOutlet],
  templateUrl: './gestor-reportes-autor.component.html',
  styleUrl: './gestor-reportes-autor.component.css'
})
export class GestorReportesAutorComponent {

}
