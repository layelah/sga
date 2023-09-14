package com.cmu.sigicmu.admin.domaine;

import com.cmu.base.domaine.BaseDomaine;
import com.cmu.sigicmu.param.domaine.TableValeur;
import com.cmu.util.JUtil;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
//@DiscriminatorValue("A")
@Table(name = "imp_agent")
//@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
public class AgentImp extends PersonnePhysique {

    private String matricule;
    
    private String cni;

    @Column(name = "date_prise_fonction")
    @Temporal(TemporalType.DATE)
    private Date datePriseFonction;

    @Column(name = "date_recrutement")
    @Temporal(TemporalType.DATE)
    private Date dateRecrutement;

    @Column(name = "tel_professionnel")
    private String telProfessionnel;

    @Column(name = "numero_poste")
    private String numeroPoste;

    //@Email(regexp = "^[a-zA-Z][a-zA-Z0-9\\.]+@[a-zA-Z]+\\.[a-zA-Z]+", message = "{email_invalid_error_message}")
    @Column(name = "email_professionnel")
    private String emailProfessionnel;

    @Column(name = "tel_bureau")
    private String telBureau;
    
    @Column(name = "fonction_libelle")
    private String libFonction;

    @JoinColumn(name = "superviseur_id", referencedColumnName = "id")
    @ManyToOne
    private AgentImp superviseur;

    @JoinColumn(name = "entite_id", referencedColumnName = "id", nullable = false)
    @ManyToOne
    private Entite entite;

    @JoinColumn(name = "utilisateur_id", referencedColumnName = "id", nullable = false)
    @OneToOne
    private Utilisateur utilisateur;

    @JoinColumn(name = "service_id", referencedColumnName = "id", nullable = false)
    @ManyToOne
    private Service service;

    @JoinColumn(name = "fonction_id", referencedColumnName = "id", nullable = false)
    @ManyToOne
    private TableValeur fonction;
    
    @Embedded
    Coordonnees coord;

    public AgentImp() {
        super(Personne.PERS_TYPE_AGENT);
    }

    public boolean getSansUtilisateur() {
        return BaseDomaine.idIsNull(utilisateur);
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getCni() {
        return cni;
    }

    public void setCni(String cni) {
        this.cni = cni;
    }
    
    

    public String getTelProfessionnel() {
        return telProfessionnel;
    }

    public void setTelProfessionnel(String telProfessionnel) {
        this.telProfessionnel = telProfessionnel;
    }

    public String getNumeroPoste() {
        return numeroPoste;
    }

    public void setNumeroPoste(String numeroPoste) {
        this.numeroPoste = numeroPoste;
    }

    public String getEmailProfessionnel() {
        return emailProfessionnel;
    }

    public void setEmailProfessionnel(String emailProfessionnel) {
        this.emailProfessionnel = emailProfessionnel;
    }

    public String getTelBureau() {
        return telBureau;
    }

    public void setTelBureau(String telBureau) {
        this.telBureau = telBureau;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Entite getEntite() {
        return entite;
    }

    public void setEntite(Entite entite) {
        this.entite = entite;
    }

    public AgentImp getSuperviseur() {
        return superviseur;
    }

    public void setSuperviseur(AgentImp superviseur) {
        this.superviseur = superviseur;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public TableValeur getFonction() {
        return fonction;
    }

    public void setFonction(TableValeur fonction) {
        this.fonction = fonction;
    }

    public Date getDatePriseFonction() {
        return datePriseFonction;
    }

    public void setDatePriseFonction(Date datePriseFonction) {
        this.datePriseFonction = datePriseFonction;
    }

    public Date getDateRecrutement() {
        return dateRecrutement;
    }

    public void setDateRecrutement(Date dateRecrutement) {
        this.dateRecrutement = dateRecrutement;
    }

    public Coordonnees getCoord() {
        return coord;
    }

    public void setCoord(Coordonnees coord) {
        this.coord = coord;
    }

    public String getLibFonction() {
        return libFonction;
    }

    public void setLibFonction(String libFonction) {
        this.libFonction = libFonction;
    }
    
    
    

    public boolean isEstUtilisateur() {
        return !(JUtil.estVide(utilisateur));
    }
    
    public boolean getEstRoot() {
        return (this.getFonction()!= null && this.getFonction().getCode().equals(FonctionEnum.AdminGeneral.toString()));
    }

    public boolean getEstAdmin() {
        return (this.getService()!= null && this.getService().getCode().equals(ServiceEnum.CSI.toString()));
    }

    public boolean estRH() {
        return (this.getFonction() != null &&  (this.getFonction().getCode().equals(FonctionEnum.ResponsableRH.toString()) ));
    }
    
    public boolean estDaf() {
        return (this.getFonction() != null &&  (this.getFonction().getCode().equals(FonctionEnum.DirecteurAdminFin.toString()))); 
               
    }
    
    public boolean estSG() {
        return (this.getFonction() != null &&  (this.getFonction().getCode().equals(FonctionEnum.SecretaireGeneral.toString()))); 
               
    }

    public boolean estDG() {
        return (this.getFonction() != null && (this.getFonction().getCode().equals(FonctionEnum.DirecteurGeneral.toString())));
    }
    
     public boolean estCD() {
        return (this.getFonction() != null && (this.getFonction().getCode().equals(FonctionEnum.ChefDivision.toString())));
    }
    
    public boolean estDIR() {
        return (this.getFonction() != null && (this.getFonction().getCode().equals(FonctionEnum.Directeur.toString())));
    }
    
    public boolean estRoot() {
        return (this.getFonction() != null && (this.getFonction().getCode().equals(FonctionEnum.AdminGeneral.toString())));
    }
    
    public boolean superviseurEstDG() {
       // return (superviseur != null && (superviseur.estDG() || superviseur.estRH()));
        return (superviseur != null && superviseur.estDG());
    }
    
    public boolean superviseurEstRH() {
        return (superviseur != null && superviseur.estRH());
    }
    
    public boolean superviseurEstCD() {
        return (superviseur != null && superviseur.estCD());
    }
    
    public boolean superviseurEstDir() {
        return (superviseur != null && superviseur.estDIR());
    }
    
    public boolean superviseurEstDAF() {
        return (superviseur != null && superviseur.estDaf());
    }
    
    public boolean superviseurEstSG() {
        return (superviseur != null && superviseur.estSG());
    }
    
    public boolean estSuperValidateur(){
        return (this.getFonction() != null && (this.estRH() || this.estCD() || 
                this.estDIR() || this.estDaf() || this.estSG() || this.estDG()));
    }
    
    public boolean estSuperAgent(){
        return (this.getFonction() != null && (this.estRH() || this.estDIR() || 
               this.estCD() || this.estDaf() || this.estSG() || this.estDG()));
    }
    
    public boolean estValidateur(){
        return (this.getFonction() != null && (this.estRH() || this.estDIR() || this.estDaf() || this.estCD()));
    }
    
    public String getNomTotal() {
        String sNom = (JUtil.estVide(getCivilite()) ? "": getCivilite().getLib() +" ") + getPrenom();
        String sNomJeuneFille = getNomJeuneFille();
        
        sNom += (JUtil.estVide(sNomJeuneFille) ? "": " "+ getNomJeuneFille()) +" "+ getNom();
        
        return sNom ;
    }

}
