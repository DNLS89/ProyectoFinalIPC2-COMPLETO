package Principales;

import Anuncios.Anuncio;
import Usuarios.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;

public class Tienda {

    private Usuario usuario;
    private Connection connection;
    protected java.sql.Date fechaTransaccion;
    //private ArrayList<Anuncio> anuncios = new ArrayList<>(); 

    public Tienda(Usuario usuario, Connection connection) {
        this.usuario = usuario;
        this.connection = connection;
    }

    public void extraerCreditos() {
        //String comando = "select * FROM usuario WHERE nombre_usuario LIKE \"" + usuario.getNombreUsuario() + "\";";

        try {
            PreparedStatement comando = connection.prepareStatement("select * FROM usuario WHERE nombre_usuario LIKE ?");
            comando.setString(1, usuario.getNombreUsuario());

            ResultSet resultSet = comando.executeQuery();

//            Statement statementInsert = connection.createStatement();
//            ResultSet resultSet = statementInsert.executeQuery(comando);
            if (resultSet.next()) {
                usuario.setCartera(resultSet.getBigDecimal("cartera"));

            }

            resultSet.close();
            comando.close();

        } catch (SQLException e) {
            System.out.println("Error encontrando Creditos");
            e.printStackTrace();
        }
    }

    public boolean recargar(BigDecimal creditosPorComprar) {
        //String comandoCrearUsuario = "UPDATE usuario set cartera=" + (usuario.getCartera().add(creditosPorComprar)) + " WHERE nombre_usuario LIKE \"" + usuario.getNombreUsuario() + "\";";
        boolean recargaRealizada;
        // System.out.println("COmando: " + comandoCrearUsuario);
        try {

            PreparedStatement comando = connection.prepareStatement("UPDATE usuario set cartera=? WHERE nombre_usuario LIKE ?;");
            comando.setString(1, usuario.getCartera().add(creditosPorComprar).toString());
            comando.setString(2, usuario.getNombreUsuario());

            comando.execute();
//            Statement statementInsert = connection.createStatement();
//            statementInsert.executeUpdate(comandoCrearUsuario);
            recargaRealizada = true;

            comando.close();

        } catch (SQLException e) {
            System.out.println("Error recargando créditos");
            recargaRealizada = false;
            e.printStackTrace();
        }

        usuario.setCartera(usuario.getCartera().add(creditosPorComprar));
        return recargaRealizada;
    }

    public boolean crearAnuncio(String tipoAnuncio, String vigenciaAnuncio, int costoAnuncio, int costoOcultacion) {
        int intVigenciaAnuncio = obtenerAnuncioInt(vigenciaAnuncio);

        try {
            PreparedStatement comando = connection.prepareStatement("INSERT INTO anuncio (tipo_anuncio, vigencia_anuncio, costo_anuncio, costo_ocultacion) VALUES (?, ?, ?, ?);");
            comando.setString(1, tipoAnuncio);
            comando.setInt(2, intVigenciaAnuncio);
            comando.setInt(3, costoAnuncio);
            comando.setInt(4, costoOcultacion);

            comando.execute();
            //ResultSet resultSet = comando.execute();

            //resultSet.close();
            comando.close();

            return true;

        } catch (SQLException e) {
            System.out.println("Error creando Anuncio");
            e.printStackTrace();
        }

        return false;
    }
    
    public boolean publicitarAnuncio(int numeroAnuncio, String nombreAnunciador, String url) {
        
        java.sql.Date fechaActual = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        
        url = url.replace("-", "/");
        
        try {
            PreparedStatement comando = connection.prepareStatement("INSERT INTO publicitar (id_anuncio, nombre_usuario, fecha_mostrado, url) VALUES (?, ?, ?, ?);");
            comando.setInt(1, numeroAnuncio);
            comando.setString(2, nombreAnunciador);
            comando.setDate(3, fechaActual);
            comando.setString(4, url);

            comando.execute();
            //ResultSet resultSet = comando.execute();

            //resultSet.close();
            comando.close();

            return true;

        } catch (SQLException e) {
            System.out.println("Error pulicitando Anuncio");
            e.printStackTrace();
        }

        return false;
    }

