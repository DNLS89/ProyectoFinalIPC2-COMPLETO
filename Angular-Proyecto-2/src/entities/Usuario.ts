
export interface Usuario {

    nombreUsuario: string,
    contraseña: string,
    cartera: any;
    foto: string,
    hobbies: string,
    descripcion: string,
    gustos: string,
    rol: string,
    admin: boolean;
    autor: boolean;
    usuario: boolean;
    rolAdminOrAutor: boolean;

}