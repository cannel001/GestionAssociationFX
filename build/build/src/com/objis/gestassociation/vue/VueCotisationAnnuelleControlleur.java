package com.objis.gestassociation.vue;

import java.util.List;

import com.objis.gestassociation.GestionAssociation;
import com.objis.gestassociation.domaine.Adherent;
import com.objis.gestassociation.domaine.CotisationAnnuelle;
import com.objis.gestassociation.service.impl.CotisationAnnuelleService;

import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class VueCotisationAnnuelleControlleur {
	
	//les proprietes
	private GestionAssociation association;
	private CotisationAnnuelleService cotisationAnnuelleService=new CotisationAnnuelleService();
	private CategoryAxis xAxis=new CategoryAxis();
	private NumberAxis yAxis=new NumberAxis();
	private Series<String, Number> maSerie=new Series<>();
	
	
	
	
	@FXML
	private TableView<CotisationAnnuelle> tableCotisationAnnuelle;
	
	@FXML
	private LineChart<String, Number> monGraphe=new LineChart<String, Number>(xAxis, yAxis);
	
	@FXML
	private TableColumn<CotisationAnnuelle, String> columNom;
	@FXML
	private TableColumn<CotisationAnnuelle,Float> columJanvier;	
	@FXML
	private TableColumn<CotisationAnnuelle,Float> columFevrier;	
	@FXML
	private TableColumn<CotisationAnnuelle,Float> columMars;
	@FXML
	private TableColumn<CotisationAnnuelle,Float> columAvril;
	@FXML
	private TableColumn<CotisationAnnuelle,Float> columMai;	
	@FXML
	private TableColumn<CotisationAnnuelle,Float> columJuin;
	@FXML
	private TableColumn<CotisationAnnuelle,Float> columJuillet;	
	@FXML
	private TableColumn<CotisationAnnuelle,Float> columAout;
	@FXML
	private TableColumn<CotisationAnnuelle,Float> columSeptembre;
	@FXML
	private TableColumn<CotisationAnnuelle,Float> columOctobre;	
	@FXML
	private TableColumn<CotisationAnnuelle,Float> columNovembre;
	@FXML
	private TableColumn<CotisationAnnuelle,Float> columDecembre;
	@FXML
	private TableColumn<CotisationAnnuelle,Float> columTt;	
	@FXML
	private TableColumn<CotisationAnnuelle, String> columPrenom;
	
	
	//methode permettant d'initialiser la vue
	@FXML
	public void initialize() {
		
		columJanvier.setCellValueFactory(e->e.getValue().montJanvierProperty());
		columFevrier.setCellValueFactory(e->e.getValue().montFevrierProperty());
		columMars.setCellValueFactory(e->e.getValue().montMarsProperty());
		columAvril.setCellValueFactory(e->e.getValue().montAvrilProperty());
		columMai.setCellValueFactory(e->e.getValue().montMainProperty());
		columJuin.setCellValueFactory(e->e.getValue().montJuinProperty());
		columJuillet.setCellValueFactory(e->e.getValue().montJuilletProperty());
		columAout.setCellValueFactory(e->e.getValue().montAoutProperty());
		columSeptembre.setCellValueFactory(e->e.getValue().montSeptembreProperty());
		columOctobre.setCellValueFactory(e->e.getValue().montOctobreProperty());
		columNovembre.setCellValueFactory(e->e.getValue().montNovembreProperty());
		columDecembre.setCellValueFactory(e->e.getValue().montDecembreProperty());
		columTt.setCellValueFactory(e->e.getValue().montTotalProperty());
		columNom.setCellValueFactory(e->e.getValue().getAdherent().nomProperty());
		columPrenom.setCellValueFactory(e->e.getValue().getAdherent().prenomProperty());
		
	}
	
	//methode permettant de peupler mon graphe
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void peupleMonGraphe() {
		
		maSerie.setName("Cotisation par mois");
		
		maSerie.getData().add(new XYChart.Data("Janvier",(association.getListTtCotisationAnnuelleParMois().get(0))/10));
		maSerie.getData().add(new XYChart.Data("Fevrier",(association.getListTtCotisationAnnuelleParMois().get(1))/10));
		maSerie.getData().add(new XYChart.Data("Mars",(association.getListTtCotisationAnnuelleParMois().get(2))/10));
		maSerie.getData().add(new XYChart.Data("Avril",(association.getListTtCotisationAnnuelleParMois().get(3))/10));
		maSerie.getData().add(new XYChart.Data("Mai",(association.getListTtCotisationAnnuelleParMois().get(4))/10));
		maSerie.getData().add(new XYChart.Data("Juin",(association.getListTtCotisationAnnuelleParMois().get(5))/10));
		maSerie.getData().add(new XYChart.Data("Juillet",(association.getListTtCotisationAnnuelleParMois().get(6))/10));
		maSerie.getData().add(new XYChart.Data("Aout",(association.getListTtCotisationAnnuelleParMois().get(7))/10));
		maSerie.getData().add(new XYChart.Data("Septembre",(association.getListTtCotisationAnnuelleParMois().get(8))/10));
		maSerie.getData().add(new XYChart.Data("Octobre",(association.getListTtCotisationAnnuelleParMois().get(9))/10));
		maSerie.getData().add(new XYChart.Data("Novembre",(association.getListTtCotisationAnnuelleParMois().get(10))/10));
		maSerie.getData().add(new XYChart.Data("Decembre",(association.getListTtCotisationAnnuelleParMois().get(11))/10));
		
		monGraphe.getData().add(maSerie);
		
		monGraphe.setTitle("Bilan annuelle des cotisations");
		
	}

	
	//Methode permettant de modifier la classe principale
	public void setGestionAssociation(GestionAssociation association) {
		
		this.association=association;
		
		tableCotisationAnnuelle.setItems(association.getListCotisationAnnuelle());
		
		peupleMonGraphe();
		
	}

}
