package com.cmu.agence.rh.bean;

import com.cmu.agence.rh.domaine.Absence;
import com.cmu.agence.rh.domaine.AbsenceEnum;
import com.cmu.agence.rh.domaine.DemandePermissionAbsence;
import com.cmu.agence.rh.domaine.EtatAbsenceEnum;
import com.cmu.agence.rh.domaine.EtatValidationEnum;
import com.cmu.agence.rh.domaine.NiveauValidationDemandeEnum;
import com.cmu.agence.rh.domaine.NoteValidationEnum;
import com.cmu.agence.rh.domaine.ParametreMail;
import com.cmu.agence.rh.domaine.SoldeConges;
import com.cmu.agence.rh.domaine.ValidationDemande;
import com.cmu.agence.rh.service.AbsenceService;
import com.cmu.agence.rh.service.NotifService;
import com.cmu.agence.rh.service.SoldeCongesService;
import com.cmu.base.bean.BaseCRUDBean;
import com.cmu.base.bean.JsfUtil;
import com.cmu.base.domaine.BaseDomaine;
import com.cmu.base.service.BaseService;
import com.cmu.base.service.CommonLibService;
import com.cmu.base.service.exception.CMUServiceException;
import com.cmu.base.service.exception.EchecSelectException;
import com.cmu.mail.JMailAccDemAgentSH;
import com.cmu.mail.JMailAccDemDG;
import com.cmu.mail.JMailAccDemRH;
import com.cmu.mail.JMailNotifDemAgent;
import com.cmu.mail.JMailRefDemAgentSH;
import com.cmu.mail.JMailRefDemDG;
import com.cmu.mail.JMailRefDemRH;

import com.cmu.sigicmu.admin.domaine.Agent;
import com.cmu.sigicmu.admin.service.AgentService;
import com.cmu.sigicmu.bureau.bean.AuthentificationBean;
import com.cmu.sigicmu.param.domaine.TableValeur;
import com.cmu.sigicmu.param.domaine.TableValeurTypeEnum;
import com.cmu.sigicmu.param.domaine.WebRacineEnum;
import com.cmu.sigicmu.param.service.TableValeurService;
import com.cmu.util.JIDate;
import com.cmu.util.JUtil;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Named("demPermisAbsence")
@javax.enterprise.context.SessionScoped
public class DemPermAbsBean extends BaseCRUDBean {

    String raison;
    TableValeur niveau;
    TableValeur etat;
    String remarque;

    @Inject
    AuthentificationBean session;

    @EJB
    CommonLibService srv;

    @EJB
    TableValeurService tvSrv;

     @EJB
    AbsenceService abSrv;

    @EJB
    AgentService agtSrv;
    
    @EJB
    SoldeCongesService scSrv;
    
    @EJB
    NotifService srvNotif;
    
    SoldeConges scAgent;
    


    public DemPermAbsBean() {
        //super(WebRacineEnum.RH, "absence");
        super(WebRacineEnum.RH, "demPermisAbsence");
    }

    @Override
    public BaseService getService() {
        return srv;
    }

    @Override
    public BaseDomaine getNewInstance() {
        return new DemandePermissionAbsence();
        //scAgent = scSrv.getSoldeConges(this.getAbsence().getAgent().getId());
    }

    public String initTableau() {

        return "/RH/fichePersonnelle.xhtml";
    }

    public List<DemandePermissionAbsence> getListDesDemandes() throws EchecSelectException {
//        if (agtSrv.estSuperviseur(getAuth().getUser().getAgent().getId()) && (!(getAuth().getUser().getAgent().estRH())) && (!(getAuth().getUser().getAgent().estDG()))) {
//            return abSrv.listDemAbsPourSH(getAuth().getUser().getAgent().getId());
//           
//        }
        return abSrv.listDemandes();
    }

    public List<DemandePermissionAbsence> getPlanning() throws EchecSelectException {
        return abSrv.listPlanningConges(getAuth().getUser().getAgent());
    }
    
    public List<Absence> getEtatAbsence() throws EchecSelectException {
        return abSrv.getEtatAbsence(getAuth().getUser().getAgent());
    }
    /*****************************/
    public List<Absence> getEtatAbsenceNew() throws EchecSelectException {
        return abSrv.getEtatAbsenceNew(getAuth().getUser().getAgent());
    }
    
    /**********************************/
    
    public List getListDemAbs() throws EchecSelectException {
        Agent agent = session.getUser().getAgent();
        return abSrv.listDemAbs(agent);
    }

    public List getListDemAbsPourSH() throws EchecSelectException {
        return abSrv.listDemAbsAValiderParSH(getAuth().getUser().getAgent().getId());
    }

    public ValidationDemande validationAFaire() throws EchecSelectException {
        DemandePermissionAbsence demAbs = (DemandePermissionAbsence) obj;
        return abSrv.validationAFaire(demAbs);
    }

    public List getListDesValidations() throws EchecSelectException {
        DemandePermissionAbsence dem = (DemandePermissionAbsence) obj;
        return abSrv.listValidationEffectives(dem);
    }
    
