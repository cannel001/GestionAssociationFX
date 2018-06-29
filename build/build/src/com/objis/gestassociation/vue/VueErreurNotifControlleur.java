package com.objis.gestassociation.vue;

import com.objis.gestassociation.GestionAssociation;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class VueErreurNotifControlleur {
	
	//les proprietes
	private GestionAssociation association;
	
	@FXML
	private Label lbInfo=new Label();
	
	//methode permettant d'initialiser la vue
	@FXML
	public void initialize() {
		
	}
	
	//methode permettant  de retourner le label
	public Label getLabelInfo() {
		
		return lbInfo;
		
	}
	
	//Methode permettant de modifier la classe
	public void setGestion(GestionAssociation association) {
		
		this.association=association;
		lbInfo.setText(association.getMessage());
		
	}
	

}
