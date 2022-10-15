import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {EditGpEmployeeRoutingModule} from './edit-gp-employee-routing.module';
import {ReactiveFormsModule} from '@angular/forms';
import {EditGpEmployeeComponent} from './edit-gp-employee.component';


@NgModule({
  declarations: [
    EditGpEmployeeComponent
  ],
  imports: [
    CommonModule,
    EditGpEmployeeRoutingModule,
    ReactiveFormsModule
  ]
})
export class EditGpEmployeeModule {
}
