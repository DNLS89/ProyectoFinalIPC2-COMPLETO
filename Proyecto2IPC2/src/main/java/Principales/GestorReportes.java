package Principales;

import Anuncios.Anuncio;
import jakarta.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class GestorReportes {

    //private Usuario usuario;
    private Connection connection;
    //private Revista revistaIngresada;
    private Revista[] revistasYElementos;
    private Anuncio[] anuncios;
    private Revista[] revistasFiltradas;
    private Revista[] revistasCon5Elementos;
    private java.sql.Date fechaInicio;
    private java.sql.Date fechaFin;

    public GestorReportes(Connection connection) {
        this.connection = connection;
    }

    public Revista[] getRevistasYElementos() {
        return revistasYElementos;
    }

    public Revista[] getRevistasFiltradas() {
        return revistasFiltradas;
    }

    public void setRevistasFiltradas(Revista[] revistasFiltradas) {
        this.revistasFiltradas = revistasFiltradas;
    }

    public void setRevistasYElementos(Revista[] revistasYElementos) {
        this.revistasYElementos = revistasYElementos;
    }

    public Anuncio[] getAnuncios() {
        return anuncios;
    }

    public void setAnuncios(Anuncio[] anuncios) {
        this.anuncios = anuncios;
    }

    public ArrayList<Revista> extraeRevistasConComentarios(String nombrePublicador) {
        String comandoRevistasSuscritas = "select * from comentar LEFT JOIN publicar ON comentar.numero_revista = publicar.numero_revista WHERE publicar.nombre_usuario LIKE ?;";
        ArrayList<Revista> revistasConComentarios = new ArrayList<>();
        //
        try {

            PreparedStatement comando = connection.prepareStatement(comandoRevistasSuscritas);
            comando.setString(1, nombrePublicador);
            ResultSet resultSet = comando.executeQuery();


            while (resultSet.next()) {

                int numeroRevista = resultSet.getInt("numero_revista");
                
                
                Revista revista = new Revista(numeroRevista);

                String usuarioQueComento = resultSet.getString("comentar.nombre_usuario");
                revista.setUsuarioQueComento(usuarioQueComento);
                
                String numeroRevistaString = resultSet.getString("numero_revista");
                revista.setNumeroRevistaString(numeroRevistaString);

                String comentario = resultSet.getString("comentario");
                revista.setComentario(comentario);
                Date fechaProceso = resultSet.getDate("fecha_creacion");
                revista.setFechaProceso(fechaProceso);

                revistasConComentarios.add(revista);
                //String nombreAutor = resultSet.getString("nombre_usuario");
            }
            resultSet.close();
            comando.close();

            return revistasConComentarios;

        } catch (SQLException e) {
            System.out.println("Error al extraer las revistas con comentarios");
            e.printStackTrace();
        }

        return null;
    }
    
    public ArrayList<Revista> extraeRevistasConMeGustas(String nombrePublicador) {
        String comandoRevistasSuscritas = "select * from comentar LEFT JOIN publicar ON comentar.numero_revista = publicar.numero_revista WHERE publicar.nombre_usuario LIKE ?;";
        ArrayList<Revista> revistasConMeGustas = new ArrayList<>();
        //
        try {

            PreparedStatement comando = connection.prepareStatement(comandoRevistasSuscritas);
            comando.setString(1, nombrePublicador);
            ResultSet resultSet = comando.executeQuery();


            while (resultSet.next()) {

                int numeroRevista = resultSet.getInt("numero_revista");
                
                
                Revista revista = new Revista(numeroRevista);

                String usuarioQueDioMeGusta = resultSet.getString("nombre_usuario");
                revista.setUsuarioQueDioMeGusta(usuarioQueDioMeGusta);
                
                String numeroRevistaString = resultSet.getString("numero_revista");
                revista.setNumeroRevistaString(numeroRevistaString);

                
                Date fechaProceso = resultSet.getDate("fecha_creacion");
                System.out.println("Fecha PROECESO" + fechaProceso);
                revista.setFechaProceso(fechaProceso);

                revistasConMeGustas.add(revista);
                //String nombreAutor = resultSet.getString("nombre_usuario");
            }
            resultSet.close();
            comando.close();

            return revistasConMeGustas;

        } catch (SQLException e) {
            System.out.println("Error al extraer las revistas con me gustas");
            e.printStackTrace();
        }

        return null;
    }
    
    
 
    
    public ArrayList<Revista> extraerSuscripciones(String nombrePublicador) {
        String comandoRevistasSuscritas = "SELECT s.*, p.nombre_usuario FROM suscribir s JOIN publicar p ON s.numero_revista = p.numero_revista WHERE p.nombre_usuario LIKE ? ORDER BY numero_revista;";
        ArrayList<Revista> suscripciones = new ArrayList<>();
        
        try {

            PreparedStatement comando = connection.prepareStatement(comandoRevistasSuscritas);
            comando.setString(1, nombrePublicador);
            ResultSet resultSet = comando.executeQuery();


            while (resultSet.next()) {
                int numeroRevista = resultSet.getInt("numero_revista");
                
                
                Revista revista = new Revista(numeroRevista);

                String usuarioQueSeSuscribio = resultSet.getString("nombre_usuario");
                revista.setUsuarioQueSeSuscribio(usuarioQueSeSuscribio);
                
                String numeroRevistaString = resultSet.getString("numero_revista");
                revista.setNumeroRevistaString(numeroRevistaString);

                
                Date fechaProceso = resultSet.getDate("fecha_creacion");
                revista.setFechaProceso(fechaProceso);

                suscripciones.add(revista);
                //String nombreAutor = resultSet.getString("nombre_usuario");
            }
            resultSet.close();
            comando.close();

            return suscripciones;

        } catch (SQLException e) {
            System.out.println("Error al extraer suscripciones");
            e.printStackTrace();
        }

        return null;
    }
    
    public ArrayList<Revista> extraerMeGustas(String nombrePublicador) {
        String comandoRevistasSuscritas = "SELECT m.*, p.nombre_usuario FROM megusta m JOIN publicar p ON m.numero_revista = p.numero_revista ORDER BY numero_revista;";
        ArrayList<Revista> meGustas = new ArrayList<>();
        
        try {

            PreparedStatement comando = connection.prepareStatement(comandoRevistasSuscritas);
            //comando.setString(1, nombrePublicador);
            ResultSet resultSet = comando.executeQuery();


            while (resultSet.next()) {
                int numeroRevista = resultSet.getInt("numero_revista");
                
                
                Revista revista = new Revista(numeroRevista);

                String usuarioQueDioMeGusta = resultSet.getString("nombre_usuario");
                revista.setUsuarioQueDioMeGusta(usuarioQueDioMeGusta);
                
                String numeroRevistaString = resultSet.getString("numero_revista");
                revista.setNumeroRevistaString(numeroRevistaString);

                
                Date fechaProceso = resultSet.getDate("fecha_creacion");
                revista.setFechaProceso(fechaProceso);

                meGustas.add(revista);
                //String nombreAutor = resultSet.getString("nombre_usuario");
            }
            resultSet.close();
            comando.close();

            return meGustas;

        } catch (SQLException e) {
            System.out.println("Error al extraer suscripciones");
            e.printStackTrace();
        }

        return null;
    }
    
    public ArrayList<Revista> extraerRecurrenciasMeGustas(String nombrePublicador, String fechaInicio, String fechaFin, MotorPrograma motorPrograma) {
        
        ArrayList<Revista> meGustasOcurrencias = new ArrayList<>();
        
        String comandoOcurrencias = "";
        
        //El proceso es para saber que datos meter en el Prepared Statement
        int proceso = -1;
        
        if (fechaInicio.equals("undefined") && fechaFin.equals("undefined")) {
            //No filtra por revista ni fecha
            proceso = 1;
            comandoOcurrencias = "select numero_revista, count(*) AS occurrences FROM megusta GROUP BY numero_revista ORDER BY occurrences DESC LIMIT 5;";
            
        } else if (!(fechaInicio.equals("undefined") || fechaFin.equals("undefined"))) {
            //Filtra solo por fechas
            proceso = 2;
            comandoOcurrencias = "SELECT numero_revista, COUNT(*) AS occurrences FROM megusta WHERE fecha_creacion BETWEEN ? AND ? "
                    + "GROUP BY numero_revista ORDER BY occurrences DESC LIMIT 5;";
            
        } else {
            return null;
        }
        
        //System.out.println("PROCESO : " + proceso);
        
        try {

            PreparedStatement comando = connection.prepareStatement(comandoOcurrencias);
            
            switch (proceso) {
                case 2:
                    comando.setDate(1, motorPrograma.formatoFechaAdecuado(fechaInicio));
                    comando.setDate(2, motorPrograma.formatoFechaAdecuado(fechaFin));
                    break;
                default:
                    break;
            }
            
            ResultSet resultSet = comando.executeQuery();


            while (resultSet.next()) {
                int numeroRevista = resultSet.getInt("numero_revista");
                
                
                Revista revista = new Revista(numeroRevista);
                
                String numeroRevistaString = resultSet.getString("numero_revista");
                revista.setNumeroRevistaString(numeroRevistaString);

                int ocurrencias = resultSet.getInt("occurrences");
                revista.setOccurrences(ocurrencias);

                meGustasOcurrencias.add(revista);
                //String nombreAutor = resultSet.getString("nombre_usuario");
            }
            resultSet.close();
            comando.close();

            return meGustasOcurrencias;

        } catch (SQLException e) {
            System.out.println("Error al extraer ocurrencias me gustas");
            e.printStackTrace();
        }

        return null;
    }

    //REPORTES ADMIN
    public ArrayList<Revista> extraerSuscripcionesReporteAdmin(String nombrePublicador) {
        String comandoRevistasSuscritas = "SELECT s.*, p.nombre_usuario FROM suscribir s JOIN publicar p ON s.numero_revista = p.numero_revista ORDER BY numero_revista;";
        ArrayList<Revista> meGustas = new ArrayList<>();
        
        try {

            PreparedStatement comando = connection.prepareStatement(comandoRevistasSuscritas);
            //comando.setString(1, nombrePublicador);
            ResultSet resultSet = comando.executeQuery();


            while (resultSet.next()) {
                int numeroRevista = resultSet.getInt("numero_revista");
                
                
                Revista revista = new Revista(numeroRevista);

                String usuarioQueDioMeGusta = resultSet.getString("nombre_usuario");
                revista.setUsuarioQueDioMeGusta(usuarioQueDioMeGusta);
                
                String numeroRevistaString = resultSet.getString("numero_revista");
                revista.setNumeroRevistaString(numeroRevistaString);

                
                Date fechaProceso = resultSet.getDate("fecha_creacion");
                revista.setFechaProceso(fechaProceso);

                meGustas.add(revista);
                //String nombreAutor = resultSet.getString("nombre_usuario");
            }
            resultSet.close();
            comando.close();

            return meGustas;

        } catch (SQLException e) {
            System.out.println("Error al extraer suscripciones");
            e.printStackTrace();
        }

        return null;
    }
    
    public ArrayList<Revista> extraerRecurrenciaSuscripcionesReporteAdmin(String nombrePublicador, String fechaInicio, String fechaFin, MotorPrograma motorPrograma) {
        
        ArrayList<Revista> meGustasOcurrencias = new ArrayList<>();
        
        String comandoOcurrencias = "";
        
        //El proceso es para saber que datos meter en el Prepared Statement
        int proceso = -1;
        
        if ((fechaInicio.equals("undefined") && fechaFin.equals("undefined"))) {
            //No filtra por fecha
            proceso = 1;
            comandoOcurrencias = "select numero_revista, count(*) AS occurrences FROM suscribir GROUP BY numero_revista ORDER BY occurrences DESC LIMIT 5;";
            
        } else if (!(fechaInicio.equals("undefined") || fechaFin.equals("undefined"))) {
            //Filtra solo por fechas
            proceso = 2;
            comandoOcurrencias = "SELECT numero_revista, COUNT(*) AS occurrences FROM suscribir WHERE fecha_creacion BETWEEN ? AND ? "
                    + "GROUP BY numero_revista ORDER BY occurrences DESC LIMIT 5;";
            
        } else {
            return null;
        }
        
        //System.out.println("PROCESO : " + proceso);
        
        try {

            PreparedStatement comando = connection.prepareStatement(comandoOcurrencias);
            
            switch (proceso) {
                case 2:
                    comando.setDate(1, motorPrograma.formatoFechaAdecuado(fechaInicio));
                    comando.setDate(2, motorPrograma.formatoFechaAdecuado(fechaFin));
                    break;
                default:
                    break;
            }
            
            ResultSet resultSet = comando.executeQuery();


            while (resultSet.next()) {
                int numeroRevista = resultSet.getInt("numero_revista");
                
                
                Revista revista = new Revista(numeroRevista);
                
                String numeroRevistaString = resultSet.getString("numero_revista");
                revista.setNumeroRevistaString(numeroRevistaString);

                int ocurrencias = resultSet.getInt("occurrences");
                revista.setOccurrences(ocurrencias);

                meGustasOcurrencias.add(revista);
                //String nombreAutor = resultSet.getString("nombre_usuario");
            }
            resultSet.close();
            comando.close();

            return meGustasOcurrencias;

        } catch (SQLException e) {
            System.out.println("Error al extraer ocurrencias me gustas");
            e.printStackTrace();
        }

        return null;
    }
    
    public ArrayList<Revista> extraerComentariosReporteAdmin(String nombrePublicador) {
        String comandoRevistasSuscritas = "SELECT c.*, p.nombre_usuario FROM comentar c JOIN publicar p ON c.numero_revista = p.numero_revista ORDER BY numero_revista;";
        ArrayList<Revista> meGustas = new ArrayList<>();
        
        try {

            PreparedStatement comando = connection.prepareStatement(comandoRevistasSuscritas);
            //comando.setString(1, nombrePublicador);
            ResultSet resultSet = comando.executeQuery();


            while (resultSet.next()) {
                int numeroRevista = resultSet.getInt("numero_revista");
                
                
                Revista revista = new Revista(numeroRevista);

                String usuarioQueDioMeGusta = resultSet.getString("nombre_usuario");
                revista.setUsuarioQueDioMeGusta(usuarioQueDioMeGusta);
                
                String numeroRevistaString = resultSet.getString("numero_revista");
                revista.setNumeroRevistaString(numeroRevistaString);
                
                String comentario = resultSet.getString("comentario");
                revista.setComentario(comentario);
                
                
                Date fechaProceso = resultSet.getDate("fecha_creacion");
                revista.setFechaProceso(fechaProceso);

                meGustas.add(revista);
                //String nombreAutor = resultSet.getString("nombre_usuario");
            }
            resultSet.close();
            comando.close();

            return meGustas;

        } catch (SQLException e) {
            System.out.println("Error al extraer COMENTARIOS REPORTE ADMIN");
            e.printStackTrace();
        }

        return null;
    }
    
    public ArrayList<Revista> extraerRecurrenciaComentariosReporteAdmin(String nombrePublicador, String fechaInicio, String fechaFin, MotorPrograma motorPrograma) {
        
        ArrayList<Revista> meGustasOcurrencias = new ArrayList<>();
        
        String comandoOcurrencias = "";
        
        //El proceso es para saber que datos meter en el Prepared Statement
        int proceso = -1;
        
        if ((fechaInicio.equals("undefined") && fechaFin.equals("undefined"))) {
            //No filtra por fecha
            proceso = 1;
            comandoOcurrencias = "select numero_revista, count(*) AS occurrences FROM comentar GROUP BY numero_revista ORDER BY occurrences DESC LIMIT 5;";
            
        } else if (!(fechaInicio.equals("undefined") || fechaFin.equals("undefined"))) {
            //Filtra solo por fechas
            proceso = 2;
            comandoOcurrencias = "SELECT numero_revista, COUNT(*) AS occurrences FROM comentar WHERE fecha_creacion BETWEEN ? AND ? "
                    + "GROUP BY numero_revista ORDER BY occurrences DESC LIMIT 5;";
            
        } else {
            return null;
        }
        
        //System.out.println("PROCESO : " + proceso);
        
        try {

            PreparedStatement comando = connection.prepareStatement(comandoOcurrencias);
            
            switch (proceso) {
                case 2:
                    comando.setDate(1, motorPrograma.formatoFechaAdecuado(fechaInicio));
                    comando.setDate(2, motorPrograma.formatoFechaAdecuado(fechaFin));
                    break;
                default:
                    break;
            }
            
            ResultSet resultSet = comando.executeQuery();


            while (resultSet.next()) {
                int numeroRevista = resultSet.getInt("numero_revista");
                
                
                Revista revista = new Revista(numeroRevista);
                
                String numeroRevistaString = resultSet.getString("numero_revista");
                revista.setNumeroRevistaString(numeroRevistaString);

                int ocurrencias = resultSet.getInt("occurrences");
                revista.setOccurrences(ocurrencias);

                meGustasOcurrencias.add(revista);
                //String nombreAutor = resultSet.getString("nombre_usuario");
            }
            resultSet.close();
            comando.close();

            return meGustasOcurrencias;

        } catch (SQLException e) {
            System.out.println("Error al extraer OCURRENCIAS COMENTARIOS REPORTE ADMIN");
            e.printStackTrace();
        }

        return null;
    }
    
    public ArrayList<Anuncio> extraerAnunciosReporteAdmin(String nombrePublicador) {
        String comandoAnuncios = "SELECT a.*, p.* FROM anuncio a JOIN anunciar1 p ON a.id_anuncio = p.id_anuncio ORDER BY a.id_anuncio;";
        ArrayList<Anuncio> anuncios = new ArrayList<>();
        
        try {

            PreparedStatement comando = connection.prepareStatement(comandoAnuncios);
            ResultSet resultSet = comando.executeQuery();


            while (resultSet.next()) {
                
                Anuncio anuncio = new Anuncio();
                
                int idAnuncio = resultSet.getInt("id_anuncio");
                anuncio.setIdAnuncio(idAnuncio);
                
                String tipo = resultSet.getString("tipo_anuncio");
                anuncio.setTipo(tipo);
                
                String vigencia = resultSet.getString("vigencia_anuncio");
                anuncio.setVigenciaString(vigencia);

                String usuarioQueComproAnuncio = resultSet.getString("nombre_usuario");
                anuncio.setUsuarioQueComproAnuncio(usuarioQueComproAnuncio);
                
                String idAnuncioString = resultSet.getString("id_anuncio");
                anuncio.setIdAnuncioString(idAnuncioString);
                
                BigDecimal costoAnuncio = resultSet.getBigDecimal("costo_anuncio");
                anuncio.setCostoAnuncioDecimal(costoAnuncio);

                
                Date fechaProceso = resultSet.getDate("fecha_creacion");
                anuncio.setFechaInicio(fechaProceso);

                anuncios.add(anuncio);
                //String nombreAutor = resultSet.getString("nombre_usuario");
            }
            resultSet.close();
            comando.close();

            return anuncios;

        } catch (SQLException e) {
            System.out.println("Error al extraer anuncios");
            e.printStackTrace();
        }

        return null;
    }
    
    public ArrayList<Anuncio> extraerAnunciosMostradosReporteAdmin(String nombrePublicador) {
        String comandoAnuncios = "select * from publicitar;";
        ArrayList<Anuncio> anuncios = new ArrayList<>();
        
        try {

            PreparedStatement comando = connection.prepareStatement(comandoAnuncios);
            ResultSet resultSet = comando.executeQuery();


            while (resultSet.next()) {
                
                Anuncio anuncio = new Anuncio();
                
                int idAnuncio = resultSet.getInt("id_anuncio");
                anuncio.setIdAnuncio(idAnuncio);
                
                String idAnuncioString = resultSet.getString("id_anuncio");
                anuncio.setIdAnuncioString(idAnuncioString);
                
                Date fechaMostrado = resultSet.getDate("fecha_mostrado");
                anuncio.setFechaInicio(fechaMostrado);
                
                String nombreAnunciador = resultSet.getString("nombre_usuario");
                anuncio.setNombreUsuario(nombreAnunciador);
                
                String url = resultSet.getString("url");
                anuncio.setUrlAnuncioMostrado(url);
                
                

                anuncios.add(anuncio);
                //String nombreAutor = resultSet.getString("nombre_usuario");
            }
            resultSet.close();
            comando.close();

            return anuncios;

        } catch (SQLException e) {
            System.out.println("Error al extraer anuncios mostrados");
            e.printStackTrace();
        }

        return null;
    }
    
    public ArrayList<Revista> extraerPagosReporteAutor(String nombrePublicador) {
        String comandoRevistasSuscritas = "select * from pagarsuscripcion ORDER BY numero_revista ASC;";
        ArrayList<Revista> pagos = new ArrayList<>();
        
        try {

            PreparedStatement comando = connection.prepareStatement(comandoRevistasSuscritas);
            //comando.setString(1, nombrePublicador);
            ResultSet resultSet = comando.executeQuery();


            while (resultSet.next()) {
                int numeroRevista = resultSet.getInt("numero_revista");
                
                
                Revista revista = new Revista(numeroRevista);

                String usuarioQueDioMeGusta = resultSet.getString("nombre_usuario");
                revista.setUsuarioQueDioMeGusta(usuarioQueDioMeGusta);
                
                String numeroRevistaString = resultSet.getString("numero_revista");
                revista.setNumeroRevistaString(numeroRevistaString);

                
                Date fechaProceso = resultSet.getDate("fecha_suscripcion");
                revista.setFechaProceso(fechaProceso);
                
                int costoSuscripcion = resultSet.getInt("costo");
                revista.setCostoSuscripcion(costoSuscripcion);
                
                

                pagos.add(revista);
                //String nombreAutor = resultSet.getString("nombre_usuario");
            }
            resultSet.close();
            comando.close();

            return pagos;

        } catch (SQLException e) {
            System.out.println("Error al extraer suscripciones");
            e.printStackTrace();
        }

        return null;
    }
    
    public void compilarReporteComentario(HttpServletRequest request) {

        String comando = "select * FROM comentar;";

        if (this.verificarHayElementosEnSQL(comando)) {
            String comandoObtenerRevistas = "select * from comentar ORDER BY numero_revista;";
            this.obtenerRevistasYElementos(comandoObtenerRevistas, comando, "COMENTARIO");
            request.getSession().setAttribute("revistasYComentarios", this.getRevistasYElementos());
            request.getSession().setAttribute("revistas", this.getRevistasYElementos());
        }

    }

    public void compilarReporteComentarioFiltrado(HttpServletRequest request, String fechaInicio, String fechaFin, String numeroRevista) {
        String comando = "select * FROM comentar" + this.crearComandoFiltrarComentarios(fechaInicio, fechaFin, numeroRevista) + ";";

        if (this.verificarHayElementosEnSQL(comando)) {
            //String comandoObtenerRevistasFiltradas = "select * FROM comentar" + crearComandoFiltrarComentarios(fechaInicio, fechaFin, numeroRevista) + ";";
            this.obtenerRevistasFiltradas(comando, "COMENTARIO");
            request.getSession().setAttribute("revistasFiltradas", this.getRevistasFiltradas());
        }
    }

    public void compilarReporteSuscripcion(HttpServletRequest request) {

        String comando = "select * FROM suscribir;";

        if (this.verificarHayElementosEnSQL(comando)) {
            String comandoObtenerRevistas = "select * from suscribir ORDER BY numero_revista;";
            this.obtenerRevistasYElementos(comandoObtenerRevistas, comando, "SUSCRIPCION");
            request.getSession().setAttribute("revistasYSuscripciones", revistasYElementos);
            request.getSession().setAttribute("revistas", revistasYElementos);
        }

    }

    public void compilarReporteSuscripcionFiltrado(HttpServletRequest request, String fechaInicio, String fechaFin, String numeroRevista) {
        String comando = "select * FROM suscribir" + this.crearComandoFiltrarComentarios(fechaInicio, fechaFin, numeroRevista) + ";";
        //System.out.println("COmando filtrado: " + comando);

        if (this.verificarHayElementosEnSQL(comando)) {
            //String comandoObtenerRevistasFiltradas = "select * FROM comentar" + crearComandoFiltrarComentarios(fechaInicio, fechaFin, numeroRevista) + ";";
            this.obtenerRevistasFiltradas(comando, "SUSCRIPCION");
            request.getSession().setAttribute("revistasFiltradas", this.getRevistasFiltradas());
        }
    }

    public void compilarReporteMeGustasAutor(HttpServletRequest request) {

        String comando = "select * FROM megusta;";

        if (this.verificarHayElementosEnSQL(comando)) {
            String comandoObtenerRevistas = "select * from megusta ORDER BY numero_revista;";
            this.obtenerRevistasYElementos(comandoObtenerRevistas, comando, "MEGUSTA");
            String comandoObtenerRevistasMasGustadas = "select numero_revista, count(*) AS occurrences FROM megusta GROUP BY numero_revista ORDER BY occurrences DESC LIMIT 5;";
            obtenerRevistaCon5Elementos(comandoObtenerRevistasMasGustadas);
            request.getSession().setAttribute("revistasY5Elementos", revistasCon5Elementos);
            request.getSession().setAttribute("revistasYMeGustas", revistasYElementos);
            request.getSession().setAttribute("revistas", revistasYElementos);
        }

    }

    public void compilarReporteMeGustasAutorFiltrado(HttpServletRequest request, String fechaInicio, String fechaFin, String numeroRevista) {
        String comando = "select * FROM megusta" + this.crearComandoFiltrarComentarios(fechaInicio, fechaFin, numeroRevista) + ";";
        System.out.println("COmando apra filtrar: " + comando);

        if (this.verificarHayElementosEnSQL(comando)) {
            //String comandoObtenerRevistasFiltradas = "select * FROM comentar" + crearComandoFiltrarComentarios(fechaInicio, fechaFin, numeroRevista) + ";";
            this.obtenerRevistasFiltradas(comando, "MEGUSTA");
            request.getSession().setAttribute("revistasFiltradas", this.getRevistasFiltradas());
        }
    }

    public void compilarReporteComentariosAdmin(HttpServletRequest request) {

        String comando = "select * FROM comentar;";

        if (this.verificarHayElementosEnSQL(comando)) {
            String comandoObtenerRevistas = "select * from comentar ORDER BY numero_revista;";
            this.obtenerRevistasYElementos(comandoObtenerRevistas, comando, "COMENTARIO");
            String comandoObtenerRevistasMasGustadas = "select numero_revista, count(*) AS occurrences FROM comentar GROUP BY numero_revista ORDER BY occurrences DESC LIMIT 5;";
            obtenerRevistaCon5Elementos(comandoObtenerRevistasMasGustadas);
            request.getSession().setAttribute("revistasY5Elementos", revistasCon5Elementos);
            request.getSession().setAttribute("revistasYMeGustas", revistasYElementos);
            request.getSession().setAttribute("revistas", revistasYElementos);
        }

    }

    public void compilarReporteComentariosAdminFiltrado(HttpServletRequest request, String fechaInicio, String fechaFin, String numeroRevista) {
        String comando = "select * FROM comentar" + this.crearComandoFiltrarComentarios(fechaInicio, fechaFin, numeroRevista) + ";";
        //System.out.println("COmando apra filtrar: " + comando);

        if (this.verificarHayElementosEnSQL(comando)) {
            //String comandoObtenerRevistasFiltradas = "select * FROM comentar" + crearComandoFiltrarComentarios(fechaInicio, fechaFin, numeroRevista) + ";";
            this.obtenerRevistasFiltradas(comando, "COMENTARIO");
            request.getSession().setAttribute("revistasFiltradas", this.getRevistasFiltradas());
        }
    }

    public void compilarReportePopularesAdmin(HttpServletRequest request) {

        String comando = "select * FROM suscribir;";

        if (this.verificarHayElementosEnSQL(comando)) {
            String comandoObtenerRevistas = "select * from suscribir ORDER BY numero_revista;";
            this.obtenerRevistasYElementos(comandoObtenerRevistas, comando, "SUSCRIBIR");
            String comandoObtenerRevistasMasGustadas = "select numero_revista, count(*) AS occurrences FROM suscribir GROUP BY numero_revista ORDER BY occurrences DESC LIMIT 5;";
            obtenerRevistaCon5Elementos(comandoObtenerRevistasMasGustadas);
            request.getSession().setAttribute("revistasY5Elementos", revistasCon5Elementos);
            request.getSession().setAttribute("revistasYMeGustas", revistasYElementos);
            request.getSession().setAttribute("revistas", revistasYElementos);
        }

    }

    public void compilarReportePopularesAdminFiltrado(HttpServletRequest request, String fechaInicio, String fechaFin, String numeroRevista) {
        String comando = "select * FROM suscribir" + this.crearComandoFiltrarComentarios(fechaInicio, fechaFin, numeroRevista) + ";";
        //System.out.println("COmando apra filtrar: " + comando);

        if (this.verificarHayElementosEnSQL(comando)) {
            //String comandoObtenerRevistasFiltradas = "select * FROM comentar" + crearComandoFiltrarComentarios(fechaInicio, fechaFin, numeroRevista) + ";";
            this.obtenerRevistasFiltradas(comando, "SUSCRIBIR");
            request.getSession().setAttribute("revistasFiltradas", this.getRevistasFiltradas());
        }
    }

    public void compilarReporteAnunciosAdmin(HttpServletRequest request) {

        String comando = "select * FROM anunciar;";

        if (this.verificarHayElementosEnSQL(comando)) {
            String comandoObtenerAnuncios = "select * from anunciar ORDER BY tipo_anuncio;";
            this.obtenerAnunciosYElementos(comandoObtenerAnuncios, comando, "ANUNCIAR");
            request.getSession().setAttribute("anuncios", anuncios);
            request.getSession().setAttribute("revistas", anuncios);
        }

    }

    public void compilarReporteAnunciosAdminFiltrado(HttpServletRequest request, String fechaInicio, String fechaFin, String tipoAnuncio, String vigenciaAnuncio) {
        String comando = "select * FROM anunciar" + this.crearComandoFiltrarAnuncios(fechaInicio, fechaFin, tipoAnuncio, vigenciaAnuncio) + ";";
        //System.out.println("COmando apra filtrar: " + comando);
        System.out.println("Comando: " + comando);
        if (this.verificarHayElementosEnSQL(comando)) {
            //String comandoObtenerRevistasFiltradas = "select * FROM comentar" + crearComandoFiltrarComentarios(fechaInicio, fechaFin, numeroRevista) + ";";
            anuncios = new Anuncio[contarElementos(comando)];
            this.obtenerRevistasFiltradas(comando, "ANUNCIAR");
            request.getSession().setAttribute("revistasFiltradas", this.getAnuncios());

            for (Anuncio anuncio : anuncios) {
                System.out.println("Anuncio: vigencia " + anuncio.getVigenciaString() + " tipo " + anuncio.getTipo() + " usuario " + anuncio.getNombreUsuario());
            }
        }
    }

    public String crearComandoFiltrarAnuncios(String fechaInicio, String fechaFin, String tipoAnuncio, String vigenciaAnuncio) {
        String comando = " WHERE fecha_creacion "
                + "BETWEEN '" + fechaInicio + "' AND '" + fechaFin + "' ORDER BY tipo_anuncio";

        String comandoFiltrarTipo = "AND tipo_anuncio LIKE \"" + tipoAnuncio + "\"";

        if (!tipoAnuncio.equals("null")) {
            comando = " WHERE fecha_creacion "
                    + "BETWEEN '" + fechaInicio + "' AND '" + fechaFin + "' " + comandoFiltrarTipo + " ORDER BY tipo_anuncio";
        }

        if (!vigenciaAnuncio.equals("null")) {

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
            String comandoFiltrarVigencia = "AND vigencia_anuncio LIKE " + intVigenciaAnuncio + "";
            comando = " WHERE fecha_creacion "
                    + "BETWEEN '" + fechaInicio + "' AND '" + fechaFin + "' " + comandoFiltrarVigencia + " ORDER BY tipo_anuncio";
        }

        //System.out.println("Comandof para filtrart con fecha: " + comando);
        return comando;
    }

    public void obtenerAnunciosYElementos(String comando, String comandoParaContar, String tipoElemento) {
        //String comando = "select * from comentar ORDER BY numero_revista;";
        anuncios = new Anuncio[contarElementos(comandoParaContar)];

        try {
            Statement statementInsert = connection.createStatement();
            ResultSet resultSet = statementInsert.executeQuery(comando);

            int contador = 0;
            while (resultSet.next()) {

                Anuncio anuncio = new Anuncio(resultSet.getString("tipo_anuncio"));

                anuncio.setVigenciaString(resultSet.getString("vigencia_anuncio"));
                anuncio.setNombreUsuario(resultSet.getString("nombre_usuario"));

                anuncios[contador] = anuncio;

                contador++;
            }

        } catch (SQLException e) {
            System.out.println("Error verificnado hay anuncios");
            e.printStackTrace();
        }
        //this.revistasYComentarios;
    }

    public List<Anuncio> obtenerListaAnuncios() {
        List<Anuncio> anuncios = new ArrayList<Anuncio>();
        String comando = "select * FROM anunciar";

        try {
            Statement statementInsert = connection.createStatement();
            ResultSet resultSet = statementInsert.executeQuery(comando);

            int contador = 0;
            while (resultSet.next()) {

                Anuncio anuncio = new Anuncio(resultSet.getString("tipo_anuncio"));

                anuncio.setVigenciaString(resultSet.getString("vigencia_anuncio"));
                anuncio.setNombreUsuario(resultSet.getString("nombre_usuario"));

                anuncios.add(anuncio);

                contador++;
            }

        } catch (SQLException e) {
            System.out.println("Error verificnado hay anuncios");
            e.printStackTrace();
        }

        return anuncios;
    }

    public List<Revista> obtenerListaComentarios() {
        List<Revista> revistas = new ArrayList<Revista>();
        String comando = "select * FROM comentar";

        try {
            Statement statementInsert = connection.createStatement();
            ResultSet resultSet = statementInsert.executeQuery(comando);

            int contador = 0;
            while (resultSet.next()) {

                Revista revista = new Revista(Integer.parseInt(resultSet.getString("numero_revista")));

                revista.setComentario(resultSet.getString("comentario"));

                revista.setUsuarioQueComento(resultSet.getString("nombre_usuario"));

                revistas.add(revista);

                contador++;
            }

        } catch (SQLException e) {
            System.out.println("Error verificnado hay anuncios");
            e.printStackTrace();
        }

        return revistas;
    }

    public List<Revista> obtenerListaSuscripciones() {
        List<Revista> revistas = new ArrayList<Revista>();
        String comando = "select * FROM suscribir";

        try {
            Statement statementInsert = connection.createStatement();
            ResultSet resultSet = statementInsert.executeQuery(comando);

            int contador = 0;
            while (resultSet.next()) {

                Revista revista = new Revista(Integer.parseInt(resultSet.getString("numero_revista")));

                //java.sql.Date fecha = 
                //revista.setFechaProceso(resultSet.getString("comentario"));
                revista.setUsuarioQueComento(resultSet.getString("nombre_usuario"));

                revistas.add(revista);

                contador++;
            }

        } catch (SQLException e) {
            System.out.println("Error verificnado hay anuncios");
            e.printStackTrace();
        }

        return revistas;
    }

    public List<Revista> obtenerListaMeGustas() {
        List<Revista> revistas = new ArrayList<Revista>();
        String comando = "select * FROM megusta";

        try {
            Statement statementInsert = connection.createStatement();
            ResultSet resultSet = statementInsert.executeQuery(comando);

            int contador = 0;
            while (resultSet.next()) {

                Revista revista = new Revista(Integer.parseInt(resultSet.getString("numero_revista")));

                //java.sql.Date fecha = 
                //revista.setFechaProceso(resultSet.getString("comentario"));
                revista.setUsuarioQueComento(resultSet.getString("nombre_usuario"));

                revistas.add(revista);

                contador++;
            }

        } catch (SQLException e) {
            System.out.println("Error verificnado hay anuncios");
            e.printStackTrace();
        }

        return revistas;
    }

    public List<Revista> obtenerListaPopularesSuscripciones() {
        List<Revista> revistas = new ArrayList<Revista>();
        String comando = "select numero_revista, count(*) AS occurrences FROM suscribir GROUP BY numero_revista ORDER BY occurrences DESC LIMIT 5;";

        try {
            Statement statementInsert = connection.createStatement();
            ResultSet resultSet = statementInsert.executeQuery(comando);

            int contador = 0;
            while (resultSet.next()) {

                Revista revista = new Revista(Integer.parseInt(resultSet.getString("numero_revista")));

                //java.sql.Date fecha = 
                //revista.setFechaProceso(resultSet.getString("comentario"));
                revista.setOccurrences(resultSet.getInt("occurrences"));

                revistas.add(revista);

                contador++;
            }

        } catch (SQLException e) {
            System.out.println("Error verificnado hay anuncios");
            e.printStackTrace();
        }

        return revistas;
    }

    public List<Revista> obtenerListaPopularesComentarios() {
        List<Revista> revistas = new ArrayList<Revista>();
        String comando = "select numero_revista, count(*) AS occurrences FROM comentar GROUP BY numero_revista ORDER BY occurrences DESC LIMIT 5;";

        try {
            Statement statementInsert = connection.createStatement();
            ResultSet resultSet = statementInsert.executeQuery(comando);

            int contador = 0;
            while (resultSet.next()) {

                Revista revista = new Revista(Integer.parseInt(resultSet.getString("numero_revista")));

                //java.sql.Date fecha = 
                //revista.setFechaProceso(resultSet.getString("comentario"));
                revista.setOccurrences(resultSet.getInt("occurrences"));

                revistas.add(revista);

                contador++;
            }

        } catch (SQLException e) {
            System.out.println("Error verificnado hay anuncios");
            e.printStackTrace();
        }

        return revistas;
    }

    public List<Revista> obtenerListaPopularesGustadas() {
        List<Revista> revistas = new ArrayList<Revista>();
        String comando = "select numero_revista, count(*) AS occurrences FROM megusta GROUP BY numero_revista ORDER BY occurrences DESC LIMIT 5;";

        try {
            Statement statementInsert = connection.createStatement();
            ResultSet resultSet = statementInsert.executeQuery(comando);

            int contador = 0;
            while (resultSet.next()) {

                Revista revista = new Revista(Integer.parseInt(resultSet.getString("numero_revista")));

                //java.sql.Date fecha = 
                //revista.setFechaProceso(resultSet.getString("comentario"));
                revista.setOccurrences(resultSet.getInt("occurrences"));

                revistas.add(revista);

                contador++;
            }

        } catch (SQLException e) {
            System.out.println("Error verificnado hay anuncios");
            e.printStackTrace();
        }

        return revistas;
    }

    private void obtenerRevistaCon5Elementos(String comandoObtenerRevistasMasGustadas) {
        revistasCon5Elementos = new Revista[contarElementos(comandoObtenerRevistasMasGustadas)];

        try {
            Statement statementInsert = connection.createStatement();
            ResultSet resultSet = statementInsert.executeQuery(comandoObtenerRevistasMasGustadas);

            int contador = 0;
            while (resultSet.next()) {
                Revista revista = new Revista(Integer.parseInt(resultSet.getString("numero_revista")));
                revista.setOccurrences(Integer.parseInt(resultSet.getString("occurrences")));

                revistasCon5Elementos[contador] = revista;
                contador++;

            }

        } catch (SQLException e) {
            System.out.println("Error extrayendo 5 elementos");
            e.printStackTrace();
        }
    }

    public boolean verificarHayElementosEnSQL(String comando) {
        //String comando = "select * FROM comentar" + verificarFechas + ";";
        //System.out.println("COmando verificando: " + comando);
        //System.out.println("COmando que entra al verificarHayElementos: " + comando);
        try {
            Statement statementInsert = connection.createStatement();
            ResultSet resultSet = statementInsert.executeQuery(comando);
            if (resultSet.next()) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error verificnado hay comentarios");
            e.printStackTrace();
        }

        return false;
    }

    public String crearComandoFiltrarComentarios(String fechaInicio, String fechaFin, String numeroRevista) {
        String comando = " WHERE fecha_creacion "
                + "BETWEEN '" + fechaInicio + "' AND '" + fechaFin + "' ORDER BY numero_revista";

        String comandoFiltrarConRevista = "AND numero_revista LIKE " + numeroRevista;

        if (!numeroRevista.equals("null")) {
            comando = " WHERE fecha_creacion "
                    + "BETWEEN '" + fechaInicio + "' AND '" + fechaFin + "' " + comandoFiltrarConRevista + " ORDER BY numero_revista";
        }

        //System.out.println("Comandof para filtrart con fecha: " + comando);
        return comando;
    }

    public void obtenerRevistasYElementos(String comando, String comandoParaContar, String tipoElemento) {
        //String comando = "select * from comentar ORDER BY numero_revista;";

        revistasYElementos = new Revista[contarElementos(comandoParaContar)];

        try {
            Statement statementInsert = connection.createStatement();
            ResultSet resultSet = statementInsert.executeQuery(comando);

            int contador = 0;
            while (resultSet.next()) {

                Revista revista = new Revista(Integer.parseInt(resultSet.getString("numero_revista")));
                //Para comentarios
                if (tipoElemento.equals("COMENTARIO")) {
                    revista.setComentario(resultSet.getString("comentario"));
                }
                revista.setUsuarioQueComento(resultSet.getString("nombre_usuario"));
                revistasYElementos[contador] = revista;

                contador++;
            }

        } catch (SQLException e) {
            System.out.println("Error verificnado hay comentarios");
            e.printStackTrace();
        }
        //this.revistasYComentarios;
    }

    public void obtenerRevistasFiltradas(String comando, String tipoElemento) {
        revistasFiltradas = new Revista[contarElementos(comando)];

        try {
            Statement statementInsert = connection.createStatement();
            ResultSet resultSet = statementInsert.executeQuery(comando);

            int contador = 0;
            while (resultSet.next()) {

                if (!tipoElemento.equals("ANUNCIAR")) {

                    Revista revista = new Revista(Integer.parseInt(resultSet.getString("numero_revista")));
                    if (tipoElemento.equals("COMENTARIO")) {
                        revista.setComentario(resultSet.getString("comentario"));
                    }

                    revista.setUsuarioQueComento(resultSet.getString("nombre_usuario"));
                    revistasFiltradas[contador] = revista;

                } else {
                    Anuncio anuncio = new Anuncio(resultSet.getString("tipo_anuncio"));

                    anuncio.setVigenciaString(resultSet.getString("vigencia_anuncio"));
                    anuncio.setNombreUsuario(resultSet.getString("nombre_usuario"));

                    anuncios[contador] = anuncio;
                }

                //Para comentarios
                contador++;
            }

        } catch (SQLException e) {
            System.out.println("Error verificnado hay comentarios");
            e.printStackTrace();
        }

    }

    public void definirFechas(Date fechaInicio, Date fechaFin) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public int contarElementos(String comando) {
        //String comando = "select * FROM comentar;";
        int totalElementos = 0;
        try {
            Statement statementInsert = connection.createStatement();
            ResultSet resultSet = statementInsert.executeQuery(comando);
            while (resultSet.next()) {
                totalElementos++;
            }

        } catch (SQLException e) {
            System.out.println("Error verificnado hay comentarios");
            e.printStackTrace();
        }

        return totalElementos;
    }

}
