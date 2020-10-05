/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.dto;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author serfin
 */
public class NotificacionDTO {
    
    private String idNotificacion;
    private Timestamp FechaEnvio;
    private String descripcionNotificacion;
    private String FKProductoVirtual;
    private String idTipoNotificacionFK;
    private String idFuncionarioFK;

    private ProductoVirtualDTO productoVirtualDTO;
    
    private DetallesNotificacionDTO detallesNotificacionDTO;
    private String idDetalleNotificacion;
    
    public NotificacionDTO() {
    }

    public NotificacionDTO(String idNotificacion, Timestamp FechaEnvio, String descripcionNotificacion, String FKProductoVirtual, String idTipoNotificacionFK, String idFuncionarioFK) {
        this.idNotificacion = idNotificacion;
        this.FechaEnvio = FechaEnvio;
        this.descripcionNotificacion = descripcionNotificacion;
        this.FKProductoVirtual = FKProductoVirtual;
        this.idTipoNotificacionFK = idTipoNotificacionFK;
        this.idFuncionarioFK = idFuncionarioFK;
    }

    public String getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(String idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public Timestamp getFechaEnvio() {
        return FechaEnvio;
    }

    public void setFechaEnvio(Timestamp FechaEnvio) {
        this.FechaEnvio = FechaEnvio;
    }

    public String getDescripcionNotificacion() {
        return descripcionNotificacion;
    }

    public void setDescripcionNotificacion(String descripcionNotificacion) {
        this.descripcionNotificacion = descripcionNotificacion;
    }

    public String getFKProductoVirtual() {
        return FKProductoVirtual;
    }

    public void setFKProductoVirtual(String FKProductoVirtual) {
        this.FKProductoVirtual = FKProductoVirtual;
    }

    public String getIdTipoNotificacionFK() {
        return idTipoNotificacionFK;
    }

    public void setIdTipoNotificacionFK(String idTipoNotificacionFK) {
        this.idTipoNotificacionFK = idTipoNotificacionFK;
    }

    public String getIdFuncionarioFK() {
        return idFuncionarioFK;
    }

    public void setIdFuncionarioFK(String idFuncionarioFK) {
        this.idFuncionarioFK = idFuncionarioFK;
    }

    public ProductoVirtualDTO getProductoVirtualDTO() {
        return productoVirtualDTO;
    }

    public void setProductoVirtualDTO(ProductoVirtualDTO productoVirtualDTO) {
        this.productoVirtualDTO = productoVirtualDTO;
    }

    public DetallesNotificacionDTO getDetallesNotificacionDTO() {
        return detallesNotificacionDTO;
    }

    public void setDetallesNotificacionDTO(DetallesNotificacionDTO detallesNotificacionDTO) {
        this.detallesNotificacionDTO = detallesNotificacionDTO;
    }

    public String getIdDetalleNotificacion() {
        return idDetalleNotificacion;
    }

    public void setIdDetalleNotificacion(String idDetalleNotificacion) {
        this.idDetalleNotificacion = idDetalleNotificacion;
    }

    @Override
    public String toString() {
        return "NotificacionDTO{" + "idNotificacion=" + idNotificacion + ", FechaEnvio=" + FechaEnvio + ", descripcionNotificacion=" + descripcionNotificacion + ", FKProductoVirtual=" + FKProductoVirtual + ", idTipoNotificacionFK=" + idTipoNotificacionFK + ", idFuncionarioFK=" + idFuncionarioFK + ", productoVirtualDTO=" + productoVirtualDTO + ", detallesNotificacionDTO=" + detallesNotificacionDTO + ", idDetalleNotificacion=" + idDetalleNotificacion + '}';
    }

  
  
}
