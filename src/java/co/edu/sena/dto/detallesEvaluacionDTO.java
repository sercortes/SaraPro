/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.dto;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author serfin
 */
public class detallesEvaluacionDTO {
    
    @SerializedName("idDetallesEvaluacion")
    private String idDetallesEvaluacion;
    @SerializedName("valoracion")
    private String valoracion;
    @SerializedName("observacion")
    private String observacion;
    @SerializedName("idDetallesLista")
    private String idDetallesLista;
    @SerializedName("id_evaluacion_general")
    private String id_evaluacion_general;

    public detallesEvaluacionDTO() {
    }

    public detallesEvaluacionDTO(String idDetallesEvaluacion, String valoracion, String observacion, String idDetallesLista, String id_evaluacion_general) {
        this.idDetallesEvaluacion = idDetallesEvaluacion;
        this.valoracion = valoracion;
        this.observacion = observacion;
        this.idDetallesLista = idDetallesLista;
        this.id_evaluacion_general = id_evaluacion_general;
    }

    public String getIdDetallesEvaluacion() {
        return idDetallesEvaluacion;
    }

    public void setIdDetallesEvaluacion(String idDetallesEvaluacion) {
        this.idDetallesEvaluacion = idDetallesEvaluacion;
    }

    public String getValoracion() {
        return valoracion;
    }

    public void setValoracion(String valoracion) {
        this.valoracion = valoracion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getIdDetallesLista() {
        return idDetallesLista;
    }

    public void setIdDetallesLista(String idDetallesLista) {
        this.idDetallesLista = idDetallesLista;
    }

    public String getId_evaluacion_general() {
        return id_evaluacion_general;
    }

    public void setId_evaluacion_general(String id_evaluacion_general) {
        this.id_evaluacion_general = id_evaluacion_general;
    }

    @Override
    public String toString() {
        return "detallesEvaluacionDTO{" + "idDetallesEvaluacion=" + idDetallesEvaluacion + ", valoracion=" + valoracion + ", observacion=" + observacion + ", idDetallesLista=" + idDetallesLista + ", id_evaluacion_general=" + id_evaluacion_general + '}';
    }
    
    
    
}
