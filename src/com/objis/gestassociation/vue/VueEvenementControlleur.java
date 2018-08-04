package com.objis.gestassociation.vue;

import java.time.LocalDate;
import java.util.Optional;

import com.objis.gestassociation.GestionAssociation;
import com.objis.gestassociation.domaine.Evenement;
import com.objis.gestassociation.service.impl.AssociationService;
import com.objis.gestassociation.service.impl.EvenementService;
import com.objis.gestassociation.servicevue.impl.contrainte.ContrainteEvenement;
import com.objis.gestassociation.servicevue.impl.enregistrement.EnregistrementEvenement;
import com.objis.gestassociation.servicevue.impl.modification.ModificationEvenement;
import com.objis.gestassociation.servicevue.impl.reset.ResetEvenement;
import com.objis.gestassociation.servicevue.impl.supprimer.SuppressionEvenement;

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
    private AssociationService associationService = new AssociationService();
    private EvenementService service = new EvenementService();
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

        columDate.setCellValueFactory(e -> e.getValue().dateProperty());
        columObjet.setCellValueFactory(e -> e.getValue().objetProperty());

        tableEvenement.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> chargerChamps(newValue));

    }

    //methode permettant de charger les champs de saises
    public void chargerChamps(Evenement evenement) {

        if (evenement != null) {

            dateEvent.setValue(evenement.getDate());
            txfLieu.setText(evenement.getLieu());
            txfAchat.setText(evenement.getAchat());
            txfDepenstt.setText("" + evenement.getDepenseTt());
            txfObjet.setText(evenement.getObjet());

            cbxSolde.getSelectionModel().select(evenement.getSolde());

            btnAjouter.setDisable(true);
            btnModifier.setDisable(false);
            btnReset.setDisable(false);
            btnSupprimer.setDisable(false);

        }

    }

    //methode permettant d'initialiser les champs de saises
    public void initChamps() {

        (new ResetEvenement(association, associationService, service, alert, tableEvenement, dateEvent, txfLieu, txfObjet, txfAchat, txfDepenstt, btnAjouter, btnModifier, btnSupprimer, btnReset, cbxSolde, lbMontEvent)).reset();

    }

    //methode permettant de gerer les contrainte
    public Boolean gestContrainte() {

        return (new ContrainteEvenement(association, associationService, service, alert, tableEvenement, dateEvent, txfLieu, txfObjet, txfAchat, txfDepenstt, cbxSolde, lbMontEvent)).contrainte();

    }

    //methode permettant de faire un enregistrement
    @FXML
    public void clickerSurBtnAjouter() {

        if (gestContrainte()) {

            (new EnregistrementEvenement(association, associationService, service, alert, tableEvenement, dateEvent, txfLieu, txfObjet, txfAchat, txfDepenstt, cbxSolde, lbMontEvent)).enregistrement();
            initChamps();

        }

    }

    //methode permettant de modifier un enregistrement
    @FXML
    public void clickerSurBtnModifier() {

        if (gestContrainte()) {

            (new ModificationEvenement(association, associationService, service, alert, tableEvenement, dateEvent, txfLieu, txfObjet, txfAchat, txfDepenstt, cbxSolde, lbMontEvent)).modification();
            initChamps();

        }

    }

    //methode permettant de supprimer un enregistrement
    @FXML
    public void clickerSurBtnSupprimer() {

        (new SuppressionEvenement(association, associationService, service, alert, tableEvenement, dateEvent, txfLieu, txfObjet, txfAchat, txfDepenstt, btnAjouter, btnModifier, btnSupprimer, btnReset, cbxSolde, lbMontEvent)).supprimer();
        initChamps();

    }

    //methode permettant de faire un reset
    @FXML
    public void clickerSurBtnReset() {

        initChamps();

    }

    //methode permettant de modifier la classe principale
    public void setGestionAssociation(GestionAssociation association) {

        this.association = association;
        tableEvenement.setItems(association.getListeEvenement());
        cbxSolde.setItems(association.getListeTypSoldeEvenenemt());

    }

}
