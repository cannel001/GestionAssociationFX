package com.objis.gestassociation.vue;

import com.objis.gestassociation.GestionAssociation;
import com.objis.gestassociation.service.impl.BureauService;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.StageStyle;

public class VueConnexionControlleur {
	
	private GestionAssociation gestionAssociation;
	private BureauService service=new BureauService();
	
	@FXML
	private Button btnConnexion;
	
	@FXML
	private Button btnChangerMotDePasse;
	
	@FXML
	private TextField txfUsername;
	@FXML
	private TextField txfPassword;
	
	@FXML
	private Label lbinfoConnection;
	
	@FXML
	public void initialize() {
		
		lbinfoConnection.setVisible(false);
		
	}
	
	//modifier le gestionassociation
	public void setGestionAssociation(GestionAssociation association) {
		
		this.gestionAssociation=association;
		association.getPrimaryStage().setWidth(773);
		association.getPrimaryStage().setHeight(500);
		
	}
	
	@FXML
	private void clickerSurBtnConnecter() {
		
		if(!(txfUsername.getText().equals(""))) {
			
			if(!(txfPassword.getText().equals(""))) {
				
				if(service.mmbParLoginPassword(txfUsername.getText(), txfPassword.getText()) != null) {
					
					gestionAssociation.getInfoMembConnecte().add(service.mmbParLoginPassword(txfUsername.getText(), txfPassword.getText()));
					gestionAssociation.changerFenetre("../vue/VueMenuPrincipal.fxml");
					gestionAssociation.chargerVueSlide();
					gestionAssociation.chargerVueCentre("../vue/VueAccueil.fxml");
					gestionAssociation.getPrimaryStage().setResizable(true);
					gestionAssociation.getPrimaryStage().setMaximized(true);
					
				}else {
					
					lbinfoConnection.setText("Login ou mot de passe incorrect");
					
				}
				
			}else {
				
				lbinfoConnection.setText("Veuillez entrer le mot de passe");
				
			}
			
		}else {
			
			lbinfoConnection.setText("Veuillez entrer l'username");
			
		}
		
		lbinfoConnection.setVisible(true);
		
	}
	
	@FXML
	private void clickerSurBtnChangerPass() {
		
		if(service.mmbParLoginPassword(txfUsername.getText(), txfPassword.getText()) != null) {
			
			gestionAssociation.setIdAdherent(service.mmbParLoginPassword(txfUsername.getText(), txfPassword.getText()).getId());
			
			gestionAssociation.afficherVueChangerMdp();
					
		}else {
			
			lbinfoConnection.setText("Login ou mot de passe incorrect");
			
			lbinfoConnection.setVisible(true);
			
		}
		
	}

}
