import { Injectable } from '@angular/core';
import { ServerConstant } from '../constant/server-constant';
import { HttpClient, HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Failure } from '../model/failure';

@Injectable({
  providedIn: 'root'
})
export class FailureService {
  
  constant: ServerConstant = new ServerConstant();
  public host: string = this.constant.host;

  constructor(private http: HttpClient) { }

  getFailures(): Observable<Failure[]>{
    return this.http.get<Failure[]>(`${this.host}/failure/list`);
  }




}
