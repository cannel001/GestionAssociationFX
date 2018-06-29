package com.objis.gestassociation.vue;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.objis.gestassociation.GestionAssociation;
import com.objis.gestassociation.domaine.Adherent;
import com.objis.gestassociation.domaine.Rencontre;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.IntegerStringConverter;

public class VueRencontreControlleur implements Initializable {
	
	//les proprietes
	private GestionAssociation association;
	
	@FXML
	private Label lbInfoRencontre;
	
	@FXML
	private JFXTextField txfDescription;
	@FXML
	private JFXTextField txfLieu;
	@FXML
	private JFXTextField txfNbPartcipants;
	@FXML
	private JFXComboBox<String> cbxPresentPresident;
	@FXML
	private JFXTextField txfOrdJr;
	@FXML
	private JFXDatePicker dateRencontre;
	@FXML
	private JFXTextArea txfDecisionPrise;
	
	@FXML
	private JFXComboBox<Adherent> cbxMbBureau;
	@FXML
	private JFXComboBox<Adherent> cbxAdherent;
	
	@FXML
	private TableView<Adherent> tableMbBureau;
	@FXML
	private TableColumn<Adherent,String> colMatriculeMbBureau;
	@FXML
	private TableColumn<Adherent,String> colNomMbbureau;
	@FXML
	private TableColumn<Adherent,String> colPrenomBureau;
	@FXML
	private Button btnAddMbBureau;
	@FXML
	private Button btnDelMbBureau;
	
	@FXML
	private TableView<Adherent> tableAdherent;
	@FXML
	private TableColumn<Adherent,String> colMatriculeAdherent;
	@FXML
	private TableColumn<Adherent,String> colNomAdherent;
	@FXML
	private TableColumn<Adherent,String> colPrenomAdherent;
	@FXML
	private Button btnAddAdherent;
	@FXML
	private Button btnDelAdherent;
	
	@FXML
	private TableView<Rencontre> tableRencontre;
	@FXML
	private TableColumn<Rencontre,String> colDescription;
	@FXML
	private TableColumn<Rencontre,String> colLieu;
	
	@FXML
	private Button btnAJouter;
	@FXML
	private Button btnModifier;
	@FXML
	private Button btnSupprimer;
	@FXML
	private Button btnReset;
	
	private Rencontre Rencontre;
	
	
	
	
	//methode permettant d'initialiser la vue
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//peuplement de la table rencontres
		colDescription.setCellValueFactory(e->e.getValue().descriptionProperty());
		colLieu.setCellValueFactory(e->e.getValue().lieuProperty());
		
		tableRencontre.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->chargerChampsSaisies(newValue));
		
		//peuplement de la table membres bureau
		colMatriculeMbBureau.setCellValueFactory(e->e.getValue().matriculeProperty());
		colNomMbbureau.setCellValueFactory(e->e.getValue().nomProperty());
		colPrenomBureau.setCellValueFactory(e->e.getValue().prenomProperty());
		
		//peuplement de la table adherent hors bureau
		colMatriculeAdherent.setCellValueFactory(e->e.getValue().matriculeProperty());
		colNomAdherent.setCellValueFactory(e->e.getValue().nomProperty());
		colPrenomAdherent.setCellValueFactory(e->e.getValue().prenomProperty());
		
		//initialisation des champs
		initChamps();
		
	}
	
	
	//methode permettant de faire un enregsitrement
	@FXML
	public void clickerSurBtnAjouterEnr() {
		
		Rencontre=new Rencontre(txfDescription.getText(), txfLieu.getText(), Integer.parseInt(txfNbPartcipants.getText()), cbxPresentPresident.getSelectionModel().getSelectedItem(), txfOrdJr.getText(), txfDecisionPrise.getText(), dateRencontre.getValue(),null, null);
		
		association.getListRencontre().add(Rencontre);
		
		initChamps();
		
	}
	
	//methode permettant de faire une modification
	@FXML
	public void clickerSurBtnModifierEnreg() {
		
	}
	
	//methode permettant de faire une suppression
	@FXML
	public void clickerSurBtnSupprimerEnreg() {
		
	}
	
	//methode permettant de faire un reset
	@FXML
	public void clickerSurBtnReset() {
		
		initChamps();
		
		if (association.getListMbBureauPresentRencontre()!=null) association.getListMbBureauPresentRencontre().clear();
		
		if(association.getListAdherentPresentRencontre()!=null) association.getListAdherentPresentRencontre().clear();
		
	}
	
	//methode permettant d'initialiser les champs de saisies
	public void initChamps() {
		
		txfNbPartcipants.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
		
		txfDescription.setText(""); 
		txfLieu.setText("");
		txfNbPartcipants.setText("");
		cbxPresentPresident.getSelectionModel().clearSelection();
		txfOrdJr.setText("");
		txfDecisionPrise.setText("");
		dateRencontre.setValue(null);
		
		tableRencontre.getSelectionModel().clearSelection();
		
		lbInfoRencontre.setText(""+tableRencontre.getItems().size());
		
		cbxAdherent.getSelectionModel().clearSelection();
		
		cbxMbBureau.getSelectionModel().clearSelection();
		
		//changement d'etats des btns
		btnAJouter.setDisable(false);
		btnModifier.setDisable(true);
		btnSupprimer.setDisable(true);
		btnReset.setDisable(true);
		
	}
	
	//methode permettant de charger les champs de saisies
	public void chargerChampsSaisies(Rencontre rencontre) {
		
		txfDescription.setText(rencontre.getDescription());
		txfLieu.setText(rencontre.getLieu());
		txfNbPartcipants.setText(""+rencontre.getNbParticipants());
		cbxPresentPresident.getSelectionModel().select(rencontre.getPresencePresi());
		txfOrdJr.setText(rencontre.getOrdreJr());
		txfDecisionPrise.setText(rencontre.getDecisionPrise());
		dateRencontre.setValue(rencontre.getDateRencontre());
		
		//changement d'etats des btn
		btnAJouter.setDisable(true);
		btnModifier.setDisable(false);
		btnSupprimer.setDisable(false);
		btnReset.setDisable(false);
		
	}
	
	//methode permettant d'ajouter un membre du bueau à la liste
	@FXML
	public void addMbBureau() {
		
		association.getListMbBureauPresentRencontre().add(cbxMbBureau.getSelectionModel().getSelectedItem());
		
	}
	
	//Methode permettant de supprimer un membre du bureau de la liste
	@FXML
	public void deleteMbBureau() {
		
		association.getListMbBureauPresentRencontre().remove(tableMbBureau.getSelectionModel().getFocusedIndex());
		
	}
	
	//methode permettant d'ajouter un adherent à la liste
	@FXML
	public void addAdherent() {
		
		association.getListAdherentPresentRencontre().add(cbxAdherent.getSelectionModel().getSelectedItem());
		
	}
	
	//methode permettant de supprimer un adherent à la liste
	@FXML
	public void suppAdherent() {
		
		association.getListAdherentPresentRencontre().remove(tableAdherent.getSelectionModel().getSelectedIndex());
		
	}
	
	//methode permettant de modifier la classe principale
	public void setGestionAssociation(GestionAssociation association) {
		this.association=association;
		
		cbxAdherent.setItems(association.getlisteAdherentHorsBureau());
		cbxMbBureau.setItems(association.getListADherentDansBureau());
		cbxPresentPresident.setItems(association.getListePresencePresident());
		
		this.tableRencontre.setItems(association.getListRencontre());
		this.tableMbBureau.setItems(association.getListMbBureauPresentRencontre());
		this.tableAdherent.setItems(association.getListAdherentPresentRencontre());
	}

	

}
