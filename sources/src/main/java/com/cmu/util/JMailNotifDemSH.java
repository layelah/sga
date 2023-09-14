/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmu.util;

import java.util.Date;
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
public class JMailNotifDemSH {
    
     private String mailSH;
    private String mailInterne;
    private String nomAgent;
    private String nomSH;
    private int numDemande;
    private Date dateDemande;
    private Date dateDebut;
    private Date dateFin;
    
    /*COnstrutors*/

    public JMailNotifDemSH() {
    }

    public JMailNotifDemSH(String mailSH, String mailInterne, String nomAgent, String nomSH, int numDemande, Date dateDemande, Date dateDebut, Date dateFin) {
        this.mailSH = mailSH;
        this.mailInterne = mailInterne;
        this.nomAgent = nomAgent;
        this.nomSH = nomSH;
        this.numDemande = numDemande;
        this.dateDemande = dateDemande;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }
    
    
    
    /*Getters and Setters */

    public String getMailSH() {
        return mailSH;
    }

    public void setMailSH(String mailSH) {
        this.mailSH = mailSH;
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

    public Date getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(Date dateDemande) {
        this.dateDemande = dateDemande;
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
    
    
    public static void sendMail(String destinataire) throws Exception{
        System.out.println("Preparation à l'envoie d'un Message");
        Properties properties = new Properties();
        
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        
        
        String monCompte = "techimmosarl@gmail.com";
        String mdp = "OUakam2020";
        
       
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(monCompte, mdp);
            }
        });
        
        
        Message message = prepareMessage(session, monCompte, destinataire);
        
        Transport.send(message);
        System.out.println("Message Envoyé avec Succés!!!");
    }

    private static Message prepareMessage(Session session, String monCompte, String destinataire) {
        
         try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(monCompte));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(destinataire));
            message.setSubject("First Mail sended from JAVA");
            message.setText("Hey \n Check my email box");
            
            return message;
            
        } catch (Exception ex) {
            Logger.getLogger(JMailNotifDemSH.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;   
    }
    
    
    
    
    
    
}
