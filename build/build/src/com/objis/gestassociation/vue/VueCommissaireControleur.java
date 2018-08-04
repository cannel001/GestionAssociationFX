package com.objis.gestassociation.vue;

import java.util.Calendar;

import com.objis.gestassociation.GestionAssociation;
import com.objis.gestassociation.service.impl.CaisseService;
import com.objis.gestassociation.service.impl.CotisationService;
import com.objis.gestassociation.service.impl.EvenementService;

import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Label;

public class VueCommissaireControleur {
	
	//les proprietes
	private GestionAssociation association;
	
	private NumberAxis yAxis=new NumberAxis();
	private CategoryAxis xAxis=new CategoryAxis();
	
	private Series serieCotistion=new Series<>();
	
	
	
	private CotisationService cotisationService=new CotisationService();
	private EvenementService evenementService=new EvenementService();
	private CaisseService caisseService=new CaisseService();
	
	@FXML
	private LineChart<String,Number> lineChart=new LineChart<>(xAxis, yAxis);
	
	@FXML
	private Label lbMontCaisse;
	@FXML
	private Label lbDepensAnnee;
	@FXML
	private Label lbDepensMois;
	@FXML
	private Label lbCotisationAnnee;
	@FXML
	private Label lbCotisationMois;
	
	//methode d'initialisation
	public void initialize() {
		
		affectationValeur();
		
	}
	
	//methode permettant de peupler le graphe
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void peuplerGraphe() {
		
		//peuplement de la serie cotisation
		serieCotistion.getData().add(new XYChart.Data("Janvier", (association.getListTtCotisationAnnuelleParMois().get(0))/10));
		serieCotistion.getData().add(new XYChart.Data("Fevrier", (association.getListTtCotisationAnnuelleParMois().get(1))/10));
		serieCotistion.getData().add(new XYChart.Data("Mars", (association.getListTtCotisationAnnuelleParMois().get(2))/10));
		serieCotistion.getData().add(new XYChart.Data("Avril",(association.getListTtCotisationAnnuelleParMois().get(3))/10));
		serieCotistion.getData().add(new XYChart.Data("Mai", (association.getListTtCotisationAnnuelleParMois().get(4))/10));
		serieCotistion.getData().add(new XYChart.Data("Juin", (association.getListTtCotisationAnnuelleParMois().get(5))/10));
		serieCotistion.getData().add(new XYChart.Data("Juillet",(association.getListTtCotisationAnnuelleParMois().get(6))/10));
		serieCotistion.getData().add(new XYChart.Data("Aout", (association.getListTtCotisationAnnuelleParMois().get(7))/10));
		serieCotistion.getData().add(new XYChart.Data("Septembre",(association.getListTtCotisationAnnuelleParMois().get(8))/10));
		serieCotistion.getData().add(new XYChart.Data("Octobre", (association.getListTtCotisationAnnuelleParMois().get(9))/10));
		serieCotistion.getData().add(new XYChart.Data("Novembre", (association.getListTtCotisationAnnuelleParMois().get(10))/10));
		serieCotistion.getData().add(new XYChart.Data("Decembre", (association.getListTtCotisationAnnuelleParMois().get(11))/10));
		
		serieCotistion.setName("Cotisations Annuelle");
		
		lineChart.getData().add(serieCotistion);
		
		lineChart.setTitle("Rapport annuelle");
		
	}
	
	
	//methode permettant d'attribuer les montants aux labels
	public void affectationValeur() {
		
		lbMontCaisse.setText(""+caisseService.montantDisponible());
		lbDepensAnnee.setText(""+evenementService.depensesParAnnee(""+Calendar.getInstance().getWeekYear()));
		lbDepensMois.setText(""+evenementService.depensesParMois(Calendar.getInstance().getTime().getMonth()+1));
		lbCotisationMois.setText(""+cotisationService.cotisationPrMois(Calendar.getInstance().getTime().getMonth()+1));
		lbCotisationAnnee.setText(""+cotisationService.cotisationPrAnnee(""+Calendar.getInstance().getWeekYear()));
		
	}
	
	//methode permettant d'attribuer modifier l'objet association
	public void setGestion(GestionAssociation association) {
		
		this.association=association;
		peuplerGraphe();
		
	}

}
