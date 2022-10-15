import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {GpAddressesRoutingModule} from './gp-addresses-routing.module';
import {GpAddressesComponent} from './gp-addresses.component';


@NgModule({
  declarations: [GpAddressesComponent],
  imports: [
    CommonModule,
    GpAddressesRoutingModule
  ]
})
export class GpAddressesModule {
}
