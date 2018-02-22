/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package services;


import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import static services.CrudLogin.id_user;

/**
 * FXML Controller class
 *
 * @author mrad
 */
public class LoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane rootpane;
    @FXML
    public JFXTextField username;
    @FXML
    public JFXPasswordField password;
    public static String EmailUtilisateur ;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    @FXML
    private void methodeinscri(ActionEvent event) throws SQLException, IOException {
        AnchorPane Anchpane = FXMLLoader.load(getClass().getResource("/allforkids/loginpkg/Inscription.fxml"));
        rootpane.getChildren().setAll(Anchpane);
        
    }
     @FXML
    private void methconnexion(ActionEvent event) throws SQLException, IOException {
     CrudLogin cp = new CrudLogin();
     AnchorPane Anchpane = FXMLLoader.load(getClass().getResource("/allforkids/MainClass/MainClass.fxml"));
      //AnchorPane Anchpane = FXMLLoader.load(getClass().getResource("./test.fxml"));
        
     if(cp.testlogin(username.getText(),password.getText()))
     {      System.out.println("id_user : "+id_user);
                  rootpane.getChildren().setAll(Anchpane);
}
     else{
         JOptionPane.showMessageDialog(null,"Nom d'utilisateur ou mot de pass incorrecte ","Verifier vos informations",1);}
    }
    
     
    
}
