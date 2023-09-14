/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmu.mail;

import com.cmu.agence.rh.service.NotifService;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author issathiam
 */
public class JMailNotifDemAgent {
    
    @EJB
    NotifService notif;
    
    private String typeConges;
    private String mailSH;
    private String mailAgent;
    private String mailInterne;
    private String nomAgent;
    private String nomSH;
    private int numDemande;
    private String dateDemande;
    private String dateDebut;
    private String dateFin;
    
    
    

/* COnstrutor */

    public JMailNotifDemAgent(String typeConges, String mailSH, String mailAgent, String mailInterne, String nomAgent, String nomSH, int numDemande, String dateDemande, String dateDebut, String dateFin) {
        this.typeConges = typeConges;
        this.mailSH = mailSH;
        this.mailAgent = mailAgent;
        this.mailInterne = mailInterne;
        this.nomAgent = nomAgent;
        this.nomSH = nomSH;
        this.numDemande = numDemande;
        this.dateDemande = dateDemande;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        
        this.notif = new NotifService();
    }

    public JMailNotifDemAgent() {
    }

    /* Getters and Setters*/
    
    public String getTypeConges() {    
        return typeConges;
    }


    public void setTypeConges(String typeConges) {
        this.typeConges = typeConges;
    }
    
    public String getMailSH() {
        return mailSH;
    }

    public void setMailSH(String mailSH) {
        this.mailSH = mailSH;
    }

    public String getMailAgent() {
        return mailAgent;
    }

    public void setMailAgent(String mailAgent) {
        this.mailAgent = mailAgent;
    }

    public String getMailInterne() {
        return mailInterne;
    }

    public void setMailInterne(String mailInterne) {
        this.mailInterne = mailInterne;
    }

    public String getNomAgent() {
        return nomAgent;
    }

    public void setNomAgent(String nomAgent) {
        this.nomAgent = nomAgent;
    }

    public String getNomSH() {
        return nomSH;
    }

    public void setNomSH(String nomSH) {
        this.nomSH = nomSH;
    }

    public int getNumDemande() {
        return numDemande;
    }

    public void setNumDemande(int numDemande) {
        this.numDemande = numDemande;
    }

    public String getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(String dateDemande) {
        this.dateDemande = dateDemande;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    
    
    
    
    
    public void sendMailToAgent(String emailToAgent, String emailInterne, String mdp, String smtpHost, String smtpPort) throws Exception{
        System.out.println("Preparation à l'envoie d'un Message");
        ResultSet rs = null;
       
        Properties properties = new Properties();
        
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.port", smtpPort);
        
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                
                return new PasswordAuthentication(emailInterne, mdp);
            }
        });
        
        
        Message message = prepareMailToAgent(session, emailInterne, emailToAgent);
        
        Transport.send(message);
        System.out.println("Message Envoyé avec Succés!!!");
    }
    
    
    private Message prepareMailToAgent(Session session, String emailInterne, String emailToAgent) {
        
       
        String subject = "Notification de la demande d'absence N° " + this.getNumDemande();
        String text = "Bonjour "+this.getNomAgent()+" \n\n"
                + " Votre demande d'absence "+this.typeConges+" du "+this.getDateDebut() +" au "+this.getDateFin() +" est bien prise en compte à la date du "+this.getDateDemande()+ "." +" \n \n"
                + "La demande est en attente de validation par votre supérieur hiérarchique "+this.getNomSH();
         try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailInterne));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(emailToAgent));
            message.setSubject(subject);
            message.setText(text);
            
            return message;
            
        } catch (Exception ex) {
            Logger.getLogger(JMailNotifDemAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;   
    }
    
    
 /********************************************************************************************
 *
 * 
 * 
  *******************************************************************************************/   
    public void sendMailToSH(String emailToSH, String emailInterne, String mdp, String smtpHost, String smtpPort) throws Exception{
        System.out.println("Preparation à l'envoie d'un Message");
        Properties properties = new Properties();
        
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.port", smtpPort);
        
        
        //String monCompte = "techimmosarl@gmail.com";
        //String mdp = "OUakam2020";
        
       
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(emailInterne, mdp);
            }
        });
        
        
        Message message = prepareMailToSH(session, emailInterne, emailToSH);
        
        Transport.send(message);
        System.out.println("Message Envoyé avec Succés!!!");
    }
    
    
    private Message prepareMailToSH(Session session, String emailInterne, String emailToSH) {
        
       
        String subject = "Notification de la demande d'absence N° " + this.getNumDemande();
        String text = "Bonjour "+this.getNomSH()+" \n\n"
                + " Une absence "+this.typeConges+" est demandée par votre Agent "+this.getNomAgent()+" pour la période du "+this.getDateDebut() +" au "+this.getDateFin()+ "." +" \n \n"
                + "La demande est envoyée le "+this.getDateDemande()+" et elle est en attente de votre appréciation";
         try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailInterne));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(emailToSH));
            message.setSubject(subject);
            message.setText(text);
            
            return message;
            
        } catch (Exception ex) {
            Logger.getLogger(JMailNotifDemAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;   
    }
    
    
}
    
    
    
    

