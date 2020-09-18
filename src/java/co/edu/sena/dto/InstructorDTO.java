/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.dto;

import java.util.ArrayList;

/**
 *
 * @author serfin
 */
public class InstructorDTO {
    
    private String idFuncionario;
    private String idTipoDocumento;
    private String numDocumento;
    private String nomFuncionario;
    private String apeFuncionario;
    private String correo;
    private String cargo;
    private String telefono;
    private String pass;
    private String idEstadoFK;
    private String idAreaCentroFK;
    
    private String linkHash;
    
    private String idCentroFK;
    
    private ArrayList<RolDTO> rolDTO;

    public InstructorDTO() {
    }

    public InstructorDTO(String idFuncionario, String idTipoDocumento, String numDocumento, String nomFuncionario, String apeFuncionario, String correo, String cargo, String telefono, String pass, String idEstadoFK, String idAreaCentroFK) {
        this.idFuncionario = idFuncionario;
        this.idTipoDocumento = idTipoDocumento;
        this.numDocumento = numDocumento;
        this.nomFuncionario = nomFuncionario;
        this.apeFuncionario = apeFuncionario;
        this.correo = correo;
        this.cargo = cargo;
        this.telefono = telefono;
        this.pass = pass;
        this.idEstadoFK = idEstadoFK;
        this.idAreaCentroFK = idAreaCentroFK;
    }

    public InstructorDTO(String idFuncionario, String pass) {
        this.idFuncionario = idFuncionario;
        this.pass = pass;
    }
    
    

    public String getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(String idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(String idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    public String getNomFuncionario() {
        return nomFuncionario;
    }

    public void setNomFuncionario(String nomFuncionario) {
        this.nomFuncionario = nomFuncionario;
    }

    public String getApeFuncionario() {
        return apeFuncionario;
    }

    public void setApeFuncionario(String apeFuncionario) {
        this.apeFuncionario = apeFuncionario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getIdEstadoFK() {
        return idEstadoFK;
    }

    public void setIdEstadoFK(String idEstadoFK) {
        this.idEstadoFK = idEstadoFK;
    }

    public String getIdAreaCentroFK() {
        return idAreaCentroFK;
    }

    public void setIdAreaCentroFK(String idAreaCentroFK) {
        this.idAreaCentroFK = idAreaCentroFK;
    }

    public String getIdCentroFK() {
        return idCentroFK;
    }

    public void setIdCentroFK(String idCentroFK) {
        this.idCentroFK = idCentroFK;
    }

    public ArrayList<RolDTO> getRolDTO() {
        return rolDTO;
    }

    public void setRolDTO(ArrayList<RolDTO> rolDTO) {
        this.rolDTO = rolDTO;
    }

    public String getLinkHash() {
        return linkHash;
    }

    public void setLinkHash(String linkHash) {
        this.linkHash = linkHash;
    }

    @Override
    public String toString() {
        return "InstructorDTO{" + "idFuncionario=" + idFuncionario + ", idTipoDocumento=" + idTipoDocumento + ", numDocumento=" + numDocumento + ", nomFuncionario=" + nomFuncionario + ", apeFuncionario=" + apeFuncionario + ", correo=" + correo + ", cargo=" + cargo + ", telefono=" + telefono + ", pass=" + pass + ", idEstadoFK=" + idEstadoFK + ", idAreaCentroFK=" + idAreaCentroFK + ", linkHash=" + linkHash + ", idCentroFK=" + idCentroFK + ", rolDTO=" + rolDTO + '}';
    }
    
}
