package com.cmu.sigicmu.admin.bean;

import com.cmu.base.bean.BaseCRUDBean;
import com.cmu.base.domaine.BaseDomaine;
import com.cmu.base.service.BaseService;
import com.cmu.base.service.exception.EchecSelectException;
import com.cmu.sigicmu.admin.domaine.Utilisateur;
import com.cmu.sigicmu.admin.service.UtilisateurService;
import com.cmu.sigicmu.param.domaine.WebRacineEnum;
import javax.inject.Inject;
import javax.inject.Named;
    
@Named("user")
@javax.enterprise.context.SessionScoped
public class UserBean extends BaseCRUDBean {

    @Inject
    UtilisateurService srv;
 
    private String newMdpBis;

    public UserBean() {
        super(WebRacineEnum.ADMINISTRATION, "user");
    }

    public String getNewMdpBis() {
        return newMdpBis;
    }

    public void setNewMdpBis(String newMdpBis) {
        this.newMdpBis = newMdpBis;
    }

    @Override
    public BaseService getService() {
        return srv;
    }

    @Override
    public BaseDomaine getNewInstance() {
        return new Utilisateur();        
    }

    @Override
    public boolean sansErreurAvantEnregistrement() throws EchecSelectException {
        Utilisateur m_obj = (Utilisateur) obj;
        UserCheckError chk = new UserCheckError(m_obj, srv);
        
        return chk.sansErreur(m_obj.getMotDePasse(), newMdpBis, !BaseDomaine.idIsNull(obj));
    }
    
    @Override
    public void beforeDisplayPageModif() {
        Utilisateur m_obj = (Utilisateur) obj;
        
        m_obj.setNewMdp(m_obj.getMotDePasse());
        m_obj.setNewMdpBis(m_obj.getMotDePasse());
    }
}
