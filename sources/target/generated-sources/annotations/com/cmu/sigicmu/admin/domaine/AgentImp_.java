package com.cmu.sigicmu.admin.domaine;

import com.cmu.sigicmu.admin.domaine.AgentImp;
import com.cmu.sigicmu.admin.domaine.Entite;
import com.cmu.sigicmu.admin.domaine.Service;
import com.cmu.sigicmu.admin.domaine.Utilisateur;
import com.cmu.sigicmu.param.domaine.TableValeur;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-08-16T13:07:27")
@StaticMetamodel(AgentImp.class)
public class AgentImp_ extends PersonnePhysique_ {

    public static volatile SingularAttribute<AgentImp, String> matricule;
    public static volatile SingularAttribute<AgentImp, String> libFonction;
    public static volatile SingularAttribute<AgentImp, String> telBureau;
    public static volatile SingularAttribute<AgentImp, Date> dateRecrutement;
    public static volatile SingularAttribute<AgentImp, AgentImp> superviseur;
    public static volatile SingularAttribute<AgentImp, String> numeroPoste;
    public static volatile SingularAttribute<AgentImp, Entite> entite;
    public static volatile SingularAttribute<AgentImp, Date> datePriseFonction;
    public static volatile SingularAttribute<AgentImp, Utilisateur> utilisateur;
    public static volatile SingularAttribute<AgentImp, Service> service;
    public static volatile SingularAttribute<AgentImp, TableValeur> fonction;
    public static volatile SingularAttribute<AgentImp, String> telProfessionnel;
    public static volatile SingularAttribute<AgentImp, String> emailProfessionnel;
    public static volatile SingularAttribute<AgentImp, String> cni;

}