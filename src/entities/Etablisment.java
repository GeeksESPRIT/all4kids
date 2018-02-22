/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Mokh
 */
public class Etablisment {

    private int id;
    public String nom;
    private String adresse;
    private String tel;
    private int idUser;
    private String type;
    private int Rating;
    private String proprietaire;
    private String email;
    private String datecreation;
    private int capacite;
    private String description;
    private int enabled;

    public Etablisment() {
    }

    public Etablisment(int id, String nom, String adresse, String tel, String type, int idUser,  String proprietaire, String email, String datecreation, int capacite, String description,int Rating,int enabled) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.tel = tel;
        this.type = type;
        this.idUser = idUser;
        this.proprietaire = proprietaire;
        this.email = email;
        this.datecreation = datecreation;
        this.capacite = capacite;
        this.description = description;
        this.Rating = Rating;
        this.enabled= enabled;
    }

    public Etablisment(int id, String nom, String adresse, String tel, String type, String proprietaire, String email, String datecreation, int capacite, String description) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.tel = tel;
        this.type = type;
        this.proprietaire = proprietaire;
        this.email = email;
        this.datecreation = datecreation;
        this.capacite = capacite;
        this.description = description;
    }

    public Etablisment(String nom, String adresse, String tel, int idUser, String type, int Rating, String proprietaire, String email, String datecreation, int capacite, String description) {
        this.nom = nom;
        this.adresse = adresse;
        this.tel = tel;
        this.idUser = idUser;
        this.type = type;
        this.Rating = Rating;
        this.proprietaire = proprietaire;
        this.email = email;
        this.datecreation = datecreation;
        this.capacite = capacite;
        this.description = description;
               
    }

    public String getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(String proprietaire) {
        this.proprietaire = proprietaire;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(String datecreation) {
        this.datecreation = datecreation;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Etablisment(int id, String nom, String adresse, String tel, String type) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.tel = tel;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getRating() {
        return Rating;
    }

    public void setRating(int Rating) {
        this.Rating = Rating;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "Etablisment{" + "id=" + id + ", nom=" + nom + ", adresse=" + adresse + ", tel=" + tel + ", idUser=" + idUser + ", type=" + type + ", Rating=" + Rating + ", proprietaire=" + proprietaire + ", email=" + email + ", datecreation=" + datecreation + ", capacite=" + capacite + ", description=" + description + ", enabled=" + enabled + '}';
    }

   

}
