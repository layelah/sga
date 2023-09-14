package com.cmu.agence.rh.service;

import com.cmu.agence.rh.domaine.Absence;
import com.cmu.agence.rh.domaine.AbsenceEnum;
import com.cmu.agence.rh.domaine.DemandePermissionAbsence;
import com.cmu.agence.rh.domaine.EtatAbsenceEnum;
import com.cmu.agence.rh.domaine.EtatValidationEnum;
import com.cmu.agence.rh.domaine.NiveauValidationDemandeEnum;
import com.cmu.agence.rh.domaine.SoldeConges;
import com.cmu.agence.rh.domaine.ValidationDemande;
import com.cmu.base.service.BaseService;
import com.cmu.base.service.exception.EchecSelectException;
import com.cmu.sigicmu.admin.domaine.Agent;
import com.cmu.sigicmu.admin.domaine.FonctionEnum;
import com.cmu.util.JIDate;
import com.cmu.util.JUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

@Stateless
@LocalBean
public class AbsenceService extends BaseService {

    public ValidationDemande getNextValidation(int demandeId) {
        JUtil.s("getNextValidation demandeId", demandeId );
        
        String sql = "SELECT o "
            + " FROM ValidationDemande as o "
            + " WHERE o.demandePermissionAbsence.id = :demandeId "
            + "     AND o.etat.code = :etatCode "
             + "    AND o.niveauValidation.code = :niveauValidation"     ;

        Query q = getEm().createQuery(sql);
        q.setParameter("demandeId", demandeId);
        q.setParameter("etatCode", EtatValidationEnum.Encours.toString());

        return (ValidationDemande) q.setMaxResults(1).getSingleResult();
    }
    
    public List<Absence> getEtatAbsence(Agent agent) {

        String sql = "SELECT o "
                + " FROM Absence as o "
                + " WHERE (o.etat.code = :etatCode1 OR o.etat.code = :etatCode2)"
                + " ORDER BY o.dateDebutAbsence DESC";
        
        /* Ici on a modifié la requete pour la liste des absences
        soit accessible à tous les agents
        */
        /*if (!agent.estRH()) {
            sql += "     AND o.agent.superviseur.id = :agentId ";
        }
        sql += " ORDER BY o.dateDebutAbsence";
       
        Query q = getEm().createQuery(sql);
        if (!agent.estRH()) {
            q.setParameter("agentId", agent.getId());
        } */   
        Query q = getEm().createQuery(sql);
        q.setParameter("etatCode1", EtatAbsenceEnum.Acceptee.toString());     
        q.setParameter("etatCode2", EtatAbsenceEnum.Effective.toString());     
        return (List<Absence>)q.getResultList();
    }
    
    /********************************************************************/
    
    public List<Absence> getEtatAbsenceNew(Agent agent) {

        String sql = "SELECT o "
                + " FROM Absence as o "
                + " WHERE o.agent.service.id = :serviceId"
                + " and (o.etat.code = :etatCode1 OR o.etat.code = :etatCode2)";
       
        sql += " ORDER BY o.dateDebutAbsence";
       
        Query q = getEm().createQuery(sql);
        
        q.setParameter("serviceId", agent.getService().getId());
        q.setParameter("etatCode1", EtatAbsenceEnum.Acceptee.toString());     
        q.setParameter("etatCode2", EtatAbsenceEnum.Effective.toString());     
        return (List<Absence>)q.getResultList();
    }
    /*********************************************************************/
    
    // pour recuperer l historique des absences pour un agent
    public List<Absence> getPlanningAbsence() {

        String sql = "SELECT o "
                + " FROM Absence as o "
                + " WHERE o.agent.id = :agentId "
                + " ORDER BY o.dateDebut";
       
        Query q = getEm().createQuery(sql);
     
        return (List<Absence>)q.getResultList();
    }
    // pour recuperer l historique des absences pour un agent
    public List<Absence> getListAbsence(Agent agent) {

        String sql = "SELECT o "
            + " FROM Absence as o "
            + " WHERE o.agent.id = :agentId "
            
           
            + " ORDER BY o.dateDebutAbsence DESC";

        Query q = getEm().createQuery(sql);
        q.setParameter("agentId", agent.getId());
        

        return (List<Absence>)q.getResultList();
    }

