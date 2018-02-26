package javafx;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import entities.Etablisment;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.ListEtabController.idEtablissement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.EtablismentService;
import utils.MyBdConnection;

/**
 * FXML Controller class
 *
 * @author mokht
 */
public class mesEtabConroller implements Initializable {

    @FXML
    private AnchorPane rootpane;
    @FXML
    private TableView<Etablisment> table;
    @FXML
    private TableColumn<Etablisment, String> cnom;
    @FXML
    private TableColumn<Etablisment, String> idUser;
    @FXML
    private TableColumn<Etablisment, String> rating;
    @FXML
    private TableColumn<Etablisment, String> idEtab;
    @FXML
    private TableColumn<Etablisment, String> cadresse;
    @FXML
    private TableColumn<Etablisment, String> ctel;
    @FXML
    private TableColumn<Etablisment, String> ctype;
    @FXML
    private TableColumn<Etablisment, String> proprietaire;
    @FXML
    private TableColumn<Etablisment, String> email;
    @FXML
    private TableColumn<Etablisment, String> datecreation;
    @FXML
    private TableColumn<Etablisment, String> capacite;
    @FXML
    private TableColumn<Etablisment, String> description;
    @FXML
    private TableColumn<Etablisment, String> nbrdispo;
    @FXML
    private JFXButton btnSupprimer;
    @FXML
    private JFXButton details;
   
    @FXML
    private Label nomLabel;
    @FXML
    private Label telLabel;
    @FXML
    private JFXComboBox choixEtab;
    @FXML
    private JFXButton modifierEtablissement;

    private ObservableList<Etablisment> data;
    private MyBdConnection mycon;
    
    
    @FXML
    private JFXButton ajouter;
    @FXML
    private JFXTextField rechercher;
    @FXML
    private ImageView imageetab;
    @FXML
    private AnchorPane overview;
    
