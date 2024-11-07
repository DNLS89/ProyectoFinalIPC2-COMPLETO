export class RestConstants {
    public readonly API_URL = 'http://localhost:8080/Proyecto2IPC2/api/v1/';
    public readonly nombreUsuario: any;

    public getApiURL() : string {
        return this.API_URL;
    }
}