package Principales;

import Usuarios.Usuario;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuInicio {

    private String nombreUsuario;
    private String contraseña;
    private Connection connection;
    private SQL sql;

    public MenuInicio() {
        this.sql = SQL.getInstance();
        this.connection = sql.getConnection();
    }
    
    public void close() {
        System.out.println("Cerrando conexión");
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(MotorPrograma.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean verificarCredenciales(String nombreUsuario, String contraseña) {

        //Primero verificar que el nombre exista
        if (verificarNombreRegistrado(nombreUsuario)) {

            //Luego verificar que la contraseña corresponda al nombre de usuario

            //Convierte la contraseña a Hash
            //contraseña = contraseña.replace("", "界");
            contraseña = sql.convertirAHash(contraseña);
            
            try {
                
                PreparedStatement comando = connection.prepareStatement("SELECT * FROM usuario WHERE nombre_usuario like ?");
                comando.setString(1, nombreUsuario);
                ResultSet resultSet = comando.executeQuery();
                //Verifica la contraseña
                if (resultSet.next()) {
                    if (contraseña.equals(resultSet.getString("contraseña"))) {
                        System.out.println("La contraseña concuerda");
                        //La contraseña concuerda
                        
                        resultSet.close();
                        comando.close();
                        return true;
                    }
                    System.out.println("La contraseña no concuerda");
                    
                    resultSet.close();
                    comando.close();
                    return false;
                }
                comando.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //El nombre no existe en el registro
        
        
        return false;

    }

    public boolean verificarNombreRegistrado(String nombreUsuario) {
        //String comandoNombre = "SELECT * FROM usuario WHERE nombre_usuario like \"" + nombreUsuario + "\"";

        try {
            PreparedStatement comando = connection.prepareStatement("SELECT * FROM usuario WHERE nombre_usuario like ?");
            comando.setString(1, nombreUsuario);
            ResultSet resultSet = comando.executeQuery();
            if (resultSet.next()) {
                //Existe el registro
                //System.out.println("Existe el usuario");
                resultSet.close();
                comando.close();
                return true;
            }
            
            resultSet.close();
            comando.close();
            
        } catch (Exception e) {
            System.out.println("Error en verificar el nombre registrado");
            e.printStackTrace();
        }
        //System.out.println("El usuario no existe");
        return false;
    }

    public void crearUsuarioNuevo(String nombreUsuario, String contraseña, String rol, String foto, String hobbies, String descripcion, String gustos) {

        //Convertir a HASH la contraseña
        contraseña = sql.convertirAHash(contraseña);
        
        try {
            
            PreparedStatement comando = connection.prepareStatement("INSERT INTO usuario "
                    + "(nombre_usuario, contraseña, rol, foto, hobbies, descripcion, gustos) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?);");
            comando.setString(1, nombreUsuario);
            comando.setString(2, contraseña);
            comando.setString(3, rol);
            comando.setString(4, foto);
            comando.setString(5, hobbies);
            comando.setString(6, descripcion);
            comando.setString(7, gustos);
            
            comando.execute();
            comando.close();
            
        } catch (SQLException e) {
            System.out.println("Error creando un usuario nuevo");
            e.printStackTrace();
        }

    }

//    public MotorPrograma ingresarAlSistema(String nombreUsuario) {
//        //Acá que cree al motor del programa
//        MotorPrograma motorPrograma = MotorPrograma.getInstance(nombreUsuario);
//        //MotorPrograma motorPrograma = new MotorPrograma(nombreUsuario);
//        return motorPrograma;
//    }
    
}
