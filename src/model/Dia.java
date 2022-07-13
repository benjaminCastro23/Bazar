package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author benju
 */
public class Dia {
    public int id;
    public int activo;
    public String fecha;
    
    /**
     * Verificar que el día de hoy esté abierto
     * @return 
     */
    public boolean verificarDia(){
        ConexionBD c = new ConexionBD();
        String sql = "SELECT * FROM dia WHERE activo = 1 AND fecha = curdate()";
        try {
            
            PreparedStatement sentencia = c.conn.prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery();
            if (resultado.next()) {
                return true;
            } else {
                return false;
            }     
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
    
    
    /**
     * Método que abre el día
     * @return 
     */
    public int abrirDia(){
        int estado = 0;
        try {
            ConexionBD c = new ConexionBD();
            String sql = "INSERT INTO dia(activo, fecha)VALUES(1, curdate());";
            PreparedStatement sentencia = c.conn.prepareStatement(sql);
            estado = sentencia.executeUpdate();
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return estado;
    }
    
    /**
     * Método que cierra el día
     * @return 
     */
    public int cerrarDia(){
        int estado = 0;
        try{
            ConexionBD c = new ConexionBD();
            String sql = "UPDATE dia SET activo = 0 WHERE fecha = curdate();";
            PreparedStatement sentencia = c.conn.prepareStatement(sql);
            estado = sentencia.executeUpdate();
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return estado;
    }
        
    
}
