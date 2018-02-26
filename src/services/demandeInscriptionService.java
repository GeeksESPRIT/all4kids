/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.DemandeInscription;
import entities.Etablisment;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import utils.MyBdConnection;

/**
 *
 * @author mokht
 */
public class demandeInscriptionService implements IdemandeInscriptionService<DemandeInscription>{
    private static demandeInscriptionService instance;
    private Statement st;
    private ResultSet rs;
     private MyBdConnection mycon;
      private ObservableList<Etablisment> data;


    public demandeInscriptionService() {
        MyBdConnection cnx = MyBdConnection.getInstanceBD();
        try {
            st = cnx.getConnection().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(MyBdConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static demandeInscriptionService getInstance() {
        if (instance == null) {
            instance = new demandeInscriptionService();
        }
        return instance;
    }
    public void ajouterDemandeInscri(DemandeInscription p){
    
        MyBdConnection cnx = MyBdConnection.getInstanceBD();
        DemandeInscription obj = new DemandeInscription();
         
        String req="INSERT INTO `allforkids`.`demandeinscription` (`id`, `id_participant`, `nbreInscription`, `idEtab`, `enabled`) VALUES (NULL, '"+p.getId_participant()+"', '"+p.getNbreInscription()+"', '"+p.getIdEtab()+"' , 0)";
         try {
            PreparedStatement statement = cnx.getConnection().prepareStatement(req);
            statement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
 /*public void deleteDemande(int id) {

        MyBdConnection cnx = MyBdConnection.getInstanceBD();

        String req = "DELETE FROM `demandeinscription` WHERE `id`=" +id;
        try {
            PreparedStatement statement = cnx.getConnection().prepareStatement(req);
            statement.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();

        }

    }*/
 /*public void ModifierDemandeInscri(DemandeInscription object , int id){
    
        MyBdConnection cnx = MyBdConnection.getInstanceBD();
        DemandeInscription obj = new DemandeInscription();
         
        String req="UPDATE `demandeinscription` SET `id`=?,`id_user`=?,`nbreInscription`=? WHERE `id`=" +id ;
         try {
            PreparedStatement statement = cnx.getConnection().prepareStatement(req);

            
            statement.setInt(1, obj.getId());
            statement.setInt(2, obj.getIdUser());
            statement.setInt(3,obj.getNbreInscription());
            statement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }*/
 
    }
    
    
    
    
    

    

