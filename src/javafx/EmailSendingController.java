/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import static javafx.ProfilEtabController.EmailEtab;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.Action;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author mokht
 */
public class EmailSendingController implements Initializable {

    @FXML
    private AnchorPane cp;
    @FXML
    private JFXTextField from;
    @FXML
    private JFXTextField recipient;
    @FXML
    private static JFXTextField subject;
    @FXML
    private static JFXTextArea text;

    @FXML
    private Button envoyer;

    public static String mailUtilisateur;
   
    private static final String user = "mokhtarammar.ma@gmail.com";
    private static final String pass = "princemma";

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mailUtilisateur = from.getText();
        recipient.setText(EmailEtab);

    }

    @FXML

    private void send(ActionEvent event) {
        send(EmailEtab, mailUtilisateur);

    }

    public static void send(String EmailEtab, String mailUtilisateur) {
        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.user", user);

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });
        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(user));
            message.setReplyTo(new Address[]{new InternetAddress(mailUtilisateur)});
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(EmailEtab));
            message.setSubject(subject.getText());
            message.setText(text.getText());

            Transport.send(message);

            JOptionPane.showMessageDialog(null, "Email send!");

        } catch (MessagingException e) {
            JOptionPane.showMessageDialog(null, "Something happened!");

            throw new RuntimeException(e);
        }

    }
}
