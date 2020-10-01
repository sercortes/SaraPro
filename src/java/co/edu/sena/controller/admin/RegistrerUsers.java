/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.controller.admin;

import co.edu.sena.util.DJCorreoHTML;
import co.edu.sena.dto.InstructorDTO;
import co.edu.sena.dao.AreaCentroDAO;
import co.edu.sena.dao.InstructoresDAO;
import co.edu.sena.util.ConexionSer;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.RandomStringUtils;

/**
 *
 * @author equipo
 */
public class RegistrerUsers extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String direccion = request.getServletPath();

        switch (direccion) {

            case "/newUser":

                newUser(request, response);

                break;

        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void newUser(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {

        request.setCharacterEncoding("UTF-8");
        
        response.setContentType("application/json");

        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("ape");
        String tipoIdentificacion = request.getParameter("tipoIden");
        String identificacion = request.getParameter("id");
        String email = request.getParameter("email");
        String numero = request.getParameter("numero");
        String cargo = request.getParameter("cargo");

        String area = request.getParameter("area");
        String centro = request.getParameter("centro");
        String roles = request.getParameter("roles");

        InstructorDTO instructorDTO = new InstructorDTO();
        instructorDTO.setNomFuncionario(nombre);
        instructorDTO.setApeFuncionario(apellido);
        instructorDTO.setIdTipoDocumento(tipoIdentificacion);
        instructorDTO.setNumDocumento(identificacion);
        instructorDTO.setCorreo(email);
        instructorDTO.setTelefono(numero);
        instructorDTO.setCargo(cargo);

        ConexionSer conexionSer = new ConexionSer();
        Connection conn = null;

        InstructoresDAO instructoresDAO = null;
        AreaCentroDAO areaCentroDAO = null;

        try {

            conn = conexionSer.getConnection();

            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }

            instructoresDAO = new InstructoresDAO(conn);
            areaCentroDAO = new AreaCentroDAO(conn);

            int idAreaCentro = areaCentroDAO.getAreaCentro(area, centro);

            if (idAreaCentro != 0) {
                instructorDTO.setIdAreaCentroFK(Integer.toString(idAreaCentro));
            } else {
                idAreaCentro = areaCentroDAO.insertReturn(area, centro);
                instructorDTO.setIdAreaCentroFK(Integer.toString(idAreaCentro));
            }

            String pass = generatePassword();
            instructorDTO.setPass(pass);

            System.out.println(instructorDTO.toString());
            int idUsuario = areaCentroDAO.insertFunctionario(instructorDTO);
            System.out.println(idUsuario);

            String[] arreglo = roles.split(",");

            for (String item : arreglo) {
                areaCentroDAO.insertRoles(item, Integer.toString(idUsuario));
            }
            
            DJCorreoHTML correoHTML = new DJCorreoHTML();
            correoHTML.mandarCorreo(email, "ACCESO A SARA PRO", pass);

            conn.commit();
            System.out.println("Exito");
            new Gson().toJson(1, response.getWriter());

        } catch (SQLException sql) {
            System.out.println(sql);
            new Gson().toJson(2, response.getWriter());
            try {
                conn.rollback();
                System.out.println("ROLLL");
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        } catch (Exception ex) {
            System.out.println(ex);
            new Gson().toJson(3, response.getWriter());
            try {
                conn.rollback();
                System.out.println("ROLL");
            } catch (SQLException ex1) {
                System.out.println(ex1);
            }
        } finally {
            instructoresDAO.CloseAll();
            areaCentroDAO.CloseAll();
        }

    }

    public String generatePassword() {
        return RandomStringUtils.random(10, 0, 20, true, true, "qw32rfHIJk9iQ8Ud7h0X".toCharArray());
    }

}
