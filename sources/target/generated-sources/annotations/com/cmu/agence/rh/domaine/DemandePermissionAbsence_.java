package com.cmu.agence.rh.domaine;

import com.cmu.agence.rh.domaine.Absence;
import com.cmu.agence.rh.domaine.ValidationDemande;
import com.cmu.base.domaine.BaseDomaine_;
import com.cmu.sigicmu.param.domaine.TableValeur;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-08-16T13:07:27")
@StaticMetamodel(DemandePermissionAbsence.class)
public class DemandePermissionAbsence_ extends BaseDomaine_ {

    public static volatile SingularAttribute<DemandePermissionAbsence, Absence> absence;
    public static volatile SingularAttribute<DemandePermissionAbsence, Integer> insertUserId;
    public static volatile ListAttribute<DemandePermissionAbsence, ValidationDemande> validationDemandeList;
    public static volatile SingularAttribute<DemandePermissionAbsence, Date> dateAcceptation;
    public static volatile SingularAttribute<DemandePermissionAbsence, Date> dateDemande;
    public static volatile SingularAttribute<DemandePermissionAbsence, TableValeur> etat;

}