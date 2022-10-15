import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {EditGpAddressComponent} from './edit-gp-address.component';

const routes: Routes = [
  {path: '', component: EditGpAddressComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EditGpAddressRoutingModule {
}
