package com.cmu.sigicmu.param.domaine;

import com.cmu.base.domaine.BaseLibCode;
import javax.persistence.Basic;
import javax.persistence.Entity;
import static javax.persistence.FetchType.EAGER;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "localite")
public class Localite extends BaseLibCode {

    @Basic(optional = false)
    @NotNull
    private int niveau;
    
    @JoinColumn(name = "localite_rattachement_id", referencedColumnName = "id")
    @ManyToOne(fetch = EAGER)
    private Localite localiteRattachement;
    
    @JoinColumn(name = "type_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = EAGER)
    private TableValeur type;
    
    @JoinColumn(name = "pays_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = EAGER)
    private TableValeur pays;

    public Localite() {
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public Localite getLocaliteRattachement() {
        return localiteRattachement;
    }

    public void setLocaliteRattachement(Localite localiteRattachement) {
        this.localiteRattachement = localiteRattachement;
    }

    public TableValeur getType() {
        return type;
    }

    public void setType(TableValeur type) {
        this.type = type;
    }

    public TableValeur getPays() {
        return pays;
    }

    public void setPays(TableValeur pays) {
        this.pays = pays;
    }
}
