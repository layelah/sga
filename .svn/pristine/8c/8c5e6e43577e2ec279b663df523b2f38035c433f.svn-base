package com.cmu.base.bean;

import com.cmu.agence.rh.domaine.AbsenceExceptionnelle;
import com.cmu.agence.rh.domaine.CongesNonDeductible;
import com.cmu.base.service.BaseItemService;
import com.cmu.base.service.CommonLibService;
import com.cmu.base.service.exception.EchecGetRefenceException;
import com.cmu.base.service.exception.EchecSelectException;
import com.cmu.sigicmu.admin.domaine.Entite;
import com.cmu.sigicmu.admin.domaine.RaisonBlocageEnum;
import com.cmu.sigicmu.admin.domaine.Service;
import com.cmu.sigicmu.admin.service.AgentService;
import com.cmu.sigicmu.admin.service.UtilisateurService;
import com.cmu.sigicmu.bureau.bean.AuthentificationBean;
import com.cmu.sigicmu.param.domaine.Annee;
import com.cmu.sigicmu.param.domaine.Localite;
import com.cmu.sigicmu.param.domaine.Periode;
import com.cmu.sigicmu.param.domaine.TableValeurTypeEnum;
import com.cmu.sigicmu.param.service.TableValeurService;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "myItems")
@ApplicationScoped
public class MyItems {

    @EJB
    UtilisateurService user;
    
    @Inject
    AuthentificationBean auth;
    
    @EJB
    TableValeurService tv;
    
    @EJB
    AgentService agent;

    
    @EJB
    BaseItemService item;
    
    @EJB
    CommonLibService ComLibSrv;
  
     
    public List<Annee> getAnnees() throws EchecSelectException{
        return item.getAnnee();
    }
    
    // liste annee
    public List<SelectItem> lesAnnees() throws EchecSelectException{
        return item.getSelectItems(new Annee(), "annee");
    }
    
    // liste role
    public List<SelectItem> getLesPeriodes() throws EchecSelectException{
        return item.getSelectItems(new Periode(), "lib", "dateDebut");
    }
   
    
//    public List<DossierFacture> getLesDossiers() throws EchecSelectException{
//        return item.getDossierFact();
//    }
    // les types de factures
    public List<SelectItem> getLesTypeFact() throws EchecSelectException{
        return tv.getTableValeurItems(TableValeurTypeEnum.TypeFacture.getCode());
    } 
    // les types d'absence
    public List<SelectItem> getLesTypeAbs() throws EchecSelectException{
        return tv.getTableValeurItems(TableValeurTypeEnum.TypeAbsence.getCode());
    } 
    
    // les états de validation
    public List<SelectItem> getLesEtatValidation() throws EchecSelectException{
        return tv.getTableValeurItems(TableValeurTypeEnum.EtatValidation.getCode());
    } 
    
    // les types de factures
    public List<SelectItem> getLesMotifRejetDetail() throws EchecSelectException{
        return tv.getTableValeurItemsWithId(TableValeurTypeEnum.MotifRejet.getCode());
    } 
    // les types de factures
    public List<SelectItem> getLesInitiativesFact() throws EchecSelectException{
        return tv.getTableValeurItems(TableValeurTypeEnum.InitiativeFacture.getCode());
    } 
     // les types de factures
    public List<SelectItem> getLesInitiatives() throws EchecSelectException{
        return tv.Initiatives(TableValeurTypeEnum.InitiativeFacture.getCode());
    } 
    
    // les etats de factures
    public List<SelectItem> getLesEtatFact() throws EchecSelectException{
        return tv.getTableValeurItems(TableValeurTypeEnum.EtatFacture.getCode());
    } 
    
    public List<SelectItem> getLesGraphic() throws EchecSelectException{
        return tv.getTableValeurItems(TableValeurTypeEnum.GraphiqueIndicateur.getCode());
    }
    
