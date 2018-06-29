package com.objis.gestassociation.vue;

import java.security.cert.PKIXRevocationChecker.Option;
import java.util.Optional;

import com.objis.gestassociation.GestionAssociation;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

/**
 * Controlleur de la vue menu principale
 * @author beta_
 *
 */

public class VueMenuPrincipalController {
	
	/**
	 * les proprietes
	 */
	private GestionAssociation association;
	private Alert alert;
	
	@FXML
	private Button btnDeconnexion;
	
	/**
	 * methode d'initialisation
	 */
	@FXML
	public void initialize() {
		
		
		
	}
	
	
	/**
	 * methode permettant de se deconnecter
	 */
	@FXML
	public void clikerSurBtnDeconnecter() {
		
		alert=new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Fermeture");
		alert.setContentText("Voulez-vous quitter cette sexion");
		Optional<ButtonType> option= alert.showAndWait();
		if(option.get().equals(ButtonType.OK)) {
			association.afficherFenetreConnexion();
			association.getStage().setWidth(773);
			association.getStage().setHeight(500);
		}else {
			alert=new Alert(AlertType.INFORMATION);
			alert.setTitle("Annulation");
			alert.setContentText("Fermeture annulée");
			alert.show();
		}
		
		
	}
	
	
	
	/**
	 * methode permettant d'avoir acces en ecriture à la classe principale
	 * @param association
	 */
	public void setGestion(GestionAssociation association) {
		
		this.association=association;
		
	}

}
