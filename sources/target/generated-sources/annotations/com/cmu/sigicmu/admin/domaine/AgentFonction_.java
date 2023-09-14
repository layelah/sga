package com.cmu.sigicmu.admin.domaine;

import com.cmu.base.domaine.BaseDomaine_;
import com.cmu.sigicmu.admin.domaine.Agent;
import com.cmu.sigicmu.admin.domaine.Service;
import com.cmu.sigicmu.param.domaine.TableValeur;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-08-16T13:07:27")
@StaticMetamodel(AgentFonction.class)
public class AgentFonction_ extends BaseDomaine_ {

    public static volatile SingularAttribute<AgentFonction, TableValeur> typeOrgane;
    public static volatile SingularAttribute<AgentFonction, Agent> agent;
    public static volatile SingularAttribute<AgentFonction, String> notes;
    public static volatile SingularAttribute<AgentFonction, Date> dateDebut;
    public static volatile SingularAttribute<AgentFonction, Service> service;
    public static volatile SingularAttribute<AgentFonction, TableValeur> fonction;
    public static volatile SingularAttribute<AgentFonction, Date> dateFin;

}