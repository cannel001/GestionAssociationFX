package com.objis.gestassociation.servicevue.impl.supprimer;

import com.objis.gestassociation.GestionAssociation;
import com.objis.gestassociation.domaine.Mouvement;
import com.objis.gestassociation.service.impl.AssociationService;
import com.objis.gestassociation.service.impl.MouvementService;
import com.objis.gestassociation.servicevue.ISuppressionVue;
import com.objis.gestassociation.servicevue.impl.MouvementServiceVue;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class SupprimerMouvement extends MouvementServiceVue implements ISuppressionVue {

    public SupprimerMouvement(DatePicker date, TextField nature, TextField quantite, ComboBox<String> cbxMouvement,
            GestionAssociation association, TableView<Mouvement> tableMouvement, Button btnValider, Button btnModifier,
            Button btnSuprimer, Button btnReset, Label ttEntree, Label ttSortie) {
        super(date, nature, quantite, cbxMouvement, association, tableMouvement, btnValider, btnModifier, btnSuprimer, btnReset,
                ttEntree, ttSortie);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void supprimer() {

        System.out.println(tableMouvement.getSelectionModel().getSelectedItem().getId());

        if (mouvementService.delete(tableMouvement.getSelectionModel().getSelectedItem().getId())) {

            association.afficherAlertSuppression();
            association.getListeMouvement().remove(tableMouvement.getSelectionModel().getSelectedIndex());

        } else {

            association.setMessageErreur("Erreur survenue pendant le traitement");
            association.afficherAlerteErreur();

        }

    }

}
