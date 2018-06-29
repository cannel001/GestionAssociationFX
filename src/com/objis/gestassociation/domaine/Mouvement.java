package com.objis.gestassociation.domaine;

import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Mouvement {
	
	//les proprietes
	private ObjectProperty<Long> id;
	private StringProperty nature;
	private ObjectProperty<Double> quantite;
	private StringProperty typMouvement;
	private ObjectProperty<LocalDate> date;
	private StringProperty etatMouvement;
	
	//le constructeur par defaut
	public Mouvement() {
		// TODO Auto-generated constructor stub
	}

	//le constructeur avec parametre
	public Mouvement(Long id, String nature, Double quantite, String typMouvement,LocalDate date,String etatMouvement) {
		super();
		this.id = new SimpleObjectProperty<>(id);
		this.nature = new SimpleStringProperty(nature);
		this.quantite = new SimpleObjectProperty<>(quantite);
		this.typMouvement = new SimpleStringProperty(typMouvement);
		this.date=new SimpleObjectProperty<LocalDate>(date);
		this.etatMouvement=new SimpleStringProperty(etatMouvement);
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
	
	//nature
	public String getNature() {
		return nature.get();
	}

	public void setNature(String nature) {
		this.nature.set(nature);
	}
	
	public StringProperty natureProperty() {
		return nature;
	}

	//quantite
	public Double getQuantite() {
		return quantite.get();
	}

	public void setQuantite(Double quantite) {
		this.quantite.set(quantite);
	}
	
	public ObjectProperty<Double> quantiteProperty(){
		return quantite;
	}

	//type mouvement
	public String getTypMouvement() {
		return typMouvement.get();
	}

	public void setTypMouvement(String typMouvement) {
		this.typMouvement.set(typMouvement);
	}
	
	public StringProperty typePouvementProperty() {
		return typMouvement;
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
	
	//etatMouvement
	public String getEtatMouvement() {
		
		return etatMouvement.get();
		
	}
	
	public StringProperty etatMouvementProperty() {
		
		return etatMouvement;
		
	}

	//methode de descirpiton toString
	@Override
	public String toString() {
		return "Mouvement [id=" + id + ", nature=" + nature + ", quantite=" + quantite + ", typMouvement="
				+ typMouvement + ", date=" + date + "]";
	}
	
	

}
