/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.dao;

import co.edu.sena.dto.AutorDTO;
import co.edu.sena.dto.EvaluacionDTO;
import co.edu.sena.dto.InstructorDTO;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import co.edu.sena.util.ConexionSer;

/**
 *
 * @author serfin
 */
public class EvaluacionDAO {
    
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    
    public EvaluacionDAO(Connection conn) {
        this.conn = conn;
    }
    
    public int insertReturn(EvaluacionDTO evaluacionDTO) {
        int productoVirtual = 0;
        String sql = "INSERT INTO evaluacion_general (observacion, resultado, id_version, "
                + "id_lista_chequeo, id_funcionario)"
                + "VALUES (?, ?, ?, ?, ?)";

        try {
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, evaluacionDTO.getObservacion());
            ps.setString(2, evaluacionDTO.getResultado());
            ps.setString(3, evaluacionDTO.getIdVersionFK());
            ps.setString(4, evaluacionDTO.getIdListaChequeoFK());
            ps.setString(5, evaluacionDTO.getIdFuncionario());

            ps.executeUpdate();
            
            System.out.println(ps.toString());
            
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
    
     public EvaluacionDTO getEvaluation(String id) {

        try {
            String sql = "SELECT E.id_evaluacion_general, E.fecha_evaluacion, E.observacion, E.id_lista_chequeo "
                    + "FROM evaluacion_general E WHERE id_evaluacion_general = ? limit 1";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            EvaluacionDTO evaluacionDTO = new EvaluacionDTO();
            while (rs.next()) {
                
                evaluacionDTO.setIdEvaluacion(rs.getString("E.id_evaluacion_general"));
                evaluacionDTO.setFechaEvaluacion(rs.getDate("E.fecha_evaluacion"));
                evaluacionDTO.setObservacion(rs.getString("E.observacion"));
                evaluacionDTO.setIdListaChequeoFK(rs.getString("E.id_lista_chequeo"));

            }
            return evaluacionDTO;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }
    
      public void CloseAll(){
        ConexionSer.close(conn);
        ConexionSer.close(ps);
        ConexionSer.close(rs);
    }
    
    
}
