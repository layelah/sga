package com.cmu.sigicmu.admin.domaine;

import com.cmu.base.domaine.BaseDomaine;
import com.cmu.sigicmu.param.domaine.TableValeur;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "agent_fonction")
public class AgentFonction extends BaseDomaine {

    @Column(name = "date_debut")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDebut;
    
    @Column(name = "date_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateFin;
    
    private String notes;
    
    @JoinColumn(name = "agent_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Agent agent;
   
    @JoinColumn(name = "fonction_id", referencedColumnName = "id", nullable=false)
    @ManyToOne(optional = false)
    private TableValeur fonction;
    
    @JoinColumn(name = "service_id", referencedColumnName = "id")
    @ManyToOne
    private Service service;
    
    @JoinColumn(name = "type_organe_id", referencedColumnName = "id")
    @ManyToOne
    private TableValeur typeOrgane;

    
    
    public AgentFonction() {
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public TableValeur getFonction() {
        return fonction;
    }

    public void setFonction(TableValeur fonction) {
        this.fonction = fonction;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public TableValeur getTypeOrgane() {
        return typeOrgane;
    }

    public void setTypeOrgane(TableValeur typeOrgane) {
        this.typeOrgane = typeOrgane;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
