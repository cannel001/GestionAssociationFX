package com.objis.gestassociation.vue;

import com.objis.gestassociation.GestionAssociation;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class VueSlideBarControlleur {
	
	//les proprietes
	private GestionAssociation association=new GestionAssociation();
	private String maFonction;
	private Alert alert;
	
	
	@FXML
	private ImageView btnReduire;

	
	//methode d'initialisation
	@FXML
	public void initialize() {
		
				
	}
	
	//methode permettant de charger la vue adherent
	@FXML
	public void clickerSurBtnAdherent() {
		
		association.chargerVueCentre("../vue/VueAdherent.fxml");
	}
	
	//methode permettant d'afficher la grande slide bar
	@FXML
	public void clickerSurBtnReduire() {
		association.afficherMiniSlideBar();
	}
	
	//methode permettant de charger la vue bureau
	@FXML
	public void clickerSurBureau() {
		
		association.chargerVueCentre("../vue/VueBureau.fxml");
	}
	
	//methode permettant de charger la vue cotisation
	@FXML
	public void clickerSurCotisation() {
		
		association.chargerVueCentre("../vue/VueCotisation.fxml");
		
	}
	
	//methode permettant de charger la vue cotisation annuelle
	@FXML
	public void clickerSurCotisationAnnuelle() {
		
		association.chargerVueCentre("../vue/VueCotisationAnnuelle.fxml");
		
	}
	
	//methode permettant de charger la vue vie sociale
	@FXML
	public void clickerSurVieSociale() {
		
		association.chargerVueCentre("../vue/VueVieSociale.fxml");
		
	}
	
	
	//methode permettant de charger la vue divers
	@FXML
	public void clickerSurDivers() {
		
		association.chargerVueCentre("../vue/VueDivers.fxml");
	}
	
	//methode permettant de charger la vue rencontre
	@FXML
	public void clickerSurRencontre() {
		
		association.chargerVueCentre("../vue/VueRencontre.fxml");

	}
	
	//methode permettant de charger la vue evenement
	@FXML
	public void clickerSurEvent() {
		
		association.chargerVueCentre("../vue/VueEvenement.fxml");
	
	}
	
	//methode permettant de chargr la vue commissaire cu compte
	@FXML
	public void clickerSurCommissaireCompte() {
		
		association.chargerVueCentre("../vue/VueComissaireCompte.fxml");
		
	}
	
	//methode permettant de charger la vue Entree/sortie
	@FXML
	public void clickerSurEntreeSortie() {
		
		association.chargerVueCentre("../vue/VueEntreeSortie.fxml");
		
	}
	
	
	//methode permettant de modifier la classe principale
	public void setGestionAssociation(GestionAssociation association) {
		this.association=association;

		maFonction=association.getInfoMembConnecte().get(0).getFonction();

	}

}
