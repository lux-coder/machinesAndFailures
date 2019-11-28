import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { HttpEventType } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { FailureService } from '../service/failure.service';
import { MachineService } from '../service/machine.service';
import { Failure } from '../model/failure';
import { Machine } from '../model/machine';
import { FormGroup, FormBuilder, FormArray } from '@angular/forms';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})

export class NavbarComponent implements OnInit, OnDestroy {
  private subscriptions: Subscription[] = [];

  machine: Machine;
  host: string;
  showNavbar: boolean;
  clientHost: string;
  machineForm: FormGroup;

  constructor(
    private router: Router,
    private machineService: MachineService,
    private failureService: FailureService,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit() {
    this.host = this.failureService.host;    
    this.showNavbar = true;
    this.machineForm = this.formBuilder.group({
      name: '',
      type: '',
      manufacturer: '',
      failures: this.formBuilder.array([])
    })

  }

  get failureForms(){
    return this.machineForm.get('failures') as FormArray
  }

  addFailure(){
    const failure = this.formBuilder.group({
      title: [],
      description: [],
      status: [],
      time: []
    })
    this.failureForms.push(failure);
  }

  deleteFailure(i){
    this.failureForms.removeAt(i);
  }

  ngOnDestroy() {
    this.subscriptions.forEach(sub => sub.unsubscribe);
  }

}
