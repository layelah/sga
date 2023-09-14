package com.cmu.agence.rh.domaine;

import com.cmu.base.domaine.BaseLib;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "parametrage_mail")
public class ParametreMail extends BaseLib {

   
    @Basic(optional = false)
    @NotNull
    @Column(name = "mail_notification")
    private String mailNotification;
    @Column(name = "mot_de_passe")
    private String mdp;
    @Column(name = "type")
    private String type;
    @Column(name = "smtp_host")
    private String smtpHost;
    @Column(name = "smtp_port")
    private String smtpPort;
    

    public ParametreMail() {
        
    }

    

    public String getMail() {
        return mailNotification;
    }

    public void setMail(String mailNotification) {
        this.mailNotification = mailNotification;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
    
    
    
    
}
