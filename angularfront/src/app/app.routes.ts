import { Routes } from '@angular/router';
import { UserUpdateComponent } from './user/user-update/user-update.component';
import { SignupComponent } from './user/signup/signup.component';
import { SigninComponent } from './user/signin/signin.component';
import { CardAddComponent } from './cards/card-add/card-add.component';
import { CardReadComponent } from './cards/card-read/card-read.component';
import { CardUpdateComponent } from './cards/card-update/card-update.component';


export const routes: Routes = [
    {path: "signin", component: SigninComponent},
    {path: "signup", component: SignupComponent},
    {path: "userupdate", component: UserUpdateComponent},
    {path: "addcard", component: CardAddComponent},
    {path: "cards", component: CardReadComponent},
    {path: "updatecard", component: CardUpdateComponent}
];
