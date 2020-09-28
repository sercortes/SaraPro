/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.dao;

import co.edu.sena.dto.DetallesNotificacionDTO;
import co.edu.sena.dto.VersioDTO;
import co.edu.sena.util.ConexionSer;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author serfin
 */
public class DetallesNotificacionDAO {
    
     private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    
    public DetallesNotificacionDAO(Connection conn) {
        this.conn = conn;
    }
   
     public int insertReturnTwo(DetallesNotificacionDTO detallesNotificacionDTO) throws Exception{
        int productoVirtual = 0;
        String sql = "INSERT INTO detalles_notificacion (id_notificacion, id_funcionario)"
                + "VALUES (?, ?)";

        try {
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, detallesNotificacionDTO.getIdNotificacionFK());
            ps.setString(2, detallesNotificacionDTO.getIdFuncionarioFK());

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
    
    public int insertReturn(DetallesNotificacionDTO detallesNotificacionDTO) throws Exception{
        int productoVirtual = 0;
        String sql = "INSERT INTO detalles_notificacion (id_notificacion, id_funcionario)"
                + "VALUES (?, ?)";

        try {
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, detallesNotificacionDTO.getIdNotificacionFK());
            ps.setString(2, detallesNotificacionDTO.getIdFuncionarioFK());

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
    
      public void CloseAll(){
          ConexionSer.close(conn);
        ConexionSer.close(ps);
        ConexionSer.close(rs);
    }
    
    
}