    public ArrayList<Anuncio> extraerAnuncios() {

        ArrayList<Anuncio> anuncios = new ArrayList<>();
        try {
            PreparedStatement comando = connection.prepareStatement("select * from anuncio;");
            ResultSet resultSet = comando.executeQuery();

            while (resultSet.next()) {
                int idAnuncio = resultSet.getInt("id_anuncio");
                String tipoAnuncio = resultSet.getString("tipo_anuncio");
                int estadoAnuncio = resultSet.getInt("estado_anuncio");
                int vigenciaAnuncio = resultSet.getInt("vigencia_anuncio");
                int costoAnuncio = resultSet.getInt("costo_anuncio");
                int costoOcultacion = resultSet.getInt("costo_ocultacion");

                anuncios.add(new Anuncio(idAnuncio, tipoAnuncio, estadoAnuncio, vigenciaAnuncio, costoAnuncio, costoOcultacion));

            }
            resultSet.close();
            comando.close();

        } catch (SQLException e) {
            System.out.println("Error al extraer todos los anuncios");
            e.printStackTrace();
        }

        if (!anuncios.isEmpty()) {
            return anuncios;
        } else {
            return null;
        }
    }

    public ArrayList<Anuncio> extraerAnunciosComprados() {
        ArrayList<Anuncio> anuncios = new ArrayList<>();
        try {
            //EXTRAE LOS ANUNCIOS DESDE LA TABLA ANUNCIAR1, CUANDO NO SE ES ADMIN
            PreparedStatement comando = connection.prepareStatement("select * from anuncio LEFT JOIN anunciar1 ON anuncio.id_anuncio "
                    + "= anunciar1.id_anuncio WHERE nombre_usuario LIKE ? AND caducado=0;");
            comando.setString(1, usuario.getNombreUsuario());
            ResultSet resultSet = comando.executeQuery();

            while (resultSet.next()) {
                int idAnuncio = resultSet.getInt("id_anuncio");
                String tipoAnuncio = resultSet.getString("tipo_anuncio");
                int estadoAnuncio = resultSet.getInt("estado_anuncio");
                int vigenciaAnuncio = resultSet.getInt("vigencia_anuncio");
                int costoAnuncio = resultSet.getInt("costo_anuncio");
                int costoOcultacion = resultSet.getInt("costo_ocultacion");

                anuncios.add(new Anuncio(idAnuncio, tipoAnuncio, estadoAnuncio, vigenciaAnuncio, costoAnuncio, costoOcultacion));

            }
            resultSet.close();
            comando.close();

        } catch (SQLException e) {
            System.out.println("Error al extraer todos los anuncios");
            e.printStackTrace();
        }

        if (!anuncios.isEmpty()) {
            return anuncios;
        } else {
            return null;
        }
    }
    
    public ArrayList<Anuncio> extraerAnunciosPorMostrar() {
//        String comando = "select * from anuncio LEFT JOIN anunciar1 ON anuncio.id_anuncio = anunciar1.id_anuncio "
//                + "WHERE caducado=0 AND estado_anuncio=1 AND CURDATE() BETWEEN fecha_creacion AND fecha_fin;";
        ArrayList<Anuncio> anuncios = new ArrayList<>();
        try {
            //EXTRAE LOS ANUNCIOS 
            PreparedStatement comando = connection.prepareStatement("select * from anuncio LEFT JOIN anunciar1 ON anuncio.id_anuncio = anunciar1.id_anuncio "
                    + "WHERE caducado=0 AND estado_anuncio_comprado=1 AND CURDATE() BETWEEN fecha_creacion AND fecha_fin;");
            ResultSet resultSet = comando.executeQuery();

            while (resultSet.next()) {
                Anuncio anuncio;
                
                int idAnuncio = resultSet.getInt("id_anuncio");
                String tipoAnuncio = resultSet.getString("tipo_anuncio");
                int estadoAnuncio = resultSet.getInt("estado_anuncio");
                int vigenciaAnuncio = resultSet.getInt("vigencia_anuncio");
                int costoAnuncio = resultSet.getInt("costo_anuncio");
                int costoOcultacion = resultSet.getInt("costo_ocultacion");
                String nombreAnunciador = resultSet.getString("nombre_usuario");
                
                
                anuncio = new Anuncio(idAnuncio, tipoAnuncio, estadoAnuncio, vigenciaAnuncio, costoAnuncio, costoOcultacion);
                anuncio.setNombreUsuario(nombreAnunciador);

//                anuncios.add(new Anuncio(idAnuncio, tipoAnuncio, estadoAnuncio, vigenciaAnuncio, costoAnuncio, costoOcultacion));
                anuncios.add(anuncio);

            }
            resultSet.close();
            comando.close();

        } catch (SQLException e) {
            System.out.println("Error al extraer los anuncios por mostrar");
            e.printStackTrace();
        }

        if (!anuncios.isEmpty()) {
            return anuncios;
        } else {
            return null;
        }
        
        
    }

