package com.objis.gestassociation.affichage;

import java.io.IOException;

import com.objis.gestassociation.GestionAssociation;
import com.objis.gestassociation.vue.VueConnexionControlleur;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class VueConnexion {
	
	private AnchorPane pane;
	private GestionAssociation association;
	
	public VueConnexion(GestionAssociation association) {
		
		this.association=association;
		
	}
	
	//methode permettant d'afficher la vue
	public void afficherVue() {
		
		pane=new AnchorPane();
		
		FXMLLoader loader=new FXMLLoader();
		
		loader.setLocation(getClass().getResource("../vue/VueConnexion.fxml"));
		
		try {
			pane=loader.load();
			
			Scene scene=new Scene(pane);
			
			association.getPrimaryStage().setScene(scene);
			
			association.getPrimaryStage().setResizable(false);
			association.getPrimaryStage().setMaximized(false);
			
			association.setPrimaryStage(association.getPrimaryStage());
			
			VueConnexionControlleur controlleur=loader.getController();
			
			controlleur.setGestionAssociation(association);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

}
