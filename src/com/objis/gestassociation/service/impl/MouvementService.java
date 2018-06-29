package com.objis.gestassociation.service.impl;

import java.util.List;

import com.objis.gestassociation.dao.impl.MouvementDao;
import com.objis.gestassociation.domaine.Mouvement;
import com.objis.gestassociation.service.IMouvementService;

public class MouvementService implements IMouvementService {
	
	//les proprietes
	private MouvementDao dao=new MouvementDao();
	
	public MouvementService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Boolean create(Mouvement t) {
		
		if(!(t.getId().equals(null))) {
			return dao.create(t);
		}
		
		return null;
	}

	@Override
	public Mouvement readOne(Long pk) {
		
		if(!(pk.equals(null))) {
			return dao.readOne(pk);
		}
		
		return null;
	}

	@Override
	public Boolean update(Mouvement t) {
		
		if(!(t.getId().equals(null))) {
			return dao.update(t);
		}
		
		return null;
	}

	@Override
	public Boolean delete(Long pk) {
		
		if(pk!=0) {
			return dao.delete(pk);
		}
		
		return null;
	}

	@Override
	public List<Mouvement> readAll() {
		// TODO Auto-generated method stub
		return dao.readAll();
	}
	
	//methode permettant de retourner la liste des entrees
	public List<Mouvement> listEntree(){
		return dao.getListEntree();
	}
	
	
	//methode permettant de retourner la liste des sorties
	public List<Mouvement> listSortie(){
		return dao.getListSortie();
	}

}
