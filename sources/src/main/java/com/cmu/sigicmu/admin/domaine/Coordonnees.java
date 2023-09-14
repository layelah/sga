package com.cmu.sigicmu.admin.domaine;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;

@Embeddable
@Access(AccessType.FIELD)
public class Coordonnees  {

    //@Pattern(regexp = "^\\+?[\\d ]+$", message = "format numéro non valide")
    //@Column(length = 20)
    private String tel1;
    
    //@Size(min=9, max = 20)
    //@Pattern(regexp = "^\\+?[\\d ]+$", message = "format numéro non valide")
    //@Column(length = 20)
    private String tel2;
    
    //@Column(length = 20)
    private String fax;
    
    //@Column(length = 100)
    private String adresse1;
    
    //@Column(length = 100)
    private String adresse2;
    
    //@Column(length = 100)
    private String adresse3;
    
    //@Column(length = 255)
    private String email;
    
    //@Column(length = 100)
    private String ville;
    
    public Coordonnees() {
    }

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getAdresse1() {
        return adresse1;
    }

    public void setAdresse1(String adresse1) {
        this.adresse1 = adresse1;
    }

    public String getAdresse2() {
        return adresse2;
    }

    public void setAdresse2(String adresse2) {
        this.adresse2 = adresse2;
    }

    public String getAdresse3() {
        return adresse3;
    }

    public void setAdresse3(String adresse3) {
        this.adresse3 = adresse3;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
 }
