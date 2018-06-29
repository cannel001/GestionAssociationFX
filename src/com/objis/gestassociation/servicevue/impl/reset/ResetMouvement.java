package com.objis.gestassociation.servicevue.impl.reset;

import com.objis.gestassociation.GestionAssociation;
import com.objis.gestassociation.domaine.Mouvement;
import com.objis.gestassociation.servicevue.IResetVue;
import com.objis.gestassociation.servicevue.impl.MouvementServiceVue;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ResetMouvement extends MouvementServiceVue implements IResetVue {

	public ResetMouvement(DatePicker date, TextField nature, TextField quantite, ComboBox<String> cbxMouvement,
			GestionAssociation association, TableView<Mouvement> tableMouvement, Button btnValider, Button btnModifier,
			Button btnSuprimer, Button btnReset, Label ttEntree, Label ttSortie) {
		super(date, nature, quantite, cbxMouvement, association, tableMouvement, btnValider, btnModifier, btnSuprimer, btnReset,
				ttEntree, ttSortie);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void reset() {
		
		nature.setText("");
		quantite.setText("");
		date.setValue(null);
		cbxMouvement.getSelectionModel().select(null);
		
		tableMouvement.getSelectionModel().clearSelection();
		
		btnValider.setDisable(false);
		btnModifier.setDisable(true);
		btnSupprimer.setDisable(true);
		btnReset.setDisable(true);
		
		ttEntree.setText(""+mouvementService.listEntree().size());
		ttSortie.setText(""+mouvementService.listSortie().size());
		
	}

}
