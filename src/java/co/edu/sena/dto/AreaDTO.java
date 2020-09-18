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
public class AreaDTO {
    
    private String idArea;
    private String nomArea;

    public AreaDTO() {
    }

    public AreaDTO(String idArea, String nomArea) {
        this.idArea = idArea;
        this.nomArea = nomArea;
    }

    public String getIdArea() {
        return idArea;
    }

    public void setIdArea(String idArea) {
        this.idArea = idArea;
    }

    public String getNomArea() {
        return nomArea;
    }

    public void setNomArea(String nomArea) {
        this.nomArea = nomArea;
    }
    
    
    
}
