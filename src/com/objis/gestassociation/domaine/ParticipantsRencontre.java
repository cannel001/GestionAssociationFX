package com.objis.gestassociation.domaine;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ParticipantsRencontre {
	
	//les proprietes
	private ObjectProperty<Adherent> adherent;
	private ObjectProperty<Rencontre> rencontre;
	private ObjectProperty<Long> idParticipant;
	private StringProperty etat;
	
	//constructeur par defaut
	public ParticipantsRencontre() {
		super();
		// TODO Auto-generated constructor stub
	}

	//constructeur avec parametre
	public ParticipantsRencontre(Adherent adherent,Rencontre rencontre,
			Long idParticipant,String etat) {
		this.adherent = new SimpleObjectProperty<>(adherent);
		this.rencontre = new SimpleObjectProperty<>(rencontre);
		this.idParticipant = new SimpleObjectProperty<>(idParticipant);
		this.etat=new SimpleStringProperty(etat);
	}

	
	//getters et setters
	//adherent
	public Adherent getAdherent() {
		return adherent.get();
	}
	
	public void setAdherent(Adherent adherent) {
		this.adherent = new SimpleObjectProperty<>(adherent);
	}
	
	public ObjectProperty<Adherent> adherentProperty(){
		return adherent;
	}

	//rencontre
	public Rencontre getRencontre() {
		return rencontre.get();
	}
	
	public void setRencontre(Rencontre rencontre) {
		this.rencontre = new SimpleObjectProperty<>(rencontre);
	}
	
	public ObjectProperty<Rencontre> rencontreProperty(){
		return rencontre;
	}

	//idParticipant
	public Long getIdParticipant() {
		return idParticipant.get();
	}

	public void setIdParticipant(Long idParticipant) {
		this.idParticipant = new SimpleObjectProperty<>(idParticipant);
	}
	
	public ObjectProperty<Long> idParticipant(){
		return idParticipant;
	}
	
	//etat
	public String getEtat() {
		return etat.get();
	}
	
	public void setEtat(String etat) {
		this.etat=new SimpleStringProperty(etat);
	}
	
	public StringProperty etatProperty(){
		return etat;
	}

	
	//methode de description
	@Override
	public String toString() {
		return "ParticipantsRencontre [adherent=" + adherent + ", rencontre=" + rencontre + ", idParticipant="
				+ idParticipant + ", etat="+etat+"]";
	}
	
	
	
	

}
