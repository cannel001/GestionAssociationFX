package com.objis.gestassociation.vue;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Optional;

import com.objis.gestassociation.GestionAssociation;
import com.objis.gestassociation.domaine.Adherent;
import com.objis.gestassociation.service.impl.AdherentService;
import com.objis.gestassociation.service.impl.AssociationService;
import com.objis.gestassociation.service.impl.CotisationAnnuelleService;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.converter.IntegerStringConverter;


public class VueAdherentControlleur{
	
	//les proprietes
	private GestionAssociation association=new GestionAssociation();
	private AdherentService service=new AdherentService();
	private Adherent adherent; 
	private static Long idAdherent;
	private AssociationService associationService=new AssociationService();
	private Alert alert;

	//traiment de l'image
	private static String ext="";
	private static String cible="";
	private static FileInputStream fluxEntree=null;
	private File fichierSelectionner=null;
	
	private String sexe="";
	private String typePiece="";
	private String typAdherent="";
	
	@FXML
	private TableView<Adherent> tableAdherent;
	
	
	@FXML
	private TableColumn<Adherent, String> columNom;
	@FXML
	private TableColumn<Adherent, String> columPrenom;
	@FXML
	private TableColumn<Adherent, String> columMatricule;
	
	@FXML
	private Button btnAjouter;
	@FXML
	private Button btnModifier;
	@FXML
	private Button btnSupprimer;
	@FXML
	private Button btnReset;
	@FXML
	private Button btnGenerationMatri;
	@FXML
	private Button btnAjouterPhoto;
	
	@FXML
	private RadioButton selectInterne;
	@FXML
	private RadioButton selectExterne;
	
	@FXML
	private ImageView imgPhoto;
	
	@FXML
	protected TextField txfNom;
	@FXML
	private TextField txfPrenom;
	@FXML
	private TextField txfLieuNaissance;
	@FXML
	private TextField txfNationalite;
	@FXML
	private TextField txfTelephone;
	@FXML
	private TextField txfEmail;
	@FXML
	private TextField txfProfession;
	@FXML
	private TextField txfResidence;
	@FXML
	private TextField txfAdresse;
	@FXML
	private TextField txfNumPiece;
	@FXML
	private TextField txfMatricule;
	
	@FXML
	private RadioButton selectFem;
	@FXML
	private RadioButton selectHom;
	@FXML
	private RadioButton selectCni;
	@FXML
	private RadioButton selectPasseport;
	@FXML
	private RadioButton selectConsulaire;
	@FXML
	private RadioButton selectAutre;
	@FXML 
	private RadioButton selectAttestation;
	
	@FXML
	private DatePicker txfDateNaissance;
	@FXML
	private DatePicker txfDateEntree;
	
	@FXML
	private Label ttAdInterne;
	@FXML
	private Label ttAdExterne;
	@FXML
	private Label lbId;
	
	private FileInputStream lienPhoto;
	
	private CotisationAnnuelleService cotisationAnnuelleService=new CotisationAnnuelleService();
	
