package com.objis.gestassociation.servicevue.impl.reset;

import com.objis.gestassociation.GestionAssociation;
import com.objis.gestassociation.domaine.Evenement;
import com.objis.gestassociation.service.impl.AssociationService;
import com.objis.gestassociation.service.impl.EvenementService;
import com.objis.gestassociation.servicevue.IResetVue;
import com.objis.gestassociation.servicevue.impl.EvenementServiceVue;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ResetEvenement extends EvenementServiceVue implements IResetVue{

	public ResetEvenement(GestionAssociation association, AssociationService associationService,
			EvenementService service, Alert alert, TableView<Evenement> tableEvenement, DatePicker dateEvent,
			TextField txfLieu, TextArea txfObjet, TextArea txfAchat, TextField txfDepenstt, Button btnAjouter,
			Button btnModifier, Button btnSupprimer, Button btnReset, ComboBox<String> cbxSolde, Label lbMontEvent) {
		super(association, associationService, service, alert, tableEvenement, dateEvent, txfLieu, txfObjet, txfAchat,
				txfDepenstt, btnAjouter, btnModifier, btnSupprimer, btnReset, cbxSolde, lbMontEvent);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void reset() {
		
		btnAjouter.setDisable(false);
		btnModifier.setDisable(true);
		btnReset.setDisable(true);
		btnSupprimer.setDisable(true);
		
		dateEvent.setValue(null);
		txfLieu.setText("");
		txfAchat.setText("");
		txfDepenstt.setText("");
		txfObjet.setText("");
		
		lbMontEvent.setText(""+service.nbEnregist());
		
		cbxSolde.getSelectionModel().clearSelection();
		
		tableEvenement.getSelectionModel().clearSelection();
		
	}

}
