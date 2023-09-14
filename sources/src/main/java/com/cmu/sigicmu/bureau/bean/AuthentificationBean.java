package com.cmu.sigicmu.bureau.bean;

import com.cmu.base.bean.BaseBean;
import com.cmu.base.bean.JsfUtil;
import com.cmu.base.service.exception.CMUServiceException;
import com.cmu.base.service.exception.EchecSelectException;
import com.cmu.sigicmu.admin.bean.AgentBean;
import com.cmu.sigicmu.admin.bean.UserCheckError;
import com.cmu.sigicmu.admin.domaine.Entite;
import com.cmu.sigicmu.admin.domaine.Utilisateur;
import com.cmu.sigicmu.admin.service.AgentService;
import com.cmu.sigicmu.admin.service.UtilisateurService;
import com.cmu.sigicmu.bureau.service.ConnexionService;
import java.util.Date;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.log4j.Logger;

@Named("auth")
@SessionScoped
public class AuthentificationBean extends BaseBean {

    private static final Logger log = Logger.getLogger(AuthentificationBean.class);

    private String mdp;
    private String passAChanger;
    private String newMdp;
    private String newMdpBis;
    private String login;
    private Utilisateur user;
    private Entite entiteEncours;
    private String emailSaisi;
    private String reinitpwd, reinitpwdBis;

    @Inject
    private AgentBean agentBean;

    
    @Inject
    private AgentService agtSrv;

    @Inject
    private ConnexionService srv;

    
    @Inject
    private UtilisateurService userSrv;

    // Constructeurs
    public AuthentificationBean() {
    }

    // getters et setters
    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getPassAChanger() {
        return passAChanger;
    }

