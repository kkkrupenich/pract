import { Game } from "./game";

export class Review {
    id!: number;
    user!: number;
    game!: Game;
    message!: string;
    rating!: string;
    date!: Date;
}