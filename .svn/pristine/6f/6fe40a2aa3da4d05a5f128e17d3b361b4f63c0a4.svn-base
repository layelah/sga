/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmu.util;

import com.cmu.base.service.exception.EchecSelectException;
import com.cmu.sigicmu.param.domaine.TypeDonneeEnum;
import com.cmu.sigicmu.param.service.TableValeurTypeService;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bsow
 */
public class CheckType {

    private CheckType() {
    }

//    public static boolean typeCorrect(TypeDonneeEnum type, String sValue) {
//        boolean bCorrect = false;
//
//        switch (type) {
//            case STR:
//                bCorrect = estString(sValue);
//                break;
//            case NUM:
//                bCorrect = estNumerique(sValue);
//                break;
//            case DAT:
//                bCorrect = estDate(sValue);
//                break;
//            case BOL:
//                bCorrect = estBooleen(sValue);
//                break;
//            case DEC:
//                bCorrect = estDecimal(sValue);
//                break;
//            case HER:
//                bCorrect = estHeure(sValue);
//                break;
//            case ANN:
//                bCorrect = estAnnee(sValue);
//                break;
//            case COD:
//                bCorrect = estTableValeurCode(sValue);
//                break;
//        }
//
//        return bCorrect;
//    }
    
    public static boolean estVide(Collection c) {
        if (c == null || c.isEmpty()) {
            return true;
        }
        return (c.size() <= 0);
    }

    public static boolean estString(String sValue) {
        return true;
    }

    public static boolean estNumerique(String sValue) {
        return (getNumerique(sValue) != null);
    }

    public static boolean estDate(String sValue) {
        return (getDate(sValue) != null);
    }

    public static boolean estTableValeurCode(String sValue) {
        boolean bResult = false;

        bResult = (!estVide(sValue) && sValue.length() <= 20);

        return bResult;
    }

    public static boolean estTypeCode(String sValue, TableValeurTypeService tvtSrv) {
        boolean bResult = false;

        bResult = (sValue != null && sValue.length() == 3 && estNumerique(sValue));

        if (bResult) {
            try {
                bResult = tvtSrv.existeTypeCode(sValue);
            } catch (EchecSelectException ex) {
                Logger.getLogger(CheckType.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return bResult;
    }

    public static boolean estBooleen(String sValue) {
        return (getBooleen(sValue) != null);
    }

    public static boolean estDecimal(String sValue) {
        return (getDecimal(sValue) != null);
    }

    public static boolean estHeure(String sValue) {
        String str[] = sValue.split(":");

        if (str != null && str.length == 2) {
            Integer iH = getNumerique(str[0]);
            Integer iM = getNumerique(str[1]);

            return ((iH != null && iM != null) && (0 <= iH && iH <= 23) && (0 <= iM && iM <= 59));
        }
        return false;
    }

    public static String getHH(java.util.Date heure) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH");
            return dateFormat.format(heure);
        } catch (Exception e) {
        }
        return null;
    }

    public static String getMM(java.util.Date heure) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("mm");
            return dateFormat.format(heure);
        } catch (Exception e) {
        }
        return null;
    }

    public static boolean estAnnee(String sValue) {
        Integer iAnnee = getNumerique(sValue);

        return ((iAnnee != null) && (1800 <= iAnnee) && (iAnnee <= 2100));
    }

    public static Date getDate(String sValue) {
        Date date = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            dateFormat.setLenient(false);
            date = dateFormat.parse(sValue);
        } catch (Exception e) {
            date = null;
        }
        return date;
    }

    public static Timestamp toDBDateFormat(String sDate) {
        return new Timestamp(getDate(sDate).getTime());
    }

    public static Date getHeure(String sValue) {
        Date date = null;

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
            dateFormat.setLenient(false);
            date = dateFormat.parse(sValue);
        } catch (Exception e) {
            date = null;
        }
        return date;
    }

    public static Date getDateHeure(String sValue) {
        Date date = null;

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy à HH:mm");
            dateFormat.setLenient(false);
            date = dateFormat.parse(sValue);
        } catch (Exception e) {
            date = null;
        }
        return date;
    }

    public static Boolean getBooleen(String sValue, Boolean defaultValue) {
        Boolean num = getBooleen(sValue);
        if (num == null) {
            num = defaultValue;
        }

        return num;
    }

    public static Boolean getBooleen(String sValue) {
        String sBool = sValue.trim().toUpperCase();

        if (sBool.equals("1") || sBool.equals("TRUE") || sBool.equals("VRAI") || sValue.equals("OUI")) {
            return true;
        }

        if (sBool.equals("0") || sBool.equals("FALSE") || sBool.equals("FAUX") || sValue.equals("NON")) {
            return false;
        }

        return null;
    }

    public static Double getDecimal(String sValue) {
        Double dValue = null;

        try {
            dValue = Double.valueOf(sValue);
        } catch (Exception ex) {
            dValue = null;
        }
        return dValue;
    }

    public static Integer getNumerique(String sValue, Integer defaultValue) {
        Integer num = getNumerique(sValue);
        if (num == null) {
            num = defaultValue;
        }

        return num;
    }

    public static Integer getNumerique(String sValue) {
        Integer iValue = null;

        try {
            iValue = Integer.valueOf(sValue.trim());
        } catch (Exception ex) {
            iValue = null;
        }
        return iValue;
    }

    public static boolean estVide(String s) {
        return (s == null || s.trim().length() <= 0);
    }

    public static boolean estVide(Integer i) {
        return (i == null || i == 0);
    }
}
