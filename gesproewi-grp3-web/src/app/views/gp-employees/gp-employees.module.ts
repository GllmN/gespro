import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {GpEmployeesRoutingModule} from './gp-employees-routing.module';
import {GpEmployeesComponent} from './gp-employees.component';
import {ReactiveFormsModule} from '@angular/forms';


@NgModule({
  declarations: [GpEmployeesComponent],
  imports: [
    CommonModule,
    GpEmployeesRoutingModule,
    ReactiveFormsModule
  ]
})
export class GpEmployeesModule {
}
