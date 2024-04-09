import { Injectable } from '@angular/core';
import axios from 'axios';

@Injectable({
  providedIn: 'root'
})
export class GameService {

  constructor() { }

  async getGames() {
    let response = await axios.get(
        "http://localhost:8080/games"
    )
    return response.data
  }
}
