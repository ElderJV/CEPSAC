import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { DiplomadosModel } from '../../models/diplomados.model';
import { DIPLOMADOS_DATA } from '../../data/diplomados.data';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-diplomados',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './diplomados.component.html',
  styleUrl: './diplomados.component.css',
})
export class DiplomadosComponent {
  diplomados: DiplomadosModel = DIPLOMADOS_DATA;
}
