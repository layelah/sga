package com.cmu.sigicmu.admin.service;

import com.cmu.base.service.BaseCodeService;
import com.cmu.base.service.BaseItemService;
import com.cmu.base.service.exception.EchecSelectException;
import com.cmu.sigicmu.admin.domaine.Agent;
import com.cmu.sigicmu.admin.domaine.Utilisateur;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.Query;

@Stateless
@LocalBean
public class AgentService extends BaseCodeService {

    @Inject
    BaseItemService itemServ;

    public boolean estSuperviseur(int agentId) {
        long nbAgent = 0;

        try {
            String sql = "select count(o.id) from Agent as o "
                    + " where not (o.superviseur is null) and  (o.superviseur.id = :agentId)";

            Query q = getEm().createQuery(sql);
            q.setParameter("agentId", agentId);

            nbAgent = (long) q.getSingleResult();
        } catch (Exception ex) {
            log.error("AgentService.estSuperviseur:", ex);
        }

        return (0 < nbAgent);
    }
    
    public Utilisateur getUserParLogin(String email) throws EchecSelectException {

        try {
            String sql = "select a from Utilisateur as a "
                    + " where a.login = :mail";
                 

            Query q = getEm().createQuery(sql);
            q.setParameter("mail", email);
            return (Utilisateur) q.setMaxResults(1).getSingleResult();

        } catch (Exception ex2) {
            throw new EchecSelectException(ex2);
        }
    }

    public List getListAgent() throws EchecSelectException {
        return itemServ.getSelectItems(new Agent(), "nomComplet");
    }

    public List getListAgentById() throws EchecSelectException {
        return itemServ.getSelectItems(new Agent(), "nomComplet");
    }

    public List getListAgentByIdDeMonService(Agent agent) throws EchecSelectException {
        String sql;
        Query q;

        try {
            if (agent.getService() != null && !agent.estRH()) {
                sql = "SELECT new javax.faces.model.SelectItem(o, o.nomComplet)"
                        + " FROM Agent as o "
                        + " WHERE o.service.id = :serviceId"
                        + " ORDER BY o.nom, o.prenom";

                q = getEm().createQuery(sql);
                q.setParameter("serviceId", agent.getService().getId());

            }else if(agent.getService() != null && agent.estRH()) {

                sql = "SELECT new javax.faces.model.SelectItem(o, o.nomComplet)"
                        + " FROM Agent as o "
                       
                        + " ORDER BY o.prenom, o.nom";
                q = getEm().createQuery(sql);
                //q.setParameter("agentId", agent.getId());
            } else {

                sql = "SELECT new javax.faces.model.SelectItem(o, o.nomComplet)"
                        + " FROM Agent as o "
                        + " WHERE o.id = :agentId"
                        + " ORDER BY o.nom, o.prenom";
                q = getEm().createQuery(sql);
                q.setParameter("agentId", agent.getId());
            }

            return q.getResultList();

        } catch (Exception ex) {
            log.error("AgentService.estSuperviseur:", ex);
        }

        return null;
    }

    public List getListAgents(Integer entiteId) throws EchecSelectException {
        return itemServ.getSelectItemsFilter(new Agent(), "nomComplet", "entite", entiteId, "o.nom, o.prenom, o.matricule, o.dateNaissance, o.id");
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public boolean agentEstUnique(Agent obj) throws EchecSelectException {
        try {
            String sql = "select o from Agent as o "
                    + " where upper(o.prenom) = upper(:prenom) and upper(o.nom) = upper(:nom) and o.dateNaissance = :date_naissance and o.id <> :id";
            Query q = getEm().createQuery(sql);
            q.setParameter("id", obj.getId());
            q.setParameter("prenom", obj.getPrenom());
            q.setParameter("nom", obj.getNom());
            q.setParameter("date_naissance", obj.getDateNaissance());

            List l = q.getResultList();
            return ((l == null) || (l.isEmpty()));

        } catch (Exception ex2) {
            throw new EchecSelectException(ex2);
        }
    }
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public boolean cniEstUnique(Agent obj) throws EchecSelectException {
        try {
            String sql = "select o from Agent as o "
                    + " where o.cni = :cni and o.id <> :id";
            Query q = getEm().createQuery(sql);
            q.setParameter("id", obj.getId());
            q.setParameter("cni", obj.getCni());

            List l = q.getResultList();
            return ((l == null) || (l.isEmpty()));

        } catch (Exception ex2) {
            throw new EchecSelectException(ex2);
        }
    }
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public boolean matriculeEstUnique(Agent obj) throws EchecSelectException {
        try {
            String sql = "select o from Agent as o "
                    + " where o.matricule = :matricule and o.id <> :id";
            Query q = getEm().createQuery(sql);
            q.setParameter("id", obj.getId());
            q.setParameter("matricule", obj.getMatricule());
           
            List l = q.getResultList();
            return ((l == null) || (l.isEmpty()));

        } catch (Exception ex2) {
            throw new EchecSelectException(ex2);
        }
    }
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public boolean emailProEstUnique(Agent obj) throws EchecSelectException {
        try {
            String sql = "select o from Agent as o "
                    + " where upper(o.emailProfessionnel) = upper(:emailProfessionnel) and o.id <> :id";
            Query q = getEm().createQuery(sql);
            q.setParameter("id", obj.getId());
            q.setParameter("emailProfessionnel", obj.getEmailProfessionnel());
           
            List l = q.getResultList();
            return ((l == null) || (l.isEmpty()));

        } catch (Exception ex2) {
            throw new EchecSelectException(ex2);
        }
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List resultatRecherche(Integer entiteId, String nom) throws EchecSelectException {
        try {
            String sql = "select o from Agent as o join fetch o.entite";
            sql += " where (o.prenom like :nom or o.nom like :nom or o.surnom like :nom)";
            if (entiteId != null && 0 < entiteId) {
                sql += " and (o.entite.id = :entiteId)";
            }

            sql += " order by o.nom, o.prenom, o.dateNaissance, o.code";

            System.out.printf("existe: SQL = [" + sql + "]");

            Query q = getEm().createQuery(sql);
            q.setMaxResults(100);
            q.setParameter("nom", "%" + nom + "%");
            if (entiteId != null && 0 < entiteId) {
                q.setParameter("entiteId", entiteId);
            }

            return q.getResultList();

        } catch (Exception ex2) {
            throw new EchecSelectException(ex2);
        }
    }
}
