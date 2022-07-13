package model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author benju
 */
public class Venta {
    public int id;
    public int total_neto;
    public int total_iva;
    public String fecha;
    public int tipo_venta;
    
    /**
     * Este método sirve para entregarle valores a las ventas mediante los siguientes parámetros:
     * @param total_neto total neto del producto.
     * @param total_iva total del producto con IVA(19%).
     * @param tipo_venta tipo de venta los cuales son Boleta(1) o Factura(2).
     */
    public Venta(int total_neto, int total_iva, int tipo_venta){
        this.total_neto = total_neto;
        this.total_iva = total_iva;
        this.tipo_venta = tipo_venta;
    }
    
    /**
     * 
     * @return Si estado2 es mayor a cero se insertará un registro en la base de datos, en el caso contrario debió de haber
     * sucedido algún error.
     */
    public int agregarVenta(){
        int estado2 = 0;
        try {
            ConexionBD c = new ConexionBD();
            String sql = "INSERT INTO venta(total_neto, total_iva, fecha, tipo_venta)"+"VALUES(?, ?, curdate(), ?);";
            PreparedStatement sentencia = c.conn.prepareStatement(sql);
            sentencia.setInt(1, total_neto);
            sentencia.setInt(2, total_iva);
            sentencia.setInt(3, tipo_venta);
            estado2 = sentencia.executeUpdate();
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return estado2;
    }
    
}
