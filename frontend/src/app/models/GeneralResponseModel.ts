import { ClientModel } from './ClientModel';

export class GeneralResponseModel<T> {
  data?: T;
  success?: boolean;
  message?: string;
}
