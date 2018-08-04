package com.objis.gestassociation.service;

import java.util.List;

import com.objis.gestassociation.domaine.CotisationAnnuelle;

/**
 * Interface Cotisation Annuelle Service
 * @author Seka Cannel ulrich Evrard
 *
 */
public interface ICotisationAnnuelleService {
	
	public CotisationAnnuelle getListCotisationParId(Long id,int annee);
	public List<CotisationAnnuelle> getAllListCotisation(String annee);
	public List<Float> getTotalCotisationAnnuelleParAnnee(String annee);

}
