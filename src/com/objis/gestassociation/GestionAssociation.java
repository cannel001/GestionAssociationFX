package com.objis.gestassociation;

import com.objis.gestassociation.affichage.*;
import com.objis.gestassociation.domaine.*;
import com.objis.gestassociation.service.impl.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;

@Setter
@Getter
@AllArgsConstructor
public class GestionAssociation extends Application {

    private Long idAdherent;

    private GestionAssociation gestionAssociation;

    private Stage primaryStage;
    private Stage stageAttenteUser;
    private Stage stageAlerte = new Stage();

    private String messageErreur;

    private AnchorPane pane;

    private BorderPane menuPrincipal;

    private AnchorPane vueSlider;

    private Boolean traitementEnCours = false;

    private Adherent adherent;

    private Bureau bureau;

    //les services
    private AdherentService adherentService;
    private BureauService bureauService;
    private CotisationService cotisationService;
    private EvenementService evenementService;
    private CotisationAnnuelleService cotisationAnnuelleService;
    private AssociationService associationService;
    private VieSocialeService vieSocialeService;
    private DiversService diversService;
    private MouvementService mouvementService;

    //les listes
    private ObservableList<Adherent> listeAdherent;
    private ObservableList<Adherent> listeAdherentHorsBureau;
    private ObservableList<Bureau> listeBureau;
    private ObservableList<String> listFonction;
    private ObservableList<Bureau> infoMmbrConnecte;
    private ObservableList<Cotisation> listeCotisation;
    private ObservableList<String> listeTypSoldeEvenenemt;
    private ObservableList<Evenement> listeEvenement;
    private ObservableList<CotisationAnnuelle> listCotisationAnnuelle;
    private ObservableList<Float> listTtCotisationAnnuelleParMois;
    private ObservableList<VieSociale> listeVieSociale;
    private ObservableList<String> listeTypAssuranc;
    private ObservableList<Divers> listeDivers;
    private ObservableList<String> listTypFormulaire;
    private ObservableList<String> listeTypMouvement;
    private ObservableList<Mouvement> listeMouvement;
    private ObservableList<Rencontre> listeRencontre;
    private ObservableList<Adherent> listeMbBureauPresentRencontre;
    private ObservableList<Adherent> listeAdherentPresentRencontre;
    private ObservableList<String> listePresencePresident;
    private ObservableList<Adherent> listAdherentDansBureau;

    private String date;

    //constructeur
    public GestionAssociation() {

        pane = new AnchorPane();

        menuPrincipal = new BorderPane();

        date = "" + Calendar.getInstance().getWeekYear();

        //instanciation des services
        //les services
        adherentService = new AdherentService();
        bureauService = new BureauService();
        cotisationService = new CotisationService();
        evenementService = new EvenementService();
        cotisationAnnuelleService = new CotisationAnnuelleService();
        associationService = new AssociationService();
        vieSocialeService = new VieSocialeService();
        diversService = new DiversService();
        mouvementService = new MouvementService();

        //instanciation des listes
        //les listes
        listeAdherent = FXCollections.observableArrayList();
        listeAdherentHorsBureau = FXCollections.observableArrayList();
        listeBureau = FXCollections.observableArrayList();
        listFonction = FXCollections.observableArrayList();
        infoMmbrConnecte = FXCollections.observableArrayList();
        listeCotisation = FXCollections.observableArrayList();
        listeTypSoldeEvenenemt = FXCollections.observableArrayList();
        listeEvenement = FXCollections.observableArrayList();
        listCotisationAnnuelle = FXCollections.observableArrayList();
        listTtCotisationAnnuelleParMois = FXCollections.observableArrayList();
        listeVieSociale = FXCollections.observableArrayList();
        listeTypAssuranc = FXCollections.observableArrayList();
        listeDivers = FXCollections.observableArrayList();
        listTypFormulaire = FXCollections.observableArrayList();
        listeTypMouvement = FXCollections.observableArrayList();
        listeMouvement = FXCollections.observableArrayList();
        listeRencontre = FXCollections.observableArrayList();
        listeMbBureauPresentRencontre = FXCollections.observableArrayList();
        listeAdherentPresentRencontre = FXCollections.observableArrayList();
        listePresencePresident = FXCollections.observableArrayList();
        listAdherentDansBureau = FXCollections.observableArrayList();

        //recuperation de tous les enregistrements de la base de donn�es
        listeAdherent.addAll(adherentService.readAll());
        listeBureau.addAll(bureauService.readAll());
        listeCotisation.addAll(cotisationService.readAll());
        listeEvenement.addAll(evenementService.readAll());
        listCotisationAnnuelle.addAll(cotisationAnnuelleService.getAllListCotisation(date));
        listTtCotisationAnnuelleParMois.addAll(cotisationAnnuelleService.getTotalCotisationAnnuelleParAnnee(date));
        listeVieSociale.addAll(vieSocialeService.readAll());
        listeDivers.addAll(diversService.readAll());
        listeMouvement.addAll(mouvementService.readAll());
        listeAdherentHorsBureau.addAll(adherentService.readAllAdherentHorsBureau());
        listAdherentDansBureau.addAll(adherentService.readAllADherentDansBureau());

        //ajout des informations � combo box
        listeTypSoldeEvenenemt.add("OK");
        listeTypSoldeEvenenemt.add("EN COURS");
        listeTypSoldeEvenenemt.add("AVORTE");
        listeTypSoldeEvenenemt.add("AVORTE");
        listeTypSoldeEvenenemt.add("KO");

        //ajout des informations � combo fonction
        listFonction.add("PRESIDENT");
        listFonction.add("TRESORIER(E)");
        listFonction.add("COMMISSAIRE AU COMPTE");
        listFonction.add("SECRETAIRE");

        //ajout des informations au combo presence president
        listePresencePresident.add("PRESENT");
        listePresencePresident.add("ABSENT");

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

        this.primaryStage = primaryStage;

        primaryStage.setTitle("GESTION ASSOCIATION");

        afficherFenetreConnexion();

        primaryStage.show();

    }

    public void afficherFenetreConnexion() {

        (new VueConnexion(this)).afficherVue();

    }

    //methode permettant de changer de fenetre
    public void changerFenetre(String url) {

        (new VuePrincipal(url, this)).afficherVue();

    }

    //methode permettant d'afficher la vue changer mot de passe
    public void afficherVueChangerMdp() {

        (new VueChangerMdp(this)).afficherVue();

    }

    //methode permettant de changer les vues au centre
    public void chargerVueCentre(String url) {

        (new VueCentre(url, this)).afficherVue();

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

    public static void main(String[] args) {
        launch(args);
    }
}
