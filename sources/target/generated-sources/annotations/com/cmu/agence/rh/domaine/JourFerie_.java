package com.cmu.agence.rh.domaine;

import com.cmu.base.domaine.BaseLib_;
import com.cmu.sigicmu.param.domaine.Annee;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-08-16T13:07:27")
@StaticMetamodel(JourFerie.class)
public class JourFerie_ extends BaseLib_ {

    public static volatile SingularAttribute<JourFerie, Boolean> estFixe;
    public static volatile SingularAttribute<JourFerie, Date> jour;
    public static volatile SingularAttribute<JourFerie, Boolean> estRecurrent;
    public static volatile SingularAttribute<JourFerie, Annee> annee;

}