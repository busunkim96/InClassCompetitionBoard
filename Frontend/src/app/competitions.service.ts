import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';

import { COMPETITIONS } from './MOCK-COMPETITIONS';
import { Competition } from './competition';

@Injectable()
export class CompetitionsService {

  constructor() { }

  getCompetitions(): Observable<Competition[]> {
  	return of(COMPETITIONS);
  	/* todo: replace with GET call to backend API */
  }

   getCompetition(id: number): Observable<Competition> {

  	return of (COMPETITIONS.find(competition => competition.id === id));
  }

}