    // liste service 
    public List<SelectItem> getListService() throws EchecSelectException{
        return item.getSelectItems(new Service(), "lib");
    }
    
    // Liste des Absences Exceptionnelles
    public List<SelectItem> getLesAbsencesExceptionnelles() throws EchecSelectException {
        return item.getSelectItems(new AbsenceExceptionnelle(), "lib");
    }
    
     // Liste des Notes de validation
//    public List<SelectItem> getLesNotesValidation() throws EchecSelectException {
//        return item.getSelectItems(new AbsenceExceptionnelle(), "lib");
//    }
//     public List<SelectItem> getLesNotesValidation() throws EchecSelectException {
//        return tv.getTableValeurItems(TableValeurTypeEnum.NoteValidation.toString());
//    }

    
    // liste des annees
     public List<SelectItem> getLesAnnees() throws EchecSelectException, EchecGetRefenceException {
        List<SelectItem> l = new ArrayList<SelectItem>();
        l.add(new SelectItem(item.getReference(new Annee(), 2018), "2019"));
        l.add(new SelectItem(item.getReference(new Annee(), 2018), "2018"));
        l.add(new SelectItem(item.getReference(new Annee(), 2017), "2017"));
        l.add(new SelectItem(item.getReference(new Annee(), 2016), "2016"));
        l.add(new SelectItem(item.getReference(new Annee(), 2015), "2015"));

        return l;
    }
     
     // liste des mois
     public List<SelectItem> getLesMois() throws EchecSelectException {
        List<SelectItem> l = new ArrayList<SelectItem>();
        l.add(new SelectItem(1,"Janvier"));
        l.add(new SelectItem(2,"Février"));
        l.add(new SelectItem(3,"Mars"));
        l.add(new SelectItem(4,"Avril"));
        l.add(new SelectItem(5,"Mai"));
        l.add(new SelectItem(6,"Juin"));
        l.add(new SelectItem(7,"Juillet"));
        l.add(new SelectItem(8,"Aout"));
        l.add(new SelectItem(9,"Septembre"));
        l.add(new SelectItem(10,"Octobre"));
        l.add(new SelectItem(11,"Novembre"));
        l.add(new SelectItem(12,"Décembre"));
        return l;
    }
      
    public List getPeriodes() throws EchecSelectException {
        return null; //ComLibSrv.getSelectItems(new Periode());
    }
    
    public List<SelectItem> getRaisonUtilisateur() throws EchecSelectException {
        List<SelectItem> l = new ArrayList<SelectItem>();

        l.add(new SelectItem(RaisonBlocageEnum.Absence, "Absence"));
        l.add(new SelectItem(RaisonBlocageEnum.Interdiction, "Interdiction"));

        return l;
    }

    public List<SelectItem> getListUserByLogin() throws EchecSelectException {
        return user.getListUserByLogin();
    }

    public List<SelectItem> getLesUtilisateurs() throws EchecSelectException {
        return user.getListUserById();
    }

    public List<SelectItem> getLesUtilisateursAvecCode() throws EchecSelectException {
        return user.getUtilisateurAvecEntite();
    }
    
    public List<SelectItem> getLesAgents() throws EchecSelectException {
        return agent.getListAgentById();
    }
    
    public List<SelectItem> getLesAgentsDeMonService() throws EchecSelectException {
        return agent.getListAgentByIdDeMonService(auth.getUser().getAgent());
    }
    
     public List<SelectItem> getLesActionsVerifications() throws EchecSelectException {
        return item.getInitialtives();
    }

    public List<SelectItem> getLesEntites() throws EchecSelectException {
        return ComLibSrv.getSelectItems(new Entite());
    }

    public List<SelectItem> getLesServices() throws EchecSelectException {
        return ComLibSrv.getSelectItems(new Service());
    }

    
    public List<SelectItem> getLesPoidsSIgnature() throws EchecSelectException {
        return tv.getTableValeurItems(TableValeurTypeEnum.PoidsSignature.getCode());
    }
    
    
    public List<SelectItem> getLesFormeJuridiques() throws EchecSelectException {
        return tv.getTableValeurItems(TableValeurTypeEnum.FormeJuridique.getCode());
    }
    
