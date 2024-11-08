package com.mycompany.proyecto2ipc2.resources;

import Principales.MotorPrograma;
import Principales.Revista;
import Usuarios.Usuario;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;


@Path("explorarRevistas")
public class ObtenerRevistasResource {
    
    //OBTENER TODAS TODAS LAS REVISTAS AL EXPLORAR REVISTAS
    @GET
    @Path("/{nombreUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRevistas(@PathParam("nombreUsuario") String nombreUsuario) {
        
        MotorPrograma motorPrograma = new MotorPrograma(nombreUsuario);
        
        if (motorPrograma.hayRevistasCreadas()) {
            
            Revista[] revistas = motorPrograma.getRevistas();
            motorPrograma.closeConnection();
            return Response.ok(revistas).build();

        } else {
            motorPrograma.closeConnection();
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    //OBTENER TODAS TODAS LAS REVISTAS SIN PRECIO ASIGNADO AL EXPLORAR REVISTAS
    @GET
    @Path("/sinPrecio/{nombreUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRevistasSinPrecio(@PathParam("nombreUsuario") String nombreUsuario) {
        
        MotorPrograma motorPrograma = new MotorPrograma(nombreUsuario);
        
        if (motorPrograma.hayRevistasCreadas2()) {
            
            ArrayList<Revista> revistasSinPrecio = motorPrograma.obtenerRevistas2();
           
            motorPrograma.closeConnection();
            return Response.ok(revistasSinPrecio).build();

        } else {
            motorPrograma.closeConnection();
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    
    //OBTENER REVISTAS PUBLICADAS
    @GET
    @Path("/revistasPublicadas/{nombreAutor}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRevistasPublicadas(@PathParam("nombreAutor") String nombreAutor) {
        
        //System.out.println("Recibiendo revistas publicas");
        MotorPrograma motorPrograma = new MotorPrograma(nombreAutor);
        //MotorPrograma motorPrograma = MotorPrograma.getInstance(nombreAutor);
        ArrayList<Revista> revistasPublicadas = motorPrograma.obtenerRevistasPublicadas(nombreAutor);
        
        if (revistasPublicadas != null) {
            //System.out.println("Hay revistas");
            motorPrograma.closeConnection();
            return Response.ok(revistasPublicadas).build();
        }
        
        motorPrograma.closeConnection();
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    
    //OBTENER REVISTAS SUSCRITAS
    @GET
    @Path("/revistasSuscritas/{nombreUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRevistasSuscritas(@PathParam("nombreUsuario") String nombreUsuario) {
        MotorPrograma motorPrograma = new MotorPrograma(nombreUsuario);
        motorPrograma.obtenerRevistasSuscritas();
        if (motorPrograma.getRevistasSuscritas() != null) {
            
            motorPrograma.closeConnection();
            return Response.ok(motorPrograma.getRevistasSuscritas()).build();
            
        }
        motorPrograma.closeConnection();
        return Response.status(Response.Status.NOT_FOUND).build();
        
        //Regresa a revistas Suscritas
    }
    
    //REVISTA INDIVIDUAL AL VER UNA REVISTA ENTERA (EN REVISTAS SUSCRITAS)
    @GET
    @Path("/revistaIndividual/{nombreUsuario}/{numeroRevista}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRevistaIndividual(@PathParam("nombreUsuario") String nombreUsuario, @PathParam("numeroRevista") int numeroRevista) {
        MotorPrograma motorPrograma = new MotorPrograma(nombreUsuario);
        
        Revista revistaIndividual = motorPrograma.obtenerRevistaIndividual(numeroRevista);
        
        if (revistaIndividual != null) {
            motorPrograma.closeConnection();
            return Response.ok(revistaIndividual).build();
            
        }
        motorPrograma.closeConnection();
        return Response.status(Response.Status.NOT_FOUND).build();
        
        //Regresa a revistas Suscritas
    }
}
