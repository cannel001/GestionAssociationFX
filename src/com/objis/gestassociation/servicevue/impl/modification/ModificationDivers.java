package com.objis.gestassociation.servicevue.impl.modification;

import com.objis.gestassociation.GestionAssociation;
import com.objis.gestassociation.domaine.Divers;
import com.objis.gestassociation.service.impl.AssociationService;
import com.objis.gestassociation.service.impl.DiversService;
import com.objis.gestassociation.servicevue.IModificationVue;
import com.objis.gestassociation.servicevue.impl.DiversServiceVue;

import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ModificationDivers extends DiversServiceVue implements IModificationVue {

	public ModificationDivers(GestionAssociation association, DiversService diversService,
			AssociationService associationService, DatePicker date, TextField txfLieu, TextField txfMotif,
			TextArea txfCorpsFormulaire, ComboBox<String> cbxTypFormulaire, Label lbTt, TableView<Divers> tableDivers,
			Divers divers) {
		super(association, diversService, associationService, date, txfLieu, txfMotif, txfCorpsFormulaire, cbxTypFormulaire,
				lbTt, tableDivers, divers);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void modification() {
		
		divers=new Divers(date.getValue(),txfLieu.getText(),cbxTypFormulaire.getSelectionModel().getSelectedItem(),txfMotif.getText(),txfCorpsFormulaire.getText(),tableDivers.getSelectionModel().getSelectedItem().getId(),"ACTIF");
		
		if(diversService.update(divers)) {
			
			association.getListDivers().set(tableDivers.getSelectionModel().getSelectedIndex(), divers);
			
			association.afficherNotifModification();
			
			tableDivers.getSelectionModel().clearSelection();
			
		}else {
			
			association.setMessageErreur("Erreur survenue pendant la modification");
			association.afficherAlerteErreur();
			
		}
		
		
	}

}