    public List<SelectItem> getLesStructures() throws EchecSelectException {
        return tv.getTableValeurItems(TableValeurTypeEnum.Structure.getCode());
    }
    public List<SelectItem> getLesTypeLocalites() throws EchecSelectException {
        return tv.getTableValeurItems(TableValeurTypeEnum.TypeLocalite.getCode());
    }
    
    public List<SelectItem> getLesCategorieMutuelles() throws EchecSelectException {
        return tv.getTableValeurItems(TableValeurTypeEnum.CategorieMutuelle.getCode());
    }
    
    public List<SelectItem> getLesUnitesDeTemps() throws EchecSelectException {
        return tv.getTableValeurItems(TableValeurTypeEnum.UniteTemps.getCode());
    }
    
    public List<SelectItem> getLesTypeDonnees() throws EchecSelectException {
        return tv.getTableValeurItems(TableValeurTypeEnum.TypeDeDonnee.getCode());
    }

    public List<SelectItem> getLesTypeEntiteMutualistes() throws EchecSelectException {
        return tv.getTableValeurItems(TableValeurTypeEnum.TypeEntiteMutualiste.getCode());
    }

    public List<SelectItem> getLesNiveauxHierarchiques() throws EchecSelectException {
        return tv.getTableValeurItems(TableValeurTypeEnum.HierarchiePyramideAdministrative.getCode());
    }

    public List<SelectItem> getLesTypesStructureSante() throws EchecSelectException {
        return tv.getTableValeurItems(TableValeurTypeEnum.TypeStructureSante.getCode());
    }

    public List<SelectItem> getLesNatureStructureSante() throws EchecSelectException {
        return tv.getTableValeurItems(TableValeurTypeEnum.NatureStructureSante.getCode());
    }

    public List<SelectItem> getLaPyramideSanitaire() throws EchecSelectException {
        return null;// pyrSanSrv.listRegionMedical();
    }

    public List<SelectItem> getLesObjetDesMutuelles() throws EchecSelectException {
        return tv.getTableValeurItems(TableValeurTypeEnum.ObjetMutuelle.getCode());
    }
    
    public List<SelectItem> getLesTypesPeriode() throws EchecSelectException {
        return null; // tv.getTableValeurItems(TableValeurTypeEnum.TypePeriode.getCode());
    }

    public List<SelectItem> getLesLocalites() throws EchecSelectException {
        return ComLibSrv.getSelectItems(new Localite());
    }
    
    public List<SelectItem> getLesLRegion() throws EchecSelectException {
        return ComLibSrv.getSelectItems(new Localite());
    }
    
    public List<SelectItem> getLesModules() throws EchecSelectException {
        return tv.getTableValeurItems(TableValeurTypeEnum.Module.toString());
    }
    public List<SelectItem> getLesServicesMedicale() throws EchecSelectException {
        return tv.getTableValeurItems(TableValeurTypeEnum.Service.toString());
    }
    public List<SelectItem> getLesCategoriesActe() throws EchecSelectException {
        return tv.getTableValeurItems(TableValeurTypeEnum.CategorieActe.toString());
    }

    public List<SelectItem> getLesDestinatairesGeneraux() throws EchecSelectException {
        return null; // dest.entitesGeneraux();
    }

    public List<SelectItem> getLesJours() throws EchecSelectException {
        return null; // tv.getTableValeurItems(TableValeurTypeEnum.Jour.toString());
    }

    public List<SelectItem> getLesMomentsJournees() throws EchecSelectException {
        return null; // tv.getTableValeurItems(TableValeurTypeEnum.MomentJournee.toString());
    }

