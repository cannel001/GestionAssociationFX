package com.objis.gestassociation.domaine;


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * Classe Cotisation Annuelle
 * @author Seka Cannel Ulrich Evrard
 *
 */
public class CotisationAnnuelle {
	
	//les proprietes
	private ObjectProperty<Float> montJanvier;
	private ObjectProperty<Float> montFevrier;
	private ObjectProperty<Float> montMars;
	private ObjectProperty<Float> montAvril;
	private ObjectProperty<Float> montMai;
	private ObjectProperty<Float> montJuin;
	private ObjectProperty<Float> montjuillet;
	private ObjectProperty<Float> montAout;
	private ObjectProperty<Float> montSeptembre;
	private ObjectProperty<Float> montOctobre;
	private ObjectProperty<Float> montNovembre;
	private ObjectProperty<Float> montDecembre;
	private ObjectProperty<Float> montTotal;
	private ObjectProperty<Adherent> adherent;
	
	
	/**
	 * constructeur par defaut
	 */
	public CotisationAnnuelle() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * constructeur avec parametre
	 * @param montJanvier
	 * @param montFevrier
	 * @param montMars
	 * @param montAvril
	 * @param montMai
	 * @param montJuin
	 * @param montjuillet
	 * @param montAout
	 * @param montSeptembre
	 * @param montOctobre
	 * @param montNovembre
	 * @param montDecembre
	 * @param montTotal
	 * @param adherent
	 */
	public CotisationAnnuelle(float montJanvier, float montFevrier, float montMars, float montAvril, float montMai,
			float montJuin, float montjuillet, float montAout, float montSeptembre, float montOctobre,
			float montNovembre, float montDecembre,float montTotal,Adherent adherent) {
		super();
		this.montJanvier = new SimpleObjectProperty<Float>(montJanvier) ;
		this.montFevrier = new SimpleObjectProperty<Float>(montFevrier) ;
		this.montMars = new SimpleObjectProperty<Float>(montMars) ;
		this.montAvril = new SimpleObjectProperty<Float>(montAvril) ;
		this.montMai = new SimpleObjectProperty<Float>(montMai) ;
		this.montJuin = new SimpleObjectProperty<Float>(montJuin) ;
		this.montjuillet = new SimpleObjectProperty<Float>(montjuillet) ;
		this.montAout = new SimpleObjectProperty<Float>(montAout) ;
		this.montSeptembre = new SimpleObjectProperty<Float>(montSeptembre) ;
		this.montOctobre = new SimpleObjectProperty<Float>(montOctobre) ;
		this.montNovembre = new SimpleObjectProperty<Float>(montNovembre) ;
		this.montDecembre = new SimpleObjectProperty<Float>(montDecembre) ;
		this.montTotal= new SimpleObjectProperty<Float>(montTotal);
		this.adherent=new SimpleObjectProperty<Adherent>(adherent);
	}
	
	//les getters et setters
	/**
	 * getter janvier
	 * @return
	 */
	public float getMontJanvier() {
		return montJanvier.get();
	}
	
	/**
	 * setter janvier
	 * @param montJanvier
	 */
	public void setMontJanvier(float montJanvier) {
		this.montJanvier=new SimpleObjectProperty<>(montJanvier);
	}
	
	/**
	 * getter janvier Property
	 * @return
	 */
	public ObjectProperty<Float> montJanvierProperty(){
		return montJanvier;
	}

	//fevrier
	public float getMontFevrier() {
		return montFevrier.get();
	}

	public void setMontFevrier(float montFevrier) {
		this.montFevrier=new SimpleObjectProperty<>(montFevrier);
	}

	public ObjectProperty<Float> montFevrierProperty(){
		return montFevrier;
	}
	
	//mars
	public float getMontMars() {
		return montMars.get();
	}

	public void setMontMars(float montMars) {
		this.montMars=new SimpleObjectProperty<>(montMars);
	}
	
	public ObjectProperty<Float> montMarsProperty(){
		return montMars;
	}

