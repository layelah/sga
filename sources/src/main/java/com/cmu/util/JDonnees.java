package com.cmu.util;

import com.cmu.sigicmu.param.domaine.TableValeur;
import com.cmu.sigicmu.param.domaine.TypeDonneeEnum;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class JDonnees {

    public static Object getValue(TableValeur typeDeDonnee, String sValue) {
        String typDonnee = typeDeDonnee.getCode();
        
        Object obj = null;
        
        if (JUtil.estVide(sValue)) {
            return null;
        }
        
        System.out.println("getValue typDonnee = "+ typDonnee +" /  sValue = "+ sValue);
                
        
        if (typDonnee.equals(TypeDonneeEnum.Booleen.toString())) {
            return JDonnees.getBoolean(sValue);
        } if (typDonnee.equals(TypeDonneeEnum.CNI.toString())) {
            return JDonnees.getCNI(sValue);
        } if (typDonnee.equals(TypeDonneeEnum.Date.toString())) {
            return JDonnees.getDateExcel(sValue);
        } if (typDonnee.equals(TypeDonneeEnum.Decimal.toString())) {
            return JDonnees.getDecimal(sValue);
        } if (typDonnee.equals(TypeDonneeEnum.Numerique.toString())) {
            return JDonnees.getInteger(sValue);            
        } if (typDonnee.equals(TypeDonneeEnum.Texte.toString())) {
            return JUtil.trim(sValue);
        } 
        
        System.out.println("getValue FIN");
        return obj;
    }
    
    public static boolean typeEstCorrect(TableValeur typeDeDonnee, String sValue) {
        System.out.println("typeEstCorrect DEBUT");
        
        String typDonnee = typeDeDonnee.getCode();
        
        JUtil.s("typeEstCorrect typDonnee = ["+ typDonnee +"] / sValue = ["+ sValue +"]");
        
        if (JUtil.estVide(sValue)) {
            System.out.println("typeEstCorrect VIDE");
            return true;
            
        } if (typDonnee.equals(TypeDonneeEnum.Booleen.toString())) {
            return JDonnees.estBoolean(sValue);
            
        } if (typDonnee.equals(TypeDonneeEnum.Date.toString())) {
            return JDonnees.estDateExcel(sValue);
            
        } if (typDonnee.equals(TypeDonneeEnum.Decimal.toString())) {
            return JDonnees.estDecimal(sValue);
            
        } if (typDonnee.equals(TypeDonneeEnum.Numerique.toString())) {
            return JDonnees.estInteger(sValue);
            
        } if (typDonnee.equals(TypeDonneeEnum.CNI.toString())) {
            return JDonnees.estHeure(sValue);
            
        } if (typDonnee.equals(TypeDonneeEnum.Texte.toString())) {
            return JDonnees.estString(sValue);
        }
        
        System.out.println("typeEstCorrect FIN");
        return false;
    }
    
    public static boolean estAnnee(String sValue) {
        int iValue = 0;
       
        try {
            iValue = Integer.parseInt(sValue);
            return ((1900 <= iValue) && (iValue <= 2100));
        } catch (Exception ex) {
        }
       
        return false;
    }
    
    public static boolean estBoolean(String sValue) {
        String sBool = sValue.trim().toUpperCase().charAt(0) +"";
        
        try {
            return (sBool.equals("N") || 
                    sBool.equals("O") || 
                    sBool.equals("1") || 
                    sBool.equals("0") || 
                    sBool.equals("Y") || 
                    sBool.equals("T") || 
                    sBool.equals("F")); 
        } catch (Exception ex) {
        }
        
        return false;
    }
    
    public static boolean estCode(String sValue) {
        return false;
    }
    
    public static boolean estString(String sValue) {
        return true;
    }
    
    public static boolean estDateExcel(String sValue) {
        if (JUtil.estVide(sValue)) {
            return true;
        } else {
            return (getDateExcel(sValue) != null);
        }
    }
    
    public static boolean estHeure(String sValue) {
        return JIDate.estHeure(sValue);        
    }
    
    public static boolean estCNI(String sValue) {
        String sResult = "";
        
        if (JUtil.estVide(sValue)) {
            return true;
        } else {
            sResult = sValue.replace(" ", "");
            
            return ((estInteger(sResult)) && (sResult.length() == 13));
        }
        
    }
    
    
    public static boolean estInteger(String sValue) {
        boolean bResult = false;
        
        try {
            int iValue = Integer.parseInt(sValue);
            bResult = true;
        } catch (Exception ex) {
        }

        return bResult;
    }
    
    public static int getInteger(String sValue) {
        int iValue = 0;
       
        try {
            iValue = Integer.parseInt(sValue);
        } catch (Exception ex) {
        }
       
        return iValue;
    }
    
    public static boolean estDecimal(String sValue) {
        boolean bResult = false;
        
        try {
            double dblValue = Double.parseDouble(sValue);
            bResult = true;
        } catch (Exception ex) {
        }

        return bResult;
    }
    
    public static double getDecimal(String sValue) {
        double dblValue = 0.0;
       
        try {
            dblValue = Double.parseDouble(sValue);
        } catch (Exception ex) {
        }
       
        return dblValue;
    }
        
    public static String getString(String sValue) {
        return sValue.trim();
    }
    
    public static Boolean getBoolean(String sValue) {
        Boolean bValue = null;
        
        return bValue;
    }
     
    public static Date getDateExcel(String sValue) {      
        try {
            long nbDays = Long.parseLong(sValue);
            GregorianCalendar gc = new GregorianCalendar(1900, Calendar.JANUARY, 1);
            gc.setTimeInMillis(gc.getTimeInMillis()+ (nbDays-1)*24*60*60*1000);
            return gc.getTime();
            
        } catch (NumberFormatException ex) {            
        }
        
        return null;
    }
    
    /*
    public static Date getDate(String sValue) {        
        return JIDate.getDate(sValue);
    }
    */
    
    public static Date getHeure(String sValue) {
        return JIDate.getHeure(sValue);
    }
    
    public static String getCNI(String sValue) {
        String sResult = "";
        
        if (!JUtil.estVide(sValue)) {
            sResult = sValue.replace(" ", "");
        }
        
        return sValue;
    }
    
    public static Integer getAnnee(String sValue) {
        int iValue = 0;
       
        try {
            iValue = Integer.parseInt(sValue);
            if ((1900 <= iValue) && (iValue <= 2100)) {
                return iValue;
            }
        } catch (Exception ex) {
        }
       
        return null;
    }
    
}
