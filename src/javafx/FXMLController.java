/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx;

import javafx.scene.image.Image;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import entities.Etablisment;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Path;
import javafx.stage.FileChooser;
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
    public File fileSelected = null;

    private Etablisment s;
    @FXML
    private Label nomimage;
    @FXML
    private ImageView image;
    public String path;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        type.getItems().addAll("Garderie", "Jardins enfants", "Ecole privé");

    }
 public static boolean isValidTel(String tel){
        String testString = "\\D";
      
     if (testString.contains(tel) ){
         return false;
         
     } else {  
         return true;
     }
    
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
        if (fileSelected != null) {
            path = fileSelected.getAbsolutePath();

            path.replace("\\", "\\\\");
            System.out.println("path" + path);
        }
        e.setImage(path);
        e.setEmail(email.getText());

        e.setEnabled(0);
        if (nom.getText().equals("") || fix.getText().equals("")
 || adresse.getText().equals("") || type.getValue().toString().equals("")
  || propretaire.getText().equals("") || description.getText().equals("")||capacite.getValue()==0||path.equals("")||email.getText().equals("")||date.equals("")|| isValidMail(email.getText())==false || isValidTel(fix.getText())) {
            Alertt.infoBox("veuillez remplir tous les champs oubien vérifiez Votre adresse Email", "veuillez remplir tous les champs");
           
        } else {
            s.ajouterEtablissement(e);
            Alertt.infoBox("Votre demande va etre traitée", "Ajout", "succes");
        }
    }

    @FXML
    public void imagechoice(ActionEvent event) throws IOException {

        FileChooser fc = new FileChooser();
        fileSelected = fc.showOpenDialog(null);

        System.out.println("" + fileSelected.getCanonicalPath());

        nomimage.setText(fileSelected.getName());
        File file = new File(fileSelected.getAbsolutePath());

        Image image = new Image(file.toURI().toString());

        // imageview.setImage(image);
    }

    @FXML
    private void annuler(ActionEvent event) throws SQLException, IOException {
        AnchorPane Anchpane = FXMLLoader.load(getClass().getResource("mesEtab.fxml"));
        rootpane.getChildren().setAll(Anchpane);

    }
}
