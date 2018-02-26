/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author mokht
 */
public class DemandeInscription {
    
    private int id;
    private int id_participant ;
    private int nbreInscription	;
    private int idEtab;
    private int enabled;

    public DemandeInscription() {
    }

    public DemandeInscription(int id, int id_participant, int nbreInscription, int idEtab) {
        this.id = id;
        this.id_participant = id_participant;
        this.nbreInscription = nbreInscription;
        this.idEtab = idEtab;
    }

    
    public int getId_participant() {
        return id_participant;
    }

    public void setId_participant(int id_participant) {
        this.id_participant = id_participant;
    }

    public int getIdEtab() {
        return idEtab;
    }

    public void setIdEtab(int idEtab) {
        this.idEtab = idEtab;
    }


    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getNbreInscription() {
        return nbreInscription;
    }

    public void setNbreInscription(int nbreInscription) {
        this.nbreInscription = nbreInscription;
    }
    
    
    
    
    
    
}
