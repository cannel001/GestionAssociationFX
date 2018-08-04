package com.objis.gestassociation.dao.impl;

import com.objis.gestassociation.dao.impl.Dao;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.objis.gestassociation.domaine.Rencontre;

public class RencontreDao extends Dao<Rencontre, Long> {

    //les proprietes
    private static String CREATE_QUERY = "insert into rencontre (dateRencontre,decisionPrise,description,idRencontre,lieu,nbParticipants,ordreJr,presencePresi,etat) values (?,?,?,?,?,?,?,?,?)";
    private static String READONE_QUERY = "select * from rencontre where idRencontre=? and etat='ACTIF";
    private static String READALL_QUERY = "select * from rencontre where etat=?";
    private static String UPDATE_QUERY = "update rencontre set dateRencontre=?,decisionPrise=?,description=?,lieu=?,nbParticipants=?,ordreJr=?,presencePresi=? where idRencontre=?";
    private static String DELETE_QUERY = "update rencontre set etat=? where id=?";
    private static String LASTELEMENT_QUERY = "select * from rencontre where etat='ACTIF' order by id desc limit 1";

    private static int executeQuery = -1;

    @Override
    public Boolean create(Rencontre t) {

        try {

            ps = createStatement(CREATE_QUERY);

            ps.setDate(1, Date.valueOf(t.getDateRencontre()));
            ps.setString(2, t.getDecisionPrise());
            ps.setString(3, t.getDescription());
            ps.setLong(4, t.getIdRencontre());
            ps.setString(5, t.getLieu());
            ps.setInt(6, t.getNbParticipants());
            ps.setString(7, t.getOrdreJr());
            ps.setString(8, t.getPresencePresi());
            ps.setString(9, t.getEtat());

            executeQuery = ps.executeUpdate();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return executeQuery > 0;
    }

    @Override
    public Rencontre readOne(Long pk) {

        Rencontre rencontre = null;

        try {

            ps = createStatement(READONE_QUERY);

            ps.setLong(1, pk);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                rencontre = new Rencontre(rs.getString("description"), rs.getString("lieu"), rs.getInt("nbParticipants"), rs.getString("presencePresi"), rs.getString("ordreJr"), rs.getString("decisionPrise"), rs.getDate("dateRencontre").toLocalDate(), rs.getLong("idRencontre"), rs.getString("etat"));

            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return rencontre;
    }

    @Override
    public Boolean update(Rencontre t) {

        try {

            ps = createStatement(UPDATE_QUERY);

            ps.setDate(1, Date.valueOf(t.getDateRencontre()));
            ps.setString(2, t.getDecisionPrise());
            ps.setString(3, t.getDescription());
            ps.setString(5, t.getLieu());
            ps.setInt(6, t.getNbParticipants());
            ps.setString(7, t.getOrdreJr());
            ps.setString(8, t.getPresencePresi());

            executeQuery = ps.executeUpdate();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return executeQuery > 0;
    }

    @Override
    public Boolean delete(Long pk) {

        try {

            ps = createStatement(DELETE_QUERY);

            ps.setLong(1, pk);

            executeQuery = ps.executeUpdate();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return executeQuery > 0;
    }

    @Override
    public List<Rencontre> readAll() {

        List<Rencontre> maLste = new LinkedList<>();
        Rencontre rencontre = null;

        try {

            ps = createStatement(READALL_QUERY);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                rencontre = new Rencontre(rs.getString("description"), rs.getString("lieu"), rs.getInt("nbParticipants"), rs.getString("presencePresi"), rs.getString("ordreJr"), rs.getString("decisionPrise"), rs.getDate("dateRencontre").toLocalDate(), rs.getLong("idRencontre"), rs.getString("etat"));

                maLste.add(rencontre);

            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return maLste;
    }

    //methode permettant de retourner le dernier enregistrement
    public List<Rencontre> lastElement() {

        Rencontre rencontre = null;

        List<Rencontre> maListe = new LinkedList<>();

        try {

            ps = createStatement(LASTELEMENT_QUERY);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                rencontre = new Rencontre(rs.getString("description"), rs.getString("lieu"), rs.getInt("nbParticipants"), rs.getString("presencePresi"), rs.getString("ordreJr"), rs.getString("decisionPrise"), rs.getDate("dateRencontre").toLocalDate(), rs.getLong("idRencontre"), rs.getString("etat"));

                maListe.add(rencontre);

            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return maListe;

    }

}
