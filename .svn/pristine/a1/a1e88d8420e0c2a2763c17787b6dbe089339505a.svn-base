package com.cmu.sigicmu.param.service;

import com.cmu.base.domaine.BaseDomaine;
import com.cmu.base.domaine.BaseLib;
import com.cmu.base.service.BaseLibCodeService;
import com.cmu.base.service.exception.EchecSelectException;
import com.cmu.base.service.exception.EchecUniqueSelectException;
import com.cmu.sigicmu.param.domaine.TableValeur;
import com.cmu.sigicmu.param.domaine.TableValeurType;
import com.cmu.sigicmu.param.domaine.TableValeurTypeEnum;
import com.cmu.sigicmu.param.domaine.TypeLocaliteEnum;
import com.cmu.util.JUtil;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.faces.model.SelectItem;
import javax.persistence.Query;

import org.apache.log4j.Logger;

@Stateless
@LocalBean
public class TableValeurService extends BaseLibCodeService {

    private static final Logger log = Logger.getLogger(TableValeurService.class);
    
    @Override
    public boolean existeLib(BaseLib obj) throws EchecSelectException {
        TableValeur m_obj = (TableValeur) obj;
        //return existeLib2(m_obj, "tableValeurType", m_obj.getType().getId());
        return false;
    }
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<SelectItem> getTableValeurItemsWithId(String typeCode) throws EchecSelectException {
        String sql = "select new javax.faces.model.SelectItem(tv.id, tv.lib)"
                + " from TableValeur as tv, TableValeurType as tvt"
                + " where tvt.code = :typeCode"
                + "     and tv.type.id = tvt.id"
                + " order by tv.estParDefaut desc, tv.ordre, tv.lib";
        try {
            Query q = getEm().createQuery(sql);
            q.setParameter("typeCode", typeCode);
            return q.getResultList();
        } catch (Exception ex) {
            throw new EchecSelectException(ex);
        }
    }
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<SelectItem> getChampsIntervention() throws EchecSelectException {
        String sql = "select new javax.faces.model.SelectItem(tv, tv.lib)"
                + " from TableValeur as tv, TableValeurType as tvt"
                + " where tvt.code = :typeCode"
                + "     and tv.code in (:national,:region,:departement,:commune)"
                + "     and tv.type.id = tvt.id"
                + " order by tv.estParDefaut desc, tv.ordre, tv.lib";
        try {
            Query q = getEm().createQuery(sql);
            q.setParameter("typeCode", TableValeurTypeEnum.TypeLocalite.toString());
            q.setParameter("national", TypeLocaliteEnum.Nationale.toString());
            q.setParameter("region", TypeLocaliteEnum.Region.toString());
            q.setParameter("departement", TypeLocaliteEnum.Departement.toString());
            q.setParameter("commune", TypeLocaliteEnum.Commune.toString());
            return q.getResultList();
        } catch (Exception ex) {
            throw new EchecSelectException(ex);
        }
    }
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<SelectItem> getTableValeurItems(String typeCode) throws EchecSelectException {
        String sql = "select new javax.faces.model.SelectItem(tv, tv.lib)"
                + " from TableValeur as tv, TableValeurType as tvt"
                + " where tvt.code = :typeCode"
                + "     and tv.type.id = tvt.id"
                + " order by tv.estParDefaut desc, tv.ordre, tv.lib";
        try {
            Query q = getEm().createQuery(sql);
            q.setParameter("typeCode", typeCode);
            return q.getResultList();
        } catch (Exception ex) {
            throw new EchecSelectException(ex);
        }
    }
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<SelectItem> Initiatives(String typeCode) throws EchecSelectException {
        String sql = "select new javax.faces.model.SelectItem(tv.id, tv.lib)"
                + " from TableValeur as tv, TableValeurType as tvt"
                + " where tvt.code = :typeCode"
                + "     and tv.type.id = tvt.id"
                + " order by tv.estParDefaut desc, tv.ordre, tv.lib";
        try {
            Query q = getEm().createQuery(sql);
            q.setParameter("typeCode", typeCode);
            return q.getResultList();
        } catch (Exception ex) {
            throw new EchecSelectException(ex);
        }
    }
     @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<BaseDomaine> Initiative(String code) throws EchecSelectException {
        String sql = "select tv"
                + " from TableValeur as tv, TableValeurType as tvt"
                + " where tvt.code = :code"
                + " and tv.type.id = tvt.id"
                + " order by tv.estParDefaut desc, tv.ordre, tv.lib";
        try {
            Query q = getEm().createQuery(sql);
            q.setParameter("code", code);
            return q.getResultList();
        } catch (Exception ex) {
            throw new EchecSelectException(ex);
        }
    }
    
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<BaseDomaine> getTableValeur(int typeId) throws EchecSelectException {
        String sql = "select tv "
                + " from TableValeur as tv"
                + " where tv.type.id = :typeId"
                + " order by tv.estParDefaut desc, tv.ordre, tv.lib";
        try {
            Query q = getEm().createQuery(sql);
            q.setParameter("typeId", typeId);
            return q.getResultList();
        } catch (Exception ex) {
            throw new EchecSelectException(ex);
        }
    }
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public TableValeur getRef(TableValeurTypeEnum typeCode, String code) throws EchecSelectException {        
        JUtil.s("typeCode = "+ typeCode.toString() + " / code = "+ code);
        try {
            String sql = "select tv"
                    + " from TableValeurType tvt, TableValeur as tv"
                    + " where tv.type.id = tvt.id"
                    + "     and tvt.code = :type_code"
                    + "     and tv.code  = :code";
            Query q = getEm().createQuery(sql);
            q.setParameter("type_code", typeCode.toString());
            q.setParameter("code", code);
            return (TableValeur)q.getSingleResult();

        } catch (javax.persistence.NoResultException ex1) {
            log.fatal("getRef : Exception 1 [" + ex1 + "]", ex1);
            throw new EchecUniqueSelectException(ex1);
        } catch (Exception ex2) {
            log.fatal("getRef : Exception 2 [" + ex2 + "]", ex2);
            throw new EchecSelectException(ex2);
        }
    }
    
