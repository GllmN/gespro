import { Component, OnInit } from '@angular/core';
import { GpProject } from 'src/app/models/gp-project';
import {Router} from '@angular/router';
import {GpProjectFormService} from 'src/app/forms/gp-project-form.service';
import {GpProjectService} from 'src/app/services/gp-projects.service';
import {ToastrService} from "ngx-toastr";
@Component({
  selector: 'app-gp-projects',
  templateUrl: './gp-projects.component.html',
  styleUrls: ['./gp-projects.component.scss']
})
export class GpProjectsComponent implements OnInit {
  project!: GpProject;
  projectList!: GpProject[];

  constructor(
    private gpProFormService: GpProjectFormService,
    private gpProjectService: GpProjectService,
    private router: Router,
    private alertService: ToastrService
  ) { 

  }

  ngOnInit(): void {
    this.getAllProjects();
  }

  edit(id: any) {
    this.router.navigate(['/admin/projects/', id]);
  }
  delete(id: any) {
    if (confirm(`Do want to delete item ${id}`)) {
      this.gpProjectService.delete(id).subscribe(() => {
          this.getAllProjects();
        },
        (error: { error: { message: string; }; status: any; }) => {
          this.alertService.error(`${error.error.message.split(';', 1)}`, `${error.status}`);
        });
    }
  }
  
  getAllProjects() {
    this.gpProjectService.getAll().subscribe(
      (res) => {
        this.projectList = res;
      },
      (error) => {
      }
    );
  }
}
