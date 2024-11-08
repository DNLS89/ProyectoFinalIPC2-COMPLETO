package Proceso;

import Principales.Tienda;
import Usuarios.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Suscripcion extends Proceso {

    public Suscripcion(Usuario usuario, Connection connection) {
        super(usuario, connection);
    }

    public boolean suscribir(java.sql.Date fechaSuscripcion, BigDecimal costoSuscripcion, Tienda tienda) {

        if (tienda.creditosSuficientes(costoSuscripcion)) {
            String comandoSuscribir = "INSERT INTO suscribir (nombre_usuario, numero_revista, fecha_creacion) "
                    + "VALUES (?, ?, ?);";

            try {
                PreparedStatement comando = connection.prepareStatement(comandoSuscribir);
                comando.setString(1, usuario.getNombreUsuario());
                comando.setInt(2, revista.getNumeroRevista());
                comando.setDate(3, fechaSuscripcion);

                comando.execute();
                comando.close();

                if (registrarPagoSuscripcion(fechaSuscripcion, costoSuscripcion)) {

                    return true;
                }

//            Statement statementInsert = connection.createStatement();
//            statementInsert = connection.createStatement();
//            statementInsert.executeUpdate(comandoSuscribir);
            } catch (SQLException e) {
                System.out.println("Error suscribiendo");
                e.printStackTrace();
                return false;
            }
        }

        return false;
    }

    public boolean registrarPagoSuscripcion(java.sql.Date fechaSuscripcion, BigDecimal costo) {
        usuario.getNombreUsuario();

        this.revista.getNumeroRevista();

        String comandoSuscribir = "INSERT INTO pagarsuscripcion (nombre_usuario, numero_revista, costo, fecha_suscripcion) "
                + "VALUES (?, ?, ?, ?);";

        try {
            PreparedStatement comando = connection.prepareStatement(comandoSuscribir);
            comando.setString(1, usuario.getNombreUsuario());
            comando.setInt(2, revista.getNumeroRevista());
            comando.setBigDecimal(3, costo);
            comando.setDate(4, fechaSuscripcion);

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
