import { Component, OnInit, Inject } from '@angular/core';
import { UserService } from '../shared/user.service';
import { MatDialogRef, MAT_DIALOG_DATA, MatDialog } from '@angular/material';
import { NavigationService } from '../../core/navigation.service';
import { UserDto } from '../../shared/dto/user.dto';

@Component({
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.css']
})
export class UsersListComponent implements OnInit {
  color = 'primary';
  mode = 'indeterminate';
  showSpinner = true;
  public users: UserDto[] = [];

  constructor(
    private userService: UserService,
    private navigationService: NavigationService,
    public dialog: MatDialog
  ) { }

  ngOnInit() {
    this.getUsers();
  }

  private getUsers() {
    this.showSpinner = true;
    this.userService.getAllUsers().subscribe(users => this.assignUsers(users));
  }

  private assignUsers(users: any): void {
    this.showSpinner = false;
    this.users = users;
  }

  showAddUserDialog(): void {
    const dialogRef = this.dialog.open(AddUserDialogComponent, {
      width: '250px',
      data: { username: '' }
    });

    dialogRef.afterClosed().subscribe(user => {
      if (user) {
        this.showSpinner = true;
        this.userService.addUser(user).subscribe(() => this.getUsers());
      }
    });
  }

  deleteUser(username: string) {
    this.userService.deleteUser(username);

    this.users.splice(this.users.findIndex(x => x.username === username) , 1);
  }

  goToUserBoards(username: string) {
    this.navigationService.goToUserBoards(username);
  }

}

@Component({
  selector: 'app-add-user-dialog',
  templateUrl: 'add-user-dialog.html',
})
export class AddUserDialogComponent {

  constructor(
    public dialogRef: MatDialogRef<AddUserDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) { }

    onCancelClick(): void {
      this.dialogRef.close();
    }

  }
