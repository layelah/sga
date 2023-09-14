package com.cmu.sigicmu.admin.domaine;

import com.cmu.base.domaine.BaseLibCode_;
import com.cmu.sigicmu.admin.domaine.Agent;
import com.cmu.sigicmu.admin.domaine.Coordonnees;
import com.cmu.sigicmu.admin.domaine.Entite;
import com.cmu.sigicmu.param.domaine.Localite;
import com.cmu.sigicmu.param.domaine.TableValeur;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-08-16T13:07:27")
@StaticMetamodel(Entite.class)
public class Entite_ extends BaseLibCode_ {

    public static volatile SingularAttribute<Entite, Coordonnees> coord;
    public static volatile SingularAttribute<Entite, Agent> responsable;
    public static volatile SingularAttribute<Entite, Localite> departement;
    public static volatile SingularAttribute<Entite, Localite> commune;
    public static volatile SingularAttribute<Entite, TableValeur> champIntervention;
    public static volatile SingularAttribute<Entite, Entite> entiteRattachement;
    public static volatile ListAttribute<Entite, Agent> agentList;
    public static volatile SingularAttribute<Entite, TableValeur> type;
    public static volatile SingularAttribute<Entite, Localite> region;

}