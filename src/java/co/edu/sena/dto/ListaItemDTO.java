/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.dto;

/**
 *
 * @author equipo
 */
public class ListaItemDTO {
    
    private String idItem;
    private String nombre;
    
    private String idListaFK;

    public ListaItemDTO() {
    }

    public String getIdItem() {
        return idItem;
    }

    public void setIdItem(String idItem) {
        this.idItem = idItem;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdListaFK() {
        return idListaFK;
    }

    public void setIdListaFK(String idListaFK) {
        this.idListaFK = idListaFK;
    }

    @Override
    public String toString() {
        return "ListaItemDTO{" + "idItem=" + idItem + ", nombre=" + nombre + ", idListaFK=" + idListaFK + '}';
    }

    
    
}
