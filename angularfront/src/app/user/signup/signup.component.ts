import { Component } from '@angular/core';
import { AuthorizationService } from '../../services/authorization.service';
import { User } from '../../entities/user';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})
export class SignupComponent {
  email = '';
  password = '';
  fio = '';
  serialNumber = '';
  identificationNumber = '';
  registration = '';
  issueDate = new Date();
  expirationDate = new Date();
  user: User = new User();

  constructor(private authorizationService: AuthorizationService) {}

  async signUp() {
    this.user.email = this.email;
    this.user.fio = this.fio;
    this.user.serialNumber = this.serialNumber;
    this.user.identificationNumber = this.identificationNumber;
    this.user.balance = 0;
    this.user.issueDate = this.issueDate;
    this.user.expirationDate = this.expirationDate;
    this.user.registration = this.registration;

    await this.authorizationService.signUp(this.user, this.password);
    window.location.href = '/';
  }

}
