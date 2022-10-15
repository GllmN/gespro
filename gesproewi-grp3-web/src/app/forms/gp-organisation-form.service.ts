import {Injectable} from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class GpOrganisationFormService {

  constructor(private fb: FormBuilder) {
  }

  getOrganisationForm() {
    return this.fb.group({
      id: ['',],
      adrWeb: ['', [Validators.compose([Validators.required])]],
      contactEmail: ['', [Validators.compose([Validators.required])]],
      contactName: ['', [Validators.required]],
      name: ['', [Validators.required]],
      orgCode: ['', [Validators.compose([Validators.max(7), Validators.required])]],
      phoneNumber: ['', [Validators.compose([Validators.required])]],
    })
  }
}
