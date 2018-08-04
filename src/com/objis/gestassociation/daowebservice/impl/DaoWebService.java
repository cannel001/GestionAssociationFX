package com.objis.gestassociation.daowebservice.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.objis.gestassociation.daowebservice.IDaoWebService;

/**
 * Classe Abstraite generique DaoWebService
 *
 * @author Seka Cannel Ulrich Evrard
 *
 * @param <T>
 * @param <PK>
 */
public abstract class DaoWebService<T, PK> implements IDaoWebService<T, PK> {

    //les proprietes
    protected PreparedStatement ps;
    private Connection connex;

    /**
     * contructeur par defaut
     */
    public DaoWebService() {
        connex = Singleton.getInstence();
    }

    /**
     * mehode de la requete precompil�
     *
     * @param req
     * @return
     */
    public PreparedStatement createStatement(String req) {

        try {
            ps = connex.prepareStatement(req);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ps;

    }

}
