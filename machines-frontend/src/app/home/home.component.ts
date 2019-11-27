import { Component, OnInit, OnDestroy, ViewChild } from '@angular/core';
import { Subscription, Observable } from 'rxjs';
import { Failure } from '../model/failure';
import { MatTableDataSource, MatSort, MatPaginator } from '@angular/material';
import { FailureService } from '../service/failure.service';
import { DataSource } from '@angular/cdk/table';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit, OnDestroy {

  private subscriptions: Subscription[] = [];
  failures: Failure[] = [];
  host: string;

  displayedColumns: string[] = ['position', 'title', 'description', 'machineName', 'priority', 'timestamp'];
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
    this.subscriptions.push(this.failureService.getFailures().subscribe(
      (response: Failure[]) => {
        this.failures = response;
        this.dataSource = new MatTableDataSource(this.failures);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
        //console.log("Console log");
        //console.log(this.dataSource.sort);
      },
      error => {
        console.log(error);
      }
    ));
  } 

  onRowClicked(row){
    console.log("Row clicked: ", row);
  }

   
 
  ngOnDestroy(){
    this.subscriptions.forEach(sub => sub.unsubscribe());
  }

}

/* export class FailureDataSource extends DataSource<any>{

  constructor (private failureService: FailureService){
    super();
  }

  connect(): Observable<Failure[]>{
    return this.failureService.getFailures();
  }

  disconnect(){}

} */
