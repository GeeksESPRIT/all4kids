/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

/**
 *
 * @author mrad
 */
public class Utilisateur {
    
public String Nom,Prenom,username,Email,Mdp;
public int id,Age,numero;

  

    public Utilisateur() {
    }

    public Utilisateur(int id,String Nom, String Prenom, String Email, int numero) {
        this.id=id;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Email = Email;
        this.numero = numero;
    }
    public Utilisateur(String Nom, String Prenom, String Email, int numero) {
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Email = Email;
        this.numero = numero;
    }

    public Utilisateur(String Nom, String Prenom, String Email, int id, int numero) {
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Email = Email;
        this.id = id;
        this.numero = numero;
    }

    public Utilisateur(String Nom, String Prenom, String username, String Email, String Mdp, int id, int Age, int numero) {
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.username = username;
        this.Email = Email;
        this.Mdp = Mdp;
        this.id = id;
        this.Age = Age;
        this.numero = numero;
    }

  

    public Utilisateur(String Nom, String Prenom,String username, String Email, String Mdp, int Age) {
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.username=username;
        this.Email = Email; 
        this.Mdp = Mdp;
        this.Age = Age;
    }
  public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getMdp() {
        return Mdp;
    }

    public void setMdp(String Mdp) {
        this.Mdp = Mdp;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int Age) {
        this.Age = Age;
    }
  public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
    @Override
    public String toString() {
        return "Utilisateur{" + "Nom=" + Nom + ", Prenom=" + Prenom + ", username=" + username + ", Email=" + Email + ", Mdp=" + Mdp + ", Age=" + Age + ", numero=" + numero + '}';
    }

    
    



}
