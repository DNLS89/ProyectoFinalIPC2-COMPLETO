package com.mycompany.proyecto2ipc2.resources;

import Principales.MotorPrograma;
import Principales.Tienda;
import Usuarios.Usuario;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.math.BigDecimal;



@Path("tienda")
public class TiendaResource {
    
    
    
    @GET
    @Path("/{nombreUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCreditos(@PathParam("nombreUsuario") String nombreUsuario) {
        MotorPrograma motorPrograma = new MotorPrograma(nombreUsuario);
        motorPrograma.comprar();
        Tienda tienda = motorPrograma.getTienda();
        tienda.extraerCreditos();
        
        Usuario usuario = motorPrograma.getUsuario();
        
        //Redigirige a la tienda
        motorPrograma.closeConnection();
        return Response.ok(usuario).build();
    }
    

    @POST
    @Path("/{nombreUsuario}/{creditos}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response comprarCreditos(@PathParam("nombreUsuario") String nombreUsuario, @PathParam("creditos") String creditos,
            @PathParam("rol") String rol, @PathParam("foto") String foto, @PathParam("hobbies") String hobbies, @PathParam("descripcion") String descripcion,
            @PathParam("gustos") String gustos) {

        MotorPrograma motorPrograma = new MotorPrograma(nombreUsuario);

                
        BigDecimal creditosPorComprar = new BigDecimal(creditos);
        
        if (creditosPorComprar.compareTo(BigDecimal.ZERO) == 0) {
            motorPrograma.closeConnection();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        
        
        
        motorPrograma.comprar();
        Tienda tienda = motorPrograma.getTienda();
        tienda.extraerCreditos();
        
        
        if (tienda.recargar(creditosPorComprar)) {
            motorPrograma.closeConnection();
            return Response.ok().build();
        } else {
            motorPrograma.closeConnection();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }        
    }
}
