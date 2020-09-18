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
public class ProductoVirtualDTO {
    
    private String idProducto;
    private String nombre;
    private String descripcion;
    private String palabrasClave;
    private String formato;
    private String derechosAutor;
    
    private VersioDTO versioDTO;
    
    private EvaluacionDTO evaluacionDTO;

    public ProductoVirtualDTO() {
    }

    public ProductoVirtualDTO(String nombre) {
        this.nombre = nombre;
    }
    
    

    public ProductoVirtualDTO(String idProducto, String nombre, String descripcion, String palabrasClave, String formato) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.palabrasClave = palabrasClave;
        this.formato = formato;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPalabrasClave() {
        return palabrasClave;
    }

    public void setPalabrasClave(String palabrasClave) {
        this.palabrasClave = palabrasClave;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getDerechosAutor() {
        return derechosAutor;
    }

    public void setDerechosAutor(String derechosAutor) {
        this.derechosAutor = derechosAutor;
    }

    public VersioDTO getVersioDTO() {
        return versioDTO;
    }

    public void setVersioDTO(VersioDTO versioDTO) {
        this.versioDTO = versioDTO;
    }

    public EvaluacionDTO getEvaluacionDTO() {
        return evaluacionDTO;
    }

    public void setEvaluacionDTO(EvaluacionDTO evaluacionDTO) {
        this.evaluacionDTO = evaluacionDTO;
    }

    @Override
    public String toString() {
        return "ProductoVirtualDTO{" + "idProducto=" + idProducto + ", nombre=" + nombre + ", descripcion=" + descripcion + ", palabrasClave=" + palabrasClave + ", formato=" + formato + ", derechosAutor=" + derechosAutor + ", versioDTO=" + versioDTO + ", evaluacionDTO=" + evaluacionDTO + '}';
    }
    
}
