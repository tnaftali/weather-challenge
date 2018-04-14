import { Injectable } from '@angular/core';
import { backend_api_boards } from '../../shared/constants';
import { Observable } from 'rxjs/Observable';
import { HttpService } from '../../core/http.service';
import 'rxjs/add/operator/map';

@Injectable()
export class BoardService {
    constructor(private httpService: HttpService) { }

    getAllUserBoards(username: string) {
        return this.httpService.doGet(backend_api_boards + username, {});
    }

    getBoardLocations(username: string, boardName: string) {
        return this.httpService.doGet(`${backend_api_boards}${username}/${boardName}`, {});
    }

    // deleteBoard(username: string) {
    //     return this.httpService.doDelete(backend_api_users + username, {}).subscribe();
    // }

    addBoard(username: string, boardName: string) {
        return this.httpService.doPost(`${backend_api_boards}${username}/${boardName}`, {});
    }

}
