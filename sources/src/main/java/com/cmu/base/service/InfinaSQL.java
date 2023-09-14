/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmu.base.service;

import com.cmu.base.domaine.BaseDomaine;

/**
 *
 * @author bsow
 */
public class InfinaSQL {
    
    private String sqlAll;
    private String sqlALias;
    private String sqlSelect;
    private String sqlFrom;
    private String sqlWhere;
    private String sqlOrderBy;
    
    public InfinaSQL() {        
    }
    
    public String getAllFromTable(BaseDomaine obj) {
        return getAllFromTable(obj.getClass().getSimpleName(), "o");
    }
    
    public String getAllFromTable(String sTable, String sAlias) {
        sqlAll = "select "+ sAlias +" from " + sTable + " "+ sAlias;
        return sqlAll;
    }
    
}
