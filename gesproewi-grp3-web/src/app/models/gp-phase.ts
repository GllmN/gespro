import {GpBill} from './gp-bill';
import {GpProject} from './gp-project';

export interface GpPhase {
  id?: number;
  isEnded?: boolean;
  phaseCode?: string;
  startDate?: Date;
  status?: boolean;
  updateDate?: Date;
  amount?: number;
  creationDate?: Date;
  description?: string;
  endDate?: Date;
  gpBills: GpBill[];
  gpProject: GpProject;
}
