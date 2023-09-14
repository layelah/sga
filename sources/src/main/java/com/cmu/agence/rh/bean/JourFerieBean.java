package com.cmu.agence.rh.bean;

import com.cmu.agence.rh.domaine.JourFerie;
import com.cmu.base.bean.BaseCRUDBean;
import com.cmu.base.bean.BaseCheckErrorLib;
import com.cmu.base.bean.JsfUtil;
import com.cmu.base.domaine.BaseDomaine;
import com.cmu.base.service.BaseService;
import com.cmu.base.service.CommonLibService;
import com.cmu.base.service.exception.CMUServiceException;
import com.cmu.base.service.exception.EchecSelectException;
import com.cmu.sigicmu.admin.bean.AgentBean;
import com.cmu.sigicmu.param.domaine.WebRacineEnum;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;


@Named("ferie")
@javax.enterprise.context.SessionScoped
public class JourFerieBean extends BaseCRUDBean{

    @EJB
    CommonLibService srv;
    
    public JourFerieBean() {
        super(WebRacineEnum.RH, "jourFerie");
    }

    @Override
    public BaseService getService() {
        return srv;
    }

    @Override
    public BaseDomaine getNewInstance() {
        return new JourFerie();
    }

    @Override
    public boolean sansErreurAvantEnregistrement() throws EchecSelectException {
        JourFerie m_obj = (JourFerie) obj;
        BaseCheckErrorLib chk = new BaseCheckErrorLib(m_obj, srv);

        if  (srv.existe(m_obj, "jour", m_obj.getJour())) {
            JsfUtil.addErrorMessage("jour", "La date est déjà définie");
            return false;
        }
        
        return chk.sansErreur();
    }
    
    public String supprimer(){
        
         JourFerie m_obj = (JourFerie) obj;
        
        System.out.println("Avant suppression");
        
        try {
            
            srv.supprimer(m_obj);
        } catch (CMUServiceException ex) {   
            Logger.getLogger(JourFerieBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         System.out.println("Après suppression");
       
        return "/RH/jourFerieList.xhtml";
    }
}
