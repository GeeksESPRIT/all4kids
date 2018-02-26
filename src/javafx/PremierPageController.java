/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import services.SendMail;

/**
 * FXML Controller class
 *
 * @author mokht
 */
public class PremierPageController implements Initializable {

    @FXML
    private JFXButton btntous;
    @FXML
    private JFXButton btnmes;
    @FXML
    private AnchorPane rootpane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    // TODO
    }   
    @FXML
    private void tousEtablissement(ActionEvent event) throws SQLException, IOException {
      AnchorPane Anchpane = FXMLLoader.load(getClass().getResource("ListEtab.fxml"));
        rootpane.getChildren().setAll(Anchpane);
       
    }
    @FXML
    private void mesEtablissement(ActionEvent event) throws SQLException, IOException {
      AnchorPane Anchpane = FXMLLoader.load(getClass().getResource("mesEtab.fxml"));
        rootpane.getChildren().setAll(Anchpane);
       
    }
}
