package com.cmu.agence.rh.bean;

import com.cmu.agence.rh.domaine.Absence;
import com.cmu.agence.rh.domaine.AbsenceEnum;
import com.cmu.agence.rh.domaine.DemandePermissionAbsence;
import com.cmu.agence.rh.domaine.EtatAbsenceEnum;
import com.cmu.agence.rh.domaine.EtatValidationEnum;
import com.cmu.agence.rh.domaine.NiveauValidationDemandeEnum;
import com.cmu.agence.rh.domaine.SoldeConges;
import com.cmu.agence.rh.domaine.ValidationDemande;
import com.cmu.agence.rh.service.AbsenceService;
import com.cmu.agence.rh.service.SoldeCongesService;
import com.cmu.base.bean.BaseCRUDBean;
import com.cmu.base.bean.JsfUtil;
import com.cmu.base.domaine.BaseDomaine;
import com.cmu.base.service.BaseService;
import com.cmu.base.service.CommonExcepService;
import com.cmu.base.service.CommonLibService;
import com.cmu.base.service.exception.CMUServiceException;
import com.cmu.base.service.exception.EchecSelectException;
import com.cmu.mail.JMailAccDemAgentSH;
import com.cmu.mail.JMailAccDemDG;
import com.cmu.mail.JMailAccDemRH;

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
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JasperExportManager;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;

@Named("demAbsExcep")
@javax.enterprise.context.SessionScoped
public class DemAbsenceExcepBean extends BaseCRUDBean {

    String raison;
    TableValeur niveau;
    TableValeur etat;

    @Inject
    AuthentificationBean session;

    @EJB
    CommonExcepService srv;

    @EJB
    TableValeurService tvSrv;

     @EJB
    AbsenceService abSrv;

    @EJB
    AgentService agtSrv;
    
    @EJB
    SoldeCongesService scSrv;
    
    SoldeConges scAgent;
    
    private Part file1;
 

    public DemAbsenceExcepBean() {
        super(WebRacineEnum.RH, "absenceExceptionnelle");
    }

    @Override
    public BaseService getService() {
        return srv;
    }

    @Override
    public BaseDomaine getNewInstance() {
        return new DemandePermissionAbsence();
    }

    public Part getFile1() {
        return file1;
    }

    public void setFile1(Part file1) {
        this.file1 = file1;
    }
    
    

    
   
}
