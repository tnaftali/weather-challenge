import { Injectable } from '@angular/core';
import { backend_api_get_users, backend_api_users } from '../../shared/constants';
import { Observable } from 'rxjs/Observable';
import { HttpService } from '../../core/http.service';
import 'rxjs/add/operator/map';

@Injectable()
export class UserService {
    constructor(private httpService: HttpService) { }

    getAllUsers() {
        return this.httpService.doGet(backend_api_get_users, {});
    }

    deleteUser(username: string) {
        return this.httpService.doDelete(backend_api_users + username, {}).subscribe();
    }

    addUser(username: string) {
        return this.httpService.doPost(backend_api_users + username, {});
    }

}
