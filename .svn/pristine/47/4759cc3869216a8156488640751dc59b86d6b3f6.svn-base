package com.cmu.sigicmu.param.domaine;

import com.cmu.base.domaine.BaseLibCodeDesc;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "table_valeur")
public class TableValeur extends BaseLibCodeDesc {

    @Size(max = 100)
    private String lib;
    
    @Size(max = 100)
    private String code;
    
    @Size(max = 100)
    private String lib1;
    
    @Size(max = 100)
    private String lib2;
    
    @Size(max = 100)
    private String lib3;
    
    @Size(max = 100)
    private String lib4;
    
    @Size(max = 100)
    private String lib5;
    
    private Integer ordre;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "est_par_defaut")
    private boolean estParDefaut;
    
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TableValeurType type;

    @ManyToMany(mappedBy = "initiativeList")
    private List<ActionVerification> actionVerificationList;

    public TableValeur() {
    }

    public String getLib() {
        return lib;
    }

    public void setLib(String lib) {
        this.lib = lib;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    

    public String getLib1() {
        return lib1;
    }

    public void setLib1(String lib1) {
        this.lib1 = lib1;
    }

    public String getLib2() {
        return lib2;
    }

    public void setLib2(String lib2) {
        this.lib2 = lib2;
    }

    public String getLib3() {
        return lib3;
    }

    public void setLib3(String lib3) {
        this.lib3 = lib3;
    }

    public String getLib4() {
        return lib4;
    }

    public void setLib4(String lib4) {
        this.lib4 = lib4;
    }

    public String getLib5() {
        return lib5;
    }

    public void setLib5(String lib5) {
        this.lib5 = lib5;
    }

    public Integer getOrdre() {
        return ordre;
    }

    public void setOrdre(Integer ordre) {
        this.ordre = ordre;
    }

    public boolean getEstParDefaut() {
        return estParDefaut;
    }

    public void setEstParDefaut(boolean estParDefaut) {
        this.estParDefaut = estParDefaut;
    }

    public TableValeurType getType() {
        return type;
    }

    public void setType(TableValeurType type) {
        this.type = type;
    }

    public List<ActionVerification> getActionVerificationList() {
        return actionVerificationList;
    }

    public void setActionVerificationList(List<ActionVerification> actionVerificationList) {
        this.actionVerificationList = actionVerificationList;
    }
    
    public void addAction(ActionVerification av) {
        if (actionVerificationList == null) {
            actionVerificationList = new ArrayList<>();
        }
        if (!actionVerificationList.contains(av)) {
            actionVerificationList.add(av);
        }
    }

    public void removeAllActions() {
        if (actionVerificationList == null) {
            actionVerificationList = new ArrayList<>();
        }else{
            actionVerificationList.clear();
        }
    }
}
