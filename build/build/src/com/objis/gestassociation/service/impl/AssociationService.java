package com.objis.gestassociation.service.impl;

import java.util.Calendar;
import java.util.Date;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.stage.Stage;

public class AssociationService {

	
	//methode permettant de generer l'id
	public Long generationId() {
		return Calendar.getInstance().getTimeInMillis();
	}
	
	//methode permettant de verifier le type entier
	public Boolean verifTypInt(String valeur) {
		
		try {
			
			Integer.parseInt(valeur);
			
			return true;
			
		} catch (Exception e) {
			// TODO: handle exception
			
			return false;
		}
		
	}
	
	//methode permettant de verifier le type Float
	public Boolean verifTypeFloat(String valeur) {
		
		try {
			
			Float.parseFloat(valeur);
			
			return true;
			
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
	}
	
	//methode permettant de fermer automatiquement le stage d'alert
		public void closeAlert(Stage monStage,int timeout) {
			
			new Service<Void>() {

				@Override
				protected Task<Void> createTask() {
					// TODO Auto-generated method stub
					return new Task<Void>() {

						@Override
						protected Void call() throws Exception {
							// TODO Auto-generated method stub
							
							Date date=Calendar.getInstance().getTime();
							
							String tempsEnCours=date.getMinutes()+" "+date.getSeconds();
							
							System.out.println(tempsEnCours);
							
							String tempsEnCours2;

							int cptFin=0;
							
							while(cptFin<timeout+1) {
								
								date=Calendar.getInstance().getTime();
								
								tempsEnCours2=date.getMinutes()+" "+date.getSeconds();
								
								if(!(tempsEnCours.equals(tempsEnCours2))) {
									
									tempsEnCours=tempsEnCours2;
									
									cptFin++;
									
								}
								
							}
							
							//tread permettant de gerer la vue
							new Thread(()->{
								Platform.runLater(()->{
									monStage.close();
								});
							}).start();
							
							return null;
						}
					};
				}
			}.start();
			
		}
		
		//

}
