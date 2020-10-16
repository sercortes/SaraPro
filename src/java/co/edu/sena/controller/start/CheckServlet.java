/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.controller.start;

import co.edu.sena.dao.ProductoVirtualDAO;
import co.edu.sena.dto.ProductoVirtualDTO;
import co.edu.sena.util.ConexionSer;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author equipo
 */
@Singleton
public class CheckServlet extends HttpServlet {

//     @Schedule(hour="*", minute="*", second="*")
//    public void someDailyJobs() {
//        System.out.println("DAY");
//    }
//    
    @Schedule(hour = "12", minute = "20", second = "0", persistent = false)
    public void someDailyJob() {
        System.out.println("-----Disable Products--------");
    }

//    @Schedule(hour="*", minute="*", second="*/10", persistent=false)
//    public void someFiveSecondelyJob() {
//        System.out.println("ddjdkjk");
//        // Do your job here which should run every 5 seconds.
//    }
    public String disabledProducts() {

        ProductoVirtualDAO productoVirtualDAO = null;
        ConexionSer conexions = new ConexionSer();
        Connection conn = null;

        try {

            conn = conexions.getConnection();

            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }

            productoVirtualDAO = new ProductoVirtualDAO(conn);
            ArrayList<ProductoVirtualDTO> lista = null;
            lista = productoVirtualDAO.getProductosLoser();

            boolean status = false;

            for (ProductoVirtualDTO item : lista) {
                System.out.println(item.toString());
                item.getVersioDTO().setIdEstadoFK("7");
                status = productoVirtualDAO.disabledStatus(item.getVersioDTO());
            }

            conn.commit();
            return "Hay " + lista.size() + " Elementos deshabilitados";

        } catch (Exception ex) {

            System.out.println("ROLL");
            System.out.println(ex);

            try {
                conn.rollback();
            } catch (SQLException ex1) {
                System.out.println(ex1);
            }
            
            return "ROLL";
            
        } finally {

            productoVirtualDAO.CloseAll();

        }
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
