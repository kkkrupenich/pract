import { Component } from '@angular/core';
import { User } from '../../entities/user';
import { FormsModule } from '@angular/forms';
import { AuthorizationService } from '../../services/authorization.service';
import { UserService } from '../../services/user.service';
import axios from 'axios';

@Component({
  selector: 'app-user-update',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './user-update.component.html',
  styleUrl: './user-update.component.css'
})
export class UserUpdateComponent {
  password = '';
  user = new User();

  constructor(private authorizationService: AuthorizationService,
    private userService: UserService) {
    authorizationService.user.subscribe(user => {
      this.user = user;
    });
  }

  logout() {
    this.authorizationService.logOut();
    window.location.href = '/signin';
  }

  async updateUser() {
    await this.userService.updatePassword(this.password);
    alert('Обновлено');
  }
}
