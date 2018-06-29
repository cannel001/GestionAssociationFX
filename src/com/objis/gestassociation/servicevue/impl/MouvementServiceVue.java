package com.objis.gestassociation.servicevue.impl;

import com.objis.gestassociation.GestionAssociation;
import com.objis.gestassociation.domaine.Mouvement;
import com.objis.gestassociation.service.impl.AssociationService;
import com.objis.gestassociation.service.impl.MouvementService;
import com.objis.gestassociation.servicevue.IServiceVue;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MouvementServiceVue {
	
	protected DatePicker date;
	protected TextField nature;
	protected TextField quantite;
	protected ComboBox<String> cbxMouvement;
	protected GestionAssociation association;
	protected Mouvement mouvement;
	protected MouvementService mouvementService;
	protected AssociationService associationService;
	protected TableView<Mouvement> tableMouvement;
	protected Button btnValider;
	protected Button btnModifier;
	protected Button btnSupprimer;
	protected Button btnReset;
	protected Label ttEntree;
	protected Label ttSortie;
	
	public MouvementServiceVue(DatePicker date,TextField nature,TextField quantite,ComboBox<String> cbxMouvement,
			GestionAssociation association,TableView<Mouvement> tableMouvement,Button btnValider,Button btnModifier,
			Button btnSuprimer,Button btnReset,Label ttEntree,Label ttSortie) {
		
		this.date=date;
		this.nature=nature;
		this.quantite=quantite;
		this.cbxMouvement=cbxMouvement;
		this.association=association;
		this.mouvementService=new MouvementService();
		this.associationService=new AssociationService();
		this.tableMouvement=tableMouvement;
		this.btnValider=btnValider;
		this.btnModifier=btnModifier;
		this.btnSupprimer=btnSuprimer;
		this.btnReset=btnReset;
		this.ttEntree=ttEntree;
		this.ttSortie=ttSortie;
		
	}

}
