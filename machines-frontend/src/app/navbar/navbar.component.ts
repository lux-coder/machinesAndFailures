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

export class NavbarComponent implements OnInit {
  private subscriptions: Subscription[] = [];
  
  host: string;
  showNavbar: boolean;
  clientHost: string;

  constructor(
    private router: Router,
  ) { }

  ngOnInit() {    
    this.showNavbar = true;
  }

}
