package com.objis.gestassociation.servicevue.impl.supprimer;

import java.util.Optional;

import com.objis.gestassociation.GestionAssociation;
import com.objis.gestassociation.domaine.Evenement;
import com.objis.gestassociation.service.impl.AssociationService;
import com.objis.gestassociation.service.impl.EvenementService;
import com.objis.gestassociation.servicevue.ISuppressionVue;
import com.objis.gestassociation.servicevue.impl.EvenementServiceVue;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

public class SuppressionEvenement extends EvenementServiceVue implements ISuppressionVue {

    public SuppressionEvenement(GestionAssociation association, AssociationService associationService,
            EvenementService service, Alert alert, TableView<Evenement> tableEvenement, DatePicker dateEvent,
            TextField txfLieu, TextArea txfObjet, TextArea txfAchat, TextField txfDepenstt, Button btnAjouter,
            Button btnModifier, Button btnSupprimer, Button btnReset, ComboBox<String> cbxSolde, Label lbMontEvent) {
        super(association, associationService, service, alert, tableEvenement, dateEvent, txfLieu, txfObjet, txfAchat,
                txfDepenstt, btnAjouter, btnModifier, btnSupprimer, btnReset, cbxSolde, lbMontEvent);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void supprimer() {

        alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Attention");
        alert.setContentText("Cette action entrainera la suppression de cet enregistrement");
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get().equals(ButtonType.OK)) {

            if (service.delete(tableEvenement.getSelectionModel().getSelectedItem().getId())) {

                association.getListeEvenement().remove(tableEvenement.getSelectionModel().getSelectedIndex());

                lbMontEvent.setText("" + service.nbEnregist());

                association.afficherAlertSuppression();

            }

            if (tableEvenement.getSelectionModel().getSelectedIndex() < 0) {

                tableEvenement.getSelectionModel().clearSelection();

            }

        } else {

            association.afficherAlerteAnnulation();

        }

    }

}
