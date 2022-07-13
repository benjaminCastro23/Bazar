package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Usuario es la clase que se relaciona directamente con la tabla del mismo nombre en la base de datos
 * @author benju
 */
public class Usuario {
    
    public int id;
    public int tipo_usuario;
    public String nombre;
    public String apellido;
    public String rut;
    public String contrasena;
    
    public String cargo;
    
    /**
     * 
     * @param rut Indica el rut del usuario
     * @param contrasena Indica la contraseña del usuario
     * @param cargo Indica el cargo del usuario
     * @return retorna true en caso de que haya encontrado al usuario, en caso contrario retornará false
     */
    public boolean validarSesion(String rut, String contrasena, String cargo){
        ConexionBD c = new ConexionBD();
        String sql = "SELECT usuario.id, usuario.rut, usuario.contrasena, tipo_usuario.cargo "
                + "FROM usuario INNER JOIN tipo_usuario ON usuario.tipoUsuario = tipo_usuario.id "
                + "WHERE usuario.rut = ? AND usuario.contrasena = ? AND tipo_usuario.cargo = ?";
        try{
            PreparedStatement sentencia = c.conn.prepareStatement(sql);
            sentencia.setString(1, rut);
            sentencia.setString(2, contrasena);
            sentencia.setString(3, cargo);
            ResultSet resultado = sentencia.executeQuery();
            if (resultado.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
}
