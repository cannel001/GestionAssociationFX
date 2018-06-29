package com.objis.gestassociation.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

import com.objis.gestassociation.dao.impl.CotisationDao;
import com.objis.gestassociation.domaine.Cotisation;
import com.objis.gestassociation.service.ICotisationService;

/**
 * Classe Cotisation Service
 * @author Seka Cannel Ulrich Evrard
 *
 */
public class CotisationService implements ICotisationService {
	
	//les proprietes
	CotisationDao dao=new CotisationDao();
	
	/**
	 * Constructeur par defaut
	 */
	public CotisationService() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * methode permettant de faire un enregistrement redefinie
	 */
	@Override
	public Boolean create(Cotisation t) {
		
		if(!(t.getIdCotisation().equals(null)) && !(t.equals(null))) {
			
			return dao.create(t);
			
		}
		
		return null;
	}

	/**
	 * methode permettant de lire un enregistrement redefinie
	 */
	@Override
	public Cotisation readOne(Long pk) {

		if(!(pk.equals(null))) {
			
			return dao.readOne(pk);
			
		}
		
		return null;
	}

	/**
	 * methode permettant de modifier un enregistrement redefinie
	 */
	@Override
	public Boolean update(Cotisation t) {
		
		if (!(t.getIdCotisation().equals(null))) {
			return dao.update(t);
		}
		
		return null;
	}

	/**
	 * methode permettant de supprimer un enregistrement redefinie
	 */
	@Override
	public Boolean delete(Long pk) {
		
		if(!(pk.equals(null))) {
			return dao.delete(pk);
		}
		
		return null;
	}

	/**
	 * methode permettant de lire tous les enregistrements redefinie
	 */
	@Override
	public List<Cotisation> readAll() {
		
		return dao.readAll();
		
	}
	
	/**
	 * methode permettant de retourner la liste des cotisations annuelle par identifiant redefinie
	 */
	@Override
	public List<Cotisation> listCotisationAnnuelleParId(Long id,LocalDate dateDebut,LocalDate dateFin){
		return dao.listAnnuelleParId(id, dateDebut, dateFin);
	}
	
	/**
	 * methode permettant de retourner la liste des cotisations annuelle redefinie
	 */
	@Override
	public List<Cotisation> listCotisationAnnuelle(LocalDate dateDebut,LocalDate dateFin){
		
		List<Cotisation> Malist=dao.listAnnuelle(dateDebut, dateFin);
		
		return Malist;
	}
	
	/**
	 * methode permettant de retourner la somme des cotisations redefinie
	 */
	@Override
	public Float sommeCotisation() {
		
		return dao.sommeCotisation();
		
	}

	@Override
	public Double cotisationPrAnnee(String annee) {
		
		LocalDate dateDebut=LocalDate.parse("01/01/"+annee, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		LocalDate dateFin=LocalDate.parse("31/12/"+annee, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		
		return dao.cotisationParAnnee(dateDebut, dateFin);
	}

	@Override
	public Double cotisationPrMois(int mois) {
		
		Double ttCotisation=(double) 0;
		
		int anneeActuelle=Calendar.getInstance().getWeekYear();
		
		for (Cotisation cotisation : this.readAll()) {
			
			if((cotisation.getDate().getYear()==anneeActuelle) && (cotisation.getDate().getMonthValue()==mois)){
				ttCotisation+=cotisation.getMontant();
			}
			
		}
		
		return ttCotisation;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
