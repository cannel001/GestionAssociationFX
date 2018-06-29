package com.objis.gestassociation.vue;

import com.objis.gestassociation.GestionAssociation;
import com.objis.gestassociation.domaine.Bureau;
import com.objis.gestassociation.service.impl.BureauService;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class VueChangerDePassControlleur {
	
	//les proprietes
	private GestionAssociation gestionAssociation;
	private BureauService bureauService=new BureauService();
	
	@FXML
	private TextField txfNewPass;
	@FXML
	private TextField txfRepetPass;
	
	@FXML
	private Button btnValider;
	@FXML
	private Button btnAnnuler;
	
	@FXML
	private Label lbInfo;
	
	
	//methode permettant d'initialiser la vue
	@FXML
	public void initialize() {
		
		initChamps();
		
	}
	
	//methode permettant de valider le nouveau de pass
	@FXML
	public void clickerSurBtnValider() {
		
		if(!(txfNewPass.getText().equals(""))) {
			
			if(!(txfRepetPass.getText().equals(""))) {
				
				if(txfNewPass.getText().equals(txfRepetPass.getText())) {
					
					Bureau bureau=bureauService.readOne(gestionAssociation.getIdAdherent());
					bureau.setPassword(txfNewPass.getText());
					
					if(bureauService.update(bureau)){
						
						gestionAssociation.afficherFenetreConnexion();
						
					}else {
						
						lbInfo.setText("Erreur survenue pendant la modification");
						
					}
					
				}else {
					lbInfo.setText("Veuillez entrer les valeurs identiques svp");
				}
				
			}else {
				lbInfo.setText("Veuillez repeter le mot de passe");
			}
			
		}else {
			lbInfo.setText("Veuillez entrer le nouveau mot de passe");
		}
		
		lbInfo.setVisible(true);
		
	}
	
	//methode permettant d'annuler le mot de passe
	@FXML
	public void clickerSurBtnAnnuler() {
		
		gestionAssociation.afficherFenetreConnexion();
		
	}
	
	//methode permettant d'initialiser les champs de saisies
	public void initChamps() {
		
		txfNewPass.setText("");
		txfRepetPass.setText("");
		
	}
	
	//methode permettant de modifier gestion association
	public void setGestion(GestionAssociation association) {
		
		this.gestionAssociation=association;
		
	}
	
	

}
