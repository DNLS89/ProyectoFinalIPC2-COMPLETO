package com.mycompany.proyecto2ipc2.resources;

import Principales.MotorPrograma;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("gestionRevista")
public class GestionRevistaResource {
    
    
    //PUBLICAR REVISTA
    @POST
    @Path("/publicar/{nombreUsuario}/{archivoRevista}/{descripcion}/{categoria}/{etiquetas}/{fechaPublicacion}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response publicar(@PathParam("nombreUsuario") String nombreUsuario, @PathParam("archivoRevista") String archivoRevista, 
            @PathParam("descripcion") String descripcion, @PathParam("categoria") String categoria, @PathParam("etiquetas") String etiquetas, 
            @PathParam("fechaPublicacion") String fechaPublicacion) {
        
        //System.out.println("Datos recibidos: " + nombreUsuario + " " + archivoRevista + " " + descripcion + " " + categoria + " " + etiquetas + " " + fechaPublicacion);
        
        MotorPrograma motorPrograma = new MotorPrograma(nombreUsuario);
        motorPrograma.publicar(archivoRevista, descripcion, categoria, etiquetas, fechaPublicacion);
        
        //Debe redigir a la misma pantalla de la revista para que se pueda publicar una nueva revista
        motorPrograma.closeConnection();
        return Response.ok().build();
        
    }
    
    
    //PARA SUSCRIBIR
    @POST
    @Path("/suscribir/{nombreUsuario}/{numeroRevista}/{fechaSuscripcion}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response suscribir(@PathParam("nombreUsuario") String nombreUsuario, 
            @PathParam("numeroRevista") String numeroRevistaString, @PathParam("fechaSuscripcion") String fechaSuscripcion) {
        
        System.out.println("SUCRIPCION DATOS INGRESADO: " + nombreUsuario + " " + numeroRevistaString + " " + fechaSuscripcion);
        
        MotorPrograma motorPrograma = new MotorPrograma(nombreUsuario);
        
        int numeroRevista = Integer.parseInt(numeroRevistaString);
        //String fechaDeSuscripcion = fechaSuscripcion;
        
        if (motorPrograma.suscribir(numeroRevista, fechaSuscripcion)) {
            motorPrograma.closeConnection();
            return Response.ok().build();
        } else {
            motorPrograma.closeConnection();
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    
    //COMENTAR
    @POST
    @Path("/comentar/{nombreUsuario}/{numeroRevista}/{comentario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response comentar(@PathParam("nombreUsuario") String nombreUsuario, @PathParam("comentario") String comentario, 
            @PathParam("numeroRevista") String numeroRevistaString) {
        
        MotorPrograma motorPrograma = new MotorPrograma(nombreUsuario);
        
        int numeroRevista = Integer.parseInt(numeroRevistaString);
        
        
        if (motorPrograma.comentar(numeroRevista, comentario)) {
            motorPrograma.closeConnection();
            return Response.ok().build();
        }
        
        motorPrograma.closeConnection();
        
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    
    
    //DAR ME GUSTA
    @POST
    @Path("/darMeGusta/{nombreUsuario}/{numeroRevista}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response darMeGusta(@PathParam("nombreUsuario") String nombreUsuario, 
            @PathParam("numeroRevista") String numeroRevistaString) {
        
        MotorPrograma motorPrograma = new MotorPrograma(nombreUsuario);
        
        int numeroRevista = Integer.parseInt(numeroRevistaString);
        
        motorPrograma.darMeGusta(numeroRevista);
        
        motorPrograma.closeConnection();
        
        return Response.ok().build();
        
    }
    
    //CAMBIAR EL ESTADO DE LAS SUSCRIPCIONES, COMENTARIOS Y ME GUSTAS
//    @POST
//    @Path("/cambiarEstadoSuscripcion/{nombreUsuario}/{numeroRevista}/{estadoActual}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response cambiarEstadoSuscripcion(@PathParam("nombreUsuario") String nombreUsuario, 
//            @PathParam("numeroRevista") String numeroRevistaString, @PathParam("estadoActual") boolean estadoActual) {
//        
//        MotorPrograma motorPrograma = new MotorPrograma(nombreUsuario);
//        
//        int numeroRevista = Integer.parseInt(numeroRevistaString);
//        
//        int estadoPorCambiar;
//        if (estadoActual == true) {
//            estadoPorCambiar = 0;
//        } else {
//            estadoPorCambiar = 1;
//        }
//        
//        if (motorPrograma.cambiarEstadoSuscripcion(numeroRevista, estadoPorCambiar)) {
//            motorPrograma.close();
//            return Response.ok().build();
//        }
//        
//        motorPrograma.close();
//        return Response.status(Response.Status.NOT_FOUND).build();
//    }
    
    @POST
    @Path("/cambiarEstado/{procesoPorCambiar}/{nombreUsuario}/{numeroRevista}/{estadoActual}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response cambiarEstadoComentarios(@PathParam("procesoPorCambiar") String proceso, @PathParam("nombreUsuario") String nombreUsuario, 
            @PathParam("numeroRevista") String numeroRevistaString, @PathParam("estadoActual") boolean estadoActual) {
        
        MotorPrograma motorPrograma = new MotorPrograma(nombreUsuario);
        
        int numeroRevista = Integer.parseInt(numeroRevistaString);
        String procesoPorCambiar = proceso;
        
        
        int estadoPorCambiar;
        if (estadoActual == true) {
            estadoPorCambiar = 0;
        } else {
            estadoPorCambiar = 1;
        }
        
        if (motorPrograma.cambiarEstadoGestion(numeroRevista, estadoPorCambiar, procesoPorCambiar)) {
            motorPrograma.closeConnection();
            return Response.ok().build();
        }
        
        motorPrograma.closeConnection();
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
