package com.objis.gestassociation.vue;

import java.util.Optional;

import com.objis.gestassociation.GestionAssociation;
import com.objis.gestassociation.domaine.Adherent;
import com.objis.gestassociation.domaine.VieSociale;
import com.objis.gestassociation.service.impl.AdherentService;
import com.objis.gestassociation.service.impl.AssociationService;
import com.objis.gestassociation.service.impl.VieSocialeService;

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
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.DoubleStringConverter;

public class VueVieSocialeControlleur {

    //les proprietes
    private GestionAssociation association;

    private AdherentService adherentService = new AdherentService();

    private VieSocialeService vieSocialService = new VieSocialeService();

    private AssociationService associationService = new AssociationService();

    private VieSociale vieSociale;

    private Alert alert;

    @FXML
    private Button btnAJouter;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnReset;

    @FXML
    private TextField txfMontant;

    @FXML
    private ComboBox<String> cbxTypeAssurance;
    @FXML
    private ComboBox<Adherent> cbxAdherent;

    @FXML
    private DatePicker dateDebut;
    @FXML
    private DatePicker dateFin;

    @FXML
    private Label lbInfoTt;

    @FXML
    private TableView<VieSociale> tableVieSociale;

    @FXML
    private TableColumn<VieSociale, String> columTypeAssurance;
    @FXML
    private TableColumn<VieSociale, Double> columMontant;

    //methode permettant d'initialiser la vue
    @FXML
    public void initialize() {

        //initialisation des champs de saisies
        initChamps();

        //formater les champs de saisies
        txfMontant.setTextFormatter(new TextFormatter<>(new DoubleStringConverter()));

        //affecter des valeurs aux colonnes du tableau
        columMontant.setCellValueFactory(e -> e.getValue().montantProperty());
        columTypeAssurance.setCellValueFactory(e -> e.getValue().typeAssuranceProperty());

        //ecouter la selection d'une ligne du tableau
        tableVieSociale.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> chargerChamps(newValue));

    }

    //methode permettant de faire un enregistrement
    @FXML
    public void clickerSurBtnAJouter() {

        if (gestContraintes().equals(true)) {

            vieSociale = new VieSociale(associationService.generationId(), dateDebut.getValue(), dateFin.getValue(), Double.parseDouble(txfMontant.getText()), cbxAdherent.getSelectionModel().getSelectedItem().getId(), cbxTypeAssurance.getSelectionModel().getSelectedItem(), "ACTIF");

            if (vieSocialService.create(vieSociale)) {

                association.getListeVieSociale().add(vieSociale);

                association.afficherVueValider();

                initChamps();

            } else {
                association.setMessageErreur("Erreur survenue pendant l'enregistrement");
                association.afficherAlerteErreur();
            }

        }

    }

    //methode permettant de modifier un enregistrement
    @FXML
    public void clickerSurBtnModifier() {

        if (gestContraintes()) {

            vieSociale = new VieSociale(tableVieSociale.getSelectionModel().getSelectedItem().getIdVieSociale(), dateDebut.getValue(), dateFin.getValue(), Double.parseDouble(txfMontant.getText()), cbxAdherent.getSelectionModel().getSelectedItem().getId(), cbxTypeAssurance.getSelectionModel().getSelectedItem(), "ACTIF");

            if (vieSocialService.update(vieSociale)) {
                association.getListeVieSociale().set(tableVieSociale.getSelectionModel().getSelectedIndex(), vieSociale);
                association.afficherNotifModification();
                initChamps();
            } else {
                association.setMessageErreur("Erreur survenue pendant la modification");
                association.afficherAlerteErreur();
            }

        }

    }

    //methode permettant de supprimer un enregistrement
    @FXML
    public void clickerSurBtnSupprimer() {

        alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Attention");
        alert.setContentText("Cette action entrainera la suppression de cet enregistrement");
        Optional<ButtonType> optional = alert.showAndWait();
        if (optional.get().equals(ButtonType.OK)) {

            if (vieSocialService.delete(tableVieSociale.getSelectionModel().getSelectedItem().getIdVieSociale())) {

                association.getListeVieSociale().remove(tableVieSociale.getSelectionModel().getSelectedIndex());
                association.afficherAlertSuppression();
                lbInfoTt.setText("" + vieSocialService.getNbEnregistrement());

            } else {
                association.setMessageErreur("Erreur survenue pendant la suppression");
                association.afficherAlerteErreur();
            }

            if (tableVieSociale.getSelectionModel().getFocusedIndex() < 0) {

                initChamps();

                tableVieSociale.getSelectionModel().clearSelection();

            }

        } else {

            association.afficherAlerteAnnulation();

        }

    }

    //methode permettant de faire un reset
    @FXML
    public void clickerSurBtnReset() {

        initChamps();

        tableVieSociale.getSelectionModel().clearSelection();

    }

    //methode permettant d'initialiser mles champs de saisies
    public void initChamps() {

        btnAJouter.setDisable(false);
        btnModifier.setDisable(true);
        btnSupprimer.setDisable(true);
        btnReset.setDisable(true);

        txfMontant.setText("");

        cbxAdherent.getSelectionModel().select(null);
        cbxTypeAssurance.getSelectionModel().clearSelection();

        dateDebut.setValue(null);
        dateFin.setValue(null);

        lbInfoTt.setText("" + vieSocialService.getNbEnregistrement());

    }

    //methode permettant de charger les champs de saisies
    public void chargerChamps(VieSociale sociale) {

        if (sociale != null) {

            cbxTypeAssurance.getSelectionModel().select(sociale.getTypAssurance());
            cbxAdherent.getSelectionModel().select(adherentService.readOne(sociale.getIdAdherent()));

            dateDebut.setValue(sociale.getDateDebut());
            dateFin.setValue(sociale.getDateFin());

            txfMontant.setText("" + sociale.getMontant());

            btnAJouter.setDisable(true);
            btnModifier.setDisable(false);
            btnReset.setDisable(false);
            btnSupprimer.setDisable(false);

        }

    }

    //methode permettant de gerer les contraintes
    public Boolean gestContraintes() {

        if (!(cbxTypeAssurance.getSelectionModel().getSelectedIndex() < 0)) {

            if (!(dateDebut.getEditor().getText().equals(""))) {

                if (!(dateFin.getEditor().getText().equals(""))) {

                    if (!(txfMontant.getText().equals(""))) {

                        if (cbxAdherent.getSelectionModel().getSelectedItem() != null) {

                            return true;

                        } else {

                            association.setMessageErreur("Veuillez selectionner l'adherent svp");
                            association.afficherAlerteErreur();

                        }

                    } else {

                        association.setMessageErreur("Veuillez entrer un montant svp");
                        association.afficherAlerteErreur();

                    }

                } else {

                    association.setMessageErreur("Veuillez entrer la date fin");
                    association.afficherAlerteErreur();

                }

            } else {

                association.setMessageErreur("Veuillez entrer la date debut");
                association.afficherAlerteErreur();

            }

        } else {

            association.setMessageErreur("Veuillez entrer le type d'assurance svp");
            association.afficherAlerteErreur();

        }

        return false;

    }

    //methode permettant de modifier la classe principale
    public void setGestionAssociation(GestionAssociation association) {
        this.association = association;
        tableVieSociale.setItems(association.getListeVieSociale());
        cbxTypeAssurance.setItems(association.getListeTypAssuranc());
        cbxAdherent.setItems(association.getListeAdherent());
    }

}
