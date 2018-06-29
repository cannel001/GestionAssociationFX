package com.objis.gestassociation.servicevue.impl;

import java.io.File;
import java.io.FileInputStream;

import com.objis.gestassociation.GestionAssociation;
import com.objis.gestassociation.domaine.Adherent;
import com.objis.gestassociation.service.impl.AdherentService;
import com.objis.gestassociation.service.impl.AssociationService;
import com.objis.gestassociation.service.impl.CotisationAnnuelleService;
import com.objis.gestassociation.servicevue.IServiceVue;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class AdherentServiceVue {
	
	//les proprietes
	protected GestionAssociation association;
	protected AdherentService service;
	protected Adherent adherent; 
	protected static Long idAdherent;
	protected AssociationService associationService=new AssociationService();
	protected Alert alert;

	//traiment de l'image
	protected static String ext="";
	protected static String cible="";
	protected static FileInputStream fluxEntree=null;
	protected File fichierSelectionner=null;
	
	protected String sexe="";
	protected String typePiece="";
	protected String typAdherent="";
	
	protected TableView<Adherent> tableAdherent;
	
	
	protected Button btnAjouter;
	protected Button btnModifier;
	protected Button btnSupprimer;
	protected Button btnReset;
	protected Button btnGenerationMatri;
	protected Button btnAjouterPhoto;
	
	
	protected RadioButton selectInterne;
	
	protected RadioButton selectExterne;
	
	
	protected ImageView imgPhoto;
	
	
	protected TextField txfNom;

	protected TextField txfPrenom;

	protected TextField txfLieuNaissance;

	protected TextField txfNationalite;

	protected TextField txfTelephone;

	protected TextField txfEmail;

	protected TextField txfProfession;
	
	protected TextField txfResidence;

	protected TextField txfAdresse;

	protected TextField txfNumPiece;
	
	protected TextField txfMatricule;
	

	protected RadioButton selectFem;
	
	protected RadioButton selectHom;

	protected RadioButton selectCni;

	protected RadioButton selectPasseport;

	protected RadioButton selectConsulaire;

	protected RadioButton selectAutre;
	
	protected RadioButton selectAttestation;
	
	
	protected DatePicker txfDateNaissance;
	
	protected DatePicker txfDateEntree;
	
	
	protected Label ttAdInterne;

	protected Label ttAdExterne;
	
	protected Label lbId;
	
	protected FileInputStream lienPhoto;
	
	protected CotisationAnnuelleService cotisationAnnuelleService=new CotisationAnnuelleService();
	
	protected String date;

}
