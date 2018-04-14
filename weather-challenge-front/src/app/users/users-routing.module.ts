import { Routes, RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { UsersListComponent } from './users-list/users-list.component';

const usersRoutes: Routes = [
    {
        path: '',
        component: UsersListComponent
    }
];

@NgModule({
    imports: [RouterModule.forChild(usersRoutes)],
    exports: [RouterModule]
})
export class UsersRoutingModule { }
