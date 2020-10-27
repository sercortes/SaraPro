/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.dao;


import co.edu.sena.dto.InstructorDTO;
import co.edu.sena.dto.VersioDTO;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import co.edu.sena.util.ConexionSer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author serfin
 */
public class VersionDAO {
    
     private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    
    public VersionDAO(Connection conn) {
        this.conn = conn;
    }
    
//     public boolean deleteEvaluations(String id) {
//        try {
//            String sql = "DELETE FROM evaluacion_general WHERE id_version = ?";
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setString(1, id);
//            int rows = ps.executeUpdate();
//            boolean estado = rows > 0;
//            return estado;
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//            return false;
//        }
//    }
     public boolean updateEvaluations(String id) {
        try {
            String sql = "UPDATE evaluacion_general set estado = 0 WHERE id_version = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            int rows = ps.executeUpdate();
            boolean estado = rows > 0;
            return estado;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
      public boolean updateStatus(VersioDTO versioDTO) throws Exception{
            try {
           
            String sql = "UPDATE version set id_estado = ?, fecha_publicacion = NOW(), fecha_vigencia = ? "
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
     
       public boolean updateProductoVirtual(VersioDTO versioDTO) throws Exception{
            try {
           
            String sql = "UPDATE version set url_version = ?, num_version = ?, id_estado = ? "
                    + "WHERE id_version = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, versioDTO.getUrl());
            ps.setString(2, versioDTO.getNumVersion());
            ps.setString(3, versioDTO.getIdEstadoFK());
            ps.setString(4, versioDTO.getIdVersion());
            
            int rows = ps.executeUpdate();
            boolean estado = rows > 0;
            return estado;
        } catch (Exception ex) {
            System.out.println("Error edit " + ex.getMessage());
            throw new Exception();
        }
    }
    
    public int insertReturn(VersioDTO versioDTO) throws Exception{
        int productoVirtual = 0;
        String sql = "INSERT INTO version (url_version, inst_instalacion, reqst_instalacion, id_p_virtual)"
                + "VALUES (?, ?, ?, ?)";

        try {
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, versioDTO.getUrl());
            ps.setString(2, versioDTO.getInstrucionesInstalacion());
            ps.setString(3, versioDTO.getRequeInstalacion());
            ps.setString(4, versioDTO.getIdProductoVirtualFK());

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
    
     public int getStatus(String idVersion) {
        try {
            int id = 0;
            String sql = "SELECT id_version, id_estado from version where id_version = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, idVersion);
            
            rs = ps.executeQuery();
            List<InstructorDTO> list = new ArrayList<InstructorDTO>();
            InstructorDTO instructorDTO;
            while (rs.next()) {
                id = rs.getInt("id_estado");
            }
            return id;
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }
    
    
      public void CloseAll(){
        ConexionSer.close(conn);
        ConexionSer.close(ps);
        ConexionSer.close(rs);
    }
    
}
