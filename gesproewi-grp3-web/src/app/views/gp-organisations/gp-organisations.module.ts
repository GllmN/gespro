import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {GpOrganisationsRoutingModule} from './gp-organisations-routing.module';
import {ReactiveFormsModule} from '@angular/forms';
import {GpOrganisationsComponent} from './gp-organisations.component';


@NgModule({
  declarations: [GpOrganisationsComponent],
  imports: [
    CommonModule,
    GpOrganisationsRoutingModule,
    ReactiveFormsModule
  ]
})
export class GpOrganisationsModule {
}
