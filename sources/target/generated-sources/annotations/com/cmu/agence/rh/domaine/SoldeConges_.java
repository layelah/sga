package com.cmu.agence.rh.domaine;

import com.cmu.base.domaine.BaseDomaine_;
import com.cmu.sigicmu.admin.domaine.Agent;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-08-16T13:07:27")
@StaticMetamodel(SoldeConges.class)
public class SoldeConges_ extends BaseDomaine_ {

    public static volatile SingularAttribute<SoldeConges, Double> nbCongesSupplementaire;
    public static volatile SingularAttribute<SoldeConges, Agent> agent;
    public static volatile SingularAttribute<SoldeConges, Date> dateDernierCalcul;
    public static volatile SingularAttribute<SoldeConges, Double> nbCongesEncours;
    public static volatile SingularAttribute<SoldeConges, Double> nbCongesReliquat;
    public static volatile SingularAttribute<SoldeConges, Double> nbCongesAcquis;
    public static volatile SingularAttribute<SoldeConges, Double> nbJourPrisDeductible;
    public static volatile SingularAttribute<SoldeConges, Double> nbCongesEchus;
    public static volatile SingularAttribute<SoldeConges, Double> nbJourPrisNonDeductible;

}