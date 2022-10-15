import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {GpEmployeeFormService} from 'src/app/forms/gp-employee-form.service';
import {GpEmployee} from 'src/app/models/gp-employee';
import {GpEmployeeService} from 'src/app/services/gp-employee.service';
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-gp-employees',
  templateUrl: './gp-employees.component.html',
  styleUrls: ['./gp-employees.component.scss'],
})
export class GpEmployeesComponent implements OnInit {
  employee!: GpEmployee;
  employeeList!: GpEmployee[];

  constructor(
    private gpEmpFormService: GpEmployeeFormService,
    private gpEmployeeService: GpEmployeeService,
    private router: Router,
    private alertService: ToastrService
  ) {
  }

  ngOnInit(): void {
    this.getAllEmployees();
  }

  edit(id: any) {
    this.router.navigate(['/admin/employees/', id]);
  }

  delete(id: any) {
    if (confirm(`Do want to delete item ${id}`)) {
      this.gpEmployeeService.delete(id).subscribe(() => {
          this.getAllEmployees();
        },
        (error) => {
          this.alertService.error(`${error.error.message.split(';', 1)}`, `${error.status}`);
        });
    }
  }

  getAllEmployees() {
    this.gpEmployeeService.getAll().subscribe(
      (res) => {
        this.employeeList = res;
      },
      (error) => {
      }
    );
  }
}
