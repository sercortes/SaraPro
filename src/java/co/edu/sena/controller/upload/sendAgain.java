/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.controller.upload;

import co.edu.sena.util.ConexionSer;
import co.edu.sena.dto.AutorDTO;
import co.edu.sena.dto.DetallesNotificacionDTO;
import co.edu.sena.dto.DetallesTemaDTO;
import co.edu.sena.dto.NotificacionDTO;
import co.edu.sena.dto.ProductoVirtualDTO;
import co.edu.sena.dto.VersioDTO;
import co.edu.sena.dao.ProductoVirtualDAO;
import co.edu.sena.dao.VersionDAO;
import com.google.gson.Gson;
import com.mysql.jdbc.StringUtils;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author serfin
 */
public class sendAgain extends HttpServlet {

// private final String UPLOAD_DIRECTORY = "C:\\glassfish4\\glassfish\\domains\\domain1\\docroot\\files";
//    private final String SERVER_UPLOAD = "http://sarapro.datasena.com:8080/files/";
//
//    private static final long serialVersionUID = 1L;
    private final String UPLOAD_DIRECTORY = "/home/equipo/servers2/glassfish4/glassfish/domains/domain1/docroot/files/";

    private final String SERVER_UPLOAD = "http://192.168.0.6:8080/files/";
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idVer = request.getParameter("idVer");

        VersioDTO versioDTO = new VersioDTO();
        ConexionSer conexionSer = new ConexionSer();
        Connection connection = conexionSer.getConnection();
        ProductoVirtualDAO productoVirtualDAO = null;
        VersionDAO versionDAO = null;

        if (ServletFileUpload.isMultipartContent(request)) {

            try {

                if (connection.getAutoCommit()) {
                    connection.setAutoCommit(false);
                }

                //validate someone send correction before
                productoVirtualDAO = new ProductoVirtualDAO(connection);
                versionDAO = new VersionDAO(connection);

                int estatus = versionDAO.getStatus(idVer);

                System.out.println("ESTADO " + estatus);

                if (estatus != 9 && estatus != 10) {
                    System.out.println("Ya ha sido evaluado");
                    new Gson().toJson(2, response.getWriter());
                    throw new Exception();
                }

                versioDTO = productoVirtualDAO.getIdProductoVirtual(idVer);

                versioDTO.setIdVersion(idVer);

                String idProducto = versioDTO.getIdProductoVirtualFK();

                int newVersion = Integer.parseInt(versioDTO.getNumVersion()) + 1;

                List<FileItem> multiparts = new ServletFileUpload(
                        new DiskFileItemFactory()).parseRequest(request);

                for (FileItem item : multiparts) {
                    if (!item.isFormField()) {

                        String name = new File(item.getName()).getName();
                        File tempFile = new File(UPLOAD_DIRECTORY + File.separator + idProducto + File.separator + name);

                        FileUtils.cleanDirectory(tempFile.getParentFile());

                        if (tempFile.exists()) {
                            tempFile.delete();
                        }

                        item.write(tempFile);

                        versioDTO.setUrl(SERVER_UPLOAD + idProducto + File.separator + tempFile.getName());
                        versioDTO.setNumVersion(Integer.toString(newVersion));

                        int estado = Integer.parseInt(versioDTO.getIdEstadoFK());
                        System.out.println("ESTADO " + estado);
                        if (estado == 10) {
                            versioDTO.setIdEstadoFK("4");
                        } else {
                            versioDTO.setIdEstadoFK("3");
                        }

                    }
                }

                versionDAO.updateProductoVirtual(versioDTO);
//                boolean estado2 = versionDAO.deleteEvaluations(idVer);

                connection.commit();
                new Gson().toJson(1, response.getWriter());

            } catch (Exception e) {

                try {
                    System.out.println("ROLLL");
                    connection.rollback();
                } catch (SQLException ex) {
                    Logger.getLogger(sendAgain.class.getName()).log(Level.SEVERE, null, ex);
                }

                new Gson().toJson(0, response.getWriter());

            } finally {
                versionDAO.CloseAll();
                productoVirtualDAO.CloseAll();

            }

        } else {

            new Gson().toJson(0, response.getWriter());

        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
