package com.cmu.sigicmu.param.domaine;

import com.cmu.base.domaine.BaseDomaine_;
import com.cmu.sigicmu.param.domaine.Localite;
import com.cmu.sigicmu.param.domaine.TableValeur;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-08-16T13:07:27")
@StaticMetamodel(Population.class)
public class Population_ extends BaseDomaine_ {

    public static volatile SingularAttribute<Population, Integer> nbFemme;
    public static volatile SingularAttribute<Population, TableValeur> zoneGeographique;
    public static volatile SingularAttribute<Population, Localite> localite;
    public static volatile SingularAttribute<Population, Integer> nbHomme;
    public static volatile SingularAttribute<Population, Integer> nbTotal;
    public static volatile SingularAttribute<Population, TableValeur> anneeRecensement;

}