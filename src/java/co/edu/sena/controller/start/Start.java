/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.controller.start;


import co.edu.sena.util.DJCorreoHTML;
import co.edu.sena.dto.InstructorDTO;
import co.edu.sena.dto.RolDTO;
import co.edu.sena.dao.CategoriaDAO;
import co.edu.sena.dao.InstructoresDAO;
import com.google.gson.Gson;
import com.mysql.jdbc.StringUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.RandomStringUtils;
import co.edu.sena.util.ConexionSer;

/**
 *
 * @author serfin
 */
public class Start extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String direccion = request.getServletPath();

        switch (direccion) {
            case "/setRolOne":

                setRolOne(request, response);

                break;

            case "/RedirectRol":

                RedirectRol(request, response);
                
                break;
                
            case "/Logout":

                Logout(request, response);

                break;

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String direccion = request.getServletPath();

        switch (direccion) {

            case "/Start":

                login(request, response);

                break;

            case "/Roles":

                getRoles(request, response);

                break;

            case "/setRolOne":

                setRolOne(request, response);

                break;

            case "/setMyRol":

                setMyRol(request, response);

                break;

            case "/ForgotPass":

                ForgotPass(request, response);

                break;

            case "/ResetPass":

                ResetPass(request, response);

                break;

            default:
                System.out.println("OPCION NO VÁLIDA");
                break;

        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");

        String correo = request.getParameter("user");
        String clave = request.getParameter("pass");

        System.out.println(correo);
        System.out.println(clave);

        HttpSession sesion = request.getSession();

        ConexionSer conexionSer = new ConexionSer();
        InstructoresDAO instructoresDAO = new InstructoresDAO(conexionSer.getConnection());
        InstructorDTO instructorDTO = instructoresDAO.login(correo, clave);

        response.setContentType("application/json");

        if (StringUtils.isNullOrEmpty(instructorDTO.getIdFuncionario())) {
            
            new Gson().toJson(0, response.getWriter());
            
        } else {
            
            instructorDTO.setIdCentroFK(instructoresDAO.getCentro(instructorDTO.getIdAreaCentroFK()));
            sesion.setAttribute("idUser", Integer.parseInt(instructorDTO.getIdFuncionario()));
            sesion.setAttribute("nomUser", instructorDTO.getNomFuncionario());
            sesion.setAttribute("idCentro", Integer.parseInt(instructorDTO.getIdCentroFK()));
            sesion.setAttribute("idAreaCentro", instructorDTO.getIdAreaCentroFK());
            
            System.out.println(instructorDTO.toString());
            
            new Gson().toJson(instructorDTO.getIdFuncionario(), response.getWriter());
        }
        
        instructoresDAO.CloseAll();

    }

    private void getRoles(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        request.setCharacterEncoding("UTF-8");
        ConexionSer conexionSer = new ConexionSer();
        String idFuncionario = request.getParameter("idFuncionario");
        InstructoresDAO instructoresDAO = new InstructoresDAO(conexionSer.getConnection());
        ArrayList<RolDTO> lista = instructoresDAO.getRoles(idFuncionario);
        instructoresDAO.CloseAll();
        response.setContentType("application/json");
        new Gson().toJson(lista, response.getWriter());
        
    }

    private void setRolOne(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        HttpSession sesion = request.getSession();
        
        if (sesion.getAttribute("idUser") == null) {
            
            request.getRequestDispatcher("index.jsp").forward(request, response);
            
        }else{
        
        ConexionSer conexionSer = new ConexionSer();
        InstructoresDAO instructoresDAO = new InstructoresDAO(conexionSer.getConnection());
        int idFun = (Integer) sesion.getAttribute("idUser");
        String rol = instructoresDAO.getRol(Integer.toString(idFun));
        System.out.println("ROL");
        System.out.println(rol);
        sesion.setAttribute("idRol", Integer.parseInt(rol));
        instructoresDAO.CloseAll();
        
        request.getRequestDispatcher("pages/home.jsp").forward(request, response);
            
        }
        
    }

    private void setMyRol(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");

        HttpSession sesion = request.getSession();

        String rol = request.getParameter("idRol");

        System.out.println(rol);

        sesion.setAttribute("idRol", Integer.parseInt(rol));

        request.getRequestDispatcher("pages/home.jsp").forward(request, response);
         

    }

    private void RedirectRol(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        HttpSession sesion = request.getSession();

        if (sesion.getAttribute("idRol") == null) {
            
            request.getRequestDispatcher("index.jsp").forward(request, response);
            
        } else {

            request.getRequestDispatcher("pages/home.jsp").forward(request, response);          

        }

    }

    private void ForgotPass(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {

        request.setCharacterEncoding("UTF-8");

        String iden = request.getParameter("iden");
        String pass = generatePassword();

        DJCorreoHTML dJCorreoHTML = new DJCorreoHTML();

        InstructorDTO instructorDTO = new InstructorDTO();
        instructorDTO.setNumDocumento(iden);
        instructorDTO.setLinkHash(pass);

        System.out.println(instructorDTO.toString());

        ConexionSer conexions = new ConexionSer();
        InstructoresDAO instructoresDAO = new InstructoresDAO(conexions.getConnection());
        boolean estado = instructoresDAO.updateHashPassword(instructorDTO);

        String correo = instructoresDAO.getEmail(iden);

        dJCorreoHTML.RestartClave(correo, "Clave Sara Pro", pass, iden);

        instructoresDAO.CloseAll();
        response.setContentType("application/json");
        new Gson().toJson(estado, response.getWriter());
    }

    private void ResetPass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String hash = request.getParameter("hash");
        String iden = request.getParameter("id");

        ConexionSer conexions = new ConexionSer();
        InstructoresDAO instructoresDAO = new InstructoresDAO(conexions.getConnection());
        
        int id = instructoresDAO.isEnableReset(iden, hash);
        
        System.out.println(hash);
        System.out.println(iden);
        System.out.println(id);
        
        HttpSession session = request.getSession();
        String mensaje = "";
        
        if (id!=0) {
            instructoresDAO.resetPass(iden);
            mensaje = "Tu contraseña ha sido restaurada por tu identificación. Muchas Gracias.";
            response.sendRedirect("start/passok.jsp");
        }else{
            mensaje = "Enlace vencido. Muchas Gracias.";
            response.sendRedirect("start/passok.jsp");
        }
        
        session.setAttribute("MESSAGE", mensaje);
        instructoresDAO.CloseAll();

    }
    
      private void Logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          
                request.getSession().removeAttribute("idUser");
                request.getSession().removeAttribute("nomUser");
                request.getSession().removeAttribute("idCentro");
                request.getSession().removeAttribute("idAreaCentro");
                request.getSession().removeAttribute("idRol");
                request.getSession().invalidate();
                
                request.getRequestDispatcher("index.jsp").forward(request, response);
          
    }


    public boolean isValid(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
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
