package com.objis.gestassociation.affichage;

import java.io.IOException;

import com.objis.gestassociation.GestionAssociation;
import com.objis.gestassociation.vue.VueMiniSlideBarControlleur;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class VueMiniSlideBar {
	
	private AnchorPane vueSlider;
	private FXMLLoader loader;

	private GestionAssociation association;
	
	public VueMiniSlideBar(GestionAssociation association) {

		this.association=association;
		
	}
	
	//Methode permettant d'afficher la vue
	public void afficherVue() {
		
		vueSlider=new AnchorPane();
		
		loader=new FXMLLoader();
		
		loader.setLocation(getClass().getResource("../vue/VueMiniSlideBar.fxml"));
		
		try {
			
			vueSlider=(AnchorPane)loader.load();
			
			association.getMenuPrincipal().setLeft(vueSlider);
			association.setMenuPrincipal(association.getMenuPrincipal());
			
			VueMiniSlideBarControlleur controlleur=loader.getController();
			
			controlleur.setGestion(association);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
