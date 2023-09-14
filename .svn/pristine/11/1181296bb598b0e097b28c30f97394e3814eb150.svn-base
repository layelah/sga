package com.cmu.base.bean;

import com.cmu.base.domaine.BaseLibCode;
import com.cmu.base.service.BaseLibCodeService;
import com.cmu.base.service.exception.EchecSelectException;

public class BaseCheckErrorLibCode extends BaseCheckErrorLib {

    protected BaseLibCode obj;
    protected BaseLibCodeService srv;
    
    public BaseCheckErrorLibCode(BaseLibCode obj, BaseLibCodeService srv) {
        super(obj, srv);
        this.obj = obj;
        this.srv = srv;
    }
    
    @Override
    public boolean sansErreur() throws EchecSelectException {
        boolean bSansErreur = super.sansErreur()
            && checkLongueur("code", obj.getCode(), 1, 20)
            && estCodeUnique(obj);
                    
        return bSansErreur;
    }
    
    public boolean estCodeUnique(BaseLibCode obj) throws EchecSelectException {
        boolean bSansErreur = true;
        
        if  (srv.existeCode(obj)) {
            bSansErreur = false;
            JsfUtil.addErrorMessage("code", "le code doit être unique");
        }
       
        return bSansErreur;
    }
    
        
}
