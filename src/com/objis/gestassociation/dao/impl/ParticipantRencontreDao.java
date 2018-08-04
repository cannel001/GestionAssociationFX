package com.objis.gestassociation.dao.impl;

import com.objis.gestassociation.dao.impl.Dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.objis.gestassociation.domaine.Adherent;
import com.objis.gestassociation.domaine.ParticipantsRencontre;
import com.objis.gestassociation.domaine.Rencontre;
import com.objis.gestassociation.service.impl.AdherentService;
import com.objis.gestassociation.service.impl.RencontreService;

public class ParticipantRencontreDao extends Dao<ParticipantsRencontre, Long> {

    //les proprietes
    private static String CREATE_QUERY = "insert into participantRencontre (adherent,idParticipant,rencontre,etat) values (?,?,?,?)";
    private static String READONE_QUERY = "select * from participantRencontre where idParticipant=? and etat='ACTIF'";
    private static String READALL_QUERY = "select * from participantRencontre where etat='ACTIF'";
    private static String UPDATE_QUERY = "update participantRencontre set adherent=?,rencontre=? where etat='ACTIF'";
    private static String DELETE_QUERY = "update participantRencontre set etat=?";
    private static String LASTELEMENT_QUERY = "select * from participantRencontre where etat='ACTIF' order by idParticipant desc limit 1";

    private static int executeQuery = -1;

    private AdherentService adherentService = new AdherentService();
    private RencontreService rencontreService = new RencontreService();

    private ParticipantsRencontre participantsRencontre = null;
    private Adherent adherent = null;
    private Rencontre rencontre = null;

    @Override
    public Boolean create(ParticipantsRencontre t) {

        try {

            ps = createStatement(CREATE_QUERY);

            ps.setLong(1, t.getAdherent().getId());
            ps.setLong(2, t.getIdParticipant());
            ps.setLong(3, t.getRencontre().getIdRencontre());
            ps.setString(4, t.getEtat());

            executeQuery = ps.executeUpdate();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return executeQuery > 0;
    }

    @Override
    public ParticipantsRencontre readOne(Long pk) {

        try {

            ps = createStatement(READONE_QUERY);

            ps.setLong(1, pk);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                //recuperation de l'adherent
                adherent = adherentService.readOne(rs.getLong("adherent"));
                //recuperation de la rencontre
                rencontre = rencontreService.readOne(rs.getLong("rencontre"));

                participantsRencontre = new ParticipantsRencontre(adherent, rencontre, rs.getLong("idParticipant"), rs.getString("etat"));

            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return participantsRencontre;
    }

    @Override
    public Boolean update(ParticipantsRencontre t) {

        try {

            ps = createStatement(UPDATE_QUERY);

            ps.setLong(1, t.getAdherent().getId());
            ps.setLong(2, t.getRencontre().getIdRencontre());

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
    public List<ParticipantsRencontre> readAll() {

        List<ParticipantsRencontre> maListe = new LinkedList<>();

        try {

            ps = createStatement(READALL_QUERY);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                //recuperation de l'adherent
                adherent = adherentService.readOne(rs.getLong("adherent"));
                //recuperation de la rencontre
                rencontre = rencontreService.readOne(rs.getLong("rencontre"));

                participantsRencontre = new ParticipantsRencontre(adherent, rencontre, rs.getLong("idParticipant"), rs.getString("etat"));

                maListe.add(participantsRencontre);

            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return maListe;
    }

    //methode permettant de retourner le dernier enregistrement
    public ParticipantsRencontre lastElement() {

        try {

            ps = createStatement(LASTELEMENT_QUERY);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                //recuperation de l'adherent
                adherent = adherentService.readOne(rs.getLong("adherent"));
                //recuperation de la rencontre
                rencontre = rencontreService.readOne(rs.getLong("rencontre"));

                participantsRencontre = new ParticipantsRencontre(adherent, rencontre, rs.getLong("idParticipant"), rs.getString("etat"));

            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return participantsRencontre;

    }

}
