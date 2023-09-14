package com.cmu.sigicmu.admin.domaine;

public enum TypeEntiteEnum {
    Siege("SIEGE"),
    ServiceRegional("SR"),
    UniteTechniqueGestion("UTG");
    
    private String code;

    private TypeEntiteEnum(String code) {
        this.code = code;
    }

    public String getNiveau() {
        return this.code;
    }
}
