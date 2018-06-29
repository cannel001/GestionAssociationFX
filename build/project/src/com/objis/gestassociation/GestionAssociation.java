package com.objis.gestassociation;

import java.io.IOException;
import java.util.Calendar;

import com.objis.gestassociation.domaine.Adherent;
import com.objis.gestassociation.domaine.Bureau;
import com.objis.gestassociation.domaine.Cotisation;
import com.objis.gestassociation.domaine.CotisationAnnuelle;
import com.objis.gestassociation.domaine.Divers;
import com.objis.gestassociation.domaine.Evenement;
import com.objis.gestassociation.domaine.VieSociale;
import com.objis.gestassociation.service.impl.AdherentService;
import com.objis.gestassociation.service.impl.AssociationService;
import com.objis.gestassociation.service.impl.BureauService;
import com.objis.gestassociation.service.impl.CaisseService;
import com.objis.gestassociation.service.impl.CotisationAnnuelleService;
import com.objis.gestassociation.service.impl.CotisationService;
import com.objis.gestassociation.service.impl.DiversService;
import com.objis.gestassociation.service.impl.EvenementService;
import com.objis.gestassociation.service.impl.VieSocialeService;
import com.objis.gestassociation.vue.VueAdherentControlleur;
import com.objis.gestassociation.vue.VueBureauControlleur;
import com.objis.gestassociation.vue.VueChangerDePassControlleur;
import com.objis.gestassociation.vue.VueCommissaireControleur;
import com.objis.gestassociation.vue.VueConnexionControlleur;
import com.objis.gestassociation.vue.VueCotisationAnnuelleControlleur;
import com.objis.gestassociation.vue.VueCotisationControlleur;
import com.objis.gestassociation.vue.VueDiversControlleur;
import com.objis.gestassociation.vue.VueEntreeSortieControlleur;
import com.objis.gestassociation.vue.VueErreurNotifControlleur;
import com.objis.gestassociation.vue.VueEvenementControlleur;
import com.objis.gestassociation.vue.VueMenuPrincipalController;
import com.objis.gestassociation.vue.VueMiniSlideBarControlleur;
import com.objis.gestassociation.vue.VueRencontreControlleur;
import com.objis.gestassociation.vue.VueSlideBarControlleur;
import com.objis.gestassociation.vue.VueVieSocialeControlleur;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GestionAssociation extends Application {
	
	private Long idAdherent;
	
	private GestionAssociation gestionAssociation;
	
	private Stage primaryStage;
	private Stage stageAttenteUser;
	private Stage stageAlerte=new Stage();
	
	private String messageErreur;
	
	private AnchorPane pane=new AnchorPane();
	
	private static BorderPane menuPrincipal=new BorderPane();
	
	private AnchorPane vueSlider;
	
	private Boolean traitementEnCours=false;
	
	private Adherent adherent;
	
	private Bureau bureau;
	
	//les services
	private AdherentService adherentService=new AdherentService();
	private BureauService bureauService=new BureauService();
	private CotisationService cotisationService=new CotisationService();
	private EvenementService evenementService=new EvenementService();
	private CotisationAnnuelleService cotisationAnnuelleService=new CotisationAnnuelleService();
	private AssociationService associationService=new AssociationService();
	private VieSocialeService vieSocialeService=new VieSocialeService();
	private DiversService diversService=new DiversService();
	
	//les listes
	private ObservableList<Adherent> listeAdherent=FXCollections.observableArrayList();
	private ObservableList<Bureau> listeBureau=FXCollections.observableArrayList();
	private ObservableList<String> listFonction=FXCollections.observableArrayList();
	private ObservableList<Bureau> infoMmbrConnecte=FXCollections.observableArrayList();
	private ObservableList<Cotisation> listeCotisation=FXCollections.observableArrayList();
	private ObservableList<String> listeTypSoldeEvenenemt=FXCollections.observableArrayList();
	private ObservableList<Evenement> listeEvenement=FXCollections.observableArrayList();
	private ObservableList<CotisationAnnuelle> listCotisationAnnuelle=FXCollections.observableArrayList();
	private ObservableList<Float> listTtCotisationAnnuelleParMois=FXCollections.observableArrayList();
	private ObservableList<VieSociale> listeVieSociale=FXCollections.observableArrayList();
	private ObservableList<String> listeTypAssuranc=FXCollections.observableArrayList();
	private ObservableList<Divers> listeDivers=FXCollections.observableArrayList();
	private ObservableList<String> listTypFormulaire=FXCollections.observableArrayList();
	private ObservableList<String> listeTypMouvement=FXCollections.observableArrayList();
	
	private String date;
	
	
	
	//constructeur
	public GestionAssociation() {
		
		date=""+Calendar.getInstance().getWeekYear();
		
		//recuperation de tous les enregistrements de la base de données
		listeAdherent.addAll(adherentService.readAll());
		listeBureau.addAll(bureauService.readAll());
		listeCotisation.addAll(cotisationService.readAll());
		listeEvenement.addAll(evenementService.readAll());
		listCotisationAnnuelle.addAll(cotisationAnnuelleService.getAllListCotisation(date));
		listTtCotisationAnnuelleParMois.addAll(cotisationAnnuelleService.getTotalCotisationAnnuelleParAnnee(date));
		listeVieSociale.addAll(vieSocialeService.readAll());
		listeDivers.addAll(diversService.readAll());

		
		//ajout des informations à combo box
		listeTypSoldeEvenenemt.add("OK");
		listeTypSoldeEvenenemt.add("EN COURS");
		listeTypSoldeEvenenemt.add("AVORTE");
		listeTypSoldeEvenenemt.add("AVORTE");
		listeTypSoldeEvenenemt.add("KO");
		
		//ajout des informations à combo fonction
		listFonction.add("PRESIDENT");
		listFonction.add("TRESORIER(E)");
		listFonction.add("COMMISSAIRE AU COMPTE");
		listFonction.add("SECRETAIRE");
		
		//ajout des informations au combo type formulaire
		listeTypAssuranc.add("ASSURANCE");
		listeTypAssuranc.add("MALADIE");
		listeTypAssuranc.add("DECES");
		
		//peuplement de la liste
		listTypFormulaire.add("DEMANDE AIDE");
		listTypFormulaire.add("PARTENARIAT");
		listTypFormulaire.add("PARRAINAGE");
		
		//peuplement de la liste type mouvement
		listeTypMouvement.add("ENTREE");
		listeTypMouvement.add("SORTIE");
		
	}
	

	@Override
	public void start(Stage primaryStage) {
		
		this.primaryStage=primaryStage;
		
		primaryStage.setTitle("GESTION ASSOCIATION");
		
		afficherFenetreConnexion();
		
		primaryStage.show();
		
	}
	
	
	public void afficherFenetreConnexion() {
		
		pane=new AnchorPane();
		
		FXMLLoader loader=new FXMLLoader();
		
		loader.setLocation(getClass().getResource("vue/VueConnexion.fxml"));
		
		try {
			pane=loader.load();
			
			Scene scene=new Scene(pane);
			
			primaryStage.setScene(scene);
			
			primaryStage.setResizable(false);
			primaryStage.setMaximized(false);
			
			VueConnexionControlleur controlleur=loader.getController();
			
			controlleur.setGestionAssociation(this);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("echec");
		}
	}
	
	//methode permettant de changer de fenetre
	public void changerFenetre(String url) {
		
		try {
			
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(getClass().getResource(url));
			
			menuPrincipal=(BorderPane)loader.load();
			
			Scene scene=new Scene(menuPrincipal);
			//ajouter la vue slider au menu principal
			primaryStage.setScene(scene);
			primaryStage.setMaximized(true);
			
			VueMenuPrincipalController controller=loader.getController();
			
			controller.setGestion(this);
			
			chargerVueSlide();
			
			chargerVueCentre("vue/VueAccueil.fxml");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//methode permettant d'afficher la vue changer mot de passe
	public void afficherVueChangerMdp() {
		
		AnchorPane root=new AnchorPane();
		
		FXMLLoader loader=new FXMLLoader();
		
		loader.setLocation(getClass().getResource("vue/VueChangerMotDePasse.fxml"));
		
		try {
			
			root=(AnchorPane)loader.load();
			primaryStage.setScene(new Scene(root));
			
			VueChangerDePassControlleur controlleur=loader.getController();
			controlleur.setGestion(this);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	//methode permettant de changer les vues au centre
	public void chargerVueCentre(String url) {
			
			//charger les controller
			if (url.equals("vue/VueAdherent.fxml")) {
				
				try {
					VBox parent=new VBox();
					FXMLLoader loader=new FXMLLoader();
					loader.setLocation(getClass().getResource(url));
					parent=(VBox)loader.load();
					menuPrincipal.setCenter(parent);		
					VueAdherentControlleur controlleur=loader.getController();
					controlleur.setGestionAssociation(this);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} else if(url.equals("vue/VueAccueil.fxml")) {
				try {
					AnchorPane parent=new AnchorPane();
					FXMLLoader loader=new FXMLLoader();
					loader.setLocation(getClass().getResource(url));
					parent=(AnchorPane)loader.load();
					menuPrincipal.setCenter(parent);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} else if(url.equals("vue/VueBureau.fxml")) {
				try {
					VBox parent=new VBox();
					FXMLLoader loader=new FXMLLoader();
					loader.setLocation(getClass().getResource(url));
					parent=(VBox)loader.load();
					menuPrincipal.setCenter(parent);
					VueBureauControlleur controlleur=loader.getController();
					controlleur.setGestionAssociation(this);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} 
			
			else if(url.equals("vue/VueCotisation.fxml")) {
				
				try {
					VBox parent=new VBox();
					FXMLLoader loader=new FXMLLoader();
					loader.setLocation(getClass().getResource(url));
					parent=(VBox)loader.load();
					menuPrincipal.setCenter(parent);		
					VueCotisationControlleur controlleur=loader.getController();
					controlleur.setGestionAssociation(this);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} else if(url.equals("vue/VueCotisationAnnuelle.fxml")) {
				
				try {
					VBox parent=new VBox();
					FXMLLoader loader=new FXMLLoader();
					loader.setLocation(getClass().getResource(url));
					parent=(VBox)loader.load();
					menuPrincipal.setCenter(parent);		
					VueCotisationAnnuelleControlleur controlleur=loader.getController();
					controlleur.setGestionAssociation(this);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}  else if(url.equals("vue/VueDivers.fxml")) {
				
				try {
					VBox parent=new VBox();
					FXMLLoader loader=new FXMLLoader();
					loader.setLocation(getClass().getResource(url));
					parent=(VBox)loader.load();
					menuPrincipal.setCenter(parent);		
					VueDiversControlleur controlleur=loader.getController();
					controlleur.setGestionAssociation(this);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} else if(url.equals("vue/VueVieSociale.fxml")) {
				
				try {
					VBox parent=new VBox();
					FXMLLoader loader=new FXMLLoader();
					loader.setLocation(getClass().getResource(url));
					parent=(VBox)loader.load();
					menuPrincipal.setCenter(parent);		
					VueVieSocialeControlleur controlleur=loader.getController();
					controlleur.setGestionAssociation(this);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} else if(url.equals("vue/VueRencontre.fxml")) {
				
				try {
					VBox parent=new VBox();
					FXMLLoader loader=new FXMLLoader();
					loader.setLocation(getClass().getResource(url));
					parent=(VBox)loader.load();
					menuPrincipal.setCenter(parent);		
					VueRencontreControlleur controlleur=loader.getController();
					controlleur.setGestionAssociation(this);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} else if(url.equals("vue/VueEvenement.fxml")) {
				
				try {
					VBox parent=new VBox();
					FXMLLoader loader=new FXMLLoader();
					loader.setLocation(getClass().getResource(url));
					parent=(VBox)loader.load();
					menuPrincipal.setCenter(parent);
					VueEvenementControlleur controlleur=loader.getController();
					controlleur.setGestionAssociation(this);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} else if(url.equals("vue/VueEntreeSortie.fxml")) {
				
				try {
					VBox root=new VBox();
					FXMLLoader loader=new FXMLLoader();
					loader.setLocation(getClass().getResource(url));
					root=(VBox)loader.load();
					menuPrincipal.setCenter(root);
					VueEntreeSortieControlleur controlleur=loader.getController();
					controlleur.setGestionAssociation(this);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else if(url.equals("vue/VueComissaireCompte.fxml")) {
				
				try {
					VBox root=new VBox();
					FXMLLoader loader=new FXMLLoader();
					loader.setLocation(getClass().getResource(url));
					root=(VBox)loader.load();
					menuPrincipal.setCenter(root);
					VueCommissaireControleur controleur=loader.getController();
					controleur.setGestion(this);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			
			
	}
	
	//methode pour charger la vue slider
	public void chargerVueSlide() {
		try {
			vueSlider=new AnchorPane();
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(getClass().getResource("vue/VueSlideBar.fxml"));
			vueSlider=(AnchorPane)loader.load();
			menuPrincipal.setLeft(vueSlider);
			VueSlideBarControlleur controlleur=loader.getController();
			controlleur.setGestionAssociation(this);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//methode permettant d'afficher la petite slide Bar
	public void afficherMiniSlideBar() {
		
		vueSlider=new AnchorPane();
		
		FXMLLoader loader=new FXMLLoader();
		
		loader.setLocation(getClass().getResource("vue/VueMiniSlideBar.fxml"));
		
		try {
			
			vueSlider=(AnchorPane)loader.load();
			
			menuPrincipal.setLeft(vueSlider);
			
			VueMiniSlideBarControlleur controlleur=loader.getController();
			
			controlleur.setGestion(this);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	//methode permettant de faire patienter l'user
	public void afficherVueAttenteUser() {
		
		AnchorPane root=new AnchorPane();
		
		FXMLLoader loader=new FXMLLoader();
		
		loader.setLocation(getClass().getResource("vue/VueAttenteTraitement.fxml"));
		
		try {
			
			root=(AnchorPane)loader.load();
			
			Scene scene=new Scene(root);
			
			stageAttenteUser=new Stage();
			
			scene.setFill(Color.TRANSPARENT);
			
			stageAttenteUser.setScene(scene);
			stageAttenteUser.initStyle(StageStyle.TRANSPARENT);			
			stageAttenteUser.initOwner(primaryStage);
			stageAttenteUser.initModality(Modality.WINDOW_MODAL);
			stageAttenteUser.centerOnScreen();
			stageAttenteUser.show();
			
			//traitement permettant de fermer automatiquement le stage d'alert
	
			new Service<Void>() {

				@Override
				protected Task<Void> createTask() {
					// TODO Auto-generated method stub
					return new Task<Void>() {

						@Override
						protected Void call() throws Exception {
							// TODO Auto-generated method stub
							
							while(getTraitementEnCours().equals(true)) {
								
								//attendre jusqu'a ce que la variable prenne false comme valeur
								System.out.println("passage dans la boucle");
								
							}
							
							//tread permettant de gerer la vue
							new Thread(()->{
								Platform.runLater(()->{
									stageAttenteUser.close();
								});
							}).start();
							
							return null;
						}
					};
				}
			}.start();
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
	
	//methode permettant d'afficher la vue valider
	public void afficherVueValider() {
		
		AnchorPane pane=new AnchorPane();
		
		FXMLLoader loader=new FXMLLoader();
		
		loader.setLocation(getClass().getResource("vue/VueValider.fxml"));
		
		try {
			
			if(stageAlerte.isShowing()) {
				stageAlerte.close();
			}
			
			stageAlerte=new Stage();
			
			pane=(AnchorPane)loader.load();
			
			Scene scene=new Scene(pane);
			
			scene.setFill(Color.TRANSPARENT);
			
			stageAlerte.setScene(scene);
			
			stageAlerte.initStyle(StageStyle.TRANSPARENT);
			
			stageAlerte.show();
			
			//lancement du thread d'arret
			associationService.closeAlert(stageAlerte, 3);
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	//methode permettant d'afficher la vue modification
	public void afficherNotifModification() {
		
		if(stageAlerte.isShowing()) {
			stageAlerte.close();
		}
		
		AnchorPane pane=new AnchorPane();
		
		FXMLLoader loader=new FXMLLoader();
		
		loader.setLocation(getClass().getResource("vue/VueModification.fxml"));
		
		try {
			
			stageAlerte=new Stage();
			
			pane=(AnchorPane) loader.load();
			
			Scene scene=new Scene(pane);
			scene.setFill(Color.TRANSPARENT);
			
			stageAlerte.setScene(scene);
			stageAlerte.initStyle(StageStyle.TRANSPARENT);
			stageAlerte.show();
			
			//lancement du thread d'arret
			associationService.closeAlert(stageAlerte, 3);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//methode permettant d'afficher le message d'alert annulation
	public void afficherAlerteAnnulation() {
		
		if(stageAlerte.isShowing()) {
			stageAlerte.close();
		}
		
		AnchorPane pane=new AnchorPane();
		
		FXMLLoader loader=new FXMLLoader();
		
		loader.setLocation(getClass().getResource("vue/VueAlerteAnnulation.fxml"));
		
		try {
			
			stageAlerte=new Stage();
			
			pane=(AnchorPane) loader.load();
			
			Scene scene=new Scene(pane);
			scene.setFill(Color.TRANSPARENT);
			
			stageAlerte.setScene(scene);
			stageAlerte.initStyle(StageStyle.TRANSPARENT);
			stageAlerte.show();
			
			//lancement du thread d'arret
			associationService.closeAlert(stageAlerte, 3);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//methode permettant d'afficher le message d'alert de suppression
	public void afficherAlertSuppression() {
		
		if(stageAlerte.isShowing()) {
			stageAlerte.close();
		}
		
		AnchorPane pane=new AnchorPane();
		
		FXMLLoader loader=new FXMLLoader();
		
		loader.setLocation(getClass().getResource("vue/VueAlerteSuppression.fxml"));
		
		try {
			
			stageAlerte=new Stage();
			
			pane=(AnchorPane) loader.load();
			
			Scene scene=new Scene(pane);
			scene.setFill(Color.TRANSPARENT);
			
			stageAlerte.setScene(scene);
			stageAlerte.initStyle(StageStyle.TRANSPARENT);
			stageAlerte.show();
			
			//lancement du thread d'arret
			associationService.closeAlert(stageAlerte, 3);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//methode permettant d'afficher la vue de l'alerte erreur
	public void afficherAlerteErreur() {
		
		AnchorPane pane=new AnchorPane();
		
		FXMLLoader loader=new FXMLLoader();
		
		loader.setLocation(getClass().getResource("vue/VueErreurNotif.fxml"));
		
		try {
			
			if(stageAlerte.isShowing()) {
				
				stageAlerte.close();
				
			}
			
			pane=(AnchorPane)loader.load();
			
			Scene scene=new Scene(pane);
			scene.setFill(Color.TRANSPARENT);
			
			stageAlerte=new Stage();
			stageAlerte.setScene(scene);
			stageAlerte.initStyle(StageStyle.TRANSPARENT);
			stageAlerte.show();
			
			VueErreurNotifControlleur controlleur=loader.getController();
			controlleur.setGestion(this);
			
			//lancement du thread d'arret
			associationService.closeAlert(stageAlerte, 3);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//methode permettant de retourner le stage
	public Stage getStage() {
		return primaryStage;
	}
	
	//methode permettant de retourner la liste des adherents
	public ObservableList<Adherent> getListAdherent(){
		return listeAdherent;
	}
	
	//methode permettant de retourner la liste des membres du bureau
	public ObservableList<Bureau> getListBureau(){
		return listeBureau;
	}
	
	//methode permettant de retourner la liste des fonctions
	public ObservableList<String> getListFocntion(){
		return listFonction;
	}
	
	//methode permettant de retourner les infos du membre connecté
	public ObservableList<Bureau> getInfoMembConnecte(){
		return infoMmbrConnecte;
	}
	
	//methode permettant de retourner la liste des cotisations
	public ObservableList<Cotisation> getListCotisation(){
		return listeCotisation;
	}
	
	//methode permettant de retourner la liste des evenements
	public ObservableList<Evenement> getListEvenement(){
		return listeEvenement;
	}
	
	//methode permettant de retourner la liste des type de solde pour les evenements
	public ObservableList<String> getListTypSolde(){
		return listeTypSoldeEvenenemt;
	}
	
	//methode permettant de retourner la liste des cotisations annuelles
	public ObservableList<CotisationAnnuelle> getListCotisationAnnuelle(){
		return listCotisationAnnuelle;
	}
	
	//methode permettant de retourner l'id de l'adherent
	public Long getIdAdherent() {
		return idAdherent;
	}
	
	//methode permettant de modifier l'id de l'adherent
	public void setIdAdherent(Long id) {
		this.idAdherent=id;
	}
	
	//methode permettant de retourner la liste des cotisations annuelles par mois
	public ObservableList<Float> getListTtCotisationAnnuelleParMois(){
		return listTtCotisationAnnuelleParMois;
	}
	
	//methode permettant de retourner le stage de attente user
	public Stage getStageAttenteUser() {
		return stageAttenteUser;
	}
	
	
	//methode permettant de retourner la valeur du traitement en cours
	public Boolean getTraitementEnCours() {
		return traitementEnCours;
	}
	
	//methode permettant de modifier la valeur du traitement en cours
	public void setTraitementEnCours(Boolean valeur) {
		this.traitementEnCours=valeur;
	}
	
	//methode permettant de retourner la liste des types mouvements
	public ObservableList<String> getListTypMouvement(){
		return listeTypMouvement;
	}
	
	//methode permettant de retourner le stage valider
	public Stage getStageValider() {
		return stageAlerte;
	}
	
	//methode permettant de modifier le message d'erreur
	public void setMessageErreur(String message) {
		
		this.messageErreur=message;
		
	}
	
	//methode permettant de retourner le message d'erreur
	public String getMessage() {
		return messageErreur;
	}
	
	//methode permettant de retourner la liste vie sociale
	public ObservableList<VieSociale> getListVieSociale(){
		return listeVieSociale;
	}
	
	//methode permettant de retourner la liste des types formulaires
	public ObservableList<String> getListTypeAssurance(){
		return listeTypAssuranc;
	}
	
	//Methode permettant de retourner la liste des divers
	public ObservableList<Divers> getListDivers(){
		return listeDivers;
	}
	
	//Methode permettant de retourner la liste des types de formulaire
	public ObservableList<String> getListTypFormulaire(){
		return listTypFormulaire;
	}
	
	
	//setters de gestion association
	public void setGestionAssociation(GestionAssociation association) {
		
		this.gestionAssociation=association;
		
	}
	
	//getters de gestion association
	public GestionAssociation getGestionAssociation() {
		
		return gestionAssociation;
		
	}
	
	

	public static void main(String[] args) {
		launch(args);
	}
}
