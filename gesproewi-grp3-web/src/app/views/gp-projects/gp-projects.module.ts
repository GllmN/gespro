import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {GpProjectsRoutingModule} from './gp-projects-routing.module';
import {GpProjectsComponent} from './gp-projects.component';
import {ReactiveFormsModule} from '@angular/forms';


@NgModule({
  declarations: [GpProjectsComponent],
  imports: [
    CommonModule,
    GpProjectsRoutingModule,
    ReactiveFormsModule
  ]
})
export class GpProjectsModule {
}
