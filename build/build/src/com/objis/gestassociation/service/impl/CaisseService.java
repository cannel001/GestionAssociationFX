package com.objis.gestassociation.service.impl;

import com.objis.gestassociation.service.ICaisseService;

public class CaisseService implements ICaisseService {
	
	private CotisationService cotisationService;
	private EvenementService evenementService;
	
	//contructeur par defaut
	public CaisseService() {
		cotisationService=new CotisationService();
		evenementService=new EvenementService();
	}
	

	@Override
	public Double montantDisponible() {
		// TODO Auto-generated method stub
		Double montan=cotisationService.sommeCotisation()-evenementService.depensesTT();
		
		return montan;
	}

}
