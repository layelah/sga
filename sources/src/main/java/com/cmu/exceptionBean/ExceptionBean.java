package com.cmu.exceptionBean;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named("exceptionBean")
@SessionScoped
public class ExceptionBean implements Serializable{

    public String fermer(){
        // On ferme la session de l'utilisateur ici
        return "/BUR/connexion.xhtml?faces-redirect=true";
    }
  
}
