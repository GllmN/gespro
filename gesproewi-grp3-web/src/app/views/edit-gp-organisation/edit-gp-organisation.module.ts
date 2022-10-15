import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {EditGpOrganisationRoutingModule} from './edit-gp-organisation-routing.module';
import {ReactiveFormsModule} from '@angular/forms';
import {EditGpOrganisationComponent} from './edit-gp-organisation.component';


@NgModule({
  declarations: [EditGpOrganisationComponent],
  imports: [
    CommonModule,
    EditGpOrganisationRoutingModule,
    ReactiveFormsModule
  ]
})
export class EditGpOrganisationModule {
}
