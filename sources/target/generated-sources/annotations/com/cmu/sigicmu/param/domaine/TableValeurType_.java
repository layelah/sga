package com.cmu.sigicmu.param.domaine;

import com.cmu.base.domaine.BaseLibCodeDesc_;
import com.cmu.sigicmu.param.domaine.TableValeur;
import com.cmu.sigicmu.param.domaine.TableValeurLib;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-08-16T13:07:27")
@StaticMetamodel(TableValeurType.class)
public class TableValeurType_ extends BaseLibCodeDesc_ {

    public static volatile SingularAttribute<TableValeurType, Boolean> estInvariant;
    public static volatile ListAttribute<TableValeurType, TableValeurLib> tableValeurLibList;
    public static volatile ListAttribute<TableValeurType, TableValeur> tableValeurList;
    public static volatile SingularAttribute<TableValeurType, Integer> nbChampSupplementaire;
    public static volatile SingularAttribute<TableValeurType, Boolean> estCache;

}