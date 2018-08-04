package com.objis.gestassociation.dao.impl;

import com.objis.gestassociation.dao.impl.Dao;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.objis.gestassociation.domaine.Mouvement;

public class MouvementDao extends Dao<Mouvement, Long> {

    //les proprietes
    private static String createStatement = "insert into mouvement (id,date,nature,quantite,typMouvement,etatMouvement) values(?,?,?,?,?,?)";
    private static String readOneStatement = "select * from mouvement where etatMouvement='ACTIF' and id=?";
    private static String updateStatement = "update mouvement set date=?,nature=?,quantite=?,typMouvement=?,etatMouvement=? where id=?";
    private static String deleteStatement = "update mouvement set etatMouvement='SUPPRIMER' where id=?";
    private static String readAllStatement = "select * from mouvement where etatMouvement='ACTIF'";
    private static String lastElementStatement = "select * from mouvement order by id desc limit 1;";
    private static String listEntreeStatement = "select * from mouvement where etatMouvement='ACTIF' and typMouvement='ENTREE'";
    private static String listSortieStatement = "select * from mouvement where etatMouvement='ACTIF' and typMouvement='SORTIE'";
    private static int executeQuery = -1;

    @Override
    public Boolean create(Mouvement t) {

        ps = createStatement(createStatement);

        try {

            ps.setLong(1, t.getId());
            ps.setDate(2, Date.valueOf(t.getDate()));
            ps.setString(3, t.getNature());
            ps.setDouble(4, t.getQuantite());
            ps.setString(5, t.getTypMouvement());
            ps.setString(6, t.getEtatMouvement());

            executeQuery = ps.executeUpdate();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return executeQuery > 0;
    }

    @Override
    public Mouvement readOne(Long pk) {

        Mouvement mouvement = null;

        ps = createStatement(readOneStatement);

        try {

            ps.setLong(1, pk);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                mouvement = new Mouvement(rs.getLong("id"), rs.getString("nature"), rs.getDouble("quantite"), rs.getString("typMouvement"), rs.getDate("date").toLocalDate(), rs.getString("etatMouvement"));

            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return mouvement;
    }

    @Override
    public Boolean update(Mouvement t) {

        ps = createStatement(updateStatement);

        try {

            ps.setDate(1, Date.valueOf(t.getDate()));
            ps.setString(2, t.getNature());
            ps.setDouble(3, t.getQuantite());
            ps.setString(4, t.getTypMouvement());
            ps.setString(5, t.getEtatMouvement());
            ps.setLong(6, t.getId());

            executeQuery = ps.executeUpdate();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return executeQuery > 0;
    }

    @Override
    public Boolean delete(Long pk) {

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
    public List<Mouvement> readAll() {

        List<Mouvement> listMouvement = new LinkedList<>();

        ps = createStatement(readAllStatement);

        try {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                listMouvement.add(new Mouvement(rs.getLong("id"), rs.getString("nature"), rs.getDouble("quantite"), rs.getString("typMouvement"), rs.getDate("date").toLocalDate(), rs.getString("etatMouvement")));

            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return listMouvement;
    }

    //methode permettant de retourner le dernier enregistrement
    public Mouvement lastElement() {

        Mouvement mouvement = null;

        ps = createStatement(lastElementStatement);

        try {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                mouvement = new Mouvement(rs.getLong("id"), rs.getString("nature"), rs.getDouble("quantite"), rs.getString("typMouvement"), rs.getDate("date").toLocalDate(), rs.getString("etatMouvement"));

            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return mouvement;

    }

    //methode permettant de retourner la liste des entrées
    public List<Mouvement> getListEntree() {

        List<Mouvement> listMouvement = new LinkedList<>();

        ps = createStatement(listEntreeStatement);

        try {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                listMouvement.add(new Mouvement(rs.getLong("id"), rs.getString("nature"), rs.getDouble("quantite"), rs.getString("typMouvement"), rs.getDate("date").toLocalDate(), rs.getString("etatMouvement")));

            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return listMouvement;
    }

    //methode permettant de retourner la liste des entrées
    public List<Mouvement> getListSortie() {

        List<Mouvement> listMouvement = new LinkedList<>();

        ps = createStatement(listSortieStatement);

        try {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                listMouvement.add(new Mouvement(rs.getLong("id"), rs.getString("nature"), rs.getDouble("quantite"), rs.getString("typMouvement"), rs.getDate("date").toLocalDate(), rs.getString("etatMouvement")));

            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return listMouvement;
    }

}
