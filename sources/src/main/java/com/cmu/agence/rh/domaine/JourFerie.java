package com.cmu.agence.rh.domaine;

import com.cmu.base.domaine.BaseLib;
import com.cmu.sigicmu.param.domaine.Annee;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "jour_ferie")
public class JourFerie extends BaseLib {

    @Basic(optional = false)
    @NotNull
    @Column(name = "jour")
    @Temporal(TemporalType.TIMESTAMP)
    private Date jour;
   
    @Basic(optional = false)
    @NotNull
    @Column(name = "est_recurrent")
    private boolean estRecurrent = true;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "est_fixe")
    private boolean estFixe = false;
    
    @JoinColumn(name = "annee_id", referencedColumnName = "id")
    @ManyToOne(optional = true)
    private Annee annee;

    public JourFerie() {
    }

    public Date getJour() {
        return jour;
    }

    public void setJour(Date jour) {
        this.jour = jour;
    }

    public boolean isEstRecurrent() {
        return estRecurrent;
    }

    public void setEstRecurrent(boolean estRecurrent) {
        this.estRecurrent = estRecurrent;
    }

    public boolean isEstFixe() {
        return estFixe;
    }

    public void setEstFixe(boolean estFixe) {
        this.estFixe = estFixe;
    }

    public Annee getAnnee() {
        return annee;
    }

    public void setAnnee(Annee annee) {
        this.annee = annee;
    }
}