    public TableValeur getRef(Integer typeId, String code) throws EchecSelectException {
        
        try {
            String sql = "select o from TableValeur as o where o.type.id = :valeur1 and o.code = :valeur2";

            Query q = getEm().createQuery(sql);
            q.setParameter("valeur1", typeId);
            q.setParameter("valeur2", code);
            return (TableValeur)q.getSingleResult();

        } catch (javax.persistence.NoResultException ex1) {
            log.fatal("getRef : Exception 1 [" + ex1 + "]", ex1);
            throw new EchecUniqueSelectException(ex1);
        } catch (Exception ex2) {
            log.fatal("getRef : Exception 2 [" + ex2 + "]", ex2);
            throw new EchecSelectException(ex2);
        }
    }
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<BaseDomaine> tableValeurInitiative(String typeCode) throws EchecSelectException {
        String sql = "select tv "
                + " from TableValeur as tv"
                + " where tv.type.code = :typeCode"
                + " order by tv.estParDefaut desc, tv.ordre, tv.lib";
        try {
            Query q = getEm().createQuery(sql);
            q.setParameter("typeCode", typeCode);
            return q.getResultList();
        } catch (Exception ex) {
            throw new EchecSelectException(ex);
        }
    }
    
    // get type id
    public TableValeurType tableValeurType(String typeCode)  throws EchecSelectException{
        String sql = "select tvt "
                + " from TableValeurType as tvt"
                + " where tvt.code = :typeCode";
        try {
            Query q = getEm().createQuery(sql);
            q.setParameter("typeCode", typeCode);
            return (TableValeurType)q.getSingleResult();
        } catch (Exception ex) {
            throw new EchecSelectException(ex);
        }
    }
    
    public List<TableValeur> getTypeLocalite() throws EchecSelectException{
        String sql = "select tv "
                + " from TableValeur as tv"
                + " where tv.type.code = :typeCode"
                + " order by tv.estParDefaut desc, tv.ordre, tv.lib";
        try {
            Query q = getEm().createQuery(sql);
            q.setParameter("typeCode", "002");
            return q.getResultList();
        } catch (Exception ex) {
            throw new EchecSelectException(ex);
        }
    }
    
    
    public BaseDomaine getTableValeurInitiative(int id) throws EchecSelectException {
        String sql = "select tv from TableValeur as tv"
                + " where tv.id = :id";
        try {
            Query q = getEm().createQuery(sql);
            q.setParameter("id", id);
            return (BaseDomaine) q.getSingleResult();
        } catch (Exception ex) {
            throw new EchecSelectException(ex);
        }
    }
}