    public int obtenerAnuncioInt(String vigenciaAnuncio) {

        int intVigenciaAnuncio = 0;
        switch (vigenciaAnuncio) {
            case "UNDIA":
                intVigenciaAnuncio = 1;
                break;
            case "TRESDIAS":
                intVigenciaAnuncio = 3;
                break;
            case "UNASEMANA":
                intVigenciaAnuncio = 7;
                break;
            case "DOSSEMANAS":
                intVigenciaAnuncio = 14;
                break;
            default:
                break;
        }
        return intVigenciaAnuncio;
    }

    public boolean modificarAnuncio(String tipoAnuncio, String vigenciaAnuncio, int costoAnuncio, int costoOcultacion, int idAnuncio) {
        int intVigenciaAnuncio = obtenerAnuncioInt(vigenciaAnuncio);

        try {
            PreparedStatement comando = connection.prepareStatement("UPDATE anuncio set tipo_anuncio=?, vigencia_anuncio=?, "
                    + "costo_anuncio=?, costo_ocultacion=? WHERE id_anuncio=?;");
            comando.setString(1, tipoAnuncio);
            comando.setInt(2, intVigenciaAnuncio);
            comando.setInt(3, costoAnuncio);
            comando.setInt(4, costoOcultacion);
            comando.setInt(5, idAnuncio);

            comando.execute();
            comando.close();

            return true;

        } catch (SQLException e) {
            System.out.println("Error modificando Anuncio");
            e.printStackTrace();
        }

        return false;
    }

    public boolean eliminarAnuncio(int idAnuncio) {

        try {
            PreparedStatement comando = connection.prepareStatement("DELETE FROM anuncio WHERE id_anuncio = ?;");
            comando.setInt(1, idAnuncio);

            comando.execute();
            comando.close();

            return true;

        } catch (SQLException e) {
            System.out.println("Error eliminando Anuncio");
            e.printStackTrace();
        }

        return false;
    }

    public boolean cambiarEstadoAnuncio(int idAnuncio, int estadoAnuncio) {
        if (estadoAnuncio == 0) {
            estadoAnuncio = 1;
        } else {
            estadoAnuncio = 0;
        }

        try {

            PreparedStatement comando1 = connection.prepareStatement("UPDATE anunciar1 set estado_anuncio_comprado=? WHERE id_anuncio=?;");
            comando1.setInt(1, estadoAnuncio);
            comando1.setInt(2, idAnuncio);

            comando1.execute();
            comando1.close();

            if (usuario.getRol().equals("ADMINISTRADOR")) {
                PreparedStatement comando2 = connection.prepareStatement("UPDATE anuncio set estado_anuncio=? WHERE id_anuncio=?;");
                comando2.setInt(1, estadoAnuncio);
                comando2.setInt(2, idAnuncio);

                comando2.execute();
                comando2.close();
            }

            return true;

        } catch (SQLException e) {
            System.out.println("Error cambiando estado del Anuncio");
            e.printStackTrace();
        }

        return false;
    }

