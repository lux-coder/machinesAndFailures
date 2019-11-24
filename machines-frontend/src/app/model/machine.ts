import { Failure } from './failure';

export class Machine{
    id: number;
    name: string;
    type: string;
    manufacturer: string;
    failureList: Failure[];
}