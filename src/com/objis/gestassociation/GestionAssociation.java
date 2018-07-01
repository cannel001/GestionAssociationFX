package com.objis.gestassociation;

import java.util.Calendar;

import com.objis.gestassociation.affichage.VueAttenteUser;
import com.objis.gestassociation.affichage.VueCentre;
import com.objis.gestassociation.affichage.VueChangerMdp;
import com.objis.gestassociation.affichage.VueConnexion;
import com.objis.gestassociation.affichage.VueMiniSlideBar;
import com.objis.gestassociation.affichage.VueNotifAnnulation;
import com.objis.gestassociation.affichage.VueNotifErreur;
import com.objis.gestassociation.affichage.VueNotifModification;
import com.objis.gestassociation.affichage.VueNotifSuppression;
import com.objis.gestassociation.affichage.VueNotifValider;
import com.objis.gestassociation.affichage.VuePrincipal;
import com.objis.gestassociation.affichage.VueSlideBar;
import com.objis.gestassociation.domaine.Adherent;
import com.objis.gestassociation.domaine.Bureau;
import com.objis.gestassociation.domaine.Cotisation;
import com.objis.gestassociation.domaine.CotisationAnnuelle;
import com.objis.gestassociation.domaine.Divers;
import com.objis.gestassociation.domaine.Evenement;
import com.objis.gestassociation.domaine.Mouvement;
import com.objis.gestassociation.domaine.Rencontre;
import com.objis.gestassociation.domaine.VieSociale;
import com.objis.gestassociation.service.impl.AdherentService;
import com.objis.gestassociation.service.impl.AssociationService;
import com.objis.gestassociation.service.impl.BureauService;
import com.objis.gestassociation.service.impl.CotisationAnnuelleService;
import com.objis.gestassociation.service.impl.CotisationService;
import com.objis.gestassociation.service.impl.DiversService;
import com.objis.gestassociation.service.impl.EvenementService;
import com.objis.gestassociation.service.impl.MouvementService;
import com.objis.gestassociation.service.impl.VieSocialeService;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GestionAssociation extends Application {
	
	private Long idAdherent;
	
	private GestionAssociation gestionAssociation;
	
	private Stage primaryStage;
	private Stage stageAttenteUser;
	private Stage stageAlerte=new Stage();
	
	private String messageErreur;
	
	private AnchorPane pane;
	
	private BorderPane menuPrincipal;
	
	private AnchorPane vueSlider;
	
	private Boolean traitementEnCours=false;
	
	private Adherent adherent;
	
	private Bureau bureau;
	
	//les services
//	private AdherentService adherentService=new AdherentService();
//	private BureauService bureauService=new BureauService();
//	private CotisationService cotisationService=new CotisationService();
//	private EvenementService evenementService=new EvenementService();
//	private CotisationAnnuelleService cotisationAnnuelleService=new CotisationAnnuelleService();
	private AssociationService associationService=new AssociationService();
//	private VieSocialeService vieSocialeService=new VieSocialeService();
//	private DiversService diversService=new DiversService();
//	private MouvementService mouvementService=new MouvementService();
	
	//les listes
	private ObservableList<Adherent> listeAdherent=FXCollections.observableArrayList();
	private ObservableList<Adherent> listeAdherentHorsBureau=FXCollections.observableArrayList();
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
	private ObservableList<Mouvement> listeMouvement=FXCollections.observableArrayList();
	private ObservableList<Rencontre> listeRencontre=FXCollections.observableArrayList();
	private ObservableList<Adherent> listeMbBureauPresentRencontre=FXCollections.observableArrayList();
	private ObservableList<Adherent> listeAdherentPresentRencontre=FXCollections.observableArrayList();
	private ObservableList<String> listePresencePresident=FXCollections.observableArrayList();
	private ObservableList<Adherent> listAdherentDansBureau=FXCollections.observableArrayList();
	
	private String date;
	
	
	
	//constructeur
	public GestionAssociation() {
		
		pane=new AnchorPane();
		
		menuPrincipal=new BorderPane();
		
		
		date=""+Calendar.getInstance().getWeekYear();
		
//		//recuperation de tous les enregistrements de la base de données
//		listeAdherent.addAll(adherentService.readAll());
//		listeBureau.addAll(bureauService.readAll());
//		listeCotisation.addAll(cotisationService.readAll());
//		listeEvenement.addAll(evenementService.readAll());
//		listCotisationAnnuelle.addAll(cotisationAnnuelleService.getAllListCotisation(date));
//		listTtCotisationAnnuelleParMois.addAll(cotisationAnnuelleService.getTotalCotisationAnnuelleParAnnee(date));
//		listeVieSociale.addAll(vieSocialeService.readAll());
//		listeDivers.addAll(diversService.readAll());
//		listeMouvement.addAll(mouvementService.readAll());
//		listeAdherentHorsBureau.addAll(adherentService.readAllAdherentHorsBureau());
//		listAdherentDansBureau.addAll(adherentService.readAllADherentDansBureau());
//
//		
//		//ajout des informations à combo box
//		listeTypSoldeEvenenemt.add("OK");
//		listeTypSoldeEvenenemt.add("EN COURS");
//		listeTypSoldeEvenenemt.add("AVORTE");
//		listeTypSoldeEvenenemt.add("AVORTE");
//		listeTypSoldeEvenenemt.add("KO");
//		
//		//ajout des informations à combo fonction
//		listFonction.add("PRESIDENT");
//		listFonction.add("TRESORIER(E)");
//		listFonction.add("COMMISSAIRE AU COMPTE");
//		listFonction.add("SECRETAIRE");
//		
//		//ajout des informations au combo presence president
//		listePresencePresident.add("PRESENT");
//		listePresencePresident.add("ABSENT");
//		
//		//ajout des informations au combo type formulaire
//		listeTypAssuranc.add("ASSURANCE");
//		listeTypAssuranc.add("MALADIE");
//		listeTypAssuranc.add("DECES");
//		
//		//peuplement de la liste
//		listTypFormulaire.add("DEMANDE AIDE");
//		listTypFormulaire.add("PARTENARIAT");
//		listTypFormulaire.add("PARRAINAGE");
//		
//		//peuplement de la liste type mouvement
//		listeTypMouvement.add("ENTREE");
//		listeTypMouvement.add("SORTIE");
		
	}
	

	@Override
	public void start(Stage primaryStage) {
		
		this.primaryStage=primaryStage;
		
		primaryStage.setTitle("GESTION ASSOCIATION");
		
		afficherFenetreConnexion();
		
		primaryStage.show();
		
	}
	
	
	public void afficherFenetreConnexion() {
		
		(new VueConnexion(this)).afficherVue();
		
	}
	
	//methode permettant de changer de fenetre
	public void changerFenetre(String url) {
		
		(new VuePrincipal(url,this)).afficherVue();
		
	}
	
	//methode permettant d'afficher la vue changer mot de passe
	public void afficherVueChangerMdp() {
		
		(new VueChangerMdp(this)).afficherVue();
		
	}
	
	//methode permettant de changer les vues au centre
	public void chargerVueCentre(String url) {
			
		(new VueCentre(url,this)).afficherVue();	
			
	}
	
	//methode pour charger la vue slider
	public void chargerVueSlide() {
		
		(new VueSlideBar(this)).afficherVue();
		
	}
	
	
	//methode permettant d'afficher la petite slide Bar
	public void afficherMiniSlideBar() {
		
		(new VueMiniSlideBar(this)).afficherVue();
		
	}
	
	
	//methode permettant de faire patienter l'user
	public void afficherVueAttenteUser() {
		
		(new VueAttenteUser(this)).afficherVue();
		
	}
	
	//methode permettant d'afficher la vue valider
	public void afficherVueValider() {
		
		(new VueNotifValider(stageAlerte, associationService)).afficherVue();
		
	}
	
	
	//methode permettant d'afficher la vue modification
	public void afficherNotifModification() {
		
		(new VueNotifModification(stageAlerte, associationService)).afficherVue();
		
	}
	
	//methode permettant d'afficher le message d'alert annulation
	public void afficherAlerteAnnulation() {
		
		(new VueNotifAnnulation(stageAlerte, associationService)).afficherVue();
		
	}
	
	//methode permettant d'afficher le message d'alert de suppression
	public void afficherAlertSuppression() {
		
		(new VueNotifSuppression(stageAlerte, associationService)).afficherVue();
		
	}
	
	//methode permettant d'afficher la vue de l'alerte erreur
	public void afficherAlerteErreur() {
		
		(new VueNotifErreur(stageAlerte, this, associationService)).afficherVue();
		
	}
	
	//methode permettant de retourner le stage
	public Stage getPrimaryStage() {
		return primaryStage;
	}
	
	//setter de primary stage
	public void setPrimaryStage(Stage stage) {
		this.primaryStage=stage;
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
	
	//Methode permettant de retourner la liste presence president
	public ObservableList<String> getListePresencePresident(){
		return listePresencePresident;
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
	
	//Methode permettant de retourner la liste des membres dans le bureau
	public ObservableList<Adherent> getListADherentDansBureau(){
		return listAdherentDansBureau;
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
	
	//methode permettant de retounrer la liste des rencontre
	public ObservableList<Rencontre> getListRencontre(){
		return listeRencontre;
	}
	
	//methode permettant de retourner la liste des membres du bureau presents aux rencontres
	public ObservableList<Adherent> getListMbBureauPresentRencontre(){
		return listeMbBureauPresentRencontre;
	}
	
	//methode permettant de retourner la liste des adherents presents aux rencontres
	public ObservableList<Adherent> getListAdherentPresentRencontre(){
		return listeAdherentPresentRencontre;
	}
	
	//Methode permettant de retourner la liste des types de formulaire
	public ObservableList<String> getListTypFormulaire(){
		return listTypFormulaire;
	}
	
	//Methode permettant de retourner la liste des adherents hors bureau
	public ObservableList<Adherent> getlisteAdherentHorsBureau(){
		return listeAdherentHorsBureau;
	}
	
	
	//setters de gestion association
	public void setGestionAssociation(GestionAssociation association) {
		
		this.gestionAssociation=association;
		
	}
	
	//getters de gestion association
	public GestionAssociation getGestionAssociation() {
		
		return gestionAssociation;
		
	}
	
	//methode permettant de retourner la liste des mouvements
	public ObservableList<Mouvement> getListMouvement(){
		
		return listeMouvement;
		
	}
	
	//setter du menu principal
	public void setMenuPrincipal(BorderPane pane) {
		this.menuPrincipal=pane;
	}
	
	//getter du menu principal
	public BorderPane getMenuPrincipal() {
		return menuPrincipal;
	}
	
	
	

	public static void main(String[] args) {
		launch(args);
	}
}
