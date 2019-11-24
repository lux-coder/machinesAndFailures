import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Machine } from '../model/machine';
import { Failure } from '../model/failure';
import { ServerConstant } from '../constant/server-constant';

@Injectable({
  providedIn: 'root'
})
export class MachineService {

  constant: ServerConstant = new ServerConstant();
  public host: string = this.constant.host;

  constructor(private http: HttpClient) { }

  addMachine(machine: Machine): Observable<Machine | HttpErrorResponse>{
    return this.http.post<Machine>(`${this.host}/machine/save`, machine);
  }

  getMachines(): Observable<Machine[]>{
    return this.http.get<Machine[]>(`${this.host}/machine/list`);
  }

  getMachineInformation(name: string): Observable<Machine>{
    return this.http.get<Machine>(`${this.host}/machine/${name}`);
  }

  searchMachine(name: string): Observable<Machine[]>{
    return this.http.get<Machine[]>(`${this.host}/machine/findByName/${name}`);
  }

  updateMachine(updateMachine: Machine): Observable<Machine>{
    return this.http.post<Machine>(`${this.host}/machine/update`, updateMachine);
  }


}
