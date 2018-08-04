package com.objis.gestassociation.singleton;

import com.objis.gestassociation.domaine.Adherent;

public class SingletonAdherent {
	
	private static Adherent adherent;
	
	private SingletonAdherent() {
		
		adherent=new Adherent();
		
	}
	
	public static Adherent getInstance() {
		
		if(adherent.equals(null)) {
		   new SingletonAdherent();
		}
		
		return adherent;
		
	}

}
