import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import {HttpRequest,HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';



import { COMPETITIONS } from './MOCK-COMPETITIONS';
import { Competition } from './competition';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class CompetitionsService {

  constructor( private http: HttpClient) { }

  private getAll = 'http://localhost:8080/competition/getAll';  // URL to web api
  private createCompetitionURL = 'http://localhost:8080/competition/create';
  private uploadURL = 'http://localhost:8080/competition/uploadFile/';
  getCompetitions(): Observable<Competition[]> {
    return this.http.get<Competition[]>(this.getAll);
  }

   getCompetition(id: number): Observable<Competition> {

    return of (COMPETITIONS.find(competition => competition.id === id));
  }

  createCompetition(competition: {}): Observable<{}> {
    let params =  new HttpParams();
    for (const key in competition) {
      const val = competition[key];
      params = params.append(key, val);
    }
    console.log(params);
    return this.http.post(this.createCompetitionURL, params);
  }

  uploadTestcase(formData): Observable<{}> {
    // let params =  new HttpParams();
    // let formData = new FormData();
    // formData.append('compId', parameters['compId']);
    // formData.append('uploadFile', parameters['uploadFile']);
    // const options = {
    //   params: params,
    //   reportProgress: true,
    // };
    // const req = new HttpRequest('POST', this.uploadURL, formData, options);
    // return  this.http.request(req);

    const req = new HttpRequest('POST',  this.uploadURL, formData, {
      reportProgress: true,
    });

    return this.http.request(req);
  }

}
