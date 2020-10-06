/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.controller.selects;

import co.edu.sena.controller.redirect.redirect;
import co.edu.sena.dto.InstructorDTO;
import co.edu.sena.dao.AutorDAO;
import co.edu.sena.dao.CategoriaDAO;
import co.edu.sena.dao.CategoriasTemasDAO;
import co.edu.sena.dao.InstructoresDAO;
import co.edu.sena.dao.ProductoVirtualDAO;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import co.edu.sena.util.ConexionSer;

/**
 *
 * @author serfin
 */
public class Instructores extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getSession().getAttribute("idUser") != null) {
            
            String direccion = request.getServletPath();

            switch (direccion) {

                case "/Instructores":

                    getInstructores(request, response);

                    break;
                case "/Categorias":

                    getCategorias(request, response);

                    break;

                case "/TemasCategorias":

                    getTemasCategorias(request, response);

                    break;

                case "/ProductosVirtuales":

                    ProductosVirtuales(request, response);

                    break;

                case "/getAutoresPro":

                    getAutoresPro(request, response);

                    break;

                case "/updatePassword":

                    updatePass(request, response);

                    break;

                case "/ProductosVirtualesCoor":

                    ProductosVirtualesCoor(request, response);

                    break;

                case "/getSizeNotification":

                    getSizeNotification(request, response);

                    break;

            }
        } else {

            System.out.println("No Valores de sesión Clase Instructores");

        }
    }

    private void getInstructores(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");

        ConexionSer conexions = new ConexionSer();
        InstructoresDAO instructoresDAO = new InstructoresDAO(conexions.getConnection());

        String centroCoor = (String) request.getSession().getAttribute("idAreaCentro");

        ArrayList<?> aprendices = instructoresDAO.getInstructores(centroCoor);

        instructoresDAO.CloseAll();
        response.setContentType("application/json");
        new Gson().toJson(aprendices, response.getWriter());
    }

    private void getCategorias(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {

        request.setCharacterEncoding("UTF-8");

        ConexionSer conexions = new ConexionSer();
        CategoriaDAO categoriaDAO = new CategoriaDAO(conexions.getConnection());

        int idcentro = (Integer) request.getSession().getAttribute("idCentro");

        ArrayList<?> aprendices = categoriaDAO.getCategorias(idcentro);

        categoriaDAO.CloseAll();
        response.setContentType("application/json");
        new Gson().toJson(aprendices, response.getWriter());

    }

    private void getTemasCategorias(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {

        request.setCharacterEncoding("UTF-8");

        ConexionSer conexions = new ConexionSer();
        CategoriasTemasDAO categoriasTemasDAO = new CategoriasTemasDAO(conexions.getConnection());

        String Categoria = request.getParameter("id");

        ArrayList<?> temas = categoriasTemasDAO.getTemas(Categoria);

        categoriasTemasDAO.CloseAll();
        response.setContentType("application/json");
        new Gson().toJson(temas, response.getWriter());

    }

    private void updatePass(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {

        request.setCharacterEncoding("UTF-8");

        ConexionSer conexions = new ConexionSer();
        InstructoresDAO instructoresDAO = new InstructoresDAO(conexions.getConnection());

        String pass = request.getParameter("pass");
        int id = (Integer) request.getSession().getAttribute("idUser");

        boolean estado = instructoresDAO.updatePass(new InstructorDTO(Integer.toString(id), pass));

        instructoresDAO.CloseAll();
        response.setContentType("application/json");
        new Gson().toJson(estado, response.getWriter());

    }

    private void ProductosVirtuales(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("UTF-8");

        ConexionSer conexions = new ConexionSer();
        ProductoVirtualDAO productoVirtualDAO = new ProductoVirtualDAO(conexions.getConnection());

        String centroCoor = (String) request.getSession().getAttribute("idAreaCentro");
        int idRol = (Integer) request.getSession().getAttribute("idRol");
        System.out.println("Lista con rol " + idRol);
        ArrayList<?> lista = null;

        if (idRol == 2) {
            lista = productoVirtualDAO.getProductosVirtualesTecnico(centroCoor);
        } else if (idRol == 3) {
            lista = productoVirtualDAO.getProductosVirtualesPedagogico(centroCoor);
        }

        productoVirtualDAO.CloseAll();
        response.setContentType("application/json");
        new Gson().toJson(lista, response.getWriter());

    }

    private void getAutoresPro(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {

        request.setCharacterEncoding("UTF-8");

        ConexionSer conexions = new ConexionSer();
        AutorDAO autorDAO = new AutorDAO(conexions.getConnection());

        String idVersio = request.getParameter("id");

        ArrayList<?> autores = autorDAO.getAutoresByVersion(idVersio);

        autorDAO.CloseAll();
        response.setContentType("application/json");
        new Gson().toJson(autores, response.getWriter());

    }

    private void ProductosVirtualesCoor(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("UTF-8");

        ConexionSer conexions = new ConexionSer();
        ProductoVirtualDAO productoVirtualDAO = new ProductoVirtualDAO(conexions.getConnection());

        int centroCoor = (Integer) request.getSession().getAttribute("idCentro");

        ArrayList<?> lista = productoVirtualDAO.getProductosVirtualesCoor(Integer.toString(centroCoor));

        productoVirtualDAO.CloseAll();
        response.setContentType("application/json");
        new Gson().toJson(lista, response.getWriter());

    }

    private void getSizeNotification(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("UTF-8");

        ConexionSer conexions = new ConexionSer();
        ProductoVirtualDAO productoVirtualDAO = new ProductoVirtualDAO(conexions.getConnection());

        String centroCoor = (String) request.getSession().getAttribute("idAreaCentro");
        int idRol = (Integer) request.getSession().getAttribute("idRol");
        System.out.println("productos con rol " + idRol);
        int size = 0;

        if (idRol == 2) {
            size = productoVirtualDAO.getSizeTecnico(centroCoor);
        } else if (idRol == 3) {
            size = productoVirtualDAO.getSizePedagogo(centroCoor);
        } else if (idRol == 4) {
            size = productoVirtualDAO.getSizeCoor(centroCoor);
        }

        productoVirtualDAO.CloseAll();
        response.setContentType("application/json");
        new Gson().toJson(size, response.getWriter());

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
