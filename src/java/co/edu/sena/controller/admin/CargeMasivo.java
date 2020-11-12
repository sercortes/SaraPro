/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.controller.admin;

//import co.edu.sena.util.DJCorreoHTML;
import co.edu.sena.dto.InstructorDTO;
import co.edu.sena.dao.AreaCentroDAO;
import co.edu.sena.dao.InstructoresDAO;
import co.edu.sena.util.Read;
import co.edu.sena.util.ConexionSer;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.commons.lang3.RandomStringUtils;
/**
 *
 * @author equipo
 */
@MultipartConfig
public class CargeMasivo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            System.out.println("Cargue masivo no soporta GET");
            response.sendRedirect(request.getContextPath() + "/Home");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        String roles = request.getParameter("roles");
        String[] arrayRoles = roles.split(",");

        for (String item : arrayRoles) {
            System.out.println("Roles " + item);
        }

        String area = request.getParameter("area");
        String centro = request.getParameter("centro");

        System.out.println(area);
        System.out.println(centro);

        Part file = request.getPart("files");

        Read readxls = new Read();

        //validación del excel de acuerdo a su estructura
        if (!readxls.validationXls(file)) {
            System.out.println("Estructura excel");
            new Gson().toJson(2, response.getWriter());
            try {
                throw new Exception();
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }

        ArrayList<InstructorDTO> lista = readxls.readingXls(file);

        // comprobando que no existan objetos nulos
        for (InstructorDTO item : lista) {
            if (!readxls.validateFuncionario(item)) {
                new Gson().toJson(3, response.getWriter());
                // imprimiendo objetos por consola que contenga errores
                System.out.println("objeto error VACIO");
                System.out.println(item.toString());
                try {
                    throw new Exception();
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        }

        System.out.println("LISTA SUBIR");
        System.out.println(lista.size());

        for (InstructorDTO item : lista) {
            System.out.println(item.toString());
        }
        System.out.println("");
        System.out.println(lista.size() + " SIZE");

        if (lista.size() > 0) {

            registrar(lista, area, centro, request, response, arrayRoles);

        } else {
            System.out.println("Usuarios ya registrados");
            new Gson().toJson(4, response.getWriter());
        }

    }

    public void registrar(ArrayList<InstructorDTO> lista, String area, String centro,
            HttpServletRequest request, HttpServletResponse response, String[] roles) throws IOException {

        ConexionSer conexionSer = new ConexionSer();
        Connection conn = null;
//        DJCorreoHTML correoHTML = new DJCorreoHTML();

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
            String idAreaCen = "";
            String pass = "";

            if (idAreaCentro != 0) {
                idAreaCen = Integer.toString(idAreaCentro);
            } else {
                idAreaCentro = areaCentroDAO.insertReturn(area, centro);
                idAreaCen = Integer.toString(idAreaCentro);
            }

            for (InstructorDTO item : lista) {
                item.setIdAreaCentroFK(idAreaCen);
                pass = generatePassword();
                item.setPass(pass);
                item.setIdTipoDocumento("1");
                System.out.println(item.toString());
            }
            System.out.println("NUEVA");
            for (InstructorDTO item : lista) {
                int idUsuario = areaCentroDAO.insertFunctionario(item);
                item.setIdFuncionario(Integer.toString(idUsuario));
                for (String items : roles) {
                    areaCentroDAO.insertRoles(items, Integer.toString(idUsuario));
                }
            }

//            for (InstructorDTO item : lista) {
//                correoHTML.mandarCorreo(item.getCorreo(), "ACCESO A SARA PRO", item.getPass());
//            }

            conn.commit();
            System.out.println("Exito");
            new Gson().toJson(1, response.getWriter());

        } catch (SQLException sql) {
            System.out.println(sql);
            new Gson().toJson(5, response.getWriter());
            try {
                conn.rollback();
                System.out.println("ROLLL");
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        } catch (Exception ex) {
            System.out.println(ex);
            new Gson().toJson(6, response.getWriter());
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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
