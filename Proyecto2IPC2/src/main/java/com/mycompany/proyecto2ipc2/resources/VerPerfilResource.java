
package com.mycompany.proyecto2ipc2.resources;

import Principales.MotorPrograma;
import Usuarios.Usuario;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("perfilAutor")

public class VerPerfilResource {
    
    
    @GET
    @Path("/{nombreUsuario}/{nombreAutor}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPerfilAutor(@PathParam("nombreAutor") String nombreUsuario, @PathParam("nombreAutor") String nombreAutor) {
        
        MotorPrograma motorPrograma = new MotorPrograma(nombreUsuario);
        //MotorPrograma motorPrograma = MotorPrograma.getInstance(nombreUsuario);
        
        Usuario autor = motorPrograma.obtenerAutor(nombreAutor);
        motorPrograma.closeConnection();
        return Response.ok(autor).build();
        
    }
}
