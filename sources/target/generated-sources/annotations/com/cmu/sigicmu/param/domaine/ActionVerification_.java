package com.cmu.sigicmu.param.domaine;

import com.cmu.base.domaine.BaseLibDesc_;
import com.cmu.sigicmu.param.domaine.TableValeur;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-08-16T13:07:27")
@StaticMetamodel(ActionVerification.class)
public class ActionVerification_ extends BaseLibDesc_ {

    public static volatile SingularAttribute<ActionVerification, Boolean> estActive;
    public static volatile SingularAttribute<ActionVerification, Boolean> estObligatoire;
    public static volatile ListAttribute<ActionVerification, TableValeur> initiativeList;

}