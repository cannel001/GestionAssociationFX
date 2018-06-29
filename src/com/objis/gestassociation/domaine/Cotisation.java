package com.objis.gestassociation.domaine;

import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Classe Cotisation
 * @author Seka Cannel Ulrich Evrard
 *
 */
public class Cotisation{
	
	//les proprietes
	private ObjectProperty<Float> montant;
	private ObjectProperty<LocalDate> date;
	private StringProperty etatCotisation;
	private ObjectProperty<Long> idCotisation;
	private ObjectProperty<Long> adherent;
	
	/**
	 * constructeur par defaut
	 */
	public Cotisation() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * constructeur avec parametres
	 * @param montant
	 * @param date
	 * @param etatCotisation
	 * @param idCotisation
	 * @param adherent
	 */
	public Cotisation(Float montant,LocalDate date,String etatCotisation,Long idCotisation,Long adherent) {
		
		this.montant = new SimpleObjectProperty<Float>(montant);
		this.date = new SimpleObjectProperty<LocalDate>(date);
		this.etatCotisation=new SimpleStringProperty(etatCotisation);
		this.idCotisation=new SimpleObjectProperty<Long>(idCotisation);
		this.adherent=new SimpleObjectProperty<Long>(adherent);
	}

	//les gettes et setters

	//montant
	public Float getMontant() {
		return montant.get();
	}
	
	public void setMontant(Float montant) {
		this.montant=new SimpleObjectProperty<>(montant);
	}
	
	public ObjectProperty<Float> montantProperty(){
		return montant;
	}

	//date
	public LocalDate getDate() {
		return date.get();
	}

	public void setDate(LocalDate date) {
		this.date=new SimpleObjectProperty<>(date);
	}
	
	public ObjectProperty<LocalDate> dateProperty(){
		return date;
	}
	
	//idCotisation
	public Long getIdCotisation() {
		return idCotisation.get();
	}
	
	public ObjectProperty<Long> idProperty(){
		return idCotisation;
	}
	
	public void setIdCotisation(Long id) {
		this.idCotisation=new SimpleObjectProperty<Long>(id);
	}
	
	//etatcotisation
	public String getEtatCotisation() {
		return etatCotisation.get();
	}
	
	public StringProperty etatCotisationProperty() {
		return etatCotisation;
	}
	
	public void setEtatCotisation(String etat) {
		this.etatCotisation=new SimpleStringProperty(etat);
	}
	
	//adherent
	public Long getAdherent() {
		return adherent.get();
	}
	
	public ObjectProperty<Long> AdherentProperty(){
		return adherent;
	}
	
	public void setAdherent(Long adherent) {
		this.adherent=new SimpleObjectProperty<Long>(adherent);
	}

	/**
	 * methode de description toString
	 */
	@Override
	public String toString() {
		return "Cotisation [idAdherent=" + AdherentProperty() + ", montant=" + montantProperty() + ", date=" + dateProperty() + "Etat cotisation="+etatCotisationProperty()+"]";
	}
	
	
	

}