    public List getListSolde(Agent agent) {
        try {
            String sql = "select o "
                    + " from SoldeConges as o "
                    + " where o.agent.id = :agentId";
            //+ "group by anneeId";
            Query q = getEm().createQuery(sql);
            q.setParameter("agentId", agent.getId());

            return q.getResultList();

        } catch (NoResultException e) {
            JUtil.s("getSolde ERREUR = " + e);
            return null;
        }
    }

    public Integer getNbJourFerie(Date dateDebut, Date dateFin) {
        if (JIDate.estVide(dateDebut) || JIDate.estVide(dateFin)) {
            return 0;
        }
        String sql = "SELECT count(o)"
                + " FROM JourFerie as o"
                + " WHERE (o.jour BETWEEN :dateDebut AND :dateFin)";
        Query q = getEm().createQuery(sql);
        q.setParameter("dateDebut", JIDate.dateSansHeure(dateDebut));
        q.setParameter("dateFin", JIDate.dateSansHeure(dateFin));
        return ((Long) q.getSingleResult()).intValue();
    }

    public List listDemAbsAValider() throws EchecSelectException {

        try {
            String sql = "select o from DemandePermissionAbsence as o"
                    + " where o.etat.code = :etatCode"
                    + " order by o.dateDemande DESC";

            Query q = getEm().createQuery(sql);
            q.setParameter("etatCode", EtatValidationEnum.Encours.toString());
            return q.getResultList();

        } catch (Exception ex2) {
            throw new EchecSelectException(ex2);
        }
    }
    
    //Demande a valider par le chef de division
    
    public List<DemandePermissionAbsence> listDemAbsAValiderParSH(int agentId) throws EchecSelectException {

        try {
            String sql = "select DISTINCT v.demandePermissionAbsence "
                    + " from Absence a, ValidationDemande v, DemandePermissionAbsence d "
                    + " where v.demandePermissionAbsence NOT IN"
                    + " (select DISTINCT e.demandePermissionAbsence"
                    + " from Absence b, ValidationDemande e, DemandePermissionAbsence f "
                    + " where f.id = e.demandePermissionAbsence.id"                   
                    + " AND b.id = f.absence.id"  
                    + " and b.agent.superviseur.id = :agentId"
                    + " AND e.niveauValidation.code = :niveauValidation1)"
                    
                    + " and d.id = v.demandePermissionAbsence.id"
                    + " AND a.id = d.absence.id"  
                    + " and a.agent.superviseur.id = :agentId"
      
                    + " AND v.niveauValidation.code = :niveauValidation2"
                    + " AND d.etat.code = :etatCode" 
                    + " order by d.dateDemande";

            Query q = getEm().createQuery(sql);
            q.setParameter("agentId", agentId);
            q.setParameter("etatCode", EtatValidationEnum.Encours.toString());
            q.setParameter("niveauValidation1", NiveauValidationDemandeEnum.ValidationRH.toString());
            q.setParameter("niveauValidation2", NiveauValidationDemandeEnum.ValidationSH.toString());
            
            System.out.println("Taille de la liste"+ q.getResultList().size());
            

            return (List<DemandePermissionAbsence>) q.getResultList();

        } catch (Exception ex2) {
            throw new EchecSelectException(ex2);
        }
    }
//

