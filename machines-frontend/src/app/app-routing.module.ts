import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MachineComponent } from './machine/machine.component';
import { HomeComponent } from './home/home.component';
import { MachineDetailsComponent } from './machine-details/machine-details.component';
import { FailureComponent } from './failure/failure.component';


const routes: Routes = [
  { path: 'machine', component: MachineComponent },
  { path: 'machine/machineId', component: MachineDetailsComponent },
  { path: 'failure', component: FailureComponent },
  { path: 'home', component: HomeComponent },
  { path: '', redirectTo: '/home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }