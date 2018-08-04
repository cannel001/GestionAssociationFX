package com.objis.gestassociation.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Singleton {
	
	//les proprietes de connexion
	private static String URL="JDBC:Mysql://localhost:3306/bd_associationFX";
	private static String LOGIN="root";
	private static String PASS="";
	
	private static Connection connex;
	
	//constructeur
	private Singleton() {
		
		try {
			connex=DriverManager.getConnection(URL, LOGIN, PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Alert alert=new Alert(AlertType.ERROR);
			alert.setTitle("Erreur Fatale");
			alert.setContentText("Impossible de se connecter à la base de données. Le programme va s'arreter");
			alert.showAndWait();
			System.exit(0);
		}
		
	}
	
	//methode pour obtenir une instance de la connexion
	public static Connection getInstence() {
		
		if(connex != null) {
			return connex;
		} else {
			new Singleton();
			return connex;
		}
		
	}

}
