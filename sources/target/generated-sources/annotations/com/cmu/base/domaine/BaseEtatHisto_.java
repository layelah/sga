package com.cmu.base.domaine;

import com.cmu.sigicmu.param.domaine.TableValeur;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-08-16T13:07:27")
@StaticMetamodel(BaseEtatHisto.class)
public abstract class BaseEtatHisto_ extends BaseDomaine_ {

    public static volatile SingularAttribute<BaseEtatHisto, Date> dateDebut;
    public static volatile SingularAttribute<BaseEtatHisto, TableValeur> etat;

}