package com.cmu.sigicmu.admin.domaine;

import com.cmu.sigicmu.param.domaine.TableValeur;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@DiscriminatorValue("M")
@Table(name = "personne_morale")
@PrimaryKeyJoinColumn(name = "id")
public class PersonneMorale extends Personne {
    
    @Size(max = 255)
    private String objet;
    
    @Size(max = 20)
    private String ninea;
    
    @Size(max = 20)
    private String rcs;
    
    @JoinColumn(name = "forme_juridique_id", referencedColumnName = "id")
    @ManyToOne
    private TableValeur formeJuridique;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personneMorale")
    private List<Contact> contactList;
    
    public PersonneMorale() {
    }

    public PersonneMorale(String persType) {
        super(persType);
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getNinea() {
        return ninea;
    }

    public void setNinea(String ninea) {
        this.ninea = ninea;
    }

    public String getRcs() {
        return rcs;
    }

    public void setRcs(String rcs) {
        this.rcs = rcs;
    }

    public TableValeur getFormeJuridique() {
        return formeJuridique;
    }

    public void setFormeJuridique(TableValeur formeJuridique) {
        this.formeJuridique = formeJuridique;
    }

    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }
}
