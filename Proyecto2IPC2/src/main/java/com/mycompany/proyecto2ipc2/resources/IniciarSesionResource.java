
package com.mycompany.proyecto2ipc2.resources;

import Principales.MenuInicio;
import Principales.MotorPrograma;
import Principales.SQL;
import Usuarios.Usuario;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("iniciarSesion")
public class IniciarSesionResource {
    
    @GET
    @Path("/{nombreUsuario}/{contraseña}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response inicioSesion(@PathParam("nombreUsuario") String nombreUsuario, @PathParam("contraseña") String contraseña) {
        
        MenuInicio menuInicio = new MenuInicio();
        
        System.out.println("NOmbre Usuario: " + nombreUsuario);
        if (menuInicio.verificarCredenciales(nombreUsuario, contraseña)) {
            
            //menuInicio.ingresarAlSistema(nombreUsuario);
            
            MotorPrograma motorPrograma = new MotorPrograma(nombreUsuario);
            
            //MotorPrograma motorPrograma = MotorPrograma.getInstance(nombreUsuario);
            Usuario usuario = motorPrograma.extraerDatosUsuario();
            
            //Despues en JSP y SERVLET agregaba el usuario a la sesión para saber qué contenido mostrar de la página inicial
            //Redirige al menú principal
            menuInicio.close();
            
            return Response.ok(usuario).build();
            
        } else {
            menuInicio.close();
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}
