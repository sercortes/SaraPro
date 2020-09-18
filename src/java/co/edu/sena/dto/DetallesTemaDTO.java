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
public class DetallesTemaDTO {
    
    private String idDetalles;
    private String idTemaFK;
    private String idProductoFK;
    private String tipoTema;

    public DetallesTemaDTO() {
    }

    public DetallesTemaDTO(String idDetalles, String idTemaFK, String idProductoFK, String tipoTema) {
        this.idDetalles = idDetalles;
        this.idTemaFK = idTemaFK;
        this.idProductoFK = idProductoFK;
        this.tipoTema = tipoTema;
    }

    public String getIdDetalles() {
        return idDetalles;
    }

    public void setIdDetalles(String idDetalles) {
        this.idDetalles = idDetalles;
    }

    public String getIdTemaFK() {
        return idTemaFK;
    }

    public void setIdTemaFK(String idTemaFK) {
        this.idTemaFK = idTemaFK;
    }

    public String getIdProductoFK() {
        return idProductoFK;
    }

    public void setIdProductoFK(String idProductoFK) {
        this.idProductoFK = idProductoFK;
    }

    public String getTipoTema() {
        return tipoTema;
    }

    public void setTipoTema(String tipoTema) {
        this.tipoTema = tipoTema;
    }

    @Override
    public String toString() {
        return "DetallesTemaDTO{" + "idDetalles=" + idDetalles + ", idTemaFK=" + idTemaFK + ", idProductoFK=" + idProductoFK + ", tipoTema=" + tipoTema + '}';
    }
    
}
