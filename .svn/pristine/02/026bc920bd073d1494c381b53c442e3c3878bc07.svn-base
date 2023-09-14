package com.cmu.agence.rh.domaine;

import com.cmu.base.domaine.BaseDomaine;
import com.cmu.sigicmu.admin.domaine.Agent;
import com.cmu.util.JIDate;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "solde_conges")
public class SoldeConges extends BaseDomaine {

    @Column(name = "nb_conges_acquis")
    private double nbCongesAcquis;

    @Column(name = "nb_jour_pris_deductible")
    private double nbJourPrisDeductible;

    @Column(name = "nb_jour_pris_non_deductible")
    private double nbJourPrisNonDeductible;

    @Column(name = "nb_conges_reliquat")
    private double nbCongesReliquat;

    @Column(name = "nb_conges_supplementaire")
    private double nbCongesSupplementaire;

    @Column(name = "nb_conges_echus")
    private double nbCongesEchus;

    @Column(name = "nb_conges_encours")
    private double nbCongesEncours;

    @Column(name = "date_dernier_calcul", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDernierCalcul;

    
    @JoinColumn(name = "agent_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Agent agent;

    public SoldeConges() {
    }

    public SoldeConges(Agent agent) {
        this.nbCongesAcquis = 0.0;
        this.nbJourPrisDeductible = 0.0;
        this.nbJourPrisNonDeductible = 0.0;
        this.nbCongesReliquat = 0.0;
        this.nbCongesSupplementaire = 0.0;
        this.nbCongesEchus = 0.0;
        this.nbCongesEncours = 0.0;
        this.dateDernierCalcul = JIDate.now();
        this.agent = agent;
    }
    
    

    public SoldeConges(double nbCongesAcquis, double nbJourPrisNonDeductible, double nbCongesReliquat) {
        this.nbCongesAcquis = nbCongesAcquis;
        this.nbJourPrisNonDeductible = nbJourPrisNonDeductible;
        this.nbCongesReliquat = nbCongesReliquat;
    }
    
    public void setValeur(SoldeConges sc) {
        this.nbCongesAcquis = sc.nbCongesAcquis;
        this.nbJourPrisDeductible = sc.nbJourPrisDeductible;
        this.nbJourPrisNonDeductible = sc.nbJourPrisNonDeductible;
        this.nbCongesReliquat = sc.nbCongesReliquat;
        this.nbCongesEchus = sc.nbCongesEchus;
        this.nbCongesEncours = sc.nbCongesEncours;
        this.nbCongesSupplementaire = sc.nbCongesSupplementaire;
    }

    public double getNbCongesAcquis() {
        return nbCongesAcquis;
    }

    public void setNbCongesAcquis(double nbCongesAcquis) {
        this.nbCongesAcquis = nbCongesAcquis;
    }

    public double getNbJourPris() {
        return nbJourPrisDeductible;
    }

    public void setNbJourPris(double nbJourPris) {
        this.nbJourPrisDeductible = nbJourPris;
    }

    public double getNbJourPrisDeductible() {
        return nbJourPrisDeductible;
    }

    public void setNbJourPrisDeductible(double nbJourPrisDeductible) {
        this.nbJourPrisDeductible = nbJourPrisDeductible;
    }

    public double getNbJourPrisNonDeductible() {
        return nbJourPrisNonDeductible;
    }

    public void setNbJourPrisNonDeductible(double nbJourPrisNonDeductible) {
        this.nbJourPrisNonDeductible = nbJourPrisNonDeductible;
    }

    public double getNbCongesReliquat() {
        return nbCongesReliquat;
    }

    public void setNbCongesReliquat(double nbCongesReliquat) {
        this.nbCongesReliquat = nbCongesReliquat;
    }

    public double getNbCongesSupplementaire() {
        return nbCongesSupplementaire;
    }

    public void setNbCongesSupplementaire(double nbCongesSupplementaire) {
        this.nbCongesSupplementaire = nbCongesSupplementaire;
    }

    public double getNbCongesEchus() {
//        if(nbCongesEchus < 0){
//            return 0.0;
//        }else{
//            return nbCongesEchus;
//        }
        
        return nbCongesEchus;
        
    }

    public void setNbCongesEchus(double nbCongesEchus) {
        this.nbCongesEchus = nbCongesEchus;
    }

    public double getNbCongesEncours() {
        return nbCongesEncours;
    }

    public void setNbCongesEncours(double nbCongesEncours) {
        this.nbCongesEncours = nbCongesEncours;
    }

    public Date getDateDernierCalcul() {
        return dateDernierCalcul;
    }

    public void setDateDernierCalcul(Date dateDernierCalcul) {
        this.dateDernierCalcul = dateDernierCalcul;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }
}
