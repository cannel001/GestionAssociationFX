package com.objis.gestassociation.servicevue.impl.enregistrement;

import com.objis.gestassociation.GestionAssociation;
import com.objis.gestassociation.domaine.Evenement;
import com.objis.gestassociation.service.impl.AssociationService;
import com.objis.gestassociation.service.impl.EvenementService;
import com.objis.gestassociation.servicevue.IEnregistrementVue;
import com.objis.gestassociation.servicevue.impl.EvenementServiceVue;

import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class EnregistrementEvenement extends EvenementServiceVue implements IEnregistrementVue {

    public EnregistrementEvenement(GestionAssociation association, AssociationService associationService,
            EvenementService service, Alert alert, TableView<Evenement> tableEvenement, DatePicker dateEvent,
            TextField txfLieu, TextArea txfObjet, TextArea txfAchat, TextField txfDepenstt, ComboBox<String> cbxSolde,
            Label lbMontEvent) {
        super(association, associationService, service, alert, tableEvenement, dateEvent, txfLieu, txfObjet, txfAchat,
                txfDepenstt, cbxSolde, lbMontEvent);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void enregistrement() {

        Evenement evenement = new Evenement(associationService.generationId(), dateEvent.getValue(), txfLieu.getText(), txfObjet.getText(), txfAchat.getText(), Float.parseFloat(txfDepenstt.getText()), cbxSolde.getSelectionModel().getSelectedItem(), "ACTIF");

        if (service.create(evenement)) {

            association.getListeEvenement().add(evenement);

            //ajouter le message d'alert
            association.afficherVueValider();

        }

    }

}
