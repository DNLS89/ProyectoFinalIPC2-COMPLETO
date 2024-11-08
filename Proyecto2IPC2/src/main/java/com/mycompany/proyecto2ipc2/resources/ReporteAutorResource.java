package com.mycompany.proyecto2ipc2.resources;

import Anuncios.Anuncio;
import Principales.GestorReportes;
import Principales.MotorPrograma;
import Principales.Revista;
import Principales.Tienda;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;


@Path("reportesAutor")
public class ReporteAutorResource {
    
    
    //OBTENER REVISTAS CON COMENTARIOS
    @GET
    @Path("/extraerComentarios/{nombreUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerRevistasConComentarios(@PathParam("nombreUsuario") String nombreUsuario) {
        
        
        MotorPrograma motorPrograma = new MotorPrograma(nombreUsuario);
        GestorReportes gestorReportes = motorPrograma.getGestorReportes();
        
        
        ArrayList<Revista> revistasConComentarios = gestorReportes.extraeRevistasConComentarios(nombreUsuario);
        
        if (revistasConComentarios != null) {
            //System.out.println("Hay revistas");
            motorPrograma.closeConnection();
            return Response.ok(revistasConComentarios).build();
        }
        
        motorPrograma.closeConnection();
        return Response.status(Response.Status.NOT_FOUND).build();
        
    }
    
    //OBTENER SUSCRIPCIONES A REVISTAS
    @GET
    @Path("/extraerSuscripciones/{nombreUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerSuscripciones(@PathParam("nombreUsuario") String nombreUsuario) {
        
        MotorPrograma motorPrograma = new MotorPrograma(nombreUsuario);
        GestorReportes gestorReportes = motorPrograma.getGestorReportes();
        
        
        ArrayList<Revista> suscripcionesArray = gestorReportes.extraerSuscripciones(nombreUsuario);
        
        if (suscripcionesArray != null) {
            //System.out.println("Hay revistas");
            motorPrograma.closeConnection();
            return Response.ok(suscripcionesArray).build();
        }
        
        motorPrograma.closeConnection();
        return Response.status(Response.Status.NOT_FOUND).build();
        
    }
    
    //OBTENER REVISTAS CON ME GUSTAS
    @GET
    @Path("/extraerMeGustas/{nombreUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerRevistasConMeGustas(@PathParam("nombreUsuario") String nombreUsuario) {
        
        
        MotorPrograma motorPrograma = new MotorPrograma(nombreUsuario);
        GestorReportes gestorReportes = motorPrograma.getGestorReportes();
        
        
        ArrayList<Revista> revistasConMeGustas = gestorReportes.extraerMeGustas(nombreUsuario);
        
        if (revistasConMeGustas != null) {
            //System.out.println("Hay revistas");
            motorPrograma.closeConnection();
            return Response.ok(revistasConMeGustas).build();
        }
        
        motorPrograma.closeConnection();
        return Response.status(Response.Status.NOT_FOUND).build();
        
    }
    
    //OBTENER Recurrencia REVISTAS CON ME GUSTAS
    @GET
    @Path("/extraerMeGustas/recurrencias/{nombreUsuario}/{fechaInicio}/{fechaFin}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerRecurrenciasRevistasConMeGustas(@PathParam("nombreUsuario") String nombreUsuario, @PathParam("fechaInicio") String fechaInicio,
            @PathParam("fechaFin") String fechaFin) {
        
        
        
        
        MotorPrograma motorPrograma = new MotorPrograma(nombreUsuario);
        GestorReportes gestorReportes = motorPrograma.getGestorReportes();
        
        
        ArrayList<Revista> revistasConMeGustas = gestorReportes.extraerRecurrenciasMeGustas(nombreUsuario, fechaInicio, fechaFin, motorPrograma);
        
        if (revistasConMeGustas != null) {
            //System.out.println("Hay revistas");
            motorPrograma.closeConnection();
            return Response.ok(revistasConMeGustas).build();
        }
        
        motorPrograma.closeConnection();
        return Response.status(Response.Status.NOT_FOUND).build();
        
    }
    
    @GET
    @Path("/extraerPagos/{nombreUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerRevistasConPagos(@PathParam("nombreUsuario") String nombreUsuario) {
        
        
        MotorPrograma motorPrograma = new MotorPrograma(nombreUsuario);
        GestorReportes gestorReportes = motorPrograma.getGestorReportes();
        
        
        ArrayList<Revista> revistasConPagos = gestorReportes.extraerPagosReporteAutor(nombreUsuario);
        
        if (revistasConPagos != null) {
            //System.out.println("Hay revistas");
            motorPrograma.closeConnection();
            return Response.ok(revistasConPagos).build();
        }
        
        motorPrograma.closeConnection();
        return Response.status(Response.Status.NOT_FOUND).build();
        
    }
    
    
    //CREAR ANUNCIO
    @POST
    @Path("/crear/{nombreUsuario}/{tipoAnuncio}/{vigenciaAnuncio}/{costoAnuncio}/{costoOcultacion}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearAnuncio(@PathParam("nombreUsuario") String nombreUsuario, @PathParam("tipoAnuncio") String tipoAnuncio, 
            @PathParam("vigenciaAnuncio") String vigenciaAnuncio, @PathParam("costoAnuncio") int costoAnuncio, 
            @PathParam("costoOcultacion") int costoOcultacion) {
        
        
        MotorPrograma motorPrograma = new MotorPrograma(nombreUsuario);
        motorPrograma.comprar();
        Tienda tienda = motorPrograma.getTienda();
        
        if (tienda.crearAnuncio(tipoAnuncio, vigenciaAnuncio, costoAnuncio, costoOcultacion)) {
            motorPrograma.closeConnection();
            return Response.ok().build();
        } else {
            motorPrograma.closeConnection();
            return Response.status(Response.Status.CONFLICT).build();
        }
        
    }
    
}
