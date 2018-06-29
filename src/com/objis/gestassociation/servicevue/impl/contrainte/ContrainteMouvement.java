package com.objis.gestassociation.servicevue.impl.contrainte;

import com.objis.gestassociation.GestionAssociation;
import com.objis.gestassociation.domaine.Mouvement;
import com.objis.gestassociation.servicevue.IContrainteVue;
import com.objis.gestassociation.servicevue.impl.MouvementServiceVue;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ContrainteMouvement extends MouvementServiceVue implements IContrainteVue {

	public ContrainteMouvement(DatePicker date, TextField nature, TextField quantite, ComboBox<String> cbxMouvement,
			GestionAssociation association, TableView<Mouvement> tableMouvement, Button btnValider, Button btnModifier,
			Button btnSuprimer, Button btnReset, Label ttEntree, Label ttSortie) {
		super(date, nature, quantite, cbxMouvement, association, tableMouvement, btnValider, btnModifier, btnSuprimer, btnReset,
				ttEntree, ttSortie);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Boolean contrainte() {
		
		if(date.getValue()!=null) {
			
			if(!(nature.getText().equals(""))) {
						
				if(cbxMouvement.getValue()!=null) {
							
					if(!(quantite.getText().equals(""))) {
								
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
