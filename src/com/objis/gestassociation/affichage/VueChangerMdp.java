package com.objis.gestassociation.affichage;

import java.io.IOException;

import com.objis.gestassociation.GestionAssociation;
import com.objis.gestassociation.vue.VueChangerDePassControlleur;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class VueChangerMdp {
	
	private AnchorPane root;
	private FXMLLoader loader;
	private GestionAssociation association;
	
	public VueChangerMdp(GestionAssociation association) {
		
		this.association=association;
		
	}
	
	//Methode permettant d'afficher la vue
	public void afficherVue() {
		
		new Thread(()->{
			Platform.runLater(()->{
				
				root=new AnchorPane();
				
				loader=new FXMLLoader();
				
				loader.setLocation(getClass().getResource("../vue/VueChangerMotDePasse.fxml"));
				
				try {
					
					root=(AnchorPane)loader.load();
					association.getPrimaryStage().setScene(new Scene(root));
					association.setPrimaryStage(association.getPrimaryStage());
					
					VueChangerDePassControlleur controlleur=loader.getController();
					controlleur.setGestion(association);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			});
		}).start();
		
	}

}
