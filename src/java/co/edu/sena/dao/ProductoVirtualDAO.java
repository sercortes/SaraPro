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
            String sql = "SELECT PV.nom_p_virtual, PV.des_p_virtual, PV.palabras_clave, PV.id_p_virtual, "
                    + "V.id_version, V.num_version, V.fecha_envio, V.id_p_virtual, f.id_area_centro, "
                    + "(select GROUP_CONCAT(\" \",nom_funcionario, \" \", apellidos) from funcionario fu INNER JOIN "
                    + "autor a ON fu.id_funcionario = a.id_funcionario where id_version = V.id_version) autores, "
                    + "count(*) FROM producto_virtual PV \n" +
                    "INNER JOIN version V ON PV.id_p_virtual=V.id_p_virtual " +
                    "INNER JOIN autor a ON V.id_version = a.id_version " +
                    "INNER JOIN funcionario f ON a.id_funcionario=f.id_funcionario " +
                    "WHERE V.id_estado = 3 AND f.id_area_centro = ? GROUP BY(PV.id_p_virtual)";
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
//                versioDTO.setUrl(rs.getString("url_version"));
                versioDTO.setFechaEnvio(rs.getTimestamp("fecha_envio"));
                
                versioDTO.setAutores(rs.getString("autores"));
                
                productoVirtualDTO.setVersioDTO(versioDTO);
                list.add(productoVirtualDTO);
            }
            return (ArrayList<ProductoVirtualDTO>) list;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
   
    public ProductoVirtualDTO getProductoVirtualIndividual(String ver) throws Exception{
        

        String sql = "SELECT P.id_p_virtual, P.des_p_virtual, P.palabras_clave, "
                + "V.fecha_envio, V.num_version, V.url_version, V.id_p_virtual, V.inst_instalacion, V.reqst_instalacion " 
                + "FROM producto_virtual P INNER JOIN version V ON P.id_p_virtual = V.id_p_virtual "
                + "WHERE V.id_version = ? LIMIT 1";
        
        try{
            ps = conn.prepareStatement(sql);
            ps.setString(1, ver);
            
           rs = ps.executeQuery();
           ProductoVirtualDTO productoVirtualDTO = new ProductoVirtualDTO();
           VersioDTO versioDTO;
            while (rs.next()) {
                
                productoVirtualDTO.setDescripcion(rs.getString("des_p_virtual"));
                productoVirtualDTO.setPalabrasClave(rs.getString("palabras_clave"));
                
                versioDTO = new VersioDTO();
                versioDTO.setNumVersion(rs.getString("num_version"));
                versioDTO.setUrl(rs.getString("url_version"));
                versioDTO.setFechaEnvio(rs.getTimestamp("fecha_envio"));
                versioDTO.setIntrucionesInstalacion(rs.getString("inst_instalacion"));
                versioDTO.setRequeInstalacion(rs.getString("reqst_instalacion"));
                
                productoVirtualDTO.setVersioDTO(versioDTO);

            }
            return productoVirtualDTO;  
        } catch(MySQLIntegrityConstraintViolationException e){
            System.out.println("D"+e+"D");
            throw new Exception();
        }catch(Exception e){
            System.out.println(e);
            throw new Exception();
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
            String sql = "SELECT PV.nom_p_virtual, PV.des_p_virtual, PV.palabras_clave, "
                    + "V.id_version, V.num_version, V.url_version, V.fecha_envio, "
                    + "f.id_area_centro, count(*) FROM producto_virtual PV "
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
                versioDTO.setFechaEnvio(rs.getTimestamp("fecha_envio"));
                
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
            String sql = "SELECT PV.nom_p_virtual, PV.des_p_virtual, PV.palabras_clave, "
                    + "V.id_version, V.num_version, V.url_version, V.fecha_envio, "
                    + "f.id_area_centro, count(*) FROM producto_virtual PV "
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
                versioDTO.setFechaEnvio(rs.getTimestamp("fecha_envio"));
                
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
      
     public int getSizeTecnico(String idAreaCentro) {
        
             int number = 0;

        String sql = "SELECT count(DISTINCT PV.id_p_virtual) 'size' FROM producto_virtual PV " +
                    "INNER JOIN version V ON PV.id_p_virtual=V.id_p_virtual " +
                    "INNER JOIN autor a ON V.id_version = a.id_version " +
                    "INNER JOIN funcionario f ON a.id_funcionario=f.id_funcionario " +
                    "WHERE V.id_estado = 3 AND f.id_area_centro = ? GROUP BY V.id_estado";
        
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
            return 0;
        }catch(Exception e){
            System.out.println(e);
            return 0;
        }
    }
     
      public int getSizePedagogo(String idAreaCentro) {
        
             int number = 0;

        String sql = "SELECT count(DISTINCT PV.id_p_virtual) 'size' FROM producto_virtual PV " +
                    "INNER JOIN version V ON PV.id_p_virtual=V.id_p_virtual " +
                    "INNER JOIN autor a ON V.id_version = a.id_version " +
                    "INNER JOIN funcionario f ON a.id_funcionario=f.id_funcionario " +
                    "WHERE V.id_estado = 4 AND f.id_area_centro = ? GROUP BY V.id_estado";
        
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
            return 0;
        }catch(Exception e){
            System.out.println(e);
            return 0;
        }
    }
      
      public int getSizeCoor(String idAreaCentro) {
        
             int number = 0;

        String sql = "SELECT count(DISTINCT PV.id_p_virtual) 'size' FROM producto_virtual PV " +
                    "INNER JOIN version V ON PV.id_p_virtual=V.id_p_virtual " +
                    "INNER JOIN autor a ON V.id_version = a.id_version " +
                    "INNER JOIN funcionario f ON a.id_funcionario=f.id_funcionario " +
                    "WHERE V.id_estado = 5 AND f.id_area_centro = ? GROUP BY V.id_estado";
        
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
            return 0;
        }catch(Exception e){
            System.out.println(e);
            return 0;
        }
    }
    
       public ArrayList<ProductoVirtualDTO> getProductosLoser() {
        try {
            String sql = "SELECT PV.nom_p_virtual, "
                    + "V.id_version, V.num_version, V.fecha_envio, V.fecha_vigencia, "
                    + "count(*) FROM producto_virtual PV "
                    + "INNER JOIN version V ON PV.id_p_virtual=V.id_p_virtual "
                    + "WHERE V.id_estado IN (9,10) AND V.fecha_vigencia <= NOW() "
                    + "GROUP BY(PV.id_p_virtual)";
            ps = conn.prepareStatement(sql);
            
            rs = ps.executeQuery();
            List<ProductoVirtualDTO> list = new ArrayList<ProductoVirtualDTO>();
            ProductoVirtualDTO productoVirtualDTO;
            VersioDTO versioDTO;
            while (rs.next()) {
                productoVirtualDTO = new ProductoVirtualDTO();
                
                productoVirtualDTO.setNombre(rs.getString("nom_p_virtual"));
                
                versioDTO = new VersioDTO();
                versioDTO.setIdVersion(rs.getString("id_version"));
                versioDTO.setNumVersion(rs.getString("num_version"));
                versioDTO.setFechaVigencia(rs.getTimestamp("fecha_vigencia"));
                
                productoVirtualDTO.setVersioDTO(versioDTO);
                list.add(productoVirtualDTO);
            }
            return (ArrayList<ProductoVirtualDTO>) list;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
       public boolean disabledStatus(VersioDTO versioDTO) throws Exception{
            try {
           
            String sql = "UPDATE version set id_estado = ?, fecha_publicacion = NOW(), fecha_vigencia = NOW() "
                    + "WHERE id_version = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, versioDTO.getIdEstadoFK());
            ps.setTimestamp(2, versioDTO.getFechaVigencia());
            ps.setString(3, versioDTO.getIdVersion());
            
            int rows = ps.executeUpdate();
            boolean estado = rows > 0;
            return estado;
        } catch (Exception ex) {
            System.out.println("Error edit " + ex.getMessage());
            throw new Exception();
        }
    }
       
     public void CloseAll(){
         ConexionSer.close(conn);
        ConexionSer.close(ps);
        ConexionSer.close(rs);
    }
     
      public ArrayList<ProductoVirtualDTO> getNotificationToday() {
        try {
            String sql = "SELECT PV.nom_p_virtual, "
                    + "V.id_version, V.num_version, V.fecha_envio, V.fecha_vigencia, "
                    + "DATEDIFF(V.fecha_vigencia, NOW())+1 'dias', "
                    + "count(*) FROM producto_virtual PV "
                    + "INNER JOIN version V ON PV.id_p_virtual=V.id_p_virtual "
                    + "WHERE V.id_estado IN (9,10) AND DATEDIFF(V.fecha_vigencia, NOW()) <= 1 "
                    + "GROUP BY(PV.id_p_virtual)";
            ps = conn.prepareStatement(sql);
            
            rs = ps.executeQuery();
            List<ProductoVirtualDTO> list = new ArrayList<ProductoVirtualDTO>();
            ProductoVirtualDTO productoVirtualDTO;
            VersioDTO versioDTO;
            while (rs.next()) {
                productoVirtualDTO = new ProductoVirtualDTO();
                
                productoVirtualDTO.setNombre(rs.getString("nom_p_virtual"));
                
                versioDTO = new VersioDTO();
                versioDTO.setIdVersion(rs.getString("id_version"));
                versioDTO.setNumVersion(rs.getString("num_version"));
                versioDTO.setFechaVigencia(rs.getTimestamp("fecha_vigencia"));
                versioDTO.setDiasFaltantes(rs.getString("dias"));
                
                productoVirtualDTO.setVersioDTO(versioDTO);
                list.add(productoVirtualDTO);
            }
            return (ArrayList<ProductoVirtualDTO>) list;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
}
