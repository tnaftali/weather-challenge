import { Injectable } from '@angular/core';
import { Headers, Http, Response, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import { backend_api_base_endpoint, business_error_code, backend_api_get_users } from '../shared/constants';
import { ErrorDto } from '../shared/dto/error.dto';

@Injectable()
export class HttpService {

    headers: Headers;
    options: RequestOptions;

    // private headers = new Headers({
    //     'Content-Type': 'application/json'
    // });

    constructor(private http: Http) {
        this.headers = new Headers({
            'Content-Type': 'application/json'
        });
        this.options = new RequestOptions({ headers: this.headers });
    }

    doGet(service: string, params: any): Observable<Response> {
        return this.doCall(service, 'GET', params, {});
    }

    doPost(service: string, body: any): Observable<Response> {
        return this.doCall(service, 'POST', {}, body);
    }

    doPut(service: string, body: any) {
        return this.doCall(service, 'PUT', {}, body);
    }

    doDelete(service: string, body: any) {
        return this.doCall(service, 'DELETE', {}, body);
    }

    private doCall(service: string, method: string, params: any, body: any): Observable<Response> {
        const observableResponse = this.http.options(backend_api_base_endpoint + service, {
            method: method,
            headers: this.headers,
            params: params,
            body: body
        }).map(this.extractData)
        .catch(error => this.handleError(error));

        return observableResponse;
    }

    private extractData(res: Response) {
        const contentType = res.headers.get('content-type');

        if (contentType && contentType.indexOf('application/json') !== -1) {
            return res.json();
        } else {
            return {};
        }
    }

    private handleError(error: any) {
        const errMsg = (error.message) ? error.message :
        error.status ? `${error.status} - ${error.statusText}` : 'Server error';

        console.error(errMsg);

        return Observable.throw(errMsg);
    }
}
