import { Injectable } from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class GpProjectFormService {

  constructor(private fb: FormBuilder) { }

  gpProjectForm() {
    return this.fb.group({
    id:['',],
    amount: ['',],
    creationDate:[new Date()],
    endDate:[new Date()],
    startDate: [new Date()],
    updateDate: [new Date()],
    description: ['',],
    name: ['',],
    code: ['', Validators.required],
    projectmanager: [],
    organization: [],

    })
}
}