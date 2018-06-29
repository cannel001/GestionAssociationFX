package com.objis.gestassociation.vue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import com.objis.gestassociation.domaine.Adherent;
import com.objis.gestassociation.domaine.Cotisation;
import com.objis.gestassociation.domaine.CotisationAnnuelle;
import com.objis.gestassociation.service.impl.AdherentService;
import com.objis.gestassociation.service.impl.AssociationService;
import com.objis.gestassociation.service.impl.CaisseService;
import com.objis.gestassociation.service.impl.CotisationAnnuelleService;
import com.objis.gestassociation.service.impl.CotisationService;
import com.objis.gestassociation.service.impl.EvenementService;

public class Test {
	
	public static void main(String[] args) {
		
//	EvenementService evenementService=new EvenementService();
//	
//	System.out.println(evenementService.depensesTT());
//	System.out.println(evenementService.depensesParMois(5));
//	
//	
//	System.out.println(evenementService.depensesParAnnee("2018"));
//	
//	CotisationService cotisationService=new CotisationService();
//	
//	System.out.println("---------------------------------------");
//	System.out.println(cotisationService.cotisationPrAnnee("2018"));
//	System.out.println(cotisationService.cotisationPrMois(0));
//	
//	int mois=Calendar.getInstance().getTime().getMonth()+1;
//	System.out.println(mois);
//	
//	System.out.println("---------------------");
//	System.out.println("caisse");
//	CaisseService caisseService=new CaisseService();
//	System.out.println(caisseService.montantDisponible());
		
		AdherentService adherentService=new AdherentService();
		
		System.out.println(adherentService.readAllAdherentHorsBureau());
	
		
	}

}
