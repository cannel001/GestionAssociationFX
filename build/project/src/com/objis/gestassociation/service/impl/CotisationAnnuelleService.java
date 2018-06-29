package com.objis.gestassociation.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

import com.objis.gestassociation.domaine.Adherent;
import com.objis.gestassociation.domaine.Cotisation;
import com.objis.gestassociation.domaine.CotisationAnnuelle;
import com.objis.gestassociation.service.ICotisationAnnuelleService;

/**
 * Classe cotisation annuelle service
 * @author Seka Cannel ulrich evrard
 *
 */
public class CotisationAnnuelleService implements ICotisationAnnuelleService {
	
	//les proprietes
	LocalDate dateDebut;
	LocalDate dateFin;
	
	List<Cotisation> listCotisation=new LinkedList<>();
	
	CotisationService serviceCotisation;
	AdherentService serviceAdherent;
	
	List<Adherent> listAdherent=new LinkedList<>();
	List<CotisationAnnuelle> listeCotisationAnnuelle=new LinkedList<>();
	
	Float montJanvier = null;
	Float montFevrier = null;
	Float montMars = null;
	Float montAvril = null;
	Float montMai = null;
	Float montJuin = null;
	Float montjuillet = null;
	Float montAout = null;
	Float montSeptembre = null;
	Float montOctobre = null;
	Float montNovembre = null;
	Float montDecembre = null;
	Float montTotal = null;
	
	/**
	 * constructeur par defaut
	 */
	public CotisationAnnuelleService() {
		// TODO Auto-generated constructor stub
		serviceCotisation=new CotisationService();
		serviceAdherent=new AdherentService();
		
	}
	
