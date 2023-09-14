package com.cmu.sigicmu.admin.bean;

import com.cmu.base.bean.BaseCheckError;
import com.cmu.base.bean.JsfUtil;
import com.cmu.base.service.exception.EchecSelectException;
import com.cmu.sigicmu.admin.domaine.Utilisateur;
import com.cmu.sigicmu.admin.service.UtilisateurService;

public class UserCheckError extends BaseCheckError {

    private Utilisateur user;
    private UtilisateurService srv;
    
    public UserCheckError(Utilisateur user, UtilisateurService srv) {
        this.user = user;
        this.srv = srv;
    }
    public boolean sansErreur(String newMdp, String newMdpBis, Boolean isModif) throws EchecSelectException {
        boolean bSansErreur = super.sansErreur();

        // Contrôle d'existence du login
        if (srv.existeLogin(user)) {
            bSansErreur = false;
            JsfUtil.addErrorMessage("login", "le login doit être unique");
        }

        // Contrôle sur la longueur du login
        bSansErreur = bSansErreur && super.checkLongueurMin("login", user.getLogin(), 8);

        if (!isModif) {
            // Conrôle sur le mot de passe
            bSansErreur = bSansErreur && yaErreurAvecNouveauMdp(user, newMdp, newMdpBis, isModif);
        }

        return bSansErreur;
    }

    public boolean pasMdpUtilisateur(Utilisateur user, String mdp) {
        if (!user.getMotDePasse().equals(mdp)) {
            JsfUtil.addErrorMessage("form:mdp", "Le mot de passe n'est pas celui de l'utilisateur");
            return true;
        }

        return false;
    }

    public boolean yaErreurAvecNouveauMdp(Utilisateur user, String newMdp, String newMdpBis, boolean isModif) {
        if (isModif && user.getMotDePasse().equals(newMdp)) {
            JsfUtil.addErrorMessage("Le nouveau mot de passe doit être différent de l'ancien");
            return true;
        }

        if (!newMdpBis.equals(newMdp)) {
            JsfUtil.addErrorMessage("Les deux mots de passe doivent être identiques");
            return true;
        }

        if (!srv.estValiditeMdp(newMdp)) {
            JsfUtil.addErrorMessage("Le nouveau mot de passe doit au moins être égal à 8 caractères");
            return true;
        }
        
        return false;
    }
}
