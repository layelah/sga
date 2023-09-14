package com.cmu.base.service;

import com.cmu.base.domaine.BaseDomaine;
import com.cmu.base.service.exception.EchecSelectException;
import com.cmu.sigicmu.param.domaine.Annee;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.faces.model.SelectItem;

@Stateless
@LocalBean
public class BaseItemService extends CmuJPAService {

    public List<Annee> getAnnee() throws EchecSelectException {
        String sql = "select a from Annee as a";
        return (List<Annee>)getResults(sql);
    }
    
    public List<SelectItem> getSelectItemsWithObject(BaseDomaine obj, String colText, String colSorting) throws EchecSelectException {
        String sql = "select new javax.faces.model.SelectItem(o, o." + colText + ")  "
                + " from " + obj.getNomClasse() + " as o "
                + " order by o." + colSorting;
        return selectItems(sql);
    }
    
    public List<SelectItem> getSelectItemsWithIdANDLib(BaseDomaine obj, String colText, String colSorting) throws EchecSelectException {
        String sql = "select new javax.faces.model.SelectItem(o, o." + colText + ")  "
                + " from " + obj.getNomClasse() + " as o "
                + " order by o." + colSorting;
        return selectItems(sql);
    }
    
    public List<SelectItem> getSelectItemsWhithId(BaseDomaine obj, String colId, String colText, String colSorting) throws EchecSelectException {
        String sql = "select new javax.faces.model.SelectItem(o." + colId + ", o." + colText + ")  "
                + " from " + obj.getNomClasse() + " as o "
                //+ " where o.validation.etatCode = :etatCode"                
                + " order by o." + colSorting;
        return selectItems(sql);
    }

    //#BS TO DELETE
    public List<SelectItem> getSelectItemsId(BaseDomaine obj, String colId, String colText, String colSorting) throws EchecSelectException {
            return getSelectItemsWhithId(obj, colId, colText, colSorting);
    }
    
    public List<SelectItem> getSelectItemsId(BaseDomaine obj, String colId, String colText) throws EchecSelectException {
        return getSelectItemsId(obj, colId, colText, colText);
    }
    
    public List<SelectItem> getSelectItemsWithObject(BaseDomaine obj, String colText) throws EchecSelectException {
        return getSelectItemsWithObject(obj, colText, colText);
    }
    
    public List<SelectItem> getSelectItems(BaseDomaine obj, String colText) throws EchecSelectException {
        return getSelectItemsWithObject(obj, colText, colText);
    }
    
    public List<SelectItem> getSelectItems(BaseDomaine obj, String colText, String colSorting) throws EchecSelectException {
        return getSelectItemsWithObject(obj, colText, colSorting);
    }

    public List<SelectItem> getSelectItemsFilter(BaseDomaine obj, String colText, String sAttribut, Integer idFiltre, String colSorting) throws EchecSelectException {
        String sql = "select new javax.faces.model.SelectItem(o, o." + colText + ") "
                + " from " + obj.getNomClasse() + " as o"
                //+ " where o.validation.etatCode = :etatCode and o." + sAttribut + ".id = " + idFiltre
                + " where o." + sAttribut + ".id = " + idFiltre
                + " order by " + colSorting;
        return selectItems(sql);
    }

    public List<SelectItem> getSelectItems2Lib(BaseDomaine obj, String colId, String colText1, String colText2) throws EchecSelectException {
        String sql = "select new javax.faces.model.SelectItem(o." + colId + ", concat(concat(o." + colText1 + ", ' - '), o." + colText2 + "))  "
                + " from " + obj.getNomClasse() + " as o "
                //+ " where o.validation.etatCode = :etatCode"    
                + " order by o." + colText1 + ", o." + colText2;
        return selectItems(sql);
    }

    public List<SelectItem> getSelectItems2Lib(BaseDomaine obj, String colText1, String colText2) throws EchecSelectException {
        return getSelectItems2Lib(obj, "id", colText1, colText2);
    }
    
     public List<SelectItem> getInitialtives() throws EchecSelectException {
        String sql = "select new javax.faces.model.SelectItem(o.id ,o.lib) from ActionVerification o order by o.lib";
        return (List<SelectItem>)getResults(sql);
    }
}
