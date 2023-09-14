package com.cmu.base.bean;

import com.cmu.base.domaine.BaseLibCodeDesc;
import com.cmu.base.service.BaseLibCodeService;
import com.cmu.base.service.exception.EchecSelectException;

public class BaseCheckErrorLibCodeDesc extends BaseCheckErrorLibCode {
    
    private BaseLibCodeDesc obj;
            
    public BaseCheckErrorLibCodeDesc(BaseLibCodeDesc obj, BaseLibCodeService srv) {
        super(obj, srv);
        this.obj = obj;
    }

    @Override
    public boolean sansErreur() throws EchecSelectException {
        boolean bSansErreur = true;
    
        bSansErreur = super.sansErreur()
                && checkLongueurDescription(obj);

        return bSansErreur;         
    }
}
