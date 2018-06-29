package com.objis.gestassociation.service;

import java.io.InputStream;
import java.util.List;

import com.objis.gestassociation.domaine.Adherent;

/**
 * Interface Adherent Service
 * @author Seka Cannel Ulrich Evrard
 *
 */
public interface IAdherentService extends IService<Adherent,Long> {
	
	public List<Adherent> getAllInterne();
	public List<Adherent> getAllExterne();
	public Boolean verifDoublonMatricule(String matricule);
	public String matriculeGenerer();
	public int nbAdherentInterne();
	public int nbAdherentexterne();
	public Boolean uploadImageFtp(String chemin,InputStream source);
	public Boolean verifAdherentBureau(Long id);
	public Boolean verifAdherentCotisation(Long id);
	public List<Adherent> readAllAdherentHorsBureau();
	public List<Adherent> readAllADherentDansBureau();

}
