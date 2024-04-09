import { Routes } from '@angular/router';
import { UserUpdateComponent } from './user/user-update/user-update.component';
import { SignupComponent } from './user/signup/signup.component';
import { SigninComponent } from './user/signin/signin.component';
import { ReviewAddComponent } from './review/review-add/review-add.component';
import { ReviewReadComponent } from './review/review-read/review-read.component';
import { ReviewUpdateComponent } from './review/review-update/review-update.component';
import { GameComponent } from './game/game.component';
import { HomeComponent } from './home.component';


export const routes: Routes = [
    {path: "reviews", component: ReviewReadComponent},
    {path: "addreview", component: ReviewAddComponent},
    {path: "updatereview", component: ReviewUpdateComponent},
    {path: "signin", component: SigninComponent},
    {path: "signup", component: SignupComponent},
    {path: "profile", component: UserUpdateComponent},
    {path: "games", component: GameComponent},
    {path: "", component: HomeComponent}
];
