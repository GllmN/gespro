import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {EditGpOrganisationComponent} from './edit-gp-organisation.component';

const routes: Routes = [
  {path: '', component: EditGpOrganisationComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EditGpOrganisationRoutingModule {
}
