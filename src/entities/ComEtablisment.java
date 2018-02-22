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
public class ComEtablisment {
    private int id;
    private int idEtablisment;
    private int idUser;
    private String commentaire;
    private int rating;

    public ComEtablisment(int id, int idEtablisment, int idUser, String commentaire, int rating) {
        this.id = id;
        this.idEtablisment = idEtablisment;
        this.idUser = idUser;
        this.commentaire = commentaire;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEtablisment() {
        return idEtablisment;
    }

    public void setIdEtablisment(int idEtablisment) {
        this.idEtablisment = idEtablisment;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "ComEtablisment{" + "id=" + id + ", idEtablisment=" + idEtablisment + ", idUser=" + idUser + ", commentaire=" + commentaire + ", rating=" + rating + '}';
    }
    
}
