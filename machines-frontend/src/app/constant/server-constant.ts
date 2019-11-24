export class ServerConstant {

    public host: string = 'http://localhost:8080';
    public client: string =  'http://localhost:4200';

    public failureFile: string = `${this.host}/file/failure`;
}
