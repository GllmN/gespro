import {GpAddress} from "./gp-address";

export interface GpEmployee {
  id?: number,
  creationDate?: Date,
  email?: string,
  fileNumber?: string,
  firstname?: string,
  lastname?: string,
  login?: string,
  password?: string,
  phoneNumber?: string,
  updateDate?: Date,
  gpAddresses?: GpAddress[]
}
