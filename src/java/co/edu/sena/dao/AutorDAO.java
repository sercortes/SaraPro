/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.dao;

import co.edu.sena.dto.AutorDTO;
import co.edu.sena.dto.InstructorDTO;
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
public class AutorDAO {
    
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    
    public AutorDAO(Connection conn) {
        this.conn = conn;
    }
    
    public int insertReturn(AutorDTO autorDTO) throws Exception{
        int productoVirtual = 0;
        String sql = "INSERT INTO autor (id_version, id_funcionario, principal)"
                + "VALUES (?, ?, ?)";

        try {
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, autorDTO.getIdVersionFK());
            ps.setString(2, autorDTO.getIdFuncionarioFK());
            ps.setString(3, autorDTO.getPrincipal());

            ps.executeUpdate();
            
            System.out.println(ps.toString());
            
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
    
    public String getNamePV(String id){
       
        String name = "";
       
        try{
            String sql = "SELECT pv.nom_p_virtual FROM producto_virtual pv "
                    + "INNER JOIN version v ON pv.id_p_virtual=v.id_p_virtual WHERE v.id_version = ?";
              ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            
            rs = ps.executeQuery();
            while (rs.next()) {
                name = rs.getString("nom_p_virtual");
            }
            return name;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
    
    public ArrayList<InstructorDTO> getAutoresByVersion(String idVersion) {
        try {
            String sql = "SELECT A.*, F.* FROM autor A "
                    + "INNER JOIN funcionario F ON A.id_funcionario = F.id_funcionario where A.id_version = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, idVersion);
            
            rs = ps.executeQuery();
            List<InstructorDTO> list = new ArrayList<InstructorDTO>();
            InstructorDTO instructorDTO;
            while (rs.next()) {
                instructorDTO = new InstructorDTO();
                
                instructorDTO.setIdFuncionario(rs.getString("id_funcionario"));
                instructorDTO.setNomFuncionario(rs.getString("nom_funcionario"));
                instructorDTO.setApeFuncionario(rs.getString("apellidos"));
                instructorDTO.setCorreo(rs.getString("correo"));
       
                list.add(instructorDTO);
            }
            return (ArrayList<InstructorDTO>) list;
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
