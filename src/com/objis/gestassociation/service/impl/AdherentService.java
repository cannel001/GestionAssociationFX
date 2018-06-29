package com.objis.gestassociation.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import com.objis.gestassociation.dao.impl.AdherentDao;
import com.objis.gestassociation.domaine.Adherent;
import com.objis.gestassociation.service.IAdherentService;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.collections.ObservableList;

/**
 * 
 * @author Seka Cannel Ulrich evrard
 * Classe adherent service
 *
 */

public class AdherentService implements IAdherentService {
	
	//les proprietes
	private AdherentDao dao=new AdherentDao();
	private Random aleatoire=new Random();
	private String code="1234567890ABCDEFGHJIKLMNOPQRSTUVWXYZ";
	
	/**
	 * constructeur par defaut
	 */
	
	public AdherentService() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * methode d'enregistrement redefinie
	 */
	@Override
	public Boolean create(Adherent t) {
		
		if (!(t.getId().equals(null))) {
			return dao.create(t);
		}
		
		return null;
	}

	/**
	 * methode permettant de lire un enregistrement redefinie
	 */
	@Override
	public Adherent readOne(Long pk) {
		
		if (!(pk.equals(null))){
			return dao.readOne(pk);
		}
		
		return null;
	}

	/**
	 * methode permettant de faire une modification redefinie
	 */
	@Override
	public Boolean update(Adherent t) {

		if (!(t.getId().equals(null))) {
			return dao.update(t);
		}
		
		return null;
	}

	/**
	 * methode permettant de supprimer un enregistrement redefinie
	 */
	@Override
	public Boolean delete(Long pk) {
		
		if (!(pk.equals(null))) {
			return dao.delete(pk);
		}
		
		return null;
	}

	/**
	 * methode permettant de lire tous les enregistrements redefinie
	 */
	@Override
	public List<Adherent> readAll() {
		
		return dao.readAll();
		
	}

	/**
	 * methode de lire tous les adherents internes redefinie
	 */
	@Override
	public List<Adherent> getAllInterne() {
		
		return dao.getAllAdherentInterne();
		
	}

	/**
	 * methode permettant de lire tous les adherents externes redefinie
	 */
	@Override
	public List<Adherent> getAllExterne() {

		return dao.getAllAdherentExterne();
		
	}
	
	/**
	 * methode permettant de retourner le nombre d'enregistrement par matricule redefinie
	 */
	@Override
	public Boolean verifDoublonMatricule(String matricule) {
		
		if (!(matricule.equals(null))) {
			return dao.verifDoublonMatriculeStatement(matricule);
		}
		return null;
		
	}
	
	
	/**
	 * methode permettant de generer le matricule redefinie
	 */
	@Override
	public String matriculeGenerer() {
		
		String codeGenere = null;
		
		int index=0;
		codeGenere="AJE";
		
		for(int i=0;i<6;i++) {
			
			index=aleatoire.nextInt(code.length());
			
			codeGenere+=code.charAt(index);
			
		}
		
		while(verifDoublonMatricule(codeGenere).equals(true)) {
			
			index=0;
			codeGenere="AJE";
			
			for(int i=0;i<6;i++) {
				
				index=aleatoire.nextInt(code.length());
				
				codeGenere+=code.charAt(index);
				
			}
			
		}
		
		return codeGenere;
		
	}
	
	/**
	 * methode permettant de retourner le nombre d'adherents internes redefinie
	 */
	@Override
	public int nbAdherentInterne() {
		return dao.getAllAdherentInterne().size();
	}
	
	/**
	 * methode permettant de retourner le nombre d'adherents externes redefinie
	 */
	@Override
	public int nbAdherentexterne() {
		return dao.getAllAdherentExterne().size();
	}
	
	/**
	 * uploader l'image par ftp redefinie
	 */
	@Override
		public Boolean uploadImageFtp(String chemin,InputStream source) {
			
			Boolean repTelechagement=false;
			
			//parametre de connexion
			String user="javaDev";
			int port=23;
			String password="codeur";
			String adresse="localhost";
			
			FTPClient ftpClient=new FTPClient();
			
			try {
				
				ftpClient.connect(adresse, port);
				ftpClient.login(user, password);
				ftpClient.enterLocalPassiveMode();
				
				ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
				
				boolean res=ftpClient.storeFile("/photoAdherents/"+chemin,source);
				
				if(res==true) {
					repTelechagement=true;
				}
				
			} catch (SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			finally {
				if(ftpClient.isConnected()) {
					try {
						ftpClient.logout();
						ftpClient.disconnect();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			return repTelechagement;	
			
		}
		
	/**
	 * methode permettant de verifier l'existence d'adherent dans la table bureau redefinie
	 */
	@Override
		public Boolean verifAdherentBureau(Long id) {
			
			if(!(id.equals(null))) {
				
				return dao.verifExistADherentDansBureau(id);
				
			}
			
			return false;
			
		}
		
	/**
	 * methode permettant de verifier l'existence d'un adherent dans la table cotisation redefinie
	 */
	@Override
	public Boolean verifAdherentCotisation(Long id) {
			
		if (!(id.equals(null))) {
			
			return dao.verifExistencAdhDansCotisation(id);
			
		}
			
		return false;
		
	}

	@Override
	public List<Adherent> readAllAdherentHorsBureau() {
		// TODO Auto-generated method stub
		return dao.readAllAdherentHorsBureau();
	}

	@Override
	public List<Adherent> readAllADherentDansBureau() {
		// TODO Auto-generated method stub
		return dao.readAllADherentDansBureau();
	}
	
	
		

}
