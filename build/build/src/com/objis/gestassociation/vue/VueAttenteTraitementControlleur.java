package com.objis.gestassociation.vue;

import com.objis.gestassociation.GestionAssociation;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;

public class VueAttenteTraitementControlleur {
	
	//les proprietes
	GestionAssociation gestionAssociation=new GestionAssociation();
	
	//methode initialisation
	@FXML
	public void initialize() {
		
		
		
	}
	
	public void testThread() {
		
		Service<Void> vueAttenteThread = new Service<Void>() {
					
			@Override
			protected Task<Void> createTask() {
				// TODO Auto-generated method stub
				return new Task<Void>() {
					
					@Override
					protected Void call() throws Exception {
						
						String variable="";
						
						while(variable=="") {
							System.out.println("dhjgfsdgfjd");
						}
						
						
						
						return null;
					}
				};
			}
		};
		
		vueAttenteThread.start();
		
	}
	
	
	

	

	
	
	

}
