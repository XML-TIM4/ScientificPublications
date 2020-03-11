import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {AuthService, IUserRegister} from '../../services/auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})
export class SignUpComponent implements OnInit {

  signupForm: FormGroup;
  errorMessage = '';

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit() {
    this.signupForm = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', Validators.required),
      editor: new FormControl('', Validators.required),
      expertise: new FormControl('', Validators.required),
    });
  }

  onSubmit() {
    if (this.signupForm.invalid) {
      return;
    }

    const { email, password, editor, expertise } = this.signupForm.value;
    const newUser: IUserRegister = {
      email,
      password,
      editor,
      expertise,
    };

    this.errorMessage = '';

    this.authService.signup(newUser).subscribe(
      resData => {
        console.log(resData);
        this.signupForm.reset();
        this.router.navigate(['/login']);
      },
      error => {
        if (error.status === 400) {
          this.errorMessage = error.error.message;
        } else {
          this.errorMessage = 'Error';
        }
      }
    );
  }


  get editor() {
    return this.signupForm.get('editor');
  }

  get expertise() {
    return this.signupForm.get('expertise');
  }

  get email() {
    return this.signupForm.get('email');
  }

  get password() {
    return this.signupForm.get('password');
  }


}
