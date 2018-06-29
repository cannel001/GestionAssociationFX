package com.objis.gestassociation.domaine;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Classe Bureau
 * @author Seka Cannel Ulrich Evrard
 *
 */
public class Bureau extends Adherent {
	
	//les proprietes
	private StringProperty fonction;
	private StringProperty login;
	private StringProperty password;
	private StringProperty etatBureau;
	
	/**
	 * constructeur par defaut
	 */
	public Bureau() {
		
	}
	
	/**
	 * constructeur avec parametres
	 * @param fonction
	 * @param login
	 * @param password
	 * @param etatBureau
	 */
	public Bureau(String fonction,String login,String password,String etatBureau) {
		
		this.fonction=new SimpleStringProperty(fonction);
		this.login=new SimpleStringProperty(login);
		this.password=new SimpleStringProperty(password);
		this.etatBureau=new SimpleStringProperty(etatBureau);
		
	}
	

	//les getters et setters
	//fonction
	public StringProperty fonctionProperty() {
		return fonction;
	}
	
	public void setFonction(String fonction) {
		this.fonction=new SimpleStringProperty(fonction);
	}
	
	public String getFonction() {
		return fonction.get();
	}

	//login
	public StringProperty loginProperty() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login=new SimpleStringProperty(login);
	}
	
	public String getLogin() {
		return login.get();
	}

	//password
	public StringProperty passwordProperty() {
		return password;
	}

	public void setPassword(String password) {
		this.password=new SimpleStringProperty(password);
	}
	
	public String getPassword() {
		return password.get();
	}

	//etat bureau
	public StringProperty etatBureauProperty() {
		return etatBureau;
	}

	public void setEtatBureau(String etatBureau) {
		this.etatBureau=new SimpleStringProperty(etatBureau);
	}

	public String getEtatBureau() {
		return etatBureau.get();
	}
	
	
	/**
	 * mehode de description toString
	 */
	@Override
	public String toString() {
		return "Bureau [fonction=" + fonction + ", login=" + login + ", password=" + password + "]";
	}		

}
