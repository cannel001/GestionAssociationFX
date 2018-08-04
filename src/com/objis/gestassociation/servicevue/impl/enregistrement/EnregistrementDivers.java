package com.objis.gestassociation.servicevue.impl.enregistrement;

import com.objis.gestassociation.GestionAssociation;
import com.objis.gestassociation.domaine.Divers;
import com.objis.gestassociation.service.impl.AssociationService;
import com.objis.gestassociation.service.impl.DiversService;
import com.objis.gestassociation.servicevue.IEnregistrementVue;
import com.objis.gestassociation.servicevue.impl.DiversServiceVue;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class EnregistrementDivers extends DiversServiceVue implements IEnregistrementVue {

    public EnregistrementDivers(GestionAssociation association, DiversService diversService,
            AssociationService associationService, DatePicker date, TextField txfLieu, TextField txfMotif,
            TextArea txfCorpsFormulaire, ComboBox<String> cbxTypFormulaire, Label lbTt, TableView<Divers> tableDivers,
            Divers divers) {
        super(association, diversService, associationService, date, txfLieu, txfMotif, txfCorpsFormulaire, cbxTypFormulaire,
                lbTt, tableDivers, divers);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void enregistrement() {

        divers = new Divers(date.getValue(), txfLieu.getText(), cbxTypFormulaire.getSelectionModel().getSelectedItem(), txfMotif.getText(), txfCorpsFormulaire.getText(), associationService.generationId(), "ACTIF");

        if (diversService.create(divers)) {

            association.getListeDivers().add(divers);
            association.afficherVueValider();

        } else {

            association.setMessageErreur("Erreur survenue pendant l'enregistrement");
            association.afficherAlerteErreur();

        }

    }

}
