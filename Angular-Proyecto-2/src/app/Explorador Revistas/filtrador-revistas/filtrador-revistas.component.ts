import { Component, Input } from '@angular/core';
import { Revista } from '../../../entities/Revista';

@Component({
  selector: 'app-filtrador-revistas',
  standalone: true,
  imports: [],
  templateUrl: './filtrador-revistas.component.html',
  styleUrl: './filtrador-revistas.component.css'
})
export class FiltradorRevistasComponent {

  @Input({required: true})
  revistasList!: Revista[];

  


}
