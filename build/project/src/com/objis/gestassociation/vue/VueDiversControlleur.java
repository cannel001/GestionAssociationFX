package com.objis.gestassociation.vue;

import java.time.LocalDate;
import java.util.Optional;

import com.objis.gestassociation.GestionAssociation;
import com.objis.gestassociation.domaine.Divers;
import com.objis.gestassociation.service.impl.AssociationService;
import com.objis.gestassociation.service.impl.DiversService;

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

public class VueDiversControlleur {
	
	//les proprietes
	private GestionAssociation association;
	private DiversService diversService=new DiversService();
	private AssociationService associationService=new AssociationService();
	
	@FXML
	private DatePicker date;
	
	@FXML
	private TextField txfLieu;
	@FXML
	private TextField txfMotif;
	@FXML
	private TextArea txfCorpsFormulaire;
	
	@FXML
	private ComboBox<String> cbxTypFormulaire;
	
	@FXML
	private Label lbTt;
	
	@FXML
	private Button btnAjouter;
	@FXML
	private Button btnModifier;
	@FXML
	private Button btnSupprimer;
	@FXML
	private Button btnReset;
	
	@FXML
	private TableView<Divers> tableDivers;
	
	@FXML
	private TableColumn<Divers,LocalDate> columDate;
	@FXML
	private TableColumn<Divers, String> columLieu;
	@FXML
	private TableColumn<Divers, String> columTypeFormulaire;
	
	private Divers divers;
	
	private Alert alert;
	
	
	//methode permettant d'initialiser la vue 
	@FXML
	public void initialize() {
		
		initChamps();
		
		columDate.setCellValueFactory(e->e.getValue().dateProperty());
		columLieu.setCellValueFactory(e->e.getValue().lieuProperty());
		columTypeFormulaire.setCellValueFactory(e->e.getValue().typeFormulaireProperty());
		
		//ecouter la selection d'une ligne du tableau et remplir la methode chargeChamps
		tableDivers.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->remplirChamps(newValue));
	
	}
	
	//methode permettant de remplir les champs de saisies
	public void remplirChamps(Divers divers) {
		
		if(divers!=null) {
			
			txfCorpsFormulaire.setText(divers.getCorpsFormalire());
			txfLieu.setText(divers.getLieu());
			txfMotif.setText(divers.getMotif());
			date.setValue(divers.getDate());
			
			btnAjouter.setDisable(true);
			btnModifier.setDisable(false);
			btnSupprimer.setDisable(false);
			btnReset.setDisable(false);
			
			cbxTypFormulaire.getSelectionModel().select(divers.getTypeFormulaire());
			
		}
		
		
	}
	
	//methode permettant d'initialiser les champs de saisies
	public void initChamps() {
		
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
		
	}
	
	//Methode permettant de faire un enregistrement
	@FXML
	public void clickerSurBtnEnregistrement() {
		
		if(gestContrainte()) {
			
			divers=new Divers(date.getValue(),txfLieu.getText(),cbxTypFormulaire.getSelectionModel().getSelectedItem(),txfMotif.getText(),txfCorpsFormulaire.getText(),associationService.generationId(),"ACTIF");
			
			if(diversService.create(divers)) {
				association.getListDivers().add(divers);
				association.afficherVueValider();
				initChamps();
			}else {
				
				association.setMessageErreur("Erreur survenue pendant l'enregistrement");
				association.afficherAlerteErreur();
				
			}
			
		}	
		
	}
	
	//methode permettant de faire une modification
	@FXML
	public void clickerSurBtnModifier() {
		
		if(gestContrainte()) {
			
			divers=new Divers(date.getValue(),txfLieu.getText(),cbxTypFormulaire.getSelectionModel().getSelectedItem(),txfMotif.getText(),txfCorpsFormulaire.getText(),tableDivers.getSelectionModel().getSelectedItem().getId(),"ACTIF");
			
			if(diversService.update(divers)) {
				
				association.getListDivers().set(tableDivers.getSelectionModel().getSelectedIndex(), divers);
				
				initChamps();
				
				association.afficherNotifModification();
				
				tableDivers.getSelectionModel().clearSelection();
				
			}else {
				
				association.setMessageErreur("Erreur survenue pendant la modification");
				association.afficherAlerteErreur();
				
			}
			
		}
		
	}
	
	//methode permettant de faire une suppression
	@FXML
	public void clickerSurBtnSupprimer() {
		
		alert=new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Attention");
		alert.setContentText("Cette action entrainera la suppression de cet enregistrement");
		Optional<ButtonType> option=alert.showAndWait();
		if(option.get().equals(ButtonType.OK)) {
			
			if(diversService.delete(tableDivers.getSelectionModel().getSelectedItem().getId())) {
				
				association.afficherAlertSuppression();
				
				association.getListDivers().remove(tableDivers.getSelectionModel().getSelectedIndex());
				
				lbTt.setText(""+diversService.nbEnregistrement());
				
			}else {
				
				association.setMessageErreur("Erreur survenue pendant la suppression");
				association.afficherAlerteErreur();
				
			}
			
		}else {
			
			association.afficherAlerteAnnulation();
			
		}
		
		if(tableDivers.getSelectionModel().getSelectedIndex()<0) {
			
			initChamps();
			
			tableDivers.getSelectionModel().clearSelection();
			
		}
		
	}
	
	//Methode permettant de faire un reset
	@FXML
	public void clickerSurBtnreset() {
		
		initChamps();
		
		tableDivers.getSelectionModel().clearSelection();
		
	}
	
	//Methode permettant de gerer les contraintes
	public Boolean gestContrainte() {
		
		if(!(date.getEditor().getText().equals(""))){
			
			if (!(txfLieu.getText().equals(""))) {
				
				if(cbxTypFormulaire.getSelectionModel().getSelectedIndex()>-1) {
					
					if(!(txfMotif.getText().equals(""))) {
						
						if(!(txfCorpsFormulaire.getText().equals(""))) {
							
							return true;
							
						}else {
							
							association.setMessageErreur("Veuillez remplir le coprs du formulaire avant de continuer ce traitement");
							association.afficherAlerteErreur();
							
						}
						
					}else {
						
						association.setMessageErreur("Veuillez entrer le motif avant de continuer ce traitement");
						association.afficherAlerteErreur();
						
					}
					
				}else {
					
					association.setMessageErreur("Veuillez selectionner le type de formulaire avant de continuer ce traitement");
					association.afficherAlerteErreur();
					
				}
				
			}else {
				
				association.setMessageErreur("Veuillez entrer le lieu avant de continuer ce traitement");
				association.afficherAlerteErreur();
				
			}
			
		}else {
			
			association.setMessageErreur("Veuillez entrer la date avant de continuer ce traitement");
			association.afficherAlerteErreur();
			
		}
		
		return false;
		
	}
	
	
	//methode permettant de modifier la classe principale
	public void setGestionAssociation(GestionAssociation association) {
		this.association=association;
		tableDivers.setItems(association.getListDivers());
		cbxTypFormulaire.setItems(association.getListTypFormulaire());
	}

}
