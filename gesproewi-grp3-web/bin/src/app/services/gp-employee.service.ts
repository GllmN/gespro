import {Injectable} from '@angular/core';
import {CrudService} from "./crud.service";
import {GpEmployee} from "../models/gp-employee";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class GpEmployeeService extends CrudService<GpEmployee> {

  constructor(httpClient: HttpClient) {
    const url: string = environment.baseUrl;
    super(httpClient, `${url}/employee`);
  }
}
