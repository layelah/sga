package com.cmu.base.service;

import com.cmu.base.domaine.BaseDomaine;
import com.cmu.base.service.exception.*;
import com.cmu.sigicmu.bureau.service.SessionService;
import com.cmu.util.CheckType;
import com.cmu.util.JUtil;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.log4j.Logger;

public class CmuJPAService implements Serializable{
    
    private static final Logger log = Logger.getLogger(CmuJPAService.class);
    
    // ATTRIBUTS
    //////////////////////////////////////////////////////////////
    @PersistenceContext(unitName = "sigicmuPU")
    private EntityManager entManag = null;
    
    @Inject
    private SessionService sess;
   
    public EntityManager getEm() {
        return entManag;
    }
    
    public Connection getJDBCConnection() {
        Map map = entManag.getProperties();
        String driver = (String)map.get("javax.persistence.jdbc.driver");
        String url = (String)map.get("javax.persistence.jdbc.url");
        String user = (String)map.get("javax.persistence.jdbc.user");
        String password = (String)map.get("javax.persistence.jdbc.password");
        try {
            Class.forName(driver);
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(CmuJPAService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CmuJPAService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    // CONSTRCUTEURS
    //////////////////////////////////////////////////////////////
    public CmuJPAService() {
    }

    
    // METHODES DE LECTURE - Références sur un objet
    //////////////////////////////////////////////////////////////
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public BaseDomaine getReference(BaseDomaine obj, Integer id) throws EchecGetRefenceException {
        if (obj == null) {
            log.warn("getReference: obj is null");
            return null;
            
        } else if (BaseDomaine.idIsNull(id)) {
            log.warn("getReference: id is null");
            return null;
        }
        
        try {
            return entManag.getReference(obj.getClass(), id);
        } catch (Exception ex) {
            log.error("getReference class: [" + obj.getClass() + "], erreur lors la récupération de la référence", ex);
            throw new EchecGetRefenceException(ex);
        }
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public BaseDomaine getReference(BaseDomaine obj) throws CMUServiceException {
        if (obj == null) {
            log.warn("getReference: obj is null");
            return null;
        }
        return getReference(obj, obj.getId());
    }
    
    // METHODE PROTEGEES DE BASE DE L'Entity Manager
    // PERSIST, REMOVE, REFRESH, DETACH et MERGE
    //////////////////////////////////////////////////////////////
    
    protected BaseDomaine merge(BaseDomaine obj) {
        if (obj == null) {
            log.warn("merge: obj is null");
        } else {
            if (log.isInfoEnabled()) {
                log.info("merge class: " + obj.getClass() + ", id = " + obj.getId());
            }
            
            return entManag.merge(obj);            
        }        
        
        return null;
    }
     
    protected void persist(BaseDomaine obj) {
        if (obj == null) {
            log.warn("persist: obj is null");
            
        } else {
            if (log.isInfoEnabled()) {
                log.info("persist class: " + obj.getClass() + ", id = " + obj.getId());
            }            
            entManag.persist(obj);            
        }        
    }
    
    protected void remove(BaseDomaine obj) {
        if (obj == null) {
            log.warn("remove: obj is null");
            
        } else {
            if (log.isInfoEnabled()) {
                log.info("remove class: " + obj.getClass() + ", id = " + obj.getId());
            }
            
            entManag.remove(obj);            
        }        
    }
   
    protected void flush() {
        if (log.isInfoEnabled()) {
            log.info("flush: Mise à jour de la base de données");
        }
        entManag.flush();
    }
    
    protected void refresh(BaseDomaine obj) {
        if (log.isInfoEnabled()) {
            log.info("refresh: Rechargement de l'entité depuis la base de données");
        }
        entManag.refresh(obj);
    }
    
    protected void refresh(BaseDomaine obj, LockModeType lockMode) {
        if (obj == null) {
            log.warn("refresh: obj is null");
        } else {
            if (log.isInfoEnabled()) {
                log.info("refresh class: " + obj.getClass() + ", id = " + obj.getId());
            }
            
            entManag.refresh(this, lockMode);            
        }        
    }
    
    protected boolean contains(BaseDomaine obj) {
        if (obj == null) {
            log.warn("refresh: obj is null");
            return false;
        }
        
        return entManag.contains(obj);                    
    }
    
    protected void detach(BaseDomaine obj) {
        if (obj == null) {
            log.warn("detach: obj is null");
        } else {
            if (log.isInfoEnabled()) {
                log.info("detach class: " + obj.getClass() + ", id = " + obj.getId());
            }
            
            entManag.detach(obj);            
        }        
    }    

    // METHODES PUBLIQUES PERSISTANTES - Modification des données
    //////////////////////////////////////////////////////////////
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public BaseDomaine save(BaseDomaine obj) throws CMUServiceException {

        BaseDomaine m_obj = null;
        
        if (obj == null) {
            log.info("save class: obj is null");
            return null;
            
        } else  {
            if (BaseDomaine.idIsNull(obj.getId())) {
                m_obj = insert(obj);
            } else {
                m_obj = update(obj);
            }
        }
        return m_obj;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    protected BaseDomaine insert(BaseDomaine obj) throws EchecInsertException {
        try {
            if (sess == null ) {
                log.warn("insert class: session IS NULL ");
            } else {
                obj.setInsertUserId(sess.srvCurrentUserId());
            }
           
            persist(obj);            
                        
        } catch (Exception ex) {
            log.error("insert class: [" + obj.getClass() + "], erreur lors de l'insertion", ex);
            throw new EchecInsertException(ex);
        }
        return obj;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    protected BaseDomaine update(BaseDomaine obj) throws EchecUpdateException {
        BaseDomaine m_obj = null;

        try {
            if (sess == null ) {
                log.warn("update class: session IS NULL ");
            } else {
                obj.setUpdateUserId(sess.srvCurrentUserId());
            }
            m_obj = merge(obj);
            
            
        } catch (Exception ex) {
            log.error("update class: [" + obj.getClass() + "], erreur lors de la mise à jour", ex);
            throw new EchecUpdateException(ex);
        }
        return m_obj;
    }
     
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void supprimer(BaseDomaine obj) throws CMUServiceException {
        try {
            if (!this.contains(obj)) {
                obj = this.merge(obj);
            }
            remove(obj);
        } catch (Exception ex) {
            log.error("supprimer class: [" + obj.getClass() + "], erreur lors de la suppression", ex);
            throw new EchecSupprimerException(ex);
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public BaseDomaine supprimer(BaseDomaine obj, Integer id) throws CMUServiceException {
        if (log.isInfoEnabled()) {
            log.info("supprimer class: " + obj.getClass() + ", id = " + id);
        }
        try {
            obj = getReference(obj, id);
            remove(obj);            
        } catch (Exception ex) {
            log.error("supprimer class: [" + obj.getClass() + "], erreur lors de la suppression", ex);
            throw new EchecSupprimerException(ex);
        }
        return null;
    }
    
    
    // METHODES PUBLIQUES DE LECTURE
    //////////////////////////////////////////////////////////////

    public List<SelectItem> selectItems(String sql) throws EchecSelectException {
        return (List<SelectItem>)getResults(sql);
    }
    
    public List<SelectItem> selectItemsWithDefault(String sql) throws EchecSelectException {
        List<SelectItem> l =  selectItems(sql);
        l.add(new SelectItem(null, "<AUCUN(E)>"));
        
        return l;
    }
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public int getNbEntites(BaseDomaine obj, String suiteSQL) throws EchecSelectException {
        String sql = "";
        try {
            sql = "select count(o) from " + obj.getNomClasse()+ " as o " + suiteSQL;
            return getSingleIntValue(sql);
        
        } catch (Exception ex) {
            log.error("getNbEntites: erreur avec la requête ["+ sql +"]", ex);
            throw new EchecSelectException(ex);
        }
    }
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public int getNbLignes(BaseDomaine obj, String propName, Object propValeur) throws EchecSelectException {
        String sql = "";
        try {
            sql = "select count(o.id) from " + obj.getNomClasse() + " as o where o." + propName + " = :propValeur";
            Query q = entManag.createQuery(sql);
            q.setParameter("propValeur", propValeur);
            return ((Long) entManag .createQuery(sql).getSingleResult()).intValue();
            
        } catch (Exception ex) {
            log.error("getNbLignes: erreur avec la requête ["+ sql +"]", ex);
            throw new EchecSelectException(ex);
        }
    }    

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public int getMax(BaseDomaine obj) throws EchecSelectException {
        String sql = "";
        int lMax = 0;
        try {
            sql = "select max(o.id) from " + obj.getNomClasse() +" as o";
            lMax = getSingleIntValue(sql);
            
        } catch (Exception ex) {
            log.error("getMax: erreur avec la requête ["+ sql +"]", ex);
            throw new EchecSelectException(ex);
        }
        return lMax;
    }
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public int getSingleIntValue(String sql) throws EchecSelectException {
        try {
            return ((Long) entManag.createQuery(sql).getSingleResult()).intValue();
        } catch (Exception ex) {
            log.error("getSingleIntValue: erreur avec la requête ["+ sql +"]", ex);
            throw new EchecSelectException(ex);
        }
    }

    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public boolean existe(BaseDomaine obj, String attribut, Object valeur) throws EchecSelectException {
        String sql = "";
        try {
            sql = "select o "
                + " from " + obj.getNomClasse() + " as o "
                + " where o." + attribut + " = :valeur and o.id <> :id";

            Query q = entManag.createQuery(sql);
            q.setParameter("id", obj.getId());
            q.setParameter("valeur", valeur);

            List l = q.getResultList();
            return ((l != null) && (!l.isEmpty()));

        } catch (Exception ex) {
            log.error("existe: erreur avec la requête ["+ sql +"]", ex);
            throw new EchecSelectException(ex);
        }
    }
    
    public boolean existe(BaseDomaine obj, String attribut1, Object valeur1, String attribut2, Object valeur2) throws EchecSelectException {
        String sql = "";
        try {
            sql = "select o from " + obj.getNomClasse() + " as o "
                    + " where o." + attribut1 + " = :valeur1 "
                    + "     and o." + attribut2 + " = :valeur2 "
                    + "     and o.id <> :id";

            Query q = entManag.createQuery(sql);
            q.setParameter("id", obj.getId());
            q.setParameter("valeur1", valeur1);
            q.setParameter("valeur2", valeur2);

            List l = q.getResultList();
            return ((l != null) && (!l.isEmpty()));

        } catch (Exception ex) {
            log.error("existe: erreur avec la requête ["+ sql +"]", ex);
            throw new EchecSelectException(ex);
        }
    }
    public boolean existe(BaseDomaine obj, String attribut1, Object valeur1, String attribut2, Object valeur2, String attribut3, Object valeur3, String attribut4, Object valeur4) throws EchecSelectException {
        String sql = "";
        try {
            sql = "select o from " + obj.getNomClasse() + " as o "
            + " where o." + attribut1 + " = :valeur1 "
                    + " and o." + attribut2 + " = :valeur2 "
                    + " and o." + attribut3 + " = :valeur3 "
                    + " and o." + attribut4 + " = :valeur4 "
                    + " and o.id <> :id";

            Query q = entManag.createQuery(sql);
            q.setParameter("id", obj.getId());
            q.setParameter("valeur1", valeur1);
            q.setParameter("valeur2", valeur2);
            q.setParameter("valeur3", valeur3);
            q.setParameter("valeur4", valeur4);

            List l = q.getResultList();
            return ((l != null) && (!l.isEmpty()));

        } catch (Exception ex) {
            log.error("existe: erreur avec la requête ["+ sql +"]", ex);
            throw new EchecSelectException(ex);
        }
    }       

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public boolean existe(BaseDomaine obj, String propName, Object propValue, String refName, Integer refId) throws EchecSelectException {
        String sql = "";
        try {
            sql = "select o from " + obj.getNomClasse() + " as o "
                    + " where o." + propName + " = :propValue "
                    + "     and o." + refName + ".id = :refId "
                    + "     and o.id <> :id";

            Query q = entManag.createQuery(sql);
            q.setParameter("id", obj.getId());
            q.setParameter("propValue", propValue);
            q.setParameter("refId", refId);

            List l = q.getResultList();
            return ((l != null) && (!l.isEmpty()));

       } catch (Exception ex) {
            log.error("existe: erreur avec la requête ["+ sql +"]", ex);
            throw new EchecSelectException(ex);
        }
    }
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List getResults(String sql) throws EchecSelectException {
        try {
            Query q = entManag.createQuery(sql);
            return q.getResultList();
        } catch (Exception ex) {
            log.error("getResults: erreur avec la requête ["+ sql +"]", ex);
            throw new EchecSelectException(ex);
        }
    }
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List getResults(String sql, int maxRow) throws EchecSelectException {
        try {
            Query q = entManag.createQuery(sql);
            q.setMaxResults(maxRow);
            return q.getResultList();
        } catch (Exception ex) {
            log.error("getResults: erreur avec la requête ["+ sql +"]", ex);
            throw new EchecSelectException(ex);
        }
    }
    
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<BaseDomaine> lesEntitesParticulieres(BaseDomaine laClasse, String propNom, Object propValeur, String sortedColum) throws EchecSelectException {
        try {
            String sql = "select o from " + laClasse.getClass().getSimpleName() + " as o where o." + propNom + " = :propValeur"
                    + (CheckType.estVide(sortedColum) ? "" : " order by " + sortedColum);
            Query q = entManag.createQuery(sql);
            q.setParameter("propValeur", propValeur);
            return q.getResultList();

        } catch (javax.persistence.NoResultException ex1) {
            return null;
        } catch (Exception ex2) {
            throw new EchecSelectException(ex2);
        }
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Integer> getIdResults(String sql) throws EchecSelectException {
        try {
            Query q = entManag.createQuery(sql);
            return q.getResultList();
        } catch (javax.persistence.NoResultException ex1) {
            return null;
        } catch (Exception ex2) {
            log.fatal("getIdResults : Exception [" + ex2 + "]", ex2);
            throw new EchecSelectException(ex2);
        }
    }

    public List<Integer> lesIdFiltrees(BaseDomaine laClasse, String attribut, String filtre, Integer id) throws EchecSelectException {
        try {
            String sql = "select o." + attribut + ".id from " + laClasse.getClass().getSimpleName() + " as o where o." + filtre + ".id = :id";
            Query q = entManag.createQuery(sql);
            q.setParameter("id", id);
            return q.getResultList();

        } catch (javax.persistence.NoResultException ex1) {
            return null;
        } catch (Exception ex2) {
            log.fatal("lesIdFiltrees : Exception [" + ex2 + "]", ex2);
            throw new EchecSelectException(ex2);
        }
    }

    public String listId(String sql) throws EchecSelectException {
        String str = "(0)";
        List<Integer> result;

        try {
            Query q = entManag.createQuery(sql);
            result = q.getResultList();
            str = JUtil.listIdToStrId(result);
        } catch (Exception ex) {
            throw new EchecSelectException(ex);
        }

        return str;
    }
    
     // METHODES d'exploitation de données sur des tables relation
    public void deleteFiltered(BaseDomaine obj, String colFilteredName, Integer id) throws EchecSupprimerException {
        try {
            String sql = "delete from " + obj.getClass().getSimpleName() + " where " + colFilteredName + ".id = " + id;
            Query q = entManag.createQuery(sql);
            q.executeUpdate();
        } catch (Exception ex2) {
            throw new EchecSupprimerException(ex2);
        }
    }

    
    // GESTION DU CACHE
    //////////////////////////////////////////////////////////////////////////////////////
     // Gestion de la cache
    /*
    public void viderDuCache(BaseDomaine obj, Integer id) {
        Cache cache = entManag.getEntityManagerFactory().getCache();
        if (cache != null && obj != null && !BaseDomaine.idIsNull(id) && cache.contains(obj.getClass(), id)) {
            cache.evict(obj.getClass(), id);
        }
    }

    public void viderDuCache(BaseDomaine obj) {
        Cache cache = entManag.getEntityManagerFactory().getCache();
        cache.evict(obj.getClass());
    }

    public void viderToutLeCache() {
        Cache cache = entManag.getEntityManagerFactory().getCache();
        cache.evictAll();
    }*/
    
    // METHODES DE LECTURE - d'une valeur unique
    //////////////////////////////////////////////////////////////
    ////@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public BaseDomaine uneEntiteLiee(BaseDomaine obj, String attribut1, Integer valeur1, String attribut2, Integer valeur2) throws EchecSelectException {
        try {
            String sql = "select o from " + obj.getNomClasse() + " as o where o." + attribut1 + ".id = :valeur1 and o." + attribut2 + ".id = :valeur2";

            Query q = entManag.createQuery(sql);
            q.setParameter("valeur1", valeur1);
            q.setParameter("valeur2", valeur2);
            return (BaseDomaine) q.getSingleResult();

        } catch (javax.persistence.NoResultException ex1) {
            throw new EchecUniqueSelectException(ex1);
        } catch (Exception ex2) {
            log.fatal("uneEntiteLiee : Exception [" + ex2 + "]", ex2);
            throw new EchecSelectException(ex2);
        }
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public BaseDomaine uneEntiteFiltree(BaseDomaine obj, String attribut, Object valeur) throws EchecSelectException {
        try {
            String sql = "select o from " + obj.getNomClasse() + " as o where o." + attribut + " = :valeur";
            Query q = entManag.createQuery(sql);
            q.setParameter("valeur", valeur);
    
            return (BaseDomaine) q.getSingleResult();

        } catch (javax.persistence.NoResultException ex1) {
            throw new EchecUniqueSelectException(ex1);
        } catch (Exception ex2) {
            throw new EchecSelectException(ex2);
        }
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public BaseDomaine getEntite(BaseDomaine obj, Integer id) throws EchecSelectException {
        BaseDomaine m_obj = null;

        if (BaseDomaine.idIsNull(id)) {
            return null;
        }

        try {
            m_obj = entManag.find(obj.getClass(), id);            
        } catch (Exception ex) {
            throw new EchecSelectException(ex);
        }

        return m_obj;
    }
    
    
    /*
    public Utilisateur getMonUtilisateur() throws EchecGetRefenceException {
        try {
            return em.find((new Utilisateur()).getClass(), 0);
        } catch (Exception ex) {
            throw new EchecGetRefenceException(ex);
        }
    }
    
    
    ////@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public BaseDomaine getParLogin(Object valeur) throws EchecSelectException {
        try {
            String sql = "select o from " + (new Utilisateur()).getNomClasse() + " as o where o.login = :valeur";
            System.out.println("SQL : "+ sql);
            Query q = em.createQuery(sql);
            q.setParameter("valeur", valeur);
    
            return (BaseDomaine) q.getSingleResult();

        } catch (javax.persistence.NoResultException ex1) {
            throw new EchecUniqueSelectException(ex1);
        } catch (Exception ex2) {
            throw new EchecSelectException(ex2);
        }
    }
    
     public BaseDomaine unUtilisateurFiltree(BaseDomaine obj, String attribut, Object valeur) throws EchecSelectException {
        try {
            String sql = "select o from " + obj.getNomClasse() + " as o where o." + attribut + " = :valeur";
            System.out.println("SQL : "+ sql);
            Query q = em.createQuery(sql);
            q.setParameter("valeur", valeur);
    
            return (BaseDomaine) q.getSingleResult();

        } catch (javax.persistence.NoResultException ex1) {
            throw new EchecUniqueSelectException(ex1);
        } catch (Exception ex2) {
            throw new EchecSelectException(ex2);
        }
    }
    */
}
