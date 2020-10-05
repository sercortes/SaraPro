package co.edu.sena.util;

import java.io.File;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * Clase DJCorreoHTML se encarga de enviar orreo electronicos con la informacion
 * requerida para los usuarios de la aplicacion
 *
 * @author Juan lopez alvarez
 * @version 21/11/2017 v4
 */
public class DJCorreoHTML {

    Properties properties = new Properties();
    
    private final String urlClave = "http://sarapro.datasena.com:8080/sarapro-pruebasfinales/ResetPass";
    private final String urlClaveT = "http://localhost:8080/SaraPro/ResetPass";

    // La configuración para enviar correo
    public DJCorreoHTML() {

        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port", "25");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.user", "SaraPro53@gmail.com");
        properties.put("mail.password", "7848742juma");

    }

    public void mandarCorreo(String destinatario, String asunt, String contenido) throws Exception{
        String destinatarios = destinatario;
        String asunto = asunt;
        Session session = Session.getInstance(properties, null);

        try {
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress("SaraPro53@gmail.com", "Sara Pro"));
            InternetAddress[] internetAddresses = {new InternetAddress(
                destinatarios)};
            mimeMessage.setRecipients(Message.RecipientType.TO,
                    internetAddresses);
            // Agregar el asunto al correo
            mimeMessage.setSubject(asunto);
            // Crear el multipart para agregar la parte del mensaje anterior
            Multipart multipart = new MimeMultipart();
            // Leer la plantilla
            StringBuffer msjHTML = new StringBuffer("<html>\n"
                    + "    "
                    + "<body style=\"margin: 0; padding: 0;\">\n"
                    + "<table border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" 
                    +" \n" 
                    +"<tr>\n" 
                    +" \n" 
                    +" <td align=\"center\" style=\"padding: 40px 0 30px 0;\">\n" 
 //                   +" <img src=\"/src/java/M_Controller/Correos/logo.png\" />\n" 
                   +" <img src=\"http://img.fenixzone.net/i/Y4GqnUW.png\" alt=\"Creating Email Magic\" width=\"200\" height=\"100\" style=\"display: block;\" />\n" 
                    +" </td>\n" 
                    +" \n" 
                    +"</tr>\n" 
                    +" \n" 
                    +"<tr>\n" 
                    +" \n" 
                    +" <td  bgcolor=\"#218276\" >\n" 
                    + "        <div align=\"center\">\n"
                    + "            <font size=\"4\" face=\"Candara\" style=\"color:#FFFFFF;\" >     \n"
                    + "            Estimado Funcionario, le informamos que su usuario  fue creado satisfactoriamente su contraseña"
                    + "          asignada es:\n"
                    + "            <br>\n"
                    + "            </font>\n"
                    + "            <font size=\"5\" style=\"color:#FFFFFF;\" face=\"Candara\">     \n"
                    + "            <u>"  + contenido + "</u> \n"
                    + "            <br>\n"
                    + "            </font>\n"
                    + "            <font size=\"4\" face=\"Candara\" style=\"color:#FFFFFF;\">     \n"
                    + "            Estimado Funcionario, le recomendamos cambiar la contraseña por su seguridad"
                    + "            <br>\n"
                    + "            </font>\n"        
                    + "        </div>\n"
                    +" </td>\n" 
                    +" \n" 
                    +"</tr>\n" 
                    +" \n" 
                    +" \n" 
                    +"<tr>\n" 
                    +" \n" 
                    +" <td bgcolor=\"#ee4c50\">\n" 
                    +"      <div align=\"center\">\n"
                    + "            Gracias por su atención.\n"
                    + "            <br>\n"
                    + "            BIENVENIDO A SARAPRO.\n"
                    + "            </font>\n"
                    + "        </div>\n"
                    +" </td>\n" 
                    +" \n" 
                    +"</tr>\n" 
                    +" </table>"                            
                    +"      <div align=\"center\">\n"
                    + "         <p >Declinación de Responsabilidades: Los servicios de MISENA son soportados tecnológicamente por © Google y ofrecidos por el Servicio Nacional de Aprendizaje – SENA de manera gratuita a los aprendices e instructores de programas de formación titulada, las opiniones que contenga este mensaje son exclusivas de su autor y no representan la opinión del Servicio Nacional de Aprendizaje o de sus autoridades. El receptor deberá verificar posibles virus informáticos que tenga el correo o cualquier anexo, razón por la cual el SENA no es responsable de los daños causados por cualquier virus transmitido en este correo electrónico.\n" 
                    +"\n"    
                    +"Los contenidos, textos, imágenes, archivos enviados en este mensaje son responsabilidad exclusiva del remitente y no reflejan ni comprometen de ninguna manera a la institución. No se autoriza el uso de esta herramienta para el intercambio de correos masivos, cadenas o spam, ni de mensajes ofensivos, de carácter político, sexual o religioso, con fines de lucro, con propósitos delictivos o cualquier otro mensaje que se considere indebido o que vaya en contra de la Ley.</p>\n"
                    + "        </div>\n"
                    + "    </body>\n"
                    + "</html>");

            // Url del directorio donde estan las imagenes
            String urlImagenes = "inicio/ConfirmacionCon/img";
            File directorio = new File(urlImagenes);
            // Obtener los nombres de las imagenes en el directorio
            String[] imagenesDirectorio = directorio.list();
            // Creo la parte del mensaje HTML
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(msjHTML.toString(), "text/html");
            // Validar que el directorio si tenga las imagenes
            
            // Agregar la parte del mensaje HTML al multiPart
            multipart.addBodyPart(mimeBodyPart);

            // Agregar el multipart al cuerpo del mensaje
            mimeMessage.setContent(multipart);

            // Enviar el mensaje
            Transport transport = session.getTransport("smtp");
            transport.connect("sarapro.emails@gmail.com", "SaraPro2020_R");
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
            transport.close();

        } catch (Exception ex) {
            System.out.println(ex);
            throw new Exception();
        }
    }


    public void NotificacionProducto(String destinatario, String asunt, String estado, String nombrep) throws Exception{
        String destinatarios = destinatario;
        String asunto = asunt;
        Session session = Session.getInstance(properties, null);

        try {
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress("SaraPro53@gmail.com", "Sara Pro"));
            InternetAddress[] internetAddresses = {new InternetAddress(
                destinatarios)};
            mimeMessage.setRecipients(Message.RecipientType.TO,
                    internetAddresses);
            // Agregar el asunto al correo
            mimeMessage.setSubject(asunto);
            // Crear el multipart para agregar la parte del mensaje anterior
            Multipart multipart = new MimeMultipart();
            // Leer la plantilla
            StringBuffer msjHTML = new StringBuffer("<html>\n"
                    + "    "
                    + "<body style=\"margin: 0; padding: 0;\">\n"
                    + "<table border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" 
                    +" \n" 
                    +"<tr>\n" 
                    +" \n" 
                    +" <td align=\"center\" style=\"padding: 40px 0 30px 0;\">\n" 
 //                   +" <img src=\"/src/java/M_Controller/Correos/logo.png\" />\n" 
                   +" <img src=\"http://img.fenixzone.net/i/Y4GqnUW.png\" alt=\"Creating Email Magic\" width=\"200\" height=\"100\" style=\"display: block;\" />\n" 
                    +" </td>\n" 
                    +" \n" 
                    +"</tr>\n" 
                    +" \n" 
                    +"<tr>\n" 
                    +" \n" 
                    +" <td  bgcolor=\"#218276\" >\n" 
                    + "        <div align=\"center\">\n"
                    + "            <font size=\"4\" face=\"Candara\" style=\"color:#FFFFFF;\" >     \n"
                    + "            Estimado Funcionario, le informamos que el producto virtual <b>"+ nombrep +"</b> fué "+estado
                    + "          \n"
                    + "            <br>\n"
                    + "            </font>\n"
                    + "            <font size=\"5\" style=\"color:#FFFFFF;\" face=\"Candara\">     \n"
                    + "            <u></u> \n"
                    + "            </font>\n"
                    + "            <font size=\"4\" face=\"Candara\" style=\"color:#FFFFFF;\">     \n"
                    + "            Muchas Gracias por utilizar nuestro sistema."
                    + "            <br>\n"
                    + "            </font>\n"        
                    + "        </div>\n"
                    +" </td>\n" 
                    +" \n" 
                    +"</tr>\n" 
                    +" \n" 
                    +" \n" 
                    +"<tr>\n" 
                    +" \n" 
                    +" <td bgcolor=\"#ee4c50\">\n" 
                    +"      <div align=\"center\">\n"
                    + "            Gracias por su atención.\n"
                    + "            <br>\n"
                    + "            BIENVENIDO A SARAPRO.\n"
                    + "            </font>\n"
                    + "        </div>\n"
                    +" </td>\n" 
                    +" \n" 
                    +"</tr>\n" 
                    +" </table>"                            
                    +"      <div align=\"center\">\n"
                    + "         <p >Declinación de Responsabilidades: Los servicios de MISENA son soportados tecnológicamente por © Google y ofrecidos por el Servicio Nacional de Aprendizaje – SENA de manera gratuita a los aprendices e instructores de programas de formación titulada, las opiniones que contenga este mensaje son exclusivas de su autor y no representan la opinión del Servicio Nacional de Aprendizaje o de sus autoridades. El receptor deberá verificar posibles virus informáticos que tenga el correo o cualquier anexo, razón por la cual el SENA no es responsable de los daños causados por cualquier virus transmitido en este correo electrónico.\n" 
                    +"\n"    
                    +"Los contenidos, textos, imágenes, archivos enviados en este mensaje son responsabilidad exclusiva del remitente y no reflejan ni comprometen de ninguna manera a la institución. No se autoriza el uso de esta herramienta para el intercambio de correos masivos, cadenas o spam, ni de mensajes ofensivos, de carácter político, sexual o religioso, con fines de lucro, con propósitos delictivos o cualquier otro mensaje que se considere indebido o que vaya en contra de la Ley.</p>\n"
                    + "        </div>\n"
                    + "    </body>\n"
                    + "</html>");

            // Url del directorio donde estan las imagenes
            String urlImagenes = "inicio/ConfirmacionCon/img";
            File directorio = new File(urlImagenes);
            // Obtener los nombres de las imagenes en el directorio
            String[] imagenesDirectorio = directorio.list();
            // Creo la parte del mensaje HTML
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(msjHTML.toString(), "text/html");
            // Validar que el directorio si tenga las imagenes
            
            // Agregar la parte del mensaje HTML al multiPart
            multipart.addBodyPart(mimeBodyPart);

            // Agregar el multipart al cuerpo del mensaje
            mimeMessage.setContent(multipart);

            // Enviar el mensaje
            Transport transport = session.getTransport("smtp");
            transport.connect("sarapro.emails@gmail.com", "SaraPro2020_R");
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
            transport.close();

        } catch (Exception ex) {
            System.out.println(ex);
            throw new Exception();
        }
    }

    
       public void RestartClave(String destinatario, String asunt, String hash, String id) throws Exception{
        String destinatarios = destinatario;
        String asunto = asunt;
        Session session = Session.getInstance(properties, null);

        try {
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress("SaraPro53@gmail.com", "Sara Pro"));
            InternetAddress[] internetAddresses = {new InternetAddress(
                destinatarios)};
            mimeMessage.setRecipients(Message.RecipientType.TO,
                    internetAddresses);
            // Agregar el asunto al correo
            mimeMessage.setSubject(asunto);
            // Crear el multipart para agregar la parte del mensaje anterior
            Multipart multipart = new MimeMultipart();
            // Leer la plantilla
            StringBuffer msjHTML = new StringBuffer("<html>\n"
                    + "    "
                    + "<body style=\"margin: 0; padding: 0;\">\n"
                    + "<table border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" 
                    +" \n" 
                    +"<tr>\n" 
                    +" \n" 
                    +" <td align=\"center\" style=\"padding: 40px 0 30px 0;\">\n" 
 //                   +" <img src=\"/src/java/M_Controller/Correos/logo.png\" />\n" 
                   +" <img src=\"http://img.fenixzone.net/i/Y4GqnUW.png\" alt=\"Creating Email Magic\" width=\"200\" height=\"100\" style=\"display: block;\" />\n" 
                    +" </td>\n" 
                    +" \n" 
                    +"</tr>\n" 
                    +" \n" 
                    +"<tr>\n" 
                    +" \n" 
                    +" <td  bgcolor=\"#218276\" >\n" 
                    + "        <div align=\"center\">\n"
                    + "            <font size=\"4\" face=\"Candara\" style=\"color:#FFFFFF;\" >     \n"
                    + "            Estimado Funcionario, ¿ha olvidado su contraseña?. por favor ingrese al siguiente enlace.<b>"
                    + "            <form action=\""+this.urlClaveT+"\" method=\"POST\" >"
                    + "            <input type=\"hidden\" value=\""+hash+"\" name=\"hash\">"
                    + "            <input type=\"hidden\" value=\""+id+"\" name=\"id\">"
                    + "            <button type=\"submit\"  style=\"text-decoration:none;display:inline-block;font-family:Oracle Sans,sans-serif\" target=\"_blank\"><span class=\"il\">Restablecer</span> <span class=\"il\">contraseña</span></button>"
                    + "          \n"
                    + "            <br>\n"
                    + "            </form>\n"
                    + "            </font>\n"
                    + "            <font size=\"5\" style=\"color:#FFFFFF;\" face=\"Candara\">     \n"
                    + "            <u></u> \n"
                    + "            </font>\n"
                    + "            <font size=\"4\" face=\"Candara\" style=\"color:#FFFFFF;\">     \n"
                    + "            Muchas Gracias por utilizar nuestro sistema."
                    + "            <br>\n"
                    + "            </font>\n"        
                    + "        </div>\n"
                    +" </td>\n" 
                    +" \n" 
                    +"</tr>\n" 
                    +" \n" 
                    +" \n" 
                    +"<tr>\n" 
                    +" \n" 
                    +" <td bgcolor=\"#ee4c50\">\n" 
                    +"      <div align=\"center\">\n"
                    + "            Gracias por su atención.\n"
                    + "            <br>\n"
                    + "            BIENVENIDO A SARAPRO.\n"
                    + "            </font>\n"
                    + "        </div>\n"
                    +" </td>\n" 
                    +" \n" 
                    +"</tr>\n" 
                    +" </table>"                            
                    +"      <div align=\"center\">\n"
                    + "         <p >Declinación de Responsabilidades: Los servicios de MISENA son soportados tecnológicamente por © Google y ofrecidos por el Servicio Nacional de Aprendizaje – SENA de manera gratuita a los aprendices e instructores de programas de formación titulada, las opiniones que contenga este mensaje son exclusivas de su autor y no representan la opinión del Servicio Nacional de Aprendizaje o de sus autoridades. El receptor deberá verificar posibles virus informáticos que tenga el correo o cualquier anexo, razón por la cual el SENA no es responsable de los daños causados por cualquier virus transmitido en este correo electrónico.\n" 
                    +"\n"    
                    +"Los contenidos, textos, imágenes, archivos enviados en este mensaje son responsabilidad exclusiva del remitente y no reflejan ni comprometen de ninguna manera a la institución. No se autoriza el uso de esta herramienta para el intercambio de correos masivos, cadenas o spam, ni de mensajes ofensivos, de carácter político, sexual o religioso, con fines de lucro, con propósitos delictivos o cualquier otro mensaje que se considere indebido o que vaya en contra de la Ley.</p>\n"
                    + "        </div>\n"
                    + "    </body>\n"
                    + "</html>");

            // Url del directorio donde estan las imagenes
            String urlImagenes = "inicio/ConfirmacionCon/img";
            File directorio = new File(urlImagenes);
            // Obtener los nombres de las imagenes en el directorio
            String[] imagenesDirectorio = directorio.list();
            // Creo la parte del mensaje HTML
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(msjHTML.toString(), "text/html");
            // Validar que el directorio si tenga las imagenes
            
            // Agregar la parte del mensaje HTML al multiPart
            multipart.addBodyPart(mimeBodyPart);

            // Agregar el multipart al cuerpo del mensaje
            mimeMessage.setContent(multipart);

            // Enviar el mensaje
            Transport transport = session.getTransport("smtp");
            transport.connect("sarapro.emails@gmail.com", "SaraPro2020_R");
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
            transport.close();

        } catch (Exception ex) {
            System.out.println(ex);
            throw new Exception();
        }
    }

    
