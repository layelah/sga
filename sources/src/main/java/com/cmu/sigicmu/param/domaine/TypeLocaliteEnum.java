package com.cmu.sigicmu.param.domaine;

public enum TypeLocaliteEnum {
    Nationale("NT"),
    Region("RG"),
    Departement("DP"),
    Commune("CM"),
    Ville("VL");
    
    private String code;

    private TypeLocaliteEnum(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return this.code;
    }
}

