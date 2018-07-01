package com.objis.gestassociation.affichage;

import java.io.IOException;

import com.objis.gestassociation.GestionAssociation;
import com.objis.gestassociation.vue.VueSlideBarControlleur;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class VueSlideBar {
	
	private GestionAssociation association;
	
	
	public VueSlideBar(GestionAssociation association) {
		
		this.association=association;
		
	}
	
	//methode permettant d'afficher la vue
	public void afficherVue() {
		
		System.out.println("autre test");
		
		new Thread(()->{
			Platform.runLater(()->{
				
				try {
					AnchorPane vueSlider=new AnchorPane();
					FXMLLoader loader=new FXMLLoader();
					loader.setLocation(getClass().getResource("../vue/VueSlideBar.fxml"));
					vueSlider=(AnchorPane)loader.load();
					association.getMenuPrincipal().setLeft(vueSlider);
					VueSlideBarControlleur controlleur=loader.getController();
					controlleur.setGestionAssociation(association);
					System.out.println("test");
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			});
		}).start();
		
	}

}
