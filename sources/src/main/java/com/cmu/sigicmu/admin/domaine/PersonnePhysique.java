package com.cmu.sigicmu.admin.domaine;

import com.cmu.sigicmu.param.domaine.TableValeur;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("P")
@Table(name = "personne_physique")
//@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name="id", referencedColumnName="id")
public class PersonnePhysique extends Personne {

    private String prenom;

    @Column(name="nom_jeune_fille") 
    private String nomJeuneFille;
    
    @JoinColumn(name = "civilite_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private TableValeur civilite;
    
    @JoinColumn(name = "genre_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private TableValeur genre;
    
    @Column(name = "nom_complet", nullable = false)
    private String nomComplet;
    
    @JoinColumn(name = "statut_marital_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private TableValeur statutMarital;
            
    public PersonnePhysique() {
    }

    public PersonnePhysique(String persType) {
        super(persType);
    }
    
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSurnom() {
        return getSurnomSigle();
    }

    public void setSurnom(String surnom) {
        setSurnomSigle(surnom);
    }

    public String getNomJeuneFille() {
        return nomJeuneFille;
    }

    public void setNomJeuneFille(String nomJeuneFille) {
        this.nomJeuneFille = nomJeuneFille;
    }

    public TableValeur getCivilite() {
        return civilite;
    }

    public void setCivilite(TableValeur civilite) {
        this.civilite = civilite;
    }

    public TableValeur getGenre() {
        return genre;
    }

    public void setGenre(TableValeur genre) {
        this.genre = genre;
    }

    public String getNomComplet() {
        return nomComplet;
    }

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }

    public TableValeur getStatutMarital() {
        return statutMarital;
    }

    public void setStatutMarital(TableValeur statutMarital) {
        this.statutMarital = statutMarital;
    }
}
