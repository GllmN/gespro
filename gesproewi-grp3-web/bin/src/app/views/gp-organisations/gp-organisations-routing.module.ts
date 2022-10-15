import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {GpOrganisationsComponent} from './gp-organisations.component';

const routes: Routes = [

  {path: '', component: GpOrganisationsComponent,},
  {
    path: 'nouveau', loadChildren: () => import('../edit-gp-organisation/edit-gp-organisation-routing.module').then(
      (m) => m.EditGpOrganisationRoutingModule
    ),
  },
  {
    path: ':id', loadChildren: () => import('../edit-gp-organisation/edit-gp-organisation-routing.module').then(
      (m) => m.EditGpOrganisationRoutingModule
    ),
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class GpOrganisationsRoutingModule {
}
