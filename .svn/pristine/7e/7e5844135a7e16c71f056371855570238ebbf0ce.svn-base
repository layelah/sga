package com.cmu.util;

import com.cmu.sigicmu.admin.domaine.Agent;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EnvoiMail {

    private Agent agent;
   
   public static void sendMessage(String sujet, String contenu, String destinataire) {
   
    // 1 -> Création de la session
    Properties properties = new Properties();
    properties.setProperty("mail.transport.protocol", "smtp");
    properties.setProperty("mail.smtp.host","extranet.gouv.sn");
    properties.setProperty("mail.smtp.user","Babacar" );
    properties.setProperty("mail.from", "babacar.sow@agencecmu.sn");
  
    Session session = Session.getInstance(properties);
    
    //creation du message
  
 MimeMessage message = new MimeMessage(session);
    try {
        
        message.setText(contenu);
        message.setSubject(sujet);
        message.addRecipients(Message.RecipientType.TO, destinataire);
       
    } catch (MessagingException e) {
    }

   //envoi du message

     Transport transport = null;
    try {
        transport = session.getTransport("smtp");
        transport.connect("Babacar", "Azertui");
        transport.sendMessage(message, new Address[] { new InternetAddress(destinataire),
                                                        });
      
    } catch (MessagingException e) {
    } finally {
        try {
            if (transport != null) {
                transport.close();
            }
        } catch (MessagingException e) {
        }
    }
}
 

}
