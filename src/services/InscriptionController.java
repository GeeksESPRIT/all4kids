/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package services;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import java.util.*;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author mrad
 */
public class InscriptionController implements Initializable {
    @FXML
    private AnchorPane rootpane;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField prenom;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXTextField mail;
    @FXML
    private JFXPasswordField mdp;
    @FXML
    private JFXSlider age;
    @FXML
    private JFXButton inscription;
    @FXML
    private Button login;
    
   
    @FXML
    private void pageconnexion(ActionEvent event) throws SQLException, IOException {
      AnchorPane Anchpane = FXMLLoader.load(getClass().getResource("Login.fxml"));
        rootpane.getChildren().setAll(Anchpane);
       
    }
   
   @FXML
    private void handleButtonAction(ActionEvent event) throws SQLException, IOException {
      List <String> usernames = new ArrayList<>();
        CrudLogin cp = new CrudLogin();
        usernames = cp.Getuserames();
        int valeur = (int)age.getValue();
        Utilisateur u = new Utilisateur(nom.getText(),prenom.getText(),username.getText(),mail.getText(),mdp.getText(),valeur);
        if(!mail.getText().equals("")&&!nom.getText().equals("")&&!prenom.getText().equals("")
                &&!username.getText().equals("")&&!mdp.getText().equals("")
                ){
        if(isValidMail(mail.getText()))
        {
        if(cp.insertutilisateur(u,usernames))
        {AnchorPane Anchpane = FXMLLoader.load(getClass().getResource("/allforkids/MainClass/MainClass.fxml"));
        rootpane.getChildren().setAll(Anchpane);}
        }
        else{JOptionPane.showMessageDialog(null,"E-mail incorrecte");}}
        else{JOptionPane.showMessageDialog(null,"remplir les champs");}
    }
   
  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
   public static boolean isValidMail(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                            "[a-zA-Z0-9_+&*-]+)*@" +
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                            "A-Z]{2,7}$";
                             
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    } 
}
