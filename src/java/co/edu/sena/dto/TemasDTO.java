/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.dto;

/**
 *
 * @author serfin
 */
public class TemasDTO {
    
    private String idTema;
    private String nombreTema;

    public TemasDTO() {
    }

    public TemasDTO(String idTema, String nombreTema) {
        this.idTema = idTema;
        this.nombreTema = nombreTema;
    }

    public String getIdTema() {
        return idTema;
    }

    public void setIdTema(String idTema) {
        this.idTema = idTema;
    }

    public String getNombreTema() {
        return nombreTema;
    }

    public void setNombreTema(String nombreTema) {
        this.nombreTema = nombreTema;
    }

    @Override
    public String toString() {
        return "TemasDTO{" + "idTema=" + idTema + ", nombreTema=" + nombreTema + '}';
    }
    
}
