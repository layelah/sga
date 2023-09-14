package com.cmu.sigicmu.param.bean;

import com.cmu.base.bean.BaseCRUDBean;
import com.cmu.base.domaine.BaseDomaine;
import com.cmu.sigicmu.param.domaine.ActionVerification;
import com.cmu.sigicmu.param.domaine.WebRacineEnum;
import com.cmu.sigicmu.param.service.TableValeurService;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;

@Named("actionVerificationBean")
@javax.enterprise.context.SessionScoped
public class ActionVerificationBean extends BaseCRUDBean {

    private List<String> selectedId;
    
    @EJB
    TableValeurService tvSrv;
    
    @Override
    public BaseDomaine getNewInstance() {
        return new ActionVerification();
    }
    
    public ActionVerificationBean(){
        super(WebRacineEnum.PARAMETRAGE, "actionVerification");
    }
    
    public List<String> getSelectedId() {
        return selectedId;
    }

    public void setSelectedId(List<String> selectedId) {
        this.selectedId = selectedId;
    }
}
