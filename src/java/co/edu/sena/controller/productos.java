/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.controller;

import co.edu.sena.dto.InstructorDTO;
import co.edu.sena.dao.AutorDAO;
import co.edu.sena.dao.CategoriaDAO;
import co.edu.sena.dao.CategoriasTemasDAO;
import co.edu.sena.dao.InstructoresDAO;
import co.edu.sena.dao.ProductoVirtualDAO;
import co.edu.sena.dto.ProductoVirtualDTO;
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
public class productos extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String direccion = request.getServletPath();
        
        switch(direccion){
                
        case "/Productos":
                
                Productos(request, response);
                
                break;
                
//         case "/getAutoresPro":
//                
//                getAutoresPro(request, response);
//                
//                break;                
                
        }
    }

    private void Productos(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
                request.setCharacterEncoding("UTF-8");
                response.setCharacterEncoding("UTF-8");
                ConexionSer conexions = new ConexionSer();
                ProductoVirtualDAO productoVirtualDAO = new ProductoVirtualDAO(conexions.getConnection());
                ArrayList<ProductoVirtualDTO> lista = productoVirtualDAO.getProductos();
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
