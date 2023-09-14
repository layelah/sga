
package com.cmu.agence.rh.bean;

import com.cmu.base.bean.BaseBean;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named("rh")
@SessionScoped
public class ConnexionBean extends BaseBean implements Serializable {

    private String mdp;
    private String login;

    public String connecter() {

        return "/BUR/bienvenueRH.xhtml";
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
