package model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Felipe
 */
public class Factura {
    
    public int id;
    public String rut_cliente;
    public String giro;
    public String direccion;
    public String razon_social;
    public int total;
    
    /**
     * Este método sirve para asignarle valores a las facturas mediante los siguientes parámetros:
     * @param rut_cliente RUT del cliente.
     * @param giro Giro.
     * @param direccion Direccion que indica el cliente.
     * @param razon_social Razón social.
     * @param total Valor total.
     */
    public Factura(String rut_cliente, String giro, String direccion, String razon_social, int total) {
        this.rut_cliente = rut_cliente;
        this.giro= giro;
        this.direccion= direccion;
        this.razon_social= razon_social;
        this.total=total;
    }

    /**
     * Método para agregar facturas.
     * @return Si estado es mayor a cero se insertará un registro en la base de datos, en el caso contrario debió de haber
     * sucedido algún error.
     */
    public int agregarFactura(){
        int estado = 0;
        try {
            ConexionBD c = new ConexionBD();
            String sql = "INSERT INTO factura(rut_cliente, giro, razon_social, direccion, total)"
                    + "VALUES(?, ?, ?, ?, ?)";
            PreparedStatement sentencia = c.conn.prepareStatement(sql);
            sentencia.setString(1, rut_cliente);
            sentencia.setString(2, giro);
            sentencia.setString(3, direccion);
            sentencia.setString(4, razon_social);
            sentencia.setInt(5, total);
            estado = sentencia.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return estado;
    }
    

}