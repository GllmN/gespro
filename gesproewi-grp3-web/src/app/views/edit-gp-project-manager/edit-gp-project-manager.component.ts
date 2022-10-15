import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { GpProjectManagerFormService } from 'src/app/forms/gp-project-manager-form.service';
import { GpProjectManager } from 'src/app/models/gp-project-manager';
import { GpProjectManagerService } from 'src/app/services/gp-project-manager.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-edit-gp-project-manager',
  templateUrl: './edit-gp-project-manager.component.html',
  styleUrls: ['./edit-gp-project-manager.component.scss']
})
export class EditGpProjectManagerComponent implements OnInit {
  title: string = 'New ';
  proManForm!: FormGroup;
  projectManager!: GpProjectManager;
  idProMan!: number;

  constructor(
    private gpProManFormService: GpProjectManagerFormService,
    private gpProManService: GpProjectManagerService,
    private route: ActivatedRoute,
    private router: Router,
    private alertService: ToastrService
  ) {
    this.proManForm = this.gpProManFormService.gpProjectManagerForm();
    this.idProMan = this.route.snapshot.params.id;
  }

  get f() {
    return this.proManForm.controls;
  }

  ngOnInit(): void {
    this.populateForm();
    if (this.idProMan){
      this.title = 'Update ';
    }
  }
  
  populateForm() {
    if (this.idProMan) {
      this.gpProManService.getByid(this.idProMan).subscribe((res) => {
        this.projectManager = res;
        this.proManForm.patchValue(this.projectManager);
      });
    }
  }

  save() {
    if (this.idProMan) {
      if (JSON.stringify(this.projectManager) !== JSON.stringify(this.proManForm.value)){
        this.gpProManService
          .update(this.proManForm.value, this.idProMan)
          .subscribe((res)=> {
            this.alertService.success(`Item ${res.fileNumber} was updated`, 'Success');
            this.router.navigate(['/admin/project-managers']);
          },
          (error) => {
            this.alertService.error(`Item ${error.error.message.split(';', 1)}`, `${error.status}`);
          });
      } else {
        this.alertService.warning(`Nothing to update`);
      }
    } else {
        this.gpProManService.create(this.proManForm.value).subscribe((res) => {
          this.alertService.success(`Item ${res.fileNumber} was created`, 'Success');
          this.router.navigate(['/admin/project-managers']);
        },
        (error) => {
          this.alertService.error(`Item ${error.error.message.split(';', 1)}`, `${error.status}`);
        });
    }
  }
}

