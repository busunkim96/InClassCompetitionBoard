import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';

import { Users } from './MOCK-USERS';
import { User } from './user';
import {HttpClient, HttpHeaders} from '@angular/common/http';


const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application.jso'})
};

@Injectable()
export class UserService {

  constructor(private http: HttpClient) { }
  private url = 'http://localhost:8080/admin/acceptUsers';

  getUsers(): Observable<User[]> {
  	return this.http.get<User[]>('http://localhost:8080/admin/getUsersToBeRegistered');
  }

  acceptUsers(users: Number[]): void {
   this.http.post(this.url, {'userIds': users});

  }


}
