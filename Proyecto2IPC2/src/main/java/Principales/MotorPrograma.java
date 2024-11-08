package Principales;

import Proceso.Publicacion;
import Proceso.Reaccion;
import Proceso.Suscripcion;
import Usuarios.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class MotorPrograma {

    //private static MotorPrograma UNICA_INSTANCIA_DE_MOTOR_PROGRAMA;
    private Usuario usuario;
    private Connection connection;
    private Tienda tienda;
    private GestorAnuncios gestorAnuncios;
    private Revista[] revistas;
    private Revista[] revistasSuscritas;
    private boolean usuarioYaReacciono;
    private GestorReportes gestorReportes;
    private SQL sql;

    public MotorPrograma(String nombreUsuario) {

        this.sql = SQL.getInstance();
        this.connection = sql.getConnection();

        this.usuario = new Usuario();
        this.usuario.setNombreUsuario(nombreUsuario);
        this.gestorAnuncios = new GestorAnuncios(usuario.getNombreUsuario(), connection);
        this.gestorReportes = new GestorReportes(connection);

    }

    public void closeConnection() {
        //System.out.println("Cerrando conexión");
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(MotorPrograma.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Tienda getTienda() {
        return tienda;
    }

    public GestorAnuncios getGestorAnuncios() {
        return gestorAnuncios;
    }

    public Revista[] getRevistas() {

        return revistas;
    }

    public void setRevistas(Revista[] revistas) {
        this.revistas = revistas;
    }

    public Revista[] getRevistasSuscritas() {
        return revistasSuscritas;
    }

    public void setRevistasSuscritas(Revista[] revistasSuscritas) {
        this.revistasSuscritas = revistasSuscritas;
    }

    public GestorReportes getGestorReportes() {
        return gestorReportes;
    }

    public void setGestorReportes(GestorReportes gestorReportes) {
        this.gestorReportes = gestorReportes;
    }

//    public static MotorPrograma getInstance(String nombreUsuario) {
//        if (UNICA_INSTANCIA_DE_MOTOR_PROGRAMA == null) {
//            UNICA_INSTANCIA_DE_MOTOR_PROGRAMA = new MotorPrograma(nombreUsuario);
//        }
//
//        return UNICA_INSTANCIA_DE_MOTOR_PROGRAMA;
//    }
    public Usuario extraerDatosUsuario() {

        try {
            PreparedStatement comando = connection.prepareStatement("SELECT * FROM usuario WHERE nombre_usuario like ?");
            comando.setString(1, usuario.getNombreUsuario());

            //Statement statementInsert = connection.createStatement();
            ResultSet resultSet = comando.executeQuery();
            if (resultSet.next()) {
                usuario.setContraseña(resultSet.getString("contraseña").replace("界", ""));
                usuario.setFoto(resultSet.getString("foto"));
                usuario.setHobbies(resultSet.getString("hobbies"));
                usuario.setDescripcion(resultSet.getString("descripcion"));
                usuario.setGustos(resultSet.getString("gustos"));
                usuario.setRol(resultSet.getString("rol"));

            }
            resultSet.close();
            comando.close();
        } catch (SQLException e) {
            System.out.println("Error al extraer los datos del usuario para editarlos");
            e.printStackTrace();
        }

        return usuario;
    }

    public void extraerRolUsuario() {

        try {
            PreparedStatement comando = connection.prepareStatement("SELECT * FROM usuario WHERE nombre_usuario like ?");
            comando.setString(1, usuario.getNombreUsuario());

            //Statement statementInsert = connection.createStatement();
            ResultSet resultSet = comando.executeQuery();
            if (resultSet.next()) {
                usuario.setRol(resultSet.getString("rol"));

            }
            resultSet.close();
            comando.close();
        } catch (SQLException e) {
            System.out.println("Error al extraer rol del usuario");
            e.printStackTrace();
        }

    }

    public boolean verificarNombreRegistrado(String nombreUsuario) {
        //String comandoNombre = "SELECT * FROM usuario WHERE nombre_usuario like \"" + nombreUsuario + "\"";

        try {

            PreparedStatement comando = connection.prepareStatement("SELECT * FROM usuario WHERE nombre_usuario like ?");
            comando.setString(1, nombreUsuario);
            ResultSet resultSet = comando.executeQuery();
            if (resultSet.next()) {
                return true;

            }
            resultSet.close();
            comando.close();
//            Statement statementInsert = connection.createStatement();
//            ResultSet resultSet = statementInsert.executeQuery(comandoNombre);
//            //Existe el numero de tarjeta
//            if (resultSet.next()) {
//                return true;
//
//            }

        } catch (Exception e) {
            System.out.println("Error en verificar el nombre registrado");
            e.printStackTrace();
        }
        //No existe el registro o hubo un error en la base de datos
        //System.out.println("El usuario no existe");
        return false;
    }

    public void actualizarDatosUsuario(String nombreUsuario, String contraseña, String rol, String foto, String hobbies, String descripcion, String gustos) {

        if (foto.equals("undefined")) {
            foto = "";
        }

        if (!contraseña.equals("undefined")) {
            contraseña = sql.convertirAHash(contraseña);

            String comandoCrearUsuario = "UPDATE usuario set nombre_usuario= ?, contraseña=?, rol=?, foto=?, hobbies=?, descripcion=?, gustos=? "
                    + "WHERE nombre_usuario LIKE ?;";

            // System.out.println("COmando: " + comandoCrearUsuario);
            try {

                PreparedStatement comando = connection.prepareStatement(comandoCrearUsuario);
                comando.setString(1, nombreUsuario);
                comando.setString(2, contraseña);
                comando.setString(3, rol);
                comando.setString(4, foto);
                comando.setString(5, hobbies);
                comando.setString(6, descripcion);
                comando.setString(7, gustos);
                comando.setString(8, this.usuario.getNombreUsuario());
                comando.execute();
                comando.close();

                this.usuario.setNombreUsuario(nombreUsuario);
            } catch (SQLException e) {
                System.out.println("Error actualizando datos al editar perfil");
                e.printStackTrace();
            }
        } else {

            String comandoCrearUsuario = "UPDATE usuario set nombre_usuario= ?, rol=?, foto=?, hobbies=?, descripcion=?, gustos=? "
                    + "WHERE nombre_usuario LIKE ?;";

            try {

                PreparedStatement comando = connection.prepareStatement(comandoCrearUsuario);
                comando.setString(1, nombreUsuario);
                comando.setString(2, rol);
                comando.setString(3, foto);
                comando.setString(4, hobbies);
                comando.setString(5, descripcion);
                comando.setString(6, gustos);
                comando.setString(7, this.usuario.getNombreUsuario());
                comando.execute();
                comando.close();

                this.usuario.setNombreUsuario(nombreUsuario);
            } catch (SQLException e) {
                System.out.println("Error actualizando datos al editar perfil");
                e.printStackTrace();
            }
        }

    }

    public void comprar() {
        tienda = new Tienda(usuario, connection);
    }

    public void publicar(String archivoRevista, String descripcion, String categoria, String tags, String fechaPublicacion) {
        Publicacion publicacion = new Publicacion(usuario, connection);
        publicacion.setFechaProceso(formatoFechaAdecuado(fechaPublicacion));
        //publicacion.registrarFechaRealizacion();
        publicacion.publicar(archivoRevista, descripcion, categoria, tags);
    }

    public boolean suscribir(int numeroRevista, String fechaSuscripcion, BigDecimal costoSuscripcion, Tienda tienda) {
        Suscripcion suscripcion = new Suscripcion(usuario, connection);
        Revista revista = new Revista(numeroRevista);
        suscripcion.setRevista(revista);

        if (suscripcion.suscribir(formatoFechaAdecuado(fechaSuscripcion), costoSuscripcion, tienda)) {
            return true;
        } else {
            return false;
        }

    }

    public void darMeGusta(int numeroRevista/*, int numeroMeGustas*/) {
        Reaccion reaccion = new Reaccion(usuario, connection);

        Revista revista = new Revista(numeroRevista);
        reaccion.darMeGusta(revista/*, numeroMeGustas*/);

    }
    
    public boolean definirPrecioRevista(int numeroRevista, int costoRevista) {
        
        String comandoString = "UPDATE revista set costo = ?, estado_costodefinido = 1 WHERE numero_revista LIKE ?;";
        try {
            
            PreparedStatement comando = connection.prepareStatement(comandoString);
            comando.setInt(1, costoRevista);
            comando.setInt(2, numeroRevista);
            
//            Statement statementInsert = connection.createStatement();
//            statementInsert = connection.createStatement();
//            statementInsert.executeUpdate(comando);
            comando.execute();
            comando.close();
            
            return true;
        } catch (SQLException e) {
            System.out.println("Error definiendo precio revista");
            e.printStackTrace();
            return false;
        }
    }

    public boolean comentar(int numeroRevista, String comentario) {
        Reaccion reaccion = new Reaccion(usuario, connection);

        Revista revista = new Revista(numeroRevista);

        if (reaccion.comentar(revista, comentario)) {
            return true;
        }

        return false;

    }

    public java.sql.Date formatoFechaAdecuado(String fechaMov) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date;
        java.sql.Date fechaFormatoSQL = null;

        String[] fechaSeparada = fechaMov.split("-");
        fechaMov = fechaSeparada[2] + "-" + fechaSeparada[1] + "-" + fechaSeparada[0];

        try {
            //fechaMov = fechaMov.replace("/", "-");
            date = dateFormat.parse(fechaMov);
            fechaFormatoSQL = new java.sql.Date(date.getTime());
            //System.out.println("Fecha SQL:  " + fechaFormatoSQL + " fechaHTML: " + fechaMov);

        } catch (Exception e) {
            System.out.println("Error conviertiendo fecha HTML a sql.Date");
            e.printStackTrace();
        }

        return fechaFormatoSQL;
    }

    public Object obtenerEtiquetas() {

        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean hayRevistasCreadas() {
        String comandoNombre = "SELECT * FROM publicar;";
        try {

            PreparedStatement comando = connection.prepareStatement(comandoNombre);
            ResultSet resultSet = comando.executeQuery();

            if (resultSet.next()) {
                obtenerRevistas();
                return true;

            }
            resultSet.close();
            comando.close();
//            Statement statementInsert = connection.createStatement();
//            ResultSet resultSet = statementInsert.executeQuery(comandoNombre);
//            if (resultSet.next()) {
//                obtenerRevistas();
//                return true;
//
//            }
        } catch (SQLException e) {
            System.out.println("Error al verificar si hay revistas publicadas");
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean hayRevistasCreadas2() {
        String comandoNombre = "SELECT * FROM publicar;";
        try {

            PreparedStatement comando = connection.prepareStatement(comandoNombre);
            ResultSet resultSet = comando.executeQuery();

            if (resultSet.next()) {
                return true;

            }
            resultSet.close();
            comando.close();
//            Statement statementInsert = connection.createStatement();
//            ResultSet resultSet = statementInsert.executeQuery(comandoNombre);
//            if (resultSet.next()) {
//                obtenerRevistas();
//                return true;
//
//            }
        } catch (SQLException e) {
            System.out.println("Error al verificar si hay revistas publicadas");
            e.printStackTrace();
        }
        return false;
    }
    
    public ArrayList<Revista> obtenerRevistas2() {

        this.revistas = new Revista[obtenerTotalRevistasCreadas("SELECT * FROM publicar;")];
        
        ArrayList<Revista> revistasSinPrecio = new ArrayList<>();

        String comandoNombre = "select * from revista LEFT JOIN publicar ON revista.numero_revista = publicar.numero_revista WHERE estado_costodefinido = 0;";
        
        //
        try {

            PreparedStatement comando = connection.prepareStatement(comandoNombre);
            ResultSet resultSet = comando.executeQuery();

            int contador = 0;

            while (resultSet.next()) {
                int numeroRevista = resultSet.getInt("numero_revista");
                String descripcion = resultSet.getString("descripcion");
                String nombreAutor = resultSet.getString("nombre_usuario");
                String categoria = resultSet.getString("categoria");
                String etiquetas = resultSet.getString("etiquetas");
                boolean estadoSuscripcion;
                boolean estadoComentarios;
                boolean estadoMeGustas;
                int intEstadoSuscripcion = resultSet.getInt("estado_suscripcion");
                if (intEstadoSuscripcion == 0) {
                    estadoSuscripcion = false;
                } else {
                    estadoSuscripcion = true;
                }
                int intEstadoComentarios = resultSet.getInt("estado_comentarios");
                if (intEstadoComentarios == 0) {
                    estadoComentarios = false;
                } else {
                    estadoComentarios = true;
                }
                int intEstadoMeGustas = resultSet.getInt("estado_megustas");
                if (intEstadoMeGustas == 0) {
                    estadoMeGustas = false;
                } else {
                    estadoMeGustas = true;
                }
                int meGustas = resultSet.getInt("me_gustas");

                revistasSinPrecio.add(new Revista(numeroRevista, estadoSuscripcion, estadoComentarios, estadoMeGustas, descripcion, nombreAutor, categoria, etiquetas, meGustas));
                contador++;
            }
            resultSet.close();
            comando.close();
            
            return revistasSinPrecio;

        } catch (SQLException e) {
            System.out.println("Error al extraer las revistas");
            e.printStackTrace();
        }

        return null;

    }

    private int obtenerTotalRevistasCreadas(String comando) {
        int totalRevistas = 0;

        //
        try {

            PreparedStatement comandoSQL = connection.prepareStatement(comando);
            ResultSet resultSet = comandoSQL.executeQuery();
            while (resultSet.next()) {
                totalRevistas++;
            }
            resultSet.close();

            comandoSQL.close();

//            Statement statementInsert = connection.createStatement();
//            ResultSet resultSet = statementInsert.executeQuery(comando);
//            while (resultSet.next()) {
//                totalRevistas++;
//            }
        } catch (SQLException e) {
            System.out.println("Error al contar total de revistas");
            e.printStackTrace();
        }

        return totalRevistas;
    }

    public void obtenerRevistas() {

        this.revistas = new Revista[obtenerTotalRevistasCreadas("select * from revista LEFT JOIN publicar ON revista.numero_revista = publicar.numero_revista WHERE estado_costodefinido = 1;")];

        String comandoNombre = "select * from revista LEFT JOIN publicar ON revista.numero_revista = publicar.numero_revista WHERE estado_costodefinido = 1;";

        //
        try {

            PreparedStatement comando = connection.prepareStatement(comandoNombre);
            ResultSet resultSet = comando.executeQuery();

            int contador = 0;

            while (resultSet.next()) {
                Revista revista = null;
                int numeroRevista = resultSet.getInt("numero_revista");
                int costoRevsita = resultSet.getInt("costo");
                String descripcion = resultSet.getString("descripcion");
                String nombreAutor = resultSet.getString("nombre_usuario");
                String categoria = resultSet.getString("categoria");
                String etiquetas = resultSet.getString("etiquetas");
                boolean estadoSuscripcion;
                boolean estadoComentarios;
                boolean estadoMeGustas;
                int intEstadoSuscripcion = resultSet.getInt("estado_suscripcion");
                if (intEstadoSuscripcion == 0) {
                    estadoSuscripcion = false;
                } else {
                    estadoSuscripcion = true;
                }
                int intEstadoComentarios = resultSet.getInt("estado_comentarios");
                if (intEstadoComentarios == 0) {
                    estadoComentarios = false;
                } else {
                    estadoComentarios = true;
                }
                int intEstadoMeGustas = resultSet.getInt("estado_megustas");
                if (intEstadoMeGustas == 0) {
                    estadoMeGustas = false;
                } else {
                    estadoMeGustas = true;
                }
                int meGustas = resultSet.getInt("me_gustas");
                
                revista = new Revista(numeroRevista, estadoSuscripcion, estadoComentarios, estadoMeGustas, descripcion, nombreAutor, categoria, etiquetas, meGustas);
                revista.setCostoSuscripcion(costoRevsita);

                revistas[contador] = revista;
                contador++;
            }
            resultSet.close();
            comando.close();

        } catch (SQLException e) {
            System.out.println("Error al extraer las revistas");
            e.printStackTrace();
        }

        if (revistas != null) {
            obtenerEstadoSuscripcion();
        }

    }

    public ArrayList<Revista> obtenerRevistasPublicadas(String nombreEditor) {
        String comandoRevistasSuscritas = "select * from revista LEFT JOIN publicar ON revista.numero_revista = publicar.numero_revista WHERE nombre_usuario LIKE ?;";
        ArrayList<Revista> revistasPublicadas = new ArrayList<>();
        int contador = 0;
        //
        try {

            PreparedStatement comando = connection.prepareStatement(comandoRevistasSuscritas);
            comando.setString(1, nombreEditor);
            ResultSet resultSet = comando.executeQuery();

            while (resultSet.next()) {
                contador++;
                int numeroRevista = resultSet.getInt("numero_revista");
                String descripcion = resultSet.getString("descripcion");
                String nombreAutor = resultSet.getString("nombre_usuario");
                String categoria = resultSet.getString("categoria");
                String etiquetas = resultSet.getString("etiquetas");
                int meGustas = resultSet.getInt("me_gustas");
                boolean estadoSuscripcion;
                boolean estadoComentarios;
                boolean estadoMeGustas;
                int intEstadoSuscripcion = resultSet.getInt("estado_suscripcion");
                if (intEstadoSuscripcion == 0) {
                    estadoSuscripcion = false;
                } else {
                    estadoSuscripcion = true;
                }
                int intEstadoComentarios = resultSet.getInt("estado_comentarios");
                if (intEstadoComentarios == 0) {
                    estadoComentarios = false;
                } else {
                    estadoComentarios = true;
                }
                int intEstadoMeGustas = resultSet.getInt("estado_megustas");
                if (intEstadoMeGustas == 0) {
                    estadoMeGustas = false;
                } else {
                    estadoMeGustas = true;
                }

                System.out.println("Estados: ");

                revistasPublicadas.add(new Revista(numeroRevista, estadoSuscripcion, estadoComentarios, estadoMeGustas, descripcion, nombreAutor, categoria, etiquetas, meGustas));

            }
            resultSet.close();
            comando.close();

        } catch (SQLException e) {
            System.out.println("Error al extraer las revistas");
            e.printStackTrace();
        }

        if (contador != 0) {
            return revistasPublicadas;
        } else {
            return null;
        }

    }

    private void obtenerEstadoSuscripcion() {
        System.out.println("PRUEBA1");
        for (Revista revista : revistas) {
            System.out.println(revista.getNumeroRevista());
        }
        System.out.println("PRUEBA2");

        for (Revista revista : revistas) {
            try {
                revista.getNumeroRevista();
            } catch (Exception e) {
                continue;
            }
            
            String comandoNombre = "select * from suscribir WHERE nombre_usuario LIKE \"" + usuario.getNombreUsuario() + "\" AND numero_revista =" + revista.getNumeroRevista() + ";";
            try {
                Statement statementInsert = connection.createStatement();
                ResultSet resultSet = statementInsert.executeQuery(comandoNombre);

                if (resultSet.next()) {
                    revista.setUsuarioSuscrito(true);

                } else {
                    revista.setUsuarioSuscrito(false);
                }
                resultSet.close();
                statementInsert.close();
            } catch (SQLException e) {
                System.out.println("Error comprobar el estado de suscripcion a las revistas");
                e.printStackTrace();
            }
        }
    }

    public void obtenerRevistasSuscritas() {
        String comando = "select * from suscribir WHERE nombre_usuario LIKE \"" + usuario.getNombreUsuario() + "\";";

        revistasSuscritas = new Revista[obtenerTotalRevistasCreadas(comando)];

        String comandoNombre = "select * from revista LEFT JOIN suscribir ON revista.numero_revista = suscribir.numero_revista "
                + "WHERE nombre_usuario LIKE \"" + usuario.getNombreUsuario() + "\";";

        try {
            Statement statementInsert = connection.createStatement();
            ResultSet resultSet = statementInsert.executeQuery(comandoNombre);

            int contador = 0;

            while (resultSet.next()) {
                int numeroRevista = resultSet.getInt("numero_revista");
                String descripcion = resultSet.getString("descripcion");
                String nombreAutor = resultSet.getString("nombre_usuario");
                String categoria = resultSet.getString("categoria");
                String etiquetas = resultSet.getString("etiquetas");
                int meGustas = resultSet.getInt("me_gustas");
                boolean estadoSuscripcion;
                boolean estadoComentarios;
                boolean estadoMeGustas;

                int intEstadoSuscripcion = resultSet.getInt("estado_suscripcion");
                if (intEstadoSuscripcion == 0) {
                    estadoSuscripcion = false;
                } else {
                    estadoSuscripcion = true;
                }
                int intEstadoComentarios = resultSet.getInt("estado_comentarios");
                if (intEstadoComentarios == 0) {
                    estadoComentarios = false;
                } else {
                    estadoComentarios = true;
                }
                int intEstadoMeGustas = resultSet.getInt("estado_megustas");
                if (intEstadoMeGustas == 0) {
                    estadoMeGustas = false;
                } else {
                    estadoMeGustas = true;
                }

                revistasSuscritas[contador] = new Revista(numeroRevista, estadoSuscripcion, estadoComentarios, estadoMeGustas, descripcion, nombreAutor, categoria, etiquetas, meGustas);
                revistasSuscritas[contador].setUsuarioSuscrito(true);
                contador++;
            }

            resultSet.close();
            statementInsert.close();

        } catch (SQLException e) {
            System.out.println("Error al extraer las revistas");
            e.printStackTrace();
        }

        if (revistasSuscritas != null) {
            obtenerEstadoMeGusta();
        }

    }

    private void obtenerEstadoMeGusta() {

        for (Revista revista : revistasSuscritas) {
            String comandoNombre = "select * from megusta WHERE nombre_usuario LIKE \"" + usuario.getNombreUsuario() + "\" AND numero_revista =" + revista.getNumeroRevista() + ";";

            try {
                Statement statementInsert = connection.createStatement();
                ResultSet resultSet = statementInsert.executeQuery(comandoNombre);

                if (resultSet.next()) {
                    revista.setUsuarioYaMeGusta(true);

                } else {
                    revista.setUsuarioYaMeGusta(false);
                }

                resultSet.close();
                statementInsert.close();
            } catch (SQLException e) {
                System.out.println("Error comprobar el estado de suscripcion a las revistas");
                e.printStackTrace();
            }
        }
    }

    public Revista obtenerRevistaIndividual(int numeroRevistaFE) {

//        String comandoNombre = "select * from revista LEFT JOIN suscribir ON revista.numero_revista = suscribir.numero_revista "
//                + "WHERE nombre_usuario LIKE \"" + usuario.getNombreUsuario() + "\";";
        Revista revistaIndividual = null;

        try {
            
            PreparedStatement comando = connection.prepareStatement("select * from revista LEFT JOIN suscribir ON "
                    + "revista.numero_revista = suscribir.numero_revista WHERE nombre_usuario LIKE ? AND revista.numero_revista LIKE ?;");
            
            comando.setString(1, usuario.getNombreUsuario());
            comando.setInt(2, numeroRevistaFE);
            
            ResultSet resultSet = comando.executeQuery();
            
//            Statement statementInsert = connection.createStatement();
//            ResultSet resultSet = statementInsert.executeQuery(comandoNombre);

            if (resultSet.next()) {
                int numeroRevista = resultSet.getInt("numero_revista");
                String descripcion = resultSet.getString("descripcion");
                String nombreAutor = resultSet.getString("nombre_usuario");
                String categoria = resultSet.getString("categoria");
                String etiquetas = resultSet.getString("etiquetas");
                int meGustas = resultSet.getInt("me_gustas");
                boolean estadoSuscripcion;
                boolean estadoComentarios;
                boolean estadoMeGustas;

                int intEstadoSuscripcion = resultSet.getInt("estado_suscripcion");
                if (intEstadoSuscripcion == 0) {
                    estadoSuscripcion = false;
                } else {
                    estadoSuscripcion = true;
                }
                int intEstadoComentarios = resultSet.getInt("estado_comentarios");
                if (intEstadoComentarios == 0) {
                    estadoComentarios = false;
                } else {
                    estadoComentarios = true;
                }
                int intEstadoMeGustas = resultSet.getInt("estado_megustas");
                if (intEstadoMeGustas == 0) {
                    estadoMeGustas = false;
                } else {
                    estadoMeGustas = true;
                }

                revistaIndividual = new Revista(numeroRevista, estadoSuscripcion, estadoComentarios, estadoMeGustas, descripcion, nombreAutor, categoria, etiquetas, meGustas);
                revistaIndividual.setUsuarioSuscrito(true);

            }

            resultSet.close();
            comando.close();
            //statementInsert.close();

        } catch (SQLException e) {
            System.out.println("Error al extraer la revista Individual");
            e.printStackTrace();
        }

        if (revistaIndividual != null) {
            revistaIndividual = obtenerEstadoMeGustaIndividual(revistaIndividual);
        }

        return revistaIndividual;

    }

    private Revista obtenerEstadoMeGustaIndividual(Revista revista) {

        //String comandoNombre = "select * from megusta WHERE nombre_usuario LIKE \"" + usuario.getNombreUsuario() + "\" AND numero_revista =" + revista.getNumeroRevista() + ";";

        try {
            
            PreparedStatement comando = connection.prepareStatement("select * from megusta WHERE nombre_usuario LIKE ? AND numero_revista =?;");
            comando.setString(1, usuario.getNombreUsuario());
            comando.setInt(2, revista.getNumeroRevista());
            
            ResultSet resultSet = comando.executeQuery();
            
//            Statement statementInsert = connection.createStatement();
//            ResultSet resultSet = statementInsert.executeQuery(comandoNombre);

            if (resultSet.next()) {
                revista.setUsuarioYaMeGusta(true);

            } else {
                revista.setUsuarioYaMeGusta(false);
            }

            resultSet.close();
            comando.close();
        } catch (SQLException e) {
            System.out.println("Error comprobar el estado de me gusta en la revista individual");
            e.printStackTrace();
        }
        
        return revista;
    }

    public Revista[] obtenerRevistasFiltradas(String categoriaPorFiltrar) {
        Revista[] revistasFiltradas;

        int revistasPorFiltrar = 0;

        if (revistasSuscritas != null) {

            for (Revista revista : revistasSuscritas) {
                if (revista.getCategoria().equals(categoriaPorFiltrar)) {
                    revistasPorFiltrar++;
                }
            }
            revistasFiltradas = new Revista[revistasPorFiltrar];

            int contador = 0;
            for (Revista revista : revistasSuscritas) {
                if (revista.getCategoria().equals(categoriaPorFiltrar)) {
                    revistasFiltradas[contador] = revista;
                    contador++;
                }
            }

            return revistasFiltradas;
        }
        return null;

    }

    public Usuario obtenerAutor(String nombreAutor) {
        //String comandoNombre = "SELECT * FROM usuario WHERE nombre_usuario like \"" + nombreAutor + "\"";

        Usuario autor = new Usuario();

        try {

            PreparedStatement comando = connection.prepareStatement("SELECT * FROM usuario WHERE nombre_usuario like ?");
            comando.setString(1, nombreAutor);
            ResultSet resultSet = comando.executeQuery();

            if (resultSet.next()) {

                autor.setNombreUsuario(nombreAutor);
                autor.setFoto(resultSet.getString("foto"));
                autor.setHobbies(resultSet.getString("hobbies"));
                autor.setDescripcion(resultSet.getString("descripcion"));
                autor.setGustos(resultSet.getString("gustos"));

            }

            resultSet.close();
            comando.close();

        } catch (SQLException e) {
            System.out.println("Error al extraer los datos del autor");
            e.printStackTrace();
        }

        return autor;
    }

    public boolean cambiarEstadoGestion(int numeroRevista, int estadoPorCambiar, String procesoPorCambiar) {

        String comandoEstado = "";

        if (procesoPorCambiar.equals("Suscripciones")) {
            comandoEstado = "UPDATE revista set estado_suscripcion = ? WHERE numero_revista LIKE ?;";
        } else if (procesoPorCambiar.equals("Comentarios")) {
            comandoEstado = "UPDATE revista set estado_comentarios = ? WHERE numero_revista LIKE ?;";
        } else if (procesoPorCambiar.equals("MeGustas")) {
            comandoEstado = "UPDATE revista set estado_megustas = ? WHERE numero_revista LIKE ?;";
        }

        System.out.println(comandoEstado);

        // System.out.println("COmando: " + comandoCrearUsuario);
        try {
            PreparedStatement comando = connection.prepareStatement(comandoEstado);
            comando.setInt(1, estadoPorCambiar);
            comando.setInt(2, numeroRevista);
            System.out.println(comando.toString());
            comando.execute();
            comando.close();

            return true;
        } catch (SQLException e) {
            System.out.println("Error actualizando datos al cambiar estado de las gestiones");
            e.printStackTrace();
            return false;
        }
    }
}
