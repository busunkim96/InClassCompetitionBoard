import { Component, OnInit } from '@angular/core';
import { SignUpUser } from '../sign-up-user';
import {SignupService} from '../signup.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  model = new SignUpUser('', '', 2, '');
  submitted = false;

  constructor(private signupService: SignupService, private router: Router) { }

  onSubmit() {
    this.submitted = true;
    console.log(this.model);
    this.signupService.registerUser(this.model).subscribe();
    this.router.navigate(['/login']);
  }

}


