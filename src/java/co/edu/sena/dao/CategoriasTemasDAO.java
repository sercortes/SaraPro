/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.dao;

import co.edu.sena.dto.CategoriaDTO;
import co.edu.sena.dto.TemasDTO;
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
public class CategoriasTemasDAO {
    
      private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    
    public CategoriasTemasDAO(Connection conn) {
        this.conn = conn;
    }
    
   public ArrayList<TemasDTO> getTemas(String categoria) {
        try {
            String sql = "SELECT DC.id_categoria, DC.id_tema, TE.nom_tema FROM detalles_categoria DC "
                    + "INNER JOIN tema TE ON DC.id_tema=TE.id_tema WHERE DC.id_categoria = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, categoria);
            
            rs = ps.executeQuery();
            List<TemasDTO> list = new ArrayList<TemasDTO>();
            TemasDTO temasDTO;
            while (rs.next()) {
                temasDTO = new TemasDTO();
                temasDTO.setIdTema(rs.getString("id_tema"));
                temasDTO.setNombreTema(rs.getString("nom_tema"));
                list.add(temasDTO);
            }
            return (ArrayList<TemasDTO>) list;
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