	private String date;

	
	//methode d'initialisation
	@FXML
	public void initialize() {
		
		initChamps();
		
		//formater le numero de telephone
		txfTelephone.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
		
		//retourner le total des adherents externes
		ttAdExterne.setText(""+service.nbAdherentexterne());
		
		//retourner le total des adherents internes
		ttAdInterne.setText(""+service.nbAdherentInterne());
		
		//image par defaut
		imgPhoto.setImage(new Image(new String("http://127.0.0.1/gestionAssociation/Java_Logo.svg.png")));
		
		
		txfMatricule.setDisable(true);
		
		columMatricule.setCellValueFactory(e->e.getValue().matriculeProperty());
		columNom.setCellValueFactory(e->e.getValue().nomProperty());
		columPrenom.setCellValueFactory(e->e.getValue().prenomProperty());
		
		//changer les champs lorsqu'on selectionne un champs de saises
		tableAdherent.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->chargerChamps(newValue));
		
		
	}
	
	/**
	 * methode permettant de gerer les contraintes
	 * @return
	 */
	public Boolean gestContraite() {
		
		if(!(txfNom.getText().equals(""))) {
			
			if(!(txfPrenom.getText().equals(""))){
				
				if (!(txfDateNaissance.getEditor().getText().equals(""))) {
					
					if(!(txfDateEntree.getEditor().getText().equals(""))) {
						
						if(!(sexe.equals(""))) {
								
								if(!(typePiece.equals(""))){
									
									if(!(txfMatricule.getText().equals(""))) {
										
										if(!(typAdherent.equals(""))) {
											
											return true;
											
										}else {
											
											association.setMessageErreur("Veuillez selectionner le type d'adherent avant de continuer ce traitement svp");
											association.afficherAlerteErreur();
											
										}
										
									}else {
										
										association.setMessageErreur("Veuillez generer le matricule avant de continuer ce traitement svp");
										association.afficherAlerteErreur();
										
									}
									
								}else {
									
									association.setMessageErreur("Veuillez selectionner le type de piece avant de continuer ce traitement svp");
									association.afficherAlerteErreur();
									
									}
									
						}else {
							
							association.setMessageErreur("Veuillez selectionner le sexe avant de continuer ce traitement svp");
							association.afficherAlerteErreur();
							
						}
								
					}else {
						
						association.setMessageErreur("Veuillez selectionner la date d'entrée avant de continuer ce traitement svp");
						association.afficherAlerteErreur();
						
					}
							
				}else {
					
					association.setMessageErreur("Veuillez selectionner la date de naissance avant de continuer ce traitement svp");
					association.afficherAlerteErreur();
					
				}
						
			}else {
				
				association.setMessageErreur("Veuillez entrer le(s) prenom(s) avant de continuer ce traitement svp");
				association.afficherAlerteErreur();

			}
					
		}else {
			
			association.setMessageErreur("Veuillez entrer le nom avant de continuer ce traitement svp");
			association.afficherAlerteErreur();
			
		}
									

		return false;
		
		
	}
	
	//changer les valeurs de champs de saisies
	public void chargerChamps(Adherent adherent) {
		
		if(adherent!=null) {
			
			
			lbId.setText(""+adherent.getId());
			txfNom.setText(adherent.getNom());
			txfPrenom.setText(adherent.getPrenom());
			txfLieuNaissance.setText(adherent.getLieuDeNaissance());
			txfNationalite.setText(adherent.getNationalite());
			txfTelephone.setText(adherent.getTelephone());
			txfEmail.setText(adherent.getEmail());
			txfProfession.setText(adherent.getProfession());
			txfResidence.setText(adherent.getResidence());
			txfAdresse.setText(adherent.getAdresse());
			txfNumPiece.setText(adherent.getNumeroPiece());
			txfMatricule.setText(adherent.getMatricule());
			
			
			
			if (adherent.getSexe().equals("HOMME")) {
				selectFem.setSelected(false);
				selectHom.setSelected(true);
				sexe="HOMME";
			}else if(adherent.getSexe().equals("FEMME")) {
				selectFem.setSelected(true);
				selectHom.setSelected(false);
				sexe="FEMME";
			}else {
				selectFem.setSelected(false);
				selectHom.setSelected(false);
				sexe="";
			}
			
			if (adherent.getTypePiece().equals("Cni")) {
				selectCni.setSelected(true);
				selectConsulaire.setSelected(false);
				selectAutre.setSelected(false);
				selectAttestation.setSelected(false);
				selectPasseport.setSelected(false);
				typePiece="Cni";
			} else if (adherent.getTypePiece().equals("Consulaire")) {
				selectCni.setSelected(false);
				selectConsulaire.setSelected(true);
				selectAutre.setSelected(false);
				selectAttestation.setSelected(false);
				selectPasseport.setSelected(false);
				typePiece="Consulaire";
			}else if (adherent.getTypePiece().equals("Autre")) {
				selectCni.setSelected(false);
				selectConsulaire.setSelected(false);
				selectAutre.setSelected(true);
				selectAttestation.setSelected(false);
				selectPasseport.setSelected(false);
				typePiece="Autre";
			}else if (adherent.getTypePiece().equals("Attestation")) {
				selectCni.setSelected(false);
				selectConsulaire.setSelected(false);
				selectAutre.setSelected(false);
				selectAttestation.setSelected(true);
				selectPasseport.setSelected(false);
				typePiece="Attestation";
			}else if(adherent.getTypePiece().equals("Passeport")) {
				selectCni.setSelected(false);
				selectConsulaire.setSelected(false);
				selectAutre.setSelected(false);
				selectAttestation.setSelected(false);
				selectPasseport.setSelected(true);
				typePiece="Passeport";
			} else {
				selectCni.setSelected(false);
				selectConsulaire.setSelected(false);
				selectAutre.setSelected(false);
				selectAttestation.setSelected(false);
				selectPasseport.setSelected(false);
				typePiece="";
			}
			
			if(adherent.getTypAdherent().equals("INTERNE")) {
				selectInterne.setSelected(true);
				selectExterne.setSelected(false);
				typAdherent="INTERNE";
			}else if(adherent.getTypAdherent().equals("EXTERNE")) {
				selectInterne.setSelected(false);
				selectExterne.setSelected(true);
				typAdherent="EXTERNE";
			}else {
				selectInterne.setSelected(false);
				selectExterne.setSelected(false);
				typAdherent="";
			}
			
			txfDateNaissance.setValue(adherent.getDateNaissance());
			txfDateEntree.setValue(adherent.getDateEntree());
			
			//changement d'etats des boutons
			btnAjouter.setDisable(true);
			btnGenerationMatri.setDisable(true);
			btnModifier.setDisable(false);
			btnReset.setDisable(false);
			btnSupprimer.setDisable(false);
			
			
			//charger une image par defaut
			if (!(adherent.getImage().equals(""))) {
				
				System.out.println(adherent.getImage());
				
				imgPhoto.setImage(new Image(new String("http://"+adherent.getImage())));
				
			}else {
				imgPhoto.setImage(new Image(new String("http://127.0.0.1/gestionAssociation/Java_Logo.svg.png")));
			}
			
			
		}
		
		
	}
	
	//methode permettant de generer le matricule
	public void clickerSurBtnGenerer() {
		txfMatricule.setText(service.matriculeGenerer());
	}
	
	//methode permettant d'ajouter une photo
	@FXML
	public void ajouterPhoto() {
		
		FileChooser boiteDeDialogu=new FileChooser();
		boiteDeDialogu.setTitle("Veuillez selectionner une photo");
		boiteDeDialogu.getExtensionFilters().addAll(new ExtensionFilter("Type image", "*.jpg","*.png","*.jpeg"));
		fichierSelectionner=boiteDeDialogu.showOpenDialog(null);
		
		if(fichierSelectionner!=null) {
			
			try {
				
				lienPhoto=new FileInputStream(fichierSelectionner);
				fluxEntree=new FileInputStream(fichierSelectionner);
				
				//recuperer l'extention
				ext=fichierSelectionner.toString().substring(fichierSelectionner.toString().lastIndexOf("."));
				System.out.println(fichierSelectionner);
				System.out.println(cible);
				System.out.println(ext);
				
				Image image=new Image(lienPhoto);
				imgPhoto.setImage(image);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
	}
	
	//initialiser les champs de saisies
	public void initChamps() {
		
		btnAjouter.setDisable(false);
		btnGenerationMatri.setDisable(false);
		btnModifier.setDisable(true);
		btnReset.setDisable(true);
		btnSupprimer.setDisable(true);
		
		//image par defaut
		imgPhoto.setImage(new Image(new String("http://"+"127.0.0.1/gestionAssociation/Java_Logo.svg.png")));
		
		lbId.setText("...");
		txfNom.setText("");
		txfPrenom.setText("");
		txfLieuNaissance.setText("");
		txfNationalite.setText("");
		txfTelephone.setText("");
		txfEmail.setText("");
		txfProfession.setText("");
		txfResidence.setText("");
		txfAdresse.setText("");
		txfNumPiece.setText("");
		txfMatricule.setText("");
		selectFem.setSelected(false);
		selectHom.setSelected(false);
		txfDateNaissance.setValue(null);
		txfDateEntree.setValue(null);
		selectCni.setSelected(false);
		selectConsulaire.setSelected(false);
		selectAutre.setSelected(false);
		selectAttestation.setSelected(false);
		selectPasseport.setSelected(false);
		selectInterne.setSelected(false);
		selectExterne.setSelected(false);
		
		typAdherent="";
		sexe="";
		typePiece="";
			
		
	}
	
	//mise a jour de la liste des cotsations annuelles
	public void updateListCotisationAnnuelle() {
		date=""+Calendar.getInstance().getWeekYear();
		association.getListCotisationAnnuelle().setAll(cotisationAnnuelleService.getAllListCotisation(date));
		association.getListTtCotisationAnnuelleParMois().setAll(cotisationAnnuelleService.getTotalCotisationAnnuelleParAnnee(date));
	}
			
	
	
	//methode permettant de gerer les radio buttons du type adherent
	@FXML
	public void clickerSurRadioInterne() {
		if(selectInterne.isSelected()) {
			selectExterne.setSelected(false);
			typAdherent="INTERNE";
		}else {
			typAdherent="";
		}
	}
	
	
	@FXML
	public void clickerSurRadioExterne() {
		if(selectExterne.isSelected()) {
			selectInterne.setSelected(false);
			typAdherent="EXTERNE";
		}else {
			typAdherent="";
		}
	}
	
	
	
	
	//methode permettant de gerer les radios buttons du sexe
	@FXML
	public void radioButonHomme() {
		if (selectHom.isSelected()) {
			selectFem.setSelected(false);
			sexe="HOMME";
		}
		else {
			sexe="";
		}
	}
	
	@FXML
	public void radioButonFemme() {
		if (selectFem.isSelected()) {
			selectHom.setSelected(false);
			sexe="FEMME";
		}else {
			sexe="";
		}
	}
	
	
	/**
	 * methode permettant de gerer le type de piece
	 */
	@FXML
	public void ClickerSurRadioButonCni() {
		if (selectCni.isSelected()) {
			selectPasseport.setSelected(false);
			selectConsulaire.setSelected(false);
			selectAutre.setSelected(false);
			selectAttestation.setSelected(false);
			typePiece="Cni";
		}else {
			typePiece="";
		}
	}
	
	@FXML
	public void ClickerSurRadioButonAutre() {
		if (selectAutre.isSelected()) {
			selectCni.setSelected(false);
			selectPasseport.setSelected(false);
			selectConsulaire.setSelected(false);
			selectAttestation.setSelected(false);
			typePiece="Autre";
		}else {
			typePiece="";
		}
	}
	
	@FXML
	public void ClickerSurRadioButonAttestation() {
		if (selectAttestation.isSelected()) {
			selectCni.setSelected(false);
			selectPasseport.setSelected(false);
			selectConsulaire.setSelected(false);
			selectAutre.setSelected(false);
			typePiece="Attestation";
		}else {
			typePiece="";
		}
	}
	
	@FXML
	public void ClickerSurRadioButonPasseport() {
		if (selectPasseport.isSelected()) {
			selectCni.setSelected(false);
			selectConsulaire.setSelected(false);
			selectAutre.setSelected(false);
			selectAttestation.setSelected(false);
			typePiece="Passeport";
		}else {
			typePiece="";
		}
	}
	
	@FXML
	public void ClickerSurRadioButonConsulaire() {
		if (selectConsulaire.isSelected()) {
			selectCni.setSelected(false);
			selectPasseport.setSelected(false);
			selectAutre.setSelected(false);
			selectAttestation.setSelected(false);
			typePiece="Consulaire";
		}else {
			typePiece="";
		}
	}
	
	
	//methode permettant d'ajouter un adherent dans la base de données
		@FXML
		public void clickerSurBtnAjouter() {
			
			if(gestContraite()) {
				
				//recuperation de l'id
				idAdherent=associationService.generationId()+service.nbAdherentexterne()+service.nbAdherentInterne();
				
				//uploader l'image par ftp
				if(fichierSelectionner!=null) {
					
					if(service.uploadImageFtp(idAdherent+ext, fluxEntree)) {
						cible="127.0.0.1/gestionAssociation/photoAdherents/"+idAdherent+ext;
					}
					
				}
				
				
				adherent=new Adherent(idAdherent, txfNom.getText(), txfPrenom.getText(),txfDateNaissance.getValue(),sexe, txfNationalite.getText(), txfNumPiece.getText(),typePiece,txfProfession.getText(),txfResidence.getText(),txfLieuNaissance.getText(),txfAdresse.getText(),txfTelephone.getText(),txfEmail.getText(),txfDateEntree.getValue(),txfMatricule.getText(),typAdherent,cible,"ACTIF");
				
				if (service.create(adherent)) {
					
					association.getListAdherent().add(adherent);
					
					initChamps();
					
					updateListCotisationAnnuelle();
					
					//retourner le total des adherents externes
					ttAdExterne.setText(""+service.nbAdherentexterne());
					
					//retourner le total des adherents internes
					ttAdInterne.setText(""+service.nbAdherentInterne());
					
					//ajouter le message d'alert
					association.afficherVueValider();
					
				}else {
					
					 association.setMessageErreur("Erreur survenue pendant l'enregistrement");
					 association.afficherAlerteErreur();
					
				}	
				
			}
		
	}
	
	
	//methode permattant de modifier un enregistrement
	@FXML
	public void clickerSurBtnModifier() {
		
		if(gestContraite()) {
			
			if (fichierSelectionner!=null) {
				
				//recuperation de l'id
				Long idModif=tableAdherent.getSelectionModel().getSelectedItem().getId();
				
				//uploader l'image par ftp
				if(service.uploadImageFtp(idModif+ext, fluxEntree)) {
					cible="127.0.0.1/gestionAssociation/photoAdherents/"+idModif+ext;
				}
				
			}
			
			adherent=new Adherent(tableAdherent.getSelectionModel().getSelectedItem().getId(), txfNom.getText(), txfPrenom.getText(),txfDateNaissance.getValue(),sexe, txfNationalite.getText(), txfNumPiece.getText(),typePiece,txfProfession.getText(),txfResidence.getText(),txfLieuNaissance.getText(),txfAdresse.getText(),txfTelephone.getText(),txfEmail.getText(),txfDateEntree.getValue(),txfMatricule.getText(),typAdherent,cible,"ACTIF");
			
			if(service.update(adherent)) {
				
				association.getListAdherent().set(tableAdherent.getSelectionModel().getSelectedIndex(), adherent);
				
				initChamps();
				
				updateListCotisationAnnuelle();
				
				tableAdherent.getSelectionModel().clearSelection();
				
				//retourner le total des adherents externes
				ttAdExterne.setText(""+service.nbAdherentexterne());
				
				//retourner le total des adherents internes
				ttAdInterne.setText(""+service.nbAdherentInterne());
				
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
		
		if(!(service.verifAdherentBureau(tableAdherent.getSelectionModel().getSelectedItem().getId()))) {
			
			if(!(service.verifAdherentCotisation(tableAdherent.getSelectionModel().getSelectedItem().getId()))) {
				
				alert=new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Attention");
				alert.setContentText("Cette action entrainera la suppression de l'enregistrement");
				Optional<ButtonType> option= alert.showAndWait();
				if(option.get().equals(ButtonType.OK)) {
					
					if (service.delete(tableAdherent.getSelectionModel().getSelectedItem().getId())) {
						
						association.getListAdherent().remove(tableAdherent.getSelectionModel().getSelectedIndex());
						
						updateListCotisationAnnuelle();
						
						if(tableAdherent.getSelectionModel().getSelectedIndex()<0) {
							initChamps();
						}
						
						//retourner le total des adherents externes
						ttAdExterne.setText(""+service.nbAdherentexterne());
						
						//retourner le total des adherents internes
						ttAdInterne.setText(""+service.nbAdherentInterne());
						
						association.afficherAlertSuppression();
						
					}else {
						
						association.setMessageErreur("Erreur survenue pendant la suppression");
						association.afficherAlerteErreur();
						
					}
					
				}else {
					association.afficherAlerteAnnulation();
				}
				
			}else {
				
				association.setMessageErreur("Cette suppression est impossible car ce adherent est associé à une ou plusieurs cotisations");
				association.afficherAlerteErreur();
				
			}
			
			
		}else {
			
			association.setMessageErreur("Cette suppression est impossible car ce adherent est un membre du bureau");
			association.afficherAlerteErreur();
			
		}
		
	}
	
	
	//methode permettant de faire un reset
	@FXML
	public void clickerSurBtnReset() {
		
		initChamps();
		
		tableAdherent.getSelectionModel().clearSelection();
		
	}
	
	//methode permettant de modifier la classe principal
	public void setGestionAssociation(GestionAssociation association) {
		
		//ouverture du message d'attente
		association.setTraitementEnCours(true);
		association.afficherVueAttenteUser();
		
		this.association=association;
		tableAdherent.setItems(association.getListAdherent());
		
		//fermeture du message d'attente
		association.setTraitementEnCours(false);
		
	}

}
