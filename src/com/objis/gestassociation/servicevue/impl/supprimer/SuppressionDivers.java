package com.objis.gestassociation.servicevue.impl.supprimer;

import java.util.Optional;

import com.objis.gestassociation.GestionAssociation;
import com.objis.gestassociation.domaine.Divers;
import com.objis.gestassociation.service.impl.AssociationService;
import com.objis.gestassociation.service.impl.DiversService;
import com.objis.gestassociation.servicevue.ISuppressionVue;
import com.objis.gestassociation.servicevue.impl.DiversServiceVue;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;

public class SuppressionDivers extends DiversServiceVue implements ISuppressionVue {

    public SuppressionDivers(GestionAssociation association, DiversService diversService,
            AssociationService associationService, Label lbTt, TableView<Divers> tableDivers, Divers divers,
            Alert alert) {
        super(association, diversService, associationService, lbTt, tableDivers, divers, alert);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void supprimer() {

        alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Attention");
        alert.setContentText("Cette action entrainera la suppression de cet enregistrement");
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get().equals(ButtonType.OK)) {

            if (diversService.delete(tableDivers.getSelectionModel().getSelectedItem().getId())) {

                association.afficherAlertSuppression();

                association.getListeDivers().remove(tableDivers.getSelectionModel().getSelectedIndex());

                lbTt.setText("" + diversService.nbEnregistrement());

            } else {

                association.setMessageErreur("Erreur survenue pendant la suppression");
                association.afficherAlerteErreur();

            }

        } else {

            association.afficherAlerteAnnulation();

        }

    }

}
