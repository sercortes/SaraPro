/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.controller.evaluateTC;

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
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import co.edu.sena.util.ConexionSer;
import co.edu.sena.util.DJCorreoHTML;

public class Evaluate extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = (Integer) request.getSession().getAttribute("idUser");
        String idUser = Integer.toString(id);
        int roll = (Integer) request.getSession().getAttribute("idRol");

        request.setCharacterEncoding("UTF-8");

        String[] arreglo = request.getParameterValues("detallesEvaluacion");
        String resultado = request.getParameter("resultado");
        String idlista = request.getParameter("idLista");
        String idVersion = request.getParameter("version");
        String observacion = request.getParameter("observacion");

        int res = Integer.parseInt(resultado);
        boolean resp = (res == 1) ? true : false;

        EvaluacionDTO evaluacionDTO = new EvaluacionDTO();
        evaluacionDTO.setObservacion(observacion);
        evaluacionDTO.setResultado(resultado);
        evaluacionDTO.setIdVersionFK(idVersion);
        evaluacionDTO.setIdListaChequeoFK(idlista);
        evaluacionDTO.setIdFuncionario(idUser);

        ConexionSer conexionSer = new ConexionSer();
        Connection conn = conexionSer.getConnection();

        try {
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        EvaluacionDAO evaluacionDAO = new EvaluacionDAO(conn);
        DetallesEvaluacionDAO detallesEvaluacionDAO = new DetallesEvaluacionDAO(conn);
        VersionDAO versionDAO = new VersionDAO(conn);
        NotificacionDAO notificacionDAO = new NotificacionDAO(conn);
        AutorDAO autorDAO = new AutorDAO(conn);
        DetallesNotificacionDAO detallesNotificacionDAO = new DetallesNotificacionDAO(conn);

        DJCorreoHTML dJCorreoHTML = new DJCorreoHTML();
        ArrayList<InstructorDTO> autores = new ArrayList<>();
        ArrayList<DetallesNotificacionDTO> listaDetallesNotify = new ArrayList<>();

        try {

            int estatus = versionDAO.getStatus(idVersion);

            System.out.println("ESTADO " + estatus);

            if (estatus != 3 && estatus != 4) {
                System.out.println("Ya ha sido evaluado");
                new Gson().toJson(3, response.getWriter());
                throw new Exception();
            }

            System.out.println(evaluacionDTO.toString());

            int idEvaluacion = evaluacionDAO.insertReturn(evaluacionDTO);

            String json = "";
            for (String item : arreglo) {
                json += item;
            }

            Gson gson = new Gson();
            detallesEvaluacionDTO[] detalles = gson.fromJson(json, detallesEvaluacionDTO[].class);

            for (detallesEvaluacionDTO item : detalles) {
                item.setId_evaluacion_general(Integer.toString(idEvaluacion));
                detallesEvaluacionDAO.insertDetallesEvaluacion(item);
            }

            System.out.println(Arrays.toString(detalles));

            VersioDTO versioDTO = new VersioDTO();
            versioDTO = getVersioDTO(resp, versioDTO, roll);
            versioDTO.setIdVersion(idVersion);
            versionDAO.updateStatus(versioDTO);

            System.out.println(versioDTO.toString());

            NotificacionDTO notificacionDTO = new NotificacionDTO();
            notificacionDTO = getNotificacionDTO(resp, notificacionDTO, roll);

            if (!resp) {
                notificacionDTO.setFKProductoVirtual(Integer.toString(idEvaluacion));
            } else {
                notificacionDTO.setFKProductoVirtual(idVersion);
            }

            notificacionDTO.setIdFuncionarioFK(idUser);
            int idNotify = notificacionDAO.insertReturnTwo(notificacionDTO);

            System.out.println(notificacionDTO.toString());

            autores = autorDAO.getAutoresByVersion(idVersion);

            String nombrePV = autorDAO.getNamePV(versioDTO.getIdVersion());

            System.out.println(autores.toString());

            for (InstructorDTO item : autores) {

                DetallesNotificacionDTO detallesNotificacionDTO = new DetallesNotificacionDTO();
                detallesNotificacionDTO.setIdFuncionarioFK(item.getIdFuncionario());
                detallesNotificacionDTO.setIdNotificacionFK(Integer.toString(idNotify));
                listaDetallesNotify.add(detallesNotificacionDTO);

                dJCorreoHTML.NotificacionProducto(item.getCorreo(), nombrePV, notificacionDTO.getDescripcionNotificacion(), nombrePV);
            }

            for (DetallesNotificacionDTO item : listaDetallesNotify) {
                detallesNotificacionDAO.insertReturnTwo(item);
            }

            System.out.println(listaDetallesNotify.toString());

            conn.commit();
            gson.toJson(1, response.getWriter());
        } catch (Exception ex) {
            new Gson().toJson(2, response.getWriter());
            System.out.println(ex);
            try {
                System.out.println("ROLL back evaluate");
                conn.rollback();
            } catch (SQLException ex1) {
                System.out.println(ex1);
            }
        } finally {
            evaluacionDAO.CloseAll();
            detallesEvaluacionDAO.CloseAll();
            versionDAO.CloseAll();
            notificacionDAO.CloseAll();
            autorDAO.CloseAll();
            detallesNotificacionDAO.CloseAll();
        }

    }

    public VersioDTO getVersioDTO(boolean aprobado, VersioDTO versioDTO, int rol) throws Exception {

        if (!aprobado) {

            if (rol == 2) {
                versioDTO.setIdEstadoFK("9");
            } else if (rol == 3) {
                versioDTO.setIdEstadoFK("10");
            } else {
                throw new Exception();
            }

        } else {

            if (rol == 2) {
                versioDTO.setIdEstadoFK("4");
            } else if (rol == 3) {
                versioDTO.setIdEstadoFK("5");
            } else {
                throw new Exception();
            }

        }

        return versioDTO;

    }

    public NotificacionDTO getNotificacionDTO(boolean aprobado, NotificacionDTO notificacionDTO, int rol) throws Exception {

        if (!aprobado) {

            if (rol == 2) {
                notificacionDTO.
                        setDescripcionNotificacion("Rechazado por el lider Técnico");
            } else if (rol == 3) {
                notificacionDTO.
                        setDescripcionNotificacion("Rechazado por el lider Pedagógico");
            } else {
                throw new Exception();
            }
            notificacionDTO.setIdTipoNotificacionFK("2");

        } else {

            if (rol == 2) {
                notificacionDTO.
                        setDescripcionNotificacion("Aprobado por el lider Técnico");
            } else if (rol == 3) {
                notificacionDTO.
                        setDescripcionNotificacion("Aprobado por el lider Pedagógico");
            } else {
                throw new Exception();
            }
            notificacionDTO.setIdTipoNotificacionFK("1");

        }

        return notificacionDTO;

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
