import { Component, OnInit, OnDestroy, ViewChild } from '@angular/core';
import { Subscription, Observable } from 'rxjs';
import { Failure } from '../model/failure';
import { MatTableDataSource, MatSort, MatPaginator } from '@angular/material';
import { FailureService } from '../service/failure.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit, OnDestroy {

  private subscriptions: Subscription[] = [];
  failures: Failure[] = [];
  fail: boolean;
  host: string;

  displayedColumns: string[] = ['position', 'title', 'description', 'machineName', 'priority', 'timestamp', 'status'];
  dataSource = new MatTableDataSource<Failure>([]);

  @ViewChild(MatSort, {static: true}) sort: MatSort;
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;


  constructor(
    private failureService: FailureService
  ) { 
    console.log("In failure constructor");
  }

  ngOnInit() {
    this.getFailures();
  }

   getFailures(): any {
    this.subscriptions.push(this.failureService.getFailuresUnresolved().subscribe(
      (response: Failure[]) => {
        this.failures = response;
        this.dataSource = new MatTableDataSource(this.failures);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      },
      error => {
        console.log(error);
      }
    ));
  } 

  onRowClicked(row){
    console.log("Row clicked: ", row);
  }

  onStatusChange(failure: Failure): void{
    console.log("ID of failure is: ", failure);    
    this.subscriptions.push(
      this.failureService.setFailureResolved(failure).subscribe(
        response => {
          console.log(response);
        },
        error => {
          console.log(error);}
      )
    );          
  }

   
 
  ngOnDestroy(){
    this.subscriptions.forEach(sub => sub.unsubscribe());
  }

}
