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
public class DerechosAutorDTO {
    
    private String idDerecho;
    private String nombre;
    private String descripcion;
    private String imagen;

    public String getIdDerecho() {
        return idDerecho;
    }

    public void setIdDerecho(String idDerecho) {
        this.idDerecho = idDerecho;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
}