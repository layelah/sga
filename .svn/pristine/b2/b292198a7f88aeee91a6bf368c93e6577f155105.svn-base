package com.cmu.agence.rh.domaine;

import com.cmu.base.domaine.BaseDomaine;
import com.cmu.sigicmu.admin.domaine.Utilisateur;
import com.cmu.sigicmu.param.domaine.TableValeur;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "validation_demande")
public class ValidationDemande extends BaseDomaine {

    @Column(name = "date_validation", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateValidation;
    
    @Column(name = "note")
    private String note;
    @Column(name = "validation")
    private int validation;
    
     @Column(name = "statut")
    private String statut;
    
    @JoinColumn(name = "demande_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private DemandePermissionAbsence demandePermissionAbsence;
    
    @JoinColumn(name = "etat_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private TableValeur etat;
    
    @JoinColumn(name = "niveau_validation_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private TableValeur niveauValidation;
    
    @JoinColumn(name = "validateur_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Utilisateur validateur;

    public ValidationDemande() {
    }
    
    public ValidationDemande(DemandePermissionAbsence dem) {
        demandePermissionAbsence = dem;
    }

    public ValidationDemande(Date dateValidation, String note, DemandePermissionAbsence demandePermissionAbsence, TableValeur etat, TableValeur niveauValidation) {
        this.dateValidation = dateValidation;
        this.note = note;
        this.demandePermissionAbsence = demandePermissionAbsence;
        this.etat = etat;
        this.niveauValidation = niveauValidation;
    }

    public Date getDateValidation() {
        return dateValidation;
    }

    public void setDateValidation(Date dateValidation) {
        this.dateValidation = dateValidation;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getValidation() {
        return validation;
    }

    public void setValidation(int validation) {
        this.validation = validation;
    }
    
    

    public DemandePermissionAbsence getDemandePermissionAbsence() {
        return demandePermissionAbsence;
    }

    public void setDemandePermissionAbsence(DemandePermissionAbsence demandePermissionAbsence) {
        this.demandePermissionAbsence = demandePermissionAbsence;
    }

    public TableValeur getEtat() {
        return etat;
    }

    public void setEtat(TableValeur etat) {
        this.etat = etat;
    }

    public TableValeur getNiveauValidation() {
        return niveauValidation;
    }

    public void setNiveauValidation(TableValeur niveauValidation) {
        this.niveauValidation = niveauValidation;
    }

    public Utilisateur getValidateur() {
        return validateur;
    }

    public void setValidateur(Utilisateur validateur) {
        this.validateur = validateur;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
    
    
}
