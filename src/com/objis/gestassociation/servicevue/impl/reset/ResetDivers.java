package com.objis.gestassociation.servicevue.impl.reset;

import com.objis.gestassociation.GestionAssociation;
import com.objis.gestassociation.domaine.Divers;
import com.objis.gestassociation.service.impl.AssociationService;
import com.objis.gestassociation.service.impl.DiversService;
import com.objis.gestassociation.servicevue.IResetVue;
import com.objis.gestassociation.servicevue.impl.DiversServiceVue;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ResetDivers extends DiversServiceVue implements IResetVue {

	public ResetDivers(GestionAssociation association, DiversService diversService,
			AssociationService associationService, DatePicker date, TextField txfLieu, TextField txfMotif,
			TextArea txfCorpsFormulaire, ComboBox<String> cbxTypFormulaire, Label lbTt, Button btnAjouter,
			Button btnModifier, Button btnSupprimer, Button btnReset, TableView<Divers> tableDivers, Divers divers,
			Alert alert) {
		super(association, diversService, associationService, date, txfLieu, txfMotif, txfCorpsFormulaire, cbxTypFormulaire,
				lbTt, btnAjouter, btnModifier, btnSupprimer, btnReset, tableDivers, divers, alert);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void reset() {
		
		date.setValue(null);
		txfLieu.setText("");
		txfCorpsFormulaire.setText("");
		txfMotif.setText("");
		cbxTypFormulaire.getSelectionModel().select("");
		
		btnAjouter.setDisable(false);
		btnModifier.setDisable(true);
		btnReset.setDisable(true);
		btnSupprimer.setDisable(true);
		
		lbTt.setText(""+diversService.nbEnregistrement());
		
		tableDivers.getSelectionModel().clearSelection();
		
	}

}
