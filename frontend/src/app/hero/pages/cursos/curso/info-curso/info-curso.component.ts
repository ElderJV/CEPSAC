import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { IconComponent } from '../icon/icon.component';

@Component({
  selector: 'app-info-curso',
  imports: [CommonModule, IconComponent],
  templateUrl: './info-curso.component.html',
  styleUrl: './info-curso.component.css',
})
export class InfoCursoComponent {
  // Objeto principal que contiene toda la información del curso
  course = {
    title: 'Desarrollo de',
    subtitle: 'Aplicaciones Móviles',
    details: [
      {
        icon: 'user-screen', // Nombre del ícono
        label: 'Modalidad:',
        value: 'Remota',
      },
      {
        icon: 'clock',
        label: 'Duración:',
        value: '40 horas',
      },
    ],
    description: {
      icon: 'book-2',
      title: 'ACERCA DE ESTE CURSO:',
      paragraphs: [
        'Lorem, ipsum dolor sit amet consectetur adipisicing elit. Maiores beatae at eum fugiat dolores magni earum odit expedita atque quisquam nesciunt cum consectetur ipsum et voluptas aperiam, repudiandae vel tempore!',
        'Lorem ipsum dolor sit amet consectetur adipisicing elit. Sunt distinctio, blanditiis laborum ab tempora rem soluta ratione? Perspiciatis vero corporis illo, laudantium sunt similique excepturi consequatur. Possimus minus magni fugiat.',
      ],
    },
    includedMaterials: {
      icon: 'tallymark-4',
      title: 'MATERIAL INCLUIDO:',
      items: [
        '4 horas de video bajo demanda',
        '4 artículos',
        '3 cursos recargables',
        'Acceso completo de por vida',
        'Certificado de finalización',
      ],
    },
    requirements: {
      icon: 'alert-square-rounded',
      title: 'REQUISITOS:',
      items: [
        'Conocimientos previos en programación orientada a objetos.',
        'Manejo básico de herramientas de desarrollo (Android Studio e instalación de SDK)',
      ],
    },
    // La tabla de horarios también se podría manejar aquí con un array de objetos.
  };
}
