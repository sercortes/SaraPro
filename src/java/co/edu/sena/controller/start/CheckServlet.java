/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.controller.start;

import java.io.IOException;
import java.io.PrintWriter;
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
    
    @Schedule(hour="12", minute="20", second="0", persistent=false)
    public void someDailyJob() {
        System.out.println("DAY");
    }
  
//    @Schedule(hour="*", minute="*", second="*/10", persistent=false)
//    public void someFiveSecondelyJob() {
//        System.out.println("ddjdkjk");
//        // Do your job here which should run every 5 seconds.
//    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
