/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.controller.coor;

import co.edu.sena.util.ConexionSer;
import co.edu.sena.dto.DetallesNotificacionDTO;
import co.edu.sena.dto.EvaluacionDTO;
import co.edu.sena.dto.InstructorDTO;
import co.edu.sena.dto.NotificacionDTO;
import co.edu.sena.dto.VersioDTO;
import co.edu.sena.dto.detallesEvaluacionDTO;
import co.edu.sena.dao.AutorDAO;
import co.edu.sena.dao.DetallesEvaluacionDAO;
import co.edu.sena.dao.DetallesNotificacionDAO;
import co.edu.sena.dao.EvaluacionDAO;
import co.edu.sena.dao.NotificacionDAO;
import co.edu.sena.dao.VersionDAO;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import co.edu.sena.util.DJCorreoHTML;

/**
 *
 * @author equipo
 */
public class EvaluateCoor extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Gson gson = new Gson();
        request.setCharacterEncoding("UTF-8");
       response.setContentType("application/json");
        HttpSession sesion = request.getSession();

        int idUser = (Integer) sesion.getAttribute("idUser");
        String comentario = request.getParameter("comentario");
        String idVersion = request.getParameter("idVersion");
        String resultado = request.getParameter("resultado");
        String aprobado = request.getParameter("aprobado");
        String rtecnico = request.getParameter("rtecnico");
        String rpedago = request.getParameter("rpedago");

        ConexionSer conexionSer = new ConexionSer();
        Connection conn = conexionSer.getConnection();

         DJCorreoHTML dJCorreoHTML = new DJCorreoHTML();
        ArrayList<InstructorDTO> autores = new ArrayList<>();
        ArrayList<DetallesNotificacionDTO> listaDetallesNotify = new ArrayList<>();

        EvaluacionDTO evaluacionDTO = new EvaluacionDTO();
        evaluacionDTO.setObservacion(comentario);
        evaluacionDTO.setResultado(resultado);
        evaluacionDTO.setIdVersionFK(idVersion);
        evaluacionDTO.setIdListaChequeoFK("17");
        evaluacionDTO.setIdFuncionario(Integer.toString(idUser));

        System.out.println(evaluacionDTO.toString());

        VersioDTO versioDTO = new VersioDTO();
        versioDTO.setIdVersion(idVersion);
        versioDTO.setIdEstadoFK(getStatus(aprobado, rtecnico, rpedago));

        System.out.println(versioDTO.toString());

        try {
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
        } catch (SQLException ex) {
            gson.toJson(false, response.getWriter());
            System.out.println(ex);
        }

        EvaluacionDAO evaluacionDAO = new EvaluacionDAO(conn);
        VersionDAO versionDAO = new VersionDAO(conn);
        NotificacionDAO notificacionDAO = new NotificacionDAO(conn);
        DetallesNotificacionDAO detallesNotificacionDAO = new DetallesNotificacionDAO(conn);
        AutorDAO autorDAO = new AutorDAO(conn);
        DetallesEvaluacionDAO detallesEvaluacionDAO = new DetallesEvaluacionDAO(conn);

        try {

           int estatus = versionDAO.getStatus(idVersion);

            System.out.println("ESTADO " + estatus);

            if (estatus != 5) {
                System.out.println("ESTADO INCORRECTO");
                new Gson().toJson(false, response.getWriter());
                throw new Exception();
            }
            
            versionDAO.updateStatus(versioDTO);

            int idEvaluacion = evaluacionDAO.insertReturn(evaluacionDTO);

            boolean resp = Boolean.parseBoolean(aprobado);
            NotificacionDTO notificacionDTO = new NotificacionDTO();
            notificacionDTO.setDescripcionNotificacion(getDescripcionNoti(versioDTO.getIdEstadoFK()));
            notificacionDTO = getNoti(notificacionDTO, resp, idEvaluacion, idVersion);
            notificacionDTO.setIdFuncionarioFK(Integer.toString(idUser));
            System.out.println(notificacionDTO.toString());

            int idNoti = notificacionDAO.insertReturnTwo(notificacionDTO);

            String nombrePV = autorDAO.getNamePV(versioDTO.getIdVersion());

            autores = autorDAO.getAutoresByVersion(idVersion);
            
            for (InstructorDTO item : autores) {

                DetallesNotificacionDTO detallesNotificacionDTO = new DetallesNotificacionDTO();
                detallesNotificacionDTO.setIdFuncionarioFK(item.getIdFuncionario());
                detallesNotificacionDTO.setIdNotificacionFK(Integer.toString(idNoti));
                listaDetallesNotify.add(detallesNotificacionDTO);

                dJCorreoHTML.NotificacionProducto(item.getCorreo(), nombrePV, notificacionDTO.getDescripcionNotificacion(), nombrePV);
            }

            System.out.println(autores.toString());

            for (DetallesNotificacionDTO item : listaDetallesNotify) {
                detallesNotificacionDAO.insertReturnTwo(item);
            }

            System.out.println(listaDetallesNotify.toString());
            
            detallesEvaluacionDTO deEvaluacionDTO = new detallesEvaluacionDTO();
            deEvaluacionDTO.setId_evaluacion_general(Integer.toString(idEvaluacion));
            deEvaluacionDTO.setValoracion("1");
            deEvaluacionDTO.setObservacion("");
            deEvaluacionDTO.setIdDetallesLista("68");

            detallesEvaluacionDAO.insertDetallesEvaluacion(deEvaluacionDTO);
            
            System.out.println(deEvaluacionDTO.toString());
            
            conn.commit();
            gson.toJson(true, response.getWriter());
        } catch (Exception ex) {
            System.out.println("ROLL");
            try {
                conn.rollback();
            } catch (SQLException ex1) {
                System.out.println(ex1);
            }
            gson.toJson(false, response.getWriter());
            System.out.println(ex);
        } finally {
            evaluacionDAO.CloseAll();
            versionDAO.CloseAll();
            notificacionDAO.CloseAll();
            detallesNotificacionDAO.CloseAll();
            autorDAO.CloseAll();
            detallesEvaluacionDAO.CloseAll();
        }

    }

 public NotificacionDTO getNoti(NotificacionDTO notificacionDTO, boolean estado, int idEval, String idver) {
        notificacionDTO.setFKProductoVirtual(Integer.toString(idEval));
        notificacionDTO.setIdTipoNotificacionFK("2");
        return notificacionDTO;
    }

    public String getStatus(String apro, String rtecnico, String rpedago) {
        String res = "";
        boolean estado = Boolean.parseBoolean(apro);
        boolean rtec = Boolean.parseBoolean(rtecnico);
        boolean rped = Boolean.parseBoolean(rpedago);
        if (estado) {
            res = "6";
        } else if (rtec) {
            res = "9";
        } else if (rped) {
            res = "10";
        }
        return res;
    }

    public String getDescripcionNoti(String res) {
        String respuesta = "";
        int estado = Integer.parseInt(res);
        if (estado == 6) {
            respuesta = "Aprobado por el coordinador";
        } else if(estado == 9) {
            respuesta = "Rechazado por el coordinador, para equipo técnico.";
        }else if(estado == 10){
            respuesta = "Rechazado por el coordinador, para equipo pedagógico.";
        }
        return respuesta;
    }

        @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
}
