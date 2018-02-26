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
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import entities.Etablisment;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.ListEtabController.idEtablissement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import services.Alertt;
import utils.DateUtil;
import utils.MyBdConnection;

/**
 * FXML Controller class
 *
 * @author mokht
 */
public class ModifierEtabController implements Initializable {

    @FXML
    private JFXComboBox type;
    @FXML
    private JFXTextField fix;
    @FXML
    private JFXTextField adresse;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField proprietaire;
    @FXML
    private JFXTextField email;
    @FXML
    private DatePicker dateCreation;
    @FXML
    private JFXTextArea description;
    @FXML
    private JFXSlider capacite;
   
    private MyBdConnection mycon;
    @FXML
    private JFXButton btnmod;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        type.getItems().addAll("Garderie", "Jardins enfants", "Ecole privé");
        mycon = new MyBdConnection();
        LoadData();

    }

    private void LoadData() {
        Connection con = mycon.getConnection();
        Etablisment e = new Etablisment();
        try {
            System.out.println("" + idEtablissement);
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM `etablisment` where id = " + idEtablissement + " ");
            while (rs.next()) {
                e = new Etablisment(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10), rs.getString(11), rs.getInt(12), rs.getInt(13),rs.getString(14));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListEtabController.class.getName()).log(Level.SEVERE, null, ex);

        }
        nom.setText(e.getNom());
        fix.setText(e.getTel());
        adresse.setText(e.getAdresse());
        email.setText(e.getEmail());
        description.setText(e.getDescription());
        proprietaire.setText(e.getProprietaire());
        // dateCreation.(DateUtil.format(Etablisment.getDatecreation()));
//        Date input = new Date(idEtablissement);
//        LocalDate date = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//       dateCreation.setValue(e.getDatecreation().toLocalDate());
        capacite.setValue(e.getCapacite());
        type.setValue(e.getType());

    }

    @FXML
    public void update(ActionEvent event) throws IOException {
        updateetab(idEtablissement);
    }

    public void updateetab(int id) {
        Connection cnx = MyBdConnection.getInstanceBD().getConnection();
        String DATE_PATTERN = "dd+MM-yyyy";

        /**
         * The date formatter.
         */
        DateTimeFormatter DATE_FORMATTER
                = DateTimeFormatter.ofPattern(DATE_PATTERN);
        String date = DATE_FORMATTER.format(dateCreation.getValue());

        Etablisment obj = new Etablisment(nom.getText(), adresse.getText(), fix.getText(), 5, type.getValue().toString(), 8, proprietaire.getText(), email.getText(), date, 9, description.getText());
        String req = "UPDATE `allforkids`.`etablisment` SET `nom`=?, `adresse`=?, `fix`=?, `type`=?, `idUser`=?, `proprietaire`=?, `email`=?, `datecreation`=?, `capacite`=?, `description`=?, `rating`=? where id =?";

        try {
            PreparedStatement statement = cnx.prepareStatement(req);

            statement.setString(1, obj.getNom());
            statement.setString(2, obj.getAdresse());
            statement.setString(3, obj.getTel());
            statement.setString(4, obj.getType());
            statement.setInt(5, 10);
            statement.setString(6, obj.getProprietaire());
            statement.setString(7, obj.getEmail());
            statement.setString(8, obj.getDatecreation());
            statement.setInt(9, obj.getCapacite());
            statement.setString(10, obj.getDescription());
            statement.setInt(11,5);
            statement.setInt(12,id);
            
             

          statement.executeUpdate();
           
        } catch (SQLException ex) {

            ex.printStackTrace();
            
            Alertt.infoBox("Etablissement Modifié", "Modification", "succes");

        }

    }

    private void ajouter(ActionEvent event) {
    }

}
