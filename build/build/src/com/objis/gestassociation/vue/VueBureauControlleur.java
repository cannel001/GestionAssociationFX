package com.objis.gestassociation.vue;


import java.util.Optional;

import com.objis.gestassociation.GestionAssociation;
import com.objis.gestassociation.domaine.Adherent;
import com.objis.gestassociation.domaine.Bureau;
import com.objis.gestassociation.service.impl.BureauService;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class VueBureauControlleur {
	
	//les proprietes
	private GestionAssociation association;
	private BureauService service=new BureauService();
	private Bureau bureau;
	private Alert alert;
	
	@FXML
	private TableView<Bureau> tableBureau;
	
	@FXML
	private Label lbTtMembre;
	
	@FXML
	private TableColumn<Bureau, String> columFonction;
	@FXML
	private TableColumn<Bureau, String> columPseudo;
	
	@FXML
	private ComboBox<Adherent> cbxAdherent;
	@FXML
	private ComboBox<String> cbxFonction=new ComboBox<>();
	
	@FXML
	private TextField txfPseudo;
	@FXML
	private TextField txfPasword;
	
	@FXML
	private Button btnAjouter;
	@FXML
	private Button btnModifier;
	@FXML
	private Button btnSUpprimer;
	@FXML
	private Button btnReset;
	
	
	
	//methode permettant d'initialiser la vue
	@FXML
	public void initialize() {
		
		inichamps();
		
		columFonction.setCellValueFactory(e->e.getValue().fonctionProperty());
		columPseudo.setCellValueFactory(e->e.getValue().loginProperty());
		
		//recuperer un enregistrement lorsqu'on click sur une ligne du tableau
		tableBureau.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->changerValuerChamps(newValue));
		
		
	}
	
	//methode permettant de changer les valeurs des champs de saises
	public void changerValuerChamps(Bureau bureau) {
		
		if (bureau!=null) {
			
			txfPseudo.setText(bureau.getLogin());
			txfPasword.setText(bureau.getPassword());
			
			//selectionner la ligne de adherent
			for (Adherent adherent : association.getListAdherent()) {
				
				if (adherent.getId().equals(tableBureau.getSelectionModel().getSelectedItem().getId())) {
					cbxAdherent.getSelectionModel().select(adherent);
					cbxAdherent.setDisable(true);
				}
				
			}
			
			//selectionne la ligne de la fonction
			for (String fonction : association.getListFocntion()) {
				
				if(fonction.equals(tableBureau.getSelectionModel().getSelectedItem().getFonction())){
					cbxFonction.getSelectionModel().select(fonction);
				}
				
			}
			
			//changement d'etat des boutons
			btnAjouter.setDisable(true);
			btnModifier.setDisable(false);
			btnReset.setDisable(false);
			btnSUpprimer.setDisable(false);
			
		}
			
	}
	
	//methode permettant d'initialiser les champs de saises et butons
	public void inichamps() {
		
		cbxFonction.getSelectionModel().clearSelection();
		cbxAdherent.getSelectionModel().clearSelection();
		txfPasword.setText("");
		txfPseudo.setText("");
		
		btnAjouter.setDisable(false);
		btnModifier.setDisable(true);
		btnSUpprimer.setDisable(true);
		btnReset.setDisable(true);
		
		cbxAdherent.getSelectionModel().clearSelection();
		cbxAdherent.setDisable(false);
		cbxFonction.getSelectionModel().clearSelection();
		
		lbTtMembre.setText(""+service.nbMembr());
		
	}
	
	
	//method permettant d'ajouter un enregistrement
	@FXML
	public void clickerSurBtnAjouter() {
		
		if(gestContrainte()) {
			
			bureau=new Bureau();
			
			bureau.setFonction(cbxFonction.getSelectionModel().getSelectedItem());
			bureau.setLogin(txfPseudo.getText().trim());
			bureau.setPassword(txfPasword.getText().trim());
			bureau.setEtatBureau("ACTIF");
			bureau.setId(cbxAdherent.getSelectionModel().getSelectedItem().getId());
			
			//recuperation de l'enregistrement si le login est existe
			Bureau bureau2=service.readOneByLogin(bureau.getLogin());
			
			if(!(bureau2!=null)) {
				
				//verifier si ce membre n'appartient pas au bureau avant de l'ajouter
				if(!(service.readOne(bureau.getId()) != null)) {
					
					//verifier si ce memebre n'est pas supprimé
					if(!(service.readOneMembreSupprimer(bureau.getId()) != null)) {
						
						//ajout dans la base de données
						if(service.create(bureau)) {
							
							//ajout à la liste Bureau
							association.getListBureau().add(bureau);
							
							//initialisation des champs
							inichamps();
							
							//ajouter le message d'alert de validation
							association.afficherVueValider();
							
						}else {
							
							association.setMessageErreur("Erreur survenue pendant l'enregistrement");
							association.afficherAlerteErreur();
							
						}
						
					}else {
						
						alert=new Alert(AlertType.CONFIRMATION);
						alert.setTitle("Doublon detecté");
						alert.setContentText("Ce membre a été supprimé. Voulez-vous le restaurer et apporter vos modifications ?");
						Optional<ButtonType> option=alert.showAndWait();
						if(option.get().equals(ButtonType.OK)) {
							
							System.out.println("ok");
							//modification
							modifEnregistrement(bureau,false);	
							
						}else {
							
							association.afficherAlerteAnnulation();
							
						}
						
					}
						
				}else {
					
					association.setMessageErreur("Ce membre appartient déjà au bureau");
					association.afficherAlerteErreur();
					
				}	
				
			}else {
				
				association.setMessageErreur("Ce login existe déjà");
				association.afficherAlerteErreur();
				
			}
				
		}
			
	}
	
	//methode permettant de gerer les contraintes
	public Boolean gestContrainte() {
		
		if(cbxFonction.getSelectionModel().getSelectedIndex()>-1) {
			
			if(cbxAdherent.getSelectionModel().getSelectedIndex()>-1) {
				
				if(!(txfPseudo.getText().equals(""))) {
					
					if(!(txfPasword.getText().equals(""))) {
						
						return true;
						
					}else {
						
						association.setMessageErreur("Veuillez entrer le mot de passe avant de continuer le traitement svp");
						association.afficherAlerteErreur();		
					}
					
				}else {
					
					association.setMessageErreur("Veuillez entrer l'username avant de continuer le traitement svp");
					association.afficherAlerteErreur();	
					
				}
				
			}else {
				
				association.setMessageErreur("Veuillez selectionner l'adherent avant de continuer le traitement svp");
				association.afficherAlerteErreur();	
				
			}
			
		}else {
			
			association.setMessageErreur("Veuillez selectionner la fonction avant de continuer le traitement svp");
			association.afficherAlerteErreur();	
			
		}
		
		return false;
		
	}
	
	//methode permettant de modifier un enregistremeny
	@FXML
	public void clickerSurBtnModifier() {
		
		if(gestContrainte()) {
			
			if(tableBureau.getSelectionModel().getSelectedIndex()>-1) {
				
				bureau=new Bureau();
				
				bureau.setFonction(cbxFonction.getSelectionModel().getSelectedItem());
				bureau.setLogin(txfPseudo.getText());
				bureau.setPassword(txfPasword.getText());
				bureau.setEtatBureau("ACTIF");
				bureau.setId(cbxAdherent.getSelectionModel().getSelectedItem().getId());
				
				Bureau bureau2=service.readOneByLogin(bureau.getLogin());
				
				if (bureau2!=null) {
					
					if(bureau2.getId().equals(bureau.getId())){
						
						//modification
						modifEnregistrement(bureau,true);
						
					}else {
						
						association.setMessageErreur("Ce login existe deja");
						association.afficherAlerteErreur();
						
					}
					
				}else {
					
					modifEnregistrement(bureau,true);
					
				}
	
			}	
						
		}
		
	}
	
	//method permettant de supprimer un enregistrement
	@FXML
	public void clickerSurBtnSupprimer() {
		
		alert=new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Attention");
		alert.setContentText("Cette action entrainera la suppression de cet enregistrement");
		Optional<ButtonType> option=alert.showAndWait();
		if(option.get().equals(ButtonType.OK)) {
			
			if(service.delete(tableBureau.getSelectionModel().getSelectedItem().getId())) {
				
				association.getListBureau().remove(tableBureau.getSelectionModel().getSelectedIndex());
				association.afficherAlertSuppression();
				lbTtMembre.setText(""+service.nbMembr());
				
			}else {
				
				association.setMessageErreur("Erreur survenue pendant la suppression");
				 association.afficherAlerteErreur();
				
			}

			if(tableBureau.getSelectionModel().getSelectedIndex()<0) {
				
				inichamps();
				
			}
			
		}else {
			
			association.afficherAlerteAnnulation();
			
		}
			
		
	}
	
	//methode permettant de faire un reset
	@FXML
	public void clickerSUrBtnReset() {
		
		inichamps();
		
		tableBureau.getSelectionModel().clearSelection();
		
	}
	
	//methode de faire une modification des enregostrements
	public void modifEnregistrement(Bureau bureau,Boolean modification) {
		
		if(service.update(bureau)) {
			
			if(modification.equals(true)) {
				association.getListBureau().set(tableBureau.getSelectionModel().getSelectedIndex(), bureau);
			}else {
				association.getListBureau().add(bureau);
			}
			
			inichamps();
			
			//ajouter le message d'alert
			association.afficherNotifModification();
			
		}else {
			
			association.setMessageErreur("Erreur survenue pendant la modification");
			association.afficherAlerteErreur();
			
		}
		
	}
	
	//methode permettant de modifier le menu principal
	public void setGestionAssociation(GestionAssociation association) {
		this.association=association;
		tableBureau.setItems(association.getListBureau());
		cbxFonction.setItems(association.getListFocntion());
		cbxAdherent.setItems(association.getListAdherent());
	}

}