	//avril
	public float getMontAvril() {
		return montAvril.get();
	}

	public void setMontAvril(float montAvril) {
		this.montAvril=new SimpleObjectProperty<>(montAvril);
	}
	
	public ObjectProperty<Float> montAvrilProperty(){
		return montAvril;
	}

	//mai
	public float getMontMai() {
		return montMai.get();
	}

	public void setMontMai(float montMai) {
		this.montMai=new SimpleObjectProperty<>(montMai);
	}

	public ObjectProperty<Float> montMainProperty(){
		return montMai;
	}
	
	//juin
	public float getMontJuin() {
		return montJuin.get();
	}

	public void setMontJuin(float montJuin) {
		this.montJuin=new SimpleObjectProperty<>(montJuin);
	}
	
	public ObjectProperty<Float> montJuinProperty(){
		return montJuin;
	}

	//juillet
	public float getMontjuillet() {
		return montjuillet.get();
	}

	public void setMontjuillet(float montjuillet) {
		this.montjuillet=new SimpleObjectProperty<>(montjuillet);
	}
	
	public ObjectProperty<Float> montJuilletProperty(){
		return montjuillet;
	}

	//aout
	public float getMontAout() {
		return montAout.get();
	}

	public void setMontAout(float montAout) {
		this.montAout=new SimpleObjectProperty<>(montAout);
	}
	
	public ObjectProperty<Float> montAoutProperty(){
		return montAout;
	}

	//septembre
	public float getMontSeptembre() {
		return montSeptembre.get();
	}

	public void setMontSeptembre(float montSeptembre) {
		this.montSeptembre=new SimpleObjectProperty<>(montSeptembre);
	}
	
	public ObjectProperty<Float> montSeptembreProperty(){
		return montSeptembre;
	}
	
	//octobre
	public float getMontOctobre() {
		return montOctobre.get();
	}

	public void setMontOctobre(float montOctobre) {
		this.montOctobre=new SimpleObjectProperty<>(montOctobre);
	}
	
	public ObjectProperty<Float> montOctobreProperty(){
		return montOctobre;
	}

	//novembre
	public float getMontNovembre() {
		return montNovembre.get();
	}

	public void setMontNovembre(float montNovembre) {
		this.montNovembre=new SimpleObjectProperty<>(montNovembre);
	}
	
	public ObjectProperty<Float> montNovembreProperty(){
		return montNovembre;
	}

	//decembre
	public float getMontDecembre() {
		return montDecembre.get();
	}

	public void setMontDecembre(float montDecembre) {
		this.montDecembre=new SimpleObjectProperty<>(montDecembre);
	}
	
	public ObjectProperty<Float> montDecembreProperty(){
		return montDecembre;
	}

	//total
	public float getMontTotal() {
		return montTotal.get();
	}

	public void setMontTotal(float montTotal) {
		this.montTotal=new SimpleObjectProperty<>(montTotal);
	}
	
	public ObjectProperty<Float> montTotalProperty(){
		return montTotal;
	}
	
	//adherent
	public ObjectProperty<Adherent> adherentProperty(){
		return adherent;
	}
	
	public void setAdherent(Adherent adherent) {
		this.adherent=new SimpleObjectProperty<>(adherent);
	}
	
	public Adherent getAdherent() {
		return adherent.get();
	}
	
	

	/**
	 * methode de desciption toString
	 */
	@Override
	public String toString() {
		return "CotisationAnnuelle [montJanvier=" + montJanvier + ", montFevrier=" + montFevrier + ", montMars="
				+ montMars + ", montAvril=" + montAvril + ", montMai=" + montMai + ", montJuin=" + montJuin
				+ ", montjuillet=" + montjuillet + ", montAout=" + montAout + ", montSeptembre=" + montSeptembre
				+ ", montOctobre=" + montOctobre + ", montNovembre=" + montNovembre + ", montDecembre=" + montDecembre
				+ ", montTotal=" + montTotal + "\n]";
	}
	
	
	

	
	

}
