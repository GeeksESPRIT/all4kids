/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx;

import static javafx.EmailSendingController.mailUtilisateur;
import static javafx.ProfilEtabController.EmailEtab;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static javax.mail.Transport.send;
import services.EtablismentService;
import services.SendMail;


/**
 *
 * @author Mokh
 */
public class JavaFX extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ListEtab.fxml"));
       //  Parent root = FXMLLoader.load(getClass().getResource("ConfirmerAjout.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        EtablismentService es = new EtablismentService();
//        System.out.println("testaff : "+ es.afficherEtablissement("Garderie"));
        //es.deleteEtablissement(7);
        System.out.println("ey");
      /* SendMail test = new SendMail();
        SendMail.send(EmailEtab,mailUtilisateur);*/
        launch(args);
    }

}
