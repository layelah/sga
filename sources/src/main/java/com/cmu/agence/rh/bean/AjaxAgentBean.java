/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmu.agence.rh.bean;

import com.cmu.agence.rh.service.AjaxAgentService;
import com.cmu.base.service.exception.EchecSelectException;
import com.cmu.sigicmu.admin.domaine.Entite;
import com.cmu.sigicmu.admin.domaine.Service;
import com.cmu.sigicmu.param.domaine.TableValeur;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

@Named(value = "ajaxAgent")
@SessionScoped
public class AjaxAgentBean implements Serializable {

    @EJB
    AjaxAgentService ajs;

    List<SelectItem> listService;
    List<SelectItem> listAgent;

    public void listenerEntite(Entite entite) {
             try {
            listService = ajs.getServices(entite);
            System.out.println("listenerEntite - getServices = " + listService.size());

        } catch (EchecSelectException ex) {
            Logger.getLogger(AjaxAgentBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void listenerService(Service service) {
        try {
            listAgent = ajs.getAgents(service);
            System.out.println("listenerService - getAgents = " + listAgent.size());

        } catch (EchecSelectException ex) {
            Logger.getLogger(AjaxAgentBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void listenerAbsence(TableValeur tyAbsence) {
        System.out.println("absence ++ " + tyAbsence);
    }

    public List<SelectItem> getListService() {
        return listService;
    }

    public void setListService(List<SelectItem> listService) {
        this.listService = listService;
    }

    public List<SelectItem> getListAgent() {
        return listAgent;
    }

    public void setListAgent(List<SelectItem> listAgent) {
        this.listAgent = listAgent;
    }

}