	/**
	 * methode permettant de retourner la liste de toutes les cotisations par année redefinie
	 */
	@Override
	public List<CotisationAnnuelle> getAllListCotisation(String annee) {
		
		listAdherent.addAll(serviceAdherent.readAll());
			
		dateDebut=LocalDate.parse("01/01/"+annee, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		
		dateFin=LocalDate.parse("31/12/"+annee, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		
		listCotisation.addAll(serviceCotisation.listCotisationAnnuelle(dateDebut, dateFin));
		
		for (Adherent adherent : listAdherent) {
			
			//initialisation des variables
			montJanvier =(float) 0;
			montFevrier = (float) 0;
			montMars = (float) 0;
			montAvril=(float) 0;
			montMai=(float) 0;
			montJuin=(float) 0;
			montjuillet=(float) 0;
			montAout=(float) 0;
			montSeptembre=(float) 0;
			montOctobre=(float) 0;
			montNovembre=(float) 0;
			montDecembre=(float) 0;
			montTotal=(float) 0;
			
			for (Cotisation cotisation : listCotisation) {	
				
				if(cotisation.getAdherent().equals(adherent.getId())) {
					
					if(cotisation.getDate().getMonth().getValue()==1) {
						montJanvier+=cotisation.getMontant();
					}
					
					if(cotisation.getDate().getMonth().getValue()==2) {
						montFevrier+=cotisation.getMontant();
					}
					
					if(cotisation.getDate().getMonth().getValue()==3) {
						montMars+=cotisation.getMontant();
					}
					
					if(cotisation.getDate().getMonth().getValue()==4) {
						montAvril+=cotisation.getMontant();
					}
					
					if(cotisation.getDate().getMonth().getValue()==5) {
						montMai+=cotisation.getMontant();
					}
					
					if(cotisation.getDate().getMonth().getValue()==6) {
						montJuin+=cotisation.getMontant();
					}
					
					if(cotisation.getDate().getMonth().getValue()==7) {
						montjuillet+=cotisation.getMontant();
					}
					
					if(cotisation.getDate().getMonth().getValue()==8) {
						montAout+=cotisation.getMontant();
					}
					
					if(cotisation.getDate().getMonth().getValue()==9) {
						montSeptembre+=cotisation.getMontant();
					}
					
					if(cotisation.getDate().getMonth().getValue()==10) {
						montOctobre+=cotisation.getMontant();
					}
					
					if(cotisation.getDate().getMonth().getValue()==11) {
						montNovembre+=cotisation.getMontant();
					}
					
					if(cotisation.getDate().getMonth().getValue()==12) {
						montDecembre+=cotisation.getMontant();
					}
					
				}
					
			}
			
			
			montTotal=montJanvier+montFevrier+montMars+montAvril+montMai+montJuin+montjuillet+montAout+montSeptembre+montOctobre+montNovembre+montDecembre;
			
			listeCotisationAnnuelle.add(new CotisationAnnuelle(montJanvier, montFevrier, montMars, montAvril, montMai, montJuin, montjuillet, montAout, montSeptembre, montOctobre, montNovembre, montDecembre, montTotal, adherent));
		
		}

		return listeCotisationAnnuelle;
	}


	/**
	 * methode permettant de retourner la liste de toutes les cotisations par Id et année redefinie
	 */
	@Override
	public CotisationAnnuelle getListCotisationParId(Long id, int annee) {
		
		Adherent adherent=serviceAdherent.readOne(id);
		
		CotisationAnnuelle cotisationAnnuelle;
		
		montJanvier = null;
		montFevrier = null;
		montMars = null;
		montAvril = null;
		montMai = null;
		montJuin = null;
		montjuillet = null;
		montAout = null;
		montSeptembre = null;
		montOctobre = null;
		montNovembre = null;
		montDecembre = null;
		montTotal = null;
			
		dateDebut=LocalDate.parse("01/01/"+annee, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		
		dateFin=LocalDate.parse("31/12/"+annee, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		
		listCotisation=serviceCotisation.listCotisationAnnuelleParId(id, dateDebut, dateFin);
			
		for (Cotisation cotisation : listCotisation) {
			
			if (cotisation.getAdherent().equals(adherent.getId())) {
				
				if(cotisation.getDate().getMonth().getValue()==1) {
					montJanvier+=cotisation.getMontant();
				}
				
				if(cotisation.getDate().getMonth().getValue()==2) {
					montFevrier+=cotisation.getMontant();
				}
				
				if(cotisation.getDate().getMonth().getValue()==3) {
					montMars+=cotisation.getMontant();
				}
				
				if(cotisation.getDate().getMonth().getValue()==4) {
					montAvril+=cotisation.getMontant();
				}
				
				if(cotisation.getDate().getMonth().getValue()==5) {
					montMai+=cotisation.getMontant();
				}
				
				if(cotisation.getDate().getMonth().getValue()==6) {
					montJuin+=cotisation.getMontant();
				}
				
				if(cotisation.getDate().getMonth().getValue()==7) {
					montjuillet+=cotisation.getMontant();
				}
				
				if(cotisation.getDate().getMonth().getValue()==8) {
					montAout+=cotisation.getMontant();
				}
				
				if(cotisation.getDate().getMonth().getValue()==9) {
					montSeptembre+=cotisation.getMontant();
				}
				
				if(cotisation.getDate().getMonth().getValue()==10) {
					montOctobre+=cotisation.getMontant();
				}
				
				if(cotisation.getDate().getMonth().getValue()==11) {
					montNovembre+=cotisation.getMontant();
				}
				
				if(cotisation.getDate().getMonth().getValue()==12) {
					montDecembre+=cotisation.getMontant();
				}
				
			}
				
		}
		
		montTotal=montJanvier+montFevrier+montMars+montAvril+montMai+montJuin+montjuillet+montAout+montSeptembre+montOctobre+montNovembre+montDecembre;
		
		cotisationAnnuelle=new CotisationAnnuelle(montJanvier, montFevrier, montMars, montAvril, montMai, montJuin, montjuillet, montAout, montSeptembre, montOctobre, montNovembre, montDecembre, montTotal, adherent);
		
		return cotisationAnnuelle;
	}
	
	/**
	 * methode permettant de retourner le total des cotisations de chaque mois par année redefinie
	 */
	@Override
	public List<Float> getTotalCotisationAnnuelleParAnnee(String annee){
		
		List<Float> maList=new LinkedList<>();
		
		montJanvier = null;
		montFevrier = null;
		montMars = null;
		montAvril = null;
		montMai = null;
		montJuin = null;
		montjuillet = null;
		montAout = null;
		montSeptembre = null;
		montOctobre = null;
		montNovembre = null;
		montDecembre = null;
		
		listeCotisationAnnuelle.addAll(this.getAllListCotisation(annee));
		
		for (CotisationAnnuelle cotisationAnnuelle : listeCotisationAnnuelle) {
			
			montJanvier+=cotisationAnnuelle.getMontJanvier();
			montFevrier+=cotisationAnnuelle.getMontFevrier();
			montMars+=cotisationAnnuelle.getMontMars();
			montAvril+=cotisationAnnuelle.getMontAvril();
			montMai+=cotisationAnnuelle.getMontMai();
			montJuin+=cotisationAnnuelle.getMontJuin();
			montjuillet+=cotisationAnnuelle.getMontjuillet();
			montAout+=cotisationAnnuelle.getMontAout();
			montSeptembre+=cotisationAnnuelle.getMontSeptembre();
			montOctobre+=cotisationAnnuelle.getMontOctobre();
			montNovembre+=cotisationAnnuelle.getMontNovembre();
			montDecembre+=cotisationAnnuelle.getMontDecembre();
			
		}
		
		maList.add(montJanvier);
		maList.add(montFevrier);
		maList.add(montMars);
		maList.add(montAvril);
		maList.add(montMai);
		maList.add(montJuin);
		maList.add(montjuillet);
		maList.add(montAout);
		maList.add(montSeptembre);
		maList.add(montOctobre);
		maList.add(montNovembre);
		maList.add(montDecembre);
			
		return maList;
		
	}
	
}
