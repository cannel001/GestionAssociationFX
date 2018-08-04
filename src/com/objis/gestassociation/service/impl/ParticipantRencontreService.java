package com.objis.gestassociation.service.impl;

import java.util.List;

import com.objis.gestassociation.dao.impl.ParticipantRencontreDao;
import com.objis.gestassociation.domaine.ParticipantsRencontre;
import com.objis.gestassociation.service.IParticipantRencontreService;

public class ParticipantRencontreService implements IParticipantRencontreService {

    //les proprietes
    private ParticipantRencontreDao dao;

    //constructeur par defaut
    public ParticipantRencontreService() {
        dao = new ParticipantRencontreDao();
    }

    @Override
    public Boolean create(ParticipantsRencontre t) {

        if (t.getIdParticipant() != null) {
            return dao.create(t);
        }

        return null;
    }

    @Override
    public ParticipantsRencontre readOne(Long pk) {

        if (pk > 0) {
            return dao.readOne(pk);
        }

        return null;
    }

    @Override
    public Boolean update(ParticipantsRencontre t) {

        if (t.getIdParticipant() != null) {
            return dao.update(t);
        }

        return null;
    }

    @Override
    public Boolean delete(Long pk) {

        if (pk != null) {
            return dao.delete(pk);
        }

        return null;
    }

    @Override
    public List<ParticipantsRencontre> readAll() {
        // TODO Auto-generated method stub
        return dao.readAll();
    }

}
