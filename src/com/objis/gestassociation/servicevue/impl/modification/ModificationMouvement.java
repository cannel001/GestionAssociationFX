package com.objis.gestassociation.servicevue.impl.modification;

import com.objis.gestassociation.GestionAssociation;
import com.objis.gestassociation.domaine.Mouvement;
import com.objis.gestassociation.service.impl.AssociationService;
import com.objis.gestassociation.service.impl.MouvementService;
import com.objis.gestassociation.servicevue.IModificationVue;
import com.objis.gestassociation.servicevue.impl.MouvementServiceVue;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ModificationMouvement extends MouvementServiceVue implements IModificationVue {

	public ModificationMouvement(DatePicker date, TextField nature, TextField quantite, ComboBox<String> cbxMouvement,
			GestionAssociation association, TableView<Mouvement> tableMouvement, Button btnValider, Button btnModifier,
			Button btnSuprimer, Button btnReset, Label ttEntree, Label ttSortie) {
		super(date, nature, quantite, cbxMouvement, association, tableMouvement, btnValider, btnModifier, btnSuprimer, btnReset,
				ttEntree, ttSortie);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void modification() {
		
		mouvement=new Mouvement((long) tableMouvement.getSelectionModel().getSelectedItem().getId(), nature.getText(), Double.parseDouble(quantite.getText()), cbxMouvement.getValue(), date.getValue(), "ACTIF");
		
		if(mouvementService.update(mouvement)) {
			
			association.afficherNotifModification();
			association.getListMouvement().set(tableMouvement.getSelectionModel().getSelectedIndex(), mouvement);
			
		}else {
			
			association.setMessageErreur("Erreur survenue pendant la modification");
			association.afficherAlerteErreur();
			
		}
		
	}

}
