package Proceso;

import Usuarios.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Suscripcion extends Proceso{
    
    public Suscripcion(Usuario usuario, Connection connection) {
        super(usuario, connection);
    }
    
    public boolean suscribir(java.sql.Date fechaSuscripcion) {
        
//        String comandoSuscribir = "INSERT INTO suscribir (nombre_usuario, numero_revista, fecha_creacion) "
//                + "VALUES ('" + usuario.getNombreUsuario() + "', '" + revista.getNumeroRevista() + "', '" + fechaSuscripcion + "');";
        
        String comandoSuscribir = "INSERT INTO suscribir (nombre_usuario, numero_revista, fecha_creacion) "
                + "VALUES (?, ?, ?);";
        
        try {
            PreparedStatement comando = connection.prepareStatement(comandoSuscribir);
            comando.setString(1, usuario.getNombreUsuario());
            comando.setInt(2, revista.getNumeroRevista());
            comando.setDate(3, fechaSuscripcion);
            
            comando.execute();
            comando.close();
            return true;
            
//            Statement statementInsert = connection.createStatement();
//            statementInsert = connection.createStatement();
//            statementInsert.executeUpdate(comandoSuscribir);
            
        } catch (SQLException e) {
            System.out.println("Error suscribiendo");
            e.printStackTrace();
            return false;
        }
    }
}
