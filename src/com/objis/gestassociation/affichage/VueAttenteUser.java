package com.objis.gestassociation.affichage;

import java.io.IOException;

import com.objis.gestassociation.GestionAssociation;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class VueAttenteUser {
	
	private Stage stageAttenteUser;
	private GestionAssociation association;
	
	public VueAttenteUser(GestionAssociation association) {
		
		this.association=association;
		
	}
	
	//methode permettant d'afficher la vue
	public void afficherVue() {
		
		new Thread(()->{
			Platform.runLater(()->{
				
				AnchorPane root=new AnchorPane();
				
				FXMLLoader loader=new FXMLLoader();
				
				loader.setLocation(getClass().getResource("../vue/VueAttenteTraitement.fxml"));
				
				try {
					
					root=(AnchorPane)loader.load();
					
					Scene scene=new Scene(root);
					
					stageAttenteUser=new Stage();
					
					scene.setFill(Color.TRANSPARENT);
					
					stageAttenteUser.setScene(scene);
					stageAttenteUser.initStyle(StageStyle.TRANSPARENT);			
					stageAttenteUser.initOwner(this.association.getPrimaryStage());
					stageAttenteUser.initModality(Modality.WINDOW_MODAL);
					stageAttenteUser.centerOnScreen();
					stageAttenteUser.show();
					
					//traitement permettant de fermer automatiquement le stage d'alert
			
					new Service<Void>() {

						@Override
						protected Task<Void> createTask() {
							// TODO Auto-generated method stub
							return new Task<Void>() {

								@Override
								protected Void call() throws Exception {
									// TODO Auto-generated method stub
									
									while(association.getTraitementEnCours().equals(true)) {
										
										//attendre jusqu'a ce que la variable prenne false comme valeur
										System.out.println("passage dans la boucle");
										
									}
									
									//tread permettant de gerer la vue
									new Thread(()->{
										Platform.runLater(()->{
											stageAttenteUser.close();
										});
									}).start();
									
									return null;
								}
							};
						}
					}.start();
			
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			});
		}).start();
		
		
		
	}

}
