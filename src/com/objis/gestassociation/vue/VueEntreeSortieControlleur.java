package com.objis.gestassociation.vue;

import java.time.LocalDate;

import com.objis.gestassociation.GestionAssociation;
import com.objis.gestassociation.domaine.Mouvement;
import com.objis.gestassociation.servicevue.impl.enregistrement.EnregistrementMouvement;
import com.objis.gestassociation.servicevue.impl.modification.ModificationMouvement;
import com.objis.gestassociation.servicevue.impl.reset.ResetMouvement;
import com.objis.gestassociation.servicevue.impl.supprimer.SupprimerMouvement;
import com.objis.gestassociation.vue.chargementchamps.ChargementMouvement;
import com.objis.gestassociation.vue.contrainte.ContrainteMouvement;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.DoubleStringConverter;

public class VueEntreeSortieControlleur {

    //les proprietes
    private GestionAssociation association;
    private Mouvement mouvement;

    @FXML
    private DatePicker date;
    @FXML
    private TextField txfNature;
    @FXML
    private TextField txfQte;

    @FXML
    private Label lbTtEntree;
    @FXML
    private Label lbTtSortie;

    @FXML
    private TableView<Mouvement> tableMouvement;

    @FXML
    private TableColumn<Mouvement, String> columNature;
    @FXML
    private TableColumn<Mouvement, LocalDate> columDate;
    @FXML
    private TableColumn<Mouvement, Double> columQte;

    @FXML
    private Button btnValider;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnReset;

    @FXML
    private ComboBox<String> cbxTypMouvement;

    //methode d'initialisation
    @FXML
    public void initialize() {

        initChamps();

        txfQte.setTextFormatter(new TextFormatter<>(new DoubleStringConverter()));

        columDate.setCellValueFactory(e -> e.getValue().dateProperty());
        columNature.setCellValueFactory(e -> e.getValue().natureProperty());
        columQte.setCellValueFactory(e -> e.getValue().quantiteProperty());

        tableMouvement.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> chargerChamps(newValue));

    }

    //methode permettant de charger les champs de saises
    public void chargerChamps(Mouvement mouvement) {

        (new ChargementMouvement(txfNature, txfQte, date, cbxTypMouvement, mouvement, btnValider, btnModifier, btnSupprimer, btnReset)).chargement();

    }

    //initialisation des champs
    @FXML
    public void initChamps() {

        (new ResetMouvement(date, txfNature, txfQte, cbxTypMouvement, association, tableMouvement, btnValider, btnModifier, btnSupprimer, btnReset, lbTtEntree, lbTtSortie)).reset();

    }

    //methode permettant de faire un enregistrement
    @FXML
    public void clickerSurBtnEregistrement() {

        if (gestContrainte()) {

            (new EnregistrementMouvement(date, txfNature, txfQte, cbxTypMouvement, association, tableMouvement, btnValider, btnModifier, btnSupprimer, btnReset, lbTtEntree, lbTtSortie)).enregistrement();

            initChamps();

        }

    }

    //methode permettant de faire une modification
    @FXML
    public void clickerSurBtnModification() {

        if (gestContrainte()) {

            (new ModificationMouvement(date, txfNature, txfQte, cbxTypMouvement, association, tableMouvement, btnValider, btnModifier, btnSupprimer, btnReset, lbTtEntree, lbTtSortie)).modification();

            initChamps();

        }

    }

    //methode permettant de faire une suppression
    @FXML
    public void clickerSurBtnSupprimer() {

        (new SupprimerMouvement(date, txfNature, txfQte, cbxTypMouvement, association, tableMouvement, btnValider, btnModifier, btnSupprimer, btnReset, lbTtEntree, lbTtSortie)).supprimer();

        initChamps();

    }

    //methode permettant de faire un reset
    @FXML
    public void clickerSurBtnReset() {

        (new ResetMouvement(date, txfNature, txfQte, cbxTypMouvement, association, tableMouvement, btnValider, btnModifier, btnSupprimer, btnReset, lbTtEntree, lbTtSortie)).reset();

    }

    //methode permettant de gerer les contraintes de selection
    public Boolean gestContrainte() {

        return (new ContrainteMouvement(txfNature, txfQte, date, cbxTypMouvement, association).contrainte());

    }

    //methode permettant de modifier la classe gestion association
    public void setGestionAssociation(GestionAssociation gestionAssociation) {

        this.association = gestionAssociation;
        cbxTypMouvement.setItems(association.getListeTypMouvement());

        tableMouvement.setItems(association.getListeMouvement());

    }

}
