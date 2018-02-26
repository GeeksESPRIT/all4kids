/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;
import entities.DemandeInscription;
import entities.Utilisateur;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.ListEtabController.idEtablissement;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import services.demandeInscriptionService;
import utils.MyBdConnection;

/**
 * FXML Controller class
 *
 * @author mokht
 */
public class DemandeinscriController implements Initializable {

    @FXML
    private Label nomUtilisateur;
    @FXML
    private Label mailUtilisateur;
    @FXML
    private Label nomEtablissement;
    @FXML
    private JFXSlider nombreEnfant;
    @FXML
    private JFXButton Participer;
    private MyBdConnection mycon;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        loads();

    }
public void loads(){
         mycon = new MyBdConnection();
    Connection con = mycon.getConnection();
        Utilisateur u = new Utilisateur();
        try {
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM `users` WHERE `id`=8" /*+idUtilisateur*/);
            while (rs.next()) {
                u = new Utilisateur(rs.getString(2), rs.getString(3), rs.getString(4),0);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DemandeinscriController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        nomUtilisateur.setText(u.getNom() + " " + u.getPrenom());
        mailUtilisateur.setText(u.getEmail());}
    @FXML
    private void participerbtn(ActionEvent event) throws SQLException {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("voulez vous demander l'inscription dans cet Etablissement ?");
            Optional<ButtonType> result = alert.showAndWait();
             if (result.get() == ButtonType.OK) {
        demandeInscriptionService dis = new demandeInscriptionService();
        DemandeInscription di = new DemandeInscription(0,8,(int)nombreEnfant.getValue() ,idEtablissement);
        
       

        dis.ajouterDemandeInscri(di);
             }
    }

}
