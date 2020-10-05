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
public class EvaluacionDTO {
    
    private String idEvaluacion;
    private Timestamp FechaEvaluacion;
    private String observacion;
    private String resultado;
    private String idVersionFK;
    private String idListaChequeoFK;
    private String idFuncionario;

    private String nomFuncionario;
    
    public EvaluacionDTO() {
    }

    public EvaluacionDTO(String idEvaluacion, Timestamp FechaEvaluacion, String observacion, String resultado, String idVersionFK, String idListaChequeoFK, String idFuncionario) {
        this.idEvaluacion = idEvaluacion;
        this.FechaEvaluacion = FechaEvaluacion;
        this.observacion = observacion;
        this.resultado = resultado;
        this.idVersionFK = idVersionFK;
        this.idListaChequeoFK = idListaChequeoFK;
        this.idFuncionario = idFuncionario;
    }

    public String getIdEvaluacion() {
        return idEvaluacion;
    }

    public void setIdEvaluacion(String idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public Timestamp getFechaEvaluacion() {
        return FechaEvaluacion;
    }

    public void setFechaEvaluacion(Timestamp FechaEvaluacion) {
        this.FechaEvaluacion = FechaEvaluacion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getIdVersionFK() {
        return idVersionFK;
    }

    public void setIdVersionFK(String idVersionFK) {
        this.idVersionFK = idVersionFK;
    }

    public String getIdListaChequeoFK() {
        return idListaChequeoFK;
    }

    public void setIdListaChequeoFK(String idListaChequeoFK) {
        this.idListaChequeoFK = idListaChequeoFK;
    }

    public String getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(String idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNomFuncionario() {
        return nomFuncionario;
    }

    public void setNomFuncionario(String nomFuncionario) {
        this.nomFuncionario = nomFuncionario;
    }

    @Override
    public String toString() {
        return "EvaluacionDTO{" + "idEvaluacion=" + idEvaluacion + ", FechaEvaluacion=" + FechaEvaluacion + ", observacion=" + observacion + ", resultado=" + resultado + ", idVersionFK=" + idVersionFK + ", idListaChequeoFK=" + idListaChequeoFK + ", idFuncionario=" + idFuncionario + ", nomFuncionario=" + nomFuncionario + '}';
    }

  
    
}
