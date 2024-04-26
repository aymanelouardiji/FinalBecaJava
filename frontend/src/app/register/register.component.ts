import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserAuthService } from '../services/user-auth.service';
import { UserService } from '../services/user.service';
import { Registeruser } from './register-client.Module';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  public formRegister!: FormGroup;

  public error: string = '';
  userobj: Registeruser = new Registeruser();

  constructor(
    private formBuilder: FormBuilder,
    private http: HttpClient,
    private router: Router,
    private userAuthService: UserAuthService
  ) { }

  ngOnInit(): void {
    this.formRegister = this.formBuilder.group(
      {
        username: ['', [Validators.required]],
        email: ['', [Validators.required, this.emailValidator]],
        password: ['', Validators.required],
        passwordConfirm: ['', [Validators.required]],
      },
      { validators: this.passwordMatchValidator }
    );

  }

  // Custom validator function to compare passwords
  passwordMatchValidator(g: FormGroup) {
    return g.get('password').value === g.get('passwordConfirm').value
      ? null
      : { mismatch: true };
  }

  emailValidator(control: FormControl) {
    const email = control.value;
    if (!email.endsWith('@gmail.com')) {
      return { email: true };
    }
    return null;
  }

  registerUser() {
    delete this.formRegister.value.passwordConfirm;
    this.userAuthService.register(this.formRegister.value).subscribe(
      (response) => {
        console.log(response);
      },
      (error) => {
        console.log(error);
      }
    );
    alert('Vous avez été inscrit avec succès.');
    this.router.navigate(['/login']);
  }

}
