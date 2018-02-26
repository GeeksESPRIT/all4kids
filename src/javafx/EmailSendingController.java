/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import static javafx.FXMLController.isValidMail;
import static javafx.ProfilEtabController.EmailEtab;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import services.Alertt;
import services.SendMail;

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
    private JFXTextField subject;
    @FXML
    private JFXTextArea text;
    @FXML
    private Button envoyer;
   
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
        recipient.setText(EmailEtab);
        
        
        
        

    }

    @FXML

    public void send(ActionEvent event) {
        
        System.out.println(""+from.getText());
          System.out.println(""+subject.getText());
            System.out.println(""+text.getText());
            if( isValidMail(from.getText())){
        SendMail.sending(EmailEtab,from.getText(),subject.getText(),text.getText());
            }else{
                Alertt.infoBox("veuiellez vérifier votre Adresse Email", "adresse Email érroné");
            }
    }
   
}
