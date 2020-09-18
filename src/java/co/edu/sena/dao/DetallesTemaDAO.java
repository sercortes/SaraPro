/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.dao;


import co.edu.sena.dto.DetallesTemaDTO;
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
public class DetallesTemaDAO {
    
     private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    
    public DetallesTemaDAO(Connection conn) {
        this.conn = conn;
    }
    
    public int insertReturn(DetallesTemaDTO detallesTemaDTO) {
        int productoVirtual = 0;
        String sql = "INSERT INTO detalles_tema (id_tema, id_p_virtual, tipo_tema)"
                + "VALUES (?, ?, ?)";

        try {
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, detallesTemaDTO.getIdTemaFK());
            ps.setString(2, detallesTemaDTO.getIdProductoFK());
            ps.setString(3, "1");

            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                productoVirtual = rs.getInt(1);
            }
            return productoVirtual;
        } catch (MySQLIntegrityConstraintViolationException e) {
            System.out.println(e);
            return 0;
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
