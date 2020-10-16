/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.dto;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

/**
 *
 * @author serfin
 */
public class VersioDTO {
    
    private String idVersion;
    private Date fechaEnvio;
    private Date fechaPublicacion;
    private String numVersion;
    private Timestamp fechaVigencia;
    private String url;
    private String intrucionesInstalacion;
    private String requeInstalacion;
    
    private String idProductoVirtualFK;
    private String idEstadoFK;
    
    private String idTipoNotificacion;

    public VersioDTO() {
    }

    public VersioDTO(String idVersion, Date fechaEnvio, Date fechaPublicacion, String numVersion, Timestamp fechaVigencia, String url, String instalacion, String requeInstalacion, String idProductoVirtualFK, String idEstadoFK) {
        this.idVersion = idVersion;
        this.fechaEnvio = fechaEnvio;
        this.fechaPublicacion = fechaPublicacion;
        this.numVersion = numVersion;
        this.fechaVigencia = fechaVigencia;
        this.url = url;
        this.intrucionesInstalacion = instalacion;
        this.requeInstalacion = requeInstalacion;
        this.idProductoVirtualFK = idProductoVirtualFK;
        this.idEstadoFK = idEstadoFK;
    }

    public VersioDTO(String idEstadoFK) {
        this.idEstadoFK = idEstadoFK;
    }

    public String getIdVersion() {
        return idVersion;
    }

    public void setIdVersion(String idVersion) {
        this.idVersion = idVersion;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getNumVersion() {
        return numVersion;
    }

    public void setNumVersion(String numVersion) {
        this.numVersion = numVersion;
    }

    public Timestamp getFechaVigencia() {
        return fechaVigencia;
    }

    public void setFechaVigencia(Timestamp fechaVigencia) {
        this.fechaVigencia = fechaVigencia;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getInstrucionesInstalacion() {
        return intrucionesInstalacion;
    }

    public void setInstrucionesInstalacion(String instalacion) {
        this.intrucionesInstalacion = instalacion;
    }

    public String getRequeInstalacion() {
        return requeInstalacion;
    }

    public void setRequeInstalacion(String requeInstalacion) {
        this.requeInstalacion = requeInstalacion;
    }

    public String getIdProductoVirtualFK() {
        return idProductoVirtualFK;
    }

    public void setIdProductoVirtualFK(String idProductoVirtualFK) {
        this.idProductoVirtualFK = idProductoVirtualFK;
    }

    public String getIdEstadoFK() {
        return idEstadoFK;
    }

    public void setIdEstadoFK(String idEstadoFK) {
        this.idEstadoFK = idEstadoFK;
    }

    public String getIntrucionesInstalacion() {
        return intrucionesInstalacion;
    }

    public void setIntrucionesInstalacion(String intrucionesInstalacion) {
        this.intrucionesInstalacion = intrucionesInstalacion;
    }

    public String getIdTipoNotificacion() {
        return idTipoNotificacion;
    }

    public void setIdTipoNotificacion(String idTipoNotificacion) {
        this.idTipoNotificacion = idTipoNotificacion;
    }

    @Override
    public String toString() {
        return "VersioDTO{" + "idVersion=" + idVersion + ", fechaEnvio=" + fechaEnvio + ", fechaPublicacion=" + fechaPublicacion + ", numVersion=" + numVersion + ", fechaVigencia=" + fechaVigencia + ", url=" + url + ", intrucionesInstalacion=" + intrucionesInstalacion + ", requeInstalacion=" + requeInstalacion + ", idProductoVirtualFK=" + idProductoVirtualFK + ", idEstadoFK=" + idEstadoFK + ", idTipoNotificacion=" + idTipoNotificacion + '}';
    }
    
}
