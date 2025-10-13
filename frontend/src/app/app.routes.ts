import { Routes } from '@angular/router';
import { MainComponent } from './layout/main/main.component';
import { HeroComponent } from './hero/hero.component';
import { InfoCursoComponent } from './hero/pages/cursos/curso/info-curso/info-curso.component';
import { InfoDiplomadoComponent } from './hero/pages/diplomados/info-diplomado/info-diplomado.component';

export const routes: Routes = [
  {
    path: '',
    component: MainComponent,
    children: [
      {
        path: '',
        component: HeroComponent,
        pathMatch: 'full',
      },
      {
        path: 'curso',
        component: InfoCursoComponent,
      },
      {
        path: 'diplomado',
        component: InfoDiplomadoComponent,
      },
    ],
  },
  { path: '**', component: MainComponent },
];
