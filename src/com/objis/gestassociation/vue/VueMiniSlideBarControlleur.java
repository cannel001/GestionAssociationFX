package com.objis.gestassociation.vue;

import com.objis.gestassociation.GestionAssociation;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class VueMiniSlideBarControlleur {
	
	//les proprietes
	private GestionAssociation association;
	
	@FXML
	private ImageView imageView;
	
	//methode d'initialisation
	@FXML
	public void initialize() {
		
		
		
	}
	
	//methode permettant d'afficher la grande slide bar
	@FXML
	public void clickerSurImageView() {
		
		association.chargerVueSlide();
		
	}
	
	//methode permettant d'avoir acces en ecriture à la classe principale
	public void setGestion(GestionAssociation association) {
		
		this.association=association;
		
	}

}
