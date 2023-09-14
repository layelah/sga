package com.cmu.sigicmu.admin.service;

import com.cmu.base.domaine.BaseDomaine;
import com.cmu.base.service.BaseItemService;
import com.cmu.base.service.BaseService;
import com.cmu.base.service.exception.EchecSelectException;
import com.cmu.base.service.exception.CMUServiceException;
import com.cmu.sigicmu.admin.domaine.Agent;
import com.cmu.sigicmu.admin.domaine.Utilisateur;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

@Stateless
@LocalBean
public class UtilisateurService extends BaseService {
    
    @Inject
    BaseItemService itemServ;
    
    public List getListUserByLogin() throws EchecSelectException {
        return itemServ.getSelectItems(new Utilisateur(), "login", "nomComplet");
    }
    
    public List getListUserById() throws EchecSelectException {
        return itemServ.getSelectItems(new Utilisateur(), "id", "nomComplet");
    }
    
    public List<SelectItem> getUtilisateurAvecEntite() throws EchecSelectException {
        String sql = "select new javax.faces.model.SelectItem(o.id, concat(concat(o.entite.code, ' - '), o.nomComplet))  "
                + " from Utilisateur as o "
                + " where o.validation.etatCode = :etatCode"    
                + " order by o.nom, o.prenom";
        return itemServ.selectItems(sql);
    }
    
    public String getListUserSubordonnes(Integer agentId) throws EchecSelectException {
        String sql = "select o.id from Utilisateur o where o.agent.agentSuperieur.id = "+ agentId;
        return listId(sql);
    }
    
    public boolean existeLogin(Utilisateur obj) throws EchecSelectException {
        return existe(obj, "login", obj.getLogin());
    }
      
    public boolean estValiditeMdp(String mdp) {
        
        return (8 <= mdp.length());
    }

    public void supprimer(BaseDomaine obj) throws CMUServiceException {
        Utilisateur m_obj = (Utilisateur) obj;
        Agent agent = m_obj.getAgent();
        agent.setUtilisateur(null);
        agent = (Agent)save(agent);
        m_obj.setAgent(null);
        supprimer(m_obj);
    }
}
