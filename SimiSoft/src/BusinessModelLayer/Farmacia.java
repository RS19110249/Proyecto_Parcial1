/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessModelLayer;

import DataAccessLayer.DataAccess;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alan Cabrera
 */
public class Farmacia {

    private DataAccess dataAccess = DataAccess.Instance();
    private int idFarmacia;
    private String nombre;
    private String direccion;
    private long telefono;
    private int activo;

    public Farmacia() {
    }

    public Farmacia(String nombre, String direccion, long telefono, int activo) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.activo = activo;
    }

    public DataAccess getDataAccess() {
        return dataAccess;
    }

    public void setDataAccess(DataAccess dataAccess) {
        this.dataAccess = dataAccess;
    }

    public int getIdFarmacia() {
        return idFarmacia;
    }

    public void setIdFarmacia(int idFarmacia) {
        this.idFarmacia = idFarmacia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public DefaultTableModel GetAllModel() {
        String query = "SELECT * FROM farmacias";
        return dataAccess.Query(query);
    }

    public void GetById() {
        String query = "SELECT * FROM farmacias WHERE idFarmacias = " + idFarmacia;
        DefaultTableModel res = dataAccess.Query(query);
        idFarmacia = (int) res.getValueAt(0, 0);
        nombre = (String) res.getValueAt(0, 1);
        direccion = (String) res.getValueAt(0, 2);
        telefono = (long) res.getValueAt(0, 3);
        activo = (int) res.getValueAt(0, 4);
    }

    public boolean Add() {
        String query = "INSERT INTO farmacias(nombre, direccion, telefono, activo)"
                + "VALUES('" + nombre + "','" + direccion + "'," + telefono + "," + activo + ")";
        return dataAccess.Execute(query) >= 1;
    }

    public boolean Delete() {
        String query = "DELETE FROM farmacias WHERE idfarmacias = " + idFarmacia;
        return dataAccess.Execute(query) >= 1;
    }

    public boolean Update() {
        String query = "UPDATE farmacias set "
                + "nombre = '" + nombre + "', "
                + "direccion = '" + direccion + "', "
                + "telefono = " + telefono + ", "
                + "activo = " + activo + " "
                + "WHERE idfarmacias = " + idFarmacia;
        return dataAccess.Execute(query) >= 1;
    }
}
