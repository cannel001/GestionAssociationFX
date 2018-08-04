package com.objis.gestassociation.domaine;

import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Evenement {
	
	//les proprietes
	private ObjectProperty<Long> id;
	private ObjectProperty<LocalDate> date;
	private StringProperty lieu;
	private StringProperty objet;
	private StringProperty achat;
	private ObjectProperty<Float> depenseTt;
	private StringProperty solde;
	private StringProperty etatEvenement;
	
	//constructeur par defaut
	public Evenement() {
		super();
		// TODO Auto-generated constructor stub
	}

	//contructeur avec parametres
	public Evenement(Long id,LocalDate date, String lieu, String objet,
			String achat, Float depenseTt, String solde,String etatEvenement) {
		this.id = new SimpleObjectProperty<Long>(id);
		this.date = new SimpleObjectProperty<LocalDate>(date);
		this.lieu = new SimpleStringProperty(lieu);
		this.objet = new SimpleStringProperty(objet);
		this.achat = new SimpleStringProperty(achat);
		this.depenseTt = new SimpleObjectProperty<Float>(depenseTt);
		this.solde = new SimpleStringProperty(solde);
		this.etatEvenement=new SimpleStringProperty(etatEvenement);
	}

	//les getters et setters
	//id
	public Long getId() {
		return id.get();
	}
	
	public void setId(Long id) {
		this.id.set(id);
	}
	
	public ObjectProperty<Long> idProperty(){
		return id;
	}
	
	
	//date
	public LocalDate getDate() {
		return date.get();
	}
	
	public void setDate(LocalDate date) {
		this.date.set(date);
	}
	
	public ObjectProperty<LocalDate> dateProperty(){
		return date;
	}
	
	
	//lieu
	public String getLieu() {
		return lieu.get();
	}
	
	public void setLieu(String lieu) {
		this.lieu.set(lieu);
	}
	
	public StringProperty lieuProperty() {
		return lieu;
	}
	
	
	//objet
	public String getObjet() {
		return objet.get();
	}
	
	public void setObjet(String objet) {
		this.objet.set(objet);
	}
	
	public StringProperty objetProperty() {
		return objet;
	}
	
	
	
	//achat
	public String getAchat() {
		return achat.get();
	}
	
	public void setAchat(String achat) {
		this.achat.set(achat);
	}
	
	public StringProperty achatProperty() {
		return achat;
	}
	
	
	//depense
	public Float getDepenseTt() {
		return depenseTt.get();
	}
	
	public void setDepenseTt(Float depenseTt) {
		this.depenseTt.set(depenseTt);
	}
	
	public ObjectProperty<Float> depenseTt(){
		return depenseTt;
	}

	
	
	//solde
	public String getSolde() {
		return solde.get();
	}

	public void setSolde(String solde) {
		this.solde.set(solde);
	}
	
	public StringProperty soldeProperty() {
		return solde;
	}
	
	//etat evenement
	public String getEtatEvenement() {
		
		return etatEvenement.get();
		
	}

	public StringProperty etatEvenement() {
		
		return etatEvenement;
		
	}
	
	//methode de descritpion toString
	@Override
	public String toString() {
		return "Evenement [id=" + id + ", date=" + date + ", lieu=" + lieu + ", objet=" + objet + ", achat=" + achat
				+ ", depenseTt=" + depenseTt + ", solde=" + solde + "]";
	}
	
	
	

	

}
