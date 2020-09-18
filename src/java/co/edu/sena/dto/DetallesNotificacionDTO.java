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
public class DetallesNotificacionDTO {
    
    private String idDetallesNo;
    private String idNotificacionFK;
    private String idFuncionarioFK;

    public DetallesNotificacionDTO() {
    }

    public DetallesNotificacionDTO(String idDetallesNo, String idNotificacionFK, String idFuncionarioFK) {
        this.idDetallesNo = idDetallesNo;
        this.idNotificacionFK = idNotificacionFK;
        this.idFuncionarioFK = idFuncionarioFK;
    }

    public String getIdDetallesNo() {
        return idDetallesNo;
    }

    public void setIdDetallesNo(String idDetallesNo) {
        this.idDetallesNo = idDetallesNo;
    }

    public String getIdNotificacionFK() {
        return idNotificacionFK;
    }

    public void setIdNotificacionFK(String idNotificacionFK) {
        this.idNotificacionFK = idNotificacionFK;
    }

    public String getIdFuncionarioFK() {
        return idFuncionarioFK;
    }

    public void setIdFuncionarioFK(String idFuncionarioFK) {
        this.idFuncionarioFK = idFuncionarioFK;
    }

    @Override
    public String toString() {
        return "DetallesNotificacionDTO{" + "idDetallesNo=" + idDetallesNo + ", idNotificacionFK=" + idNotificacionFK + ", idFuncionarioFK=" + idFuncionarioFK + '}';
    }
    
    
    
}
