package com.objis.gestassociation.domaine;

import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 * Classe des adherentes Internes et externes
 * @author Seka Cannel Ulrich Evrard
 *
 */
public class Adherent {
	
	//propriete 
	protected ObjectProperty<Long> id;
	protected StringProperty nom;
	protected StringProperty prenom;
	protected ObjectProperty<LocalDate> dateNaissance;
	protected StringProperty sexe;
	protected StringProperty nationalite;
	protected StringProperty numeroPiece;
	protected StringProperty typePiece;
	protected StringProperty profession;
	protected StringProperty residence;
	protected StringProperty lieuDeNaissance;
	protected StringProperty adresse;
	protected StringProperty telephone;
	protected StringProperty email;
	protected ObjectProperty<LocalDate> dateEntree;
	protected StringProperty matricule;
	protected StringProperty typAdherent;
	protected StringProperty image;
	protected StringProperty etatAdherent;
	
	/**
	 * constructeur par defaut
	 */
	public Adherent() {
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * Constructeur avec parametres
	 * @param id
	 * @param nom
	 * @param prenom
	 * @param dateNaissance
	 * @param sexe
	 * @param nationalite
	 * @param numeroPiece
	 * @param typePiece
	 * @param profession
	 * @param residence
	 * @param lieuDeNaissance
	 * @param adresse
	 * @param telephone
	 * @param email
	 * @param dateEntree
	 * @param matricule
	 * @param typAdherent
	 * @param image
	 * @param etatAdherent
	 */
	public Adherent(Long id, String nom, String prenom, LocalDate dateNaissance,
			String sexe, String nationalite, String numeroPiece, String typePiece,
			String profession, String residence, String lieuDeNaissance, String adresse,
			String telephone, String email, LocalDate dateEntree,
			String matricule, String typAdherent, String image, String etatAdherent) {
		this.id = new SimpleObjectProperty<Long>(id);
		this.nom = new SimpleStringProperty(nom);
		this.prenom = new SimpleStringProperty(prenom);
		this.dateNaissance = new SimpleObjectProperty<LocalDate>(dateNaissance);
		this.sexe = new SimpleStringProperty(sexe);
		this.nationalite = new SimpleStringProperty(nationalite);
		this.numeroPiece = new SimpleStringProperty(numeroPiece);
		this.typePiece = new SimpleStringProperty(typePiece);
		this.profession = new SimpleStringProperty(profession);
		this.residence = new SimpleStringProperty(residence);
		this.lieuDeNaissance = new SimpleStringProperty(lieuDeNaissance);
		this.adresse = new SimpleStringProperty(adresse);
		this.telephone = new SimpleStringProperty(telephone);
		this.email = new SimpleStringProperty(email);
		this.dateEntree = new SimpleObjectProperty<LocalDate>(dateEntree);
		this.matricule = new SimpleStringProperty(matricule);
		this.typAdherent = new SimpleStringProperty(typAdherent);
		this.image = new SimpleStringProperty(image);
		this.etatAdherent = new SimpleStringProperty(etatAdherent);
	}

	//les getters et setters
	
	//id
	public Long getId() {
		return id.get();
	}
	
	public void setId(Long id) {
		this.id=new SimpleObjectProperty<>(id);
	}
	
	public ObjectProperty<Long> idProperty(){
		return id;
	}
	
	//nom
	public String getNom() {
		return nom.get();
	}
	
	public void setNom(String nom) {
		this.nom=new SimpleStringProperty(nom);
	}
	
	public StringProperty nomProperty() {
		return nom;
	}

	//prenom
	public String getPrenom() {
		return prenom.get();
	}
	
	public void setPrenom(String prenom) {
		this.prenom=new SimpleStringProperty(prenom);
	}
	
	public StringProperty prenomProperty() {
		return prenom;
	}
	
	//date de naissance
	public LocalDate getDateNaissance() {
		return dateNaissance.get();
	}
	
	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance=new SimpleObjectProperty<>(dateNaissance);
	}
	
	public ObjectProperty<LocalDate> datenaissanceProperty(){
		return dateNaissance;
	}
	
	//sexe
	public String getSexe() {
		return sexe.get();
	}
	
