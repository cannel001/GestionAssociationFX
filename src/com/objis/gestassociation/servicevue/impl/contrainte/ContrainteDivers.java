package com.objis.gestassociation.servicevue.impl.contrainte;

import com.objis.gestassociation.GestionAssociation;
import com.objis.gestassociation.domaine.Divers;
import com.objis.gestassociation.service.impl.AssociationService;
import com.objis.gestassociation.service.impl.DiversService;
import com.objis.gestassociation.servicevue.IContrainteVue;
import com.objis.gestassociation.servicevue.impl.DiversServiceVue;

import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ContrainteDivers extends DiversServiceVue implements IContrainteVue {

	public ContrainteDivers(GestionAssociation association, DiversService diversService,
			AssociationService associationService, DatePicker date, TextField txfLieu, TextField txfMotif,
			TextArea txfCorpsFormulaire, ComboBox<String> cbxTypFormulaire, Label lbTt, TableView<Divers> tableDivers,
			Divers divers) {
		super(association, diversService, associationService, date, txfLieu, txfMotif, txfCorpsFormulaire, cbxTypFormulaire,
				lbTt, tableDivers, divers);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Boolean contrainte() {
		
		if(!(date.getEditor().getText().equals(""))){
			
			if (!(txfLieu.getText().equals(""))) {
				
				if(cbxTypFormulaire.getSelectionModel().getSelectedIndex()>-1) {
					
					if(!(txfMotif.getText().equals(""))) {
						
						if(!(txfCorpsFormulaire.getText().equals(""))) {
							
							return true;
							
						}else {
							
							association.setMessageErreur("Veuillez remplir le coprs du formulaire avant de continuer ce traitement");
							association.afficherAlerteErreur();
							
						}
						
					}else {
						
						association.setMessageErreur("Veuillez entrer le motif avant de continuer ce traitement");
						association.afficherAlerteErreur();
						
					}
					
				}else {
					
					association.setMessageErreur("Veuillez selectionner le type de formulaire avant de continuer ce traitement");
					association.afficherAlerteErreur();
					
				}
				
			}else {
				
				association.setMessageErreur("Veuillez entrer le lieu avant de continuer ce traitement");
				association.afficherAlerteErreur();
				
			}
			
		}else {
			
			association.setMessageErreur("Veuillez entrer la date avant de continuer ce traitement");
			association.afficherAlerteErreur();
			
		}
		
		return false;
		
	}

}
