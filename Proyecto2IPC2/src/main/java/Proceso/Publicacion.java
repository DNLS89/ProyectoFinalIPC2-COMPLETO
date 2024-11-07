package Proceso;

import Principales.CategoriaRevista;
import Principales.Revista;
import Usuarios.Usuario;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Publicacion extends Proceso{
    

    public Publicacion(Usuario usuario, Connection connection) {
        super(usuario, connection);
    }
    
    public void publicar(String archivoRevista, String descripcion, String categoria, String tags) {
        
        registrarRevista(archivoRevista, descripcion, categoria, tags);
        registrarPublicacionSQL();
        
    }
    
    
    public void registrarRevista(String archivoRevista, String descripcion, String categoria, String tags) {
        
        revista = new Revista(usuario.getNombreUsuario(), archivoRevista, descripcion, categoria, tags);
        //Crear SQL que registre a la revista, luego en publicar
        registrarRevistaSQL();
        obtenerIdSQLRevista();
    }
    
    public void registrarRevistaSQL() {
        String comandoCrearUsuario = "INSERT INTO revista (descripcion, categoria, etiquetas) "
                + "VALUES ('" + revista.getDescripcion() + "', '" + revista.getCategoria() + "', '" + revista.getTagsString() + "');";
        
        try {
            Statement statementInsert = connection.createStatement();
            statementInsert = connection.createStatement();
            statementInsert.executeUpdate(comandoCrearUsuario);
        } catch (SQLException e) {
            System.out.println("Error ingresando revista a SQLS");
            e.printStackTrace();
        }
    } 
    
    public void obtenerIdSQLRevista() {
        String comandoNumeroRevista = "select max(numero_revista) from revista;";

        try {
            Statement statementInsert = connection.createStatement();
            ResultSet resultSet = statementInsert.executeQuery(comandoNumeroRevista);
            if (resultSet.next()) {
                revista.setNumeroRevista(Integer.parseInt(resultSet.getString("max(numero_revista)")));

            }

        } catch (SQLException e) {
            System.out.println("Error encontrando ID revista");
            e.printStackTrace();
        }
    }
    
    public void registrarPublicacionSQL() {
        String comandoPublicar = "INSERT INTO publicar (nombre_usuario, numero_revista, fecha_creacion) "
                + "VALUES ('" + usuario.getNombreUsuario() + "', '" + revista.getNumeroRevista() + "', '" + fechaProceso + "');";
        
        try {
            Statement statementInsert = connection.createStatement();
            statementInsert = connection.createStatement();
            statementInsert.executeUpdate(comandoPublicar);
        } catch (SQLException e) {
            System.out.println("Error publicando");
            e.printStackTrace();
        }
    }
}
