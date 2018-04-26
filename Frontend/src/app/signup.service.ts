import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';

import { SignUpUser} from './sign-up-user';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';


const options = {
  headers: new HttpHeaders({'Content-Type': 'application.jso'})
};

@Injectable()
export class SignupService {

  constructor(private http: HttpClient) { }
  private url = 'http://localhost:8080/register/User';

  registerUser(user: SignUpUser): Observable<{}> {
    const params = new HttpParams().set('userName', user.username).set('email', user.username).
    set('password', user.password).set('type', String(user.type));
    return this.http.post(this.url, params);
  }

  loginUser(user: SignUpUser): Observable<{}> {
  const params = new HttpParams().set('userName', user.username).set('password', user.password).set('type', '' + user.type);
  return this.http.post('http://localhost:8080/login/User', params);
}

}
