package com.cmu.base.bean;

import com.cmu.base.service.BaseService;
import com.cmu.base.service.exception.EchecSelectException;
import com.cmu.base.service.exception.CMUServiceException;
import com.cmu.sigicmu.param.domaine.WebRacineEnum;
import org.apache.log4j.Logger;

public abstract class BaseCRUDBean extends BaseCRUDSansSuppressionBean {

    public static final Logger log = Logger.getLogger(BaseCRUDBean.class);
 
    // Constructeurs
    ////////////////////////////////////////////////////////////////////////
    
    public BaseCRUDBean() {
    }

    public BaseCRUDBean(WebRacineEnum repertoire, String racine) {
        super(repertoire, racine);
    }

    public BaseCRUDBean(WebRacineEnum repertoire, String racine, String defaultPage) {
        super(repertoire, racine, defaultPage);
    }

    
    // Afficher la page de confirmation de la suppression pour l'objet en cours
    
    public String goSuppression() {
        previousPage = pageInfo();
        return pageSupression();
    }
    
    
    // Afficher la page de confirmation de la suppression pour l'objet d'ID passé en paramètre

    public String goSuppression(int id) {
        previousPage = pageList();
        try {
            loadObj(id);
        } catch (EchecSelectException ex) {
            JsfUtil.addExceptionMessage("Une erreur est survenue lors du chargement de l'entité", ex);
            return null;
         } catch (Exception ex1) {
            JsfUtil.addExceptionMessage(ex1);
            return null;
        }
        supprimer();
        return pageSupression();
    }

    
    // Suppression effective    
    public String supprimer() {

        log.info("supprimer: DEBUT ID = " + obj.getId());

        try {
            log.info("supprimer : TEST suppressionInterdite()");
            if (!controleAvantSuppression()) {
                log.debug("supprimer : Y A interdiction de supprimer");
                JsfUtil.addErrorMessage("La supression demandée n'est pas possible");
                return null;
            }

            BaseService srv = getService();
            srv.supprimer(obj);

        } catch (CMUServiceException ex1) {
            JsfUtil.addExceptionMessage("Suppression impossible, vérifier les dépendances", ex1);
            log.fatal("supprimer_2 : Exception [" + ex1 + "]", ex1);
            return null;
        } catch (Exception ex2) {
            JsfUtil.addExceptionMessage(ex2);
            log.fatal("supprimer_3 : Exception [" + ex2 + "]", ex2);
            return null;
        }

        JsfUtil.addSuccessMessage("Suppresion effectuée avec succès");

        return pageDefault(true);
    }
    

    // Méthodes associé à la suppression
    ////////////////////////////////////////////////////////////////////////
    
    protected boolean controleAvantSuppression() throws CMUServiceException {
        return true;
    }

    private String pageSupression() {
        return repertoire.getCheminComplet(racine, TypePageEnum.SUPPRESSION);
    }
}
