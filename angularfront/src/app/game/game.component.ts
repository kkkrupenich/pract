import { Component } from '@angular/core';
import { Game } from '../entities/game';
import { GameService } from '../services/game.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-game',
  standalone: true,
  imports: [],
  templateUrl: './game.component.html',
  styleUrl: './game.component.css'
})
export class GameComponent {
  games: Game[] = [];

  constructor(private gameService: GameService, private _router: Router) {}

  async ngOnInit() {
    this.games = await this.gameService.getGames();
  }

  addReview(gameId: number) {
    console.log(gameId);
    this._router.navigate(['/addreview', {id: gameId}]);
  }
}
