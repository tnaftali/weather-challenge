import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { UsersModule } from './users/users.module';
import { AppRoutingModule } from './/app-routing.module';
import { HttpModule } from '@angular/http';
import { HttpService } from './core/http.service';
import { CoreModule } from './core/core.module';
import { MatListModule, MatCardModule } from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BoardsModule } from './boards/boards.module';
import { HttpClientModule } from '@angular/common/http';
import { BoardsListComponent } from './boards/boards-list/boards-list.component';
import { BoardViewComponent } from './boards/board-view/board-view.component';
import { UsersListComponent } from './users/users-list/users-list.component';
import { BoardsRoutingModule } from './boards/boards-routing.module';
import { UsersRoutingModule } from './users/users-routing.module';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    UsersModule,
    BoardsModule,
    CoreModule,
    HttpModule,
    HttpClientModule,
    BrowserAnimationsModule,
    UsersRoutingModule,
    BoardsRoutingModule,
    AppRoutingModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
