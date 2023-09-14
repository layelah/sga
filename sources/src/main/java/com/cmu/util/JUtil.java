package com.cmu.util;

import com.cmu.base.domaine.BaseDomaine;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public final class JUtil {
    
    private JUtil() {
    }
    
    public static int longueur(String s){
        return (s == null ? 0 : s.length());
    }
    
    public static void s(String s){
        //System.out.println(s);
    }
    
    public static void sMarque(String s){
        //System.out.println("-------------------- "+ s +"-------------------- ");
    }
    
    public static void s(String s, String v){
        //System.out.println(s +" = "+ v);
    }
    
    public static void s(String s, int d){
        //System.out.println(s +" = "+ d);
    }
    
    public  static void s(int i, String s){
        //System.out.println("Etape "+i +" - "+ s);
    }
    
    public  static void s(int i, String s, String value){
        //System.out.println("Etape "+i +" - "+ s +" ["+ value +"]");
    }

    public static String trim(String s){
        return (s == null ? null : s.trim());
    }
    
    public static String debugObj(BaseDomaine obj) {
        if (obj == null) {
            return "OBJ IS NULL";
        } else if ((obj.getId() == null) || (obj.getId() == 0)) {
            return "ID IS NULL";
        } else
            return "ID = ["+ obj.getId() +"]";
    }
    
     public static String listObjToStrId(List<BaseDomaine> l) {
        return null;
    }

     public static String listIdToStrId(List<Integer> l) {
        String str = "(0";
        for (Integer id: l) {
            str += ","+ id;
        }
        str += ")";
        return str;
    }
    

    public static int size(List l) {
        return (l == null ? 0 : l.size());        
    }
    
    public static boolean estVide(Date valeur) {
        return (valeur == null);
    }
    
    public static boolean estVide(Integer valeur) {
        return ((valeur == null) || (valeur <= 0));
    }
    
    public static boolean estVide(Double valeur) {
        return ((valeur == null) || (valeur <= 0.001));
    }
    
    public static boolean estVide(List listObj) {
        return (listObj == null || listObj.size() <= 0);
    }
    
    public static boolean estVide(BaseDomaine obj) {
        return (BaseDomaine.idIsNull(obj));
    }
    
    public static boolean estNull(BaseDomaine obj) {
        return (BaseDomaine.idIsNull(obj));
    }
    
    public static boolean estVide(String s) {
        return (s == null || s.trim().length() <= 0);
    }

    public static boolean estVide(Object[] listObj) {
        return (listObj == null || listObj.length <= 0);
    }
    
    public static String premiereLettreEnMajuscule(String sValeur) {
        char[] char_table = sValeur.toCharArray();
        char_table[0]=Character.toUpperCase(char_table[0]);
        return new String(char_table);
    }
    
    public static boolean contient(Integer[] listId, Integer id) {
        Boolean bContient = false;
        
        if (!estVide(listId)) {
            for (int i = 0; i < listId.length; i++) {
                if (listId[i].equals(id)) {
                    return true;
                }
            }
        }
        return bContient;
    }
    
    
    public static boolean contient(List<BaseDomaine> listObj, BaseDomaine obj) {
        Boolean bContient = false;
        
        if (!estVide(listObj)) {
            for (BaseDomaine m_obj: listObj) {
                if ((m_obj.getClass().equals(obj.getClass())) && (Objects.equals(m_obj.getId(), obj.getId()))) {
                    return true;
                }
            }
        }
        return bContient;
    }
    
    public static int max(int a, int b) {
        return (a < b ? b : a);
    }
    
    public static int min(int a, int b) {
        return (a < b ? a : b);
    }
    
    public static boolean vrai(Boolean bValue) {
        return (bValue != null && bValue == Boolean.TRUE);
    }
    
    public static String fmt(Double d) {
        DecimalFormat myFormatter = new DecimalFormat("###,###,###,###");
        return myFormatter.format(d);
    }
    
    public static String fmtDecimal(Double d) {
        DecimalFormat myFormatter = new DecimalFormat("###,###,###,###.##");
        return myFormatter.format(d);
    }

    public static String fmtCurrency(Double d) {
        DecimalFormat myFormatter = new DecimalFormat("###,###,###,### FCFA");
        return myFormatter.format(d);
    }
}
