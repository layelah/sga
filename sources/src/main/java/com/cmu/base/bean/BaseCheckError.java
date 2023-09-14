package com.cmu.base.bean;

import com.cmu.base.service.exception.EchecSelectException;
import com.cmu.util.CheckType;
import com.cmu.util.JIDate;
import com.cmu.sigicmu.param.service.TableValeurTypeService;
import com.cmu.base.domaine.ILibCodeDesc;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseCheckError {

    public BaseCheckError() {
    }

    public boolean sansErreur() throws EchecSelectException {
        return true;
    }
        
    public boolean checkNonVide(String clientId, String sValue) throws EchecSelectException {
        boolean bSansErreur = true;

        if (CheckType.estVide(sValue)) {
            bSansErreur = false;
            JsfUtil.addErrorMessage(clientId, "Valeur obligatoire");
        }

        return bSansErreur;
    }

    public boolean checkTtableValeurTypeCode(String clientId, String sValue, TableValeurTypeService tvtSrv) throws EchecSelectException {
        boolean bSansErreur = true;

        if (!CheckType.estTypeCode(sValue, tvtSrv)) {
            bSansErreur = false;
            JsfUtil.addErrorMessage("type_code", "Le code n'est pas celui d'un type de table de valeur");
        }

        return bSansErreur;
    }

    public boolean checkLongueur(String clientId, String sValue, Integer lMin, Integer lMax) throws EchecSelectException {
        boolean bSansErreur = true;

        if (CheckType.estVide(lMin)) {
            return checkLongueurMax(clientId, sValue, lMax);
        }

        if (CheckType.estVide(sValue)) {
            if (0 < lMin) {
                bSansErreur = false;
                JsfUtil.addErrorMessage(clientId, "Valeur obligatoire");
            }
        } else if (!(lMin <= sValue.length() && sValue.length() <= lMax)) {
            bSansErreur = false;
            if (lMin == lMax) {
                JsfUtil.addErrorMessage(clientId, "la longueur doit être égale à " + lMin);
            } else {
                JsfUtil.addErrorMessage(clientId, "la longueur doit être comprise entre " + lMin + " et " + lMax);
            }
        }

        return bSansErreur;
    }

    public boolean checkLongueurMin(String clientId, String sValue, Integer lMin) throws EchecSelectException {
        boolean bSansErreur = true;

        if (CheckType.estVide(lMin)) {
            return true;
        }
        if (CheckType.estVide(sValue) || !(lMin <= sValue.length())) {
            bSansErreur = false;
            JsfUtil.addErrorMessage(clientId, "la longueur doit être supérieure à " + lMin);
        }

        return bSansErreur;
    }

    public boolean checkLongueurMax(String clientId, String sValue, Integer lMax) {
        boolean bSansErreur = true;

        if (CheckType.estVide(lMax)) {
            return true;
        }

        if (!CheckType.estVide(sValue) && !(sValue.length() <= lMax)) {
            bSansErreur = false;
            JsfUtil.addErrorMessage(clientId, "la longueur doit être inférieure à " + lMax);
        }

        return bSansErreur;
    }

    public boolean checkLongueurDescription(String sValue) throws EchecSelectException {
        return checkLongueurMax("description", sValue, 2048);
    }

    public boolean checkLongueurDescription(ILibCodeDesc desc) throws EchecSelectException{
        boolean bSansErreur = true;

        if (desc != null) {
            bSansErreur = false;
            return checkLongueurDescription(desc.getDescription());
        }

        return bSansErreur;
    }

    // CONTROLES SUR L'INTERVALLE DE VALEUR
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean checkIntervalle(String clientId, Integer iValue, Integer lMin, Integer lMax) throws EchecSelectException {
        boolean bSansErreur = true;

        if (CheckType.estVide(lMin)) {
            return checkPlusPetit(clientId, iValue, lMax);
        }

        if (CheckType.estVide(iValue)) {
            if (0 < lMin) {
                bSansErreur = false;
                JsfUtil.addErrorMessage(clientId, "Valeur obligatoire");
            }
        } else if (!(lMin <= iValue && iValue <= lMax)) {
            bSansErreur = false;
            if (lMin == lMax) {
                JsfUtil.addErrorMessage(clientId, "la valeur doit être égale à " + lMin);
            } else {
                JsfUtil.addErrorMessage(clientId, "la valeur doit être comprise entre " + lMin + " et " + lMax);
            }
        }

        return bSansErreur;
    }

    public boolean checkPlusGrand(String clientId, Integer iValue, Integer lMin) throws EchecSelectException {
        boolean bSansErreur = true;

        if (CheckType.estVide(lMin)) {
            return true;
        }

        if (CheckType.estVide(iValue) || !(lMin <= iValue)) {
            bSansErreur = false;
            JsfUtil.addErrorMessage(clientId, "la valeur doit être supérieure à " + lMin);
        }

        return bSansErreur;
    }

    public boolean checkPositif(String clientId, Integer iValue) throws EchecSelectException {
        return checkPlusGrand(clientId, iValue, 0);
    }
    
    public boolean checkPlusPetit(String clientId, Integer iValue, Integer lMax) throws EchecSelectException {
        boolean bSansErreur = true;

        if (CheckType.estVide(lMax)) {
            return true;
        }

        if (iValue != null && !(iValue <= lMax)) {
            bSansErreur = false;
            JsfUtil.addErrorMessage(clientId, "la valeur doit être inférieure à " + lMax);
        }

        return bSansErreur;
    }

    // CONTROLES SUR LES DATES
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean checkAvant(java.util.Date dAvant, java.util.Date dApres) {
        return checkAvant(dAvant, dApres, "la date de début", "la date de fin");
    }

    public boolean checkAvant(java.util.Date dAvant, java.util.Date dApres, String nAvant, String nApres) {
        boolean bSansErreur = true;

        if (dApres != null && !JIDate.avantJour(dAvant, dApres)) {
            bSansErreur = false;
            JsfUtil.addErrorMessage(nAvant + " doit être antérieure à " + nApres);
        }
        return bSansErreur;
    }

    public boolean checkAvantAujourdhui(java.util.Date dDate, java.util.Date nDate) {
        boolean bSansErreur = true;

        if (dDate != null && JIDate.avantAujourdhui(dDate)) {
            bSansErreur = false;
            JsfUtil.addErrorMessage(nDate + " doit être antérieure à la date du jour");
        }
        return bSansErreur;
    }

    public boolean checkApresAujourdhui(java.util.Date dDate, String nDate) {
        boolean bSansErreur = true;

        if (dDate != null && JIDate.apresAujourdhui(dDate)) {
            bSansErreur = false;
            JsfUtil.addErrorMessage(nDate + " doit être postérieure à la date du jour");
        }
        return bSansErreur;
    }

    // CONTROLES SUR LES TYPES DE DONNEES
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    public boolean checkTypeDonnee(String clientId, TypeDonneeEnum typeDonnee, String valeur) {
//        boolean bSansErreur = CheckType.typeCorrect(typeDonnee, valeur);
//
//        if (!bSansErreur) {
//            JsfUtil.addErrorMessage(clientId, "La donnée n'est pas correcte");
//        }
//
//        return bSansErreur;
//    }
    
    public boolean checkAdresseEmail(String clientId, String email) {
        boolean bSansErreur = true;

        if (!estAdresseEmail(email)) {
            bSansErreur = false;
            JsfUtil.addErrorMessage(clientId, "La valeur saisie n'est pas une adresse email");
        }

        return bSansErreur;
    }
    
    public boolean estAdresseEmail(String email){
        Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$");
        Matcher m = p.matcher(email.toUpperCase());
        return m.matches();
    }
    
    public boolean checkTelephone(String clientId, String email) {
        boolean bSansErreur = true;

        if (!estAdresseEmail(email)) {
            bSansErreur = false;
            JsfUtil.addErrorMessage(clientId, "La valeur saisie n'est pas une adresse email");
        }

        return bSansErreur;
    }
    

}
