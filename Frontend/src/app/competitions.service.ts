import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import {HttpRequest, HttpClient, HttpHeaders, HttpParams, HttpResponse} from '@angular/common/http';



import { Competition } from './competition';
import {C} from '@angular/core/src/render3';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class CompetitionsService {

  constructor( private http: HttpClient) { }
  private getById = 'http://localhost:8080/competition/getById/?id=';
  private getAll = 'http://localhost:8080/competition/getAll';  // URL to web api
  private createCompetitionURL = 'http://localhost:8080/competition/create';
  private uploadURL = 'http://localhost:8080/competition/uploadFile/';
  private fileURL = 'http://localhost:8080/competition/getCompetitionCriteria/?compId=';
  private updateScoreURL = 'http://localhost:8080/competition/updateScore/';
  private joinCompetitionURL = 'http://localhost:8080/competition/join/';
  private uploadSubmissionURL = 'http://localhost:8080/competition/uploadSubmission/';

  getCompetitions(): Observable<Competition[]> {
    return this.http.get<Competition[]>(this.getAll);
  }

   getCompetition(id: number): Observable<Competition> {
    return this.http.get<Competition>(this.getById + '' + id);
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


  joinCompetition(compId: number, userId: string): Observable<{}> {
    let params = new HttpParams();
    params = params.append('compId', String(compId)).append('userId', String(userId));
    return this.http.post(this.joinCompetitionURL, params);
  }


  uploadTestcase(formData) {
    const req = new HttpRequest('POST',  this.uploadURL, formData, {
      reportProgress: true,
    });

    return this.http.request(req);
  }
  getFileContent(compId): Observable<string> {
    httpOptions['responseType'] = 'text';
    return this.http.get<string>(this.fileURL + '' + compId, httpOptions );

  }
  updateScore(compId, userId, score):Observable<number>{
    let params =  new HttpParams();
    params = params.append('compId', compId);
    params = params.append('userId', userId);
    params = params.append('score', score);
    console.log(params);
    return this.http.post<number>(this.updateScoreURL, params);

  }

  uploadSubmission(compId, userId, formData): Observable <{}> {
    let params = new HttpParams();
    params = params.append('compId', compId);
    params = params.append('userId', userId);
    const req = new HttpRequest('POST',  this.uploadSubmissionURL, formData, {
      reportProgress: true,
      params: params
    });

    return this.http.request(req);
  }

}
