package Proceso;

import Principales.Revista;
import Usuarios.Usuario;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Proceso {

    protected Usuario usuario;
    protected Connection connection;
    protected Revista revista;
    protected java.sql.Date fechaProceso;
    

    public Proceso(Usuario usuario, Connection connection) {
        this.usuario = usuario;
        this.connection = connection;
    }

    public void registrarFechaRealizacion() {        
        LocalDateTime now = LocalDateTime.now();
        fechaProceso = java.sql.Date.valueOf(now.toLocalDate());
        
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Revista getRevista() {
        return revista;
    }

    public void setRevista(Revista revista) {
        this.revista = revista;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void imprimirFecha() {
        System.out.println("Proceso{" + "fechaFormatoSQL=" + fechaProceso + '}');
    }

    public java.sql.Date getFechaProceso() {
        return fechaProceso;
    }

    public void setFechaProceso(java.sql.Date fechaProceso) {
        this.fechaProceso = fechaProceso;
    }
    
    
}
