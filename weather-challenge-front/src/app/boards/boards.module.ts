import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BoardsListComponent, AddBoardDialogComponent } from './boards-list/boards-list.component';
import { BoardService } from './shared/board.service';
import { BoardsRoutingModule } from './boards-routing.module';
import { HttpModule } from '@angular/http';
import { CoreModule } from '../core/core.module';
import {
  MatListModule,
  MatCardModule,
  MatButtonModule,
  MatFormFieldModule,
  MatDialogModule,
  MatIconModule,
  MatInputModule,
  MatTooltipModule,
  MatProgressSpinnerModule
} from '@angular/material';
import { FormsModule } from '@angular/forms';
import { BoardViewComponent, AddLocationDialogComponent } from './board-view/board-view.component';
import { LocationService } from './shared/location.service';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    BoardsListComponent,
    BoardViewComponent,
    AddBoardDialogComponent,
    AddLocationDialogComponent
  ],
  entryComponents: [
    AddBoardDialogComponent,
    AddLocationDialogComponent
  ],
  imports: [
    CommonModule,
    HttpModule,
    HttpClientModule,
    CoreModule,
    MatCardModule,
    MatButtonModule,
    MatListModule,
    MatIconModule,
    MatTooltipModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatProgressSpinnerModule,
    FormsModule,
    BoardsRoutingModule
  ],
  providers: [
    BoardService,
    LocationService
  ]
})
export class BoardsModule { }
