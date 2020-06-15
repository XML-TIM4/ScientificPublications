import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BehaviorSubject} from 'rxjs';
import {User} from '../model/user.model';
import {tap} from 'rxjs/operators';
import {Router} from '@angular/router';

export interface IUserRegister {
  password: string;
  email: string;
  editor: boolean;
  expertise: string;
}

interface IFullToken {
  sub: string;
  iss: string;
  iat: number;
  exp: number;
  roles: string;
}

export interface IToken {
  authority: string;
  expiration: number;
}

@Injectable({ providedIn: 'root' })
export class AuthService {
  user = new BehaviorSubject<User>(null);
  private tokenExpirationTimer: any;

  constructor(private http: HttpClient, private router: Router) {}

  private handleAuthentication(token: string) {
    const parsedToken: IToken = this.parseJwt(token);
    console.log('jwt ', parsedToken);
    const expirationDate = new Date(parsedToken.expiration * 1000);
    const user = new User(
      token,
      expirationDate,
      parsedToken.authority
    );
    this.user.next(user);
    localStorage.setItem('token', token);
  }

  signup(registerUser: IUserRegister) {
    return this.http.post<number>(
      'http://localhost:8080/api/register',
      {
        email: registerUser.email,
        password: registerUser.password,
        editor: registerUser.editor,
        expertise: registerUser.expertise
      }
    );
  }

  login(email: string, password: string) {
    return this.http.post<{token: string}>(
      'http://localhost:8080/api/login',
      {
        email,
        password
      }
    ).pipe(
      tap(resData => {
        console.log(resData.token, ' PIPE LOGINA');
        this.handleAuthentication(resData.token);
      })
    );
  }

  logout() {
    this.user.next(null);
    localStorage.removeItem('token');
    if (this.tokenExpirationTimer) {
      clearTimeout(this.tokenExpirationTimer);
    }
    this.router.navigate(['/login']);
  }


  parseJwt = (token: string): IToken => {
    const base64Url = token.split('.')[1];
    const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    const jsonPayload = decodeURIComponent(atob(base64).split('').map((c: any) => {
      return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));

    const fullToken: IFullToken = JSON.parse(jsonPayload);
    if (fullToken.roles.split(',').length > 1) {
      return {
        authority: fullToken.roles.split(',')[1],
        expiration: fullToken.exp
      };
    } else {
      return {
        authority: fullToken.roles,
        expiration: fullToken.exp
      };
    }
  }

}
