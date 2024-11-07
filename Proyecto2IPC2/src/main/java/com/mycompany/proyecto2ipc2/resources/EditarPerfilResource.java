package com.mycompany.proyecto2ipc2.resources;

import Principales.MotorPrograma;
import Usuarios.Usuario;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("editarPerfil")
public class EditarPerfilResource {

    @GET
    @Path("/{nombreUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPerfil(@PathParam("nombreUsuario") String nombreUsuario) {
        System.out.println("Extrayendo perfio de: " + nombreUsuario);
        MotorPrograma motorPrograma = new MotorPrograma(nombreUsuario);
        //MotorPrograma motorPrograma = MotorPrograma.getInstance(nombreUsuario);
        Usuario usuario = motorPrograma.extraerDatosUsuario();
        usuario.toString();
        motorPrograma.closeConnection();
        return Response.ok(usuario).build();

    }

    //LO de abajo es para pasar información, ANTES TENÍA @GET lo cambié por @POST ver eso
    @POST
    @Path("/{nombreAnterior}/{nombreUsuario}/{contraseña}/{rol}/{foto}/{hobbies}/{descripcion}/{gustos}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEditarPerfil(@PathParam("nombreAnterior") String nombreViejo, @PathParam("nombreUsuario") String nombreUsuarioNuevo, @PathParam("contraseña") String contraseña,
            @PathParam("rol") String rol, @PathParam("foto") String foto, @PathParam("hobbies") String hobbies, @PathParam("descripcion") String descripcion,
            @PathParam("gustos") String gustos) {

        MotorPrograma motorPrograma = new MotorPrograma(nombreViejo);

        //System.out.println( "NOmbre usuari: " + nombreUsuarioNuevo + " COntraseña: " + contraseña + " foto: " + foto + " rol: " + rol);

        if (!nombreUsuarioNuevo.equals(motorPrograma.getUsuario().getNombreUsuario())) {
            System.out.println("Nombre editado");
            if (!motorPrograma.verificarNombreRegistrado(nombreUsuarioNuevo)) {
                //System.out.println("Nombre disponible, editando datos");
                motorPrograma.actualizarDatosUsuario(nombreUsuarioNuevo, contraseña, rol, foto, hobbies, descripcion, gustos);

                //Redirige al menu
                motorPrograma.closeConnection();
                return Response.ok().build();

            } else {

                //Redirige a editar perfil
                motorPrograma.closeConnection();
                return Response.status(Response.Status.BAD_REQUEST).build();

                //System.out.println("Nombre ya está en uso");
            }
        } else {
            motorPrograma.actualizarDatosUsuario(nombreUsuarioNuevo, contraseña, rol, foto, hobbies, descripcion, gustos);
            //Redirige al menu
            motorPrograma.closeConnection();
            return Response.ok().build();
        }

    }

}
