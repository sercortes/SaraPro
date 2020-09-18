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
public class AutorDTO {
    
  private String idAutor;
  private String idVersionFK;
  private String idFuncionarioFK;
  private String principal;
  
  private ProductoVirtualDTO productoVirtualDTO;

    public AutorDTO() {
    }

    public AutorDTO(String idAutor, String idVersionFK, String idFuncionarioFK, String principal) {
        this.idAutor = idAutor;
        this.idVersionFK = idVersionFK;
        this.idFuncionarioFK = idFuncionarioFK;
        this.principal = principal;
    }

    public String getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(String idAutor) {
        this.idAutor = idAutor;
    }

    public String getIdVersionFK() {
        return idVersionFK;
    }

    public void setIdVersionFK(String idVersionFK) {
        this.idVersionFK = idVersionFK;
    }

    public String getIdFuncionarioFK() {
        return idFuncionarioFK;
    }

    public void setIdFuncionarioFK(String idFuncionarioFK) {
        this.idFuncionarioFK = idFuncionarioFK;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public ProductoVirtualDTO getProductoVirtualDTO() {
        return productoVirtualDTO;
    }

    public void setProductoVirtualDTO(ProductoVirtualDTO productoVirtualDTO) {
        this.productoVirtualDTO = productoVirtualDTO;
    }

    @Override
    public String toString() {
        return "AutorDTO{" + "idAutor=" + idAutor + ", idVersionFK=" + idVersionFK + ", idFuncionarioFK=" + idFuncionarioFK + ", principal=" + principal + ", productoVirtualDTO=" + productoVirtualDTO + '}';
    }

  
    
    
}
