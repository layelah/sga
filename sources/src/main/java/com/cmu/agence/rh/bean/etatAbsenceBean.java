 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmu.agence.rh.bean;

import com.cmu.agence.rh.domaine.Absence;
import com.cmu.agence.rh.domaine.DemandePermissionAbsence;
import com.cmu.agence.rh.service.ServiceSql;
import com.cmu.base.bean.BaseCRUDBean;
import com.cmu.base.bean.JsfUtil;
import com.cmu.base.domaine.BaseDomaine;
import com.cmu.base.service.BaseService;
import com.cmu.sigicmu.admin.domaine.Service;
import com.cmu.sigicmu.admin.service.AgentService;
import com.cmu.sigicmu.bureau.bean.AuthentificationBean;
import com.cmu.sigicmu.param.domaine.WebRacineEnum;
import com.cmu.sigicmu.param.service.TableValeurService;
import com.cmu.util.JIDate;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.sql.ResultSet;
import org.json.simple.JSONObject;
//import com.cmu.agence.rh.bean.DemPermAbsBean;
//import org.primefaces.context.RequestContext;


/**
 *
 * @author issathiam
 */

@Named("etatsAbsence")
@SessionScoped
public class etatAbsenceBean extends BaseCRUDBean {

    @Inject
    AuthentificationBean auth;
    
    @EJB
    TableValeurService tvSrv;

    @EJB
    ServiceSql srv;
    

    @EJB
    AgentService agtSrv;
    
    Date dateDebut;
    Date dateFin;
    String nomDirection;
    List<Absence> listeEtat;
    List<DemandePermissionAbsence> listDemAbs;
    String eventJSON;
    JSONObject objet;
    String fichierJson;
    String eventCLick;
    DemandePermissionAbsence demPerm;


    public etatAbsenceBean() {
        super(WebRacineEnum.RH, "etatAbsence");   
    }

    public etatAbsenceBean(AuthentificationBean auth, TableValeurService tvSrv, ServiceSql srv, AgentService agtSrv, Date dateDebut, Date dateFin, String nomDirection, List<Absence> listeEtat) {
        this.auth = auth;
        this.tvSrv = tvSrv;
        this.srv = srv;
        this.agtSrv = agtSrv;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nomDirection = nomDirection;
        this.listeEtat = listeEtat;
       
    }
    
    @Override
    public BaseService getService() {
        return srv;
    }

    @Override
    public BaseDomaine getNewInstance() {
           
        return new Service();
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getNomDirection() {
        return nomDirection;
    }

    public void setNomDirection(String nomDirection) {
        this.nomDirection = nomDirection;
    }

    public List<Absence> getListeEtat() {
       if(listeEtat == null){
            //listeEtat = srv.getEtatAbsenceAll();
        }        
        return listeEtat;
    }

    public void setListeEtat(List<Absence> listeEtat) {
        this.listeEtat = listeEtat;
    }

    public String getEventJSON() {
        
        return eventJSON;
    }

    public void setEventJSON(String eventJSON) {
        this.eventJSON = eventJSON;
    }

    public JSONObject getObjet() {
        return objet;
    }

    public void setObjet(JSONObject objet) {
        this.objet = objet;
    }

    public String getFichierJson() {
        return fichierJson;
    }

    public void setFichierJson(String fichierJson) {
        this.fichierJson = fichierJson;
    }

    public String getEventCLick() {
        return eventCLick;
    }

    public void setEventCLick(String eventCLick) {
        this.eventCLick = eventCLick;
    }

    public List<DemandePermissionAbsence> getListDemAbs() {
        if(listDemAbs == null){
            listDemAbs = srv.getEtatDemAbsenceAll();
        }
        return listDemAbs;
    }

    public void setListDemAbs(List<DemandePermissionAbsence> listDemAbs) {
        this.listDemAbs = listDemAbs;
    }

    public DemandePermissionAbsence getDemPerm() {
        return demPerm;
    }

    public void setDemPerm(DemandePermissionAbsence demPerm) {
        this.demPerm = demPerm;
    }
    
    
    
    
    
    
     
    
    
    public boolean yaErreurDansForm() {
        boolean yaErreur = false;
        

        if (JIDate.avantJour(getDateFin(), getDateDebut())) {
            JsfUtil.addErrorMessage("date_fin", "La date de fin selectionnée doit être postérieure à la date de début");
            yaErreur = true;
        }

        return yaErreur;        
    }
    
    public void restartEtats(){
        //this.setListDemAbs(srv.getEtatDemAbsenceAll());
       // PrimeFaces.current().resetInputs("form:panel");
        //RequestContext.getCurrentInstance().reset("panel");
       
    setDateDebut(null);
    setDateFin(null);
    srv.setService(null);
    if(listDemAbs == null){
            listDemAbs = srv.getEtatDemAbsenceAll();
        }
//end clear`
    }
    
    
    public void afficheEtats() throws SQLException{
        ResultSet rs = null;
            String aff1 = "";
            String res = "";
            
            System.out.println("On recherche ");
            System.out.println("evenJSON first:" +this.eventJSON);
            // Contrôles de la date
             if (yaErreurDansForm()) {
              // return null;
             }
            
            int id = srv.getService().getId();
            System.out.println("id Direction: "+id);
            
           
           //Recupération de la liste des DemPermAbsence pour obtenir les infos
           this.setListDemAbs(srv.getEtatDemAbsenceBySearch(id, dateDebut, dateFin));
            
            //System.out.println("Non Direction:" +nomDirection);
            System.out.println("Date Debut: "+JIDate.formatJsonDateHeure(dateDebut));
            System.out.println("Date de Fin: "+JIDate.formatJsonDateHeure(dateFin));

        try {
            rs = srv.getEtatForCalendar(srv.getService().id);
            
            System.out.println("Taille");
        
            res = srv.getJSONFromResultSet(rs).replace("\"", "'");
        } catch (SQLException ex) {
            Logger.getLogger(etatAbsenceBean.class.getName()).log(Level.SEVERE, null, ex);
        }
            System.out.println("Resultat: "+res);
            aff1 = res.substring(4, res.length()-1);
            this.setEventJSON(aff1);
            this.fichierJson = aff1;
            System.out.println("FIchier JSON: "+this.getEventJSON());
            System.out.println("JSON file created......"+ aff1+"FIn");
            System.out.println("JSON file created2......"+ fichierJson);
            String url = "https://www.google.com/";
            
            eventCLick+="eventClick:function(){"
                    +"window.open("+url+");}";
          
            
      restartEtats();      
            
    }
    
    

    public boolean estActive(){
        return this.eventJSON != null;
    }
    
    public boolean estNonActive(){
        return this.eventJSON == null;
    }
    
    
    
    

    
}
