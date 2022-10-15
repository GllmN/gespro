import {Injectable} from '@angular/core';
import {FormBuilder} from '@angular/forms';

@Injectable({
  providedIn: 'root',
})
export class GpPhasesFormService {
  constructor(private fb: FormBuilder) {
  }

  getPhasesForm() {
    return this.fb.group({
      id: '',
      isEnded: [''],
      phaseCode: [''],
      startDate: [''],
      status: [''],
      updateDate: [new Date()],
      amount: [''],
      creationDate: [new Date()],
      description: [''],
      endDate: [''],
      gpBills: [],
      gpProject: [''],
    });
  }
}
