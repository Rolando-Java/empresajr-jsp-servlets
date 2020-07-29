package util;

import constantes.MensajeError;
import excepciones.CorreoException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class Mailer {

    public static boolean enviarHTMLMail(String correoEmpresa, String contraseniaEmpresa, String correoCliente, String subtitle, String mailText) throws CorreoException {
        try {
            Properties props = new Properties();
            props.put("mail.smtp.from", correoEmpresa);
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", 587);
            props.put("mail.smtp.auth", true);
            props.put("mail.smtp.socketFactory.port", 587);
            props.put("mail.smtp.socketFactory.fallback", true);
            props.put("mail.smtp.starttls.enable", true);
            props.put("mail.smtp.starttls.required", true);
            props.put("mmail.smtp.ssl.enable", false);
            props.put("mail.smtp.debug", true);

            Session session = Session.getInstance(props,
                    new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(correoEmpresa, contraseniaEmpresa);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(correoEmpresa));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(correoCliente));
            message.setSubject(subtitle);
            message.setContent(mailText, "text/html; charset=utf-8");

            Transport.send(message);
        } catch (MessagingException ex) {
            throw new CorreoException(MensajeError.ENVIO_CORREO.getMensaje());
        }
        return true;
    }

}
