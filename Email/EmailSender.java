package T1_Layouts.SendMail.Email;

import T1_Layouts.SendMail.ConfigManager;

import java.util.*;
import javax.mail.*;
import java.io.File;
import javax.swing.*;
import javax.activation.*;
import javax.mail.internet.*;

public class EmailSender {

    private final ConfigManager configManager;

    public EmailSender(ConfigManager configManager) {
        this.configManager = configManager;
    }

    public void sendEmail(String to, String bcc, String cc, String subject, String content, List<File> attachments) {
        String usrEmail = configManager.getProperty("app_email");
        String usrPasswd = configManager.getProperty("app_pass");
        String smtpHost = configManager.getProperty("smtp_host");
        String smtpPort = configManager.getProperty("smtp_port");

        if (usrEmail == null || usrPasswd == null) {
            JOptionPane.showMessageDialog(null, "Error: Falta configuración de correo.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Properties properties = getProperties(smtpHost, smtpPort, usrEmail, usrPasswd);
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usrEmail, usrPasswd);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(usrEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            if (!cc.isEmpty()) {
                message.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));
            }
            if (!bcc.isEmpty()) {
                message.addRecipient(Message.RecipientType.BCC, new InternetAddress(bcc));
            }
            message.setSubject(subject);

            // Crear el contenido del mensaje
            Multipart multipart = new MimeMultipart();

            // Parte de texto
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText(content);
            multipart.addBodyPart(textPart);

            // Adjuntar cada archivo de la lista
            if (attachments != null) {
                for (File file : attachments) {
                    MimeBodyPart attachmentPart = new MimeBodyPart();
                    DataSource source = new FileDataSource(file);
                    attachmentPart.setDataHandler(new DataHandler(source));
                    attachmentPart.setFileName(file.getName());
                    multipart.addBodyPart(attachmentPart);
                }
            }

            // Agregar el contenido al mensaje
            message.setContent(multipart);

            // Enviar el mensaje
            Transport.send(message);
            JOptionPane.showMessageDialog(null, "Correo enviado con éxito!", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (MessagingException e) {
            JOptionPane.showMessageDialog(null, "Error al enviar el correo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Properties getProperties(String smtpHost, String smtpPort, String usrEmail, String usrPasswd) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.starttls.enable", "true"); // Habilita TLS
        properties.put("mail.smtp.port", smtpPort);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.user", usrEmail);
        properties.put("mail.password", usrPasswd);
        return properties;
    }
}