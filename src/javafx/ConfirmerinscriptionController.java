/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;
import entities.Etablisment;
import entities.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.ListEtabController.idEtablissement;
import static javafx.ProfilEtabController.EmailEtab;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import services.Alertt;
import services.EtablismentService;
import services.SendMail;
import utils.MyBdConnection;

/**
 * FXML Controller class
 *
 * @author mokht
 */
public class ConfirmerinscriptionController implements Initializable {

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
    private TableColumn<Etablisment, String> participant;
    @FXML
    private TableColumn<Etablisment, String> id_participation;
    @FXML
    private TableColumn<Etablisment, String> nbrenfants;
     @FXML
    private TableColumn<Etablisment, String> nbrparticipants;
    @FXML
    private Label nomLabel;
    @FXML
    private Label telLabel;
  @FXML
    private Label mail;
    List <Utilisateur> listu;
            
    
    private ObservableList<Etablisment> data;
    private MyBdConnection mycon;
    @FXML
    private Label etab;
    @FXML
    private Label etabnom;
    @FXML
    private TableColumn<?, ?> dispo;
    @FXML
    private AnchorPane rootpane;
    @FXML
    private JFXButton btnretour;
    @FXML
    private Label adressLabel;
    @FXML
    private Label teletab;
    @FXML
    private ProgressBar progressbar;
    

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
        try {
            listparticipant();
        } catch (SQLException ex) {
            Logger.getLogger(ConfirmerinscriptionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        table.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
            try {
                showDetails(newValue);
            } catch (SQLException ex) {
                Logger.getLogger(ConfirmerinscriptionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
       
        
    }

    private void LoadData() {
        Connection con = mycon.getConnection();
        data = FXCollections.observableArrayList();
        try {
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM `demandeinscription` WHERE enabled=0");
            List <Integer> demandes = new ArrayList<>();

            while (rs.next()) {
                 ResultSet rs2 = con.createStatement().executeQuery("SELECT * FROM `etablisment`");

                while (rs2.next()) {

                if(rs.getInt(4)==rs2.getInt(1))
                {data.add(new Etablisment(rs2.getInt(1), rs2.getString(2), rs2.getString(3), rs2.getString(4)
                 , rs2.getString(5), rs2.getInt(6), rs2.getString(7), rs2.getString(8), rs2.getString(9),
                 rs2.getInt(10), rs2.getString(11), rs2.getInt(12), rs2.getInt(13), rs2.getString(14),rs.getInt(2),rs.getInt(1),rs.getInt(3),rs2.getInt(15),rs2.getInt(16)));
                    System.out.println("rs.getInt(1)"+rs.getInt(1));
                }
                

            }
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
         participant.setCellValueFactory(new PropertyValueFactory<>("participant"));
         id_participation.setCellValueFactory(new PropertyValueFactory<>("id_participation"));
         nbrenfants.setCellValueFactory(new PropertyValueFactory<>("nbrenfant"));
         dispo.setCellValueFactory(new PropertyValueFactory<>("dispo"));
         nbrparticipants.setCellValueFactory(new PropertyValueFactory<>("nbrparticipants"));
         table.setItems(null);
        table.setItems(data);
    }

    public void listparticipant() throws SQLException
    
    {listu = new ArrayList<>();
    Utilisateur u = new Utilisateur();
    Connection con = mycon.getConnection();
            ResultSet rs3 = con.createStatement().executeQuery("SELECT * FROM `users`");
            while (rs3.next()) {
            u = new Utilisateur(rs3.getInt(1),rs3.getString(2),rs3.getString(3),rs3.getString(4),10);
            listu.add(u);
            }
    
    }
    
    public void showDetails(Etablisment etablisment) throws SQLException {
        if (etablisment != null) {
            etab.setText(etablisment.getEmail());
            etabnom.setText(etablisment.getNom());
            adressLabel.setText(etablisment.getAdresse());
            telLabel.setText(etablisment.getTel());
            System.out.println("etab"+etablisment.getParticipant()); 
            for (Utilisateur u:listu) {
            if(u.getId()==etablisment.getParticipant())    { 
               
            nomLabel.setText(u.getNom()+" "+u.getPrenom() );
            mail.setText(u.getEmail());
            telLabel.setText("20348126");
            }
            }
            }}
            
            
    
    
   
        
    

    
    public boolean updateEtab() {
         Connection con = mycon.getConnection();
         ObservableList<Etablisment> selectedRows, all;
        all = table.getItems();

        //this gives us the rows that were selected
        selectedRows = table.getSelectionModel().getSelectedItems();

        //loop over the selected rows and remove the Person objects from the table
        for (Etablisment b : selectedRows) {
            System.out.println("b.getttt"+b.getNom());
            System.out.println("b.getttt"+b.getId_participattion());
            System.out.println("b.getttt"+b.getParticipant());
                
         try {
             System.out.println("b.getId_participattion()"+b.getId_participattion());
            String update = "UPDATE `demandeinscription` SET enabled = ? WHERE id ="+b.getId_participattion();
                        String updateetab = "UPDATE `etablisment` SET nbrparticipants = ? WHERE id ="+b.getId();
                        System.out.println("b.getIddddddddddddd"+b.getId());
                        System.out.println("b.getNbrparticipants"+b.getNbrparticipants());
            PreparedStatement statement2 = con.prepareStatement(update);
            statement2.setInt(1, 1);
            PreparedStatement statement3 = con.prepareStatement(updateetab);
            statement3.setInt(1,b.getNbrparticipants()+1);
                 MyBdConnection cnx = MyBdConnection.getInstanceBD();
             System.out.println("getDispo:"+b.getNbrdispo());
             System.out.println("getNbrenfant:"+b.getNbrenfant());
             if(b.getNbrdispo()>=b.getNbrenfant())
            {statement2.executeUpdate();
            statement3.executeUpdate();
            int nbrdisp = b.getNbrdispo()-b.getNbrenfant();
            String req ="UPDATE `allforkids`.`etablisment` SET `nbrdispo` =" +nbrdisp+ " WHERE `etablisment`.`id` = "+b.getId();
                        PreparedStatement statement = cnx.getConnection().prepareStatement(req);
                                     statement.executeUpdate();
                             System.out.println("update Done");          
            return true;
            }
            else {Alertt.infoBox("Complet","Complet");
             return false;}
             //Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
          
           
        } catch (SQLException f) {
            System.out.println(f.getMessage());
            
        }
    }
return false;
    }
    @FXML
    public void send(ActionEvent event) {
        
        
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("voulez vous confirmer l'ajout de cet Etablissement ?");
            Optional<ButtonType> result = alert.showAndWait();
             if (result.get() == ButtonType.OK) {
         if(updateEtab())
         { LoadData();
             progressbar.setVisible(true);
             if(progressbar.isVisible())
            SendMail.sending(mail.getText(),etab.getText(),"Demande d'inscription dans notre etablissement  <<"+etabnom.getText()+">>","votre demande a été traitée,veuilez nous rendre visite le plus tot possible à "+adressLabel.getText()+" \n"+" Ou bien nous contactez sur: "+telLabel.getText()+".");
         
         }
             }
    }
    
    @FXML
    private void premierPage(ActionEvent event) throws SQLException, IOException {
        AnchorPane Anchpane = FXMLLoader.load(getClass().getResource("mesEtab.fxml"));
        rootpane.getChildren().setAll(Anchpane);
        
    }
    

}
