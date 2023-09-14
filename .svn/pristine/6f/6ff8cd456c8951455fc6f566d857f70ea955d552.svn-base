package com.cmu.base.bean;

import com.cmu.sigicmu.bureau.bean.AuthentificationBean;
import com.cmu.sigicmu.param.domaine.WebRacineEnum;
import java.io.Serializable;
import javax.inject.Inject;
import javax.inject.Named;

public abstract class BaseBean implements Serializable {
    
    // Attributs
    ////////////////////////////////////////////////////////////////////////
    
    protected WebRacineEnum repertoire;
    protected String racine;
    protected String defaultPage;
    protected String previousPage;
    protected ModeAffichageEnum zoneAffichage = ModeAffichageEnum.LIST;
  
  
    @Inject
    @Named("auth")
    private AuthentificationBean auth;

    
    // Constructeurs
    ////////////////////////////////////////////////////////////////////////
    
    public BaseBean() {
        zoneAffichage = ModeAffichageEnum.LIST;
    }
    
    public BaseBean(WebRacineEnum repertoire, String racine) {
        this.repertoire = repertoire;
        this.racine = racine;
        this.defaultPage = repertoire.getCheminComplet(racine, TypePageEnum.LIST);
    }
    
    public BaseBean(WebRacineEnum repertoire, String racine, String defaultPage) {
        this.repertoire = repertoire;
        this.racine = racine;
        this.defaultPage = defaultPage;
    }

    
    // Gettets & Setters
    ////////////////////////////////////////////////////////////////////////
   
    public WebRacineEnum getRepertoire() {
        return repertoire;
    }
    
    public void setRepertoire(WebRacineEnum repertoire) {
        this.repertoire = repertoire;
    }
    
    public String getRacine() {
        return racine;
    }
    
    public void setRacine(String racine) {
        this.racine = racine;
    }
    
    public AuthentificationBean getAuth() {
        return auth;
    }

    
    // Navigations
    ////////////////////////////////////////////////////////////////////////
    
    protected String goBack(boolean bRedirect) {
        JsfUtil.viderMessage();
        String sRetour = previousPage;
        previousPage = pageDefault(false);
        
        if (bRedirect) {
            sRetour =  previousPage + "?faces-redirect=true";
        }
        return sRetour;
    }

    public String goBack() {
       // initObject();
        return goBack(false);
    }
    
    //protected void initObject(){}
    
    
    protected String pageDefault(boolean bReditect) {
        if (bReditect) {
            return defaultPage + "?faces-redirect=true";
        }
        return defaultPage;
    }
    
    public String page(String typePage) {
        return repertoire.getCheminCompletStr(racine, typePage);
    }
    
    public boolean getEstModeListe() {
        return (zoneAffichage.equals(ModeAffichageEnum.LIST));
    }
    
    public boolean getEstModeRecherche() {
        return (zoneAffichage.equals(ModeAffichageEnum.RECHERCHE));
    }
}
