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
public class ListaDTO {
    
    private String idLista;
    private String descripcion;
    private String tipoItem;
    
    private String nombre;
    private String creador;
    private String descripcionGeneral;

    public ListaDTO() {
    }

    public ListaDTO(String idLista, String descripcion, String tipoItem) {
        this.idLista = idLista;
        this.descripcion = descripcion;
        this.tipoItem = tipoItem;
    }

    public String getIdLista() {
        return idLista;
    }

    public void setIdLista(String idLista) {
        this.idLista = idLista;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoItem() {
        return tipoItem;
    }

    public void setTipoItem(String tipoItem) {
        this.tipoItem = tipoItem;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCreador() {
        return creador;
    }

    public void setCreador(String creador) {
        this.creador = creador;
    }

    public String getDescripcionGeneral() {
        return descripcionGeneral;
    }

    public void setDescripcionGeneral(String descripcionGeneral) {
        this.descripcionGeneral = descripcionGeneral;
    }

    @Override
    public String toString() {
        return "ListaDTO{" + "idLista=" + idLista + ", descripcion=" + descripcion + ", tipoItem=" + tipoItem + ", nombre=" + nombre + ", creador=" + creador + ", descripcionGeneral=" + descripcionGeneral + '}';
    }
    
    
    
}
