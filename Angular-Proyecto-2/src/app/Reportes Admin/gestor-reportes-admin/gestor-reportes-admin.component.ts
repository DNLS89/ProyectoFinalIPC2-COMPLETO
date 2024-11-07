import { NgIf } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-gestor-reportes-admin',
  standalone: true,
  imports: [RouterLink, NgIf, RouterLinkActive, RouterOutlet],
  templateUrl: './gestor-reportes-admin.component.html',
  styleUrl: './gestor-reportes-admin.component.css'
})
export class GestorReportesAdminComponent {

}
