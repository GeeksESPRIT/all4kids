package javafx;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import entities.Etablisment;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.controlsfx.control.textfield.CustomTextField;
import services.EtablismentService;
import utils.MyBdConnection;

/**
 * FXML Controller class
 *
 * @author mokht
 */
public class ListEtabController implements Initializable {

    @FXML
    private AnchorPane overview;
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
    private JFXButton btnLoad;
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
    public static int idEtablissement;
    @FXML
    private AnchorPane rootpane;
    
    @FXML
    private JFXButton ajouter;
    @FXML
    private CustomTextField rechercher;
   

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

    @FXML
    private void LoadData() {
        Connection con = mycon.getConnection();
        data = FXCollections.observableArrayList();
        try {
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM `etablisment` WHERE enabled=1");
            while (rs.next()) {

                data.add(new Etablisment(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10), rs.getString(11), rs.getInt(12), rs.getInt(13)));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ListEtabController.class.getName()).log(Level.SEVERE, null, ex);

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
        table.setItems(null);
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

    @FXML
    public void recherche() {

//        FilteredList<Etablisment> filteredData = new FilteredList<>(data, e -> true);
        rechercher.setOnKeyReleased(e -> {
            if (rechercher.getText().equals("")) {
                LoadData();
            } else {
                data.clear();
                String sql = "SELECT * FROM `etablisment` WHERE nom LIKE '%" + rechercher.getText() + "%' "
                        + "UNION SELECT * FROM `etablisment` WHERE adresse LIKE '%" + rechercher.getText() + "%'"
                        + "UNION SELECT * FROM `etablisment` WHERE fix LIKE '%" + rechercher.getText() + "%'"
                        + "UNION SELECT * FROM `etablisment` WHERE type LIKE '%" + rechercher.getText() + "%'";
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
                    Logger.getLogger(ListEtabController.class.getName()).log(Level.SEVERE, null, ex);
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
                    Logger.getLogger(ListEtabController.class.getName()).log(Level.SEVERE, null, ex);
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
    void delete_act(ActionEvent event) {

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
            Logger.getLogger(ListEtabController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
