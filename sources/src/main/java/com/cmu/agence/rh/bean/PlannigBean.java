/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmu.agence.rh.bean;


import com.cmu.agence.rh.domaine.Absence;
import com.cmu.agence.rh.service.AbsenceService;
import com.cmu.base.bean.BaseBean;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named("planning")
@SessionScoped
public class PlannigBean extends BaseBean{

    @EJB
    private AbsenceService as;
    
    public List<Absence> getPlanninglistAbs() {
        return as.getPlanningAbsence();
    }   
}
