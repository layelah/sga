package com.cmu.agence.rh.service;

import com.cmu.base.service.BaseLibService;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.persistence.StoredProcedureQuery;


@Stateless
public class CalculerCongesService extends BaseLibService {

    @Schedule(hour="1", persistent = false)
    public void calculerSoldeConges() {
        StoredProcedureQuery psRecalculerSoldeConges = getEm().createStoredProcedureQuery("ps_recalculer_solde_conges");        
        psRecalculerSoldeConges.execute();    
    }   
    
    // Cette methode appele la procedure stockee 
    // pour changer le niveau de validation apres 48h sans validation du SH
    @Schedule(hour = "0", minute = "0", second = "0", persistent = false)
    public void changeNiveauValidation() {
        StoredProcedureQuery psChangeNiveauValidation = getEm().createStoredProcedureQuery("ps_validation_sh_rh");        
        psChangeNiveauValidation.execute();    
    }  
    
    // Cette methode appele la procedure stockee 
    // pour mettre a jour le compteur des reliquats a 72 jours si ca se depasse
    @Schedule(hour = "0", minute = "0", second = "0", persistent = false)
    public void blocageReliquat() {
        StoredProcedureQuery psblocageReliquat = getEm().createStoredProcedureQuery("ps_blocage_reliquat");        
        psblocageReliquat.execute();    
    }  
}