    public List<DemandePermissionAbsence> listDemAbsAValiderParCD(int agentId) throws EchecSelectException {

        try {
            String sql = "select DISTINCT v.demandePermissionAbsence "
                    + " from Absence a, ValidationDemande v, DemandePermissionAbsence d "
                    + " where v.demandePermissionAbsence NOT IN"
                    + " (select DISTINCT e.demandePermissionAbsence"
                    + " from Absence b, ValidationDemande e, DemandePermissionAbsence f "
                    + " where f.id = e.demandePermissionAbsence.id"                   
                    + " AND b.id = f.absence.id"  
                    + " and b.agent.fonction.id = :agentId"
                    + " and b.agent.superviseur.id = :agentId"
                    + " AND e.niveauValidation.code = :niveauValidation1)"
                    
                    + " and d.id = v.demandePermissionAbsence.id"
                    + " AND a.id = d.absence.id"  
                    + " and a.agent.superviseur.id = :agentId"
      
                    + " AND v.niveauValidation.code = :niveauValidation2"
                    + " AND d.etat.code = :etatCode" 
                    + " order by d.dateDemande";

            Query q = getEm().createQuery(sql);
            q.setParameter("agentId", agentId);
            q.setParameter("fonction", FonctionEnum.ChefBureau.toString());
            q.setParameter("etatCode", EtatValidationEnum.Encours.toString());
            q.setParameter("niveauValidation1", NiveauValidationDemandeEnum.ValidationRH.toString());
            q.setParameter("niveauValidation2", NiveauValidationDemandeEnum.ValidationSH.toString());
            
            System.out.println("Taille de la liste"+ q.getResultList().size());
            

            return (List<DemandePermissionAbsence>) q.getResultList();

        } catch (Exception ex2) {
            throw new EchecSelectException(ex2);
        }
    }
//
    public List<DemandePermissionAbsence> listDemAbsAValiderParRH() throws EchecSelectException {

        try {
            String sql = "select DISTINCT v.demandePermissionAbsence "
                    + " from Absence a, ValidationDemande v, DemandePermissionAbsence d "
                    + " where v.demandePermissionAbsence NOT IN"
                    + " (select DISTINCT e.demandePermissionAbsence"
                    + " from Absence b, ValidationDemande e, DemandePermissionAbsence f "
                    + " where f.id = e.demandePermissionAbsence.id"                   
                    + " AND b.id = f.absence.id"  
            
                    + " AND e.niveauValidation.code = :niveauValidation1)"
                    
                    + " and d.id = v.demandePermissionAbsence.id"
                    + " AND a.id = d.absence.id"  
                     + " AND d.etat.code = :etatCode"
                    + " AND v.niveauValidation.code = :niveauValidation2"
                    + " order by d.dateDemande";

            Query q = getEm().createQuery(sql);
                
            q.setParameter("niveauValidation1", NiveauValidationDemandeEnum.ValidationDG.toString());
            q.setParameter("niveauValidation2", NiveauValidationDemandeEnum.ValidationRH.toString());
            q.setParameter("etatCode", EtatValidationEnum.Encours.toString());
            
            System.out.println("Taille de la liste"+ q.getResultList().size());
            

            return (List<DemandePermissionAbsence>) q.getResultList();

        } catch (Exception ex2) {
            throw new EchecSelectException(ex2);
        }
    }

//    public List<DemandePermissionAbsence> listDemAbsAValiderParSH(int agentId) throws EchecSelectException {
//
//        try {
//            String sql = "select o.demandePermissionAbsence "
//                    + " from Absence as o, ValidationDemande as v, DemandePermissionAbsence d "
//                    + " where o.agent.superviseur.id = :agentId"
//                    + " AND d.id = v.demandePermissionAbsence.id"
//                     + " AND o.id = d.absence.id"
//                    + " AND v.niveauValidation.code = :niveauValidation" 
//                    + " and o.demandePermissionAbsence.etat.code = :etatCode"
//                    + " order by o.demandePermissionAbsence.dateDemande";
//
//            Query q = getEm().createQuery(sql);
//            q.setParameter("agentId", agentId);
//            q.setParameter("etatCode", EtatValidationEnum.Encours.toString());
//            q.setParameter("niveauValidation", NiveauValidationDemandeEnum.ValidationSH.toString());
//
//            return (List<DemandePermissionAbsence>) q.getResultList();
//
//        } catch (Exception ex2) {
//            throw new EchecSelectException(ex2);
//        }
//    }

//    public List<DemandePermissionAbsence> listDemAbsAValiderParRH() throws EchecSelectException {
//
//        try {
//            String sql = "select o.demandePermissionAbsence "
//                    + " from Absence as o, ValidationDemande as v"
//                    + " where o.demandePermissionAbsence.etat.code = :etatCode"
//                    + " AND o.id = v.demandePermissionAbsence.id"
//                    + " AND v.niveauValidation.code = :niveauValidation"
//                    + " order by o.demandePermissionAbsence.dateDemande";
//
//            Query q = getEm().createQuery(sql);
//            q.setParameter("etatCode", EtatValidationEnum.Encours.toString());
//            q.setParameter("niveauValidation", NiveauValidationDemandeEnum.ValidationRH.toString());
//            return (List<DemandePermissionAbsence>) q.getResultList();
//
//        } catch (Exception ex2) {
//            throw new EchecSelectException(ex2);
//        }
//    }
    
