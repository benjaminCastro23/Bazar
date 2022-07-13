/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Felipe
 */

/*Clase realcionada a al JFrame para informe de Boletas*/
public class BoletaInfor {
    public int id;
    public int neto;
    public int iva;
    public int total;

    public BoletaInfor(){
        //objeto para resultado.netx
    }
    /*Constructor del objeto Factura Boleta*/
    public BoletaInfor (int id, int neto,int iva,int total){
        this.id=id;
        this.neto=neto;
        this.iva=iva;
        this.total=total;
    }
     /*Metodo para obtener el listado de facturas con sus valores , nrofactura,precio del producto,iva,total */   
    public static ArrayList<BoletaInfor> obtenerBoletas() {
        ArrayList<BoletaInfor> listaBoletas = new ArrayList<>();
        
        try {
            ConexionBD c = new ConexionBD();
            String sql = "SELECT venta.id, venta.total_neto, venta.total_iva, boleta.total FROM venta INNER JOIN boleta ON venta.id = boleta.id;";
            PreparedStatement sentencia = c.conn.prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery();
            
            while (resultado.next()) {
                BoletaInfor b = new BoletaInfor();
                b.id = resultado.getInt("id");
                b.neto = resultado.getInt("total_neto");
                b.iva = resultado.getInt("total_iva");
                b.total= resultado.getInt("total");
                
                
                listaBoletas.add(b);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listaBoletas;
    }
}
