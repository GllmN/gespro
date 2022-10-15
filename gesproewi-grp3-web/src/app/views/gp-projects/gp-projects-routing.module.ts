import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {GpProjectsComponent} from './gp-projects.component';

const routes: Routes = [


  {path: '', component: GpProjectsComponent,},
  {
    path: 'nouveau', loadChildren: () => import('../edit-gp-project/edit-gp-project-routing.module').then(
      (m) => m.EditGpProjectRoutingModule
    ),
  },
  {
    path: ':id', loadChildren: () => import('../edit-gp-project/edit-gp-project-routing.module').then(
      (m) => m.EditGpProjectRoutingModule
    ),
  },

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class GpProjectsRoutingModule {
}
