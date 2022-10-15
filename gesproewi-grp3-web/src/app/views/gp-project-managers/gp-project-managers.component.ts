import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { GpProjectManagerFormService } from 'src/app/forms/gp-project-manager-form.service';
import { GpProjectManager } from 'src/app/models/gp-project-manager';
import { GpProjectManagerService } from 'src/app/services/gp-project-manager.service';
import { GpProject } from 'src/app/models/gp-project';


@Component({
  selector: 'app-gp-project-managers',
  templateUrl: './gp-project-managers.component.html',
  styleUrls: ['./gp-project-managers.component.scss']
})
export class GpProjectManagersComponent implements OnInit {
  projectManager!: GpProjectManager;
  projectManagerList!: GpProjectManager[];
  projectList!: GpProject[];

  constructor(
    private gpProManFormService: GpProjectManagerFormService,
    private gpProjectManagerService: GpProjectManagerService,
    private router: Router,
    private alertService: ToastrService
  ) { 
  }

  ngOnInit(): void {
    this.getAllProjectManagers();
  }

  edit(id: any) {
    this.router.navigate(['/admin/project-managers/', id]);
  }

  delete(id: any) {
    if (confirm(`Do want to delete item ${id}`)) {
      this.gpProjectManagerService.delete(id).subscribe(() => {
          this.getAllProjectManagers();
        },
        (error) => {
          this.alertService.error(`${error.error.message.split(';', 1)}`, `${error.status}`);
        });
    }
  }

  getAllProjectManagers() {
    this.gpProjectManagerService.getAll().subscribe(
      (res) => {
        this.projectManagerList = res;
      },
      (error) => {
      }
    );
  }
  getByIdProjectManagers(id: any) {
    this.gpProjectManagerService.getByid(id).subscribe(
      (res) => {
        this.projectManager = res;
        this.alertService.success(`This project list by this project :${res.lastname}`, 'Success');
        console.log(res.lastname);
      },
      (error) => {
      }
    );
  }
}