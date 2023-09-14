package com.cmu.sigicmu.bureau.service;

import com.cmu.sigicmu.admin.domaine.Agent;
import com.cmu.sigicmu.admin.domaine.Entite;
import com.cmu.sigicmu.admin.domaine.Utilisateur;
import com.cmu.sigicmu.bureau.bean.AuthentificationBean;
import com.cmu.sigicmu.param.domaine.TableValeur;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

@Stateless
@LocalBean
public class SessionService {
    
    @Inject
    @Named("auth")
    private AuthentificationBean auth;
    
    public Utilisateur srvCurrentUser() {
        if (auth != null) {
            return auth.getUser();
        }
        return null;
    }

    public Agent currentAgent() {
        if (auth != null) {
            return auth.getUser().getAgent();
        }
        return null;
    }
    
    public Entite currentEntite() {
        if (auth != null) {
            return auth.getUser().getAgent().getEntite();
        }
        return null;
    }
    
    public TableValeur userChampIntervention() {
        if (auth != null) {
            return auth.getUser().getAgent().getEntite().getChampIntervention();
        }
        return null;
    }
    
    public String srvEntitesVisibles() {
        if (auth != null) {
            return auth.getListEntitesVisibles();
        }
        return "(0)";
    }

    public Integer srvCurrentUserId() {
        if (auth != null) {
            return auth.getSessUserId();
        }
        return 0;
    }

    public Integer srvCurrentAgentId() {
        if (auth != null) {
            return auth.getSessAgentId();
        }
        return 0;
    }

    public Entite srvCurrentEntite() {
        if (auth != null) {
            return auth.getSessionEntiteEnCours();
        }
        return null;
    }

    public Integer srvCurrentEntiteId() {
        if (auth != null) {
            return auth.getSessIdAgenceEnCours();
        }
        return 0;
    }
}
