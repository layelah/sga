package com.cmu.sigicmu.param.domaine;

import com.cmu.base.domaine.BaseDomaine;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author User
 */
@Entity
@Table(name = "calendrier")
@NamedQueries({
    @NamedQuery(name = "Calendrier.findAll", query = "SELECT c FROM Calendrier c")})
public class Calendrier extends BaseDomaine {

    @Basic(optional = false)
    @NotNull
    private int jour;
    
    @Basic(optional = false)
    @NotNull
    private int mois;
    
    @Basic(optional = false)
    @NotNull
    private int annee;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "fin_annee")
    private boolean finAnnee;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "fin_mois")
    private boolean finMois;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "fin_trimestre")
    private boolean finTrimestre;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "fin_semestre")
    private boolean finSemestre;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_du_jour")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDuJour;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "date_du_jour_lib")
    private String dateDuJourLib;

    public Calendrier() {
    }

    public int getJour() {
        return jour;
    }

    public void setJour(int jour) {
        this.jour = jour;
    }

    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public boolean getFinAnnee() {
        return finAnnee;
    }

    public void setFinAnnee(boolean finAnnee) {
        this.finAnnee = finAnnee;
    }

    public boolean getFinMois() {
        return finMois;
    }

    public void setFinMois(boolean finMois) {
        this.finMois = finMois;
    }

    public boolean getFinTrimestre() {
        return finTrimestre;
    }

    public void setFinTrimestre(boolean finTrimestre) {
        this.finTrimestre = finTrimestre;
    }

    public boolean getFinSemestre() {
        return finSemestre;
    }

    public void setFinSemestre(boolean finSemestre) {
        this.finSemestre = finSemestre;
    }

    public Date getDateDuJour() {
        return dateDuJour;
    }

    public void setDateDuJour(Date dateDuJour) {
        this.dateDuJour = dateDuJour;
    }

    public String getDateDuJourLib() {
        return dateDuJourLib;
    }

    public void setDateDuJourLib(String dateDuJourLib) {
        this.dateDuJourLib = dateDuJourLib;
    }

  
}
