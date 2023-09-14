package com.cmu.sigicmu.admin.domaine;

import com.cmu.base.domaine.BaseLibCode;
import com.cmu.sigicmu.param.domaine.Localite;
import com.cmu.sigicmu.param.domaine.TableValeur;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import static javax.persistence.FetchType.LAZY;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "entite")
public class Entite extends BaseLibCode {

    @Embedded
    private Coordonnees coord;
   
    @JoinColumn(name = "responsable_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Agent responsable;

    @JoinColumn(name = "type_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private TableValeur type;
    
    @JoinColumn(name = "region_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Localite region;
    
    @JoinColumn(name = "departement_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Localite departement;
    
    @JoinColumn(name = "commune_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Localite commune;
        
    @JoinColumn(name = "entite_rattachement_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Entite entiteRattachement;

    @JoinColumn(name = "champ_intervention_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private TableValeur champIntervention;
            
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entite", fetch = LAZY)
    private List<Agent> agentList;
    
    public Entite() {
        coord = new Coordonnees();
    }

    public Coordonnees getCoord() {
        return coord;
    }

    public void setCoord(Coordonnees coord) {
        this.coord = coord;
    }

    public Localite getRegion() {
        return region;
    }

    public void setRegion(Localite region) {
        this.region = region;
    }

    public Localite getDepartement() {
        return departement;
    }

    public void setDepartement(Localite departement) {
        this.departement = departement;
    }

    public Localite getCommune() {
        return commune;
    }

    public void setCommune(Localite commune) {
        this.commune = commune;
    }

    public Agent getResponsable() {
        return responsable;
    }

    public void setResponsable(Agent responsable) {
        this.responsable = responsable;
    }

    public TableValeur getType() {
        return type;
    }

    public void setType(TableValeur type) {
        this.type = type;
    }

    public Entite getEntiteRattachement() {
        return entiteRattachement;
    }

    public TableValeur getChampIntervention() {
        return champIntervention;
    }

    public void setChampIntervention(TableValeur champIntervention) {
        this.champIntervention = champIntervention;
    }
    
    public void setEntiteRattachement(Entite entiteRattachement) {
        this.entiteRattachement = entiteRattachement;
    }

    public List<Agent> getAgentList() {
        return agentList;
    }

    public void setAgentList(List<Agent> agentList) {
        this.agentList = agentList;
    }

    public boolean getEstSiege() {
        return (TypeEntiteEnum.Siege.toString().equals(this.getTypeCode()));
    }
    
    public String getTypeCode() {
        return ((this.getType() != null) ? this.getType().getCode() : "");
    }
}