//    public static void main(String[] args) {
//        DJCorreoHTML dJCorreoHTML = new DJCorreoHTML();
//        dJCorreoHTML.NotificacionProducto("eevalenciano@misena.edu.co", ".....", "Creado", ".....");
//    }
    
}






//    public void mensajeContrasena(String destinatario, String asunt) {
//        String destinatarios = destinatario;
//        String asunto = asunt;
//        Session session = Session.getInstance(properties, null);
//
//        try {
//            MimeMessage mimeMessage = new MimeMessage(session);
//            mimeMessage.setFrom(new InternetAddress("sarapro.emails@gmail.com", "Sara Pro"));
//            InternetAddress[] internetAddresses = {new InternetAddress(
//                destinatarios)};
//            mimeMessage.setRecipients(Message.RecipientType.TO,
//                    internetAddresses);
//            // Agregar el asunto al correo
//            mimeMessage.setSubject(asunto);
//            // Crear el multipart para agregar la parte del mensaje anterior
//            Multipart multipart = new MimeMultipart();
//            // Leer la plantilla
//
//            StringBuffer msjHTML = new StringBuffer("<html>\n"
//                    + "    <body>\n"
//                    + "        <div align=\"center\">\n"
//                    + "            <img src=\"ConfirmacionCon/img/saraBlue.png\">              \n"
//                    + "        </div>\n"
//                    + "        <div align=\"center\">\n"
//                    + "            <font size=\"6\" face=\"Candara\">     \n"
//                    + "            Estimado usuario, le informamos que su contraseña ha sido modificada correctamente"
//                    + "            <br>\n"
//                    + "            si usted no ha realizado este cambio. Contáctese con el administrador del sistema."
//                    + "        </div>\n"
//                    + "    </body>\n"
//                    + "</html>");
//
//            // Url del directorio donde estan las imagenes
//            String urlImagenes = "ConfirmacionCon/img";
//            File directorio = new File(urlImagenes);
//            // Obtener los nombres de las imagenes en el directorio
//            String[] imagenesDirectorio = directorio.list();
//            // Creo la parte del mensaje HTML
//            MimeBodyPart mimeBodyPart = new MimeBodyPart();
//            mimeBodyPart.setContent(msjHTML.toString(), "text/html");
//            // Validar que el directorio si tenga las imagenes
//            if (imagenesDirectorio != null) {
//                for (int count = 0; count < imagenesDirectorio.length; count++) {
//                    MimeBodyPart imagePart = new MimeBodyPart();
//                    imagePart.setHeader("Content-ID", "<"
//                            + imagenesDirectorio[count] + ">");
//                    imagePart.setDisposition(MimeBodyPart.INLINE);
//                    imagePart.attachFile(urlImagenes
//                            + imagenesDirectorio[count]);
//                    multipart.addBodyPart(imagePart);
//                }
//            } else {
//                System.out.println("No hay imagenes en el directorio");
//            }
//
//            // Agregar la parte del mensaje HTML al multiPart
//            multipart.addBodyPart(mimeBodyPart);
//
//            // Agregar el multipart al cuerpo del mensaje
//            mimeMessage.setContent(multipart);
//
//            // Enviar el mensaje
//            Transport transport = session.getTransport("smtp");
//            transport.connect("sarapro.emails@gmail.com", "SaraPro2020_R");
//            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
//            transport.close();
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }