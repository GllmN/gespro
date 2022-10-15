import {GpAddress} from "./gp-address";
import {GpProject} from "./gp-project";

export interface GpOrganization {
  id?: number;
  adrWeb?: string;
  contactEmail?: string;
  contactName?: string;
  name?: string;
  orgCode?: string;
  phoneNumber?: number;
  gpAddresses?: GpAddress[];
  gpProjects?: GpProject[];

}
