package com.objis.gestassociation.servicevue.impl.modification;

import com.objis.gestassociation.GestionAssociation;
import com.objis.gestassociation.domaine.Evenement;
import com.objis.gestassociation.service.impl.AssociationService;
import com.objis.gestassociation.service.impl.EvenementService;
import com.objis.gestassociation.servicevue.IModificationVue;
import com.objis.gestassociation.servicevue.impl.EvenementServiceVue;

import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ModificationEvenement extends EvenementServiceVue implements IModificationVue {

	public ModificationEvenement(GestionAssociation association, AssociationService associationService,
			EvenementService service, Alert alert, TableView<Evenement> tableEvenement, DatePicker dateEvent,
			TextField txfLieu, TextArea txfObjet, TextArea txfAchat, TextField txfDepenstt, ComboBox<String> cbxSolde,
			Label lbMontEvent) {
		super(association, associationService, service, alert, tableEvenement, dateEvent, txfLieu, txfObjet, txfAchat,
				txfDepenstt, cbxSolde, lbMontEvent);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void modification() {
		
		Evenement evenement=new Evenement(tableEvenement.getSelectionModel().getSelectedItem().getId(), dateEvent.getValue(), txfLieu.getText(), txfObjet.getText(), txfAchat.getText(),Float.parseFloat(txfDepenstt.getText()),cbxSolde.getSelectionModel().getSelectedItem(),"ACTIF");
		
		if(service.update(evenement)) {
			
			association.getListEvenement().set(tableEvenement.getSelectionModel().getSelectedIndex(), evenement);
			
			tableEvenement.getSelectionModel().clearSelection();
			
			//ajouter le message d'alert
			association.afficherNotifModification();
			
		}
		
	}

}
