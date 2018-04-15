import { Injectable } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

@Injectable()
export class NavigationService {
    constructor(private router: Router, private route: ActivatedRoute) {
        this.router = router;
    }

    getUsernameFromUrl(): any {
        return this.route.snapshot.params['username'];
    }

    getBoardNameFromUrl(): any {
        return this.route.snapshot.params['board'];
    }

    goToUsersList() {
        this.router.navigate(['./']);
    }

    goToUserBoards(username: string) {
        this.router.navigate(['./boards/' + username]);
    }

    goToBoard(username: string, boardName: string) {
        this.router.navigate(['./boards/' + username + '/' + boardName]);
    }
}
