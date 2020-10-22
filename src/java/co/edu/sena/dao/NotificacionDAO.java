/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.dao;

import co.edu.sena.dto.EvaluacionDTO;
import co.edu.sena.dto.NotificacionDTO;
import co.edu.sena.dto.ProductoVirtualDTO;
import co.edu.sena.dto.VersioDTO;
import co.edu.sena.util.ConexionSer;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author serfin
 */
public class NotificacionDAO {
     private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    
    public NotificacionDAO(Connection conn) {
        this.conn = conn;
    }
    
    
     public int insertReturnTwo(NotificacionDTO notificacionDTO) throws Exception{
        int productoVirtual = 0;
        String sql = "INSERT INTO notificacion (conte_notificacion, ides_proceso, id_tipo_notificacion, "
                + "id_funcionario, estado)"
                + "VALUES (?, ?, ?, ?, ?)";

        try {
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, notificacionDTO.getDescripcionNotificacion());
            ps.setString(2, notificacionDTO.getFKProductoVirtual());
            ps.setString(3, notificacionDTO.getIdTipoNotificacionFK());
            ps.setString(4, notificacionDTO.getIdFuncionarioFK());
            ps.setString(5, "0");

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
    
    public int insertReturn(NotificacionDTO notificacionDTO) throws Exception{
        int productoVirtual = 0;
        String sql = "INSERT INTO notificacion (conte_notificacion, ides_proceso, id_tipo_notificacion, "
                + "id_funcionario, estado)"
                + "VALUES (?, ?, ?, ?, ?)";

        try {
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, notificacionDTO.getDescripcionNotificacion());
            ps.setString(2, notificacionDTO.getFKProductoVirtual());
            ps.setString(3, "1");
            ps.setString(4, notificacionDTO.getIdFuncionarioFK());
            ps.setString(5, "0");

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
    
    
       public ArrayList<ProductoVirtualDTO> getCorrecionByInstructor(String idInstructor) {
        try {
            String sql = "SELECT V.num_version, V.fecha_envio, V.id_version, V.url_version, V.id_p_virtual, " +
                "max(E.id_evaluacion_general) 'id_evaluacion', max(E.fecha_evaluacion) 'E.fecha_evaluacion', "
              + "max(E.id_funcionario) 'E.id_funcionario', max(E.id_lista_chequeo) 'IdLista', max(ES.nom_estado) 'ES.nom_estado', count(*), " +
                "(select nom_p_virtual from producto_virtual P WHERE id_p_virtual = V.id_p_virtual) 'Nombre', " +
                "(select concat(nom_funcionario, ' ', apellidos) from " +
                "funcionario where id_funcionario = E.id_funcionario) 'Evaluador' " +
                "FROM version V " +
                "INNER JOIN evaluacion_general E ON V.id_version = E.id_version " +
                "INNER JOIN autor A ON V.id_version = A.id_version  " +
                "INNER JOIN funcionario F ON A.id_funcionario = F.id_funcionario " +
                "INNER JOIN estado ES ON V.id_estado=ES.id_estado " +
                "WHERE V.id_estado IN (9,10) AND E.resultado = 0  " +
                "AND F.id_funcionario = ? " +
                "group by V.id_p_virtual";
            ps = conn.prepareStatement(sql);
            ps.setString(1, idInstructor);
            
            rs = ps.executeQuery();
            List<ProductoVirtualDTO> list = new ArrayList<ProductoVirtualDTO>();
            ProductoVirtualDTO productoVirtualDTO;
            VersioDTO versioDTO;
            EvaluacionDTO evaluacionDTO;
            while (rs.next()) {
                productoVirtualDTO = new ProductoVirtualDTO();
                
                productoVirtualDTO.setNombre(rs.getString("Nombre"));
                
                versioDTO = new VersioDTO();
                versioDTO.setFechaEnvio(rs.getTimestamp("V.fecha_envio"));
                versioDTO.setIdVersion(rs.getString("V.id_version"));
                versioDTO.setNumVersion(rs.getString("V.num_version"));
                versioDTO.setUrl(rs.getString("V.url_version"));
                
                evaluacionDTO = new EvaluacionDTO();
                evaluacionDTO.setIdEvaluacion(rs.getString("id_evaluacion"));
                evaluacionDTO.setNomFuncionario(rs.getString("Evaluador"));
                evaluacionDTO.setResultado(rs.getString("ES.nom_estado"));
                evaluacionDTO.setFechaEvaluacion(rs.getTimestamp("E.fecha_evaluacion"));
                evaluacionDTO.setIdListaChequeoFK(rs.getString("IdLista"));
                
                productoVirtualDTO.setEvaluacionDTO(evaluacionDTO);
                productoVirtualDTO.setVersioDTO(versioDTO);
                list.add(productoVirtualDTO);
            }
            return (ArrayList<ProductoVirtualDTO>) list;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
         public ArrayList<NotificacionDTO> getNotificationGeneral(String idInstructor) {
        try {
            String sql = "SELECT (SELECT pv.nom_p_virtual from version v " +
                "INNER JOIN producto_virtual pv ON v.id_p_virtual=pv.id_p_virtual WHERE v.id_version = E.id_version) 'Nombre', " +
                "N.*, E.* " +
                "FROM notificacion N " +
                "INNER JOIN detalles_notificacion DN ON N.id_notificacion=DN.id_notificacion " +
                "INNER JOIN evaluacion_general E ON N.ides_proceso=E.id_evaluacion_general " +
                "where DN.id_funcionario = ? AND N.id_tipo_notificacion = 2";
            ps = conn.prepareStatement(sql);
            ps.setString(1, idInstructor);
            
            rs = ps.executeQuery();
            List<NotificacionDTO> list = new ArrayList<NotificacionDTO>();
            NotificacionDTO notificacionDTO;
            while (rs.next()) {
                notificacionDTO = new NotificacionDTO();
                notificacionDTO.setFechaEnvio(rs.getTimestamp("E.fecha_evaluacion"));
                notificacionDTO.setDescripcionNotificacion(rs.getString("N.conte_notificacion"));
                
                notificacionDTO.setProductoVirtualDTO(new ProductoVirtualDTO(rs.getString("Nombre")));
                list.add(notificacionDTO);
            }
            return (ArrayList<NotificacionDTO>) list;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
       
           public ArrayList<NotificacionDTO> getNotificationGeneralBarra(String idInstructor) {
        try {
            String sql = "SELECT (SELECT pv.nom_p_virtual from version v " +
                "INNER JOIN producto_virtual pv ON v.id_p_virtual=pv.id_p_virtual WHERE v.id_version = E.id_version) 'Nombre', " +
                "N.*, E.*, DN.id_detalles_notificacion " +
                "FROM notificacion N " +
                "INNER JOIN detalles_notificacion DN ON N.id_notificacion=DN.id_notificacion " +
                "INNER JOIN evaluacion_general E ON N.ides_proceso=E.id_evaluacion_general " +
                "where DN.id_funcionario = ? AND N.id_tipo_notificacion = 2 AND DN.estadoN = 0 ORDER BY E.fecha_evaluacion DESC";
            ps = conn.prepareStatement(sql);
            ps.setString(1, idInstructor);
            
            rs = ps.executeQuery();
            List<NotificacionDTO> list = new ArrayList<NotificacionDTO>();
            NotificacionDTO notificacionDTO;
            while (rs.next()) {
                notificacionDTO = new NotificacionDTO();
                notificacionDTO.setFechaEnvio(rs.getTimestamp("E.fecha_evaluacion"));
                notificacionDTO.setDescripcionNotificacion(rs.getString("N.conte_notificacion"));
                notificacionDTO.setIdDetalleNotificacion(rs.getString("DN.id_detalles_notificacion"));
                
                notificacionDTO.setProductoVirtualDTO(new ProductoVirtualDTO(rs.getString("Nombre")));
                list.add(notificacionDTO);
            }
            return (ArrayList<NotificacionDTO>) list;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
           
            public boolean updateDetalleNotificacion(String detalleNotificacion) throws Exception{
            try {
           
            String sql = "UPDATE detalles_notificacion set estadoN = ? "
                    + "WHERE id_detalles_notificacion = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, "1");
            ps.setString(2, detalleNotificacion);
            
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
    
}
