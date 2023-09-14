package com.cmu.base.bean;

public enum TypePageEnum {
    LIST("List"),
    FORMULAIRE("Form"),
    INFO("Info"),
    SUPPRESSION("Sup"),
    RECHERCHE("Rech"),
    MODIFIER("Modif"),
    ANNULER("Annule"),
    TRAITEMENT("Traitement"),
    INSTRURE("Instuire"),
    STATUTER("Statuer"),
    DemanderComplement("CompDemander"),
    CompleterDossier("CompCompleter"),
    Proroguer("Proroguer");
 
    private String suffixe;

    private TypePageEnum(String suffixe) {
        this.suffixe = suffixe;
    }

    @Override
    public String toString() {
        return getSuffixe();
    }
    
    public String getSuffixe() {
        return suffixe;
    }
}
