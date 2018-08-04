package com.objis.gestassociation.service.impl;

import java.util.Calendar;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.stage.Stage;

public class ThreadMotificationAlerte extends Task<Void> {
	
	//les proprietes
	private Stage stage;
	
	//constructeur
	public ThreadMotificationAlerte(Stage stage) {
		this.stage=stage;
		stage.show();
	}


	@Override
	protected Void call() throws Exception {
		// TODO Auto-generated method stub
		String tempsActuelle=Calendar.getInstance().MINUTE+" "+Calendar.getInstance().SECOND;
		int cptDebut=3;
		int cptFin=0;
		
		while(cptDebut>cptFin) {
			if(!(tempsActuelle.equals(Calendar.getInstance().MINUTE+" "+Calendar.getInstance().SECOND))) {
				tempsActuelle=Calendar.getInstance().MINUTE+" "+Calendar.getInstance().SECOND;
				cptFin++;
				System.out.println(cptFin);
				System.out.println(tempsActuelle);
			}
		}
		
		stage.close();
		return null;
	}

}
