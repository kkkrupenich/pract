import { Injectable, Optional, SkipSelf } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { BehaviorSubject } from 'rxjs';
import axios from 'axios';
import { User } from '../entities/user';
import { UserService } from './user.service';
import { AuthenticationService } from './authentication.service';

@Injectable({
  providedIn: 'root'
})
export class AuthorizationService {
  private isAuthenticated = false;
  private _user = new BehaviorSubject(new User);
  user = this._user.asObservable();

  constructor(private cookieService: CookieService,
    private authenticationService: AuthenticationService,
    userService: UserService,
    @Optional() @SkipSelf() sharedService?: AuthorizationService) {
    if (sharedService) {
      throw new Error('AuthorizationService is already created');
    }

    this.isAuthenticated = this.cookieService.get('token').length !== 0;

    if (this.isAuthenticated) {
      axios.defaults.headers.common['Authorization'] = `Bearer ${this.cookieService.get('token')}`;
      userService.getUserById(parseInt(this.cookieService.get('userId'))).then(user => this._user.next(user));
    } else {
      axios.defaults.headers.common['Authorization'] = '';
    }
  }

  getIsAuthenticated() {
    return this.isAuthenticated;
  }

  setUser(user: User) {
    this._user.next(user);
  }

  async signIn(email: string, password: string) {
    const data = await this.authenticationService.signIn(email, password);
    this.logIn(data.token, data.id);
    return data;
  }

  async signUp(user: User, password: string) {
    const data = await this.authenticationService.signUp(user, password);
    this.logIn(data.token, data.id);
  }

  logOut() {
    this.cookieService.delete('token');
    this.cookieService.delete('userId');
    axios.defaults.headers.common['Authorization'] = '';
  }

  private logIn(token: string, userId: string) {
    this.cookieService.set('token', token);
    this.cookieService.set('userId', userId);
    axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
  }
}
