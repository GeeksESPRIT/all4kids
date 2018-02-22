/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 /*change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.Properties;
import javafx.EmailSendingController;
import javafx.ProfilEtabController;
import static javafx.ProfilEtabController.EmailEtab;
import javax.mail.Address;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

public class SendMail {

    private static String user = "mokhtarammar.ma@gmail.com";
    private static String pass = "princemma";

    public static void send(String EmailEtab,String mailUtilisateur) {
        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });
        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(user));
            message.setReplyTo(new Address[] { new InternetAddress(EmailSendingController.mailUtilisateur) });;
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(EmailEtab));
            message.setSubject("exempleeeeeeeeee");
            message.setText("test test ");

            Transport.send(message);

            JOptionPane.showMessageDialog(null, "Email send!");

        } catch (MessagingException e) {
            JOptionPane.showMessageDialog(null, "Something happened!");

            throw new RuntimeException(e);
        }

    }
}
