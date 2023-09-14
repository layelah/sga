package com.cmu.agence.rh.bean;

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


@Named("vueService")
@javax.enterprise.context.SessionScoped
public class VueServiceBean extends BaseCRUDBean{

    @EJB
    CommonLibService srv;
    
    public VueServiceBean() {
        super(WebRacineEnum.RH, "vueService");
    }

    @Override
    public BaseService getService() {
        return srv;
    }

    @Override
    public BaseDomaine getNewInstance() {
        return new Service();
    }

    @Override
    public boolean sansErreurAvantEnregistrement() throws EchecSelectException {
        Service m_obj = (Service) obj;
        BaseCheckErrorLib chk = new BaseCheckErrorLib(m_obj, srv);
        
        return chk.sansErreur();
    }
}
