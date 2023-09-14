package com.cmu.agence.rh.domaine;

import com.cmu.agence.rh.domaine.DemandePermissionAbsence;
import com.cmu.base.domaine.BaseDomaine_;
import com.cmu.sigicmu.admin.domaine.Utilisateur;
import com.cmu.sigicmu.param.domaine.TableValeur;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-08-16T13:07:27")
@StaticMetamodel(ValidationDemande.class)
public class ValidationDemande_ extends BaseDomaine_ {

    public static volatile SingularAttribute<ValidationDemande, String> note;
    public static volatile SingularAttribute<ValidationDemande, TableValeur> niveauValidation;
    public static volatile SingularAttribute<ValidationDemande, DemandePermissionAbsence> demandePermissionAbsence;
    public static volatile SingularAttribute<ValidationDemande, Utilisateur> validateur;
    public static volatile SingularAttribute<ValidationDemande, Date> dateValidation;
    public static volatile SingularAttribute<ValidationDemande, TableValeur> etat;
    public static volatile SingularAttribute<ValidationDemande, Integer> validation;
    public static volatile SingularAttribute<ValidationDemande, String> statut;

}