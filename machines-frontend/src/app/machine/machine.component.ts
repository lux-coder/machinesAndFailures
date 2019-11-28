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
  makina: Machine;
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
      machine: this.formBuilder.group({
        name: [''],
        type: [''],
        manufacturer: ['']
      }),
      failures: this.formBuilder.array([this.failures])
    });
  }

  ngOnInit() {
    this.getMachines();
    this.createForm();
  }

  onSubmit() {
    console.log("submiting");
  }

  get failures(): FormGroup {
    return this.formBuilder.group({
      failure_title: "",
      failure_description: "",
      failure_priority: "",
      files: this.formBuilder.array([this.files])
    });
  }

  get files(): FormGroup {
    return this.formBuilder.group({
      file_name: ""
    });
  }

  addFailure() {
    (this.machineForm.get("failures") as FormArray).push(this.failures);
  }

  deleteFailure(index) {
    (this.machineForm.get("failures") as FormArray).removeAt(index);
  }

  addFile(failure) {
    failure.get("files").push(this.files);
  }

  deleteFile(failure, index) {
    failure.get("files").removeAt(index);
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


  saveThis(){
    console.log(this.machineForm.value.name);
  }



  saveMachine(machine?: Machine): void {
    console.log(machine);
    console.log(this.machineForm.value);    

    this.machine = this.machineForm.value;

    this.subscriptions.push(this.machineService.addMachine(this.machine).subscribe(
      response => {
        console.log(response);
        console.log("Machine saved to database!");
      }, error => {
        console.log(error);
      }
    ));
  }
   



    /* 

    saveMachine(machine?: Machine): void {
    let name: string;
    let type: string;
    let manufacturer: string;
    let failures: FormArray = new FormArray([]);

    if (machine) {
      name = machine.name;
      type = machine.type;
      manufacturer = machine.manufacturer
      console.log(name, type, manufacturer);

      if(failures){
        
      }


    }


    this.machineForm = new FormGroup({
      failureList: failureList
    })
    this.addFailure();
        
        this.machineForm = new FormGroup({
          name: new FormControl('', Validators.required),
          type: new FormControl(type),
          manufacturer: new FormControl(manufacturer),
          failures: failures
        })
        if (machine) {
          this.addFailure();
        }
     */
    /* 
        this.subscriptions.push(this.machineService.addMachine(machine).subscribe(
          response => {
            console.log(response);
            console.log("Machine saved to database!");
          }, error => {
            console.log(error);
          }
        )); */

  

  get failureForms() {
    return this.machineForm.get('failureList') as FormArray
  }

 /*  addFailure(failure?: Failure): void {
    console.log("in addFailure");
    console.log(failure);
    let title: string;
    let description: string;
    if (failure) {
      title = failure.title;
      description = failure.description;
      console.log(title, description);
    }
    (<FormArray>this.machineForm.controls['failureList']).push(
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

 */
  ngOnDestroy() {
    this.subscriptions.forEach(sub => sub.unsubscribe());
  }


}
