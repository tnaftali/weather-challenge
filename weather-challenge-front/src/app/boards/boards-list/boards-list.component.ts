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
  color = 'primary';
  mode = 'indeterminate';
  showSpinner = true;

  constructor(
    private boardService: BoardService,
    private navigationService: NavigationService,
    public dialog: MatDialog
  ) { }

  ngOnInit() {
    this.getUserBoards();
  }

  private getUserBoards() {
    this.showSpinner = true;
    this.boardService.getAllUserBoards(this.username).subscribe(boards => this.assignBoards(boards));
  }

  private assignBoards(boards: any): void {
    this.showSpinner = false;
    this.boards = boards;
  }

  showAddBoardDialog(): void {
    const dialogRef = this.dialog.open(AddBoardDialogComponent, {
      width: '250px',
      data: { name: '' }
    });

    dialogRef.afterClosed().subscribe(board => {
      if (board) {
        this.showSpinner = true;
        this.boardService.addBoard(this.username, board).subscribe(newBoard => this.getUserBoards());
      }
    });
  }

  goToBoardView(boardName: string) {
    this.navigationService.goToBoard(this.username, boardName);
  }

  goToUsersList(boardName: string) {
    this.navigationService.goToUsersList();
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
