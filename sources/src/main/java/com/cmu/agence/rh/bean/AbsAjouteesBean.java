package com.cmu.agence.rh.bean;

import com.cmu.agence.rh.domaine.Absence;
import com.cmu.sigicmu.admin.domaine.Service;
import com.cmu.base.bean.BaseCRUDBean;
import com.cmu.base.bean.BaseCheckErrorLib;
import com.cmu.base.bean.JsfUtil;
import com.cmu.base.domaine.BaseDomaine;
import com.cmu.base.service.BaseService;
import com.cmu.base.service.CommonLibService;
import com.cmu.base.service.exception.EchecSelectException;
import com.cmu.sigicmu.param.domaine.WebRacineEnum;
import javax.ejb.EJB;
import javax.inject.Named;


@Named("absAjoutees")
@javax.enterprise.context.SessionScoped
public class AbsAjouteesBean extends BaseCRUDBean{

    @EJB
    CommonLibService srv;
    
    public AbsAjouteesBean() {
        super(WebRacineEnum.RH, "absAjoutees");
    }

    @Override
    public BaseService getService() {
        return srv;
    }

    @Override
    public BaseDomaine getNewInstance() {
        return new Absence();
    }

    @Override
    public boolean sansErreurAvantEnregistrement() throws EchecSelectException {
        Absence m_obj = (Absence) obj;
        BaseCheckErrorLib chk = new BaseCheckErrorLib(m_obj, srv);
        
        return chk.sansErreur();
    }
}