    public List<DemandePermissionAbsence> getListDemAbsAValider() throws EchecSelectException {
        
      

        if (agtSrv.estSuperviseur(getAuth().getUser().getAgent().getId()) && (!(getAuth().getUser().getAgent().estRH())) && (!(getAuth().getUser().getAgent().estDG()))) {
            return abSrv.listDemAbsAValiderParSH((getAuth().getUser().getAgent().getId()));
        }
        
//        if (getAuth().getUser().getAgent().estRH()) {
//            //return (abSrv.listDemAbsAValiderPourRH());
//            return (abSrv.listDemAbsAValiderParRH());
//        }
        
                if ((getAuth().getUser().getAgent().estRH()) && (agtSrv.estSuperviseur(getAuth().getUser().getAgent().getId()))) {
            //return (abSrv.listDemAbsAValiderPourRH());
           
            
            List<DemandePermissionAbsence> ajout = abSrv.listDemAbsAValiderParSH((getAuth().getUser().getAgent().getId()));
            ajout.addAll(abSrv.listDemAbsAValiderParRH());
            
            return ajout;
            
            //return abSrv.listDemAbsAValiderParSH((getAuth().getUser().getAgent().getId()));
        }
                
              
        
//        if (getAuth().getUser().getAgent().estDaf()) {
//            System.out.println("User connected est DAF");
//            return (abSrv.listDemAbsAValiderPourDAF());
//        }
//        
//        if (getAuth().getUser().getAgent().estSG()) {
//            return (abSrv.listDemAbsAValiderPourSG());
//        }
        
        if (getAuth().getUser().getAgent().estDG()) {
            JUtil.s("getListDemAbsAValider 3");
            return abSrv.listDemAbsAValiderParDG();
        } else {
            return null;
        }
    }
    
//    public List<DemandePermissionAbsence> getListDemAbsAValider() throws EchecSelectException {
//
//        if (agtSrv.estSuperviseur(getAuth().getUser().getAgent().getId()) && (!(getAuth().getUser().getAgent().estRH())) && (!(getAuth().getUser().getAgent().estDG()))) {
//            return abSrv.listDemAbsAValiderParSH((getAuth().getUser().getAgent().getId()));
//        }
//        
//        if (getAuth().getUser().getAgent().estRH()) {
//            //return (abSrv.listDemAbsAValiderPourRH());
//            return (abSrv.listDemAbsAValiderParRH());
//        }
//        
//        if (getAuth().getUser().getAgent().estDG()) {
//            JUtil.s("getListDemAbsAValider 3");
//            return abSrv.listDemAbsAValiderParDG();
//        } else {
//            return null;
//        }
//    }

    public List<DemandePermissionAbsence> getListDemAbsAValiderPourDG() throws EchecSelectException {

        if (getAuth().getUser().getAgent().estRH()) {
            return (abSrv.listDemAbsAValiderParDG());

        } else {
            return null;
        }
    }

    public List getListDemAbsValiderSH() throws EchecSelectException {
        return abSrv.listDemAbsValiderParSH();
    }

    public boolean peutValiderPourDG(DemandePermissionAbsence demAbs) throws EchecSelectException {
        String nivValidation = abSrv.validationAFaire(demAbs).getNiveauValidation().getCode();
        boolean bResult = true;

        if (nivValidation.equals(NiveauValidationDemandeEnum.ValidationDG.toString())) {
            bResult = getAuth().getUser().getAgent().estDG() || getAuth().getUser().getAgent().estRH();
        } else {
            bResult = false;
        }

        return bResult;
    }

    public boolean peutValider(DemandePermissionAbsence demAbs) throws EchecSelectException {
        String nivValidation = abSrv.validationAFaire(demAbs).getNiveauValidation().getCode(); //demAbs.getValidation().getNiveauValidation().getCode();
        boolean bResult = true;

        if (nivValidation.equals(NiveauValidationDemandeEnum.ValidationSH.toString())) {
            bResult = (getAuth().getUser().getAgent().getId().equals(demAbs.getAbsence().getAgent().getSuperviseur().getId()) && (abSrv.estValidable(demAbs.getId(), getAuth().getSessUserId())));
        }

        if (nivValidation.equals(NiveauValidationDemandeEnum.ValidationRH.toString())) {
            bResult = (getAuth().getUser().getAgent().estRH() && (abSrv.estValidable(demAbs.getId(), getAuth().getSessUserId())));
        }

        if (nivValidation.equals(NiveauValidationDemandeEnum.ValidationDG.toString())) {
            bResult = (getAuth().getUser().getAgent().estDG() && (abSrv.estValidable(demAbs.getId(), getAuth().getSessUserId())));
        }

        return bResult;
    }


    public String goValid(DemandePermissionAbsence demAbs) {
        obj = demAbs;
         scAgent = scSrv.getSoldeConges(demAbs.getAbsence().getAgent().getId());
        return "/RH/validationCongesForm";
    }

    public String goInfo(DemandePermissionAbsence demAbs) {
        obj = demAbs;
        
        if (demAbs != null && demAbs.getAbsence() != null) {
            scAgent = scSrv.getSoldeConges(demAbs.getAbsence().getAgent().getId());
        }
        return "/RH/demandesCongesInfo";
    }
    
    public String goMaDemande(DemandePermissionAbsence demAbs) {
        obj = demAbs;
        
        if (demAbs != null && demAbs.getAbsence() != null) {
            scAgent = scSrv.getSoldeConges(demAbs.getAbsence().getAgent().getId());
        }
        return "/RH/maDemandeInfo";
    }

    public String goInfoAbsence(Absence abs) {
        obj = abs.getDemandePermissionAbsence();
         scAgent = scSrv.getSoldeConges(abs.getAgent().getId());
        return "absenceInfo";
    }

