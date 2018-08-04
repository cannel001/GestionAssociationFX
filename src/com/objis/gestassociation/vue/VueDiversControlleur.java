package com.objis.gestassociation.vue;

import java.time.LocalDate;
import java.util.Optional;

import com.objis.gestassociation.GestionAssociation;
import com.objis.gestassociation.domaine.Divers;
import com.objis.gestassociation.service.impl.AssociationService;
import com.objis.gestassociation.service.impl.DiversService;
import com.objis.gestassociation.servicevue.impl.contrainte.ContrainteDivers;
import com.objis.gestassociation.servicevue.impl.enregistrement.EnregistrementDivers;
import com.objis.gestassociation.servicevue.impl.modification.ModificationDivers;
import com.objis.gestassociation.servicevue.impl.reset.ResetDivers;
import com.objis.gestassociation.servicevue.impl.supprimer.SuppressionDivers;

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
    private DiversService diversService = new DiversService();
    private AssociationService associationService = new AssociationService();

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
    private TableColumn<Divers, LocalDate> columDate;
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

        columDate.setCellValueFactory(e -> e.getValue().dateProperty());
        columLieu.setCellValueFactory(e -> e.getValue().lieuProperty());
        columTypeFormulaire.setCellValueFactory(e -> e.getValue().typeFormulaireProperty());

        //ecouter la selection d'une ligne du tableau et remplir la methode chargeChamps
        tableDivers.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> remplirChamps(newValue));

    }

    //methode permettant de remplir les champs de saisies
    public void remplirChamps(Divers divers) {

        if (divers != null) {

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

        (new ResetDivers(association, diversService, associationService, date, txfLieu, txfMotif, txfCorpsFormulaire, cbxTypFormulaire, lbTt, btnAjouter, btnModifier, btnSupprimer, btnReset, tableDivers, divers, alert)).reset();

    }

    //Methode permettant de faire un enregistrement
    @FXML
    public void clickerSurBtnEnregistrement() {

        if (gestContrainte()) {

            (new EnregistrementDivers(association, diversService, associationService, date, txfLieu, txfMotif, txfCorpsFormulaire, cbxTypFormulaire, lbTt, tableDivers, divers)).enregistrement();
            initChamps();
        }

    }

    //methode permettant de faire une modification
    @FXML
    public void clickerSurBtnModifier() {

        if (gestContrainte()) {

            (new ModificationDivers(association, diversService, associationService, date, txfLieu, txfMotif, txfCorpsFormulaire, cbxTypFormulaire, lbTt, tableDivers, divers)).modification();

            initChamps();

        }

    }

    //methode permettant de faire une suppression
    @FXML
    public void clickerSurBtnSupprimer() {

        (new SuppressionDivers(association, diversService, associationService, lbTt, tableDivers, divers, alert)).supprimer();
        initChamps();

    }

    //Methode permettant de faire un reset
    @FXML
    public void clickerSurBtnreset() {

        initChamps();

    }

    //Methode permettant de gerer les contraintes
    public Boolean gestContrainte() {

        return (new ContrainteDivers(association, diversService, associationService, date, txfLieu, txfMotif, txfCorpsFormulaire, cbxTypFormulaire, lbTt, tableDivers, divers)).contrainte();

    }

    //methode permettant de modifier la classe principale
    public void setGestionAssociation(GestionAssociation association) {
        this.association = association;
        tableDivers.setItems(association.getListeDivers());
        cbxTypFormulaire.setItems(association.getListTypFormulaire());
    }

}