    public void setPassAChanger(String passAChanger) {
        this.passAChanger = passAChanger;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNewMdp() {
        return newMdp;
    }

    public void setNewMdp(String newMdp) {
        this.newMdp = newMdp;
    }

    public String getNewMdpBis() {
        return newMdpBis;
    }

    public void setNewMdpBis(String newMdpBis) {
        this.newMdpBis = newMdpBis;
    }

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    public Entite getSessionEntiteEnCours() {
        return entiteEncours;
    }

    public String getAgence() {
        if (user != null && user.getAgent().getEntite() != null) {
            return user.getAgent().getEntite().getCode() + " " + user.getAgent().getEntite().getLib();
        }
        return "(ND)";
    }

    public String getBureauCode() {
        if (user != null && user.getAgent().getEntite() != null) {
            return user.getAgent().getEntite().getCode();
        }
        return "(ND)";
    }

    public String getService() {
        if (user != null && user.getAgent().getService() != null) {
            return user.getAgent().getService().getLib() + " (" + user.getAgent().getService().getCode() + ")";
        }
        return "(ND)";
    }

    public String getSessUserName() {
        if (user != null) {
            return user.getNomComplet();
        }
        return "";
    }

    public String getListEntitesVisibles() {
        if (user != null) {
            return "(" + getSessIdAgenceEnCours() + ")";
        }
        return "(0)";

    }

    public Integer getSessUserId() {
        if (user != null) {
            return user.getId();
        }
        return 0;
    }

    public Integer getSessAgentId() {
        if (user != null) {
            return user.getAgent().getId();
        }
        return 0;
    }

    public String getSessSuperviseur() {
        if (user != null && user.getAgent().getSuperviseur() != null) {
            return user.getNomComplet();
        }
        return "";
    }

    public Date getSessDebut() {
        if (user != null) {
            return user.getDateDerniereConnexion();
        }
        return null;
    }

    public Date getSessDateLAstConn() {
        if (user != null) {
            return user.getDateDerniereDeconnexion();
        }
        return null;
    }

    public String getSessAgenceEnCours() {
        if (entiteEncours != null) {
            return entiteEncours.getCode() + " " + entiteEncours.getLib();
        }
        return "";
    }

    public Integer getSessIdAgenceEnCours() {
        if (entiteEncours != null) {
            return entiteEncours.getId();
        }
        return 0;
    }

    public boolean getEstUserCourant(Integer id) {
        return (getSessUserId().intValue() == id.intValue());
    }
    
    
    public String goMdpForget() {
       String sResult;

        try {
            // 1 - Vérification de la connexion
            sResult = srv.verifierConnexion(login, mdp);

            if ((sResult == null) || (sResult.length() == 0)) {
                // Il n'y a pas d'erreur
            } else {
                System.out.println("2");
                JsfUtil.addErrorMessage(sResult);
                return null;
            }

            // 2 - Récupération de l'utilisateur
            user = srv.getUser(login, mdp);
            if (user != null) {
                entiteEncours = user.getAgent().getEntite();
            }

            //3 - Traiement particulier pour la première connexion
            //bPremiereConnexion = (user.getDateDerniereConnexion() == null);
            // 4 - Reset des données de connexion
            srv.resetConnexion(user, JsfUtil.getIpAddress());

            agentBean.setAgent(user.getAgent());
            
        } catch (CMUServiceException ex) {
            JsfUtil.addErrorMessage("impossible de se connecter");
            return null;
        } catch (Exception ex) {
            JsfUtil.addErrorMessage("Impossible de se connecter");
            return null;
        }

        System.out.println("La fonction de l'agent est: "+user.getAgent().getFonction().getCode());
        return "/RH/fichePersonnelle.xhtml";
    
    }

    public String connecter() {
        String sResult;

        try {
            // 1 - Vérification de la connexion
            sResult = srv.verifierConnexion(login, mdp);

            if ((sResult == null) || (sResult.length() == 0)) {
                // Il n'y a pas d'erreur
            } else {
                System.out.println("2");
                JsfUtil.addErrorMessage(sResult);
                return null;
            }

            // 2 - Récupération de l'utilisateur
            user = srv.getUser(login, mdp);
            if (user != null) {
                entiteEncours = user.getAgent().getEntite();
            }

            //3 - Traiement particulier pour la première connexion
            //bPremiereConnexion = (user.getDateDerniereConnexion() == null);
            // 4 - Reset des données de connexion
            srv.resetConnexion(user, JsfUtil.getIpAddress());

            agentBean.setAgent(user.getAgent());
            
        } catch (CMUServiceException ex) {
            JsfUtil.addErrorMessage("impossible de se connecter");
            return null;
        } catch (Exception ex) {
            JsfUtil.addErrorMessage("Impossible de se connecter");
            return null;
        }

        System.out.println("La fonction de l'agent est: "+user.getAgent().getFonction().getCode());
        return "/RH/fichePersonnelle.xhtml";
    }


    public String deconnecter() {
        System.out.println("com.cmu.sigicmu.bureau.bean.AuthentificationBean.deconnecter()");

        try {
            srv.saveDeconnexion(user);
            this.login = "";
            this.mdp = "";
        } catch (CMUServiceException ex) {
            //JsfUtil.addErrorMessage("Impossible de fermer la session utilisateur");
            //return null;
        }

        this.login = "";
        this.mdp = "";
        JsfUtil.finDeSession();

        return "/BUR/connexion.xhtml?faces-redirect=true;";
    }


    public String changerInfoUser() {
//        
//        if (chk.pasMdpUtilisateur(user, mdp)) {
//            return null;
//        } else if(chk.yaErreurAvecNouveauMdp(user, newMdp, newMdpBis, true)) {
//            return null;
//        }

        try {
            //userSrv.save(user);
            System.out.println("******************************");
            agtSrv.save(user.getAgent());
        } catch (CMUServiceException ex) {
            JsfUtil.addExceptionMessage("Impossible d'enregistrer", ex);
            return null;
        }

        JsfUtil.finDeSession();
        return "/BUR/dashboard.xhtml";
    }

    public boolean estAuthentifie() {
        return (user != null);
    }

    public Utilisateur srvCurrentUser() {
        return getUser();
    }

    public Integer srvCurrentUserId() {
        return getSessUserId();
    }

    public Integer srvCurrentAgentId() {
        return getSessAgentId();
    }

    public Entite srvCurrentEntite() {
        return getSessionEntiteEnCours();
    }

    public String getEmailSaisi() {
        return emailSaisi;
    }

    public void setEmailSaisi(String emailSaisi) {
        this.emailSaisi = emailSaisi;
    }

    public String getReinitpwd() {
        return reinitpwd;
    }

    public void setReinitpwd(String reinitpwd) {
        this.reinitpwd = reinitpwd;
    }

    public String getReinitpwdBis() {
        return reinitpwdBis;
    }

    public void setReinitpwdBis(String reinitpwdBis) {
        this.reinitpwdBis = reinitpwdBis;
    }
    
    public String changerMotDePasse()  {
        UserCheckError chk = new UserCheckError(user, userSrv);

        if (chk.pasMdpUtilisateur(user, passAChanger)) {
            return null;
        } else if (chk.yaErreurAvecNouveauMdp(user, newMdp, newMdpBis, true)) {
            return null;
        }

        try {
            user = (Utilisateur)srv.getEntite(user, user.getId());
            user.setMotDePasse(newMdp);
            user = (Utilisateur)srv.save(user);
            
        } catch (CMUServiceException ex) {
            JsfUtil.addExceptionMessage("Impossible de changer le mot de passe", ex);
            return null;
        }

        JsfUtil.finDeSession();
        return "/BUR/connexion.xhtml";
    }

    private void initObj() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