    public List<DemandePermissionAbsence> listDemAbsAValiderParDG() throws EchecSelectException {

        try {
            String sql = "select DISTINCT v.demandePermissionAbsence "
                    + " from Absence a, ValidationDemande v, DemandePermissionAbsence d "
                    + " where d.id = v.demandePermissionAbsence.id"
                    + " AND a.id = d.absence.id"  
                    + " AND v.statut = :statut"
                    + " AND d.etat.code = :etatCode"
                    + " AND v.niveauValidation.code = :niveauValidation"
                    + " order by d.dateDemande";
            
      

            Query q = getEm().createQuery(sql);
            q.setParameter("statut", "AvaliderRH");
            q.setParameter("etatCode", EtatValidationEnum.Encours.toString());
            q.setParameter("niveauValidation", NiveauValidationDemandeEnum.ValidationDG.toString());
            return (List<DemandePermissionAbsence>) q.getResultList();

        } catch (Exception ex2) {
            throw new EchecSelectException(ex2);
        }
    }

    public List listDemAbs(Agent agent) throws EchecSelectException {

        try {
            String sql = "select o.demandePermissionAbsence "
                    + " from Absence as o"
                    + " where o.agent.entite.id = :entiteId"
                    + "     and o.agent.service.id = :serviceId"
                    + "     AND o.etat.code != :etatCode";

            Query q = getEm().createQuery(sql);
            q.setParameter("entiteId", agent.getEntite().getId());
            q.setParameter("serviceId", agent.getService().getId());
             q.setParameter("etatCode", EtatAbsenceEnum.Cree.toString());
            return q.getResultList();

        } catch (Exception ex2) {
            throw new EchecSelectException(ex2);
        }
    }
  //Requete pour obtenir la liste des demandes d'absence  
    public List listAbsExcep() throws EchecSelectException {

        try {
            String sql = "select a.demandePermissionAbsence "
                    + " from Absence as a"
                    + " where a.typeAbsence.code = :typeAbsence"
                    + " AND a.etat.code = :etatCode";
                   // + " OR a.etat.code = :etatCode1";                    

            Query q = getEm().createQuery(sql);
            q.setParameter("typeAbsence", AbsenceEnum.PermAbsNonDeducConge.toString());
            q.setParameter("etatCode", EtatAbsenceEnum.Acceptee.toString());
           // q.setParameter("etatCode1", EtatAbsenceEnum.Effective.toString());
            
            return q.getResultList();

        } catch (Exception ex2) {
            throw new EchecSelectException(ex2);
        }
    }
    
    //Requete pour obtenir la liste des demandes d'absence en cours  
    public List listDemEnCours() throws EchecSelectException {

        try {
            String sql = "select a.demandePermissionAbsence "
                    + " from Absence as a"
                    + " where a.demandePermissionAbsence.etat.code = :etatCode";
                                    

            Query q = getEm().createQuery(sql);
            //q.setParameter("typeAbsence", AbsenceEnum.PermAbsNonDeducConge.toString());
            q.setParameter("etatCode", EtatValidationEnum.Encours.toString());
           // q.setParameter("etatCode1", EtatAbsenceEnum.Effective.toString());
            
            return q.getResultList();

        } catch (Exception ex2) {
            throw new EchecSelectException(ex2);
        }
    }
    
