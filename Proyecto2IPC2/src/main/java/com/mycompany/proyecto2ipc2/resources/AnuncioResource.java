package com.mycompany.proyecto2ipc2.resources;

import Anuncios.Anuncio;
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


@Path("anuncio")
public class AnuncioResource {
    
    
    //OBTENER TODOS LOS ANUNCIOS CREADOS
    @GET
    @Path("/explorarAnuncios/{nombreUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerAnuncios(@PathParam("nombreUsuario") String nombreUsuario) {
        
        MotorPrograma motorPrograma = new MotorPrograma(nombreUsuario);
        motorPrograma.extraerRolUsuario();
        motorPrograma.comprar();
        Tienda tienda = motorPrograma.getTienda();
        tienda.extraerAnuncios();
        
        ArrayList<Anuncio> anunciosCreados = tienda.extraerAnuncios();
        
        if (anunciosCreados != null) {
            //System.out.println("Hay revistas");
            motorPrograma.closeConnection();
            return Response.ok(anunciosCreados).build();
        }
        
        motorPrograma.closeConnection();
        return Response.status(Response.Status.NOT_FOUND).build();
        
    }
    
    //OBTENER TODOS LOS ANUNCIOS COMPRADOS
    @GET
    @Path("/explorarAnunciosComprados/{nombreUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerAnunciosComprados(@PathParam("nombreUsuario") String nombreUsuario) {
        
        MotorPrograma motorPrograma = new MotorPrograma(nombreUsuario);
        motorPrograma.extraerRolUsuario();
        motorPrograma.comprar();
        Tienda tienda = motorPrograma.getTienda();
        
        ArrayList<Anuncio> anunciosComprados = tienda.extraerAnunciosComprados();
        
        if (anunciosComprados != null) {
            //System.out.println("Hay revistas");
            motorPrograma.closeConnection();
            return Response.ok(anunciosComprados).build();
        }
        
        motorPrograma.closeConnection();
        return Response.status(Response.Status.NOT_FOUND).build();
        
    }
    
