package com.cmu.sigicmu.admin.domaine;

import com.cmu.base.domaine.BaseLibCodeDesc;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "service")
public class Service extends BaseLibCodeDesc {

    @Column(nullable = false)
    private int niveau;
    
    //@Column(name = "id")
    //private int identifiant;
    
    @Column(name = "lib")
    private String lib;

    @Column(name = "code")
    private String code;
        
    @JoinColumn(name = "service_rattachement_id", referencedColumnName = "id")
    @ManyToOne(fetch = EAGER)
    private Service serviceRattachement;
    
    @JoinColumn(name = "agent_id", referencedColumnName = "id")
    @ManyToOne(fetch = EAGER)
    private Agent chefDeService;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "service", fetch = LAZY)
    private List<Agent> agentList;
        
    public Service() {
    }

    
    //public int getIdentifiant() {
    //    return identifiant;
    //}

    //public void setIdentifiant(int identifiant) {
    //    this.identifiant = identifiant;
   // }
    
    

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }
    
    public String getLibelle() {
        return lib;
    }

    public void setLibelle(String libelle) {
        this.lib = libelle;
    }
    
    public String getcode() {
        return code;
    }

    public void setcode(String code) {
        this.code = code;
    }
    

    public Service getServiceRattachement() {
        return serviceRattachement;
    }

    public void setServiceRattachement(Service serviceRattachement) {
        this.serviceRattachement = serviceRattachement;
    }

    public Agent getChefDeService() {
        return chefDeService;
    }

    public void setChefDeService(Agent chefDeService) {
        this.chefDeService = chefDeService;
    }
    
    public List<Agent> getAgentList() {
        return agentList;
    }

    public void setAgentList(List<Agent> agentList) {
        this.agentList = agentList;
    }
    
    
}
