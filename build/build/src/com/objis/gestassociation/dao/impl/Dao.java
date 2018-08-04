package com.objis.gestassociation.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.objis.gestassociation.dao.IDao;

/**
 * Classe Abstraite generique Dao
 * @author Seka Cannel Ulrich Evrard
 *
 * @param <T>
 * @param <PK>
 */
public abstract class Dao<T,PK> implements IDao<T, PK> {
	
	//les proprietes
	protected PreparedStatement ps;
	private Connection connex;
	
	/**
	 * contructeur par defaut
	 */
	public Dao() {
		connex=Singleton.getInstence();
	}
	
	/**
	 * mehode de la requete precompilé
	 * @param req
	 * @return
	 */
	public PreparedStatement createStatement(String req) {
		
		try {
			ps=connex.prepareStatement(req);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ps;
		
	}

}
