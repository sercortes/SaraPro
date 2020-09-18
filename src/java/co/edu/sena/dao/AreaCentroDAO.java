/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.dao;

import co.edu.sena.dto.InstructorDTO;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import co.edu.sena.util.ConexionSer;

/**
 *
 * @author equipo
 */
public class AreaCentroDAO {
    
       private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    
    public AreaCentroDAO(Connection conn) {
        this.conn = conn;
    }
    
    
         public int getAreaCentro(String area, String centro) throws Exception{
     
             try {
                 int rol = 0;
            String sql = "SELECT id_area_centro FROM area_centro WHERE id_area = ? AND id_centro = ? limit 1";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, area);
            ps.setString(2, centro);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                
                rol = rs.getInt("id_area_centro");
            }
            return rol;
        } catch (Exception e) {
            System.out.println(e);
            throw new Exception();
        }
         
     }
         
     public int insertReturn(String area, String centro) {
        int areaCentro = 0;
        String sql = "INSERT INTO area_centro (id_area, id_centro)"
                + "VALUES (?, ?)";

        try {
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, area);
            ps.setString(2, centro);

            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                areaCentro = rs.getInt(1);
            }
            return areaCentro;
        } catch (MySQLIntegrityConstraintViolationException e) {
            System.out.println(e);
            return 0;
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }

    }
     
     
     public int insertFunctionario(InstructorDTO instructorDTO) throws MySQLIntegrityConstraintViolationException, Exception {
        int idFuncionario = 0;
        String sql = "INSERT INTO funcionario (id_tipo_documento, num_documento, nom_funcionario, "
                + "apellidos, correo, cargo, ip_sena, contraseña, link, id_estado, id_area_centro)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, md5(?), ?, ?, ?)";

        try {
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, instructorDTO.getIdTipoDocumento());
            ps.setString(2, instructorDTO.getNumDocumento());
            ps.setString(3, instructorDTO.getNomFuncionario());
            ps.setString(4, instructorDTO.getApeFuncionario());
            ps.setString(5, instructorDTO.getCorreo());
            ps.setString(6, instructorDTO.getCargo());
            ps.setString(7, instructorDTO.getTelefono());
            ps.setString(8, instructorDTO.getPass());
            ps.setString(9, "");
            ps.setString(10, "1");
            ps.setString(11, instructorDTO.getIdAreaCentroFK());

            System.out.println(ps.toString());
            
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                idFuncionario = rs.getInt(1);
            }
            return idFuncionario;
        } catch (MySQLIntegrityConstraintViolationException e) {
            System.out.println(e);
            throw new MySQLIntegrityConstraintViolationException();
        } catch (Exception e) {
            System.out.println(e);
            throw new Exception();
        }

    }
     
       public int insertRoles(String idRol, String idUser) throws MySQLIntegrityConstraintViolationException, Exception {
        int idFuncionario = 0;
        String sql = "INSERT INTO rol_funcionariologin (id_rol, id_funcionario, vigencia)"
                + "VALUES (?, ?, ?)";

        try {
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, idRol);
            ps.setString(2, idUser);
            ps.setString(3, "1");

            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                idFuncionario = rs.getInt(1);
            }
            return idFuncionario;
        } catch (MySQLIntegrityConstraintViolationException e) {
            System.out.println(e);
            throw new MySQLIntegrityConstraintViolationException();
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
