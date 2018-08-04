package com.objis.gestassociation.domaine;

import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class VieSociale {
	
	//les proprietes
	private ObjectProperty<Long> idVieSociale;
	private ObjectProperty<LocalDate> dateDebut;
	private ObjectProperty<LocalDate> dateFin;
	private ObjectProperty<Double> montant;
	private ObjectProperty<Long> idAdherent;
	private StringProperty typeAssurance;
	private StringProperty etatVieSociale;
	
	//constructeur par defaut
	public VieSociale() {
		super();
	}

	//constructeur avec tous les parametres
	public VieSociale(Long idVieSociale, LocalDate dateDebut,
			LocalDate dateFin, Double montant, Long idAdherent,String typeAssurance,String etatVieSociale) {

		this.idVieSociale = new SimpleObjectProperty<Long>(idVieSociale);
		this.dateDebut = new SimpleObjectProperty<>(dateDebut);
		this.dateFin = new SimpleObjectProperty<>(dateFin);
		this.montant = new SimpleObjectProperty<>(montant);
		this.idAdherent = new SimpleObjectProperty<>(idAdherent);
		this.typeAssurance=new SimpleStringProperty(typeAssurance);
		this.etatVieSociale=new SimpleStringProperty(etatVieSociale);
		
	}

	//les getters et setters
	//id vie sociale
	public Long getIdVieSociale() {
		return idVieSociale.get();
	}
	
	public void setIdVieSociale(Long idVieSociale) {
		this.idVieSociale = new SimpleObjectProperty<>(idVieSociale);
	}
	
	public ObjectProperty<Long> idVieSocialeProperty(){
		return idVieSociale;
	}
	
	
	//date debut
	public LocalDate getDateDebut() {
		return dateDebut.get();
	}
	
	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = new SimpleObjectProperty<>(dateDebut);
	}
	
	public ObjectProperty<LocalDate> dateDebutProperty(){
		return dateDebut;
	}
	
	//date fin
	public void setDateFin(LocalDate dateFin) {
		this.dateFin = new SimpleObjectProperty<>(dateFin);
	}

	public LocalDate getDateFin() {
		return dateFin.get();
	}

	public ObjectProperty<LocalDate> dateFinProperty(){
		return dateFin;
	}
	
	//montant
	public Double getMontant() {
		return montant.get();
	}
	
	public void setMontant(Double montant) {
		this.montant = new SimpleObjectProperty<>(montant);
	}
	
	public ObjectProperty<Double> montantProperty(){
		return montant;
	}

	//idAdherent
	public Long getIdAdherent() {
		return idAdherent.get();
	}

	public void setIdAdherent(Long idAdherent) {
		this.idAdherent = new SimpleObjectProperty<>(idAdherent);
	}
	
	public ObjectProperty<Long> idAdherentProperty(){
		return idAdherent;
	}
	
	//type assurance
	public String getTypAssurance() {
		return typeAssurance.get();
	}
	
	public StringProperty typeAssuranceProperty() {
		return typeAssurance;
	}
	
	public void setTypeAssurance(String typeAssurance) {
		this.typeAssurance=new SimpleStringProperty(typeAssurance);
	}
	
	//etat viesociale
	public String getEtatVieSociale() {
		return etatVieSociale.get();
	}
	
	public StringProperty etatVieSocial() {
		return etatVieSociale;
	}
	
	public void setEtatVieSocial(String etatVieSocial) {
		this.etatVieSociale=new SimpleStringProperty(etatVieSocial);
	}

	//methode de description toString
	@Override
	public String toString() {
		return "VieSociale [idVieSociale=" + idVieSociale + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin
				+ ", montant=" + montant + ", idAdherent=" + idAdherent + "]";
	}
	
	
	
	
	
	
	
	

}
