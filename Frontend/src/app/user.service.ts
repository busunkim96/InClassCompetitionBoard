import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';

import { Users } from './MOCK-USERS';
import { User } from './user';

@Injectable()
export class UserService {

  constructor() { }

  getUsers(): Observable<User[]> {
  	return of(Users);
  	/* todo: replace with GET call to backend API */
  }


}
