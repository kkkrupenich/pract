import { Component } from '@angular/core';
import { AuthorizationService } from '../../services/authorization.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-signin',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './signin.component.html',
  styleUrl: './signin.component.css'
})
export class SigninComponent {
  email = '';
  password = '';
  constructor(private authorizationService: AuthorizationService) {}

  async signIn() {
    if (this.email !== '' && this.password !== '') {
      const data = await this.authorizationService.signIn(this.email, this.password);
      if (data !== "no user with creds") {
        window.location.href = '/';
      } else {
        alert('No user with such creds');
      }
    } else {
      alert('Not valid creds');
    }
  }
}
