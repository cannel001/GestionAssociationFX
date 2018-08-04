package com.objis.gestassociation.dao.impl;

import com.objis.gestassociation.dao.impl.Dao;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.objis.gestassociation.domaine.VieSociale;

public class VieSocialeDao extends Dao<VieSociale, Long> {

    //les proprietes
    private static String createStatement = "insert into viesociale (dateDebut,dateFin,montant,idAdherent,etatVieSociale,typeAssurance,idVieSociale) values(?,?,?,?,?,?,?)";
    private static String updateStatement = "update viesociale set dateDebut=?,dateFin=?,montant=?,idAdherent=?,typeAssurance=? where idVieSociale=?";
    private static String readOneStatement = " select * from viesociale where idVieSociale=? and etatVieSociale='ACTIF'";
    private static String readAllStatement = "select * from viesociale where etatVieSociale='ACTIF'";
    private static String deleteStatement = "update viesociale set etatVieSociale='SUPPRIMER' where idVieSociale=?";
    private static String lastElement = "select * from viesociale order by idVieSociale desc limit 1";

    private static int executeQuery = -1;

    @Override
    public Boolean create(VieSociale t) {
        // TODO Auto-generated method stub
        ps = createStatement(createStatement);

        try {

            ps.setDate(1, Date.valueOf(t.getDateDebut()));
            ps.setDate(2, Date.valueOf(t.getDateFin()));
            ps.setDouble(3, t.getMontant());
            ps.setLong(4, t.getIdAdherent());
            ps.setString(5, t.getEtatVieSociale());
            ps.setString(6, t.getTypAssurance());
            ps.setLong(7, t.getIdVieSociale());

            executeQuery = ps.executeUpdate();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return executeQuery > 0;
    }

    @Override
    public VieSociale readOne(Long pk) {
        // TODO Auto-generated method stub

        VieSociale vieSociale = null;

        ps = createStatement(readOneStatement);

        try {

            ps.setLong(1, pk);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                vieSociale = new VieSociale(rs.getLong("idVieSociale"), rs.getDate("dateDebut").toLocalDate(), rs.getDate("dateFin").toLocalDate(), rs.getDouble("montant"), rs.getLong("idAdherent"), rs.getString("typeAssurance"), rs.getString("etatVieSociale"));

            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return vieSociale;
    }

    @Override
    public Boolean update(VieSociale t) {
        // TODO Auto-generated method stub

        ps = createStatement(updateStatement);

        try {

            ps.setDate(1, Date.valueOf(t.getDateDebut()));
            ps.setDate(2, Date.valueOf(t.getDateFin()));
            ps.setDouble(3, t.getMontant());
            ps.setLong(4, t.getIdAdherent());
            ps.setString(5, t.getTypAssurance());
            ps.setLong(6, t.getIdVieSociale());

            executeQuery = ps.executeUpdate();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return executeQuery > 0;
    }

    @Override
    public Boolean delete(Long pk) {
        // TODO Auto-generated method stub

        ps = createStatement(deleteStatement);

        try {

            ps.setLong(1, pk);

            executeQuery = ps.executeUpdate();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return executeQuery > 0;
    }

    @Override
    public List<VieSociale> readAll() {
        // TODO Auto-generated method stub

        List<VieSociale> listeVieSociale = new LinkedList<>();

        ps = createStatement(readAllStatement);

        try {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                listeVieSociale.add(new VieSociale(rs.getLong("idVieSociale"), rs.getDate("dateDebut").toLocalDate(), rs.getDate("dateFin").toLocalDate(), rs.getDouble("montant"), rs.getLong("idAdherent"), rs.getString("typeAssurance"), rs.getString("etatVieSociale")));

            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return listeVieSociale;
    }

    //methode permettant de retourner le dernier enregistrement
    public VieSociale lastElement() {

        VieSociale vieSociale = null;

        ps = createStatement(lastElement);

        try {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                vieSociale = new VieSociale(rs.getLong("idVieSociale"), rs.getDate("dateDebut").toLocalDate(), rs.getDate("dateFin").toLocalDate(), rs.getDouble("montant"), rs.getLong("idAdherent"), rs.getString("typeAssurance"), rs.getString("etatVieSociale"));

            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return vieSociale;

    }

}
