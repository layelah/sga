package com.cmu.sigicmu.admin.domaine;

public enum StructureEnum {
    CMU("CMU"),
    Mutuelle("MUT"),
    Bailleur("BAL"),
    Ministere("MSAS");
    
    private String code;

    private StructureEnum(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return this.code;
    }
    
    public String getCode() {
        return this.code;
    }
}
