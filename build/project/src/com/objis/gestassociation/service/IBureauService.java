package com.objis.gestassociation.service;

import com.objis.gestassociation.domaine.Bureau;

/**
 * Interface Bureau Service
 * @author Seka Cannel Ulrich Evrard
 *
 */
public interface IBureauService extends IService<Bureau, Long> {
	
	public int nbMembr();
	public Bureau mmbParLoginPassword(String login,String password);
	public Bureau readOneMembreSupprimer(Long id);
	public Boolean verifExistancLogin(String login);
	public Bureau readOneByLogin(String login);
	

}
