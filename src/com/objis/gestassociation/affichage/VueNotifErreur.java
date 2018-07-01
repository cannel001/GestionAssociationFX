package com.objis.gestassociation.affichage;

import java.io.IOException;

import com.objis.gestassociation.GestionAssociation;
import com.objis.gestassociation.service.impl.AssociationService;
import com.objis.gestassociation.vue.VueErreurNotifControlleur;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class VueNotifErreur {
	
	private Stage stageAlerte;
	private GestionAssociation association;
	private AssociationService associationService;
	
	
	public VueNotifErreur(Stage stageAlerte,GestionAssociation association,AssociationService associationService) {
		
		this.stageAlerte=stageAlerte;
		this.association=association;
		this.associationService=associationService;
		
	}
	
	//methode permettant d'afficher la vue
	public void afficherVue() {
		
		AnchorPane pane=new AnchorPane();
		
		FXMLLoader loader=new FXMLLoader();
		
		loader.setLocation(getClass().getResource("vue/VueErreurNotif.fxml"));
		
		try {
			
			if(stageAlerte.isShowing()) {
				
				stageAlerte.close();
				
			}
			
			pane=(AnchorPane)loader.load();
			
			Scene scene=new Scene(pane);
			scene.setFill(Color.TRANSPARENT);
			
			stageAlerte=new Stage();
			stageAlerte.setScene(scene);
			stageAlerte.initStyle(StageStyle.TRANSPARENT);
			stageAlerte.show();
			
			VueErreurNotifControlleur controlleur=loader.getController();
			controlleur.setGestion(association);
			
			//lancement du thread d'arret
			associationService.closeAlert(stageAlerte, 3);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