    public String accepter() {
        DemandePermissionAbsence demAbs = (DemandePermissionAbsence) obj;
        Absence abs = demAbs.getAbsence();
        String nivValidation;
        
        try {
            nivValidation = abSrv.validationAFaire(demAbs).getNiveauValidation().getCode();          
            // Cas d'une validation du Supérieur Hierarchique
            if (nivValidation.equals(NiveauValidationDemandeEnum.ValidationSH.toString())) {
                niveau = tvSrv.getRef(TableValeurTypeEnum.NiveauValidation, NiveauValidationDemandeEnum.ValidationRH.toString());
                etat = tvSrv.getRef(TableValeurTypeEnum.EtatValidation, EtatValidationEnum.Encours.toString());
                
                if (estRH()){
                    niveau = tvSrv.getRef(TableValeurTypeEnum.NiveauValidation, NiveauValidationDemandeEnum.ValidationDG.toString());
                
                }
                // Appel de la mèthode Acception de la Demande
               acceptationSH(demAbs);    
            } 
                
                
            // Cas d'une validation des RH
            if (nivValidation.equals(NiveauValidationDemandeEnum.ValidationRH.toString())) {
                //niveau = tvSrv.getRef(TableValeurTypeEnum.NiveauValidation, NiveauValidationDemandeEnum.ValidationDAF.toString());
                niveau = tvSrv.getRef(TableValeurTypeEnum.NiveauValidation, NiveauValidationDemandeEnum.ValidationDG.toString());
                etat = tvSrv.getRef(TableValeurTypeEnum.EtatValidation, EtatValidationEnum.Encours.toString());
                System.out.println("Imprime " + this.scAgent.getNbCongesAcquis());
               // Appel de la mèthode Acception de la Demande     
              acceptationRH(demAbs);
              
                
                
            }
        
            // Cas d'une validation DG
            if (nivValidation.equals(NiveauValidationDemandeEnum.ValidationDG.toString())) {
                niveau = tvSrv.getRef(TableValeurTypeEnum.NiveauValidation, NiveauValidationDemandeEnum.ValidationDG.toString());
                etat = tvSrv.getRef(TableValeurTypeEnum.EtatValidation, EtatValidationEnum.Acceptee.toString());
                demAbs.setEtat(tvSrv.getRef(TableValeurTypeEnum.EtatValidation, EtatValidationEnum.Acceptee.toString()));
                demAbs.setDateAcceptation(JIDate.now());
                
                // Appel de la mèthode Acception de la Demande 
                
              acceptationDG(demAbs);
               
                // Mise à jour du solde de congés
                abs.setEtat(tvSrv.getRef(TableValeurTypeEnum.EtatAbsence, EtatAbsenceEnum.Acceptee.toString()));
                abs = (Absence)scSrv.save(abs);
                
                if ((abs.getTypeAbsence().getCode().equals(AbsenceEnum.PermAbsDeducConge.toString())) || (abs.getTypeAbsence().getCode().equals(AbsenceEnum.PermAbsExcep.toString()))) {
                    SoldeConges sc = scSrv.getSoldeConges(abs.getAgent().getId());
                    sc.setNbCongesEchus(sc.getNbCongesEchus() - abs.getNbJourDeductible());
                    System.out.println("Le nombre Jr de l'abs : "+ abs.getNbJourDeductible());
                    System.out.println("Le nombre Jr pris avant : "+ sc.getNbJourPrisDeductible());
                    sc.setNbJourPrisDeductible(sc.getNbJourPrisDeductible() + abs.getNbJourDeductible());
                    sc.setNbJourPris(sc.getNbJourPrisDeductible());
                    System.out.println("Le nombre Jr pris avant : "+ sc.getNbJourPris());
                    sc.setNbCongesReliquat(sc.getNbCongesReliquat() - abs.getNbJourDeductible());                
                    //sc.setNbJourPrisDeductible(sc.getNbJourPrisDeductible() + abs.getNbJourDeductible());
                    sc.setNbJourPrisNonDeductible(sc.getNbJourPrisNonDeductible() + abs.getNbJourNonDeductible());
                    sc = (SoldeConges)scSrv.save(sc);                    
                }
            }
            validation(demAbs);
            
            abs = (Absence)scSrv.save(abs);
 
        } catch (EchecSelectException ex) {
            JsfUtil.addExceptionMessage("Une erreur est survenue lors de la validation de la demande (1)", ex);
            return null;

        } catch (CMUServiceException ex) {
            JsfUtil.addExceptionMessage("Une erreur est survenue lors de la validation de la demande (2)", ex);
            return null;
        } catch (Exception ex) {
            Logger.getLogger(DemPermAbsBean.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        //validationP();
        return "/RH/validationConges";
    }

    public String refuser() throws EchecSelectException, CMUServiceException {
        DemandePermissionAbsence demAbs = (DemandePermissionAbsence) obj;
        String nivValidation = abSrv.validationAFaire(demAbs).getNiveauValidation().getCode(); //demAbs.getValidation().getNiveauValidation().getCode();

        if (nivValidation.equals(NiveauValidationDemandeEnum.ValidationSH.toString())) {
            niveau = tvSrv.getRef(TableValeurTypeEnum.NiveauValidation, NiveauValidationDemandeEnum.ValidationSH.toString());
            etat = tvSrv.getRef(TableValeurTypeEnum.EtatValidation, EtatValidationEnum.Refusee.toString());
            demAbs.setEtat(tvSrv.getRef(TableValeurTypeEnum.EtatValidation, EtatValidationEnum.Refusee.toString()));
            
            // Appel de la mèthode Refus de la Demande
               refusSH(demAbs);    
               // FIn Refus de la demande

        }

        if (nivValidation.equals(NiveauValidationDemandeEnum.ValidationRH.toString())) {
            niveau = tvSrv.getRef(TableValeurTypeEnum.NiveauValidation, NiveauValidationDemandeEnum.ValidationRH.toString());
            etat = tvSrv.getRef(TableValeurTypeEnum.EtatValidation, EtatValidationEnum.Refusee.toString());
            demAbs.setEtat(tvSrv.getRef(TableValeurTypeEnum.EtatValidation, EtatValidationEnum.Refusee.toString()));
            
            // Appel de la mèthode Refus de la Demande
               refusRH(demAbs);    
               // FIn Refus de la demande

        }

        if (nivValidation.equals(NiveauValidationDemandeEnum.ValidationDG.toString())) {
            niveau = tvSrv.getRef(TableValeurTypeEnum.NiveauValidation, NiveauValidationDemandeEnum.ValidationDG.toString());
            etat = tvSrv.getRef(TableValeurTypeEnum.EtatValidation, EtatValidationEnum.Refusee.toString());
            demAbs.setEtat(tvSrv.getRef(TableValeurTypeEnum.EtatValidation, EtatValidationEnum.Refusee.toString()));
            
            // Appel de la mèthode Refus de la Demande
               refusDG(demAbs);    
               // FIn Refus de la demande

        }
        validation(demAbs);
        
        // Mise à jour du solde de congés
        Absence abs = demAbs.getAbsence();
        abs.setEtat(tvSrv.getRef(TableValeurTypeEnum.EtatAbsence, EtatAbsenceEnum.Refusee.toString()));
        abs = (Absence)scSrv.save(abs);
      
        return "validationConges";
    }

    public void validation(DemandePermissionAbsence demAbs) throws CMUServiceException {
        String nivValidation = abSrv.validationAFaire(demAbs).getNiveauValidation().getCode();

        ValidationDemande vd = new ValidationDemande(); 
        /*Bout de Code a sauvegarder 
        //abSrv.getNextValidation(demAbs.getId());
        //ValidationDemande vd = abSrv.getNextValidation(demAbs.getId());*/
        vd.setDateValidation(JIDate.now());
        vd.setDemandePermissionAbsence(demAbs);
        vd.setNiveauValidation(niveau);
        vd.setEtat(etat);
        vd.setNote(raison);
        vd.setValidateur(getAuth().getUser());
        vd.setValidation(1);
        
        //String nivValidation = abSrv.validationAFaire(demAbs).getNiveauValidation().getCode();
         if (nivValidation.equals(NiveauValidationDemandeEnum.ValidationSH.toString())) {
            vd.setStatut("AvaliderSH");
            if (estRH()){
                vd.setStatut("AvaliderRH");
            }
         }
         
         if (nivValidation.equals(NiveauValidationDemandeEnum.ValidationRH.toString())) {
             vd.setStatut("AvaliderRH"); 
         }
         if (nivValidation.equals(NiveauValidationDemandeEnum.ValidationDG.toString())) {
              vd.setStatut("AvaliderDG");
         }
       
        vd = (ValidationDemande) srv.save(vd);        
        //demAbs = (DemandePermissionAbsence) srv.save(demAbs);
    }

    public boolean peutImprimer(DemandePermissionAbsence demAbs) throws EchecSelectException {
        String nivValidation = abSrv.validationAFaire(demAbs).getNiveauValidation().getCode();
        String etat = abSrv.validationAFaire(demAbs).getEtat().getCode();
        boolean bResult = false;

        if (((nivValidation.equals(NiveauValidationDemandeEnum.ValidationDG.toString())) || (nivValidation.equals(NiveauValidationDemandeEnum.ValidationRH.toString()))) 
                && ((etat.equals(EtatValidationEnum.Encours.toString()))) || ((etat.equals(EtatValidationEnum.Acceptee.toString()))) 
                || ((etat.equals(EtatValidationEnum.Refusee.toString())))) {
            bResult = getAuth().getUser().getAgent().estDG() || getAuth().getUser().getAgent().estRH();
        } return bResult;
        
    }

    public SoldeConges getScAgent() {
        return scAgent;
    }

    public void setScAgent(SoldeConges scAgent) {
        this.scAgent = scAgent;
    }
       
    public boolean getDepassementNbJourEchus() {
        DemandePermissionAbsence demAbs = (DemandePermissionAbsence) obj;
        Absence abs;
        
        if (demAbs != null && demAbs.getAbsence() != null) {
            abs = demAbs.getAbsence();            
            return (scAgent.getNbCongesEchus() < abs.getNbJourDeductible());
        }
        
        return false;
    }

    public String getRaison() {
        return raison;
    }

    public void setRaison(String raison) {
        this.raison = raison;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }
  
    public void printPDF(int idAbs, DemandePermissionAbsence demAbs) throws JRException, IOException{
       Connection con = null;
       con = srv.getJDBCConnection();
       
        if (demAbs.getAbsence().getTypeAbsence().getCode().equals(AbsenceEnum.PermAbsDeducConge.toString())) {
            
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("id", idAbs);
            String filename = "reportAbs"+ demAbs.getId().toString().trim() + ".pdf";
            String jasperPath = "report/reportDmdAbs.jasper";
            this.PDF(params, con, jasperPath, filename);
        } 
        if (demAbs.getAbsence().getTypeAbsence().getCode().equals(AbsenceEnum.PermAbsNonDeducConge.toString())) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("id", idAbs);
            String filename = "reportAbsNon-deduct"+ demAbs.getId().toString().trim() + ".pdf";
            String jasperPath = "report/reportDmdAbsExcep.jasper";
            this.PDF(params, con, jasperPath, filename);
        }
    }
    
    public void PDF(Map<String, Object> params, Connection conn,String jasperPath, String fileName) throws JRException,IOException{
        String relativeWebPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(jasperPath);
        File file = new File(relativeWebPath);
        JasperPrint print = JasperFillManager.fillReport(file.getPath(),params,conn);
        HttpServletResponse response = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=" +fileName);
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(print, stream);
        FacesContext.getCurrentInstance().responseComplete();         
    }
    
    
    public void acceptationSH(DemandePermissionAbsence demAbs){
        /*****************************************************************************
                * Préparation des Elements pour envoyer un mail de notification
                */
               //Récuperation des Elements essentiels à l'envoi du mail
            try{ 
               int numDemande = demAbs.getId();
               String dateDebutAbsence = JIDate.formatDateHeure( demAbs.getAbsence().getDateDebutAbsence());

               String dateFinAbsence = JIDate.formatDateHeure(demAbs.getAbsence().getDateFinAbsence());
               String dateValidationSH = JIDate.formatDateHeure(JIDate.now());

               String emailAgent = demAbs.getAbsence().getAgent().getEmailProfessionnel();
               String emailSup = demAbs.getAbsence().getAgent().getSuperviseur().getEmailProfessionnel();
               String nomAgent = demAbs.getAbsence().getAgent().getNomComplet();
               String nomSup = demAbs.getAbsence().getAgent().getSuperviseur().getNomComplet();
               String typeConge = demAbs.getAbsence().getTypeAbsence().getLib();
               //Recuperation du mail de Notification
               ParametreMail notif = srvNotif.getMailNotification();
               String mailInterne = notif.getMail();
               String mdp = notif.getMdp();
               String smtpHost = notif.getSmtpHost();
               String smtpPort = notif.getSmtpPort();
               // Reccuperation du Mail RH
               ParametreMail adresseMailRH = srvNotif.getMailRH();
               String emailRH = adresseMailRH.getMail();
              
                JMailAccDemAgentSH mailNotifSH = new JMailAccDemAgentSH(typeConge, emailRH, emailSup, emailAgent, mailInterne, nomAgent, nomSup, numDemande, dateValidationSH, dateDebutAbsence, dateFinAbsence);
               /* Envoie Notification à l'agent */
                mailNotifSH.sendMailToAgent(emailAgent, mailInterne, mdp, smtpHost, smtpPort);
                
                /* Envoie Notification au SH */
                mailNotifSH.sendMailToSH(emailSup, mailInterne, mdp, smtpHost, smtpPort);
                //mailNotifSH.sendMailToSH("thiamissa@gmail.com", mailInterne, mdp, smtpHost, smtpPort);
                
                /* Envoie Notification au RH */
                mailNotifSH.sendMailToRH(emailRH, mailInterne, mdp, smtpHost, smtpPort);
                
            } catch (Exception ex) {
                Logger.getLogger(DemPermAbsBean.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    
    public void acceptationRH(DemandePermissionAbsence demAbs){
        try {
            /*****************************************************************************
             * Préparation des Elements pour envoyer un mail de notification
             */
            //Récuperation des Elements essentiels à l'envoi du mail
            
            int numDemande = demAbs.getId();
            String dateDebutAbsence = JIDate.formatDateHeure( demAbs.getAbsence().getDateDebutAbsence());
            
            String dateFinAbsence = JIDate.formatDateHeure(demAbs.getAbsence().getDateFinAbsence());
            String dateValidationRH = JIDate.formatDateHeure(JIDate.now());
            
            String emailAgent = demAbs.getAbsence().getAgent().getEmailProfessionnel();
            String emailSup = demAbs.getAbsence().getAgent().getSuperviseur().getEmailProfessionnel();
            String nomAgent = demAbs.getAbsence().getAgent().getNomComplet();
            String nomSup = demAbs.getAbsence().getAgent().getSuperviseur().getNomComplet();
            String typeConge = demAbs.getAbsence().getTypeAbsence().getLib();
            
            //Recuperation du mail de Notification
            ParametreMail notif = srvNotif.getMailNotification();
            String mailInterne = notif.getMail();
            String mdp = notif.getMdp();
            String smtpHost = notif.getSmtpHost();
            String smtpPort = notif.getSmtpPort();
            // Reccuperation du Mail RH
            ParametreMail adresseMailRH = srvNotif.getMailRH();
            String emailRH = adresseMailRH.getMail();
            
          
            //Reccuperation du Mail DG
            ParametreMail adresseMailDG = srvNotif.getMailDG();
            String emailDG = adresseMailDG.getMail();
          

           
           
            
           
            
            JMailAccDemRH mailNotifRH = new JMailAccDemRH(typeConge, emailDG, emailRH, emailSup, emailAgent, mailInterne, nomAgent, nomSup, numDemande, dateValidationRH, dateDebutAbsence, dateFinAbsence);
            /* Envoie Notification à l'agent */
            mailNotifRH.sendMailToAgent(emailAgent, mailInterne, mdp, smtpHost, smtpPort);
            
            /* Envoie Notification au SH */
            mailNotifRH.sendMailToSH(emailSup, mailInterne, mdp, smtpHost, smtpPort);
            
            /* Envoie Notification au RH */
            mailNotifRH.sendMailToRH(emailRH, mailInterne, mdp, smtpHost, smtpPort);
            
            /* Envoie Notification au DG */
            mailNotifRH.sendMailToDG(emailDG, mailInterne, mdp, smtpHost, smtpPort);
            
        } catch (Exception ex) {
            Logger.getLogger(DemPermAbsBean.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    
    public void acceptationDG(DemandePermissionAbsence demAbs){
        try {
            /*****************************************************************************
             * Préparation des Elements pour envoyer un mail de notification
             */
            
            
            //Récuperation des Elements essentiels à l'envoi du mail
            
            int numDemande = demAbs.getId();
            String dateDebutAbsence = JIDate.formatDateHeure( demAbs.getAbsence().getDateDebutAbsence());
            
            String dateFinAbsence = JIDate.formatDateHeure(demAbs.getAbsence().getDateFinAbsence());
            String dateValidationDG = JIDate.formatDateHeure(JIDate.now());
            
            String emailAgent = demAbs.getAbsence().getAgent().getEmailProfessionnel();
            String emailSup = demAbs.getAbsence().getAgent().getSuperviseur().getEmailProfessionnel();
            String nomAgent = demAbs.getAbsence().getAgent().getNomComplet();
            String nomSup = demAbs.getAbsence().getAgent().getSuperviseur().getNomComplet();
            String typeConge = demAbs.getAbsence().getTypeAbsence().getLib();
            //Recuperation du mail de Notification
            ParametreMail notif = srvNotif.getMailNotification();
            String mailInterne = notif.getMail();
            String mdp = notif.getMdp();
            String smtpHost = notif.getSmtpHost();
            String smtpPort = notif.getSmtpPort();
            // Reccuperation du Mail RH
            ParametreMail adresseMailRH = srvNotif.getMailRH();
            String emailRH = adresseMailRH.getMail();
            // Reccuperation du Mail DG
            ParametreMail adresseMailDG = srvNotif.getMailDG();
            String emailDG = adresseMailDG.getMail();

            JMailAccDemDG mailNotifDG = new JMailAccDemDG(typeConge, emailDG, emailRH, emailSup, emailAgent, mailInterne, nomAgent, nomSup, numDemande, dateValidationDG, dateDebutAbsence, dateFinAbsence);
            /* Envoie Notification à l'agent */
            mailNotifDG.sendMailToAgent(emailAgent, mailInterne, mdp, smtpHost, smtpPort);
            
            /* Envoie Notification au SH */
            mailNotifDG.sendMailToSH(emailSup, mailInterne, mdp, smtpHost, smtpPort);
            
            /* Envoie Notification au RH */
            mailNotifDG.sendMailToRH(emailRH, mailInterne, mdp, smtpHost, smtpPort);
            
            /* Envoie Notification au DG */
            mailNotifDG.sendMailToDG(emailDG, mailInterne, mdp, smtpHost, smtpPort);
            
            //Fin envoie des mails
        } catch (Exception ex) {
            Logger.getLogger(DemPermAbsBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void refusSH(DemandePermissionAbsence demAbs){
        /*****************************************************************************
                * Préparation des Elements pour envoyer un mail de notification
                */
               //Récuperation des Elements essentiels à l'envoi du mail
            try{ 
               int numDemande = demAbs.getId();
               String dateDebutAbsence = JIDate.formatDateHeure( demAbs.getAbsence().getDateDebutAbsence());

               String dateFinAbsence = JIDate.formatDateHeure(demAbs.getAbsence().getDateFinAbsence());
               String dateRefusSH = JIDate.formatDateHeure(JIDate.now());

               String emailAgent = demAbs.getAbsence().getAgent().getEmailProfessionnel();
               
               String nomAgent = demAbs.getAbsence().getAgent().getNomComplet();
               String nomSup = demAbs.getAbsence().getAgent().getSuperviseur().getNomComplet();
               String typeConge = demAbs.getAbsence().getTypeAbsence().getLib();
               //Recuperation du mail de Notification
                ParametreMail notif = srvNotif.getMailNotification();
                String mailInterne = notif.getMail();
                String mdp = notif.getMdp();
                String smtpHost = notif.getSmtpHost();
                String smtpPort = notif.getSmtpPort();
              
                JMailRefDemAgentSH mailNotifSH = new JMailRefDemAgentSH(typeConge, emailAgent, mailInterne, nomAgent, nomSup, numDemande, dateRefusSH, dateDebutAbsence, dateFinAbsence);
               /* Envoie Notification à l'agent */
                mailNotifSH.sendMailToAgent(emailAgent, mailInterne, mdp, smtpHost, smtpPort);
               
                
            } catch (Exception ex) {
                Logger.getLogger(DemPermAbsBean.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    
    
    public void refusRH(DemandePermissionAbsence demAbs){
        try {
            /*****************************************************************************
             * Préparation des Elements pour envoyer un mail de notification
             */
            //Récuperation des Elements essentiels à l'envoi du mail
            
            int numDemande = demAbs.getId();
            String dateDebutAbsence = JIDate.formatDateHeure( demAbs.getAbsence().getDateDebutAbsence());
            
            String dateFinAbsence = JIDate.formatDateHeure(demAbs.getAbsence().getDateFinAbsence());
            String dateRefusRH = JIDate.formatDateHeure(JIDate.now());
            
            String emailAgent = demAbs.getAbsence().getAgent().getEmailProfessionnel();
            String emailSup = demAbs.getAbsence().getAgent().getSuperviseur().getEmailProfessionnel();
            String nomAgent = demAbs.getAbsence().getAgent().getNomComplet();
            String nomSup = demAbs.getAbsence().getAgent().getSuperviseur().getNomComplet();
            String typeConge = demAbs.getAbsence().getTypeAbsence().getLib();
            //Recuperation du mail de Notification
            ParametreMail notif = srvNotif.getMailNotification();
            String mailInterne = notif.getMail();
            String mdp = notif.getMdp();
            String smtpHost = notif.getSmtpHost();
            String smtpPort = notif.getSmtpPort();
            // Reccuperation du Mail RH
            ParametreMail adresseMailRH = srvNotif.getMailRH();
            String emailRH = adresseMailRH.getMail();
            // Reccuperation du Mail DG
            ParametreMail adresseMailDG = srvNotif.getMailDG();
            String emailDG = adresseMailDG.getMail();

            String mailRH = "";
            String mailDG = "";
            
          
            
            JMailRefDemRH mailNotifRH = new JMailRefDemRH(typeConge, emailSup, emailAgent, mailInterne, nomAgent, nomSup, numDemande, dateRefusRH, dateDebutAbsence, dateFinAbsence);
            /* Envoie Notification à l'agent */
            mailNotifRH.sendMailToAgent(emailAgent, mailInterne, mdp, smtpHost, smtpPort);
            
            /* Envoie Notification au SH */
            mailNotifRH.sendMailToSH(emailSup, mailInterne, mdp, smtpHost, smtpPort);
            
            
            
        } catch (Exception ex) {
            Logger.getLogger(DemPermAbsBean.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    
    public void refusDG(DemandePermissionAbsence demAbs){
        try {
            /*****************************************************************************
             * Préparation des Elements pour envoyer un mail de notification
             */
            
            
            //Récuperation des Elements essentiels à l'envoi du mail
            
            int numDemande = demAbs.getId();
            String dateDebutAbsence = JIDate.formatDateHeure( demAbs.getAbsence().getDateDebutAbsence());
            
            String dateFinAbsence = JIDate.formatDateHeure(demAbs.getAbsence().getDateFinAbsence());
            String dateRefusDG = JIDate.formatDateHeure(JIDate.now());
            
            String emailAgent = demAbs.getAbsence().getAgent().getEmailProfessionnel();
            String emailSup = demAbs.getAbsence().getAgent().getSuperviseur().getEmailProfessionnel();
            String nomAgent = demAbs.getAbsence().getAgent().getNomComplet();
            String nomSup = demAbs.getAbsence().getAgent().getSuperviseur().getNomComplet();
            String typeConge = demAbs.getAbsence().getTypeAbsence().getLib();
            //Recuperation du mail de Notification
            ParametreMail notif = srvNotif.getMailNotification();
            String mailInterne = notif.getMail();
            String mdp = notif.getMdp();
            String smtpHost = notif.getSmtpHost();
            String smtpPort = notif.getSmtpPort();
            // Reccuperation du Mail RH
            ParametreMail adresseMailRH = srvNotif.getMailRH();
            String emailRH = adresseMailRH.getMail();
            
            
            JMailRefDemDG mailNotifDG = new JMailRefDemDG(typeConge,emailRH, emailSup, emailAgent, mailInterne, nomAgent, nomSup, numDemande, dateRefusDG, dateDebutAbsence, dateFinAbsence);
            /* Envoie Notification à l'agent */
            mailNotifDG.sendMailToAgent(emailAgent, mailInterne, mdp, smtpHost, smtpPort);
            
            /* Envoie Notification au SH */
            mailNotifDG.sendMailToSH(emailSup, mailInterne, mdp, smtpHost, smtpPort);
            
            /* Envoie Notification au RH */
            mailNotifDG.sendMailToRH(emailRH, mailInterne, mdp, smtpHost, smtpPort);
            
           
            
            //Fin envoie des mails
        } catch (Exception ex) {
            Logger.getLogger(DemPermAbsBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean estSHouRH() {
        return (estSuperviseur() || estRH() || estDaf() || estAdmin() || estDir());
    }
    
    public boolean estSuperviseur() {
        return agtSrv.estSuperviseur(getAuth().getUser().getAgent().getId());
    }

    public boolean estRH() {
        return (getAuth().getUser().getAgent().estRH());
    }
    
    public boolean estDir() {
        return (getAuth().getUser().getAgent().estDIR());
    }
    
    public boolean estDaf() {
        return (getAuth().getUser().getAgent().estDaf());
    }
    
    public boolean estAdmin() {
        return (getAuth().getUser().getAgent().estRoot());
    }
    
    public boolean getFileUpload(){
        DemandePermissionAbsence m_obj = (DemandePermissionAbsence) obj;
        return ((m_obj.getAbsence().getTypeAbsence().getLib().equalsIgnoreCase("Non déductible")) 
                && this.estSHouRH() && (m_obj.getAbsence().getFileUploaded() != null ));
    }
    
    public boolean estEtatCree(){
        DemandePermissionAbsence m_obj = (DemandePermissionAbsence) obj;
        return  (etat.equals(EtatValidationEnum.Encours.toString()));
           
    }
    
    public String envoyer() {
        
       
        //Absence abs;
        DemandePermissionAbsence m_obj = (DemandePermissionAbsence) obj;
       
        Absence abs = m_obj.getAbsence();
       //String nivValidation;

  
        
        // Enregistrement de la demande d'absence
        try {
            
            TableValeur nivValidation;
            TableValeur noteValidation;
            TableValeur etatAbsence = tvSrv.getRef(TableValeurTypeEnum.EtatAbsence, EtatAbsenceEnum.Demandee.toString());
        
            // Information sur la demande d'absence
            
            m_obj.getAbsence().setEtat(etatAbsence);
            

            // Information sur la demande de validation
            ValidationDemande valiDem = new ValidationDemande();
            if (m_obj.getAbsence().getAgent().superviseurEstDG()) {
                nivValidation = tvSrv.getRef(TableValeurTypeEnum.NiveauValidation, NiveauValidationDemandeEnum.ValidationRH.toString());
                //noteValidation = tvSrv.getRef(TableValeurTypeEnum.NoteValidation, NoteValidationEnum.AccordSH.toString());
            } else {
                nivValidation = tvSrv.getRef(TableValeurTypeEnum.NiveauValidation, NiveauValidationDemandeEnum.ValidationSH.toString());
                //noteValidation = tvSrv.getRef(TableValeurTypeEnum.NoteValidation, NoteValidationEnum.NoteInitial.toString());
            }

            valiDem.setEtat(m_obj.getEtat());
            valiDem.setNiveauValidation(nivValidation);
            valiDem.setDemandePermissionAbsence(m_obj);
            valiDem.setValidation(0);
            valiDem.setStatut("AvaliderSH");
            m_obj.addValidation(valiDem);
            
           

            // Sauvegarde de la demande (Absence, DemandePermissionAbsence, ValidationDemande)
            m_obj.setAbsence((Absence) srv.save(m_obj.getAbsence()));
            

            int numDemande = m_obj.getId();
            String dateDebutAbsence = JIDate.formatDateHeure( m_obj.getAbsence().getDateDebutAbsence());
                   
            String dateFinAbsence = JIDate.formatDateHeure(m_obj.getAbsence().getDateFinAbsence());
            String dateDemande = JIDate.formatDateHeure(m_obj.getDateDemande());
            
            String emailAgent = m_obj.getAbsence().getAgent().getEmailProfessionnel();
            String emailSup = m_obj.getAbsence().getAgent().getSuperviseur().getEmailProfessionnel();
            String nomAgent = m_obj.getAbsence().getAgent().getNomComplet();
            String nomSup = m_obj.getAbsence().getAgent().getSuperviseur().getNomComplet();
            String typeConge = m_obj.getAbsence().getTypeAbsence().getLib();
            ParametreMail notif = srvNotif.getMailNotification();
            String mailInterne = notif.getMail();
            String mdp = notif.getMdp();
            String smtpHost = notif.getSmtpHost();
            String smtpPort = notif.getSmtpPort();
           
            
            
            //Création de l'Instance Notification de la Demande de l'Agent
            JMailNotifDemAgent jmail = new JMailNotifDemAgent(typeConge, emailSup, emailAgent, emailAgent, nomAgent, nomSup, numDemande, dateDemande, dateDebutAbsence, dateFinAbsence);
            /*
            *On envoie le mail à l'agent
            */
            
           //jmail.sendMailToAgent("thiamissa@gmail.com", mailInterne, mdp, smtpHost, smtpPort);
           jmail.sendMailToAgent(emailAgent, mailInterne, mdp, smtpHost, smtpPort);
            
            /*
            *On envoie le mail au SH
            */
            
            jmail.sendMailToSH(emailSup, mailInterne, mdp, smtpHost, smtpPort);
            //jmail.sendMailToSH("thiamissa@gmail.com", mailInterne, mdp, smtpHost, smtpPort);
            /*
            *Fin Envoie de mail
            */
       

        } catch (CMUServiceException ex) {
            JsfUtil.addErrorMessage("Une erreur est survenue lors de l'enregistrement de la demande");
            return null;
        }catch (Exception ex) {
            Logger.getLogger(DemAbsenceBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "/RH/mesDemandesList.xhtml";
    }
    
    public String supprimer(){
        
         //Absence abs;
        DemandePermissionAbsence m_obj = (DemandePermissionAbsence) obj;
       
        Absence abs = m_obj.getAbsence();
        //Test Existance du FIchier joint
        if (m_obj.getAbsence().getFileUploaded() != null ){
                System.out.println("Fichier joint existe");
               
        }
        
        // A supprimer cette zone de code
       File file = new File(m_obj.getAbsence().getCheminFileUploaded()+"/"+m_obj.getAbsence().getFileUploaded());
        if(file.delete())
        {
            System.out.println("File deleted successfully");
        }
        else
        {
            System.out.println("Failed to delete the file");
        }
        
        try {
            
            srv.supprimer(abs);
            addMessage("Confirmé", "Demande Supprimée");
            
        } catch (CMUServiceException ex) {
            Logger.getLogger(DemAbsenceBean.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return "/RH/mesDemandesList.xhtml";
    }
    
    public String supprimerDmdeAcc(){
        
         //Absence abs;
        DemandePermissionAbsence m_obj = (DemandePermissionAbsence) obj;
       
        Absence abs = m_obj.getAbsence();
        SoldeConges scAgt = scSrv.getSoldeConges(abs.getAgent().getId());
        
        System.out.println("Avant suppression");
        System.out.println("Reliquat de l'agent: "+ scAgt.getNbCongesReliquat());
        System.out.println("Echus de l'agent: "+ scAgt.getNbCongesEchus());
        System.out.println("Pris de l'agent: "+ scAgt.getNbJourPris());
        
        //Mise a jour des Soldes COnges
        
        scAgt.setNbJourPris(scAgt.getNbJourPris() - abs.getNbJourDeductible());
        scAgt.setNbCongesReliquat(scAgt.getNbCongesReliquat() + abs.getNbJourDeductible());
        scAgt.setNbCongesEchus(scAgt.getNbCongesEchus() + abs.getNbJourDeductible());
        
        try {
            scAgt = (SoldeConges)scSrv.save(scAgt);
        } catch (CMUServiceException ex) {
            Logger.getLogger(DemPermAbsBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Nouveau Solde");
        System.out.println("Reliquat de l'agent: "+ scAgt.getNbCongesReliquat());
        System.out.println("Echus de l'agent: "+ scAgt.getNbCongesEchus());
        System.out.println("Pris de l'agent: "+ scAgt.getNbJourPris());
        
        //Test Existance du FIchier joint
        if (m_obj.getAbsence().getFileUploaded() != null ){
                System.out.println("Fichier joint existe");
               
        }
        
        // A supprimer cette zone de code
       File file = new File(m_obj.getAbsence().getCheminFileUploaded()+"/"+m_obj.getAbsence().getFileUploaded());
        if(file.delete())
        {
            System.out.println("File deleted successfully");
        }
        else
        {
            System.out.println("Failed to delete the file");
        }
        
        try {
            
            srv.supprimer(abs);
            addMessage("Confirmé", "Demande Supprimée");
            
        } catch (CMUServiceException ex) {
            Logger.getLogger(DemAbsenceBean.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return "/RH/demandesCongesList.xhtml";
    }
    
    

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
   
}