    @FXML
    private JFXButton btngestiondemande;
    @FXML
    private JFXButton btnretour;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mycon = new MyBdConnection();
        LoadData();
        recherche();
        Load();
        table.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showDetails(newValue));

        choixEtab.getItems().addAll("Garderie", "Jardins enfants", "Ecole privé");
        

    }

    private void ajout(ActionEvent event) throws SQLException, IOException {
        AnchorPane Anchpane = FXMLLoader.load(getClass().getResource("FXML.fxml"));
        rootpane.getChildren().setAll(Anchpane);

    }

    private void LoadData() {
        Connection con = mycon.getConnection();
        data = FXCollections.observableArrayList();
        try {
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM `etablisment` WHERE enabled=1 && idUser =8");
            while (rs.next()) {

                data.add(new Etablisment(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10), rs.getString(11), rs.getInt(12), rs.getInt(13),rs.getString(14),rs.getInt(15)));

            }
        } catch (SQLException ex) {
            Logger.getLogger(mesEtabConroller.class.getName()).log(Level.SEVERE, null, ex);

        }
        idEtab.setCellValueFactory(new PropertyValueFactory<>("id"));
        cnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        cadresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        ctel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        ctype.setCellValueFactory(new PropertyValueFactory<>("type"));
        proprietaire.setCellValueFactory(new PropertyValueFactory<>("proprietaire"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        datecreation.setCellValueFactory(new PropertyValueFactory<>("datecreation"));
        capacite.setCellValueFactory(new PropertyValueFactory<>("capacite"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        idUser.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        rating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        nbrdispo.setCellValueFactory(new PropertyValueFactory<>("nbrdispo"));
        
        table.setItems(data);
    }

    @FXML
    public void affichermod(ActionEvent event) throws IOException {
        ObservableList<Etablisment> selectedRows, all;
        all = table.getItems();

        selectedRows = table.getSelectionModel().getSelectedItems();

        for (Etablisment b : selectedRows) {
            try {
                
                idEtablissement = b.getId();
                System.out.println("" + idEtablissement);
                System.out.println("desole");
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("modifierEtab.fxml"));
                /* 
         * if "fx:controller" is not set in fxml
         * fxmlLoader.setController(NewWindowController);
                 */
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();

                stage.setScene(scene);
                stage.show();
                // Hide this current window (if this is what you want)
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Id not figured");

            }

        }
    }

    public void recherche() {

//        FilteredList<Etablisment> filteredData = new FilteredList<>(data, e -> true);
        rechercher.setOnKeyReleased(e -> {
            if (rechercher.getText().equals("")) {
                LoadData();
            } else {
                data.clear();
               String sql = "SELECT * FROM `etablisment` WHERE enabled = 1 && nom LIKE '%" + rechercher.getText() + "%' "
                        + "UNION SELECT * FROM `etablisment` WHERE enabled = 1 && adresse LIKE '%" + rechercher.getText() + "%'"
                        + "UNION SELECT * FROM `etablisment` WHERE enabled = 1&& fix LIKE '%" + rechercher.getText() + "%'"
                        + "UNION SELECT * FROM `etablisment` WHERE enabled = 1 && type LIKE '%" + rechercher.getText() + "%'";
                try {
                    PreparedStatement statement = mycon.getConnection().prepareStatement(sql);
                    //statement.setString(2, rechercher.getText());
//                    statement.setString(3, rechercher.getText());
//                    statement.setString(4, rechercher.getText());
//                    statement.setString(5, rechercher.getText());
                    ResultSet rs = statement.executeQuery();
                    while (rs.next()) {
                        data.add(new Etablisment(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
                    }
                    table.setItems(data);
                } catch (SQLException ex) {
                    Logger.getLogger(mesEtabConroller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private void showDetails(Etablisment etablisment) {
        if (etablisment == null) {

            System.out.println("" + etablisment.getNom());

            nomLabel.setText("");
            telLabel.setText("");
        } else {

            System.out.println("" + etablisment.getNom());
            nomLabel.setText(etablisment.getNom());
            telLabel.setText(etablisment.getTel());
            File file = new File(etablisment.getImage());
            
            Image image = new Image(file.toURI().toString());
            
           imageetab.setImage(image);

        }
    }

    public void Load() {

//        FilteredList<Etablisment> filteredData = new FilteredList<>(data, e -> true);
        choixEtab.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            if (choixEtab.getValue().equals("")) {
                LoadData();
            } else {
                data.clear();
                String sql = "SELECT * FROM `etablisment` WHERE type LIKE '%" + newValue + "%' ";
                try {
                    PreparedStatement statement = mycon.getConnection().prepareStatement(sql);
                    //statement.setString(2, rechercher.getText());
//                    statement.setString(3, rechercher.getText());
//                    statement.setString(4, rechercher.getText());
//                    statement.setString(5, rechercher.getText());
                    ResultSet rs = statement.executeQuery();
                    while (rs.next()) {
                        data.add(new Etablisment(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
                    }
                    table.setItems(data);
                } catch (SQLException ex) {
                    Logger.getLogger(mesEtabConroller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    @FXML
    public void afficherDetails(ActionEvent event) throws IOException {
        ObservableList<Etablisment> selectedRows, all;
        all = table.getItems();

        //this gives us the rows that were selected
        selectedRows = table.getSelectionModel().getSelectedItems();

        //loop over the selected rows and remove the Person objects from the table
        for (Etablisment b : selectedRows) {
            try {
                System.out.println("qqq"+b.getId());
                idEtablissement = b.getId();
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("profilEtab.fxml"));
                /* 
         * if "fx:controller" is not set in fxml
         * fxmlLoader.setController(NewWindowController);
                 */
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();

                stage.setScene(scene);
                stage.show();
                // Hide this current window (if this is what you want)
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Id not figured");

            }

        }
    }

    @FXML
    void deleteact(ActionEvent event) {

        try {
            Etablisment etablisment = table.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("voulez vous supprimer cet Etablissement ?");
            // alert.showAndWait();
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                EtablismentService.getInstance().deleteEtablissement(idEtablissement);
                
            }
            
            List<Etablisment> etablisments = EtablismentService.getInstance().afficherEtablissement();

            ObservableList<Etablisment> data = FXCollections.observableArrayList(etablisments);
            table.setItems(data);
        } catch (SQLException ex) {
            Logger.getLogger(mesEtabConroller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
   /*  @FXML
    private void premierPage(ActionEvent event) throws SQLException, IOException {
      AnchorPane Anchpane = FXMLLoader.load(getClass().getResource("PremierPage.fxml"));
        rootpane.getChildren().setAll(Anchpane);
    }
    */
     @FXML
    private void gestionDemande(ActionEvent event) throws SQLException, IOException {
      AnchorPane Anchpane = FXMLLoader.load(getClass().getResource("confirmerinscription.fxml"));
        rootpane.getChildren().setAll(Anchpane);
    }
    @FXML
    private void premierPage(ActionEvent event) throws SQLException, IOException {
        AnchorPane Anchpane = FXMLLoader.load(getClass().getResource("PremierPage.fxml"));
        rootpane.getChildren().setAll(Anchpane);
        
    }
    @FXML
    private void Ajouterbtn(ActionEvent event) throws SQLException, IOException {
        AnchorPane Anchpane = FXMLLoader.load(getClass().getResource("FXML.fxml"));
        rootpane.getChildren().setAll(Anchpane);
        
    }
}

//public void deleteButtonPushed(ActionEvent event)
//    {
//        ObservableList<Covoiturage> selectedRows, allCovoiturage;
//        allCovoiturage = table.getItems();
//        
//        //this gives us the rows that were selected
//        selectedRows = table.getSelectionModel().getSelectedItems();
//        
//        //loop over the selected rows and remove the Person objects from the table
//        for (Covoiturage covoiturage: selectedRows)
//        {
//            allCovoiturage.remove(covoiturage);
//            CovoiturageService s=new CovoiturageService();
//          
//            int Id4d;   
//
//try {
//       Id4d= covoiturage.getId();    
//      s.DeleteCovoiturage(Id4d);
//      JOptionPane.showMessageDialog(null, "delete avec succeré");
//}
//catch (NumberFormatException e) {
//    JOptionPane.showMessageDialog(null, "Id not figured");
//          
//}
