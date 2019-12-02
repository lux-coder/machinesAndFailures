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
  public counter: string;

  constructor(private http: HttpClient) { }

  getFailures(): Observable<Failure[]>{
    return this.http.get<Failure[]>(`${this.host}/failure/list`);
  }

  getFailuresUnresolved(): Observable<Failure[]>{
    return this.http.get<Failure[]>(`${this.host}/failure`);
  }

  setFailureResolved(failure: Failure): Observable<Failure>{
    console.log(failure);     
    return this.http.put<Failure>(`${this.host}/failure/updateStatus`, failure);
    
  }

  checkForUnresolved(machineName): Observable<any> {    
    console.log(machineName);
    return this.http.get<any>(`${this.host}/failure/unresolved`, machineName);
  }

}
