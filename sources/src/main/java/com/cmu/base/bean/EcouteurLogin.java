package com.cmu.base.bean;

import com.cmu.sigicmu.bureau.bean.AuthentificationBean;
import javax.faces.application.Application;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class EcouteurLogin implements PhaseListener{

    @Override
    public void afterPhase(PhaseEvent event) {        
    }

    @Override
    public void beforePhase(PhaseEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        String idVue = context.getViewRoot().getViewId();
        Application application = context.getApplication();
            System.out.print("::::::::::::::::::::::::::::::::::: connexion ::::::::::::::::::::::::::::::::::: ");
      
        AuthentificationBean sa = (AuthentificationBean)application.evaluateExpressionGet(context, "#{auth}", AuthentificationBean.class);
        
        //if (sa != null && sa.estAuthentifie() == false) {
        if ((sa == null) || (sa.estAuthentifie() == false)) {
            ViewHandler viewHandler = application.getViewHandler();
            UIViewRoot viewRoot = viewHandler.createView(context, "/BUR/connexion.xhtml");
            context.setViewRoot(viewRoot);
        } else {
            ViewHandler viewHandler = application.getViewHandler();
            UIViewRoot viewRoot = viewHandler.createView(context, idVue);
            context.setViewRoot(viewRoot);
        }
    }

    // La méthode appelée avant et après les phases
    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RENDER_RESPONSE;
    }
}