    public boolean comprarAnuncioPRUEBA(int idAnuncio, java.sql.Date fechaCompra) {
        Anuncio anuncio = new Anuncio();
        try {

            PreparedStatement comando = connection.prepareStatement("select * from anuncio WHERE id_anuncio LIKE ?;");
            comando.setInt(1, idAnuncio);

            ResultSet resultSet = comando.executeQuery();

            while (resultSet.next()) {

                String tipoAnuncio = resultSet.getString("tipo_anuncio");
                int vigenciaAnuncio = resultSet.getInt("vigencia_anuncio");
                BigDecimal costoAnuncio = resultSet.getBigDecimal("costo_anuncio");
                int costoOcultacion = resultSet.getInt("costo_ocultacion");
                anuncio.setTipo(tipoAnuncio);
                anuncio.setVigenciaInt(vigenciaAnuncio);
                anuncio.setCostoAnuncioDecimal(costoAnuncio);
                anuncio.setCostoOcultacion(costoOcultacion);
                anuncio.calcularFechas(fechaCompra);

            }
            resultSet.close();
            comando.close();

        } catch (SQLException e) {
            System.out.println("Error al extraer todos los anuncios");
            e.printStackTrace();
            return false;
        }

        if (creditosSuficientes(anuncio.getCostoAnuncioDecimal())) {
            //Debe comprobar si ya caducó o no

            try {
                if (comprobarAnuncioVigente(anuncio)) {
                    //System.out.println("ANUNCIO VIGENTE");
                    PreparedStatement comando = connection.prepareStatement("INSERT INTO anunciar1 (nombre_usuario, id_anuncio, fecha_creacion, fecha_fin) VALUES (?, ?, ?, ?);");
                    comando.setString(1, usuario.getNombreUsuario());
                    comando.setInt(2, idAnuncio);
                    comando.setDate(3, anuncio.getFechaInicio());
                    comando.setDate(4, anuncio.getFechaFin());
                    comando.execute();
                    comando.close();
                } else {
                    //System.out.println("ANUNCIO NO VIGENTE");
                    PreparedStatement comando = connection.prepareStatement("INSERT INTO anunciar1 (nombre_usuario, id_anuncio, fecha_creacion, fecha_fin, caducado) VALUES (?, ?, ?, ?, ?);");
                    comando.setString(1, usuario.getNombreUsuario());
                    comando.setInt(2, idAnuncio);
                    comando.setDate(3, anuncio.getFechaInicio());
                    comando.setDate(4, anuncio.getFechaFin());
                    comando.setInt(5, 1);
                    comando.execute();
                    comando.close();
                }

                return true;
            } catch (SQLException e) {
                System.out.println("Error creando relación comprar anuncio");
                e.printStackTrace();
                return false;
            }

        }

        return false;
    }

    public boolean comprobarAnuncioVigente(Anuncio anuncio) {
        java.sql.Date fechaActual = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        //d.after(min) && d.before(max);
        return fechaActual.after(anuncio.getFechaInicio()) && fechaActual.before(anuncio.getFechaFin());
    }

    public boolean creditosSuficientes(BigDecimal totalDescontar) {

        extraerCreditos();
        if (usuario.getCartera().compareTo(totalDescontar) >= 0) {
            usuario.setCartera(usuario.getCartera().subtract(totalDescontar));
            descontarCreditos();
            return true;
        }

        return false;

    }

    private void descontarCreditos() {

        try {
            PreparedStatement comando = connection.prepareStatement("UPDATE usuario set cartera=? WHERE nombre_usuario LIKE ?;");

            comando.setBigDecimal(1, usuario.getCartera());
            comando.setString(2, usuario.getNombreUsuario());

            System.out.println(comando.toString());
            comando.execute();

            comando.close();
        } catch (SQLException e) {
            System.out.println("Error descontando créditos");
            e.printStackTrace();
        }
    }

