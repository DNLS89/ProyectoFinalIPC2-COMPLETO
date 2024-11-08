package com.mycompany.proyecto2ipc2.resources;

import Anuncios.Anuncio;
import Principales.GestorReportes;
import Principales.MotorPrograma;
import Principales.Revista;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;



@Path("reportesAdmin")
public class ReporteAdminResource {
    
    //OBTENER SUSCRIPCIONES
    @GET
    @Path("/extraerSuscripciones/{nombreUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerSuscripciones(@PathParam("nombreUsuario") String nombreUsuario) {
        
        
        System.out.println("Extrayendo SUSCRIOPCIONES");
        MotorPrograma motorPrograma = new MotorPrograma(nombreUsuario);
        GestorReportes gestorReportes = motorPrograma.getGestorReportes();
        
        
        ArrayList<Revista> revistasConSuscripciones = gestorReportes.extraerSuscripcionesReporteAdmin(nombreUsuario);
        
        if (revistasConSuscripciones != null) {
            //System.out.println("Hay revistas");
            motorPrograma.closeConnection();
            return Response.ok(revistasConSuscripciones).build();
        }
        
        motorPrograma.closeConnection();
        return Response.status(Response.Status.NOT_FOUND).build();
        
    }
    
    //OBTENER Recurrencia Suscripciones
    @GET
    @Path("/extraerSuscripciones/recurrencias/{nombreUsuario}/{fechaInicio}/{fechaFin}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerRecurrenciasSuscripciones(@PathParam("nombreUsuario") String nombreUsuario, @PathParam("fechaInicio") String fechaInicio,
            @PathParam("fechaFin") String fechaFin) {
        
        System.out.println("Extrayendo RECURRENCIAS SUCRIPOCIONES");
        
        System.out.println("PRUEBA VALORES INDEFINIDOS: fechaINi: " + fechaInicio + " fechafin:" + fechaFin);
        
        
        MotorPrograma motorPrograma = new MotorPrograma(nombreUsuario);
        GestorReportes gestorReportes = motorPrograma.getGestorReportes();
        
        
        ArrayList<Revista> revistasConSuscripciones = gestorReportes.
                extraerRecurrenciaSuscripcionesReporteAdmin(nombreUsuario, fechaInicio, fechaFin, motorPrograma);
        
        if (revistasConSuscripciones != null) {
            //System.out.println("Hay revistas");
            motorPrograma.closeConnection();
            return Response.ok(revistasConSuscripciones).build();
        }
        
        motorPrograma.closeConnection();
        return Response.status(Response.Status.NOT_FOUND).build();
        
    }
    
    //OBTENER COmentarios
    @GET
    @Path("/extraerComentarios/{nombreUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerComentarios(@PathParam("nombreUsuario") String nombreUsuario) {
        
        
        System.out.println("Extrayendo COMENTARIOS");
        MotorPrograma motorPrograma = new MotorPrograma(nombreUsuario);
        GestorReportes gestorReportes = motorPrograma.getGestorReportes();
        
        
        ArrayList<Revista> revistasConComentarios = gestorReportes.extraerComentariosReporteAdmin(nombreUsuario);
        
        if (revistasConComentarios != null) {
            //System.out.println("Hay revistas");
            motorPrograma.closeConnection();
            return Response.ok(revistasConComentarios).build();
        }
        
        motorPrograma.closeConnection();
        return Response.status(Response.Status.NOT_FOUND).build();
        
    }
    
    //OBTENER Recurrencia Suscripciones
    @GET
    @Path("/extraerComentarios/recurrencias/{nombreUsuario}/{fechaInicio}/{fechaFin}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerRecurrenciasComentarios(@PathParam("nombreUsuario") String nombreUsuario, @PathParam("fechaInicio") String fechaInicio,
            @PathParam("fechaFin") String fechaFin) {
        
        System.out.println("Extrayendo RECURRENCIAS COMENTARIOS");
        
        System.out.println("PRUEBA VALORES INDEFINIDOS: fechaINi: " + fechaInicio + " fechafin:" + fechaFin);
        
        
        MotorPrograma motorPrograma = new MotorPrograma(nombreUsuario);
        GestorReportes gestorReportes = motorPrograma.getGestorReportes();
        
        
        ArrayList<Revista> revistasConComentarios = gestorReportes.
                extraerRecurrenciaComentariosReporteAdmin(nombreUsuario, fechaInicio, fechaFin, motorPrograma);
        
        if (revistasConComentarios != null) {
            //System.out.println("Hay revistas");
            motorPrograma.closeConnection();
            return Response.ok(revistasConComentarios).build();
        }
        
        motorPrograma.closeConnection();
        return Response.status(Response.Status.NOT_FOUND).build();
        
    }
    
    //OBTENER ANUNCIOS
    @GET
    @Path("/extraerAnuncios/{nombreUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerAnuncios(@PathParam("nombreUsuario") String nombreUsuario) {
        
        System.out.println("EXTRAYENDO ANUNCIOS");
        MotorPrograma motorPrograma = new MotorPrograma(nombreUsuario);
        GestorReportes gestorReportes = motorPrograma.getGestorReportes();
        
        
        ArrayList<Anuncio> anuncios = gestorReportes.extraerAnunciosReporteAdmin(nombreUsuario);
        
        if (anuncios != null) {
            //System.out.println("Hay revistas");
            motorPrograma.closeConnection();
            return Response.ok(anuncios).build();
        }
        
        motorPrograma.closeConnection();
        return Response.status(Response.Status.NOT_FOUND).build();
        
    }
    
}
