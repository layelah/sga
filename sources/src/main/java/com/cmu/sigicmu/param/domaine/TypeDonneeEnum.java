package com.cmu.sigicmu.param.domaine;

public enum TypeDonneeEnum {
    Booleen("BOL"),
    Date("DAT"),
    Decimal("DEC"),
    Numerique("NUM"),
    CNI("CNI"),
    Texte("TXT");
    
    private String code;
    
    private TypeDonneeEnum(String code) {
        this.code = code;
    }
    
    @Override
    public String toString() {
        return code;
    }
}
