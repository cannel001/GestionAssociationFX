package com.objis.gestassociation.service.impl;

import java.util.List;
import com.objis.gestassociation.dao.impl.BureauDao;
import com.objis.gestassociation.domaine.Bureau;
import com.objis.gestassociation.service.IBureauService;


/**
 * 
 * @author Seka Cannel Ulrich Evrard
 *
 */
public class BureauService implements IBureauService{
	
	//les proprietes
	private BureauDao dao=new BureauDao();
	
	/**
	 * contructeur par defaut
	 */
	public BureauService() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * methode permettant de faire un enregistrement redefinie
	 */
	@Override
	public Boolean create(Bureau t) {
		
		if (!(t.getFonction().equals(null))) {
			return dao.create(t);
		}
		
		return null;
	}

	/**
	 * methode permettant de lire un enregistrement redefinie
	 */
	@Override
	public Bureau readOne(Long pk) {
		
		if (!(pk.equals(null))) {
			return dao.readOne(pk);
		}
		
		return null;
	}

	/**
	 * methode permettant de faire une modification redefinie
	 */
	@Override
	public Boolean update(Bureau t) {
		
		if (!(t.getFonction().equals(null))) {
			return dao.update(t);
		}
		
		return null;
	}

	/**
	 * methode permettant de faire suppression redefinie
	 */
	@Override
	public Boolean delete(Long pk) {
		
		if (!(pk.equals(null))) {
			return dao.delete(pk);
		}
		
		return null;
	}

	/**
	 * methode permettant de lire tous les enregistrements redefinie
	 */
	@Override
	public List<Bureau> readAll() {
		
		return dao.readAll();
		
	}
	
	/**
	 * methode permettant de retourner le nombre de membres redefinie
	 */
	@Override
	public int nbMembr() {
		return dao.readAll().size();
	}
	
	/**
	 * methode permettant de retourner le membre connecté redefinie
	 */
	@Override
	public Bureau mmbParLoginPassword(String login,String password) {
		return dao.mmbParLoginPassword(login, password);
	}
	
	/**
	 * methode permettant de retourner un membre supprimer redefinie
	 */
	@Override
	public Bureau readOneMembreSupprimer(Long id) {
		
		if(!(id.equals(null))) {
			
			return dao.readOneMembreSupprimer(id);
			
		}
		
		return null;
		
	}
	
	/**
	 * methode permettant de verifier l'existance d'un login dans la base de données redefinie
	 */
	@Override
	public Boolean verifExistancLogin(String login) {
		
		if(!(login.equals(""))) {
			
			return dao.verifExistancLogin(login);
			
		}
		
		return null;
		
	}
	
	/**
	 * methode permettant de retourner un membre du bureau par login redefinie
	 */
	@Override
	public Bureau readOneByLogin(String login) {
		return dao.readOneByLogin(login);
	}
	

}
