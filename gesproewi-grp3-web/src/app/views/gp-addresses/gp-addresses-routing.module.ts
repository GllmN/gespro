import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {GpAddressesComponent} from './gp-addresses.component';

const routes: Routes = [
  {path: '', component: GpAddressesComponent},
  {
    path: 'nouveau', loadChildren: () => import('../edit-gp-address/edit-gp-address.module').then(
      (m) => m.EditGpAddressModule
    ),
  },
  {
    path: ':id', loadChildren: () => import('../edit-gp-address/edit-gp-address.module').then(
      (m) => m.EditGpAddressModule
    ),
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class GpAddressesRoutingModule {
}
