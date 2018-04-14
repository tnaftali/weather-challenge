import { Component, OnInit, Inject } from '@angular/core';
import { NavigationService } from '../../core/navigation.service';
import { User } from '../../shared/models/user';
import { BoardService } from '../shared/board.service';
import { MatDialogRef, MAT_DIALOG_DATA, MatDialog } from '@angular/material';
import { BoardDto } from '../../shared/dto/board.dto';

@Component({
  selector: 'app-boards-list',
  templateUrl: './boards-list.component.html',
  styleUrls: ['./boards-list.component.css'],
  providers: [NavigationService]
})
export class BoardsListComponent implements OnInit {
  boards: BoardDto[];
  username = this.navigationService.getUsernameFromUrl();

  constructor(
    private boardService: BoardService,
    private navigationService: NavigationService,
    public dialog: MatDialog
  ) { }

  ngOnInit() {
    this.getUserBoards(this.username);
  }

  private getUserBoards(username: string) {
    this.boardService.getAllUserBoards(username).subscribe(boards => this.success(boards));
  }

  private success(boards: any): void {
    this.boards = boards;
  }

  showAddBoardDialog(): void {
    const dialogRef = this.dialog.open(AddBoardDialogComponent, {
      width: '250px',
      data: { name: '' }
    });

    dialogRef.afterClosed().subscribe(result => {
      this.boardService.addBoard(this.username, result).subscribe(newBoard => this.addBoardToList(newBoard));
    });
  }

  public goToBoardView(boardName: string) {
    this.navigationService.goToBoard(this.username, boardName);
  }

  // public deleteBoard(name: string) {
  //   this.boardService.deleteBoard(name);

  //   this.boards.splice(this.boards.findIndex(x => x.name === name) , 1);
  // }

  private addBoardToList(user: any) {
    this.boards.push(user);
  }

}

@Component({
  selector: 'app-add-board-dialog',
  templateUrl: 'add-board-dialog.html',
})
export class AddBoardDialogComponent {

  constructor(
    public dialogRef: MatDialogRef<AddBoardDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) { }

    onCancelClick(): void {
      this.dialogRef.close();
    }

  }
