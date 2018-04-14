import { Routes, RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { BoardsListComponent } from './boards-list/boards-list.component';
import { BoardViewComponent } from './board-view/board-view.component';

const boardsRoutes: Routes = [
    {
        path: 'boards/:username',
        component: BoardsListComponent
    },
    {
        path: 'boards/:username/:board',
        component: BoardViewComponent
    }
];

@NgModule({
    imports: [RouterModule.forChild(boardsRoutes)],
    exports: [RouterModule]
})
export class BoardsRoutingModule { }
