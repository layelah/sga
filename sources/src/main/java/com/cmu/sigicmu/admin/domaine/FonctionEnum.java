package com.cmu.sigicmu.admin.domaine;

public enum FonctionEnum {
    DirecteurGeneral("DG"),
    SecretaireGeneral("SG"),
    DirecteurAdminFin("DAF"),
    ResponsableRH("RH"),
    Directeur("DIR"),
    ChefDivision("CD"),
    AdministrateurSystem("RED"),
    ChefBureau("CB"),
    AdminGeneral("ROOT");
       
    private String code;

    private FonctionEnum(String code) {
        this.code = code;
    }

    public String getNiveau() {
        return this.code;
    }
    
    @Override
    public String toString() {
        return code;
    }
    
    public String getCode() {
        return code;
    } 
}
