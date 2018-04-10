import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import { HttpClient, HttpHeaders } from '@angular/common/http';



import { COMPETITIONS } from './MOCK-COMPETITIONS';
import { Competition } from './competition';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class CompetitionsService {

  constructor( private http: HttpClient) { }

  private heroesUrl = 'http://localhost:8080/competition/getAll';  // URL to web api

  getCompetitions(): Observable<Competition[]> {
  	return this.http.get<Competition[]>(this.heroesUrl);
  }

   getCompetition(id: number): Observable<Competition> {

  	return of (COMPETITIONS.find(competition => competition.id === id));
  }

}
