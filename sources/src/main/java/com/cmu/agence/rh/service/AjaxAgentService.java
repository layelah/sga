package com.cmu.agence.rh.service;

import com.cmu.base.service.BaseService;
import com.cmu.base.service.exception.EchecSelectException;
import com.cmu.sigicmu.admin.domaine.Entite;
import com.cmu.sigicmu.admin.domaine.Service;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.faces.model.SelectItem;
import javax.persistence.Query;

@Stateless
@LocalBean
public class AjaxAgentService extends BaseService {
    public List<SelectItem> getServices(Entite en) throws EchecSelectException {
        String sql = "select s from Service as s"
                + " where s.serviceRattachement.id = :entiteId ";
        Query q = getEm().createQuery(sql);
        q.setParameter("entiteId", en.getId());
        return  q.getResultList();
        
    }
    
    public List<SelectItem> getAgents(Service se) throws EchecSelectException {
        String sql = "select a from Agent as a"
                + " where a.service.id = :serviceId ";
        Query q = getEm().createQuery(sql);
        q.setParameter("serviceId", se.getId());
        return  q.getResultList();
        
    }
}
