/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmu.agence.rh.service;

import com.cmu.agence.rh.bean.DemPermAbsBean;
import com.cmu.agence.rh.domaine.Absence;
import com.cmu.agence.rh.domaine.DemandePermissionAbsence;
import com.cmu.agence.rh.domaine.EtatAbsenceEnum;
import com.cmu.agence.rh.domaine.ParametreMail;
import com.cmu.base.service.BaseService;
import com.cmu.sigicmu.admin.domaine.Service;
import com.cmu.util.JIDate;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author issathiam
 */
@Stateless
@LocalBean
public class NotifService extends BaseService {
    
    private String mail;
    private String mdp;
    private String comm;
    private String smtpHost;
    private String smtpPort;

    public NotifService() {
        
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getComm() {
        return comm;
    }

    public void setComm(String comm) {
        this.comm = comm;
    }

    public String getSmtpHost() {
        return smtpHost;
    }

    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    public String getSmtpPort() {
        return smtpPort;
    }

    public void setSmtpPort(String smtpPort) {
        this.smtpPort = smtpPort;
    }
    
    

    public ParametreMail getMailNotification() {

        String sql = "SELECT DISTINCT d "
                + " FROM ParametreMail as d"
                + " WHERE d.type = :typeParam ";
                
               
        
        
        Query q = getEm().createQuery(sql);
         q.setParameter("typeParam", "mailNotification");   
             
       
        return (ParametreMail) q.setMaxResults(1).getSingleResult();
    }
    
    public ParametreMail getMailRH() {

        String sql = "SELECT DISTINCT d "
                + " FROM ParametreMail as d"
                + " WHERE d.type = :typeParam ";
                
               
        
        
        Query q = getEm().createQuery(sql);
         q.setParameter("typeParam", "mailRH");   
             
       
        return (ParametreMail) q.setMaxResults(1).getSingleResult();
    }
    
    public ParametreMail getMailDG() {

        String sql = "SELECT DISTINCT d "
                + " FROM ParametreMail as d"
                + " WHERE d.type = :typeParam ";
                
               
        
        
        Query q = getEm().createQuery(sql);
         q.setParameter("typeParam", "mailDG");   
             
       
        return (ParametreMail) q.setMaxResults(1).getSingleResult();
    }
    

  
    public ResultSet getMailNotif(Integer idMail) throws SQLException{
	//ResultSet resultat = null;
        
        String req = " SELECT mail_notification, mot_de_passe, lib "
                + "from parametrage_mail ";
        

       Connection con = getJDBCConnection();
            PreparedStatement ps = con.prepareStatement(req);
            ps.setInt(1, idMail);
             
        
            ResultSet rs = ps.executeQuery();
            
            
            return rs;
       
        
    
   }
    
   
    
    
    
    
     
        
        
        

}
