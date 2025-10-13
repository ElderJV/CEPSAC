import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { CursosModel } from '../../models/cursos.model';
import { CURSOS_DATA } from '../../data/cursos.data';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-cursos',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './cursos.component.html',
  styleUrl: './cursos.component.css',
})
export class CursosComponent {
  cursos: CursosModel = CURSOS_DATA;
}
