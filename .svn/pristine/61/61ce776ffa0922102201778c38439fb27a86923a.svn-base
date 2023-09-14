package com.cmu.base.domaine;

import com.cmu.sigicmu.param.domaine.TableValeur;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class BaseEtatHisto extends BaseDomaine{
    
    @JoinColumn(name = "etat_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private TableValeur etat;
    
    @Column(name = "date_debut", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDebut;    
    
    public BaseEtatHisto() {        
    }

    public BaseEtatHisto(TableValeur etat, Date dateDebut) {        
        this.etat = etat;
        this.dateDebut = dateDebut;
    }
    
    public TableValeur getEtat() {
        return etat;
    }

    public void setEtat(TableValeur etat) {
        this.etat = etat;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }
    
    public abstract BaseDomaine getObjet();
   
    
    public abstract void setObjet(BaseDomaine objet);
}
