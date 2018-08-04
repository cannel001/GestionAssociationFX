package com.objis.gestassociation.vue;

import java.time.LocalDate;
import java.util.Optional;

import com.objis.gestassociation.GestionAssociation;
import com.objis.gestassociation.domaine.Evenement;
import com.objis.gestassociation.service.impl.AssociationService;
import com.objis.gestassociation.service.impl.EvenementService;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.DoubleStringConverter;

public class VueEvenementControlleur {
	
	//les proprietes
	private GestionAssociation association;
	private AssociationService associationService=new AssociationService();
	private EvenementService service=new EvenementService();
	private Alert alert;
	
	@FXML
	private TableView<Evenement> tableEvenement;
	
	@FXML
	private TableColumn<Evenement, LocalDate> columDate;
	@FXML
	private TableColumn<Evenement, String> columObjet;
	
	@FXML
	private DatePicker dateEvent;
	
	@FXML
	private TextField txfLieu;
	@FXML
	private TextArea txfObjet;
	@FXML
	private TextArea txfAchat;
	@FXML
	private TextField txfDepenstt;
	
	@FXML
	private Button btnAjouter;
	@FXML
	private Button btnModifier;
	@FXML
	private Button btnSupprimer;
	@FXML
	private Button btnReset;
	
	@FXML
	private ComboBox<String> cbxSolde;
	
	@FXML
	private Label lbMontEvent;
	
	//methode permettant d'initialiser la vue
	@FXML
	public void initialize() {
		
		initChamps();
		
		txfDepenstt.setTextFormatter(new TextFormatter<>(new DoubleStringConverter()));
		
		columDate.setCellValueFactory(e->e.getValue().dateProperty());
		columObjet.setCellValueFactory(e->e.getValue().objetProperty());
		
		tableEvenement.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->chargerChamps(newValue));
		
	}
	
	//methode permettant de charger les champs de saises
	public void chargerChamps(Evenement evenement) {
		
		if (evenement!=null) {
			
			dateEvent.setValue(evenement.getDate());
			txfLieu.setText(evenement.getLieu());
			txfAchat.setText(evenement.getAchat());
			txfDepenstt.setText(""+evenement.getDepenseTt());
			txfObjet.setText(evenement.getObjet());
			
			for (String solde : association.getListTypSolde()) {
				
				if (tableEvenement.getSelectionModel().getSelectedItem().getSolde().equals(solde)){
					
					cbxSolde.getSelectionModel().select(solde);
					
				}
				
			}
			
			btnAjouter.setDisable(true);
			btnModifier.setDisable(false);
			btnReset.setDisable(false);
			btnSupprimer.setDisable(false);
			
		}	
		
	}
	
	//methode permettant d'initialiser les champs de saises
	public void initChamps() {
		
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
		
	}
	
	//methode permettant de gerer les contrainte
	public Boolean gestContrainte() {
		
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
	
	//methode permettant de faire un enregistrement
	@FXML
	public void clickerSurBtnAjouter() {
		
		if(gestContrainte()){
			
			Evenement evenement=new Evenement(associationService.generationId(), dateEvent.getValue(), txfLieu.getText(), txfObjet.getText(), txfAchat.getText(),Float.parseFloat(txfDepenstt.getText()),cbxSolde.getSelectionModel().getSelectedItem(),"ACTIF");
			
			if(service.create(evenement)) {
				
				association.getListEvenement().add(evenement);
				initChamps();
				
				//ajouter le message d'alert
				association.afficherVueValider();
				
			}
			
		}
		
	}
	
	//methode permettant de modifier un enregistrement
	@FXML
	public void clickerSurBtnModifier() {
		
		Evenement evenement=new Evenement(tableEvenement.getSelectionModel().getSelectedItem().getId(), dateEvent.getValue(), txfLieu.getText(), txfObjet.getText(), txfAchat.getText(),Float.parseFloat(txfDepenstt.getText()),cbxSolde.getSelectionModel().getSelectedItem(),"ACTIF");
		
		if(service.update(evenement)) {
			
			association.getListEvenement().set(tableEvenement.getSelectionModel().getSelectedIndex(), evenement);
			
			initChamps();
			
			tableEvenement.getSelectionModel().clearSelection();
			
			//ajouter le message d'alert
			association.afficherNotifModification();
			
		}
		
	}
	
	//methode permettant de supprimer un enregistrement
	@FXML
	public void clickerSurBtnSupprimer() {
		
		alert=new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Attention");
		alert.setContentText("Cette action entrainera la suppression de cet enregistrement");
		Optional<ButtonType> option=alert.showAndWait();
		if (option.get().equals(ButtonType.OK)) {
			
			if (service.delete(tableEvenement.getSelectionModel().getSelectedItem().getId())) {
				
				association.getListEvenement().remove(tableEvenement.getSelectionModel().getSelectedIndex());
				
				lbMontEvent.setText(""+service.nbEnregist());
				
				association.afficherAlertSuppression();
				
			}
			
			if(tableEvenement.getSelectionModel().getSelectedIndex()<0) {
				
				initChamps();
				
				tableEvenement.getSelectionModel().clearSelection();
				
			}
			
		}else {
			
			association.afficherAlerteAnnulation();
			
		}
		
		
		
	}
	
	//methode permettant de faire un reset
	@FXML
	public void clickerSurBtnReset() {
		
		initChamps();
		
		tableEvenement.getSelectionModel().clearSelection();
		
	}
	
	//methode permettant de modifier la classe principale
	public void setGestionAssociation(GestionAssociation association) {
		
		this.association=association;
		tableEvenement.setItems(association.getListEvenement());
		cbxSolde.setItems(association.getListTypSolde());
		
	}

}