    //Requete pour obtenir la liste des demandes d'absence acceptees 
    public List listDemAcceptees() throws EchecSelectException {

        try {
            String sql = "select a.demandePermissionAbsence "
                    + " from Absence as a"
                    + " where a.demandePermissionAbsence.etat.code = :etatCode";
                                    

            Query q = getEm().createQuery(sql);
            //q.setParameter("typeAbsence", AbsenceEnum.PermAbsNonDeducConge.toString());
            q.setParameter("etatCode", EtatValidationEnum.Acceptee.toString());
           // q.setParameter("etatCode1", EtatAbsenceEnum.Effective.toString());
            
            return q.getResultList();

        } catch (Exception ex2) {
            throw new EchecSelectException(ex2);
        }
    }
    
    //Requete pour obtenir la liste des demandes d'absence archivees 
    public List listDemArchivees() throws EchecSelectException {

        try {
            String sql = "select a.demandePermissionAbsence "
                    + " from Absence as a";
                   // + " where a.demandePermissionAbsence.etat.code = :etatCode";
                                    

            Query q = getEm().createQuery(sql);
            //q.setParameter("typeAbsence", AbsenceEnum.PermAbsNonDeducConge.toString());
            //q.setParameter("etatCode", EtatValidationEnum.Acceptee.toString());
           // q.setParameter("etatCode1", EtatAbsenceEnum.Effective.toString());
            
            return q.getResultList();

        } catch (Exception ex2) {
            throw new EchecSelectException(ex2);
        }
    }
    
    //Requete pour obtenir la liste des demandes d'absence refusees 
    public List listDemRefusees() throws EchecSelectException {

        try {
            String sql = "select a.demandePermissionAbsence "
                    + " from Absence as a"
                    + " where a.demandePermissionAbsence.etat.code = :etatCode";
                                    

            Query q = getEm().createQuery(sql);
       
            q.setParameter("etatCode", EtatValidationEnum.Refusee.toString());
          
            return q.getResultList();

        } catch (Exception ex2) {
            throw new EchecSelectException(ex2);
        }
    }
    
    
    
    public List listMesDemAbs(Agent agent, int idUser) throws EchecSelectException {

        try {
            String sql = "select DISTINCT o.demandePermissionAbsence "
                    + " from Absence as o, Utilisateur u"
                    //+ " where o.agent.id = u.agent.id"
                    +" where o.agent.id = u.agent.id "
                    + " AND (o.agent.id = :agentId" 
                    + " OR o.insertUserId = :idUser)" 
                    + " AND o.etat.code = :etatCode";
                    
                    

            Query q = getEm().createQuery(sql);
            q.setParameter("idUser", idUser);
            q.setParameter("agentId", agent.getId());
            q.setParameter("etatCode", EtatAbsenceEnum.Cree.toString());
            
            return q.getResultList();

        } catch (Exception ex2) {
            throw new EchecSelectException(ex2);
        }
    }

    public List listDemandes() throws EchecSelectException {

        try {
            String sql = "SELECT o "
                    + " FROM DemandePermissionAbsence as o"
                    + " WHERE (o.etat.code = :accepteeCode OR o.etat.code = :refuseeCode)"
                    + " ORDER BY o.dateDemande DESC";

            Query q = getEm().createQuery(sql);
            q.setParameter("accepteeCode", EtatValidationEnum.Acceptee.toString());
            q.setParameter("refuseeCode", EtatValidationEnum.Refusee.toString());
            return q.getResultList();

        } catch (Exception ex2) {
            throw new EchecSelectException(ex2);
        }
    }

    
    public List listPlanningConges(Agent agent) throws EchecSelectException {

        try {
            String sql = "SELECT o FROM DemandePermissionAbsence as o, Agent a"
                    + " WHERE o.absence.agent.id = a.id"
                    + "     AND o.etat.code <> :etatCode"
                    + "     AND :today <= o.absence.dateFinAbsence ";
            
            if (!agent.estRH()) {
                sql += " AND a.superviseur.id = :agentId ";
            }
      
            sql += " ORDER BY o.dateDemande DESC";

            Query q = getEm().createQuery(sql);
            if (!agent.estRH()) {
                q.setParameter("agentId", agent.getId());
            }        
            q.setParameter("etatCode", EtatValidationEnum.Acceptee.toString());     
            q.setParameter("today", JIDate.now());     

            return q.getResultList();

        } catch (Exception ex2) {
            throw new EchecSelectException(ex2);
        }
    }

