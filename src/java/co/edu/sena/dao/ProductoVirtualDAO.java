/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.dao;

import co.edu.sena.dto.ProductoVirtualDTO;
import co.edu.sena.dto.VersioDTO;
import co.edu.sena.util.ConexionSer;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author serfin
 */
public class ProductoVirtualDAO {
    
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    
    public ProductoVirtualDAO(Connection conn) {
        this.conn = conn;
    }
    
    public int insertReturn(ProductoVirtualDTO ProductoVirtualDTO) throws Exception{
        int productoVirtual = 0;
        String sql = "INSERT INTO producto_virtual (nom_p_virtual, des_p_virtual, palabras_clave, id_formato, derechosdeautor)"
                + "VALUES (?, ?, ?, ?, ?)";

        try {
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, ProductoVirtualDTO.getNombre());
            ps.setString(2, ProductoVirtualDTO.getDescripcion());
            ps.setString(3, ProductoVirtualDTO.getPalabrasClave());
            ps.setString(4, ProductoVirtualDTO.getFormato());
            ps.setString(5, ProductoVirtualDTO.getDerechosAutor());

            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                productoVirtual = rs.getInt(1);
            }
            return productoVirtual;
        } catch (MySQLIntegrityConstraintViolationException e) {
            System.out.println(e);
            throw new Exception();
        } catch (Exception e) {
            System.out.println(e);
            throw new Exception();
        }

    }
    
        
   public ArrayList<ProductoVirtualDTO> getProductosVirtualesTecnico(String centroCoor) {
        try {
            String sql = "SELECT PV.*, V.*, f.id_area_centro, count(*) FROM producto_virtual PV "
                    + "INNER JOIN version V ON PV.id_p_virtual=V.id_p_virtual "
                    + "INNER JOIN autor a ON V.id_version = a.id_version "
                    + "INNER JOIN funcionario f ON a.id_funcionario=f.id_funcionario "
                    + "WHERE V.id_estado = 3 AND f.id_area_centro = ? GROUP BY(PV.id_p_virtual)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, centroCoor);
            
            rs = ps.executeQuery();
            List<ProductoVirtualDTO> list = new ArrayList<ProductoVirtualDTO>();
            ProductoVirtualDTO productoVirtualDTO;
            VersioDTO versioDTO;
            while (rs.next()) {
                productoVirtualDTO = new ProductoVirtualDTO();
                
                productoVirtualDTO.setNombre(rs.getString("nom_p_virtual"));
                productoVirtualDTO.setDescripcion(rs.getString("des_p_virtual"));
                productoVirtualDTO.setPalabrasClave(rs.getString("palabras_clave"));
                
                versioDTO = new VersioDTO();
                versioDTO.setIdVersion(rs.getString("id_version"));
                versioDTO.setNumVersion(rs.getString("num_version"));
                versioDTO.setUrl(rs.getString("url_version"));
                versioDTO.setFechaEnvio(rs.getDate("fecha_envio"));
                
                productoVirtualDTO.setVersioDTO(versioDTO);
                list.add(productoVirtualDTO);
            }
            return (ArrayList<ProductoVirtualDTO>) list;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
   
    public VersioDTO getIdProductoVirtual(String ver) throws Exception{
        

        String sql = "SELECT id_p_virtual, id_version, num_version, id_estado FROM version WHERE id_version = ? LIMIT 1";
        
        try{
            ps = conn.prepareStatement(sql);
            ps.setString(1, ver);
            
           rs = ps.executeQuery();
           VersioDTO versioDTO = new VersioDTO();
            while (rs.next()) {

                versioDTO.setIdProductoVirtualFK(rs.getString("id_p_virtual"));
                versioDTO.setNumVersion(rs.getString("num_version"));
                versioDTO.setIdEstadoFK(rs.getString("id_estado"));

            }
            return versioDTO;  
        } catch(MySQLIntegrityConstraintViolationException e){
            System.out.println("D"+e+"D");
            throw new Exception();
        }catch(Exception e){
            System.out.println(e);
            throw new Exception();
        }
    }
   
    public ArrayList<ProductoVirtualDTO> getProductosVirtualesPedagogico(String areaCoor) {
        try {
             String sql = "SELECT PV.*, V.*, f.id_area_centro, count(*) FROM producto_virtual PV "
                    + "INNER JOIN version V ON PV.id_p_virtual=V.id_p_virtual "
                    + "INNER JOIN autor a ON V.id_version = a.id_version "
                    + "INNER JOIN funcionario f ON a.id_funcionario=f.id_funcionario "
                    + "WHERE V.id_estado = 4 AND f.id_area_centro = ? GROUP BY(PV.id_p_virtual)";
             
            ps = conn.prepareStatement(sql);
            ps.setString(1, areaCoor);
            
            rs = ps.executeQuery();
            List<ProductoVirtualDTO> list = new ArrayList<ProductoVirtualDTO>();
            ProductoVirtualDTO productoVirtualDTO;
            VersioDTO versioDTO;
            while (rs.next()) {
                productoVirtualDTO = new ProductoVirtualDTO();
                
                productoVirtualDTO.setNombre(rs.getString("nom_p_virtual"));
                productoVirtualDTO.setDescripcion(rs.getString("des_p_virtual"));
                productoVirtualDTO.setPalabrasClave(rs.getString("palabras_clave"));
                
                versioDTO = new VersioDTO();
                versioDTO.setIdVersion(rs.getString("id_version"));
                versioDTO.setNumVersion(rs.getString("num_version"));
                versioDTO.setUrl(rs.getString("url_version"));
                versioDTO.setFechaEnvio(rs.getDate("fecha_envio"));
                
                productoVirtualDTO.setVersioDTO(versioDTO);
                list.add(productoVirtualDTO);
            }
            return (ArrayList<ProductoVirtualDTO>) list;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
    public ArrayList<ProductoVirtualDTO> getProductosVirtualesCoor(String areaCoor) {
        try {
           String sql = "SELECT PV.*, V.*, f.id_area_centro, count(*) FROM producto_virtual PV "
                    + "INNER JOIN version V ON PV.id_p_virtual=V.id_p_virtual "
                    + "INNER JOIN autor a ON V.id_version = a.id_version "
                    + "INNER JOIN funcionario f ON a.id_funcionario=f.id_funcionario "
                    + "INNER JOIN area_centro ac ON f.id_area_centro = ac.id_area_centro "
                   + "INNER JOIN centro c ON ac.id_centro = c.id_centro "
                    + "WHERE V.id_estado = 5 AND c.id_centro = ? GROUP BY(PV.id_p_virtual)";
                         
            ps = conn.prepareStatement(sql);
            ps.setString(1, areaCoor);
            
            rs = ps.executeQuery();
            List<ProductoVirtualDTO> list = new ArrayList<ProductoVirtualDTO>();
            ProductoVirtualDTO productoVirtualDTO;
            VersioDTO versioDTO;
            while (rs.next()) {
                productoVirtualDTO = new ProductoVirtualDTO();
                
                productoVirtualDTO.setNombre(rs.getString("nom_p_virtual"));
                productoVirtualDTO.setDescripcion(rs.getString("des_p_virtual"));
                productoVirtualDTO.setPalabrasClave(rs.getString("palabras_clave"));
                
                versioDTO = new VersioDTO();
                versioDTO.setIdVersion(rs.getString("id_version"));
                versioDTO.setNumVersion(rs.getString("num_version"));
                versioDTO.setUrl(rs.getString("url_version"));
                versioDTO.setFechaEnvio(rs.getDate("fecha_envio"));
                
                productoVirtualDTO.setVersioDTO(versioDTO);
                list.add(productoVirtualDTO);
            }
            return (ArrayList<ProductoVirtualDTO>) list;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
     
    
      public ArrayList<ProductoVirtualDTO> getProductos() {
        try {
           String sql = "SELECT PV.*, V.*, f.id_area_centro, count(*) FROM producto_virtual PV "
                    + "INNER JOIN version V ON PV.id_p_virtual=V.id_p_virtual "
                    + "INNER JOIN autor a ON V.id_version = a.id_version "
                    + "INNER JOIN funcionario f ON a.id_funcionario=f.id_funcionario "
                    + "INNER JOIN area_centro ac ON f.id_area_centro = ac.id_area_centro "
                   + "INNER JOIN centro c ON ac.id_centro = c.id_centro "
                    + "WHERE V.id_estado = 6 GROUP BY(PV.id_p_virtual)";
                         
            ps = conn.prepareStatement(sql);
            
            rs = ps.executeQuery();
            List<ProductoVirtualDTO> list = new ArrayList<ProductoVirtualDTO>();
            ProductoVirtualDTO productoVirtualDTO;
            VersioDTO versioDTO;
            while (rs.next()) {
                productoVirtualDTO = new ProductoVirtualDTO();
                
                productoVirtualDTO.setNombre(rs.getString("nom_p_virtual"));
                productoVirtualDTO.setDescripcion(rs.getString("des_p_virtual"));
                productoVirtualDTO.setPalabrasClave(rs.getString("palabras_clave"));
                
                versioDTO = new VersioDTO();
                versioDTO.setIdVersion(rs.getString("id_version"));
                versioDTO.setNumVersion(rs.getString("num_version"));
                versioDTO.setUrl(rs.getString("url_version"));
                versioDTO.setFechaPublicacion(rs.getDate("fecha_publicacion"));
                
                productoVirtualDTO.setVersioDTO(versioDTO);
                list.add(productoVirtualDTO);
            }
            return (ArrayList<ProductoVirtualDTO>) list;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
      
     public int getSizeTecnico(String idAreaCentro) throws Exception{
        
             int number = 0;

        String sql = "SELECT count(*) 'size' FROM producto_virtual PV " +
                    "INNER JOIN version V ON PV.id_p_virtual=V.id_p_virtual " +
                    "INNER JOIN autor a ON V.id_version = a.id_version " +
                    "INNER JOIN funcionario f ON a.id_funcionario=f.id_funcionario " +
                    "WHERE V.id_estado = 3 AND f.id_area_centro = 9 " +
                    "GROUP BY(PV.id_p_virtual)";
        
        try{
            ps = conn.prepareStatement(sql);
            ps.setString(1, idAreaCentro);
            
           rs = ps.executeQuery();
            while (rs.next()) {

                number = rs.getInt("size");

            }
            return number;  
        } catch(MySQLIntegrityConstraintViolationException e){
            System.out.println("D"+e+"D");
            throw new Exception();
        }catch(Exception e){
            System.out.println(e);
            throw new Exception();
        }
    }
    
    
     public void CloseAll(){
         ConexionSer.close(conn);
        ConexionSer.close(ps);
        ConexionSer.close(rs);
    }
    
    
}
