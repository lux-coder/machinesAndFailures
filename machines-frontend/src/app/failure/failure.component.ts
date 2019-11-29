import { Component, OnInit, OnDestroy, ViewChild } from '@angular/core';
import { Subscription } from 'rxjs';
import { Machine } from '../model/machine';
import { MachineService } from '../service/machine.service';
import { MatSort, MatPaginator } from '@angular/material';
import { MatTableDataSource } from '@angular/material/table';
import { FormGroup, FormBuilder, FormArray, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { FailureService } from '../service/failure.service';
import { Failure } from '../model/failure';
import { FileUploader } from 'ng2-file-upload';


@Component({
  selector: 'app-failure',
  templateUrl: './failure.component.html',
  styleUrls: ['./failure.component.scss']
})
export class FailureComponent implements OnInit, OnDestroy {

  private subscriptions: Subscription[] = [];
  private failure: Failure;
  private failures: Failure[] = [];

  displayedColumns: string[] = ['machineName', 'title', 'description', 'status', 'priority', 'timestamp'];
  dataSource = new MatTableDataSource<Failure>([]);

  @ViewChild(MatSort, {static: true}) sort: MatSort;
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;


  constructor(
    private router: Router,
    private failureService: FailureService
  ) {    
   }

  ngOnInit() {
  }

  getFailures(): any {
    this.subscriptions.push(this.failureService.getFailures().subscribe(
      (response: Failure[]) => {
        this.failures = response;
        this.dataSource = new MatTableDataSource(this.failures);
        console.log(this.failure);
      },
      error => {
        console.log(error);
      }
    ));
  }

  ngOnDestroy(){
    this.subscriptions.forEach(sub => sub.unsubscribe());
  }

}
