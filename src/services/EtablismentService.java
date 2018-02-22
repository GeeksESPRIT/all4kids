/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyBdConnection;
import entities.Etablisment;
import java.util.ArrayList;
import java.util.List;
import static javafx.ListEtabController.idEtablissement;

/**
 *
 * @author Mokh
 */
public class EtablismentService implements IEtablismentService<Etablisment> {

    private static EtablismentService instance;
    private Statement st;
    private ResultSet rs;

    public EtablismentService() {
        MyBdConnection cnx = MyBdConnection.getInstanceBD();
        try {
            st = cnx.getConnection().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(MyBdConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static EtablismentService getInstance() {
        if (instance == null) {
            instance = new EtablismentService();
        }
        return instance;
    }

    public void ajouterEtablissement(Etablisment object) {
 
        MyBdConnection cnx = MyBdConnection.getInstanceBD();

        Etablisment obj = (Etablisment) object;
        String req = "INSERT INTO `etablisment` "
                + "( `nom`, `adresse`, `fix`, `type`, `idUser`, `proprietaire`, `email`, `datecreation`, `capacite`, `description`, `rating`)"
                + " values (?,?,?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement statement = cnx.getConnection().prepareStatement(req);

            statement.setString(1, obj.getNom());
            statement.setString(2, obj.getAdresse());
            statement.setString(3, obj.getTel());
            statement.setString(4, obj.getType());
            statement.setInt(5, obj.getIdUser());
            statement.setString(6, obj.getProprietaire());
            statement.setString(7, obj.getEmail());
            statement.setString(8, obj.getDatecreation());
            statement.setInt(9, obj.getCapacite());
            statement.setString(10, obj.getDescription());
            statement.setInt(11, obj.getRating());
            statement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void deleteEtablissement(int id) {

        MyBdConnection cnx = MyBdConnection.getInstanceBD();

        String req = "delete from etablisment where id=" + id;
        try {
            PreparedStatement statement = cnx.getConnection().prepareStatement(req);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();

        }

    }

    public void modifierEtablissement(Etablisment obj, int id) {
      MyBdConnection cnx = MyBdConnection.getInstanceBD();

        String req = "UPDATE `allforkids`.`etablisment` SET `nom`=?, `adresse`=?, `fix`=?, `type`=?, `proprietaire`=?, `email`=?, `datecreation`=?, `capacite`=?, `description`=?  where id =?";

        try {
            PreparedStatement statement = cnx.getConnection().prepareStatement(req);

            statement.setString(1, obj.getNom());
            statement.setString(2, obj.getAdresse());
            statement.setString(3, obj.getTel());
            statement.setString(4, obj.getType());
            statement.setString(5, obj.getProprietaire());
            statement.setString(6, obj.getEmail());
            statement.setString(7, obj.getDatecreation());
            statement.setInt(8, obj.getCapacite());
            statement.setString(9, obj.getDescription());

            

            statement.executeUpdate();

        } catch (SQLException ex) {

            ex.printStackTrace();

        }

    }

//    public List<Etablisment> afficherEtablissement(String type) {
//
//        List<Etablisment> etab = new ArrayList<>();
//        MyBdConnection cnx = MyBdConnection.getInstanceBD();
//
//        String req = "SELECT * FROM `etablisment` WHERE `type`=?";
//
//        try {
//            PreparedStatement statement = cnx.getConnection().prepareStatement(req);
//
//            statement.setString(1, type);
//
//            ResultSet rs = statement.executeQuery();
//
//            System.out.println(rs.first());
//            do {
//
//                Etablisment rand = new Etablisment();
//                rand.setNom(rs.getString("nom"));
//                rand.setAdrsse(rs.getString("adrsse"));
//                rand.setTel(rs.getString("fix"));
//
//                rand.setRating(rs.getInt("rating"));
//
//                etab.add(rand);
//
//            } while (rs.next());
//            System.out.println(etab);
//            return etab;
//
//        } catch (SQLException ex) {
//            Logger.getLogger(EtablismentService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return null;
//    }

    public List<Etablisment> afficherEtablissement() throws SQLException {
//       MyBdConnection cnx = MyBdConnection.getInstanceBD();
//        ResultSet rs = cnx.createStatement().executeQuery("Select * from etablisment order by id DESC");
        List<Etablisment> list = new ArrayList<>();
        MyBdConnection cnx = MyBdConnection.getInstanceBD();

        String req = "Select * from etablisment order by id DESC";
        try {
            PreparedStatement statement = cnx.getConnection().prepareStatement(req);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        while (rs.next()) {
            Etablisment p = new Etablisment(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6),rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10), rs.getString(11), rs.getInt(12));
            list.add(p);
        }
        System.out.println("" + list.toString());
        return list;

    }
}
