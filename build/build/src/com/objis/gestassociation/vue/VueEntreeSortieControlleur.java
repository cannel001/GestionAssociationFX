package com.objis.gestassociation.vue;

import java.time.LocalDate;

import com.objis.gestassociation.GestionAssociation;
import com.objis.gestassociation.domaine.Mouvement;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class VueEntreeSortieControlleur {
	
	//les proprietes
	private GestionAssociation association;
	
	@FXML
	private DatePicker date;
	@FXML
	private TextField txfNature;
	@FXML
	private TextField txfQte;
	
	@FXML
	private Label lbTtEntree;
	@FXML
	private Label lbTtSortie;
	
	@FXML
	private TableView<Mouvement> tableMouvement;
	
	@FXML
	private TableColumn<Mouvement,LocalDate> columNature;
	@FXML
	private TableColumn<Mouvement, String> columDate;
	@FXML
	private TableColumn<Mouvement, String> columQte;
	
	@FXML
	private Button btnValider;
	@FXML
	private Button btnModifier;
	@FXML
	private Button btnSupprimer;
	@FXML
	private Button btnReset;
	
	@FXML
	private ComboBox<String> cbxTypMouvement;
	
	
	//methode d'initialisation
	@FXML
	public void initialize() {
		
	}
	
	//methode permettant de charger les champs de saises
	public void chargerChampsSortie(Mouvement mouvement) {
		
		txfNature.setText(mouvement.getNature());
		txfQte.setText(""+mouvement.getQuantite());
		date.setValue(mouvement.getDate());
		
	}
	
	//initialisation des champs
	public void initChamps() {
		
		txfNature.setText("");
		txfQte.setText("");
		date.setValue(null);
		
		btnValider.setDisable(false);
		btnModifier.setDisable(true);
		btnSupprimer.setDisable(true);
		btnReset.setDisable(true);
		
	}
	
	//methode permettant de faire un enregistrement
	@FXML
	public void clickerSurBtnEregistrement() {
		
		
		
	}
	
	//methode permettant de faire une modification
	@FXML
	public void clickerSurBtnModification() {
		
		
		
	}
	
	//methode permettant de faire une suppression
	@FXML
	public void clickerSurBtnSupprimer() {
		
		
		
	}
	
	//methode permettant de faire un reset
	@FXML
	public void clickerSurBtnReset() {
		
		
		
	}
	
	//methode permettant de modifier la classe gestion association
	public void setGestionAssociation(GestionAssociation gestionAssociation) {
		
		this.association=gestionAssociation;
		cbxTypMouvement.setItems(association.getListTypMouvement());
		
	}
	
	
	
}
