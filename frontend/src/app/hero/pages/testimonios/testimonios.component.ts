import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { TestimoniosModel } from '../../models/testimonios.model';
import { TESTIMONIOS_DATA } from '../../data/testimonios.data';

@Component({
  selector: 'app-testimonios',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './testimonios.component.html',
  styleUrl: './testimonios.component.css',
})
export class TestimoniosComponent {
  testimonios: TestimoniosModel = TESTIMONIOS_DATA;
}
