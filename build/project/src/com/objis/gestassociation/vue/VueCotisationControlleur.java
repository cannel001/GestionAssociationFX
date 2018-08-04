package com.objis.gestassociation.vue;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Optional;

import com.objis.gestassociation.GestionAssociation;
import com.objis.gestassociation.domaine.Adherent;
import com.objis.gestassociation.domaine.Cotisation;
import com.objis.gestassociation.service.impl.AdherentService;
import com.objis.gestassociation.service.impl.AssociationService;
import com.objis.gestassociation.service.impl.CotisationAnnuelleService;
import com.objis.gestassociation.service.impl.CotisationService;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.util.converter.FloatStringConverter;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

public class VueCotisationControlleur {
	
	//les proprietes
	private GestionAssociation association;
	private CotisationService cotisationService=new CotisationService();
	private AssociationService associtionService=new AssociationService();
	private CotisationAnnuelleService cotisationAnnuelleService=new CotisationAnnuelleService();
	private AdherentService adherentService=new AdherentService();
	private Alert alert;
	
	@FXML
	private Button btnAjouter;
	@FXML
	private Button btnmodifier;
	@FXML
	private Button btnSupprimer;
	@FXML
	private Button btnReset;
	
	@FXML
	private TextField txfMontant;
	
	@FXML
	private DatePicker dateMontant;
	
	@FXML
	private ComboBox<Adherent> cbxAdherent;
	
	@FXML
	private TableView<Cotisation> tableCotisation;
	
	@FXML
	private TableColumn<Cotisation,LocalDate> columDate;
	@FXML
	private TableColumn<Cotisation, Float> columMontant;
	
	@FXML
	private Label lbMontTT;
	
