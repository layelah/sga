package com.cmu.agence.rh.domaine;

public enum EtatAbsenceEnum {
    
    Cree("CREE"),
    Demandee("DEM"),
    Acceptee("ACC"),
    Refusee("REF"),
    Effective("EFF"),
    Annulee("ANN");
    
    private String code;
    
    private EtatAbsenceEnum(String code) {
        this.code = code;
    }
    
    @Override
    public String toString() {
        return code;
    }
    
    public String getCode() {
        return code;
    }
    
}
