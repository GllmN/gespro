import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {environment} from 'src/environments/environment';
import {GpOrganization} from '../models/gp-organization';
import {CrudService} from './crud.service';

@Injectable({
  providedIn: 'root'
})
export class GpOrganisationService extends CrudService<GpOrganization> {
  constructor(httpClient: HttpClient) {
    const url: string = environment.baseUrl;
    super(httpClient, `${url}/organization`);
  }
}
