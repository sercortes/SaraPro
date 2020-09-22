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
        
        String direccion = request.getServletPath();
        
        switch(direccion){
            
            case "/Instructores":
                
                getInstructores(request, response);
                
                break;
            case "/Categorias":
                
                getCategorias(request, response);
                
                break;
                
            case "/TemasCategorias":
                
                getTemasCategorias(request, response);
                
                break;
                
            case "/ProductosVirtualesTecnico":
                
                ProductosVirtualesTecnico(request, response);
                
                break;
                
          case "/ProductosVirtualesPedagogico":
                
                ProductosVirtualesPedagogico(request, response);
                
                break;
                
         case "/ProductosVirtualesCoor":
                
                ProductosVirtualesCoor(request, response);
                
                break;
                
         case "/getAutoresPro":
                
                getAutoresPro(request, response);
                
                break;
                
            case "/updatePassword":
                
                updatePass(request, response);
                
                break;
                
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

    private void ProductosVirtualesTecnico(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
                request.setCharacterEncoding("UTF-8");
                
                ConexionSer conexions = new ConexionSer();
                ProductoVirtualDAO productoVirtualDAO = new ProductoVirtualDAO(conexions.getConnection());
                
                String centroCoor = (String) request.getSession().getAttribute("idAreaCentro");
                
                ArrayList<?> lista = productoVirtualDAO.getProductosVirtualesTecnico(centroCoor);

                productoVirtualDAO.CloseAll();
                response.setContentType("application/json");
                new Gson().toJson(lista, response.getWriter());
        
    }

    private void ProductosVirtualesPedagogico(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {
        
                request.setCharacterEncoding("UTF-8");
                
                ConexionSer conexions = new ConexionSer();
                ProductoVirtualDAO productoVirtualDAO = new ProductoVirtualDAO(conexions.getConnection());
                
                String centroCoor = (String) request.getSession().getAttribute("idAreaCentro");
                
                ArrayList<?> lista = productoVirtualDAO.getProductosVirtualesPedagogico(centroCoor);

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
