package com.cmu.base.service;

import com.cmu.base.domaine.BaseDomaine;
import com.cmu.base.service.exception.EchecActualiserException;
import com.cmu.base.service.exception.EchecDetacherException;
import com.cmu.base.service.exception.EchecNettoyerException;
import com.cmu.base.service.exception.EchecSelectException;
import com.cmu.base.service.exception.CMUServiceException;
import com.cmu.sigicmu.bureau.service.SessionService;
import com.cmu.util.CheckType;
import java.util.List;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.EJB;
import org.apache.log4j.Logger;

public abstract class BaseService  extends CmuJPAService {

    public static final Logger log = Logger.getLogger(BaseService.class);
    
    @EJB
    private SessionService sess;
    
    // CONSTRUCTEURS
    //////////////////////////////////////////////////////////////
    public BaseService() {
    }
   
    
    
    // METHODES A COMPRENDRE ET A FINALISER
    //////////////////////////////////////////////////////////////
    public void actualiser(BaseDomaine obj) throws CMUServiceException {
        if (log.isInfoEnabled()) {
            log.info("actuliser class: " + obj.getClass() + ", id = " + obj.getId());
        }
        try {
            if (contains(obj)) {
                refresh(obj);
            } else {
                obj = getEntite(obj, obj.getId());
            }
            
        } catch (Exception ex) {
            throw new EchecActualiserException(ex);
        }
    }

    public void nettoyer(BaseDomaine obj) throws CMUServiceException {
        if (log.isInfoEnabled()) {
            log.info("nettoyer class: " + obj.getClass() + ", id = " + obj.getId());
        }
        try {
            if (obj != null && obj.getId() != 0) {
                //em.(obj);
            }
        } catch (Exception ex) {
            throw new EchecNettoyerException(ex);
        }
    }

    public void detacher(BaseDomaine obj) throws CMUServiceException {
        if (log.isInfoEnabled()) {
            log.info("detacher class: " + obj.getClass() + ", id = " + obj.getId());
        }
        try {
            if (obj != null && obj.getId() != 0) {
                detach(obj);
            }
        } catch (Exception ex) {
            throw new EchecDetacherException(ex);
        }
    }

    // METHODES DE LECTURE - particulière
    //////////////////////////////////////////////////////////////
    

    // METHODES DE LECTURE - d'une liste d'entités
  
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<BaseDomaine> getAll(BaseDomaine obj) throws EchecSelectException {
        return getResults(obj, null);
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    private List<BaseDomaine> getResults(BaseDomaine obj,  String colonnesDeTri) throws EchecSelectException {
        String sql = "select o from " + obj.getClass().getSimpleName() + " as o"
                + (CheckType.estVide(colonnesDeTri) ? "" : " order by " + colonnesDeTri);
        return getResults(sql);
    }
   
    
    public List<BaseDomaine> tousLesEntitesAvecTri(BaseDomaine obj, String sortedColumn) throws EchecSelectException {
        return getResults(obj, sortedColumn);
    }

    
   
    // METHODES DE LECTURE - d'une liste d'entités selective
    //////////////////////////////////////////////////////////////
    public List<BaseDomaine> lesEntitesAssociees(BaseDomaine laClasse, String attribut, Integer id) throws EchecSelectException {
        return lesEntitesParticulieres(laClasse, attribut + ".id", id, null);
    }

    public List<BaseDomaine> lesEntitesParticulieres(BaseDomaine laClasse, String attribut, Integer id) throws EchecSelectException {
        return lesEntitesParticulieres(laClasse, attribut, id, null);
    }

    
    // METHODES DE LECTURE - Fonctions scalaire
    //////////////////////////////////////////////////////////////
    //@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public int getNbEntites(BaseDomaine obj) throws EchecSelectException {
        String sql = " where 1 = 1 "
                + (obj.avecProprieteEstSupprime() ? " and (o.estSupprime = false) " : "");
        return getNbEntites(obj, sql);
    }

}
