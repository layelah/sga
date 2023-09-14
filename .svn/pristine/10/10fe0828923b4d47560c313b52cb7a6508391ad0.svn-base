package com.cmu.base.bean;

import com.cmu.base.domaine.BaseCode;
import com.cmu.base.service.BaseCodeService;
import com.cmu.base.service.exception.EchecSelectException;

public class BaseCheckErrorCode extends BaseCheckError {

    private BaseCode obj;
    private BaseCodeService srv;

    public BaseCheckErrorCode(BaseCode obj, BaseCodeService srv) {
        super();
        this.obj = obj;
        this.srv = srv;
    }

    @Override
    public boolean sansErreur() throws EchecSelectException {
        boolean bSansErreur = true;
    
        if (srv.existeCode(obj)) {
            bSansErreur = false;
            JsfUtil.addErrorMessage("code", "le code doit être unique");
        }

        return bSansErreur;
    }

 }
