package com.cmu.agence.rh.domaine;

import com.cmu.base.domaine.BaseLib;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "absence_exceptionnelle")
public class AbsenceExceptionnelle extends BaseLib {

   
    @Basic(optional = false)
    @NotNull
    @Column(name = "nb_jour")
    private int nbJour;

    public AbsenceExceptionnelle() {
    }

    public int getNbJour() {
        return nbJour;
    }

    public void setNbJour(int nbJour) {
        this.nbJour = nbJour;
    }

    
    
}
