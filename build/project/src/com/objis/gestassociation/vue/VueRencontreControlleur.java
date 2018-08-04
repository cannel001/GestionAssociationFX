package com.objis.gestassociation.vue;

import com.objis.gestassociation.GestionAssociation;

import javafx.fxml.FXML;

public class VueRencontreControlleur {
	
	//les proprietes
	private GestionAssociation association;
	
	//methode permettant d'initialiser la vue
	@FXML
	public void initialize() {
		
	}
	
	//methode permettant de modifier la classe principale
	@FXML
	public void setGestionAssociation(GestionAssociation association) {
		this.association=association;
	}

}
