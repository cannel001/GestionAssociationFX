package com.objis.gestassociation.vue.contrainte;

import com.objis.gestassociation.GestionAssociation;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class ContrainteMouvement {
	
	private TextField txfNature;
	private TextField txfQte;
	private DatePicker date;
	private ComboBox<String> cbxTypMouvement;
	
	private GestionAssociation association;
	
	public ContrainteMouvement(TextField nature,TextField quantite,DatePicker date,ComboBox<String> cbxTypMouvement,GestionAssociation association) {
		
		this.txfNature=nature;
		this.txfQte=quantite;
		this.date=date;
		this.cbxTypMouvement=cbxTypMouvement;
		this.association=association;
		
	}
	
	//methode permettant de gerer les contraintes
	public Boolean contrainte() {
		
		if(date.getValue()!=null) {
					
			if(!(txfNature.getText().equals(""))) {
						
				if(cbxTypMouvement.getValue()!=null) {
							
					if(!(txfQte.getText().equals(""))) {
								
						return true;
								
					}else {
								
						association.setMessageErreur("Veuillez entrer une quantité svp");
						association.afficherAlerteErreur();
								
					}	
							
				}else {
							
					association.setMessageErreur("Veuillez selectionner le type mouvement svp");
					association.afficherAlerteErreur();
							
				}
						
			}else {
						
				association.setMessageErreur("Veuillez entrer la nature svp");
				association.afficherAlerteErreur();
						
						
			}
					
		}else {
			
			association.setMessageErreur("Veuillez entrer la date svp");
			association.afficherAlerteErreur();

		}
		
		return false;
		
	}

}
