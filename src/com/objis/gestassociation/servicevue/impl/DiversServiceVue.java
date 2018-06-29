package com.objis.gestassociation.servicevue.impl;


import com.objis.gestassociation.GestionAssociation;
import com.objis.gestassociation.domaine.Divers;
import com.objis.gestassociation.service.impl.AssociationService;
import com.objis.gestassociation.service.impl.DiversService;
import com.objis.gestassociation.servicevue.IServiceVue;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DiversServiceVue{
	
	//les proprietes
	protected GestionAssociation association;
	protected DiversService diversService;
	protected AssociationService associationService;
	

	protected DatePicker date;
	

	protected TextField txfLieu;

	protected TextField txfMotif;

	protected TextArea txfCorpsFormulaire;
	

	protected ComboBox<String> cbxTypFormulaire;
	

	protected Label lbTt;
	

	protected Button btnAjouter;

	protected Button btnModifier;
	
	protected Button btnSupprimer;

	protected Button btnReset;
	

	protected TableView<Divers> tableDivers;
	
	protected Divers divers;
	
	protected Alert alert;

	//constructeur
	public DiversServiceVue(GestionAssociation association, DiversService diversService,
			AssociationService associationService, DatePicker date, TextField txfLieu, TextField txfMotif,
			TextArea txfCorpsFormulaire, ComboBox<String> cbxTypFormulaire, Label lbTt, Button btnAjouter,
			Button btnModifier, Button btnSupprimer, Button btnReset, TableView<Divers> tableDivers, Divers divers,
			Alert alert) {

		this.association = association;
		this.diversService = diversService;
		this.associationService = associationService;
		this.date = date;
		this.txfLieu = txfLieu;
		this.txfMotif = txfMotif;
		this.txfCorpsFormulaire = txfCorpsFormulaire;
		this.cbxTypFormulaire = cbxTypFormulaire;
		this.lbTt = lbTt;
		this.btnAjouter = btnAjouter;
		this.btnModifier = btnModifier;
		this.btnSupprimer = btnSupprimer;
		this.btnReset = btnReset;
		this.tableDivers = tableDivers;
		this.divers = divers;
		this.alert = alert;
		
	}
	
	public DiversServiceVue(GestionAssociation association, DiversService diversService,
			AssociationService associationService, DatePicker date, TextField txfLieu, TextField txfMotif,
			TextArea txfCorpsFormulaire, ComboBox<String> cbxTypFormulaire, Label lbTt, TableView<Divers> tableDivers, Divers divers) {

		this.association = association;
		this.diversService = diversService;
		this.associationService = associationService;
		this.date = date;
		this.txfLieu = txfLieu;
		this.txfMotif = txfMotif;
		this.txfCorpsFormulaire = txfCorpsFormulaire;
		this.cbxTypFormulaire = cbxTypFormulaire;
		this.lbTt = lbTt;
		this.tableDivers = tableDivers;
		this.divers = divers;
		
	}

	public DiversServiceVue(GestionAssociation association, DiversService diversService,
			AssociationService associationService, Label lbTt, TableView<Divers> tableDivers, Divers divers,
			Alert alert) {
		super();
		this.association = association;
		this.diversService = diversService;
		this.associationService = associationService;
		this.lbTt = lbTt;
		this.tableDivers = tableDivers;
		this.divers = divers;
		this.alert = alert;
	}

	
	

}
