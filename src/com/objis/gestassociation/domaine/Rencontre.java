package com.objis.gestassociation.domaine;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Rencontre {
	
	//les proprietes
	private ObjectProperty<Long> idRencontre;
	private StringProperty description;
	private StringProperty lieu;
	private IntegerProperty nbParticipants;
	private StringProperty presencePresi;
	private StringProperty ordreJr;
	private StringProperty decisionPrise;
	private ObjectProperty<LocalDate> dateRencontre;
	private StringProperty etat;
	
	//constructeur par defaut
	public Rencontre() {
		super();
	}

	//constructeur avec parametre
	public Rencontre(String description, String lieu, int nbParticipants,
			String presencePresi, String ordreJr, String decisionPrise,
			LocalDate dateRencontre,Long idRencontre,String etat) {
		super();
		this.description = new SimpleStringProperty(description);
		this.lieu = new SimpleStringProperty(lieu);
		this.nbParticipants = new SimpleIntegerProperty(nbParticipants);
		this.presencePresi = new SimpleStringProperty(presencePresi);
		this.ordreJr = new SimpleStringProperty(ordreJr);
		this.decisionPrise = new SimpleStringProperty(decisionPrise);
		this.dateRencontre = new SimpleObjectProperty<>(dateRencontre);
		this.idRencontre=new SimpleObjectProperty<>(idRencontre);
		this.etat=new SimpleStringProperty(etat);
	}

	
	//les getters et setters
	//description
	public String getDescription() {
		return description.get();
	}
	
	public void setDescription(String description) {
		this.description = new SimpleStringProperty(description);
	}
	
	public StringProperty descriptionProperty() {
		return description;
	}
	
	//lieu
	public String getLieu() {
		return lieu.get();
	}
	
	public void setLieu(String lieu) {
		this.lieu = new SimpleStringProperty(lieu);
	}
	
	public StringProperty lieuProperty() {
		return lieu;
	}
	
	//nombre de participants
	public int getNbParticipants() {
		return nbParticipants.get();
	}
	
	public void setNbParticipants(int nbParticipants) {
		this.nbParticipants = new SimpleIntegerProperty(nbParticipants);
	}
	
	public IntegerProperty nbParticipantsProperty() {
		return nbParticipants;
	}
	
	//presence president
	public String getPresencePresi() {
		return presencePresi.get();
	}
	
	public void setPresencePresi(String presencePresi) {
		this.presencePresi = new SimpleStringProperty(presencePresi);
	}
	
	public StringProperty presencePresiProperty() {
		return presencePresi;
	}

	//ordre jour 
	public String getOrdreJr() {
		return ordreJr.get();
	}
	
	public void setOrdreJr(String ordreJr) {
		this.ordreJr = new SimpleStringProperty(ordreJr);
	}
	
	public StringProperty ordreJrProperty() {
		return ordreJr;
	}
	
	//decision prise
	public String getDecisionPrise() {
		return decisionPrise.get();
	}
	
	public void setDecisionPrise(String decisionPrise) {
		this.decisionPrise = new SimpleStringProperty(decisionPrise);
	}
	
	public StringProperty decisionPriseProperty() {
		return decisionPrise;
	}
	
	//date rencontre
	public LocalDate getDateRencontre() {
		return dateRencontre.get();
	}

	public void setDateRencontre(LocalDate dateRencontre) {
		this.dateRencontre = new SimpleObjectProperty<>(dateRencontre);
	}
	
	public ObjectProperty<LocalDate> dateRencontreProperty(){
		return dateRencontre;
	}
	
	//idRencontre
	public Long getIdRencontre() {
		return idRencontre.get();
	}
	
	public void setIdRencontre(Long idRencontre) {
		this.idRencontre=new SimpleObjectProperty<>(idRencontre);
	}
	
	public ObjectProperty<Long> idRencontreProperty(){
		return idRencontre;
	}
	
	//etat
	public StringProperty etatProperty() {
		return etat;
	}
	
	public String getEtat() {
		return etat.get();
	}
	
	public void setEtat(String etat) {
		this.etat=new SimpleStringProperty(etat);
	}

	//methode de description
	@Override
	public String toString() {
		return "Rencontre [description=" + description + ", lieu=" + lieu + ", nbParticipants=" + nbParticipants
				+ ", presencePresi=" + presencePresi + ", ordreJr=" + ordreJr + ", decisionPrise=" + decisionPrise
				+ ", dateRencontre=" + dateRencontre + ", idRencontre="+idRencontre+"]";
	}


}
