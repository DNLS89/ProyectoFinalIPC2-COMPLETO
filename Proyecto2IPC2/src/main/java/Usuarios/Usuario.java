package Usuarios;

import java.math.BigDecimal;
import java.sql.Connection;

public class Usuario {
    private String nombreUsuario = "";
    private String contraseña = "";
    private BigDecimal cartera;
    private String foto = "";
    private String hobbies = "";
    private String descripcion = "";
    private String gustos = "";
    private Connection connection;
    private String rol = "";
    
    
    public void editarPerfil() {
        //Usar la conexión con SQL
        this.connection = connection;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public BigDecimal getCartera() {
        return cartera;
    }

    public void setCartera(BigDecimal cartera) {
        this.cartera = cartera;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getGustos() {
        return gustos;
    }

    public void setGustos(String gustos) {
        this.gustos = gustos;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
    @Override
    public String toString() {
        return "Usuario{" + "nombreUsuario=" + nombreUsuario + ", contrase\u00f1a=" + contraseña + ", cartera=" + cartera + ", foto=" + foto + ", hobbies=" + hobbies + ", descripcion=" + descripcion + ", gustos=" + gustos + '}';
    }
    
    
    //USUARIO
    //ADMIN
    //AUTOR
    
    public String isRolAdmin() {
        if (rol.equals("ADMIN")) {
            return "";
        }
        
        return "style=\"visibility: hidden\"";
    }
    
    public boolean isAdmin() {
        if (rol.equals("ADMIN")) {
            return true;
        }
        
        return false;
    }
    
    public boolean isAutor() {
        if (rol.equals("AUTOR")) {
            return true;
        }
        
        return false;
    }
    
    public String isRolAutor() {
        if (rol.equals("AUTOR")) {
            return "";
        }
        
        return "style=\"visibility: hidden\"";
    }
    
    public String isRolUsuario() {
        if (rol.equals("USUARIO")) {
            return "";
        }
        
        return "style=\"visibility: hidden\"";
    }
    
    public boolean isUsuario() {
        if (rol.equals("USUARIO")) {
            return true;
        }
        
        return false;
    }
    
    public boolean isRolAdminOrAutor() {
        if (rol.equals("AUTOR") || rol.equals("ADMIN")) {
            return true;
        }
        
        return false;
    }
    
}
