import {Injectable} from '@angular/core';
import {CrudService} from "./crud.service";
import {GpProject} from '../models/gp-project';
import {HttpClient} from '@angular/common/http';
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class GpProjectService extends CrudService<GpProject> {

  constructor(httpClient: HttpClient) {
    const url: string = environment.baseUrl;
    super(httpClient, `${url}/projects`);
   }
}