package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Felipe
 */

/*Clase realcionada a al JFrame para informe de Facturas*/
public class FacturaInfor {
    
    public int id;
    public String rutCliente;
    public String giro;
    public String direccion;
    public int neto;
    public int Iva;
    public int precioTotal;
    
 
    public FacturaInfor() {
        //objeto para resultado.netx
    }
    /*Constructor del objeto Factura Informe*/
    public FacturaInfor (int id, int neto, int Iva, int precioTotal) {
        this.id = id;
        this.neto = neto;
        this.Iva=Iva;
        this.precioTotal=precioTotal;
    }
     /*Metodo para obtener el listado de facturas con sus valores , nrofactura,precio del producto,iva,total */
    public static ArrayList<FacturaInfor> obtenerFacturas() {
        ArrayList<FacturaInfor> listaFacturas = new ArrayList<>();
        
        try {
            ConexionBD c = new ConexionBD();
            String sql ="SELECT venta.id, venta.total_neto, venta.total_iva, factura.total FROM venta INNER JOIN factura ON venta.id = factura.id;";
            PreparedStatement sentencia = c.conn.prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery();
            
            while (resultado.next()) {
                FacturaInfor f = new FacturaInfor();
                f.id=resultado.getInt("id");
                f.neto=resultado.getInt("total_neto");
                f.Iva=resultado.getInt("total_iva");
                f.precioTotal=resultado.getInt("total");
                
                listaFacturas.add(f);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    return listaFacturas;
    }
}