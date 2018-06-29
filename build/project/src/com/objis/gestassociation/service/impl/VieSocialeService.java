package com.objis.gestassociation.service.impl;

import java.util.List;

import com.objis.gestassociation.dao.impl.VieSocialeDao;
import com.objis.gestassociation.domaine.VieSociale;
import com.objis.gestassociation.service.IVieSocialeService;

public class VieSocialeService implements IVieSocialeService {
	
	private VieSocialeDao dao=new VieSocialeDao();
	
	//constructeur
	public VieSocialeService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Boolean create(VieSociale t) {
		// TODO Auto-generated method stub
		if(!(t.getIdAdherent().equals(null))) {
			
			return dao.create(t);
			
		}
		return null;
	}

	@Override
	public VieSociale readOne(Long pk) {
		// TODO Auto-generated method stub
		if (!(pk.equals(null))) {
			return dao.readOne(pk);
		}
		return null;
	}

	@Override
	public Boolean update(VieSociale t) {
		// TODO Auto-generated method stub
		if(!(t.getIdAdherent().equals(null))) {
			return dao.update(t);
		}
		return null;
	}

	@Override
	public Boolean delete(Long pk) {
		// TODO Auto-generated method stub
		if(!(pk.equals(null))) {
			return dao.delete(pk);
		}
		
		return null;
	}

	@Override
	public List<VieSociale> readAll() {
		// TODO Auto-generated method stub
		return dao.readAll();
	}
	
	//methode permettant de retourner le dernier enregistrement
	public VieSociale lastElement() {
		return dao.lastElement();
	}
	
	//methode permettant de retourner le nombre d'enregistrmeents
	public int getNbEnregistrement() {
		return dao.readAll().size();
	}

}
