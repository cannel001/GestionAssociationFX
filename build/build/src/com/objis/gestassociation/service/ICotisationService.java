package com.objis.gestassociation.service;

import java.time.LocalDate;
import java.util.List;

import com.objis.gestassociation.domaine.Cotisation;

/**
 * Interface Cotisation Service
 * @author Seka Cannel Ulrich Evrard
 *
 */
public interface ICotisationService extends IService<Cotisation, Long> {
	
	public List<Cotisation> listCotisationAnnuelleParId(Long id,LocalDate dateDebut,LocalDate dateFin);
	public List<Cotisation> listCotisationAnnuelle(LocalDate dateDebut,LocalDate dateFin);
	public Float sommeCotisation();
	public Double cotisationPrAnnee(String annee);
	public Double cotisationPrMois(int mois);

}
