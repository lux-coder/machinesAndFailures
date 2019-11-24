import { Component, OnInit, OnDestroy, ViewChild } from '@angular/core';
import { Subscription } from 'rxjs';
import { Machine } from '../model/machine';
import { MachineService } from '../service/machine.service';
import { MatSort, MatPaginator } from '@angular/material';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-machine',
  templateUrl: './machine.component.html',
  styleUrls: ['./machine.component.scss']
})
export class MachineComponent implements OnInit, OnDestroy {  

  private subscriptions: Subscription[] = [];
  machineId: number;
  machines: Machine[] = [];
  host: string;

  displayedColumns: string[] = ['position', 'name', 'type', 'manufacturer', 'resolved'];
  dataSource = new MatTableDataSource<Machine>([]);

  @ViewChild(MatSort, {static: true}) sort: MatSort;


  constructor(
    private machineService: MachineService    
  ) {
    console.log("In constructor");
   }

  ngOnInit() {

    this.getMachines();

    
  }


  getMachines(): any {
    this.subscriptions.push(this.machineService.getMachines().subscribe(
      (response: Machine[]) => {        
        this.machines = response;
        this.dataSource = new MatTableDataSource(this.machines);
        
        console.log(this.machines);        
      },
      error => {
        console.log(error);        
      }
    ));
  }

  /* getMachines(): void{
    this.subscriptions.push(this.machineService.getMachines().subscribe(
      (response: Machine[] => {
        this.machine = response;
        console.log(this.machineId);
      },
      error => {
        console.log(error);
      });
      )
    ))
  } */

  ngOnDestroy() {
    this.subscriptions.forEach(sub => sub.unsubscribe());
  }


}
