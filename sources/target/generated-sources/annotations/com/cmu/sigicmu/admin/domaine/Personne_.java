package com.cmu.sigicmu.admin.domaine;

import com.cmu.base.domaine.BaseDomaine_;
import com.cmu.sigicmu.admin.domaine.Coordonnees;
import com.cmu.sigicmu.param.domaine.Localite;
import com.cmu.sigicmu.param.domaine.TableValeur;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-08-16T13:07:27")
@StaticMetamodel(Personne.class)
public class Personne_ extends BaseDomaine_ {

    public static volatile SingularAttribute<Personne, Localite> departement;
    public static volatile SingularAttribute<Personne, Date> dateNaissance;
    public static volatile SingularAttribute<Personne, Localite> commune;
    public static volatile SingularAttribute<Personne, TableValeur> typePiece;
    public static volatile SingularAttribute<Personne, String> nom;
    public static volatile SingularAttribute<Personne, String> persType;
    public static volatile SingularAttribute<Personne, String> surnomSigle;
    public static volatile SingularAttribute<Personne, String> lieuNaissance;
    public static volatile SingularAttribute<Personne, Coordonnees> coord;
    public static volatile SingularAttribute<Personne, Date> dateExpiration;
    public static volatile SingularAttribute<Personne, Date> dateDelivrance;
    public static volatile SingularAttribute<Personne, String> numPiece;
    public static volatile SingularAttribute<Personne, Localite> region;

}