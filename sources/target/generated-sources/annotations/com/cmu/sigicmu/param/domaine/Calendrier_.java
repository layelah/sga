package com.cmu.sigicmu.param.domaine;

import com.cmu.base.domaine.BaseDomaine_;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-08-16T13:07:27")
@StaticMetamodel(Calendrier.class)
public class Calendrier_ extends BaseDomaine_ {

    public static volatile SingularAttribute<Calendrier, Integer> jour;
    public static volatile SingularAttribute<Calendrier, Boolean> finAnnee;
    public static volatile SingularAttribute<Calendrier, Date> dateDuJour;
    public static volatile SingularAttribute<Calendrier, String> dateDuJourLib;
    public static volatile SingularAttribute<Calendrier, Integer> annee;
    public static volatile SingularAttribute<Calendrier, Boolean> finSemestre;
    public static volatile SingularAttribute<Calendrier, Boolean> finMois;
    public static volatile SingularAttribute<Calendrier, Boolean> finTrimestre;
    public static volatile SingularAttribute<Calendrier, Integer> mois;

}