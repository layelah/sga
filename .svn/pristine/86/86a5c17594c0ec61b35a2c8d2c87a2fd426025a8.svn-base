package com.cmu.agence.rh.bean;

import com.cmu.agence.rh.domaine.CongesNonDeductible;
import com.cmu.agence.rh.service.SoldeCongesService;
import com.cmu.base.bean.BaseCRUDBean;
import com.cmu.base.bean.BaseCheckErrorLib;
import com.cmu.base.domaine.BaseDomaine;
import com.cmu.base.service.BaseService;
import com.cmu.base.service.exception.EchecSelectException;
import com.cmu.sigicmu.param.domaine.WebRacineEnum;
import javax.ejb.EJB;
import javax.inject.Named;


@Named("congNonDeduc")
@javax.enterprise.context.SessionScoped
public class CongesNonDeductibleBean extends BaseCRUDBean{

    @EJB
    SoldeCongesService srv;
    
    public CongesNonDeductibleBean() {
        super(WebRacineEnum.RH, "congesNonDeductible");
    }

    @Override
    public BaseService getService() {
        return srv;
    }

    @Override
    public BaseDomaine getNewInstance() {
        return new CongesNonDeductible();
    }

    @Override
    public boolean sansErreurAvantEnregistrement() throws EchecSelectException {
        CongesNonDeductible m_obj = (CongesNonDeductible) obj;
        BaseCheckErrorLib chk = new BaseCheckErrorLib(m_obj, srv);

        return chk.sansErreur();
    }
}
