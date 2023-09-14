package com.cmu.base.service;

import com.cmu.base.domaine.BaseEtatHisto;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;


@Stateless
@LocalBean
public class BaseEtatHistoService extends BaseService{
    
     public List getEtatHisto(BaseEtatHisto obj, int id) {        
        String sql = "select o from " + obj.getClass().getSimpleName() + " as o where o.objet.id = :id order by o.dateDebut";
    
        Query q = getEm().createQuery(sql);
        q.setParameter("id", id);
        return q.getResultList();
    }
}
