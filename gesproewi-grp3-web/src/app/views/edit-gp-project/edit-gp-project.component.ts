import {Component, OnInit} from '@angular/core';
import {FormGroup} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {GpProjectFormService} from 'src/app/forms/gp-project-form.service';
import {GpProject} from 'src/app/models/gp-project';
import { GpOrganization } from 'src/app/models/gp-organization';
import { GpProjectManager } from 'src/app/models/gp-project-manager';
import { GpOrganisationService } from 'src/app/services/gp-organisation.service';
import { GpProjectManagerService } from 'src/app/services/gp-project-manager.service';
import {GpProjectService} from 'src/app/services/gp-projects.service';
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-edit-gp-project',
  templateUrl: './edit-gp-project.component.html',
  styleUrls: ['./edit-gp-project.component.scss'],
})
export class EditGpProjectComponent implements OnInit {
  title: string = 'New ';
  projectForm!: FormGroup;
  project!: GpProject;
  idPro!: number;
  gpOrganization: GpOrganization | undefined;
  gpProjectManager: GpProjectManager | undefined;
  listOrganisations: GpOrganization[]=[];
  listProjectManagers: GpProjectManager[]=[];

  constructor(
    private gpProFormService: GpProjectFormService,
    private gpProService: GpProjectService,
    private gpOrgService: GpOrganisationService,
    private gpProManService: GpProjectManagerService,
    private route: ActivatedRoute,
    private router: Router,
    private alertService: ToastrService
  ) {
    this.projectForm = this.gpProFormService.gpProjectForm();
    this.idPro = this.route.snapshot.params.id;
  }

  get f() {
    return this.projectForm.controls;
  }

  ngOnInit(): void {
    this.populateForm();
    this.getAllOrganizations();
    this.getAllProjectManagers();
    if (this.idPro) {
      this.title = 'Update ';
    }
    this.ngOnChanges();
  }

  ngOnChanges(): void {
    if (this.idPro) {
      this.gpProService.getByid(this.idPro).subscribe((res) => {
        this.project = res;
        this.projectForm.patchValue(this.project);
        this.gpOrganization = this.project.organization;
        this.gpProjectManager=this.project.projectmanager;
        
      });
    }
  }

  populateForm() {
    if (this.idPro) {
      this.gpProService.getByid(this.idPro).subscribe((res) => {
        this.project = res;
        this.projectForm.patchValue(this.project);
      });
    }
  }

  getAllOrganizations() {
    this.gpOrgService.getAll().subscribe((res) => {
      this.listOrganisations = res;
    });
  }

  getAllProjectManagers() {
    this.gpProManService.getAll().subscribe((res) => {
      this.listProjectManagers = res;
    });
  }

  save() {
    if (this.idPro) {
      if (JSON.stringify(this.project) !== JSON.stringify(this.projectForm.value)) {
        this.gpProService
          .update(this.projectForm.value, this.idPro)
          .subscribe((res) => {
              this.alertService.success(`Item ${res.code} was updated`, 'Success');
              this.router.navigate(['/admin/projects']);
            },
            (error) => {
              this.alertService.error(`Item ${error.error.message.split(';', 1)}`, `${error.status}`);
            });
      } else {
        this.alertService.warning(`Nothing to update`);
      }
    } else {
      this.gpProService.create(this.projectForm.value).subscribe((res) => {
          this.alertService.success(`Item ${res.code} was created`, 'Success');
          this.router.navigate(['/admin/projects']);
        },
        (error) => {
          this.alertService.error(`Item ${error.error.message.split(';', 1)}`, `${error.status}`);
        });
    }
  }
}
