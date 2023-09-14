package com.cmu.agence.rh.domaine;

import com.cmu.base.domaine.BaseLib;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "conges_non_deductible")
public class CongesNonDeductible extends BaseLib {

   
    @Basic(optional = false)
    @NotNull
    @Column(name = "nb_jour_conges")
    private int nbJourConges;

    public CongesNonDeductible() {
    }

    public int getNbJourConges() {
        return nbJourConges;
    }

    public void setNbJourConges(int nbJourConges) {
        this.nbJourConges = nbJourConges;
    }
}
