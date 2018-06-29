package com.objis.gestassociation.servicevue.impl.contrainte;

import com.objis.gestassociation.GestionAssociation;
import com.objis.gestassociation.domaine.Evenement;
import com.objis.gestassociation.service.impl.AssociationService;
import com.objis.gestassociation.service.impl.EvenementService;
import com.objis.gestassociation.servicevue.IContrainteVue;
import com.objis.gestassociation.servicevue.impl.EvenementServiceVue;

import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ContrainteEvenement extends EvenementServiceVue implements IContrainteVue {

	public ContrainteEvenement(GestionAssociation association, AssociationService associationService,
			EvenementService service, Alert alert, TableView<Evenement> tableEvenement, DatePicker dateEvent,
			TextField txfLieu, TextArea txfObjet, TextArea txfAchat, TextField txfDepenstt, ComboBox<String> cbxSolde,
			Label lbMontEvent) {
		super(association, associationService, service, alert, tableEvenement, dateEvent, txfLieu, txfObjet, txfAchat,
				txfDepenstt, cbxSolde, lbMontEvent);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Boolean contrainte() {
		// TODO Auto-generated method stub

		if(!(dateEvent.getEditor().getText().equals(""))) {
			
			if(!(txfLieu.getText().equals(""))) {
				
				if(!(txfObjet.getText().equals(""))) {
					
					if(!(txfAchat.getText().equals(""))) {
						
						if(!(txfDepenstt.getText().equals(""))) {
							
							if((cbxSolde.getSelectionModel().getSelectedIndex()>-1)) {
								
								return true;
								
							}else {
								
								association.setMessageErreur("Veuillez selectionner le type de solde avant de continuer le traitement svp");
								association.afficherAlerteErreur();
								
							}
							
						}else {
							
							association.setMessageErreur("Veuillez entrer les depenses totales avant de continuer le traitement svp");
							association.afficherAlerteErreur();
							
						}
						
					}else {
						
						association.setMessageErreur("Veuillez entrer les achats avant de continuer svp");
						association.afficherAlerteErreur();
						
					}
					
				}else {
					
					association.setMessageErreur("Veuillez entrer l'objet avant de continuer svp");
					association.afficherAlerteErreur();
					
				}
				
			}else {
				
				association.setMessageErreur("Veuillez entrer le lieu avant de continuer svp");
				association.afficherAlerteErreur();
				
			}
			
		}else {
			
			association.setMessageErreur("Veuillez selectionner la date avant de continuer svp");
			association.afficherAlerteErreur();
			
		}
		
		return false;
		
	}

}
