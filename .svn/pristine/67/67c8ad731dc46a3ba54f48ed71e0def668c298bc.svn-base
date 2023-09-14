package com.cmu.sigicmu.param.domaine;

import com.cmu.base.bean.TypePageEnum;

public enum WebRacineEnum {
    HOME("/"),
    BUREAU("BUR"),
    PARAMETRAGE("PRM"),
    ADMINISTRATION("ADM"),
    FACTURE("FCT"),
    MUTUELLE("MUT"),
    RH("RH");
    
    private String repertoire;

    private WebRacineEnum(String repertoire) {
        this.repertoire = repertoire;
    }

    @Override
    public String toString() {
        return this.repertoire;
    }
    
    public String getCheminComplet(String racine, TypePageEnum typePage) {
        return getCheminCompletStr(racine, typePage.toString());
    }

    public String getCheminCompletStr(String racine, String typePage) {
        return getCheminComplet(racine + typePage);
    }
    
        public String getCheminComplet(String page) {
        return "/"+ this.toString() +"/"+ page +".xhtml?faces-redirect=true";
    }
}
