import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {environment} from 'src/environments/environment';
import {GpAddress} from '../models/gp-address';
import {CrudService} from './crud.service';

@Injectable({
  providedIn: 'root'
})
export class GpAddressService extends CrudService<GpAddress> {

  constructor(httpClient: HttpClient) {
    const url: string = environment.baseUrl;
    super(httpClient, `${url}/address`);
  }
}
