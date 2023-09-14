package com.cmu.sigicmu.param.bean;

import com.cmu.sigicmu.param.domaine.Localite;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

@Named(value="ajLoc")
@SessionScoped
public class AjaxLocaliteBean implements Serializable {

    private Localite region;
    private Localite departement;
    
    List<SelectItem> listDepartement;
    List<SelectItem> listCommune;
    List<SelectItem> listPyramideSanitaire;
    List<SelectItem> listSructureSante;
    
    public AjaxLocaliteBean() {
        
    }
    
    public List<SelectItem> getListDepartement() {
        return listDepartement;
    }

    public void setListDepartement(List<SelectItem> listDepartement) {
        this.listDepartement = listDepartement;
    }

    public List<SelectItem> getListCommune() {
        return listCommune;
    }

    public void setListCommune(List<SelectItem> listCommune) {
        this.listCommune = listCommune;
    }

    public List<SelectItem> getListPyramideSanitaire() {
        return listPyramideSanitaire;
    }

    public void setListPyramideSanitaire(List<SelectItem> listPyramideSanitaire) {
        this.listPyramideSanitaire = listPyramideSanitaire;
    }

    public List<SelectItem> getListSructureSante() {
        return listSructureSante;
    }

    public void setListSructureSante(List<SelectItem> listSructureSante) {
        this.listSructureSante = listSructureSante;
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
}
