import { Component, OnInit, Inject } from '@angular/core';
import { NavigationService } from '../../core/navigation.service';
import { BoardService } from '../shared/board.service';
import { MatDialogRef, MAT_DIALOG_DATA, MatDialog } from '@angular/material';
import { LocationService } from '../shared/location.service';
import { LocationDto } from '../../shared/dto/location.dto';

@Component({
  selector: 'app-board-view',
  templateUrl: './board-view.component.html',
  styleUrls: ['./board-view.component.css'],
  providers: [NavigationService]
})
export class BoardViewComponent implements OnInit {
  locations: LocationDto[] = [];
  username = this.navigationService.getUsernameFromUrl();
  boardName = this.navigationService.getBoardNameFromUrl();
  color = 'primary';
  mode = 'indeterminate';
  showSpinner = true;

  constructor(
    private navigationService: NavigationService,
    private boardService: BoardService,
    private locationService: LocationService,
    public dialog: MatDialog
  ) { }

  ngOnInit() {
    this.getBoardLocations();
  }

  private getBoardLocations() {
    this.showSpinner = true;
    this.boardService.getBoardLocations(this.username, this.boardName).subscribe(board => this.assignLocations(board));
  }

  private assignLocations(locations: any): void {
    this.showSpinner = false;
    this.locations = locations;
  }

  showAddLocationDialog(): void {
    const dialogRef = this.dialog.open(AddLocationDialogComponent, {
      width: '250px',
      data: { location: '' }
    });

    dialogRef.afterClosed().subscribe(location => {
      this.showSpinner = true;
      this.locationService.addLocation(location, this.username, this.boardName).subscribe(() => this.getBoardLocations());
    });
  }

  deleteLocation(name: string) {
    this.locationService.deleteLocation(name, this.username, this.boardName);

    this.getBoardLocations();
  }

}

@Component({
  selector: 'app-location-board-dialog',
  templateUrl: 'add-location-dialog.html',
})
export class AddLocationDialogComponent {
  constructor(
    public dialogRef: MatDialogRef<AddLocationDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) { }

    onCancelClick(): void {
      this.dialogRef.close();
    }
  }
