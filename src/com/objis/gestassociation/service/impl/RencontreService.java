package com.objis.gestassociation.service.impl;

import java.util.List;

import com.objis.gestassociation.dao.impl.RencontreDao;
import com.objis.gestassociation.domaine.Rencontre;
import com.objis.gestassociation.service.IRencontreService;

public class RencontreService implements IRencontreService {

    //ls proprietes
    private RencontreDao dao;

    //constructeur par defaut
    public RencontreService() {

        dao = new RencontreDao();

    }

    @Override
    public Boolean create(Rencontre t) {

        if (t.getIdRencontre() != null) {
            return dao.create(t);
        }

        return false;
    }

    @Override
    public Rencontre readOne(Long pk) {

        if (pk > 0) {
            return dao.readOne(pk);
        }

        return null;
    }

    @Override
    public Boolean update(Rencontre t) {

        if (t.getIdRencontre() != null) {
            return dao.update(t);
        }

        return false;
    }

    @Override
    public Boolean delete(Long pk) {

        if (pk > 0) {
            return dao.delete(pk);
        }

        return false;
    }

    @Override
    public List<Rencontre> readAll() {
        // TODO Auto-generated method stub
        return dao.readAll();
    }

}
