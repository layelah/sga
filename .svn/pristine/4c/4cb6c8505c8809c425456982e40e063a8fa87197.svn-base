package com.cmu.base.bean;

import com.cmu.base.service.BaseService;
import com.cmu.base.service.exception.EchecSelectException;
import com.cmu.base.service.exception.CMUServiceException;
import com.cmu.sigicmu.param.domaine.WebRacineEnum;
import com.cmu.util.JUtil;
import org.apache.log4j.Logger;

public abstract class BaseCRUDSansSuppressionBean extends BaseListInfoBean {

    private static final Logger log = Logger.getLogger(BaseCRUDSansSuppressionBean.class);

    // Constructeurs
    ////////////////////////////////////////////////////////////////////////
    public BaseCRUDSansSuppressionBean() {
    }

    public BaseCRUDSansSuppressionBean(WebRacineEnum repertoire, String racine) {
        super(repertoire, racine);
    }

    public BaseCRUDSansSuppressionBean(WebRacineEnum repertoire, String racine, String defaultPage) {
        super(repertoire, racine, defaultPage);
    }

    protected boolean sansErreurAvantEnregistrement() throws CMUServiceException {return true;} 
    
    protected void traitementAvantEnregistrement() throws CMUServiceException {}    
    protected void traitementApresEnregistrement() throws CMUServiceException {}
    
    public void beforeDisplayPageNew() {}
    public void beforeDisplayPageModif() {}
    public void beforeDisplayPageDelete() {}
    
   
    
    // Afficher le formulaire d'insertion
    
    public void goNewObj() {
        previousPage = pageList();
        try {
            initObj();
        } catch (CMUServiceException ex) {
            log.fatal("goNew_1 : Exception [" + ex + "]", ex);
            JsfUtil.addExceptionMessage("Une erreur est survenue lors du contrôle de validité", ex);
         } catch (Exception ex1) {
            JsfUtil.addExceptionMessage(ex1);
            log.fatal("goNew_2 : Exception [" + ex1 + "]", ex1);
        }
        System.out.println("new obj -------------");
    }
    
    public String goNew() {
        previousPage = pageList();
        try {
            initObj();
        } catch (CMUServiceException ex) {
            log.fatal("goNew_1 : Exception [" + ex + "]", ex);
            JsfUtil.addExceptionMessage("Une erreur est survenue lors du contrôle de validité", ex);
            return null;
         } catch (Exception ex1) {
            JsfUtil.addExceptionMessage(ex1);
            log.fatal("goNew_2 : Exception [" + ex1 + "]", ex1);
            return null;
        }
        return pageForm();
    }
    
    
    // Afficher la page de modification sur l'objet courant    
    public String goModif() {
        previousPage = pageInfo();
        return pageForm();
    }

    
    // Affche la fiche personnel de l'agent
    
    // Afficher la page de modification sur l'objet d'id passé en paramètre
    public String goModif(int id) {
        previousPage = pageList();
        try {
            loadObj(id);
            traitementAvantModif();
        } catch (CMUServiceException ex) {
            log.fatal("goModif_1 : Exception [" + ex + "]", ex);
            JsfUtil.addExceptionMessage("Une erreur est survenue lors du contrôle de validité", ex);
            return null;
         } catch (Exception ex1) {
            JsfUtil.addExceptionMessage(ex1);
            log.fatal("goModif_2 : Exception [" + ex1 + "]", ex1);
            return null;
        }
        
        beforeDisplayPageModif();
        return pageForm();
    }
    
    protected void traitementAvantModif(){
        
    }
     
    
    // Enregistrement effectif (insertion ou modification)
    
    //@TransactionAttribute(TransactionAttributeType.REQUIRED)
    //@TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public String enregistrer(){
        
        
         
        try {
            traitementAvantEnregistrement();
            
            if (!sansErreurAvantEnregistrement()) {
                return null;
            }
      
            
        } catch (CMUServiceException ex) {
            log.fatal("enregistrer_1 : Exception [" + ex + "]", ex);
            JsfUtil.addErrorMessage("Une erreur est survenue lors du contrôle de validité");
            return null;
        
        } catch (Exception ex1) {
            JsfUtil.addExceptionMessage(ex1);
            log.fatal("enregistrer_2 : Exception [" + ex1 + "]", ex1);
            return null;
        }

        try {
            enregistrement();
            
        } catch (CMUServiceException ex) {
            JsfUtil.addExceptionMessage("Une erreur est survenue lors de l'enregistremenr à la base de données", ex);
            log.fatal("enregistrer_3 : Exception [" + ex + "]", ex);
            return null;

        } catch (Exception ex1) {
            JsfUtil.addExceptionMessage( ex1);
            log.fatal("enregistrer_4 : Exception [" + ex1 + "]", ex1);
            return null;
        }
        
        try {
            JUtil.s(11, "BaseCRUDSansSuppressionBean.enregistrer");                
            log.info("enregistrer : traitementApresEnregistrement");
            traitementApresEnregistrement();
            
        } catch (CMUServiceException ex) {
            JsfUtil.addExceptionMessage("Une erreur est survenue lors du traitement après l'enregistrement", ex);
            log.fatal("enregistrer_3 : Exception [" + ex + "]", ex);
            return null;

        } catch (Exception ex1) {
            JsfUtil.addExceptionMessage( ex1);
            log.fatal("enregistrer_4 : Exception [" + ex1 + "]", ex1);
            return null;
        }
                    
         JsfUtil.addSuccessMessage("Enregistrement effectué avec succès");
        log.info("enregsitrer: FIN ID = " + obj.getId());
        return goBack(true);
    }
    
    

    public void enregistrement() throws CMUServiceException {
        BaseService srv = (BaseService) getService();
        obj = srv.save(obj);
    }
    
    // Méthodes asociés aux actions
    /////////////////////////////////////////
    
    @Override
    protected void initObj() throws EchecSelectException {
        obj = getNewInstance();
    }

    public boolean getIsCreation() {
        return JUtil.estVide(obj);
    }
    
    public boolean getIsModification(){
        return (!getIsCreation());
    } 
}
