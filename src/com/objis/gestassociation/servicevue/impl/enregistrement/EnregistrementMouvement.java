package com.objis.gestassociation.servicevue.impl.enregistrement;

import com.objis.gestassociation.GestionAssociation;
import com.objis.gestassociation.domaine.Mouvement;
import com.objis.gestassociation.service.impl.AssociationService;
import com.objis.gestassociation.service.impl.MouvementService;
import com.objis.gestassociation.servicevue.IEnregistrementVue;
import com.objis.gestassociation.servicevue.impl.MouvementServiceVue;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class EnregistrementMouvement extends MouvementServiceVue implements IEnregistrementVue {

    public EnregistrementMouvement(DatePicker date, TextField nature, TextField quantite, ComboBox<String> cbxMouvement,
            GestionAssociation association, TableView<Mouvement> tableMouvement, Button btnValider, Button btnModifier,
            Button btnSuprimer, Button btnReset, Label ttEntree, Label ttSortie) {
        super(date, nature, quantite, cbxMouvement, association, tableMouvement, btnValider, btnModifier, btnSuprimer, btnReset,
                ttEntree, ttSortie);
    }

    //methode permettant de faire un enregistrement
    @Override
    public void enregistrement() {

        mouvement = new Mouvement(associationService.generationId(), nature.getText(), Double.valueOf(quantite.getText()), cbxMouvement.getValue(), date.getValue(), "ACTIF");

        if (mouvementService.create(mouvement)) {

            association.afficherVueValider();

            association.getListeMouvement().add(mouvement);

        }

    }

}