    public void registrarFechaTransaccion() {
        LocalDateTime now = LocalDateTime.now();
        fechaTransaccion = java.sql.Date.valueOf(now.toLocalDate());

    }

//    public boolean comprarAnuncio(String tipoAnuncio, String vigenciaAnuncio, java.sql.Date fechaCompra, HttpServletRequest request) {
    //Nombre usuario, tipo anuncio, anuncio activado = true, fecha inicio y fecha fin
//        int intVigenciaAnuncio = 0;
//
//        switch (vigenciaAnuncio) {
//            case "UNDIA":
//                intVigenciaAnuncio = 1;
//                break;
//            case "TRESDIAS":
//                intVigenciaAnuncio = 3;
//                break;
//            case "UNASEMANA":
//                intVigenciaAnuncio = 7;
//                break;
//            case "DOSSEMANAS":
//                intVigenciaAnuncio = 14;
//                break;
//            default:
//                break;
//        }
//
//        Anuncio anuncio = new Anuncio(tipoAnuncio);
//        anuncio.calcularFechas(vigenciaAnuncio, fechaCompra);
//
//        String comando = "INSERT INTO anunciar (nombre_usuario, tipo_anuncio, estado_anuncio, vigencia_anuncio, fecha_creacion, fecha_fin) " + "VALUES ('"
//                + usuario.getNombreUsuario() + "', '" + anuncio.getTipo() + "', '" + 0 + "', '" + intVigenciaAnuncio + "', '"
//                + anuncio.getFechaInicio() + "', '" + anuncio.getFechaFin() + "');";
//
//        System.out.println("Comando relacion comprar anuncio: " + comando);
//        if (anuncioNoComprado(tipoAnuncio, intVigenciaAnuncio, request)) {
//            if (creditosSuficientes(tipoAnuncio, vigenciaAnuncio, request)) {
//
//                try {
//                    Statement statementInsert = connection.createStatement();
//                    statementInsert = connection.createStatement();
//                    statementInsert.executeUpdate(comando);
//                    request.setAttribute("mensaje", "ANUNCIO COMPRADO EXITOSAMENTE");
//                    
//                    statementInsert.close();
//                    return true;
//                } catch (SQLException e) {
//                    System.out.println("Error creando relación comprar anuncio");
//                    e.printStackTrace();
//                }
//
//            }
//        } else {
//            request.setAttribute("mensaje", "ANUNCIO YA COMPRADO");
//        }
//        return false;
//    }
//
//    private boolean anuncioNoComprado(String tipoAnuncio, int intVigenciaAnuncio, HttpServletRequest request) {
//        String comando = "SELECT tipo_anuncio, caducado, vigencia_anuncio FROM anunciar WHERE (nombre_usuario=\"" + usuario.getNombreUsuario() + "\" "
//                + "AND tipo_anuncio=\"" + tipoAnuncio + "\" AND caducado=0 AND vigencia_anuncio=" + intVigenciaAnuncio + ");";
//        System.out.println(comando);
//
//        try {
//            Statement statementInsert = connection.createStatement();
//            ResultSet resultSet = statementInsert.executeQuery(comando);
//            if (!resultSet.next()) {
//                System.out.println("Anuncio no comprado");
//
//                resultSet.close();
//
//                statementInsert.close();
//
//                return true;
//
//            }
//
//            resultSet.close();
//
//            statementInsert.close();
//
//        } catch (SQLException e) {
//            System.out.println("Error encontrando Creditos");
//            e.printStackTrace();
//        }
//        System.out.println("Anuncio comprado");
//        return false;
//    }
//
//    private boolean creditosSuficientes(String tipoAnuncio, String vigenciaAnuncio) {
//
//        BigDecimal totalDescontar = new BigDecimal(0);
//
//        switch (tipoAnuncio) {
//            case "TEXTO":
//                totalDescontar = totalDescontar.add(new BigDecimal(10));
//                break;
//            case "TEXTOeIMAGEN":
//                totalDescontar = totalDescontar.add(new BigDecimal(20));
//                break;
//            case "VIDEO":
//                totalDescontar = totalDescontar.add(new BigDecimal(30));
//                break;
//            default:
//                break;
//        }
//
//        switch (vigenciaAnuncio) {
//            case "UNDIA":
//                totalDescontar = totalDescontar.add(new BigDecimal(10));
//                break;
//            case "TRESDIAS":
//                totalDescontar = totalDescontar.add(new BigDecimal(25));
//                break;
//            case "UNASEMANA":
//                totalDescontar = totalDescontar.add(new BigDecimal(50));
//                break;
//            case "DOSSEMANAS":
//                totalDescontar = totalDescontar.add(new BigDecimal(90));
//                break;
//            default:
//                break;
//        }
//
//        if (usuario.getCartera().compareTo(totalDescontar) >= 0) {
//            usuario.setCartera(usuario.getCartera().subtract(totalDescontar));
//            descontarCreditos();
//            return true;
//        }
//
//        return false;
//
//    }
}
