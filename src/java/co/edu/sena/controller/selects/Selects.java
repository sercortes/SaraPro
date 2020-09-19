/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.controller.selects;



import co.edu.sena.dto.EvaluacionDTO;
import co.edu.sena.dto.NotificacionDTO;
import co.edu.sena.dao.AutorDAO;
import co.edu.sena.dao.EvaluacionDAO;
import co.edu.sena.dao.InstructoresDAO;
import co.edu.sena.dao.NotificacionDAO;
import co.edu.sena.dao.listaDAO;
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
 * @author equipo
 */
public class Selects extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String direccion = request.getServletPath();

        switch (direccion) {

            case "/SelectsTipoDocu":

                SelectsTipoDocu(request, response);

                break;

            case "/SelectsCentros":

                SelectsCentros(request, response);

                break;

            case "/SelectsArea":

                SelectsArea(request, response);

                break;

            case "/getNotificationsCorre":

                getNotificationsCorre(request, response);

                break;
                
                
            case "/getEvaluation":

                getEvaluation(request, response);

                break;
                
            case "/getItemsList":

                getItemsList(request, response);

                break;
                
            case "/getItemsCalificados":

                getItemsCalificados(request, response);

                break;
                
                             
            case "/getNotificationsGeneral":

                getNotificationsGeneral(request, response);

                break;


        }

    }


    private void SelectsTipoDocu(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {

        request.setCharacterEncoding("UTF-8");

        ConexionSer conexions = new ConexionSer();
        InstructoresDAO instructoresDAO = new InstructoresDAO(conexions.getConnection());

        ArrayList<?> autores = instructoresDAO.getTiposDoc();

        instructoresDAO.CloseAll();
        response.setContentType("application/json");
        new Gson().toJson(autores, response.getWriter());

    }

    private void SelectsCentros(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {

        request.setCharacterEncoding("UTF-8");

        ConexionSer conexions = new ConexionSer();
        InstructoresDAO instructoresDAO = new InstructoresDAO(conexions.getConnection());

        ArrayList<?> autores = instructoresDAO.getCentros();

        instructoresDAO.CloseAll();
        response.setContentType("application/json");
        new Gson().toJson(autores, response.getWriter());

    }

    private void SelectsArea(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {

        request.setCharacterEncoding("UTF-8");

        ConexionSer conexions = new ConexionSer();
        InstructoresDAO instructoresDAO = new InstructoresDAO(conexions.getConnection());

        ArrayList<?> autores = instructoresDAO.getAreas();

        instructoresDAO.CloseAll();
        response.setContentType("application/json");
        new Gson().toJson(autores, response.getWriter());

    }

    private void getNotificationsCorre(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
              request.setCharacterEncoding("UTF-8");

        ConexionSer conexions = new ConexionSer();
        NotificacionDAO notificacionDAO = new NotificacionDAO(conexions.getConnection());

         int idUser = (Integer) request.getSession().getAttribute("idUser");
        
        ArrayList<?> autores = notificacionDAO.getCorrecionByInstructor(Integer.toString(idUser));

        notificacionDAO.CloseAll();
        response.setContentType("application/json");
        new Gson().toJson(autores, response.getWriter());
        
    }

    private void getEvaluation(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {
        
          request.setCharacterEncoding("UTF-8");

        ConexionSer conexions = new ConexionSer();
        EvaluacionDAO evaluacionDAO = new EvaluacionDAO(conexions.getConnection());

         String id = request.getParameter("idEva");
        
        EvaluacionDTO autores = evaluacionDAO.getEvaluation(id);

        evaluacionDAO.CloseAll();
        response.setContentType("application/json");
        new Gson().toJson(autores, response.getWriter());
        
    }

    private void getItemsList(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {
            request.setCharacterEncoding("UTF-8");

        ConexionSer conexions = new ConexionSer();
        listaDAO liDAO = new listaDAO(conexions.getConnection());

         String id = request.getParameter("id");
        
        ArrayList<?> autores = liDAO.getItemsLista(id);

        liDAO.CloseAll();
        response.setContentType("application/json");
        new Gson().toJson(autores, response.getWriter());
        
    }

    private void getItemsCalificados(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
         request.setCharacterEncoding("UTF-8");

        ConexionSer conexions = new ConexionSer();
        listaDAO liDAO = new listaDAO(conexions.getConnection());

         String idEvaluacion = request.getParameter("idEvaluacion");
         String idItemLista = request.getParameter("idItem");
        
        ArrayList<?> autores = liDAO.getItemsCalificados(idEvaluacion, idItemLista);

        liDAO.CloseAll();
        response.setContentType("application/json");
        new Gson().toJson(autores, response.getWriter());
        
    }

    private void getNotificationsGeneral(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
            request.setCharacterEncoding("UTF-8");

        ConexionSer conexions = new ConexionSer();
        NotificacionDAO notificacionDAO = new NotificacionDAO(conexions.getConnection());

         int idUser = (Integer) request.getSession().getAttribute("idUser");
        
        ArrayList<NotificacionDTO> autores = notificacionDAO.getNotificationGeneral(Integer.toString(idUser));

        notificacionDAO.CloseAll();
        response.setContentType("application/json");
        new Gson().toJson(autores, response.getWriter());
        
    }
    
        @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>


    
}
