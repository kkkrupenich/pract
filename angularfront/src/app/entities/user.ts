import { Card } from "./card";
import { Passport } from "./passport";

export class User {
    id!: bigint;
    email!: string;
    fio!: string;
    passport!: Passport;
    balance!: number;
    cards: Card[] = [];
}