/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.dao;

import co.edu.sena.dto.EvaluacionDTO;
import co.edu.sena.dto.detallesEvaluacionDTO;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import co.edu.sena.util.ConexionSer;

/**
 *
 * @author serfin
 */
public class DetallesEvaluacionDAO {
    
     private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    
    public DetallesEvaluacionDAO(Connection conn) {
        this.conn = conn;
    }
    
    public boolean insertDetallesEvaluacion(detallesEvaluacionDTO deEvaluacionDTO) {
        boolean estado = false;
        String sql = "INSERT INTO detalles_evaluacion (valorizacion, observacion, id_detalles_lista, "
                + "id_evaluacion_general)"
                + "VALUES (?, ?, ?, ?)";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, deEvaluacionDTO.getValoracion());
            ps.setString(2, deEvaluacionDTO.getObservacion());
            ps.setString(3, deEvaluacionDTO.getIdDetallesLista());
            ps.setString(4, deEvaluacionDTO.getId_evaluacion_general());

            int rows = ps.executeUpdate();
           
            estado = rows > 0;
            
            return estado;
        } catch (MySQLIntegrityConstraintViolationException e) {
            System.out.println(e);
            return false;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

    }
    
      public void CloseAll(){
        ConexionSer.close(conn);
        ConexionSer.close(ps);
        ConexionSer.close(rs);
    }
    
    
}
