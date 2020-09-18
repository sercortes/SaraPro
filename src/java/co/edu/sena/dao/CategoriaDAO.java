/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.dao;


import co.edu.sena.dto.CategoriaDTO;
import co.edu.sena.dto.InstructorDTO;
import co.edu.sena.util.ConexionSer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author serfin
 */
public class CategoriaDAO {
    
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    
    public CategoriaDAO(Connection conn) {
        this.conn = conn;
    }
    
   public ArrayList<CategoriaDTO> getCategorias(int centro) {
        try {
            String sql = "SELECT id_categoria, nom_categoria, id_centro from categoria WHERE id_centro = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, centro);
            
            rs = ps.executeQuery();
            List<CategoriaDTO> list = new ArrayList<CategoriaDTO>();
            CategoriaDTO categoriaDTO;
            while (rs.next()) {
                categoriaDTO = new CategoriaDTO();
                categoriaDTO.setIdCategoria(rs.getString("id_categoria"));
                categoriaDTO.setNombreCategoria(rs.getString("nom_categoria"));
                list.add(categoriaDTO);
            }
            return (ArrayList<CategoriaDTO>) list;
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
