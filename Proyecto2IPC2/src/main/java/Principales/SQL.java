
package Principales;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;


public class SQL {
    private static final String URL_MYSQL = "jdbc:mysql://localhost:3306/CONTROL_REVISTAS";
    private static final String USER = "root";
    private static final String PASSWORD = "123";

    private Connection connection;
    private DataSource datasource;
    
    private static SQL UNICA_INSTANCIA_DE_SQL;

    public SQL() {
        System.out.println("Creando Conexión");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            PoolProperties p = new PoolProperties();
            p.setUrl(URL_MYSQL);
            p.setDriverClassName("com.mysql.cj.jdbc.Driver");
            p.setUsername(USER);
            p.setPassword(PASSWORD);
            p.setJmxEnabled(true);
            p.setTestWhileIdle(false);
            p.setTestOnBorrow(true);
            p.setValidationQuery("SELECT 1");
            p.setTestOnReturn(false);
            p.setValidationInterval(30000);
            p.setTimeBetweenEvictionRunsMillis(30000);
            p.setMaxActive(100);
            p.setInitialSize(10);
            p.setMaxWait(10000);
            p.setRemoveAbandonedTimeout(15060);
            p.setMinEvictableIdleTimeMillis(30000);
            p.setMinIdle(10);
            p.setLogAbandoned(true);
            p.setRemoveAbandoned(true);
            p.setJdbcInterceptors(
                    "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"
                    + "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
            datasource = new DataSource(p);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public static SQL getInstance() {
        if (UNICA_INSTANCIA_DE_SQL == null) {
            UNICA_INSTANCIA_DE_SQL = new SQL();
        }

        return UNICA_INSTANCIA_DE_SQL;
    }
    
    public DataSource getDatasource() {
        return datasource;
    }
    
    public Connection getConnection() {
        try {
            return datasource.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public String convertirAHash(String contraseña) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA");

            messageDigest.update(contraseña.getBytes());

            byte[] resultByteArray = messageDigest.digest();

            StringBuilder sb = new StringBuilder();

            for (byte b : resultByteArray) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();

        } catch (Exception e) {
            System.out.println("Error convirtiendo a Hash");
        }
        
        return "";
    }
}
