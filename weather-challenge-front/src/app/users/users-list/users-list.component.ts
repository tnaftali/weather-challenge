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
  constructor(
    private userService: UserService,
    private navigationService: NavigationService,
    public dialog: MatDialog
  ) { }

  public users: UserDto[] = [];

  ngOnInit() {
    this.getUsers();
  }

  showAddUserDialog(): void {
    const dialogRef = this.dialog.open(AddUserDialogComponent, {
      width: '250px',
      data: { username: '' }
    });

    dialogRef.afterClosed().subscribe(result => {
      this.userService.addUser(result).subscribe(newUser => this.addUserToList(newUser));
    });
  }

  public deleteUser(username: string) {
    this.userService.deleteUser(username);

    this.users.splice(this.users.findIndex(x => x.username === username) , 1);
  }

  public goToUserBoards(username: string) {
    this.navigationService.goToUserBoards(username);
  }

  private addUserToList(user: any) {
    this.users.push(user);
  }

  private getUsers() {
    this.userService.getAllUsers().subscribe(users => this.success(users));
  }

  private success(users: any): void {
    this.users = users;
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
