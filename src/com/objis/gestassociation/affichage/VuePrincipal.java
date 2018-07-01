package com.objis.gestassociation.affichage;

import java.io.IOException;

import com.objis.gestassociation.GestionAssociation;
import com.objis.gestassociation.vue.VueMenuPrincipalController;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class VuePrincipal {
	
	private String url;
	private GestionAssociation association;
	
	public VuePrincipal(String url,GestionAssociation association) {
		
		this.url=url;
		this.association=association;
	
	}
	
	//methode permettant d'afficher la vue
	public void afficherVue() {
		
		new Thread(()->{
			Platform.runLater(()->{
				
				try {
					
					association.getPrimaryStage().hide();
					
					FXMLLoader loader=new FXMLLoader();
					loader.setLocation(getClass().getResource(url));
					
					association.setMenuPrincipal((BorderPane)loader.load());
					
					Scene scene=new Scene(association.getMenuPrincipal());
					//ajouter la vue slider au menu principal
					association.getPrimaryStage().setScene(scene);
					
					//modifier la valeur de menu prinicpal
					association.getPrimaryStage().show();
					association.setMenuPrincipal(association.getMenuPrincipal());
					
					association.getPrimaryStage().setMaximized(true);
					
					VueMenuPrincipalController controller=loader.getController();
					
					controller.setGestion(association);
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			});
		}).start();
		
	}

}