    public List<SelectItem> getLesRubriquesDeParametrage() throws EchecSelectException {
        return tv.getTableValeurItems(TableValeurTypeEnum.RubriqParam.toString());
    }

    public List<SelectItem> getLesPriorites() throws EchecSelectException {
        return null; // tv.getTableValeurItems(TableValeurTypeEnum.Priorite.toString());
    }

    public List<SelectItem> getTacheEtats() throws EchecSelectException {
        return null; // tv.getTableValeurItems(TableValeurTypeEnum.TacheEtat.toString());
    }

    public List<SelectItem> getLesObjets() throws EchecSelectException {
        return null; // tv.getTableValeurItems(TableValeurTypeEnum.Objet.toString());
    }

    public List<SelectItem> getLesPays() throws EchecSelectException {
        return tv.getTableValeurItems(TableValeurTypeEnum.Pays.toString());
    }

    public List<SelectItem> getLesTypesEntite() throws EchecSelectException {
        return tv.getTableValeurItems(TableValeurTypeEnum.TypeEntite.toString());
    }

    public List<SelectItem> getLesTypesService() throws EchecSelectException {
        return null; // tv.getTableValeurItems(TableValeurTypeEnum.TypeService.toString());
    }

    public List<SelectItem> getLesFonctions() throws EchecSelectException {
        return tv.getTableValeurItems(TableValeurTypeEnum.Fonction.toString());
    }

    public List<SelectItem> getLesNiveauConfidentialite() throws EchecSelectException {
        return tv.getTableValeurItems(TableValeurTypeEnum.NiveauConfidentialite.toString());
    }
    
    public List<SelectItem> getLesChampsInterventions() throws EchecSelectException {
        return tv.getChampsIntervention();
    }
   
    public List<SelectItem> getLesModeAcquisitionSiege() throws EchecSelectException {
        return tv.getTableValeurItems(TableValeurTypeEnum.ModeAcquisitionSiege.toString());
    }
     
    public List<SelectItem> getLesCivilites() throws EchecSelectException {
        return tv.getTableValeurItems(TableValeurTypeEnum.Civilite.toString());
    }

    public List<SelectItem> getLesGenre() throws EchecSelectException {
        return tv.getTableValeurItems(TableValeurTypeEnum.Genre.toString());
    }
    

    public List<SelectItem> getLesStatutMarital() throws EchecSelectException {
        return tv.getTableValeurItems(TableValeurTypeEnum.StatutMarital.toString());
    }
    
    public List<SelectItem> getLesFonctionOrganes() throws EchecSelectException {
        return tv.getTableValeurItems(TableValeurTypeEnum.FonctionOrgane.toString());
    }
    
    public List<SelectItem> getLesAbsencesNonDeductibles() throws EchecSelectException {
        return item.getSelectItems(new CongesNonDeductible(), "lib");
    }
    
    
    public List<SelectItem> getNiveauxValidations() throws EchecSelectException {
        return tv.getTableValeurItems(TableValeurTypeEnum.NiveauxValidation.toString());
    }
    
     public List<SelectItem> getCircuitValidation() throws EchecSelectException {
        return tv.getTableValeurItems(TableValeurTypeEnum.CicuitValidation.toString());
    }
     
     // get localite type
    public List<SelectItem> getLocaliteType() throws EchecSelectException{
        return tv.getTableValeurItems(TableValeurTypeEnum.TypeLocalite.toString());
    }
    public List<SelectItem> getPays() throws EchecSelectException{
        return tv.getTableValeurItems(TableValeurTypeEnum.Pays.toString());
    }
    // Liste des heures de travail
    public List<Integer> getHeuresTravail(){
        List<Integer> listHeure = new ArrayList<Integer>();
            listHeure.add(8);
            listHeure.add(9);
            listHeure.add(10);
            listHeure.add(11);
            listHeure.add(12);
            listHeure.add(14);
            listHeure.add(15);
            listHeure.add(16);
            
        
        return listHeure;
    }
}