    //OBTENER LOS ANUNCIOS POR MOSTRAR
    @GET
    @Path("/anunciosPorMostrar/{nombreUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerAnunciosPorMostrar(@PathParam("nombreUsuario") String nombreUsuario) {
        
        MotorPrograma motorPrograma = new MotorPrograma(nombreUsuario);
        motorPrograma.extraerRolUsuario();
        motorPrograma.comprar();
        Tienda tienda = motorPrograma.getTienda();
        
        ArrayList<Anuncio> anunciosPorMostrar = tienda.extraerAnunciosPorMostrar();
        
        if (anunciosPorMostrar != null) {
            //System.out.println("Hay revistas");
            motorPrograma.closeConnection();
            return Response.ok(anunciosPorMostrar).build();
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
    
    //PUBLICITAR ANUNCIO
    @POST
    @Path("/publicitar/{nombreUsuario}/{nombreAnunciador}/{idAnuncio}/{url}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response publicitarAnuncio(@PathParam("nombreUsuario") String nombreUsuario, @PathParam("nombreAnunciador") String nombreAnunciador
            , @PathParam("idAnuncio") int idAnuncio, @PathParam("url") String url) {
        
        
        MotorPrograma motorPrograma = new MotorPrograma(nombreUsuario);
        motorPrograma.comprar();
        Tienda tienda = motorPrograma.getTienda();
        
        if (tienda.publicitarAnuncio(idAnuncio, nombreAnunciador, url)) {
            motorPrograma.closeConnection();
            return Response.ok().build();
        } else {
            motorPrograma.closeConnection();
            return Response.status(Response.Status.CONFLICT).build();
        }
        
    }
    
    //MODIFICAR ANUNCIO
    @POST
    @Path("/modificar/{nombreUsuario}/{idAnuncio}/{tipoAnuncio}/{vigenciaAnuncio}/{costoAnuncio}/{costoOcultacion}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response modificarAnuncio(@PathParam("nombreUsuario") String nombreUsuario, @PathParam("tipoAnuncio") String tipoAnuncio, 
            @PathParam("vigenciaAnuncio") String vigenciaAnuncio, @PathParam("costoAnuncio") int costoAnuncio, 
            @PathParam("costoOcultacion") int costoOcultacion, @PathParam("idAnuncio") int idAnuncio) {
        
        System.out.println("MODIFICANDO ANUNCIO");
        MotorPrograma motorPrograma = new MotorPrograma(nombreUsuario);
        motorPrograma.comprar();
        Tienda tienda = motorPrograma.getTienda();
        
        if (tienda.modificarAnuncio(tipoAnuncio, vigenciaAnuncio, costoAnuncio, costoOcultacion, idAnuncio)) {
            motorPrograma.closeConnection();
            return Response.ok().build();
        } else {
            motorPrograma.closeConnection();
            return Response.status(Response.Status.CONFLICT).build();
        }
        
    }
    
    //ELIMINAR ANUNCIO
    @POST
    @Path("/eliminar/{nombreUsuario}/{idAnuncio}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarAnuncio(@PathParam("nombreUsuario") String nombreUsuario, @PathParam("idAnuncio") int idAnuncio) {
        
        System.out.println("ELIMINAR ANUNCIO");
        MotorPrograma motorPrograma = new MotorPrograma(nombreUsuario);
        motorPrograma.comprar();
        Tienda tienda = motorPrograma.getTienda();
        
        if (tienda.eliminarAnuncio(idAnuncio)) {
            motorPrograma.closeConnection();
            return Response.ok().build();
        } else {
            motorPrograma.closeConnection();
            return Response.status(Response.Status.CONFLICT).build();
        }
        
    }
    
    //CAMBIAR ESTADO ANUNCIO ADMIN
    @POST
    @Path("/cambiarEstado/{nombreUsuario}/{idAnuncio}/{estadoAnuncio}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response cambiarEstadoAnuncio(@PathParam("nombreUsuario") String nombreUsuario, @PathParam("idAnuncio") int idAnuncio,
            @PathParam("estadoAnuncio") int estadoAnuncio) {
        
        System.out.println("CAMBIANDO ESTADO ANUNCIO");
        MotorPrograma motorPrograma = new MotorPrograma(nombreUsuario);
        motorPrograma.extraerRolUsuario();
        motorPrograma.comprar();
        Tienda tienda = motorPrograma.getTienda();
        
        if (tienda.cambiarEstadoAnuncio(idAnuncio, estadoAnuncio)) {
            motorPrograma.closeConnection();
            return Response.ok().build();
        } else {
            motorPrograma.closeConnection();
            return Response.status(Response.Status.CONFLICT).build();
        }
        
    }
    
    //COMPRAR ANUNCIO
    @POST
    @Path("/comprar/{nombreUsuario}/{idAnuncio}/{fechaCompra}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response comprarAnuncio(@PathParam("nombreUsuario") String nombreUsuario, @PathParam("idAnuncio") int idAnuncio, 
            @PathParam("fechaCompra") String fechaCompra) {
        
        System.out.println("COMPRANDO ANUNCIO");
        System.out.println("Datos ingreados: id " + idAnuncio + " fecha:" + fechaCompra);
        
        
        MotorPrograma motorPrograma = new MotorPrograma(nombreUsuario);
        motorPrograma.comprar();
        Tienda tienda = motorPrograma.getTienda();
        
        if (tienda.comprarAnuncioPRUEBA(idAnuncio, motorPrograma.formatoFechaAdecuado(fechaCompra))) {
            motorPrograma.closeConnection();
            return Response.ok().build();
        } else {
            motorPrograma.closeConnection();
            return Response.status(Response.Status.CONFLICT).build();
        }
        
    }
}
