package com.cmu.sigicmu.admin.domaine;

import com.cmu.base.domaine.BaseLibCodeDesc_;
import com.cmu.sigicmu.admin.domaine.Agent;
import com.cmu.sigicmu.admin.domaine.Service;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-08-16T13:07:27")
@StaticMetamodel(Service.class)
public class Service_ extends BaseLibCodeDesc_ {

    public static volatile SingularAttribute<Service, String> code;
    public static volatile SingularAttribute<Service, String> lib;
    public static volatile SingularAttribute<Service, Service> serviceRattachement;
    public static volatile SingularAttribute<Service, Agent> chefDeService;
    public static volatile ListAttribute<Service, Agent> agentList;
    public static volatile SingularAttribute<Service, Integer> niveau;

}