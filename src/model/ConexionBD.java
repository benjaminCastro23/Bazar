package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author benju
 */
public class ConexionBD {
    
    private String DSN = "jdbc:mysql://localhost:3306/bazar";
    private String USER = "root";
    private String PASS = "root";
    public Connection conn;

    /**
     * Método para poder conectar con la base de datos de MySQL.
     */
    public ConexionBD() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DSN, USER, PASS);
        } catch (SQLException e) {
            System.out.println(e);
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