	public void setSexe(String sexe) {
		this.sexe=new SimpleStringProperty(sexe);
	}
	
	public StringProperty sexeProperty() {
		return sexe;
	}

	//nationalité
	public String getNationalite() {
		return nationalite.get();
	}
	
	public void setNationalite(String nationalite) {
		this.nationalite=new SimpleStringProperty(nationalite);
	}
	
	public StringProperty nationaliteProperty() {
		return nationalite;
	}

	//numero piece
	public String getNumeroPiece() {
		return numeroPiece.get();
	}
	
	public void setNumeroPiece(String numeroPiece) {
		this.numeroPiece=new SimpleStringProperty(numeroPiece);
	}
	
	public StringProperty numeroPieceProperty() {
		return numeroPiece;
	}

	//type piece
	public String getTypePiece() {
		return typePiece.get();
	}
	
	public void setTypePiece(String typePiece) {
		this.typePiece=new SimpleStringProperty(typePiece);
	}
	
	public StringProperty typPieceProperty() {
		return typePiece;
	}

	//profession
	public String getProfession() {
		return profession.get();
	}
	
	public void setProfession(String profession) {
		this.profession=new SimpleStringProperty(profession);
	}
	
	public StringProperty professionProperty() {
		return profession;
	}
	
	//residence
	public String getResidence() {
		return residence.get();
	}
	
	public void setResidence(String residence) {
		this.residence=new SimpleStringProperty(residence);
	}
	
	public StringProperty residenceProperty() {
		return residence;
	}
	
	//lieu de naissance
	public String getLieuDeNaissance() {
		return lieuDeNaissance.get();
	}
	
	public void setLieuDeNaissance(String lieuDeNaissance) {
		this.lieuDeNaissance=new SimpleStringProperty(lieuDeNaissance);
	}
	
	public StringProperty lieuNaissancProperty() {
		return lieuDeNaissance;
	}

	//adresse
	public String getAdresse() {
		return adresse.get();
	}
	
	public void setAdresse(String adresse) {
		this.adresse=new SimpleStringProperty(adresse);
	}
	
	public StringProperty adresseProperty() {
		return adresse;
	}
	
	//telephone
	public String getTelephone() {
		return telephone.get();
	}
	
	public void setTelephone(String telephone) {
		this.telephone=new SimpleStringProperty(telephone);
	}
	
	public StringProperty telephoneProperty() {
		return telephone;
	}
	
	//email
	public String getEmail() {
		return email.get();
	}
	
	public void setEmail(String email) {
		this.email=new SimpleStringProperty(email);
	}
	
	public StringProperty emailProperty() {
		return email;
	}

	//date d'entrée
	public LocalDate getDateEntree() {
		return dateEntree.get();
	}
	
	public void setDateEntree(LocalDate dateEntree) {
		this.dateEntree=new SimpleObjectProperty<>(dateEntree);
	}
	
	public ObjectProperty<LocalDate> dateEntreProperty(){
		return dateEntree;
	}
	
	//matricule
	public String getMatricule() {
		return matricule.get();
	}
	
	public void setMatricule(String matricule) {
		this.matricule=new SimpleStringProperty(matricule);
	}
	
	public StringProperty matriculeProperty() {
		return matricule;
	}
	
	//type adherent
	public String getTypAdherent() {
		return typAdherent.get();
	}
	
	public void setTypAdherent(String typAdherent) {
		this.typAdherent=new SimpleStringProperty(typAdherent);
	}
	
	public StringProperty typAdherentProperty() {
		return typAdherent;
	}

	//image
	public String getImage() {
		return image.get();
	}
	
	public void setImage(String image) {
		this.image=new SimpleStringProperty(image);
	}
	
	public StringProperty imageProperty() {
		return image;
	}

	//etat ADherent
	public String getEtatAdherent() {
		return etatAdherent.get();
	}
	
	public void setEtatAdherent(String etatAdherent) {
		this.etatAdherent=new SimpleStringProperty(etatAdherent);
	}
	
	public StringProperty etatAdherentProperty() {
		return etatAdherent;
	}

	
	/**
	 * la methode de description to String
	 */
	@Override
	public String toString() {
		return getMatricule()+" "+getNom()+ " " + getPrenom();
	}	
	

}
