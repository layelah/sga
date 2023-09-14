package com.cmu.base.bean;

import com.cmu.agence.rh.domaine.Absence;
import com.cmu.base.domaine.BaseLib;
import com.cmu.base.service.BaseLibService;
import com.cmu.base.service.CommonLibService;
import com.cmu.base.service.exception.EchecSelectException;


public class BaseCheckErrorLib extends BaseCheckError {
    
    private BaseLib obj;
    private BaseLibService srv;
    
    public BaseCheckErrorLib(BaseLib obj, BaseLibService srv) {
        super();
        this.obj = obj;
        this.srv = srv;
    }

    public BaseCheckErrorLib(Absence m_obj, CommonLibService srv) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean sansErreur() throws EchecSelectException {
        boolean bSansErreur = true;

        if (srv.existeLib(obj)) {
            bSansErreur = false;
            JsfUtil.addErrorMessage("lib", "le libellé doit être unique");
        }

        System.out.println("BaseCheckErrorLib.sansErreur = "+ bSansErreur);
        
        return bSansErreur;
    }

    public BaseLib getObj() {
        return obj;
    }

    public void setObj(BaseLib obj) {
        this.obj = obj;
    }

    public BaseLibService getSrv() {
        return srv;
    }

    public void setSrv(BaseLibService srv) {
        this.srv = srv;
    }
}
