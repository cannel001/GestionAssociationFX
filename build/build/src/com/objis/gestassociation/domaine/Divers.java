package com.objis.gestassociation.domaine;

import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Classe Divers
 * @author Seka Cannel Ulrich Evrard
 *
 */
public class Divers {
	
	//les proprietes
	private ObjectProperty<LocalDate> date;
	private StringProperty lieu;
	private StringProperty typeFormulaire;
	private StringProperty motif;
	private StringProperty corpsFormulaire;
	private ObjectProperty<Long> id;
	private StringProperty etatDivers;
	
	//constructeur par defaut
	public Divers() {
		// TODO Auto-generated constructor stub
	}

	//constructeur avec parametres
	public Divers(LocalDate date, String lieu, String typeFormulaire,
			String motif, String corpsFormulaire, Long id, String etatDivers) {
		this.date = new SimpleObjectProperty<>(date);
		this.lieu = new SimpleStringProperty(lieu);
		this.typeFormulaire = new SimpleStringProperty(typeFormulaire);
		this.motif = new SimpleStringProperty(motif);
		this.corpsFormulaire = new SimpleStringProperty(corpsFormulaire);
		this.id = new SimpleObjectProperty<>(id);
		this.etatDivers = new SimpleStringProperty(etatDivers);
	}

	//les getters et setters
	//date
	public ObjectProperty<LocalDate> dateProperty() {
		return date;
	}
	
	public void setDate(LocalDate date) {
		this.date = new SimpleObjectProperty<>(date);
	}
	
	public LocalDate getDate() {
		return date.get();
	}
	
	//lieu
	public StringProperty lieuProperty() {
		return lieu;
	}
	
	public void setLieu(String lieu) {
		this.lieu = new SimpleStringProperty(lieu);
	}
	
	public String getLieu() {
		return lieu.get();
	}

	//type formulaire
	public StringProperty typeFormulaireProperty() {
		return typeFormulaire;
	}
	
	public void setTypeFormulaire(String typeFormulaire) {
		this.typeFormulaire = new SimpleStringProperty(typeFormulaire);
	}
	
	public String getTypeFormulaire() {
		return typeFormulaire.get();
	}

	//motif
	public StringProperty motifProperty() {
		return motif;
	}
	
	public void setMotif(String motif) {
		this.motif = new SimpleStringProperty(motif);
	}
	
	public String getMotif() {
		return motif.get();
	}

	//corps formulaire
	public StringProperty corpsFormulaireProperty() {
		return corpsFormulaire;
	}
	
	public void setCorpsFormulaire(String corpsFormulaire) {
		this.corpsFormulaire = new SimpleStringProperty(corpsFormulaire);
	}
	
	public String getCorpsFormalire() {
		return corpsFormulaire.get();
	}

	//id
	public ObjectProperty<Long> idProperty() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = new SimpleObjectProperty<>(id);
	}
	
	public Long getId() {
		return id.get();
	}

	//etat divers
	public StringProperty etatDiversProperty() {
		return etatDivers;
	}

	public void setEtatDivers(String etatDivers) {
		this.etatDivers = new SimpleStringProperty(etatDivers);
	}
	
	public String getEtatDivers() {
		return etatDivers.get();
	}

	
	
	@Override
	public String toString() {
		return "Divers [date=" + date + ", lieu=" + lieu + ", typeFormulaire=" + typeFormulaire + ", motif=" + motif
				+ ", corpsFormulaire=" + corpsFormulaire + ", id=" + id + ", etatDivers=" + etatDivers + "]";
	}
	
	
	
	

}
