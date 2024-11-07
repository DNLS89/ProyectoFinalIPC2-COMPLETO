package Anuncios;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;

public class Anuncio {
    //1 va a ser true
    private int estado;
    private String tipo;
    private String vigenciaString;
    private int vigenciaInt;
    private java.sql.Date fechaInicio;
    private java.sql.Date fechaFin;
    private String nombreUsuario;
    private int idAnuncio;
    private int costoAnuncio;
    private BigDecimal costoAnuncioDecimal;
    private int costoOcultacion;

    public Anuncio() {
    }
    
    public Anuncio(String tipo) {
        this.tipo = tipo;
    }

    public Anuncio(int estado, String tipoAnuncio) {
        this.estado = estado;
        this.tipo = tipoAnuncio;
    }

    public Anuncio(int idAnuncio, String tipo, int estadoAnuncio, int vigencia, int costoAnuncio, int costoOcultacion) {
        this.idAnuncio = idAnuncio;
        this.tipo = tipo;
        this.estado = estadoAnuncio;
        this.vigenciaInt = vigencia;
        this.costoAnuncio = costoAnuncio;
        this.costoOcultacion = costoOcultacion;
        
        
        switch (vigencia) {
            case 1:
                this.vigenciaString = "UNDIA";
                break;
            case 3:
                this.vigenciaString = "TRESDIAS";
                break;
            case 7:
                this.vigenciaString = "UNASEMANA";
                break;
            case 14:
                this.vigenciaString = "DOSSEMANAS";
                break;
            default:
                break;
        }
        
    }

    public int getVigenciaInt() {
        return vigenciaInt;
    }

    public void setVigenciaInt(int vigenciaInt) {
        this.vigenciaInt = vigenciaInt;
    }
    
//    public void calcularFechas(String vigenciaAnuncio, java.sql.Date fechaCompra) {
//        System.out.println("Fecha ingresada: " + fechaCompra);
//        //LocalDateTime now = LocalDateTime.now();
//        //this.fechaInicio = java.sql.Date.valueOf(now.toLocalDate());
//        this.fechaInicio = fechaCompra;
//        
//        int diasPorAñadir;
//        switch (vigenciaAnuncio) {
//            case "UNDIA":
//                diasPorAñadir = 1;
//                break;
//            case "TRESDIAS":
//                diasPorAñadir = 3;
//                break;
//            case "UNASEMANA":
//                diasPorAñadir = 7;
//                break;
//            case "DOSSEMANAS":
//                diasPorAñadir = 14;
//                break;    
//            default:
//                throw new AssertionError();
//        }
//        
//        //fechaFin = java.sql.Date.valueOf(now.toLocalDate().plusDays(diasPorAñadir));
//        fechaFin = java.sql.Date.valueOf(fechaCompra.toLocalDate().plusDays(diasPorAñadir));
//    }
    
    public void calcularFechas(java.sql.Date fechaCompra) {
        System.out.println("Fecha ingresada: " + fechaCompra);
        //LocalDateTime now = LocalDateTime.now();
        //this.fechaInicio = java.sql.Date.valueOf(now.toLocalDate());
        this.fechaInicio = fechaCompra;
        
        //fechaFin = java.sql.Date.valueOf(now.toLocalDate().plusDays(diasPorAñadir));
        fechaFin = java.sql.Date.valueOf(fechaCompra.toLocalDate().plusDays(this.vigenciaInt));
    }

    public BigDecimal getCostoAnuncioDecimal() {
        return costoAnuncioDecimal;
    }

    public void setCostoAnuncioDecimal(BigDecimal costoAnuncioDecimal) {
        this.costoAnuncioDecimal = costoAnuncioDecimal;
    }
    
    
    
    
    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }


    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getVigenciaString() {
        return vigenciaString;
    }

    public void setVigenciaString(String vigenciaString) {
        this.vigenciaString = vigenciaString;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public int getIdAnuncio() {
        return idAnuncio;
    }

    public void setIdAnuncio(int idAnuncio) {
        this.idAnuncio = idAnuncio;
    }

    public int getCostoAnuncio() {
        return costoAnuncio;
    }

    public void setCostoAnuncio(int costoAnuncio) {
        this.costoAnuncio = costoAnuncio;
    }

    public int getCostoOcultacion() {
        return costoOcultacion;
    }

    public void setCostoOcultacion(int costoOcultacion) {
        this.costoOcultacion = costoOcultacion;
    }
    
    
    
}
