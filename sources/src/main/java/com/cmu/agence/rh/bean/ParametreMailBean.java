package com.cmu.agence.rh.bean;


import com.cmu.agence.rh.domaine.ParametreMail;
import com.cmu.base.bean.BaseCRUDBean;
import com.cmu.base.bean.BaseCheckErrorLib;
import com.cmu.base.domaine.BaseDomaine;
import com.cmu.base.service.BaseService;
import com.cmu.base.service.CommonLibService;
import com.cmu.base.service.exception.EchecSelectException;
import com.cmu.sigicmu.param.domaine.WebRacineEnum;
import javax.ejb.EJB;
import javax.inject.Named;


@Named("mailNotif")
@javax.enterprise.context.SessionScoped
public class ParametreMailBean extends BaseCRUDBean{

    @EJB
    CommonLibService srv;
    
    public ParametreMailBean() {
        super(WebRacineEnum.RH, "mailNotification");
    }

    @Override
    public BaseService getService() {
        return srv;
    }

    @Override
    public BaseDomaine getNewInstance() {
        return new ParametreMail();
    }

    @Override
    public boolean sansErreurAvantEnregistrement() throws EchecSelectException {
        ParametreMail m_obj = (ParametreMail) obj;
        BaseCheckErrorLib chk = new BaseCheckErrorLib(m_obj, srv);


        
        return chk.sansErreur();
    }
    
    public boolean estNotif(){
        ParametreMail m_obj = (ParametreMail) obj;
        return m_obj.getType().equals("mailNotification");
    }
}
