/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.controller.list;

import co.edu.sena.dto.ListaDTO;
import co.edu.sena.dto.ListaItemDTO;
import co.edu.sena.dao.CategoriaDAO;
import co.edu.sena.dao.listaDAO;
import co.edu.sena.util.ConexionSer;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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

/**
 *
 * @author equipo
 */
public class ManagementList extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Gson gson = new Gson();

        try {

            String direccion = request.getServletPath();

            switch (direccion) {

                case "/CreateItems":

                    createItems(request, response);

                    break;
                case "/getList":

                    getList(request, response);

                    break;

                case "/CreateList":

                    CreateList(request, response);

                    break;

                case "/getItems":

                    getItems(request, response);

                    break;

            }
        } catch (Exception ex) {
            gson.toJson(0, response.getWriter());
            System.out.println(ex);
        }

    }

    private void createItems(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Gson gson = new Gson();
        response.setContentType("application/json");
        request.setCharacterEncoding("UTF-8");

        String descripcion = request.getParameter("descripcion");

        ListaDTO listaDTO = new ListaDTO();
        listaDTO.setDescripcion(descripcion);
        listaDTO.setTipoItem("0");

        ConexionSer conexionSer = new ConexionSer();
        Connection conn = conexionSer.getConnection();

        listaDAO liDAO = null;
        try {
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            liDAO = new listaDAO(conn);

            int resul = liDAO.insertReturnTwo(listaDTO);
            listaDTO.setIdLista(Integer.toString(resul));
            conn.commit();
            gson.toJson(listaDTO, response.getWriter());
        } catch (SQLException ex) {
            conn.rollback();
            System.out.println("ROLL back");
            System.out.println(ex);
            gson.toJson(0, response.getWriter());
        } finally {
            liDAO.CloseAll();
        }

    }

    private void getList(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("UTF-8");
        ConexionSer conexions = new ConexionSer();
        listaDAO liDAO = new listaDAO(conexions.getConnection());

        int rol = (Integer) request.getSession().getAttribute("idRol");
        String centroCoor = (String) request.getSession().getAttribute("idAreaCentro");

        System.out.println(centroCoor + " CENTRO");

        ArrayList<ListaDTO> listas = new ArrayList();

        if (rol == 2) {
            listas = liDAO.getListaTecnico(centroCoor);
        } else if (rol == 3) {
            listas = liDAO.getListaPedago(centroCoor);
        }

        liDAO.CloseAll();
        response.setContentType("application/json");
        new Gson().toJson(listas, response.getWriter());

    }

    private void CreateList(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        String items = request.getParameter("items");
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");

        ConexionSer conexions = new ConexionSer();
        Connection conn = conexions.getConnection();
        listaDAO liDAO = null;

        try {

            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }

            liDAO = new listaDAO(conn);

            int rol = (Integer) request.getSession().getAttribute("idRol");
            int idUser = (Integer) request.getSession().getAttribute("idUser");

            ListaDTO listaDTO = new ListaDTO();
            listaDTO.setNombre(nombre);
            listaDTO.setDescripcion(descripcion);
            listaDTO.setCreador(Integer.toString(idUser));

            if (rol == 2) {
                listaDTO.setTipoItem("1");
            } else if (rol == 3) {
                listaDTO.setTipoItem("2");
            }

            System.out.println(listaDTO.toString());

            int idLista = liDAO.insertListaGeneral(listaDTO);

            String[] arregloItems = items.split(",");

            for (String item : arregloItems) {
                ListaItemDTO listaItemDTO = new ListaItemDTO();
                listaItemDTO.setIdListaFK(Integer.toString(idLista));
                listaItemDTO.setIdItem(item);
                liDAO.insertDetallesLista(listaItemDTO);
            }

            conn.commit();
            new Gson().toJson(idLista, response.getWriter());
        } catch (Exception ex) {
            System.out.println("ROLLL BBACK");
            System.out.println(ex);
            new Gson().toJson(0, response.getWriter());
            try {
                conn.rollback();
            } catch (SQLException ex1) {
                System.out.println(ex1);
            } finally {
                liDAO.CloseAll();
            }

        }

    }

    private void getItems(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        ConexionSer conexions = new ConexionSer();
        listaDAO liDAO = new listaDAO(conexions.getConnection());

//        int rol = (Integer) request.getSession().getAttribute("idRol");
//        String centroCoor = (String) request.getSession().getAttribute("idAreaCentro");

        ArrayList<ListaItemDTO> listas = liDAO.getItems();

        liDAO.CloseAll();
        response.setContentType("application/json");
        new Gson().toJson(listas, response.getWriter());

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
