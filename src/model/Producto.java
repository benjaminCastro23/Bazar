package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Felipe
 */
public class Producto {
    
    public int id;
    public String nombre;
    public int precio;
    public String sku;
    public int cantidad;
    
    public Producto(){
        //objeto para resultado.netx
    }
    
    
    /**
     * Este método sirve para entregarle valores a los los productos mediante los siguientes parámetros:
     * @param nombre nombre del producto
     * @param precio precio del producto
     * @param sku codigo SKU del producto
     * @param cantidad cantidad del producto
     */
    public Producto (String nombre,int precio ,String sku,int cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.sku = sku;
        this.cantidad = cantidad;
    }
    /**
     * Este método sirve para visualizar los productos en una lista
     * @return retorna el arreglo de lista de productos.
     */
    public static ArrayList<Producto> obtenerProductos(){
        ArrayList<Producto> listaProductos = new ArrayList<>();
        
        try {
            ConexionBD c = new ConexionBD();
            String sql = "SELECT id, nombre, precio, sku, cantidad FROM producto";
                PreparedStatement sentencia = c.conn.prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery();
            
            while (resultado.next()) {
                Producto p = new Producto();
                p.id= resultado.getInt("id");
                p.nombre = resultado.getString("nombre");
                p.precio = resultado.getInt("precio");
                p.sku = resultado.getString("sku");
                p.cantidad = resultado.getInt("cantidad");
                
                listaProductos.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return listaProductos;
    } 
    
    /**
     * Este metodo se encarga de agregar un registro a la tabla producto de la base de datos
     * @return si return es mayor a 0 hará una inserción, si no, hubo un fallo en la inserción
     */
    public int agregarProducto() {
        int estado = 0;
        try {
            ConexionBD c = new ConexionBD();
            String sql = "INSERT INTO producto (nombre, precio , sku , cantidad)" + "VALUE(?, ?, ?, ?)";
            PreparedStatement sentencia = c.conn.prepareStatement(sql);
            sentencia.setString(1, nombre);
            sentencia.setInt(2, precio);
            sentencia.setString(3, sku);
            sentencia.setInt(4, cantidad);
            estado = sentencia.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return estado;
    }
}
