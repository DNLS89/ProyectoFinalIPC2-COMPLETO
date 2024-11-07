import { TestBed } from '@angular/core/testing';

import { ExploradorRevistasService } from '../services/explorador-revistas.service';

describe('ExplorarRevistasService', () => {
  let service: ExploradorRevistasService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ExploradorRevistasService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
