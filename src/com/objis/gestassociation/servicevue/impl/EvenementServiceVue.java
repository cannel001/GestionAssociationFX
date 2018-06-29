package com.objis.gestassociation.servicevue.impl;

import java.time.LocalDate;

import com.objis.gestassociation.GestionAssociation;
import com.objis.gestassociation.domaine.Evenement;
import com.objis.gestassociation.service.impl.AssociationService;
import com.objis.gestassociation.service.impl.EvenementService;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class EvenementServiceVue {
	
	//les proprietes
	protected GestionAssociation association;
	protected AssociationService associationService=new AssociationService();
	protected EvenementService service=new EvenementService();
	protected Alert alert;
	
	protected TableView<Evenement> tableEvenement;
	
	protected DatePicker dateEvent;
	
	protected TextField txfLieu;
	protected TextArea txfObjet;
	protected TextArea txfAchat;
	protected TextField txfDepenstt;
	
	protected Button btnAjouter;
	protected Button btnModifier;
	protected Button btnSupprimer;
	protected Button btnReset;
	
	protected ComboBox<String> cbxSolde;
	
	protected Label lbMontEvent;

	public EvenementServiceVue(GestionAssociation association, AssociationService associationService,
			EvenementService service, Alert alert, TableView<Evenement> tableEvenement, DatePicker dateEvent,
			TextField txfLieu, TextArea txfObjet, TextArea txfAchat, TextField txfDepenstt, ComboBox<String> cbxSolde,
			Label lbMontEvent) {
		super();
		this.association = association;
		this.associationService = associationService;
		this.service = service;
		this.alert = alert;
		this.tableEvenement = tableEvenement;
		this.dateEvent = dateEvent;
		this.txfLieu = txfLieu;
		this.txfObjet = txfObjet;
		this.txfAchat = txfAchat;
		this.txfDepenstt = txfDepenstt;
		this.cbxSolde = cbxSolde;
		this.lbMontEvent = lbMontEvent;
	}

	public EvenementServiceVue(GestionAssociation association, AssociationService associationService,
			EvenementService service, Alert alert, TableView<Evenement> tableEvenement, DatePicker dateEvent,
			TextField txfLieu, TextArea txfObjet, TextArea txfAchat, TextField txfDepenstt, Button btnAjouter,
			Button btnModifier, Button btnSupprimer, Button btnReset, ComboBox<String> cbxSolde, Label lbMontEvent) {
		super();
		this.association = association;
		this.associationService = associationService;
		this.service = service;
		this.alert = alert;
		this.tableEvenement = tableEvenement;
		this.dateEvent = dateEvent;
		this.txfLieu = txfLieu;
		this.txfObjet = txfObjet;
		this.txfAchat = txfAchat;
		this.txfDepenstt = txfDepenstt;
		this.btnAjouter = btnAjouter;
		this.btnModifier = btnModifier;
		this.btnSupprimer = btnSupprimer;
		this.btnReset = btnReset;
		this.cbxSolde = cbxSolde;
		this.lbMontEvent = lbMontEvent;
	}
	
	

}
