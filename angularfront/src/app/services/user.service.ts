import { Injectable } from '@angular/core';
import axios from 'axios';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor() { }

  async updatePassword(password: string) {
    let response = await axios.post(
      "http://localhost:8080/updateuser",
      {
        "password": password
      }
    )
    return response.data
  }

  async getUserById(id: number) {
    let response = await axios.get(
      "http://localhost:8080/user/" + id
    )
    return response.data
  }
}