	//methode permettant d'initialiser la vue
	@FXML
	public void initialize() {
		
		inichamps();
		
		//formater le montant
		txfMontant.setTextFormatter(new TextFormatter<>(new FloatStringConverter()));
		
		columDate.setCellValueFactory(e->e.getValue().dateProperty());
		columMontant.setCellValueFactory(e->e.getValue().montantProperty());
		
		tableCotisation.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->chargerChamps(newValue));
		
	}
	
	//methode permettant de charger les champs de saisies
	public void chargerChamps(Cotisation cotisation) {
		
		if(cotisation!=null){
			
			txfMontant.setText(""+cotisation.getMontant());
			dateMontant.setValue(cotisation.getDate());
			
			cbxAdherent.getSelectionModel().select(adherentService.readOne(tableCotisation.getSelectionModel().getSelectedItem().getAdherent()));
			
			//changement d'etats des boutons
			btnAjouter.setDisable(true);
			btnmodifier.setDisable(false);
			btnSupprimer.setDisable(false);
			btnReset.setDisable(false);
			
		}

	}
	
	//methode permettant d'initialiser les champs
	public void inichamps() {
		
		dateMontant.getEditor().setText("");
		cbxAdherent.getSelectionModel().select(null);
		txfMontant.setText("");
		
		btnAjouter.setDisable(false);
		btnmodifier.setDisable(true);
		btnSupprimer.setDisable(true);
		btnReset.setDisable(true);
		lbMontTT.setText(""+cotisationService.sommeCotisation()+" F CFA");
		
	}
	
	//methode permettant d'ajouter une cotisation
	@FXML
	public void clickerSurBtnAJouter() {
		
		if(gestContrainte()) {
			
			Long id=associtionService.generationId();
			
			Cotisation cotisation=new Cotisation(Float.parseFloat(txfMontant.getText()),dateMontant.getValue(),"ACTIF",id, cbxAdherent.getSelectionModel().getSelectedItem().getId());
			
			if (cotisationService.create(cotisation)) {
				
				inichamps();
				association.getListCotisation().add(cotisation);
				
				//mise à jour de la liste
				updateAllListCotisationAnnuell();
				//ajouter le message d'alert
				association.afficherVueValider();
					
			}else {
				
				association.setMessageErreur("Erreur survenue pendant l'enregistrement");
				 association.afficherAlerteErreur();
				
			}
			
		}
		
	}
	
	//methode permettant de modifier un enregistrement
	@FXML
	public void clickerSurBtnModifier() {
		
		if(gestContrainte()) {
			
			Cotisation cotisation=new Cotisation(Float.parseFloat(txfMontant.getText()),dateMontant.getValue(),"ACTIF",tableCotisation.getSelectionModel().getSelectedItem().getIdCotisation(), cbxAdherent.getSelectionModel().getSelectedItem().getId()); 
			
			if (cotisationService.update(cotisation)) {
				association.getListCotisation().set(tableCotisation.getSelectionModel().getSelectedIndex(),cotisation);
				inichamps();
				tableCotisation.getSelectionModel().clearSelection();
				
				//mise a jour de la liste des cotisations
				updateAllListCotisationAnnuell();
				//ajouter le message d'alert
				association.afficherNotifModification();
					
			}else {
				
				association.setMessageErreur("Erreur survenue pendant la modification");
				 association.afficherAlerteErreur();
				
			}
			
		}
		
	}
	
	//methode permettant de supprimer un enregistrement
	@FXML
	public void clickerSurBtnSupprimer() {
		
		alert=new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Attention");
		alert.setContentText("Cette action entrainera la suppression de ce enregistrement");
		Optional<ButtonType> option=alert.showAndWait();
		if(option.get().equals(ButtonType.OK)) {
			
			if(cotisationService.delete(tableCotisation.getSelectionModel().getSelectedItem().getIdCotisation())) {
				association.getListCotisation().remove(tableCotisation.getSelectionModel().getSelectedIndex());
				lbMontTT.setText(""+cotisationService.sommeCotisation()+" F CFA");
				
				//mise a jour des liste des tableaux
				updateAllListCotisationAnnuell();
				
				//message d'alerte
				association.afficherAlertSuppression();
				
			}else {
				
				association.setMessageErreur("Erreur survenue pendant la suppression");
				 association.afficherAlerteErreur();
				
			}
			
			if (tableCotisation.getSelectionModel().getSelectedIndex()<0) {
				tableCotisation.getSelectionModel().clearSelection();
				inichamps();
			}
			
		}else {
			
			association.afficherAlerteAnnulation();
			
		}
		
		
		
	}
	
	//methode permettant de faire un reset
	@FXML
	public void clickerSurBtnReset() {
		
		inichamps();
		
		tableCotisation.getSelectionModel().clearSelection();
		
	}
	
	//methode permettant de mettre à jour les listes des cotisations annuelles
	public void updateAllListCotisationAnnuell() {
		
		association.getListCotisationAnnuelle().setAll(cotisationAnnuelleService.getAllListCotisation(""+Calendar.getInstance().getWeekYear()));
		association.getListTtCotisationAnnuelleParMois().setAll(cotisationAnnuelleService.getTotalCotisationAnnuelleParAnnee(""+Calendar.getInstance().getWeekYear()));
		
	}
	
	
	//methode permettant de gerer les contraintes de saises
	public Boolean gestContrainte() {
		
		if(!(dateMontant.getEditor().getText().equals(""))) {
			
			if(!(txfMontant.getText().equals(""))) {
				
				if(associtionService.verifTypeFloat(txfMontant.getText().trim())) {
					
					if(cbxAdherent.getSelectionModel().getSelectedItem() != null) {
						
						return true;
						
					}else {
						
						association.setMessageErreur("Veuillez selectionner l'adherent avant de continuer le traitement svp");
						association.afficherAlerteErreur();
						
					}
					
				}else {
					
					association.setMessageErreur("Veuillez entrer un bon montant avant de continuer le traitement svp");
					association.afficherAlerteErreur();
					
				}
				
				
			}else {
					
					association.setMessageErreur("Veuillez entrer le montant avant de continuer le traitement svp");
					association.afficherAlerteErreur();
					
			}
				
		}else {
			
			association.setMessageErreur("Veuillez selectionner la date avant de continuer le traitement svp");
			association.afficherAlerteErreur();
			
		}
			
		return false;
		
	}
	
	
	//methode permettant de modifier la classe principal
	public void setGestionAssociation(GestionAssociation association) {
		
		this.association=association;
		
		tableCotisation.setItems(association.getListCotisation());
		cbxAdherent.setItems(association.getListAdherent());
		
		
	}
	
	
	
	

}
