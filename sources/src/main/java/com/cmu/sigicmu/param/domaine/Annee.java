package com.cmu.sigicmu.param.domaine;

import com.cmu.agence.rh.domaine.JourFerie;
import com.cmu.base.domaine.BaseDomaine;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "annee")
public class Annee extends BaseDomaine {

    @Basic(optional = false)
    @NotNull
    @Column(name = "annee")
    private int annee;
  
    @Basic(optional = false)
    @NotNull
    @Column(name = "est_cachee")
    private boolean estCachee;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "est_courante")
    private boolean estCourante;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "annee")
    private List<JourFerie> jourFerieList;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "annee")
//    private List<Periode> periodeList;

    public Annee() {
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public boolean getEstCachee() {
        return estCachee;
    }

    public void setEstCachee(boolean estCachee) {
        this.estCachee = estCachee;
    }

    public boolean getEstCourante() {
        return estCourante;
    }

    public void setEstCourante(boolean estCourante) {
        this.estCourante = estCourante;
    }

    public List<JourFerie> getJourFerieList() {
        return jourFerieList;
    }

    public void setJourFerieList(List<JourFerie> jourFerieList) {
        this.jourFerieList = jourFerieList;
    }

//    public List<Periode> getPeriodeList() {
//        return periodeList;
//    }
//
//    public void setPeriodeList(List<Periode> periodeList) {
//        this.periodeList = periodeList;
//    }
}
