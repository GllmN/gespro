import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {GpEmployeesComponent} from './gp-employees.component';

const routes: Routes = [


  {path: '', component: GpEmployeesComponent,},
  {
    path: 'nouveau', loadChildren: () => import('../edit-gp-employee/edit-gp-employee-routing.module').then(
      (m) => m.EditGpEmployeeRoutingModule
    ),
  },
  {
    path: ':id', loadChildren: () => import('../edit-gp-employee/edit-gp-employee-routing.module').then(
      (m) => m.EditGpEmployeeRoutingModule
    ),
  },

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class GpEmployeesRoutingModule {
}
