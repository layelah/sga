package com.cmu.sigicmu.bureau.service;

import com.cmu.base.service.BaseService;
import com.cmu.base.service.exception.EchecSelectException;
import com.cmu.base.service.exception.EchecUniqueSelectException;
import com.cmu.base.service.exception.CMUServiceException;
import com.cmu.sigicmu.admin.domaine.EtatCompteEnum;
import com.cmu.util.JIDate;
import com.cmu.sigicmu.admin.domaine.Utilisateur;
import com.cmu.sigicmu.param.service.TableValeurService;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
@LocalBean
public class ConnexionService extends BaseService {

    @Inject
    TableValeurService tvSrv;
    
    public ConnexionService() {
    }

    public String verifierConnexion(String login, String mdp) throws EchecSelectException, CMUServiceException {
        Utilisateur user;
        String sResult = null;

        // 1 - Réupération de l'utilisateur à partir du login
        user = getUser(login);
        if (user == null) {
            return "Le login ou le mot de passe est incorrect";
        }

        // 2 - Vérification de la validité de l'état du compte utilisateur
        if (user.etatInconnu()) {
            return "Le compte utilisateur est dans un état inconnu du système";
        }

        // 3 - Vérification que le compte n'est pas INACTIF
        if (user.estInactif()) {
            return "Le compte utilisateur n'est pas inatif";
        }

        // 4 - Vérification que le compte n'est pas BLOQUE
        if (user.estBloque()) {
            return "Compte utilisateur bloqué (" + user.getRaisonBlocage() + ")";
        }

        // 5 - Vérification que le mot de passe correspond
        if (!user.getMotDePasse().equals(mdp)) {
            sResult = "Le login ou le mot de passe est incorrect";

            // 5.1 - Vérification que le nombre d'essai de connexion n'est pas épuisé
            if (user.getNbEssaiConnexion() < 5) {
                incrementerNombreEssai(user);
            }

            // 5.2 - Blocage du compte utilisateur
            if (5 <= user.getNbEssaiConnexion()) {
                bloquerUtilisateur(user, "Epuisement du nombre d'essais de connexion");
            }

            update(user);
        }

        return sResult;
    }

    public void resetConnexion(Utilisateur user, String ipAdresse) throws EchecSelectException, CMUServiceException {
        user.setNbEssaiConnexion(0);
        user.setIpDerniereConnexion(ipAdresse);
        user.setDateDerniereConnexion(JIDate.dateHeure());

        if (user.getDatePremiereConnexion() == null) {
            user.setDatePremiereConnexion(user.getDateDerniereConnexion());
        }

        if ((user.getDoitChangerSonMdp() != Boolean.TRUE) && (user.getDateChangementMdp() != null) && (JIDate.avantOuEgaleAujourdhui(user.getDateChangementMdp()))) {
            user.setDoitChangerSonMdp(Boolean.TRUE);
        }

        user = (Utilisateur) update(user);
    }

    public Utilisateur getUser(String login, String mdp) throws EchecSelectException {
        Utilisateur user = getUser(login);

        if ((user != null) && (user.getMotDePasse().equals(mdp))) {
            return user;
        }

        return null;
    }

    public void saveDeconnexion(Utilisateur user) throws CMUServiceException {
        user = (Utilisateur) getReference(user);
        user.setDateDerniereDeconnexion(JIDate.dateHeure());
        user = (Utilisateur) update(user);
        //refresh(user);
    }

    private void incrementerNombreEssai(Utilisateur user) {
        user.setNbEssaiConnexion(user.getNbEssaiConnexion() + 1);
    }

    private void bloquerUtilisateur(Utilisateur user, String raisonBlocage) throws EchecSelectException {
        user.setEtatCompte(tvSrv.getRef(1, EtatCompteEnum.BLOQUE.toString()));
        user.setRaisonBlocage(raisonBlocage);
    }

    public Utilisateur getUser(String login) throws EchecSelectException {
        try {
            return (Utilisateur) uneEntiteFiltree(new Utilisateur(), "login", login);
        } catch (EchecUniqueSelectException ex) {
            return null;
        }
    }
}
