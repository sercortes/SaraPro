/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.dao;

import co.edu.sena.dto.AreaDTO;
import co.edu.sena.dto.AutorDTO;
import co.edu.sena.dto.CentroDTO;
import co.edu.sena.dto.DerechosAutorDTO;
import co.edu.sena.dto.InstructorDTO;
import co.edu.sena.dto.RolDTO;
import co.edu.sena.dto.TipoDocumento;
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
public class InstructoresDAO {

    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public InstructoresDAO(Connection conn) {
        this.conn = conn;
    }

    public InstructorDTO login(String usuario, String pass) {

        try {
            String sql = "SELECT id_funcionario, nom_funcionario, id_area_centro FROM funcionario WHERE correo = ? AND contraseña "
                    + "= md5(?) AND id_estado = 1 LIMIT 1";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            InstructorDTO instructorDTO = new InstructorDTO();
            while (rs.next()) {

                instructorDTO.setIdFuncionario(rs.getString("id_funcionario"));
                instructorDTO.setNomFuncionario(rs.getString("nom_funcionario"));
                instructorDTO.setIdAreaCentroFK(rs.getString("id_area_centro"));

            }
            return instructorDTO;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }

    public String getRol(String usuario) {

        try {
            String rol = "";
            String sql = "SELECT * FROM rol_funcionariologin WHERE id_funcionario = ? limit 1";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, usuario);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                rol = rs.getString("id_rol");
            }
            return rol;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }

