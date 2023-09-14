/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmu.mail;

import com.cmu.util.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class JMailRefDemDG {
    
    private String typeConges;
    private String mailRH;
    private String mailSH;
    private String mailAgent;
    private String mailInterne;
    private String nomAgent;
    private String nomSH;
    private int numDemande;
    private String dateRefus;
    private String dateDebut;
    private String dateFin;
    
    
    
    

/* COnstrutor */

    public JMailRefDemDG(String typeConges, String mailRH, String mailSH, String mailAgent, String mailInterne, String nomAgent, String nomSH, int numDemande, String dateValidation, String dateDebut, String dateFin) {
        this.typeConges = typeConges;
      
        this.mailRH = mailRH;
        this.mailSH = mailSH;
        this.mailAgent = mailAgent;
        this.mailInterne = mailInterne;
        this.nomAgent = nomAgent;
        this.nomSH = nomSH;
        this.numDemande = numDemande;
        this.dateRefus = dateRefus;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public JMailRefDemDG() {
    }

    /* Getters and Setters*/
    
    public String getTypeConges() {    
        return typeConges;
    }


    public void setTypeConges(String typeConges) {
        this.typeConges = typeConges;
    }
    
    public String getMailRH() {
        return mailRH;
    }

    public void setMailRH(String mailRH) {
        this.mailRH = mailRH;
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

    public String getDateRefus() {
        return dateRefus;
    }

    public void setDateValidation(String dateRefus) {
        this.dateRefus = dateRefus;
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

    /********************************************************************************************
 *Préparation et Envoie du Mail de refus de la demande par le Directeur Général
 * Destinataire: Agent Demandeur
 * 
  ********************************************************************************************/
  
    public void sendMailToAgent(String emailAgent, String emailInterne, String mdp, String smtpHost, String smtpPort) throws Exception{
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
        
      Message message = prepareMailToAgent(session, emailInterne, emailAgent);
        
        
        
        Transport.send(message);
        System.out.println("Message Envoyé avec Succés!!!");
    }
    
   /********************************************************************************************************/ 
    
    private Message prepareMailToAgent(Session session, String emailInterne, String emailToAgent) {
        
       
        String subject = "Refus du Directeur Général de la demande d'absence N° " + this.getNumDemande();
        String text = "Bonjour "+this.getNomAgent()+" \n\n"
                + " Votre demande d'absence "+this.typeConges+" du "+this.getDateDebut() +" au "+this.getDateFin() +" a été refusée à la date du "+this.getDateRefus()+ " par le Directeur Général." +" \n \n"
                + "Cordialement";
         try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailInterne));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(emailToAgent));
            message.setSubject(subject);
            message.setText(text);
            
            return message;
            
        } catch (Exception ex) {
            Logger.getLogger(JMailRefDemDG.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;   
    }
    
    
 /********************************************************************************************
 *Préparation et Envoie du Mail de refus de la demande par le Directeur Général
 * Destinataire: Supérieur Hierarchique de l'Agent
 * 
  ********************************************************************************************/
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
    /********************************************************************************************/
    private Message prepareMailToSH(Session session, String emailInterne, String emailSH) {
        
       
        String subject = "Refus du Directeur Général de la demande d'absence N° " + this.getNumDemande();
        String text = "Bonjour "+this.getNomSH()+" \n\n"
                + " La demande d'absence "+this.typeConges+" de votre Agent "+this.getNomAgent()+" pour la période du "+this.getDateDebut() +" au "+this.getDateFin() +" a été refusée à la date du "+this.getDateRefus()+ " par le Directeur Général." +" \n \n"
                + "Cordialement";
         try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailInterne));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(emailSH));
            message.setSubject(subject);
            message.setText(text);
            
            return message;
            
        } catch (Exception ex) {
            Logger.getLogger(JMailRefDemDG.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;   
    }
    
    /********************************************************************************************
 *  Préparation et Envoie du Mail de refus de la demande par le Directeur Général
 * Destinataire: Service des Ressources Humaines
 * 
  ********************************************************************************************/
    public void sendMailToRH(String emailToRH, String emailInterne, String mdp, String smtpHost, String smtpPort) throws Exception{
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
        
        
        Message message = prepareMailToRH(session, emailInterne, emailToRH);
        
        Transport.send(message);
        System.out.println("Message Envoyé avec Succés!!!");
    }
   
  /********************************************************************************************/
    private Message prepareMailToRH(Session session, String emailInterne, String emailRH) {
        
       
        String subject = "Refus de la demande d'absence N° " + this.getNumDemande();
        String text = "Bonjour  \n\n"
                + " La demande d'absence "+this.typeConges+" de l'Agent "+this.getNomAgent()+" pour la période du "+this.getDateDebut() +" au "+this.getDateFin() +" a été refusée à la date du "+this.getDateRefus()+ " par le Directeur Général." +" \n \n"
                
                + "Cordialement";
                
         try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailInterne));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(emailRH));
            message.setSubject(subject);
            message.setText(text);
            
            return message;
            
        } catch (Exception ex) {
            Logger.getLogger(JMailRefDemDG.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;   
    }
    
    
    
}
    
    
