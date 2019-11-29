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

  uploader: FileUploader = new FileUploader({});

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
        name: '',
        type: '',
        manufacturer: ''
      }),
      failures: this.formBuilder.array([this.failures])
    });
  }

  ngOnInit() {
    this.createForm();
    this.getMachines();   
  }

  onSubmit() {
    console.log("submiting");
  }

  get failures(): FormGroup {
    return this.formBuilder.group({
      title: "",
      description: "",
      priority: "",
      status: "",      
      files: this.formBuilder.array([this.files])
    });
  }


  get files(): FormGroup {
    return this.formBuilder.group({
      files: [this.upload()]
    });
  }

  addFailure() {
    console.log("Clicked");
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

  upload() {
    for (let i = 0; i < this.uploader.queue.length; i++) {
      let fileItem = this.uploader.queue[i]._file;
      if (fileItem.size > 5000000) {
        alert("Each File should be less than 5 MB of size.");
        return;
      }
    }
    for (let j = 0; j < this.uploader.queue.length; j++) {
      let data = new FormData();
      let fileItem = this.uploader.queue[j]._file;
      console.log(fileItem.name);
      data.append('file', fileItem);
      data.append('fileSeq', 'seq' + j);
      data.append('dataType', this.files.controls.type.value);
    }
    this.uploader.clearQueue();
  }

  ngOnDestroy() {
    this.subscriptions.forEach(sub => sub.unsubscribe());
  }


}
