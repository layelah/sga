package com.cmu.sigicmu.admin.domaine;

import com.cmu.base.domaine.BaseDomaine_;
import com.cmu.sigicmu.admin.domaine.PersonneMorale;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-08-16T13:07:27")
@StaticMetamodel(Contact.class)
public class Contact_ extends BaseDomaine_ {

    public static volatile SingularAttribute<Contact, String> fonction;
    public static volatile SingularAttribute<Contact, PersonneMorale> personneMorale;
    public static volatile SingularAttribute<Contact, String> telephone;
    public static volatile SingularAttribute<Contact, Boolean> estPresent;
    public static volatile SingularAttribute<Contact, String> nom;
    public static volatile SingularAttribute<Contact, String> email;

}