/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.dto;

import java.sql.Date;

/**
 *
 * @author serfin
 */
public class CategoriaDTO {
    
    private String idCategoria;
    private String nombreCategoria;
    private String descripCategoria;
    private Date FechaCreacion;

    public CategoriaDTO() {
    }

    public CategoriaDTO(String idCategoria, String nombreCategoria, String descripCategoria, Date FechaCreacion) {
        this.idCategoria = idCategoria;
        this.nombreCategoria = nombreCategoria;
        this.descripCategoria = descripCategoria;
        this.FechaCreacion = FechaCreacion;
    }

    public String getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getDescripCategoria() {
        return descripCategoria;
    }

    public void setDescripCategoria(String descripCategoria) {
        this.descripCategoria = descripCategoria;
    }

    public Date getFechaCreacion() {
        return FechaCreacion;
    }

    public void setFechaCreacion(Date FechaCreacion) {
        this.FechaCreacion = FechaCreacion;
    }

    @Override
    public String toString() {
        return "CategoriaDTO{" + "idCategoria=" + idCategoria + ", nombreCategoria=" + nombreCategoria + ", descripCategoria=" + descripCategoria + ", FechaCreacion=" + FechaCreacion + '}';
    }
    
    
}
