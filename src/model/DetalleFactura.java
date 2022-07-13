package model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author benju
 */
public class DetalleFactura {
    public int id;
    public int factura_id;
    public int venta_id;
    public int producto_id;
    public int precio_unitario;
    public int cantidad;
    
    /**
     * Este método sirve para asignarle valores a los detalles de las facturas mediante los siguientes parámetros:
     * @param factura_id id de la factura.
     * @param venta_id id de la venta.
     * @param producto_id id del producto.
     * @param precio_unitario precio unitario de los productos.
     * @param cantidad cantidad de los productos.
     */
    public DetalleFactura(int factura_id, int venta_id, int producto_id, int precio_unitario, int cantidad){
        this.factura_id= factura_id;
        this.venta_id = venta_id;
        this.producto_id = producto_id;
        this.precio_unitario = precio_unitario;
        this.cantidad = cantidad;
    }
    
    /**
     * Método para agregar 
     * @return Si estado es mayor a cero se insertará un registro en la base de datos, en el caso contrario debió de haber
     * sucedido algún error.
     */
    public int agregarDetalleFactura(){
        int estado = 0;
        try{
            ConexionBD c = new ConexionBD();
            String sql = "INSERT INTO detalle_factura(factura_id, venta_id, producto_id, precio_unitario, cantidad)"
                    + "VALUES(?, ?, ?, ?, ?)";
            PreparedStatement sentencia = c.conn.prepareStatement(sql);
            sentencia.setInt(1, factura_id);
            sentencia.setInt(2, venta_id);
            sentencia.setInt(3, producto_id);
            sentencia.setInt(4, precio_unitario);
            sentencia.setInt(5, cantidad);
            estado = sentencia.executeUpdate();
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return estado;
    }
}
