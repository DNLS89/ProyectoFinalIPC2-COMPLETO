package Proceso;

import Principales.Revista;
import Usuarios.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Reaccion extends Proceso{

    public Reaccion(Usuario usuario, Connection connection) {
        super(usuario, connection);
    }

    
    
    
    public boolean comentar(Revista revista, String comentario) {
        this.registrarFechaRealizacion();
//        String comando = "INSERT INTO comentar (nombre_usuario, numero_revista, fecha_creacion, comentario) "
//                + "VALUES ('" + usuario.getNombreUsuario() + "', '" + revista.getNumeroRevista() + "', '" + fechaProceso + "', '" + comentario + "');";
        
        String comandoString = "INSERT INTO comentar (nombre_usuario, numero_revista, fecha_creacion, comentario) "
                + "VALUES (?, ?, ?, ?);";

        try {
            
            PreparedStatement comando = connection.prepareStatement(comandoString);
            comando.setString(1, usuario.getNombreUsuario());
            comando.setInt(2, revista.getNumeroRevista());
            comando.setDate(3, fechaProceso);
            comando.setString(4, comentario);
            
//            Statement statementInsert = connection.createStatement();
//            statementInsert = connection.createStatement();
//            statementInsert.executeUpdate(comando);
            
            comando.execute();
            comando.close();
            
            return true;
        } catch (SQLException e) {
            System.out.println("Error relación comentar");
            e.printStackTrace();
            return false;
        }
        
        
    }
    
    public void darMeGusta(Revista revista) {
        
        
        //Acá comprobar que el usuario no le haya dado me gusta antes, si ya le dió no tendría que darjar mostrar el botón de megusta
//        String comandoSuscribir = "UPDATE revista set me_gustas=" + (obtenerTotalMeGustas(revista) + 1) + " "
//                + "WHERE numero_revista LIKE " + revista.getNumeroRevista() + ";";
        
        String comandoMeGusta = "UPDATE revista set me_gustas=? WHERE numero_revista LIKE ?;";

        try {
            
            PreparedStatement comando = connection.prepareStatement(comandoMeGusta);
            comando.setInt(1, (obtenerTotalMeGustas(revista) + 1));
            comando.setInt(2, revista.getNumeroRevista());
            
//            Statement statementInsert = connection.createStatement();
//            statementInsert = connection.createStatement();
//            statementInsert.executeUpdate(comandoSuscribir);
            
            this.registrarFechaRealizacion();
            crearRelacionDarMeGusta(revista);
            //request.setAttribute("mensajeSuscripcion", "SUSCRIPCION CORRECTA");
            
            comando.execute();
            comando.close();
            
        } catch (SQLException e) {
            //request.setAttribute("mensajeSuscripcion", "ERROR AL SUSCRIBIR");
            System.out.println("Error dando Me gusta");
            e.printStackTrace();
        }
    }
    
    private void crearRelacionDarMeGusta(Revista revista) {
        
//        String comandoPublicar = "INSERT INTO megusta (nombre_usuario, numero_revista, fecha_creacion) "
//                + "VALUES ('" + usuario.getNombreUsuario() + "', '" + revista.getNumeroRevista() + "', '" + fechaProceso + "');";

        String comandoPublicar = "INSERT INTO megusta (nombre_usuario, numero_revista, fecha_creacion) "
                + "VALUES (?, ?, ?);";
        
        
        
        try {
            
            PreparedStatement comando = connection.prepareStatement(comandoPublicar);
            comando.setString(1, usuario.getNombreUsuario());
            comando.setInt(2, revista.getNumeroRevista());
            comando.setDate(3, fechaProceso);
            comando.execute();
            comando.close();
            
//            Statement statementInsert = connection.createStatement();
//            statementInsert = connection.createStatement();
//            statementInsert.executeUpdate(comandoPublicar);
            
        } catch (SQLException e) {
            System.out.println("Error relación dar me gusta");
            e.printStackTrace();
        }
    }
    
    public int obtenerTotalMeGustas (Revista revista) {
        int totalMeGustas = 0;
        
//        String comandoNombre = "select * from revista WHERE numero_revista LIKE " + revista.getNumeroRevista() + ";";
        String comandoNombre = "select * from revista WHERE numero_revista LIKE ?;";
        //System.out.println("COmando: " + comandoNombre);
        try {
            PreparedStatement comando = connection.prepareStatement(comandoNombre);
            comando.setInt(1, revista.getNumeroRevista());
            
            ResultSet resultSet = comando.executeQuery();
            
//            Statement statementInsert = connection.createStatement();
//            ResultSet resultSet = statementInsert.executeQuery(comandoNombre);

            if (resultSet.next()) {
                totalMeGustas = resultSet.getInt("me_gustas");
                //System.out.println("Número de me gustas: " + totalMeGustas);
            }
            
            resultSet.close();
            comando.execute();
            comando.close();

        } catch (SQLException e) {
            System.out.println("Error al extraer me gustas");
            e.printStackTrace();
        }
        
        return totalMeGustas;
    }
}
