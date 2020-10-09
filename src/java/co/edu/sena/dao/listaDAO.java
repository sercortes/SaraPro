/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.dao;

import co.edu.sena.dto.AutorDTO;
import co.edu.sena.dto.ListaDTO;
import co.edu.sena.dto.ListaItemDTO;
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
 * @author equipo
 */
public class listaDAO {
    
      private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    
    public listaDAO(Connection conn) {
        this.conn = conn;
    }
    
    
     public int insertReturnTwo(ListaDTO listaDTO) throws Exception{
        int productoVirtual = 0;
        String sql = "INSERT INTO item_lista (des_item_lista, tipo_item) "
                + "VALUES (?, ?)";

        try {
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, listaDTO.getDescripcion());
            ps.setString(2, listaDTO.getTipoItem());
            
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
     
       public ArrayList<ListaDTO> getListaTecnico(String centroCoor) {
        try {
            String sql = "SELECT l.*, f.nom_funcionario, f.apellidos FROM lista_chequeo l "
                    + "INNER JOIN funcionario f ON l.id_funcionario = f.id_funcionario WHERE l.Tipo = 1 AND f.id_area_centro = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, centroCoor);
            
            System.out.println(ps.toString());
            
            rs = ps.executeQuery();
            List<ListaDTO> list = new ArrayList<ListaDTO>();
            ListaDTO listaDTO;
            while (rs.next()) {
                
                listaDTO = new ListaDTO();
                listaDTO.setIdLista(rs.getString("id_lista_chequeo"));
                listaDTO.setNombre(rs.getString("nom_lista_chequeo"));
                listaDTO.setDescripcion(rs.getString("des_lista_chequeo"));
                listaDTO.setCreador(rs.getString("nom_funcionario") +" "+ rs.getString("apellidos"));
                
                list.add(listaDTO);
            }
            return (ArrayList<ListaDTO>) list;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
       
        public ArrayList<ListaDTO> getListaPedago(String centroCoor) {
        try {
            String sql = "SELECT l.*, f.nom_funcionario, f.apellidos FROM lista_chequeo l "
                    + "INNER JOIN funcionario f ON l.id_funcionario = f.id_funcionario WHERE l.Tipo = 2 AND f.id_area_centro = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, centroCoor);
            
            System.out.println(ps.toString());
            
            rs = ps.executeQuery();
            List<ListaDTO> list = new ArrayList<ListaDTO>();
            ListaDTO listaDTO;
            while (rs.next()) {
                
                listaDTO = new ListaDTO();
                listaDTO.setIdLista(rs.getString("id_lista_chequeo"));
                listaDTO.setNombre(rs.getString("nom_lista_chequeo"));
                listaDTO.setDescripcion(rs.getString("des_lista_chequeo"));
                listaDTO.setCreador(rs.getString("nom_funcionario") +" "+ rs.getString("apellidos"));
                
                list.add(listaDTO);
            }
            return (ArrayList<ListaDTO>) list;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
         public int insertListaGeneral(ListaDTO listaDTO) throws Exception{
        int productoVirtual = 0;
        String sql = "INSERT INTO lista_chequeo (nom_lista_chequeo, des_lista_chequeo, Tipo, id_funcionario)"
                + "VALUES (?, ?, ?, ?)";

        try {
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, listaDTO.getNombre());
            ps.setString(2, listaDTO.getDescripcion());
            ps.setString(3, listaDTO.getTipoItem());
            ps.setString(4, listaDTO.getCreador());

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
         
          public int insertDetallesLista(ListaItemDTO listaItemDTO) throws Exception{
        int productoVirtual = 0;
        String sql = "INSERT INTO detalles_lista (id_lista_chequeo, id_item_lista)"
                + "VALUES (?, ?)";

        try {
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, listaItemDTO.getIdListaFK());
            ps.setString(2, listaItemDTO.getIdItem());

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
        
            public ArrayList<ListaItemDTO> getItemsLista(String idLista) {
        try {
            String sql = "SELECT It.*, De.* FROM lista_chequeo Lc " +
                    "INNER JOIN detalles_lista De ON Lc.id_lista_chequeo=De.id_lista_chequeo " +
                    "INNER JOIN item_lista It ON De.id_item_lista=It.id_item_lista " +
                    "WHERE Lc.id_lista_chequeo = ? ORDER BY De.id_detalles_lista ASC";
            ps = conn.prepareStatement(sql);
            ps.setString(1, idLista);
            
            rs = ps.executeQuery();
            List<ListaItemDTO> list = new ArrayList<ListaItemDTO>();
            ListaItemDTO listaDTO;
            while (rs.next()) {
                listaDTO = new ListaItemDTO();
                listaDTO.setIdItem(rs.getString("id_item_lista"));
                listaDTO.setNombre(rs.getString("des_item_lista"));
                listaDTO.setIdListaFK(rs.getString("id_detalles_lista"));
                list.add(listaDTO);
            }
            return (ArrayList<ListaItemDTO>) list;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
            
               public ArrayList<ListaItemDTO> getItemsCalificados(String idEvaluacion, String idItemLista) {
        try {
            String sql = "SELECT DE.observacion FROM detalles_evaluacion DE "
                    + "WHERE id_evaluacion_general = ? AND id_detalles_lista = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, idEvaluacion);
            ps.setString(2, idItemLista);
            
            rs = ps.executeQuery();
            List<ListaItemDTO> list = new ArrayList<ListaItemDTO>();
            ListaItemDTO listaDTO;
            while (rs.next()) {
                listaDTO = new ListaItemDTO();
                listaDTO.setIdItem(rs.getString("observacion"));
                list.add(listaDTO);
            }
            return (ArrayList<ListaItemDTO>) list;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
      public ArrayList<ListaItemDTO> getItems() {
        try {
            String sql = "SELECT id_item_lista, des_item_lista, tipo_item FROM item_lista ORDER BY id_item_lista ASC LIMIT 50";
            ps = conn.prepareStatement(sql);
            
            rs = ps.executeQuery();
            List<ListaItemDTO> list = new ArrayList<ListaItemDTO>();
            ListaItemDTO listaDTO;
            while (rs.next()) {
                listaDTO = new ListaItemDTO();
                listaDTO.setIdItem(rs.getString("id_item_lista"));
                listaDTO.setNombre(rs.getString("des_item_lista"));
                list.add(listaDTO);
            }
            return (ArrayList<ListaItemDTO>) list;
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
