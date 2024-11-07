package Principales;

import Anuncios.Anuncio;
import Usuarios.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GestorAnuncios {

    private Usuario usuario;
    private String nombreUsuario;
    private Connection connection;
    private Anuncio anunciosComprados[];
    private int numeroAnunciosComprados = 0;

    public GestorAnuncios(String nombreUsuario, Connection connection) {
        this.nombreUsuario = nombreUsuario;
        this.connection = connection;
    }

    public Anuncio[] getAnunciosComprados() {
        return anunciosComprados;
    }

    public void setAnunciosComprados(Anuncio[] anunciosComprados) {
        this.anunciosComprados = anunciosComprados;
    }

    public void extraerAnunciosComprados() {
        contarNumeroAnunciosComprados();

        anunciosComprados = new Anuncio[numeroAnunciosComprados];

        //String comando = "select * FROM anunciar WHERE nombre_usuario LIKE \"" + nombreUsuario + "\" AND caducado=0;";

        int contadorNumeroAnuncio = 0;
        try {
            
            PreparedStatement comando = connection.prepareStatement("select * FROM anunciar WHERE nombre_usuario LIKE ? AND caducado=0;");
            comando.setString(1, nombreUsuario);
            ResultSet resultSet = comando.executeQuery();
            while (resultSet.next()) {

                int estadoAnuncio = resultSet.getInt("estado_anuncio");
                String tipoAnuncio = resultSet.getString("tipo_anuncio");

                anunciosComprados[contadorNumeroAnuncio] = new Anuncio(estadoAnuncio, tipoAnuncio);

                contadorNumeroAnuncio++;
            }
            
            
//            Statement statementInsert = connection.createStatement();
//            ResultSet resultSet = statementInsert.executeQuery(comando);
//            while (resultSet.next()) {
//
//                int estadoAnuncio = resultSet.getInt("estado_anuncio");
//                String tipoAnuncio = resultSet.getString("tipo_anuncio");
//
//                anunciosComprados[contadorNumeroAnuncio] = new Anuncio(estadoAnuncio, tipoAnuncio);
//
//                contadorNumeroAnuncio++;
//            }

            //System.out.println("Anuncios comprados: " + numeroAnunciosComprados);

        } catch (SQLException e) {
            System.out.println("Error encontrando Creditos");
            e.printStackTrace();
        }
    }

    private void contarNumeroAnunciosComprados() {
        //String comando = "select * FROM anunciar WHERE nombre_usuario LIKE \"" + nombreUsuario + "\" AND caducado=0;";

        try {
            
            PreparedStatement comando = connection.prepareStatement("select * FROM anunciar WHERE nombre_usuario LIKE ? AND caducado=0;");
            comando.setString(1, nombreUsuario);
            ResultSet resultSet = comando.executeQuery();
            while (resultSet.next()) {
                numeroAnunciosComprados++;
            }
            
            
            
//            Statement statementInsert = connection.createStatement();
//            ResultSet resultSet = statementInsert.executeQuery(comando);
//            while (resultSet.next()) {
//                numeroAnunciosComprados++;
//            }

        } catch (SQLException e) {
            System.out.println("Error encontrando Creditos");
            e.printStackTrace();
        }
    }

    public void cambiarEstadoAnuncios(boolean estadoSeleccionado, HttpServletRequest request) {
        int estado;
        if (estadoSeleccionado == true) {
            estado = 1;
        } else {
            estado = 0;
        }

        if (hayAnunciosComprados(request)) {
            String comando = "UPDATE anunciar set estado_anuncio=" + estado + " WHERE nombre_usuario LIKE \"" + nombreUsuario + "\" AND caducado=0;";

            try {
                Statement statementInsert = connection.createStatement();
                statementInsert = connection.createStatement();
                statementInsert.executeUpdate(comando);
                request.setAttribute("mensajeEstado", "ESTADO CAMBIADO CORRECTAMENTE");

            } catch (SQLException e) {
                System.out.println("Error cambiando estado anuncios");

                e.printStackTrace();
            }
        }

    }

    private boolean hayAnunciosComprados(HttpServletRequest request) {
        //String comando = "select * FROM anunciar WHERE nombre_usuario LIKE \"" + nombreUsuario + "\" AND caducado=0;";

        try {
            
            
            
            PreparedStatement comando = connection.prepareStatement("select * FROM anunciar WHERE nombre_usuario LIKE ? AND caducado=0;");
            comando.setString(1, nombreUsuario);
            ResultSet resultSet = comando.executeQuery();
            if (resultSet.next()) {
                return true;
            }
            
            
//            Statement statementInsert = connection.createStatement();
//            ResultSet resultSet = statementInsert.executeQuery(comando);
//            if (resultSet.next()) {
//                return true;
//            }

        } catch (SQLException e) {
            System.out.println("Error encontrando Creditos");
            e.printStackTrace();
        }
        request.setAttribute("mensajeEstado", "NO HAY ANUNCIOS COMPRADOS");

        return false;
    }
}
