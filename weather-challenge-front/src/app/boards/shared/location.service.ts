import { Injectable } from '@angular/core';
import { backend_api_boards, backend_api_locations_add, backend_api_locations_delete } from '../../shared/constants';
import { Observable } from 'rxjs/Observable';
import { HttpService } from '../../core/http.service';
import 'rxjs/add/operator/map';
import { LocationCriteriaDto } from '../../shared/dto/location-criteria.dto';

@Injectable()
export class LocationService {
    constructor(private httpService: HttpService) { }

    addLocation(location: string, username: string, boardName: string) {
        const locationCriteriaDto = new LocationCriteriaDto(boardName, username, location);

        return this.httpService.doPost(backend_api_locations_add, locationCriteriaDto);
    }

    deleteLocation(location: string, username: string, boardName: string) {
        const locationCriteriaDto = new LocationCriteriaDto(boardName, username, location);

        return this.httpService.doDelete(backend_api_locations_delete, locationCriteriaDto).subscribe();
    }

}
