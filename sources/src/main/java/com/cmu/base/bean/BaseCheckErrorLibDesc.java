package com.cmu.base.bean;

import com.cmu.base.domaine.BaseLibDesc;
import com.cmu.base.service.BaseLibService;
import com.cmu.base.service.exception.EchecSelectException;

public class BaseCheckErrorLibDesc extends BaseCheckErrorLib {
    
    private BaseLibDesc obj;
            
    public BaseCheckErrorLibDesc(BaseLibDesc obj, BaseLibService srv) {
        super(obj, srv);
        this.obj = obj;
    }

    @Override
    public boolean sansErreur() throws EchecSelectException {
        boolean bSansErreur = true;
    
        bSansErreur = super.sansErreur()
                && checkLongueurDescription(obj.getDescription());

        return bSansErreur;
    }
}
