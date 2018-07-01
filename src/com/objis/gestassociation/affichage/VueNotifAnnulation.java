package com.objis.gestassociation.affichage;

import java.io.IOException;

import com.objis.gestassociation.service.impl.AssociationService;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class VueNotifAnnulation {
	
	private Stage stageAlerte;
	private AnchorPane pane;
	private FXMLLoader loader;
	private AssociationService associationService;
	
	public VueNotifAnnulation(Stage stageAlerte,AssociationService associationService) {
		
		this.stageAlerte=stageAlerte;
		this.associationService=associationService;
		
	}
	
	//Methode permettant d'afficher la vue
	public void afficherVue() {
		
		if(stageAlerte.isShowing()) {
			stageAlerte.close();
		}
		
		pane=new AnchorPane();
		
		loader=new FXMLLoader();
		
		loader.setLocation(getClass().getResource("../vue/VueAlerteAnnulation.fxml"));
		
		try {
			
			stageAlerte=new Stage();
			
			pane=(AnchorPane) loader.load();
			
			Scene scene=new Scene(pane);
			scene.setFill(Color.TRANSPARENT);
			
			stageAlerte.setScene(scene);
			stageAlerte.initStyle(StageStyle.TRANSPARENT);
			stageAlerte.show();
			
			//lancement du thread d'arret
			associationService.closeAlert(stageAlerte, 3);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
