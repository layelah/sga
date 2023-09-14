package com.cmu.agence.rh.service;

import com.cmu.base.service.CommonLibService;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean

public class RapportCongeService {

    @EJB
    CommonLibService srv;

    public Map<String, Object> situationCongeAnneePrec(int agent_id, int anneeEnCours) {
        Connection con = null;
        Map<String, Object> list = null;

        try {
            con = srv.getJDBCConnection();

            Statement statement = con.createStatement();
            ResultSet resultat = statement.executeQuery("SELECT sum(nb_conges_acquis) as nb_CA, sum(nb_jour_pris) as nb_JP  FROM solde_conges where agent_id=" + agent_id + " and annee_id!=" + anneeEnCours);
            list = new HashMap<String, Object>();
            while (resultat.next()) {
                int nb1 = resultat.getInt("nb_CA");
                int nb2 = resultat.getInt("nb_JP");

                list.put("1",nb1);
                list.put("2",nb2);
            }

            return list;

        } catch (Exception e) {
            System.out.println("exception :: " + e);
        }
        return null;
    }
    
    public Map<String, Object> situationCongeAnneeCour(int agent_id, int anneeEnCours) {
        Connection con = null;
        Map<String, Object> list = null;

        try {
            con = srv.getJDBCConnection();

            Statement statement = con.createStatement();
            ResultSet resultat = statement.executeQuery("SELECT nb_conges_acquis as nb_CA, nb_jour_pris as nb_JP  FROM solde_conges where agent_id=" + agent_id + " and annee_id=" + anneeEnCours);
            list = new HashMap<String, Object>();
            while (resultat.next()) {
                int nb1 = resultat.getInt("nb_CA");
                int nb2 = resultat.getInt("nb_JP");

                list.put("1",nb1);
                list.put("2",nb2);
            }

            return list;

        } catch (Exception e) {
            System.out.println("exception :: " + e);
        }
        return null;
    }
}