    public String getCentro(String area) {

        try {
            String centro = "";
            String sql = "SELECT * FROM area_centro WHERE id_area_centro = ? limit 1";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, area);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                centro = rs.getString("id_centro");
            }
            return centro;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }

    public String getEmail(String identi) {

        try {
            String correo = "";
            String sql = "SELECT correo FROM funcionario WHERE num_documento = ? limit 1";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, identi);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                correo = rs.getString("correo");
            }
            return correo;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }

    public int checkDocument(String identi, String cor) {
        int id = 0;
        try {
            String correo = "";
            String sql = "SELECT id_funcionario FROM funcionario WHERE num_documento = ? OR correo = ? limit 1";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, identi);
            ps.setString(2, cor);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id_funcionario");
            }
            return id;
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }

    }

    public ArrayList<InstructorDTO> getInstructores(String AreaCentro) {
        try {
            String sql = "SELECT F.id_funcionario, nom_funcionario, apellidos, id_area_centro, cargo, count(*), RL.id_rol "
                    + "FROM funcionario F INNER JOIN rol_funcionariologin RL ON F.id_funcionario = RL.id_funcionario "
                    + "WHERE id_area_centro = ? AND RL.id_rol = 1 group by(F.id_funcionario) ORDER BY nom_funcionario ASC";
            ps = conn.prepareStatement(sql);
            ps.setString(1, AreaCentro);

            rs = ps.executeQuery();
            List<InstructorDTO> list = new ArrayList<InstructorDTO>();
            InstructorDTO instructorDTO;
            while (rs.next()) {
                instructorDTO = new InstructorDTO();
                instructorDTO.setIdFuncionario(rs.getString("id_funcionario"));
                instructorDTO.setNomFuncionario(rs.getString("nom_funcionario"));
                instructorDTO.setApeFuncionario(rs.getString("apellidos"));
                instructorDTO.setIdAreaCentroFK(rs.getString("id_area_centro"));
                list.add(instructorDTO);
            }
            return (ArrayList<InstructorDTO>) list;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public ArrayList<InstructorDTO> getFuncionarios() {
        try {
            String sql = "SELECT F.id_funcionario, F.nom_funcionario, F.apellidos, F.correo, "
                    + "(select a.nom_area from area a where id_area = AC.id_area) 'area', "
                    + "(SELECT c.nom_centro from centro c where id_centro = AC.id_centro) 'centro' "
                    + "FROM funcionario F "
                    + "INNER JOIN area_centro AC ON F.id_area_centro = AC.id_area_centro "
                    + "INNER JOIN rol_funcionariologin RL ON F.id_funcionario = RL.id_funcionario "
                    + "WHERE RL.id_rol IN (1,2,3) group by(F.id_funcionario) ORDER BY id_funcionario ASC";
            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();
            List<InstructorDTO> list = new ArrayList<InstructorDTO>();
            InstructorDTO instructorDTO;
            while (rs.next()) {
                instructorDTO = new InstructorDTO();
                instructorDTO.setIdFuncionario(rs.getString("id_funcionario"));
                instructorDTO.setNomFuncionario(rs.getString("nom_funcionario"));
                instructorDTO.setApeFuncionario(rs.getString("apellidos"));
                instructorDTO.setCorreo(rs.getString("F.correo"));
                instructorDTO.setArea(rs.getString("area"));
                instructorDTO.setCentro(rs.getString("centro"));
                
                list.add(instructorDTO);
            }
            return (ArrayList<InstructorDTO>) list;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

     public InstructorDTO getFuncionarioOne(String id) {
        try {
            String sql = "SELECT F.id_funcionario, F.nom_funcionario, F.apellidos, F.correo, "
                    + "(select a.nom_area from area a where id_area = AC.id_area) 'area', "
                    + "(SELECT c.nom_centro from centro c where id_centro = AC.id_centro) 'centro' "
                    + "FROM funcionario F "
                    + "INNER JOIN area_centro AC ON F.id_area_centro = AC.id_area_centro "
                    + "INNER JOIN rol_funcionariologin RL ON F.id_funcionario = RL.id_funcionario "
                    + "WHERE RL.id_rol IN (1,2,3) AND F.id_funcionario = ? group by(F.id_funcionario) LIMIT 1 ";
            
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);

            rs = ps.executeQuery();
            InstructorDTO instructorDTO = new InstructorDTO();
            while (rs.next()) {
                instructorDTO.setIdFuncionario(rs.getString("id_funcionario"));
                instructorDTO.setNomFuncionario(rs.getString("nom_funcionario"));
                instructorDTO.setApeFuncionario(rs.getString("apellidos"));
                instructorDTO.setCorreo(rs.getString("F.correo"));
                instructorDTO.setArea(rs.getString("area"));
                instructorDTO.setCentro(rs.getString("centro"));
                
            }
            return instructorDTO;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    
    public ArrayList<RolDTO> getRoles(String idFuncionario) {
        try {
            String sql = "SELECT * FROM rol R INNER JOIN rol_funcionariologin RF "
                    + "ON R.id_rol = RF.id_rol where RF.id_funcionario = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, idFuncionario);

            rs = ps.executeQuery();
            List<RolDTO> list = new ArrayList<RolDTO>();
            RolDTO rolDTO;
            while (rs.next()) {
                rolDTO = new RolDTO();
                rolDTO.setIdRol(rs.getString("id_rol"));
                rolDTO.setNombreRol(rs.getString("nom_rol"));
                list.add(rolDTO);
            }
            return (ArrayList<RolDTO>) list;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public ArrayList<TipoDocumento> getTiposDoc() {
        try {
            String sql = "SELECT id_tipo_documento, nom_tipo_documento FROM tipo_documento";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            List<TipoDocumento> list = new ArrayList<TipoDocumento>();
            TipoDocumento tipoDocumento;
            while (rs.next()) {
                tipoDocumento = new TipoDocumento();
                tipoDocumento.setIdDocumento(rs.getString("id_tipo_documento"));
                tipoDocumento.setNombre(rs.getString("nom_tipo_documento"));
                list.add(tipoDocumento);
            }
            return (ArrayList<TipoDocumento>) list;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public ArrayList<CentroDTO> getCentros() {
        try {
            String sql = "SELECT id_centro, nom_centro FROM centro";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            List<CentroDTO> list = new ArrayList<CentroDTO>();
            CentroDTO centroDTO;
            while (rs.next()) {
                centroDTO = new CentroDTO();
                centroDTO.setIdCentro(rs.getString("id_centro"));
                centroDTO.setNombreCentro(rs.getString("nom_centro"));
                list.add(centroDTO);
            }
            return (ArrayList<CentroDTO>) list;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public ArrayList<AreaDTO> getAreas() {
        try {
            String sql = "SELECT id_area, nom_area FROM area";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            List<AreaDTO> list = new ArrayList<AreaDTO>();
            AreaDTO areaDTO;
            while (rs.next()) {
                areaDTO = new AreaDTO();
                areaDTO.setIdArea(rs.getString("id_area"));
                areaDTO.setNomArea(rs.getString("nom_area"));
                list.add(areaDTO);
            }
            return (ArrayList<AreaDTO>) list;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public ArrayList<DerechosAutorDTO> getDerechosAutor() {
        try {
            String sql = "SELECT id_derecho, nombre, descripcion, imagen FROM derechos_autor";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            List<DerechosAutorDTO> list = new ArrayList<DerechosAutorDTO>();
            DerechosAutorDTO derechosAutorDTO;
            while (rs.next()) {
                derechosAutorDTO = new DerechosAutorDTO();
                derechosAutorDTO.setIdDerecho(rs.getString("id_derecho"));
                derechosAutorDTO.setNombre(rs.getString("nombre"));
                derechosAutorDTO.setDescripcion(rs.getString("descripcion"));
                derechosAutorDTO.setImagen(rs.getString("imagen"));
                list.add(derechosAutorDTO);
            }
            return (ArrayList<DerechosAutorDTO>) list;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public boolean updatePass(InstructorDTO instructorDTO) {
        try {

            String sql = "UPDATE funcionario set contraseña = md5(?) "
                    + "WHERE id_funcionario = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, instructorDTO.getPass());
            ps.setString(2, instructorDTO.getIdFuncionario());

            int rows = ps.executeUpdate();
            boolean estado = rows > 0;
            return estado;
        } catch (Exception ex) {
            System.out.println("Error edit " + ex.getMessage());
            return false;
        }
    }

    public boolean updateHashPassword(InstructorDTO instructorDTO) throws Exception {
        try {

            String sql = "UPDATE funcionario set link = md5(?) "
                    + "WHERE num_documento = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, instructorDTO.getLinkHash());
            ps.setString(2, instructorDTO.getNumDocumento());

            System.out.println(ps.toString());

            int rows = ps.executeUpdate();
            boolean estado = rows > 0;
            return estado;
        } catch (Exception ex) {
            System.out.println("Error edit " + ex.getMessage());
            throw new Exception();
        }
    }

    public long isEnableReset(String iden, String hash) {

        try {
            long id = 0;
            String sql = "SELECT num_documento FROM funcionario WHERE num_documento = ? AND link = md5(?) limit 1";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, iden);
            ps.setString(2, hash);

            System.out.println(ps.toString());

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                id = rs.getInt("num_documento");
            }
            return id;
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }

    }

    public boolean resetPass(String identifi) {
        try {

            String sql = "UPDATE funcionario set contraseña = md5(?), link = '' "
                    + "WHERE num_documento = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, identifi);
            ps.setString(2, identifi);

            System.out.println(ps.toString());

            int rows = ps.executeUpdate();
            boolean estado = rows > 0;
            return estado;
        } catch (Exception ex) {
            System.out.println("Error edit " + ex.getMessage());
            return false;
        }
    }

    public void CloseAll() {
        ConexionSer.close(conn);
        ConexionSer.close(ps);
        ConexionSer.close(rs);
    }

}
