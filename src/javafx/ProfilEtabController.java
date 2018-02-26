/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx;

import com.jfoenix.controls.JFXButton;
import entities.Etablisment;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.ListEtabController.idEtablissement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import services.Share;
import utils.MyBdConnection;

/**
 * FXML Controller class
 *
 * @author mokht
 */
public class ProfilEtabController implements Initializable {

    @FXML
    private Label nom;
    @FXML
    private Label Proprietaire;
    @FXML
    private Label tel;
    @FXML
    private Label adresse;
    @FXML
    private Label email;
    @FXML
    private Label description;

    private MyBdConnection mycon;
    @FXML
    private Label dateCre;
    @FXML
    private Label capacite;
    @FXML
    private Label type;

    public static String EmailEtab;
    @FXML
    private Button contact;
    @FXML
    private ImageView imageetab;
    @FXML
    private JFXButton participe;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mycon = new MyBdConnection();
        LoadData();
        EmailEtab = email.getText();

        System.out.println("ahawa" + EmailEtab);

    }

    private void LoadData() {
        Connection con = mycon.getConnection();
        Etablisment e = new Etablisment();
        try {
            System.out.println(" idEtablissement" + idEtablissement);
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM `etablisment` where id = " + idEtablissement + " ");
            while (rs.next()) {
                e = new Etablisment(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10), rs.getString(11), rs.getInt(12), rs.getInt(13),rs.getString(14));
                System.out.println("imgggggg"+rs.getString(14));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListEtabController.class.getName()).log(Level.SEVERE, null, ex);

        }
        nom.setText(e.getNom());
        tel.setText(e.getTel());
        adresse.setText(e.getAdresse());
        email.setText(e.getEmail());
        description.setText(e.getDescription());
        Proprietaire.setText(e.getProprietaire());
        String o = e.getCapacite() + "";
        capacite.setText(o);
        description.setText(e.getDescription());
        type.setText(e.getType());

        dateCre.setText(e.getDatecreation());
        
        System.out.println("img:"+e.getImage());
        File file = new File(e.getImage());
            
            Image image = new Image(file.toURI().toString());
            
           imageetab.setImage(image);

    }

    
    
    
    
    
    @FXML
    private void sendEmail() {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("emailSending.fxml"));
            /*
            * if "fx:controller" is not set in fxml
            * fxmlLoader.setController(NewWindowController);
             */
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.show();
            // Hide this current window (if this is what you want)
        } catch (IOException ex) {
            Logger.getLogger(ProfilEtabController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 @FXML
    public void partagefb(ActionEvent event) throws IOException{
    Share s=new Share();
        s.partager();
    }

    @FXML
    private void participate(ActionEvent event) throws IOException {
               FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("demandeinscri.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.show();
 
    }
}
