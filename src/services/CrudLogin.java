/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import javax.swing.JOptionPane;
import utils.MyBdConnection;

/**
 *
 * @author Kapio
 */
public class CrudLogin {

    Statement ste;
    Connection cn;
    PreparedStatement pst;
    ResultSet res;
    public static int id_user;

    public CrudLogin() {
        MyBdConnection cnx = MyBdConnection.getInstanceBD();
        try {
            pst = (PreparedStatement) cnx.getConnection().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(MyBdConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Boolean insertutilisateur(Utilisateur user, List<String> usernames) throws SQLException {
        System.out.println(user.getNom());
        System.out.println(user.getPrenom());
        System.out.println(user.getEmail());
        System.out.println(user.getAge());
        System.out.println(user.getMdp());

        String requete = "INSERT INTO `allforkids`.`users` (`id`,`username`, `nom`, `prenom`, `mail`, `age`, `mdp`) VALUES (NULL,'" + user.getUsername() + "', '"
                + user.getNom() + "', '" + user.getPrenom() + "', '" + user.getEmail() + "', '" + user.getAge() + "', '" + user.getMdp() + "')";
        if (usernames.contains(user.getUsername())) {
            JOptionPane.showMessageDialog(null, "Nom d'utilisateur existe déja", "Nom d'utilisateur existe déja", 1);

            return false;
        } else {
            ste = cn.createStatement();
            ste.executeUpdate(requete);
            return true;
        }
    }

    public boolean testlogin(String username, String password) throws SQLException {

        String requete = "select id,username,mdp from users where username='" + username + "'and mdp='" + password + "'";
        ste = cn.createStatement();
        res = ste.executeQuery(requete);
        int count = 0;

        while (res.next()) {
            count++;
            id_user = res.getInt(1);
        }
        if (count == 1) {

            return true;
        }
        return false;
    }

    public List<String> Getuserames() throws SQLException {
        String requete = "Select username from users";
        List<String> list = new ArrayList<>();
        ste = cn.createStatement();
        res = ste.executeQuery(requete);
        while (res.next()) {
            list.add(res.getString(1));
        }
        System.out.println("" + list.toString());
        return list;

    }

    /*
    public void insertPes(Personne p) throws SQLException{
        String requete = "insert into personne(nom,prenom) values (?,?)";
        pst=cn.prepareStatement(requete);
        pst.setString(1, p.getNom());
        pst.setString(2, p.getPrenom());
        pst.executeUpdate();
    }
    public void Delete(int id) throws SQLException{
        String requete = "Delete from personne where id =" +id ;
        ste=cn.createStatement();
        ste.executeUpdate(requete);
    }
    
    public List<Personne> DisplayAll() throws SQLException{
        String requete = "Select * from personne";
        List<Personne> list = new ArrayList<>();
        ste=cn.createStatement();
        res=ste.executeQuery(requete);
        while(res.next())
        {
            Personne p = new Personne(res.getInt(1), res.getString(2), res.getString(3));
            list.add(p);
        }
        return list;
        
    }
    public void Update(Personne p) throws SQLException{
        String requete = "Update personne set nom=?,prenom=? where id = ?";
        pst=cn.prepareStatement(requete);
        pst.setString(1, p.getNom());
        pst.setString(2, p.getPrenom());
        pst.setInt(3, p.getId());
        pst.executeUpdate();
        
    }
     */
}
