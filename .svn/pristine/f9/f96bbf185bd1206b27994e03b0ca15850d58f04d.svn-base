package com.cmu.agence.rh.bean;

import com.cmu.agence.rh.domaine.AbsenceExceptionnelle;
import com.cmu.agence.rh.service.AbsenceExcepService;
import com.cmu.base.bean.BaseCRUDBean;
import com.cmu.base.bean.BaseCheckErrorLib;
import com.cmu.base.domaine.BaseDomaine;
import com.cmu.base.service.BaseService;
import com.cmu.base.service.CommonExcepService;
import com.cmu.base.service.exception.CMUServiceException;
import com.cmu.base.service.exception.EchecSelectException;
import com.cmu.sigicmu.param.domaine.WebRacineEnum;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;


@Named("absenceExcep")
@javax.enterprise.context.SessionScoped
public class AbsenceExceptionnelleBean extends BaseCRUDBean{

    @EJB
   CommonExcepService srv;
    
    @EJB
    AbsenceExcepService absrv;
    
    public AbsenceExceptionnelleBean() {
        super(WebRacineEnum.RH, "absenceExceptionnelle");
    }

    @Override
    public BaseService getService() {
        return srv;
    }

    @Override
    public BaseDomaine getNewInstance() {
        return new AbsenceExceptionnelle();
    }

    @Override
    public boolean sansErreurAvantEnregistrement() throws EchecSelectException {
        AbsenceExceptionnelle m_obj = (AbsenceExceptionnelle) obj;
        BaseCheckErrorLib chk = new BaseCheckErrorLib(m_obj, srv);
        
//        if  (srv.existe(m_obj, "jour", m_obj.getJour())) {
//            JsfUtil.addErrorMessage("jour", "La date est déjà définie");
//            return false;
//        }

        return chk.sansErreur();
        
    }
    
    public String supprimer(){
        
         //Absence Excep;
        AbsenceExceptionnelle m_obj = (AbsenceExceptionnelle) obj;
        
        try {
            absrv.supprimer(m_obj);
        } catch (CMUServiceException ex) {
            Logger.getLogger(AbsenceExceptionnelleBean.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return "/RH/absenceExceptionnelleList.xhtml";
    }
}
