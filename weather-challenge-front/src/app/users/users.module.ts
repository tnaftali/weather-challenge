import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UsersListComponent, AddUserDialogComponent } from './users-list/users-list.component';
import { UsersRoutingModule } from './users-routing.module';
import { UserService } from './shared/user.service';
import { HttpModule } from '@angular/http';
import { CoreModule } from '../core/core.module';
import {
  MatListModule,
  MatCardModule,
  MatButtonModule,
  MatIconModule,
  MatTooltipModule,
  MatDialogModule,
  MatFormFieldModule,
  MatInputModule
} from '@angular/material';
import { FormsModule } from '@angular/forms';
import { BoardsRoutingModule } from '../boards/boards-routing.module';
import { BoardService } from '../boards/shared/board.service';

@NgModule({
  imports: [
    CommonModule,
    HttpModule,
    CoreModule,
    MatCardModule,
    MatButtonModule,
    MatListModule,
    MatIconModule,
    MatTooltipModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    UsersRoutingModule
  ],
  providers: [UserService],
  declarations: [UsersListComponent, AddUserDialogComponent],
  entryComponents: [AddUserDialogComponent]
})
export class UsersModule { }
