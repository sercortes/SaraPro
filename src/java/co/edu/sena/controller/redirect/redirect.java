/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.controller.redirect;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author equipo
 */
public class redirect extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String direccion = request.getServletPath();
        RequestDispatcher rd;
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

        switch (direccion) {
            case "/Home":

                rd = request.getRequestDispatcher("/pages/start/home.jsp");
                rd.forward(request, response);

                break;

            case "/Menu":

                rd = request.getRequestDispatcher("/pages/home.jsp");
                rd.forward(request, response);

                break;

            case "/Upload":

                rd = request.getRequestDispatcher("/pages/instructor/upload.jsp");
                rd.forward(request, response);

                break;

            case "/Search":

                rd = request.getRequestDispatcher("/pages/general/productos.jsp");
                rd.forward(request, response);

                break;

            case "/SendAgain":

                rd = request.getRequestDispatcher("/pages/instructor/corregir.jsp");
                rd.forward(request, response);

                break;

            case "/Notify":

                rd = request.getRequestDispatcher("/pages/instructor/notificaciones.jsp");
                rd.forward(request, response);

                break;
                
           case "/ChangePass":

                rd = request.getRequestDispatcher("/pages/general/changepass.jsp");
                rd.forward(request, response);

                break;
                
           case "/getProductos":

                rd = request.getRequestDispatcher("/pages/instructorTP/productos.jsp");
                rd.forward(request, response);

                break;
                
           case "/Listas":

                rd = request.getRequestDispatcher("/pages/instructorTP/listas.jsp");
                rd.forward(request, response);

                break;
                
           case "/ProductsCoor":

                rd = request.getRequestDispatcher("/pages/coordinador/productos.jsp");
                rd.forward(request, response);

                break;
                
            case "/Error500":

                rd = request.getRequestDispatcher("/pages/general/error500.jsp");
                rd.forward(request, response);

                break;
                
            case "/Error404":

                rd = request.getRequestDispatcher("/pages/general/error404.jsp");
                rd.forward(request, response);

                break;
                
            case "/addUser":

                rd = request.getRequestDispatcher("/pages/admin/add.jsp");
                rd.forward(request, response);

                break;

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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

    public static void checkSession(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        HttpSession sesion = request.getSession();

        if (sesion.getAttribute("idRol") == null) {

            request.getRequestDispatcher("index.jsp").forward(request, response);

        } else {

            request.getRequestDispatcher("pages/home.jsp").forward(request, response);

        }

    }

}
