package com.cmu.sigicmu.param.domaine;

import com.cmu.base.domaine.BaseDomaine;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "population")
public class Population extends BaseDomaine {


    @Column(name = "nb_homme", nullable = false)
    private Integer nbHomme;

    @Column(name = "nb_femme", nullable = false)
    private Integer nbFemme;

    @Column(name = "nb_total", nullable = false)
    private Integer nbTotal;

    @JoinColumn(name = "annee_recensement_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private TableValeur anneeRecensement;
    
    @JoinColumn(name = "localite_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Localite localite;
    
    @JoinColumn(name = "zone_geographique_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private TableValeur zoneGeographique;

    public Population() {
    }

    public Integer getNbHomme() {
        return nbHomme;
    }

    public void setNbHomme(Integer nbHomme) {
        this.nbHomme = nbHomme;
    }

    public Integer getNbFemme() {
        return nbFemme;
    }

    public void setNbFemme(Integer nbFemme) {
        this.nbFemme = nbFemme;
    }

    public Integer getNbTotal() {
        return nbTotal;
    }

    public void setNbTotal(Integer nbTotal) {
        this.nbTotal = nbTotal;
    }

    public TableValeur getAnneeRecensement() {
        return anneeRecensement;
    }

    public void setAnneeRecensement(TableValeur anneeRecensement) {
        this.anneeRecensement = anneeRecensement;
    }

    public Localite getLocalite() {
        return localite;
    }

    public void setLocalite(Localite localite) {
        this.localite = localite;
    }

    public TableValeur getZoneGeographique() {
        return zoneGeographique;
    }

    public void setZoneGeographique(TableValeur zoneGeographique) {
        this.zoneGeographique = zoneGeographique;
    }

 }
