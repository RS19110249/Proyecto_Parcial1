package BusinessModelLayer;

import DataAccessLayer.DataAccess;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alan Cabrera
 */
public class Producto {

    private DataAccess dataAccess = DataAccess.Instance();
    private int idProducto;
    private String nombre;
    private Date caducidad;
    private int stock;
    private int idFarmacia;
    private int activo;

    public Producto() {
    }

    public Producto(int idProducto, String nombre, Date caducidad, int stock, int idFarmacia, int activo) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.caducidad = caducidad;
        this.stock = stock;
        this.idFarmacia = idFarmacia;
        this.activo = activo;
    }

    public DataAccess getDataAccess() {
        return dataAccess;
    }

    public void setDataAccess(DataAccess dataAccess) {
        this.dataAccess = dataAccess;
    }

    public int getidProducto() {
        return idProducto;
    }

    public void setidProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getCaducidad() {
        return caducidad;
    }

    public void setCaducidad(Date caducidad) {
        this.caducidad = caducidad;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getidFarmacia() {
        return idFarmacia;
    }

    public void setidFarmacia(int idFarmacia) {
        this.idFarmacia = idFarmacia;
    }

    public int isActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public DefaultTableModel GetAllModel() {
        String query = "SELECT * FROM productos";
        return dataAccess.Query(query);
    }

    public void GetById() {
        String query = "SELECT * FROM productos WHERE idProductos = " + idProducto;
        DefaultTableModel res = dataAccess.Query(query);
        idProducto = (int) res.getValueAt(0, 0);
        nombre = (String) res.getValueAt(0, 1);
        String fecha = String.valueOf(res.getValueAt(0, 2));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(fecha);
            long d = date.getTime();
            caducidad = new java.sql.Date(d);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Ingrese el formato correctamente: "+e.getMessage(),
            "ERROR!", JOptionPane.ERROR_MESSAGE);
            
        }
        stock = (int) res.getValueAt(0, 3);
        idFarmacia = (int) res.getValueAt(0, 4);
        activo = (int) res.getValueAt(0, 5);
    }

    public boolean Add() {
        String query = "INSERT INTO productos(nombre, caducidad, stock, idFarmacia, activo)"
                + "VALUES('" + nombre + "','" + caducidad + "'," + stock + "," + idFarmacia + "," + activo + ")";
        return dataAccess.Execute(query) >= 1;
    }

    public boolean Delete() {
        String query = "DELETE FROM productos WHERE idProductos = " + idProducto;
        return dataAccess.Execute(query) >= 1;
    }
    
    public boolean Update(){
        String query = "UPDATE productos set " + 
                "nombre = '" + nombre + "', " +
                "caducidad = '" + caducidad + "', " +
                "stock = " + stock + ", " +
                "idFarmacia = " + idFarmacia + ", " +
                "activo = " + activo + " " +
                "WHERE idProductos = " + idProducto;
        return dataAccess.Execute(query)>= 1;
    }
    
    public DefaultComboBoxModel GetFarmacia(){
        String query = "SELECT * FROM farmacias";
        DefaultTableModel res = dataAccess.Query(query);
        Vector<String> farmacia = new Vector<String>();
        for (int i = 0; i < res.getRowCount(); i++) {
            farmacia.add((String) res.getValueAt(i, 1));
        }
        return new DefaultComboBoxModel(farmacia);
    }
    
    public void GetId (String farmacia){
        String query = "SELECT * FROM farmacias WHERE nombre = '" + farmacia + "'";
        DefaultTableModel res = dataAccess.Query(query);
        idFarmacia = (int) res.getValueAt(0, 0);
    }
    public String NombreFarmacia(){
        String query = "SELECT * FROM farmacias WHERE idFarmacias = " + idFarmacia;
        DefaultTableModel res = dataAccess.Query(query);
        return (String) res.getValueAt(0, 1);
    }
}
