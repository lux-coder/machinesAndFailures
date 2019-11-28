import { Component, OnInit, OnDestroy, ViewChild } from '@angular/core';
import { Subscription } from 'rxjs';
import { Machine } from '../model/machine';
import { MachineService } from '../service/machine.service';
import { MatSort, MatPaginator } from '@angular/material';
import { MatTableDataSource } from '@angular/material/table';
import { FormGroup, FormBuilder, FormArray, FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { FailureService } from '../service/failure.service';
import { Failure } from '../model/failure';

@Component({
  selector: 'app-machine',
  templateUrl: './machine.component.html',
  styleUrls: ['./machine.component.scss']
})
export class MachineComponent implements OnInit, OnDestroy {

  private subscriptions: Subscription[] = [];
  machineId: number;
  machines: Machine[] = [];
  machine: Machine;
  machineForm: FormGroup;
  host: string;



  displayedColumns: string[] = ['position', 'name', 'type', 'manufacturer', 'resolved'];
  dataSource = new MatTableDataSource<Machine>([]);

  @ViewChild(MatSort, { static: true }) sort: MatSort;


  constructor(
    private router: Router,
    private machineService: MachineService,
    private failureService: FailureService,
    private formBuilder: FormBuilder) {
    console.log("In constructor");
  }

  createForm() {
    this.machineForm = this.formBuilder.group({
      name: [''],
      type: [''],
      manufacturer: [''],
      failures: this.formBuilder.array([])
    });
  }

  ngOnInit() {
    this.getMachines();
    this.createForm();
  }

  onSubmit() {
    console.log("submiting");
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

  saveMachine(machine?: Machine): void {
    let name: string;
    let type: string;
    let manufacturer: string;
    if (machine) {
      name = machine.name;
      type = machine.type;
      manufacturer = machine.manufacturer
      console.log(name, type, manufacturer);
    }

    this.subscriptions.push(this.machineService.addMachine(machine).subscribe(
      response => {
        console.log(response);
        console.log("Machine saved to database!");
      }, error => {
        console.log(error);
      }
    ));
    /*       
        let failures: FormArray = new FormArray([]);
    
        this.machineForm = new FormGroup({
          name: new FormControl(name),
          type: new FormControl(type),
          manufacturer: new FormControl(manufacturer),
          failures: failures
        })
        if (!machine) {
          this.addFailure();
        } 
     */
  }

  get failureForms() {
    return this.machineForm.get('failures') as FormArray
  }

  addFailure(failure?: Failure): void {
    console.log("in addFailure")
    let title: string;
    let description: string;
    if (failure) {
      title = failure.title;
      description = failure.description;
    }
    (<FormArray>this.machineForm.controls['failures']).push(
      new FormGroup({
        title: new FormControl(title),
        description: new FormControl(description)
      })
    )
    console.log(title, description);
  }

  deleteFailure(i) {
    this.failureForms.removeAt(i);
  }


  ngOnDestroy() {
    this.subscriptions.forEach(sub => sub.unsubscribe());
  }


}
