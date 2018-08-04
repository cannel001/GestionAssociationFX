package com.objis.gestassociation.service;

import com.objis.gestassociation.domaine.Evenement;

public interface IEvenementService extends IService<Evenement, Long>{
	
	public Double depensesParAnnee(String annee);
	public Double depensesParMois(int mois);
	public Double depensesTT();

}
