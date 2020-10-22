///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package co.edu.sena.controller.start;
//
//import co.edu.sena.dao.AutorDAO;
//import co.edu.sena.dao.ProductoVirtualDAO;
//import co.edu.sena.dto.InstructorDTO;
//import co.edu.sena.dto.ProductoVirtualDTO;
//import co.edu.sena.util.ConexionSer;
//import co.edu.sena.util.DJCorreoHTML;
//import com.google.gson.Gson;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.sql.Timestamp;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.concurrent.TimeUnit;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.ejb.Schedule;
//import javax.ejb.Singleton;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// *
// * @author equipo
// */
//@Singleton
//public class CheckServlet extends HttpServlet {
//
////     @Schedule(hour="*", minute="*", second="*")
////    public void someDailyJobs() {
////        System.out.println("DAY");
////    }
////    
//    @Schedule(hour = "2", minute = "8", second = "0", persistent = false)
//    public void someDailyJob() {
//        System.out.println("-----Disable Products--------");
//        System.out.println(disabledProducts());
//        System.out.println("");
//        getNotificationProductsByDisabled();
//    }
//
////    @Schedule(hour="*", minute="*", second="*/10", persistent=false)
////    public void someFiveSecondelyJob() {
////        System.out.println("ddjdkjk");
////        // Do your job here which should run every 5 seconds.
////    }
//    
//    public String getNotificationProductsByDisabled(){
//    
//        System.out.println("NOTIFICAION PRODUCTS TO DISABLED");
//        
//        ProductoVirtualDAO productoVirtualDAO = null;
//        AutorDAO autorDAO = null;
//        Connection conn = null;
//
//        try {
//
//            ConexionSer conexions = new ConexionSer();
//            DJCorreoHTML dJCorreoHTML = new DJCorreoHTML();
//            conn = conexions.getConnection();
//
//            if (conn.getAutoCommit()) {
//                conn.setAutoCommit(false);
//            }
//
//            productoVirtualDAO = new ProductoVirtualDAO(conn);
//            autorDAO = new AutorDAO(conn);
//            ArrayList<ProductoVirtualDTO> lista = null;
//            ArrayList<InstructorDTO> autores = null;
//            lista = productoVirtualDAO.getNotificationToday();
//
//            for (ProductoVirtualDTO item : lista) {
//                
//                System.out.println(item.toString());
//                autores = autorDAO.getAutoresByVersion(item.getVersioDTO().getIdVersion());
//                String nombrePV = autorDAO.getNamePV(item.getVersioDTO().getIdVersion());
//                
//                DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy hh.mm aa");
//                String dateString2 = dateFormat2.format(item.getVersioDTO().getFechaVigencia()).toString();
//                
//                for(InstructorDTO itemI : autores){
//                    dJCorreoHTML.NotificacionDisabled(itemI.getCorreo(), 
//                            nombrePV, " Tiene una fecha limite de corrección para el día "+dateString2+
//                            ", recuerde realizar la respectiva corrección de lo contrario su producto quedara deshabilitado.", 
//                            nombrePV);
//                }
//                
//            }
//            
//
//            conn.commit();
//            return "Hay " + lista.size() + " Elementos notificados";
//
//        } catch (Exception ex) {
//
//            System.out.println("ROLL");
//            System.out.println(ex);
//
//            try {
//                conn.rollback();
//            } catch (SQLException ex1) {
//                System.out.println(ex1);
//            }
//            
//            return "ROLL";
//            
//        } finally {
//
//            productoVirtualDAO.CloseAll();
//            autorDAO.CloseAll();
//
//        }
//    
//    }
//    
//    public String disabledProducts() {
//
//        System.out.println("DISABLED PRODUCTS");
//            
//        ProductoVirtualDAO productoVirtualDAO = null;
//        AutorDAO autorDAO = null;
//        Connection conn = null;
//
//        try {
//
//            ConexionSer conexions = new ConexionSer();
//            DJCorreoHTML dJCorreoHTML = new DJCorreoHTML();
//            conn = conexions.getConnection();
//
//            if (conn.getAutoCommit()) {
//                conn.setAutoCommit(false);
//            }
//
//            productoVirtualDAO = new ProductoVirtualDAO(conn);
//            autorDAO = new AutorDAO(conn);
//            ArrayList<ProductoVirtualDTO> lista = null;
//            ArrayList<InstructorDTO> autores = null;
//            lista = productoVirtualDAO.getProductosLoser();
//
//            boolean status = false;
//
//            for (ProductoVirtualDTO item : lista) {
//                
//                System.out.println(item.toString());
//                item.getVersioDTO().setIdEstadoFK("7");
//                status = productoVirtualDAO.disabledStatus(item.getVersioDTO());
//                autores = autorDAO.getAutoresByVersion(item.getVersioDTO().getIdVersion());
//                String nombrePV = autorDAO.getNamePV(item.getVersioDTO().getIdVersion());
//                
//                for(InstructorDTO itemI : autores){
//                    dJCorreoHTML.NotificacionProducto(itemI.getCorreo(), nombrePV, "Ha sido deshabilitado, la fecha limite de corrección se ha vencido", nombrePV);
//                }
//                
//            }
//            
//
//            conn.commit();
//            System.out.println(status +" ESTADO");
//            return "Hay " + lista.size() + " Elementos deshabilitados";
//
//        } catch (Exception ex) {
//
//            System.out.println("ROLL");
//            System.out.println(ex);
//
//            try {
//                conn.rollback();
//            } catch (SQLException ex1) {
//                System.out.println(ex1);
//            }
//            
//            return "ROLL";
//            
//        } finally {
//
//            productoVirtualDAO.CloseAll();
//            autorDAO.CloseAll();
//
//        }
//        
//    }
//
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
//
//}
