package com.cmu.sigicmu.admin.domaine;

import com.cmu.sigicmu.admin.domaine.Contact;
import com.cmu.sigicmu.param.domaine.TableValeur;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-08-16T13:07:27")
@StaticMetamodel(PersonneMorale.class)
public class PersonneMorale_ extends Personne_ {

    public static volatile SingularAttribute<PersonneMorale, String> ninea;
    public static volatile ListAttribute<PersonneMorale, Contact> contactList;
    public static volatile SingularAttribute<PersonneMorale, String> rcs;
    public static volatile SingularAttribute<PersonneMorale, TableValeur> formeJuridique;
    public static volatile SingularAttribute<PersonneMorale, String> objet;

}