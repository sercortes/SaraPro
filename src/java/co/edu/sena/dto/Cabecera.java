/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.dto;

/**
 *
 * @author equipo
 */
public class Cabecera {
    
    private final String tipoDocumento = "TIPO_DOCUMENTO";
    private final String numeroDocumento = "#_DOCUMENTO";
    private final String nombres = "NOMBRES";
    private final String apellidos = "APELLIDOS";
    private final String correo = "CORREO";
    private final String cargo = "CARGO";
    private final String numero = "NUMERO";

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public String getCargo() {
        return cargo;
    }

    public String getNumero() {
        return numero;
    }
    
    
    
}
