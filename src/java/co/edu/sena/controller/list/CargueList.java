/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.controller.list;

import co.edu.sena.dao.listaDAO;
import co.edu.sena.dto.ListaDTO;
import co.edu.sena.dto.ListaItemDTO;
import co.edu.sena.util.ConexionSer;
import co.edu.sena.util.Read;
import co.edu.sena.util.ReadXlsList;
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

/**
 *
 * @author equipo
 */
@MultipartConfig
public class CargueList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Cargue masivo no soporta GET");
        response.sendRedirect(request.getContextPath() + "/Home");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getSession().getAttribute("idUser") != null) {

            try {
                cargueListas(request, response);
            } catch (Exception ex) {
                System.out.println(ex);
            }

        } else {
            System.out.println("Sesión vencida");
            response.sendRedirect(request.getContextPath() + "/Home");
        }
    }

    private void cargueListas(HttpServletRequest request, HttpServletResponse response) throws Exception {

        response.setContentType("text/html;charset=UTF-8");
        Gson gson = new Gson();

        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        Part file = request.getPart("files");
        ReadXlsList readxls = new ReadXlsList();

        if (!readxls.validationXlsCebecera(file)) {
            System.out.println("MAL Estructura excel, Archivo no válido");
            new Gson().toJson(2, response.getWriter());
            throw new Exception();
        }

        if (!readxls.validationXlsNameDescription(file)) {
            System.out.println("Verifique nombre y descripción de la lista");
            new Gson().toJson(3, response.getWriter());
            throw new Exception();
        }

        if (!readxls.validationSizeandContent(file)) {
            System.out.println("Verifique los items estan demasiados cortos, o muy extensos");
            gson.toJson(4, response.getWriter());
            throw new Exception();
        }
        
        ArrayList<ListaDTO> lista = readxls.readXls(file);

        ConexionSer conexionSer = new ConexionSer();
        Connection conn = conexionSer.getConnection();;
        listaDAO liDAO = new listaDAO(conn);
        
        int id = liDAO.getIDLista(readxls.getNombre_lista(), readxls.getDescripcion_lista());
        
        if (id != 0) {
            gson.toJson(5, response.getWriter());
            throw new Exception();
        }
        if (lista.size()>50 || lista.size()<=2) {
            gson.toJson(6, response.getWriter());
            throw new Exception();
        }
        
        try {
            
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            
            liDAO = new listaDAO(conn);

            for (ListaDTO item : lista) {
                item.setIdLista(Integer.toString(liDAO.insertReturnTwo(item)));
            }

            System.out.println("LISTA NUEVA");
            for (ListaDTO item : lista) {
                System.out.println(item.toString());
            }

            int rol = (Integer) request.getSession().getAttribute("idRol");
            int idUser = (Integer) request.getSession().getAttribute("idUser");

            ListaDTO listaDTO = new ListaDTO();
            listaDTO.setNombre(readxls.getNombre_lista());
            listaDTO.setDescripcion(readxls.getDescripcion_lista());
            listaDTO.setCreador(Integer.toString(idUser));

            if (rol == 2) {
                listaDTO.setTipoItem("1");
            } else if (rol == 3) {
                listaDTO.setTipoItem("2");
            }

            System.out.println(listaDTO.toString());
            System.out.println(readxls.toString());
            int idLista = liDAO.insertListaGeneral(listaDTO);

            for (ListaDTO item : lista) {
                ListaItemDTO listaItemDTO = new ListaItemDTO();
                listaItemDTO.setIdListaFK(Integer.toString(idLista));
                listaItemDTO.setIdItem(item.getIdLista());
                liDAO.insertDetallesLista(listaItemDTO);
            }

            conn.commit();
            gson.toJson(1, response.getWriter());
        } catch (SQLException ex) {

            try {
                conn.rollback();
            } catch (SQLException ex1) {
                System.out.println(ex1);
            }
            System.out.println("ROLL back");
            System.out.println(ex);
            gson.toJson(7, response.getWriter());

        } catch (Exception ex) {

            try {
                conn.rollback();
            } catch (SQLException ex1) {
                System.out.println(ex1);
            }
            System.out.println("ROLL back");
            System.out.println(ex);
            gson.toJson(7, response.getWriter());

        } finally {
            liDAO.CloseAll();
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
