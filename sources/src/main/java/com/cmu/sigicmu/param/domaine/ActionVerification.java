package com.cmu.sigicmu.param.domaine;

import com.cmu.base.domaine.BaseLibDesc;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "action_verification")
public class ActionVerification extends BaseLibDesc {

    @Basic(optional = false)
    @NotNull
    @Column(name = "est_active")
    private boolean estActive;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "est_obligatoire")
    private boolean estObligatoire;
    
    @ManyToMany
        @JoinTable(name = "action_verification_initiative",
            joinColumns = @JoinColumn(name = "action_verification_id"),
            inverseJoinColumns = @JoinColumn(name = "initiative_id"))
    private List<TableValeur> initiativeList;
  
    public ActionVerification() {
    }

    public boolean isEstActive() {
        return estActive;
    }

    public void setEstActive(boolean estActive) {
        this.estActive = estActive;
    }

    public boolean isEstObligatoire() {
        return estObligatoire;
    }

    public void setEstObligatoire(boolean estObligatoire) {
        this.estObligatoire = estObligatoire;
    }

    public List<TableValeur> getInitiativeList() {
        return initiativeList;
    }

    public void setInitiativeList(List<TableValeur> initiativeList) {
        this.initiativeList = initiativeList;
    }

   public void addInitiative(TableValeur tv) {
        if (initiativeList == null) {
            initiativeList = new ArrayList<>();
        }
        if (!initiativeList.contains(tv)) {
            initiativeList.add(tv);
        }
    }

    public void removeAllInitiative() {
        if (initiativeList == null) {
            initiativeList = new ArrayList<>();
        }else{
            initiativeList.clear();
        }
    }
 }
