package com.cmu.sigicmu.param.domaine;

import com.cmu.base.domaine.BaseLibCodeDesc;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "table_valeur_type")
public class TableValeurType extends BaseLibCodeDesc {

    @Basic(optional = false)
    @NotNull
    @Column(name = "nb_champ_supplementaire")
    private Integer nbChampSupplementaire;

    @Column(name = "est_cache")
    private Boolean estCache;

    @Column(name = "est_invariant")
    private Boolean estInvariant;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "type")
    private List<TableValeurLib> tableValeurLibList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "type")
    private List<TableValeur> tableValeurList;

    public TableValeurType() {
    }

    public Integer getNbChampSupplementaire() {
        return nbChampSupplementaire;
    }

    public void setNbChampSupplementaire(Integer nbChampSupplementaire) {
        this.nbChampSupplementaire = nbChampSupplementaire;
    }

    public Boolean getEstCache() {
        return estCache;
    }

    public void setEstCache(Boolean estCache) {
        this.estCache = estCache;
    }

    public Boolean getEstInvariant() {
        return estInvariant;
    }

    public void setEstInvariant(Boolean estInvariant) {
        this.estInvariant = estInvariant;
    }

    public List<TableValeurLib> getTableValeurLibList() {
        return tableValeurLibList;
    }

    public void setTableValeurLibList(List<TableValeurLib> tableValeurLibList) {
        this.tableValeurLibList = tableValeurLibList;
    }

    public List<TableValeur> getTableValeurList() {
        return tableValeurList;
    }

    public void setTableValeurList(List<TableValeur> tableValeurList) {
        this.tableValeurList = tableValeurList;
    }
}
