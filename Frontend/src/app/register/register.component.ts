import { Component, OnInit } from '@angular/core';
import { SignUpUser } from '../sign-up-user';
import {SignupService} from '../signup.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  model = new SignUpUser('us1', 'us1@cu.edu', 2, '1234');
  submitted = false;

  constructor(private signupService: SignupService) { }

  onSubmit() {
    this.submitted = true;
    console.log(this.model);
    this.signupService.registerUser(this.model).subscribe();
  }

}