    public List<DemandePermissionAbsence> listDemAbsPourSH(int agentId) throws EchecSelectException {

        try {
            String sql = "SELECT o  "
                    + " FROM DemandePermissionAbsence as o, Agent as a"
                    + " WHERE o.absence.agent.id = a.id"
                    + "     AND a.superviseur.id = :agentId"
                    + "     AND (o.etat.code = :accepteeCode OR o.etat.code = :refuseeCode)"
                   // + "     AND o.etat.code = :etatCode "
                  //  + "     AND v.niveauValidation.code = :niveauValidation"
                    + " ORDER BY o.dateDemande DESC";

            Query q = getEm().createQuery(sql);
            q.setParameter("agentId", agentId);
            //q.setParameter("etatCode", EtatValidationEnum.Encours.toString());
            //q.setParameter("niveauValidation", NiveauValidationDemandeEnum.ValidationSH.toString());
            q.setParameter("accepteeCode", EtatValidationEnum.Acceptee.toString());
            q.setParameter("refuseeCode", EtatValidationEnum.Refusee.toString());   
            

            return (List<DemandePermissionAbsence>) q.getResultList();

        } catch (Exception ex2) {
            throw new EchecSelectException(ex2);
        }
    }

    
    public ValidationDemande validationAFaire(DemandePermissionAbsence dem) throws EchecSelectException {

        try {
            String sql = "select o from ValidationDemande as o "
                    + " where o.demandePermissionAbsence.id = :demandeId"
                    + " order by o.dateValidation DESC";

            Query q = getEm().createQuery(sql);
            q.setParameter("demandeId", dem.getId());
            return (ValidationDemande) q.setMaxResults(1).getSingleResult();

        } catch (Exception ex2) {
            throw new EchecSelectException(ex2);
        }
    }

    public List<ValidationDemande> listValidationEffectives(DemandePermissionAbsence dem) throws EchecSelectException {

        if (dem == null) {
            return null;
        }
        
        try {
            String sql = "SELECT o "
                    + " FROM ValidationDemande as o "
                    + " WHERE o.demandePermissionAbsence.id = :demandeId"
                    + "     AND o.validateur.id IS NOT NULL"
                    + " ORDER BY o.dateValidation";

            Query q = getEm().createQuery(sql);
            q.setParameter("demandeId", dem.getId());
            return q.getResultList();

        } catch (Exception ex2) {
            throw new EchecSelectException(ex2);
        }
    }

    public List listDemAbsValiderParSH() throws EchecSelectException {

        try {
            String sql = "select o from ValidationDemande as o"
                    + " where o.niveauValidation.code = :niveauValidation"
                    + " and not (o.dateValidation is null)";

            Query q = getEm().createQuery(sql);
            q.setParameter("niveauValidation", NiveauValidationDemandeEnum.ValidationSH.toString());
            return q.getResultList();

        } catch (Exception ex2) {
            throw new EchecSelectException(ex2);
        }
    }

    public boolean estValidable(int demandePermissionAbsenceId, int validateurId) {
        long nbValidation = 0;

        try {
            String sql = "SELECT count(o.id) "
                    + " FROM ValidationDemande as o "
                    + " WHERE o.demandePermissionAbsence.id = :demandePermissionAbsenceId "
                    + "     AND o.validateur.id = :validateurId"
                    + " AND o.etat.code = :etatCode";

            Query q = getEm().createQuery(sql);
            q.setParameter("demandePermissionAbsenceId", demandePermissionAbsenceId);
            q.setParameter("validateurId", validateurId);
            q.setParameter("etatCode", EtatValidationEnum.Encours.toString());

            nbValidation = (long) q.getSingleResult();
            System.out.println("Nb Validation "+nbValidation);

        } catch (Exception ex) {
            log.error("AbsenceService.estValidable:", ex);
        }

        return (nbValidation == 0);
    }

