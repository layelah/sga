package com.cmu.sigicmu.bureau.domain;

import com.cmu.sigicmu.param.domaine.Localite;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Dashboard{
    
    private Localite localite;
    private String lib;
    private Long nbMutuelle;
    private Long popTotale;
    private Long popCible;
    private Long tauxCouverture;
    private Long nbAdherent;
    private Long nbBeneficiaire;
    private double totCotisation;
    private double totBeneficiaire;

    // CONTRUCTEEURS
    private Dashboard() {        
    }

    public Dashboard(Long nbMutuelle, Long popCible, Long nbAdherent, Long nbBeneficiaire, double totCotisation) {
        this.nbMutuelle = nbMutuelle;
        this.popCible = popCible;
        this.nbAdherent = nbAdherent;
        this.nbBeneficiaire = nbBeneficiaire;
        this.totCotisation = totCotisation;
    }

    public Dashboard(Long nbMutuelle, Long popCible, Long nbAdherent, Long nbBeneficiaire, double totCotisation, Long popTotale) {
        this.nbMutuelle = nbMutuelle;
        this.popCible = popCible;
        this.nbAdherent = nbAdherent;
        this.nbBeneficiaire = nbBeneficiaire;
        this.totCotisation = totCotisation;
        this.popTotale = popTotale;
    }

        
    public Dashboard(Localite localite, Long nbMutuelle, Long popCible, Long nbAdherent, Long nbBeneficiaire, double totCotisation) {
        this.localite = localite;
        this.nbMutuelle = nbMutuelle;
        this.popCible = popCible;
        this.nbAdherent = nbAdherent;
        this.nbBeneficiaire = nbBeneficiaire;
        this.totCotisation = totCotisation;
    }
    
    // GETTTERS & SETTETRS
    public Localite getLocalite() {
        return localite;
    }

    public void setLocalite(Localite localite) {
        this.localite = localite;
    }

    public Long getNbMutuelle() {
        return nbMutuelle;
    }

    public void setNbMutuelle(Long nbMutuelle) {
        this.nbMutuelle = nbMutuelle;
    }

    public Long getPopTotale() {
        return popTotale;
    }

    public void setPopTotale(Long popTotale) {
        this.popTotale = popTotale;
    }

    public String getSPopTotale() {
        return form(new Double(popTotale));
    }
        
    public Long getPopCible() {
        return popCible;
    }

    public String getSPopCible() {
        return form(popCible);
    }
    
    public void setPopCible(Long popCible) {
        this.popCible = popCible;
    }

    public Long getTauxCouverture() {
        return tauxCouverture;
    }

    public void setTauxCouverture(Long tauxCouverture) {
        this.tauxCouverture = tauxCouverture;
    }

    public Long getNbAdherent() {
        return nbAdherent;
    }

    public String getSNbAdherent() {
        return form(new Double(nbAdherent));
    }

    public void setNbAdherent(Long nbAdherent) {
        this.nbAdherent = nbAdherent;
    }

    public Long getNbBeneficiaire() {
        return nbBeneficiaire;
    }

    public String getSNbBeneficiaire() {
        return form(new Double(nbBeneficiaire));
    }

    public void setNbBeneficiaire(Long nbBeneficiaire) {
        this.nbBeneficiaire = nbBeneficiaire;
    }

    public double getTotCotisation() {
        return totCotisation;
    }

    public String form(Double d) {
        DecimalFormatSymbols dfs = new DecimalFormatSymbols(Locale.FRANCE);
	dfs.setGroupingSeparator(' ');
	DecimalFormat df = new DecimalFormat("#,###", dfs);
        Double dVal = ((d == null) ? 0 : d);
        return df.format(dVal);
    }
    
    public String form(Long d) {
        DecimalFormatSymbols dfs = new DecimalFormatSymbols(Locale.FRANCE);
	dfs.setGroupingSeparator(' ');
	DecimalFormat df = new DecimalFormat("#,###", dfs);
        Long dVal = ((d == null) ? 0 : d);
        return df.format(dVal);
    }
    
    public String getSTotCotisation() {
        return form(totCotisation);
    }
    public void setTotCotisation(double totCotisation) {
        this.totCotisation = totCotisation;
    }

    public double getTotBeneficiaire() {
        return totBeneficiaire;
    }

    public void setTotBeneficiaire(double totBeneficiaire) {
        this.totBeneficiaire = totBeneficiaire;
    }


     public String getSTotBeneficiaire() {
        return form(totBeneficiaire);
    }
     
    
    public String getLib() {
        return lib;
    }

    public void setLib(String lib) {
        this.lib = lib;
    }
    
    public String getNiveau() {
        if (localite == null) {
            return "Niveau ???";
        }
        return "Zone couverte: "+ localite.getLib() +" - Niveau: "+ localite.getType().getLib();
    }
}
