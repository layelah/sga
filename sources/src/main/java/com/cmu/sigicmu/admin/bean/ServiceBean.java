package com.cmu.sigicmu.admin.bean;

import com.cmu.base.bean.BaseCRUDBean;
import com.cmu.base.bean.BaseCheckErrorLibCodeDesc;
import com.cmu.base.domaine.BaseDomaine;
import com.cmu.base.service.CommonLibCodeService;
import com.cmu.base.service.exception.EchecSelectException;
import com.cmu.sigicmu.admin.domaine.Service;
import com.cmu.sigicmu.param.domaine.WebRacineEnum;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named("serv")
@SessionScoped
public class ServiceBean extends BaseCRUDBean{

    @EJB
    CommonLibCodeService lcSrv;
    
    public ServiceBean() {
        super(WebRacineEnum.ADMINISTRATION, "service");
    }

    @Override
    public BaseDomaine getNewInstance() {
        return new Service();
    }

    @Override
    public boolean sansErreurAvantEnregistrement() throws EchecSelectException {
        Service m_obj = (Service) obj;
        BaseCheckErrorLibCodeDesc chk = new BaseCheckErrorLibCodeDesc(m_obj, lcSrv);
        return chk.sansErreur();
    }
        
    @Override
    public void traitementAvantEnregistrement() {
        Service m_obj = (Service) obj;
        if (m_obj != null) {
            m_obj.setNiveau(((m_obj.getServiceRattachement() == null) ? 0: m_obj.getServiceRattachement().getNiveau() + 1));
        }
    }
}