    public List<SoldeConges> getSynthese(Agent agent) throws EchecSelectException {
        try {
            String sql = "select new com.cmu.agence.rh.domaine.SoldeConges(sum(s.nbCongesAcquis), sum(s.nbJourPris), sum(s.nbJourPrisNonDeductible), sum(s.nbCongesReliquat))"
                    + " from SoldeConges s"
                    + " where s.agent.id = :agentId";
            System.out.println("agent solde test "+agent);
            Query q = getEm().createQuery(sql);
            q.setParameter("agentId", agent.getId());

            return q.getResultList();
        } catch (NoResultException e) {
            JUtil.s("getSynthese ERREUR = " + e);
            return null;
        }
    }
    
   public ValidationDemande validation(DemandePermissionAbsence dem) throws EchecSelectException {

        try {
            String sql = "select o from ValidationDemande as o "
                    + " where o.demandePermissionAbsence.id = :demandeId"
                    + " and not (o.dateValidation is null)"
                    + " order by o.dateValidation";

            Query q = getEm().createQuery(sql);
            q.setParameter("demandeId", dem.getId());
            return (ValidationDemande) q.setMaxResults(1).getSingleResult();

        } catch (Exception ex2) {
            throw new EchecSelectException(ex2);
        }
   }
   
   public List<Absence> getNbJourNonDeduc(Date dateDebut, Date dateFin, int agentId) {
   //public Absence getNbJourNonDeduc( int agentId) {    
//        if (JIDate.estVide(dateDebut) || JIDate.estVide(dateFin)) {
//            return 0;
//        }
   // try {
        String sql = "SELECT o  FROM Absence as o"
                
                + " WHERE o.agent.id = :agentId"
                + " AND o.typeAbsence.code = :typeAbsence"
                + " AND (o.etat.code = :etatCode1 OR o.etat.code = :etatCode2)"
                + " AND o.dateDebutAbsence BETWEEN :dateDebut AND :dateFin"
                + " AND o.dateFinAbsence BETWEEN :dateDebut AND :dateFin";
        Query q = getEm().createQuery(sql);
        q.setParameter("agentId", agentId);
        q.setParameter("dateDebut", JIDate.dateSansHeure(dateDebut));
        q.setParameter("dateFin", JIDate.dateSansHeure(dateFin));
        q.setParameter("typeAbsence", AbsenceEnum.PermAbsNonDeducConge.toString());
        q.setParameter("etatCode1", EtatAbsenceEnum.Acceptee.toString());     
        q.setParameter("etatCode2", EtatAbsenceEnum.Effective.toString());  
        return (List<Absence>) q.getResultList();
        
//     } catch (Exception ex2) {
//            throw new EchecSelectException(ex2);
//        }
    }
   
    public String nomDemandeur(int absenceId)  {
        String name = "";

        
            String sql = "SELECT DISTINCT a "
                    + " FROM Agent as a, Absence abs, PersonnePhysique p "
                    + " WHERE a.id = p.id "
                    + " and a.utilisateur.id = :abs.insertUserId"
                   
                    + "     AND abs.id = :absenceId";
                   

            Query q = getEm().createQuery(sql);
            
            q.setParameter("absenceId", absenceId);
             System.out.println("Nom Complet "+name);
           return (String) q.getSingleResult();

        
           

       
    }
    
    public String nomCreateur(int idUser) {
        String name = "";

       
            String sql = "SELECT a "
                    + " FROM Agent as a, PersonnePhysique p "
                    + " WHERE a.id = p.id "
                    + " and a.utilisateur.id = :idUser";
                   
                   
                   

            Query q = getEm().createQuery(sql);
            
            q.setParameter("idUser", idUser);
             System.out.println("Nom Complet "+name);
           return (String) q.getSingleResult();

       
        
    }
           
   
   
     
       
   
   
   
}
