package model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author benju
 */
public class Boleta {
    public int id;
    public int cantidad_producto;
    public int total;
    
    /**
     * Este método sirve para entregarle valores a las boletas mediante los siguientes parametros.
     * @param cantidad_producto cantidad total de productos.
     * @param total total a pagar por el cliente.
     */
    public Boleta(int cantidad_producto, int total) {
        this.cantidad_producto = cantidad_producto;
        this.total = total;
    }
    /**
     * Método para agregar boletas.
     * @return Si estado1 es mayor a cero se insertará un registro en la base de datos, en el caso contrario debió de haber
     * sucedido algún error.
     */
    public int agregarBoleta(){
        int estado1 = 0;
        try {
            ConexionBD c = new ConexionBD();
            String sql = "INSERT INTO boleta(cantidad_producto, total)"+"VALUES(?, ?)";
            PreparedStatement sentencia = c.conn.prepareStatement(sql);
            sentencia.setInt(1, cantidad_producto);
            sentencia.setInt(2, total);
            estado1 = sentencia.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return estado1;
    }
     

}
