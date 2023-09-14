package com.cmu.base.bean;

import com.cmu.base.domaine.BaseDomaine;
import com.cmu.base.service.BaseService;
import com.cmu.base.service.CommonService;
import com.cmu.base.service.exception.EchecSelectException;
import com.cmu.base.service.exception.CMUServiceException;
import com.cmu.sigicmu.param.domaine.WebRacineEnum;
import com.cmu.util.JUtil;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.EJB;
import org.apache.log4j.Logger;

public abstract class BaseListInfoBean extends BaseBean {
 
    private static final Logger log = Logger.getLogger(BaseListInfoBean.class);

    @EJB
    CommonService srv;
    
    
    // Méthodes à redefinir
    //////////////////////////////////////////////////////////////////////
    
    
    public abstract BaseDomaine getNewInstance();
    private String motCle;
    private List listRechercheAvancee;
    private String rechResult;
    private boolean bAfficherResultatRecherche = false;
    
    
    public  BaseService getService() {
        return srv;
    }
        
    // Attributs
    //////////////////////////////////////////////////////////////////////
    
    protected BaseDomaine obj;
    protected boolean bRechercher = false;
    
    // Contructeurs
    //////////////////////////////////////////////////////////////////////
    
    public BaseListInfoBean() {
    }
     
    public BaseListInfoBean(WebRacineEnum repertoire, String racine) {
        super(repertoire, racine);
    }

    public BaseListInfoBean(WebRacineEnum repertoire, String racine, String defaultPage) {
        super(repertoire, racine, defaultPage);
    }
    
    
    // Getters & Setters
    //////////////////////////////////////////////////////////////////////
    
    public boolean enCreation() {
        return (JUtil.estVide(obj));
    }
    
    public boolean enModification() {
        return (! JUtil.estVide(obj));
    }
    
    
    public BaseDomaine getObj() {
        return obj;
    }

    public void setObj(BaseDomaine obj) {
        this.obj = obj;
    }
    
    public boolean isbRechercher() {
        return bRechercher;
    }

    public void setbRechercher(boolean bRechercher) {
        this.bRechercher = bRechercher;
    }
    
    public List resultatRechercheAvancee() throws EchecSelectException {
        return null;
    }
    
    public String goReultatRechercheAvancer() {
        JUtil.s("goReultatRechercheAvancer: ");    
        
        try {    
            listRechercheAvancee = resultatRechercheAvancee();            
            
        } catch (EchecSelectException ex) {
            JUtil.s("goReultatRechercheAvancer: erreur = "+ ex);    
            JsfUtil.addErrorMessage("Une erreur est survenue lors de la recherche");
            java.util.logging.Logger.getLogger(BaseListInfoBean.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        if (listRechercheAvancee == null || listRechercheAvancee.size() <= 0 ) {
            JUtil.s("goReultatRechercheAvancer: LIST (ZERO)");    
            JsfUtil.addSuccessMessage("Aucun résultat");
            return null;
            
        } else if (90  <  listRechercheAvancee.size()) {
            JUtil.s("goReultatRechercheAvancer: LIST (50) = "+ listRechercheAvancee.size());    
            JsfUtil.addSuccessMessage(listRechercheAvancee.size() + " (+ de 50) résultats(s) trouvé(s)! Vous devez l'affiner");
            return null;
        
        } else {
            bAfficherResultatRecherche = true;
            zoneAffichage = ModeAffichageEnum.LIST;       
            JUtil.s("goReultatRechercheAvancer: LIST SUCCESS = "+ listRechercheAvancee.size());    
            JsfUtil.addSuccessMessage(listRechercheAvancee.size() + " résultats(s) trouvé(s)");
            return goList();
        }
    }
    
    public List getList()  {
        List l = null;
        BaseService srv = getService();
        
        if (! bAfficherResultatRecherche){
            try {
                l = srv.getAll(getNewInstance());
            } catch (EchecSelectException ex) {
                JsfUtil.addErrorMessage("Une erreur est survenue lors de la lecture des données");
                return null;
            }
        
        } else if (bAfficherResultatRecherche) {
            l = listRechercheAvancee;    
        }
            
        return l;
    }
    
    public String rechercher() {
        bRechercher = true;
        return null;
    }
    
    // Méthodes associer à la recherche, à une liste ou à la page info
    ///////////////////////////////////////////////////////////////////
    
    protected String getColonnesDeTri() {
        return "o.id";
    }
    
    protected void loadObj(int id) throws CMUServiceException {
        BaseService srv = getService();
        obj = srv.getEntite(getNewInstance(), id);  
    }
    
    protected void initObj() throws EchecSelectException {
        BaseService srv = getService();
        obj = getNewInstance();
    }
    
    public String goInfo(int id) {     
        previousPage = pageList();
        
        try {
            loadObj(id);
        } catch (CMUServiceException ex) {
            log.fatal("goInfo_1 : Exception [" + ex + "]", ex);
            JsfUtil.addExceptionMessage("Une erreur est survenue lors de l'initialisaton de l'entité", ex);
            return null;
      
        }
        return pageInfo();
    }
    
    public String goInfo() {     
        previousPage = pageList();
        
        try {
            srv.actualiser(obj);
        } catch (CMUServiceException ex) {
            log.fatal("goInfo_1 : Exception [" + ex + "]", ex);
            JsfUtil.addExceptionMessage("Une erreur est survenue lors de l'initialisaton de l'entité", ex);
            return null;
      
        }
        return pageInfo();
    }
    
    // Méthodes intermédiaires
    ///////////////////////////////////////////////////////////////////////    
    
    public String goList() {
        previousPage = pageList();
        System.out.println("page liste = "+pageList());
        return pageList();
    }
    
    public String goRech() {
        bRechercher = false;
        previousPage = pageInfo();
        return pageRech();
    }
    
    public String goPageRechAvance() {
        zoneAffichage = ModeAffichageEnum.RECHERCHE;            
        return pageList();
    }
    
    // Accès direct aux pages
    /////////////////////////////////////////////////////////////////////
    
    public String pageList() {
        return repertoire.getCheminComplet(racine, TypePageEnum.LIST);
    }

    public String pageInfo() {
        return repertoire.getCheminComplet(racine, TypePageEnum.INFO);
    }

    public String pageForm() {
        return repertoire.getCheminComplet(racine, TypePageEnum.FORMULAIRE);
    }

    public String pageTraitement() {
        return repertoire.getCheminComplet(racine, TypePageEnum.TRAITEMENT);
    }

    
    public String pageRech() {
        return repertoire.getCheminComplet(racine, TypePageEnum.RECHERCHE);
    }
    
    public String page(String page) {
        return repertoire.getCheminCompletStr(racine, page);
    }
    
    public String page(TypePageEnum pageType) {
        return repertoire.getCheminComplet(racine, pageType);
    }
    
    
    // Méthodes relatives à la navigation
    //////////////////////////////////////////
  

    public String getMotCle() {
        return motCle;
    }

    public void setMotCle(String motCle) {
        this.motCle = motCle;
    }

    public String getRechResult() {
        return rechResult;
    }

    public void setRechResult(String rechResult) {
        this.rechResult = rechResult;
    }
}
