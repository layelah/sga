/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tutorialsjava;

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
public class JavaMailUtil {
    
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
            Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;   
    }
    
    
    
    
    
    
}
