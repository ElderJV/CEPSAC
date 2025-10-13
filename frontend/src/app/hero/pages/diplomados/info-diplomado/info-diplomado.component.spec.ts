import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InfoDiplomadoComponent } from './info-diplomado.component';

describe('InfoDiplomadoComponent', () => {
  let component: InfoDiplomadoComponent;
  let fixture: ComponentFixture<InfoDiplomadoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InfoDiplomadoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InfoDiplomadoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
