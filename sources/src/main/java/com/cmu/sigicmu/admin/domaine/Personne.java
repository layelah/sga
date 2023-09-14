package com.cmu.sigicmu.admin.domaine;

import com.cmu.base.domaine.BaseDomaine;
import com.cmu.base.domaine.ILib;
import com.cmu.sigicmu.param.domaine.Localite;
import com.cmu.sigicmu.param.domaine.TableValeur;
import com.cmu.util.JUtil;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import static javax.persistence.FetchType.EAGER;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "personne")
@DiscriminatorColumn(name="pers_type")
@Inheritance(strategy = InheritanceType.JOINED)
public class Personne extends BaseDomaine implements ILib {

    public static final String PERS_TYPE_AGENT = "A";
    public static final String PERS_MUTUELLE = "M";
    public static final String PERS_FONDS_GARANTIE = "F";
    
    @Column(name="pers_type", updatable = false) 
    private String persType;
        
    @Column(name = "nom", nullable = false)
    private String nom;
    
    @Column(name = "surnom_sigle")
    private String surnomSigle;
    
    //@Past(message = "Date de naissance doit être antérieure à la date d'aujord'hui" )
    @Column(name = "date_naissance")
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    
    @Column(name = "lieu_naissance")
    private String lieuNaissance;
    
    @Embedded
    Coordonnees coord;
    
    @JoinColumn(name = "region_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Localite region;
    
    @JoinColumn(name = "departement_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Localite departement;
    
    @JoinColumn(name = "commune_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Localite commune;

    @JoinColumn(name = "type_piece_id", referencedColumnName = "id")
    @ManyToOne(fetch = EAGER)
    private TableValeur typePiece;
    
    @Column(name = "num_piece")
    private String numPiece;
    
    @Column(name = "date_delivrance")
    @Temporal(TemporalType.DATE)
    private Date dateDelivrance;
    
    @Column(name = "date_expiration")
    @Temporal(TemporalType.DATE)
    private Date dateExpiration;
    
    public Personne() {
        coord = new Coordonnees();
    }
    
    public Personne(String persType) {
        this.persType = persType;
        coord = new Coordonnees();
    }
    
    @Override
    public String getLib() {
        return getNom();
    }
    
    public void setLib(String lib) {
        setNom(lib);
    }
    
    public String getPersType() {
        return persType;
    }

    public void setPersType(String persType) {
        this.persType = persType;
    }
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSurnomSigle() {
        return surnomSigle;
    }

    public void setSurnomSigle(String surnomSigle) {
        this.surnomSigle = surnomSigle;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getLieuNaissance() {
        return lieuNaissance;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }
    
    public Coordonnees getCoord() {
        return coord;
    }

    public void setCoord(Coordonnees coord) {
        this.coord = coord;
    }

    public Localite getRegion() {
        return region;
    }

    public void setRegion(Localite region) {
        this.region = region;
    }

    public Localite getDepartement() {
        return departement;
    }

    public void setDepartement(Localite departement) {
        this.departement = departement;
    }   

    public Localite getCommune() {
        return commune;
    }

    public void setCommune(Localite commune) {
        this.commune = commune;
    }

    public TableValeur getTypePiece() {
        return typePiece;
    }

    public void setTypePiece(TableValeur typePiece) {
        this.typePiece = typePiece;
    }

    public String getNumPiece() {
        return numPiece;
    }

    public void setNumPiece(String numPiece) {
        this.numPiece = numPiece;
    }

    public Date getDateDelivrance() {
        return dateDelivrance;
    }

    public void setDateDelivrance(Date dateDelivrance) {
        this.dateDelivrance = dateDelivrance;
    }

    public Date getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(Date dateExpiration) {
        this.dateExpiration = dateExpiration;
    }
    
    
    public String getLocalite() {
        String sLocalite = "";
        String sSep = "";
        
        if (! JUtil.estVide(this.getCommune())) {
            sLocalite = this.getCommune().getLib();
            sSep = " / ";
        }
        if (! JUtil.estVide(this.getDepartement())) {
            sLocalite = this.getDepartement().getLib() + sSep + sLocalite;
            sSep = " / ";
        }
        if (! JUtil.estVide(this.getRegion())) {
            sLocalite = this.getRegion().getLib() + sSep + sLocalite;;
            sSep = " / ";
        }
        
        return sLocalite;
    }
}
