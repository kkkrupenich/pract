import { Injectable } from '@angular/core';
import { User } from '../entities/user';
import axios from 'axios';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor() { }

  async signIn(email: string, password: string) {
    let response = await axios.post(
      "http://localhost:8080/signin",
      {
        "email": email,
        "password": password
      }
    )
    return response.data
  }

  async signUp(user: User, password: string) {
    let response = await axios.post(
      "http://localhost:8080/signup",
      {
        "email": user.email,
        "password": password,
        "fio": user.fio,
        "serialNumber": user.serialNumber,
        "identificationNumber": user.identificationNumber,
        "registration": user.registration,
        "issueDate": user.issueDate,
        "expirationDate": user.expirationDate
      }
    )
    return response.data;
  }
}
