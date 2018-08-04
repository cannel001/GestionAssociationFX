package com.objis.gestassociation.service.impl;

import java.util.List;

import com.objis.gestassociation.dao.impl.DiversDao;
import com.objis.gestassociation.domaine.Divers;
import com.objis.gestassociation.service.IDiversService;

/**
 * Classe Divers Service
 *
 * @author Seka Cannel Ulrich Evrard
 *
 */
public class DiversService implements IDiversService {

    //les proprietes
    private DiversDao dao = new DiversDao();

    //constructeur par defaut
    public DiversService() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public Boolean create(Divers t) {

        if (!(t.getMotif().equals(""))) {
            return dao.create(t);
        }

        return null;
    }

    @Override
    public Divers readOne(Long pk) {

        if (!(pk.equals(null))) {
            return dao.readOne(pk);
        }
        return null;
    }

    @Override
    public Boolean update(Divers t) {

        if (!(t.getMotif().equals(null))) {
            return dao.update(t);
        }

        return null;
    }

    @Override
    public Boolean delete(Long pk) {

        if (!(pk.equals(null))) {
            return dao.delete(pk);
        }

        return null;
    }

    @Override
    public List<Divers> readAll() {

        return dao.readAll();
    }

    @Override
    public int nbEnregistrement() {
        // TODO Auto-generated method stub
        return dao.readAll().size();
    }

}
