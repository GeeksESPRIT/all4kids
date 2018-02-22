/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx;

import entities.Etablisment;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.ListEtabController.idEtablissement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import services.EtablismentService;
import utils.MyBdConnection;

/**
 * FXML Controller class
 *
 * @author mokht
 */
public class ConfirmerAjoutController implements Initializable {

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
    private TableColumn<Etablisment, String> enabled;

    @FXML
    private GridPane emaillabel;
    private ObservableList<Etablisment> data;
    private MyBdConnection mycon;
    @FXML
    private Label nomLabel;
    @FXML
    private Label telLabel;
    @FXML
    private Label proplabel;
    @FXML
    private Label maillabel;
    @FXML
    private Label datelabel;
    @FXML
    private Label capacitelabel;
    @FXML
    private Label typelabel;
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        mycon = new MyBdConnection();
        LoadData();
        table.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showDetails(newValue));
       
        
    }

    private void LoadData() {
        Connection con = mycon.getConnection();
        data = FXCollections.observableArrayList();
        try {
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM `etablisment` WHERE enabled=0");
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
         enabled.setCellValueFactory(new PropertyValueFactory<>("enabled"));
        table.setItems(null);
        table.setItems(data);
    }

    private void showDetails(Etablisment etablisment) {
        if (etablisment != null) {
            System.out.println("" + etablisment.getNom());
            nomLabel.setText(etablisment.getNom());
            telLabel.setText(etablisment.getTel());
            proplabel.setText(etablisment.getProprietaire());
            capacitelabel.setText(etablisment.getCapacite() + "");
            datelabel.setText(etablisment.getDatecreation());
            maillabel.setText(etablisment.getEmail());
            typelabel.setText(etablisment.getType());

        } else {
            System.out.println("" + etablisment.getNom());

            nomLabel.setText(" ");
            telLabel.setText(" ");

        }
    }
    
    
    
    
    @FXML 
    public void changer(ActionEvent event){
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("voulez vous confirmer l'ajout de cet Etablissement ?");
            Optional<ButtonType> result = alert.showAndWait();
             if (result.get() == ButtonType.OK) {
        Etablisment e = new Etablisment();
         updateEtab(e);
              
             }
       
    }
        
    

    
    public void updateEtab(Etablisment e) {
         Connection con = mycon.getConnection();
         ObservableList<Etablisment> selectedRows, all;
        all = table.getItems();

        //this gives us the rows that were selected
        selectedRows = table.getSelectionModel().getSelectedItems();

        //loop over the selected rows and remove the Person objects from the table
        for (Etablisment b : selectedRows) {
           
                idEtablissement = b.getId();
                
                System.out.println(idEtablissement+"."+e.getNom());
         try {
            String update = "UPDATE `etablisment` SET enabled = ? WHERE id ="+idEtablissement;
            PreparedStatement statement2 = con.prepareStatement(update);
            statement2.setInt(1, 1);
           

            statement2.executeUpdate();
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
           
            System.out.println("update Done");
        } catch (SQLException f) {
            System.out.println(f.getMessage());

        }
    }

    }


}
