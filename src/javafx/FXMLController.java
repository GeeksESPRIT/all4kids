/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import entities.Etablisment;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import services.Alertt;
import services.EtablismentService;

/**
 *
 */
public class FXMLController implements Initializable {

    @FXML
    private JFXTextField nom;
    @FXML
    private JFXComboBox type;
    @FXML
    private JFXTextField adresse;
    @FXML
    private JFXTextField fix;
    @FXML
    private JFXTextField propretaire;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXSlider capacite;
    @FXML
    private DatePicker dateCreation;
    @FXML
    private JFXTextArea description;
    @FXML
    private JFXButton ajouter;
    @FXML
    private AnchorPane rootpane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        type.getItems().addAll("Garderie", "Jardins enfants", "Ecole privé");

// dateCreation.setOnAction(new EventHandler() {
//     public void handle(Event t) {
//         LocalDate date = dateCreation.getValue();
//         System.out.println("Selected date: " + date);
//     }
// });
    }

    public static boolean isValidMail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null) {
            return false;
        }
        return pat.matcher(email).matches();
    }

    @FXML
    private void ajouter(ActionEvent event) {
        String DATE_PATTERN = "dd/MM/yyyy";

        /**
         * The date formatter.
         */
        DateTimeFormatter DATE_FORMATTER
                = DateTimeFormatter.ofPattern(DATE_PATTERN);
        String date = DATE_FORMATTER.format(dateCreation.getValue());

        EtablismentService s = new EtablismentService();
        Etablisment e = new Etablisment();
        e.setDatecreation(date);
        e.setNom(nom.getText());
        e.setAdresse(adresse.getText());
        e.setTel(fix.getText());
        e.setType(type.getValue().toString());
        e.setIdUser(8 /*CrudLogin.id_user*/);
        e.setProprietaire(propretaire.getText());

        e.setDescription(description.getText());
        e.setCapacite((int) capacite.getValue());

        e.setEmail(email.getText());

        e.setEnabled(0);

        s.ajouterEtablissement(e);
        Alertt.infoBox("Votre demande va etre traitée", "Ajout", "succes");

    }

  

//    public JFXTextField getNom() {
//        return nom;
//    }
//
//    public void setNom(JFXTextField nom) {
//        this.nom = nom;
//    }
//
//    public JFXComboBox getType() {
//        return type;
//    }
//
//    public void setType(JFXComboBox type) {
//        this.type = type;
//    }
//
//    public JFXTextField getAdresse() {
//        return adresse;
//    }
//
//    public void setAdresse(JFXTextField adresse) {
//        this.adresse = adresse;
//    }
//
//    public JFXTextField getFix() {
//        return fix;
//    }
//
//    public void setFix(JFXTextField fix) {
//        this.fix = fix;
//    }
//
//    public JFXTextField getPropretaire() {
//        return propretaire;
//    }
//
//    public void setPropretaire(JFXTextField propretaire) {
//        this.propretaire = propretaire;
//    }
//
//    public JFXTextField getEmail() {
//        return email;
//    }
//
//    public void setEmail(JFXTextField email) {
//        this.email = email;
//    }
//
//    public JFXSlider getCapacite() {
//        return capacite;
//    }
//
//    public void setCapacite(JFXSlider capacite) {
//        this.capacite = capacite;
//    }
//
//    public DatePicker getDateCreation() {
//        return dateCreation;
//    }
//
//    public void setDateCreation(DatePicker dateCreation) {
//        this.dateCreation = dateCreation;
//    }
//
//    public JFXTextArea getDescription() {
//        return description;
//    }
//
//    public void setDescription(JFXTextArea description) {
//        this.description = description;
//    }
//
//    public JFXButton getAjouter() {
//        return ajouter;
//    }
//
//    public void setAjouter(JFXButton ajouter) {
//        this.ajouter = ajouter;
//    }
}
