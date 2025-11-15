package Logic;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class GestorCorreosElectrónicos {
    private String emailFrom = "frepontime@gmail.com";
    private String password = "huhh evuo innn bfbe";
    private String asunto;
    private String cuerpo;

    private Properties props;
    private Session sesión;
    private MimeMessage msg;

    private void crearCorreo(String emailTo, String códigoVerificación, String usuario) {
        asunto = "Código de verificación para FreponTime";
        cuerpo =  "[" + códigoVerificación + "]"
                + "\nSaludos cordiales - Verificación de cuenta para: \"" + usuario
                + "\". \n\n\n\nAtentamente: \n\nFreponTime.";

        // Configuración del servidor de correo
        props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.user", emailFrom);
        props.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        props.setProperty("mail.smtp.auth", "true");

        // Crear una sesión de correo
        sesión = Session.getDefaultInstance(props);

        // Crea el mensaje
        msg = new MimeMessage(sesión);
        try {
            msg.setFrom(new InternetAddress(emailFrom));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
            msg.setSubject(asunto);
            msg.setHeader("Reply-To", emailFrom);
            msg.setText(cuerpo, "UTF-8", "plain");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    private void enviarCorreo() {
        //Envía el mensaje
        try {
            Transport transport = sesión.getTransport("smtp");
            transport.connect(emailFrom, password);
            transport.sendMessage(msg, msg.getRecipients(Message.RecipientType.TO));
            transport.close();
            System.out.print("\nSe envió el correo\n");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean enviarMensajeDeVerificaciónDeRegistro(String correo, String códigoVerificación, String usuario){
        // Verificar si el correo cumple con el dominio "@epn.edu.ec"
        if (!correo.endsWith("@epn.edu.ec")) {
            System.out.println("Error: El correo debe pertenecer a un estudiante de la EPN");
            return false;
        }
        crearCorreo(correo, códigoVerificación,usuario);
        enviarCorreo();
        return true;
    }

}
