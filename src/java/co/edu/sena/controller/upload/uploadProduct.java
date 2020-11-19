/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.controller.upload;

import co.edu.sena.util.DJCorreoHTML;
import co.edu.sena.util.ConexionSer;
import co.edu.sena.dto.AutorDTO;
import co.edu.sena.dto.DetallesNotificacionDTO;
import co.edu.sena.dto.DetallesTemaDTO;
import co.edu.sena.dto.InstructorDTO;
import co.edu.sena.dto.NotificacionDTO;
import co.edu.sena.dto.ProductoVirtualDTO;
import co.edu.sena.dto.VersioDTO;
import co.edu.sena.dao.AutorDAO;
import co.edu.sena.dao.DetallesNotificacionDAO;
import co.edu.sena.dao.DetallesTemaDAO;
import co.edu.sena.dao.NotificacionDAO;
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

public class uploadProduct extends HttpServlet {

//    private final String UPLOAD_DIRECTORY = "C:\\glassfish4\\glassfish\\domains\\domain1\\docroot\\files";
//    private final String SERVER_UPLOAD = "http://sarapro.datasena.com:8080/files/";
//    private static final long serialVersionUID = 1L;
    private final String UPLOAD_DIRECTORY = "/home/equipo/servers2/glassfish4/glassfish/domains/domain1/docroot/files/";
    private final String SERVER_UPLOAD = "http://192.168.0.9:8080/files/";
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("upload product no soporta GET");
        response.sendRedirect(request.getContextPath() + "/Home");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getSession().getAttribute("idUser") != null) {

            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");

            String categorias = request.getParameter("categorias");
            String autores = request.getParameter("autores");
            int idUser = (Integer) request.getSession().getAttribute("idUser");

            ArrayList<InstructorDTO> autoresEmail = new ArrayList<>();
            DJCorreoHTML correoHTML = new DJCorreoHTML();

//        ArrayList<DetallesNotificacionDTO> detallesAutoresNotificacion = new ArrayList<>();
            String folder = "";

            ProductoVirtualDTO productoVirtualDTO = new ProductoVirtualDTO();
            VersioDTO versioDTO = new VersioDTO();

            ConexionSer conexion = new ConexionSer();
            Connection conn = conexion.getConnection();

            ProductoVirtualDAO productoVirtualDAO = new ProductoVirtualDAO(conn);
            VersionDAO versionDAO = new VersionDAO(conn);
            AutorDAO autorDAO = new AutorDAO(conn);
            DetallesTemaDAO detallesTemaDAO = new DetallesTemaDAO(conn);
            NotificacionDAO notificacionDAO = new NotificacionDAO(conn);
            DetallesNotificacionDAO detallesNotificacionDAO = new DetallesNotificacionDAO(conn);

            if (ServletFileUpload.isMultipartContent(request)) {

                try {

                    if (conn.getAutoCommit()) {
                        conn.setAutoCommit(false);
                    }

                    List<FileItem> multiparts = new ServletFileUpload(
                            new DiskFileItemFactory()).parseRequest(request);

                    for (FileItem item : multiparts) {
                        if (item.isFormField()) {
                            switch (item.getFieldName()) {
                                case "nombreProducto":
                                    productoVirtualDTO.setNombre(item.getString("UTF-8"));
                                    break;
                                case "descripcion":
                                    productoVirtualDTO.setDescripcion(item.getString("UTF-8"));
                                    break;
                                case "palabrasClave":
                                    productoVirtualDTO.setPalabrasClave(item.getString("UTF-8"));
                                    break;
                                case "formatoArchivo":
                                    productoVirtualDTO.setFormato(item.getString("UTF-8"));
                                    break;
                                case "derechosAutor":
                                    productoVirtualDTO.setDerechosAutor(item.getString("UTF-8"));
                                    break;
                                case "instrucciones":
                                    versioDTO.setInstrucionesInstalacion(item.getString("UTF-8"));
                                    break;
                                case "requisitosInstalacion":
                                    versioDTO.setRequeInstalacion(item.getString("UTF-8"));
                                    break;
                            }
                        }
                    }

                    System.out.println(productoVirtualDTO.toString());
                    int idProducto = productoVirtualDAO.insertReturn(productoVirtualDTO);

                    folder = Integer.toString(idProducto);

                    readFiles(versioDTO, multiparts, folder);

                    System.out.println(versioDTO.toString());

                    int idVersion = versionDAO.insertReturn(versioDTO);

                    ArrayList<AutorDTO> listaAutores = new ArrayList<>();

                    AutorDTO autorDTO = new AutorDTO();
                    autorDTO.setIdFuncionarioFK(Integer.toString(idUser));
                    autorDTO.setIdVersionFK(Integer.toString(idVersion));
                    autorDTO.setPrincipal("1");
                    listaAutores.add(autorDTO);

                    String[] arreglo = autores.split(",");

                    if (!StringUtils.isNullOrEmpty(autores)) {

                        for (String item : arreglo) {
                            AutorDTO adto = new AutorDTO();
                            adto.setIdFuncionarioFK(item);
                            adto.setIdVersionFK(Integer.toString(idVersion));
                            adto.setPrincipal("1");
                            listaAutores.add(adto);
                        }
                    }

                    for (AutorDTO item : listaAutores) {
                        autorDAO.insertReturn(item);
                    }

                    autoresEmail = autorDAO.getAutoresByVersion(Integer.toString(idVersion));

                    ArrayList<DetallesTemaDTO> listacategorias = new ArrayList<>();
                    DetallesTemaDTO detallesTemaDTO;
                    String[] arreglotemas = categorias.split(",");

                    if (!StringUtils.isNullOrEmpty(categorias)) {

                        for (String item : arreglotemas) {
                            detallesTemaDTO = new DetallesTemaDTO();
                            detallesTemaDTO.setIdTemaFK(item);
                            detallesTemaDTO.setIdProductoFK(Integer.toString(idProducto));
                            listacategorias.add(detallesTemaDTO);
                        }
                    }

                    for (DetallesTemaDTO item : listacategorias) {
                        detallesTemaDAO.insertReturn(item);
                        System.out.println(item.toString());
                    }

                    for (InstructorDTO item : autoresEmail) {
                        System.out.println(item.toString());
                        correoHTML.NotificacionProducto(item.getCorreo(), productoVirtualDTO.getNombre(), "creado", productoVirtualDTO.getNombre());
                    }

                    conn.commit();
                    new Gson().toJson(1, response.getWriter());
                } catch (Exception e) {

                    System.out.println(e);

                    try {
                        conn.rollback();
                        System.out.println("ROLL back UPLOAD ");
                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }

                    new Gson().toJson(0, response.getWriter());

                } finally {
                    productoVirtualDAO.CloseAll();
                    versionDAO.CloseAll();
                    autorDAO.CloseAll();
                    detallesTemaDAO.CloseAll();
                    notificacionDAO.CloseAll();
                    detallesNotificacionDAO.CloseAll();
                }

            } else {
                System.out.println("request is invalid");
            }

        } else {

            System.out.println("Sesión vencida");
            response.sendRedirect(request.getContextPath() + "/Home");

        }

    }

    public VersioDTO readFiles(VersioDTO versioDTO, List<FileItem> multiparts, String folder) throws Exception {

        for (FileItem item : multiparts) {
            if (!item.isFormField()) {

                String name = new File(item.getName()).getName();
                File tempFile = new File(UPLOAD_DIRECTORY + File.separator + folder + File.separator + name);

                if (tempFile.exists()) {
                    tempFile.delete();
                }

                if (tempFile.getParentFile().mkdirs()) {
                    item.write(tempFile);
                } else {
                    System.out.println("Error escritura archivo");
                    System.out.println("ya existe el folder " + folder);
                    throw new Exception();
                    // item.write(tempFile);
                }

                versioDTO.setUrl(SERVER_UPLOAD + folder + File.separator + tempFile.getName());
                versioDTO.setIdProductoVirtualFK(folder);

            }
        }

        return versioDTO;

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
