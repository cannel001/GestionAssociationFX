package com.objis.gestassociation.vue.chargementchamps;

import com.objis.gestassociation.domaine.Mouvement;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class ChargementMouvement {
	
	private TextField txfNature;
	private TextField txfQte;
	private DatePicker date;
	private ComboBox<String> cbxTypMouvement;
	private Mouvement mouvement;
	
	private Button btnValider;
	private Button btnModifier;
	private Button btnSupprimer;
	private Button btnReset;
	
	
	public ChargementMouvement(TextField nature,TextField quantite,DatePicker date,ComboBox<String> cbxTypMouvement,
			Mouvement mouvement,Button btnValider,Button btnModifier,Button btnSupprimer,Button btnreset) {
		
		this.txfNature=nature;
		this.txfQte=quantite;
		this.date=date;
		this.cbxTypMouvement=cbxTypMouvement;
		this.mouvement=mouvement;
		this.btnValider=btnValider;
		this.btnModifier=btnModifier;
		this.btnSupprimer=btnSupprimer;
		this.btnReset=btnreset;
		
	}
	
	//methode permettant de charger mouvement
	public void chargement() {
		
		if(mouvement!=null) {
			
			txfNature.setText(mouvement.getNature());
			txfQte.setText(""+mouvement.getQuantite());
			date.setValue(mouvement.getDate()); 
			cbxTypMouvement.getSelectionModel().select(mouvement.getTypMouvement());
			
			btnValider.setDisable(true);
			btnModifier.setDisable(false);
			btnSupprimer.setDisable(false);
			btnReset.setDisable(false);
			
		}
		
	}

}
