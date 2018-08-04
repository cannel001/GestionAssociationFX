package com.objis.gestassociation.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.objis.gestassociation.domaine.Bureau;

/**
 * Classe Bureau Dao
 * @author Seka Cannel Ulrich Evrard
 *
 */
public class BureauDao extends Dao<Bureau, Long> {
	
	//les proprietes
	private static String createStatement="insert into bureau (etatBureau,fonction,login,password,idAdherent) values (?,?,?,?,?)";
	private static String readOneStatement="select * from adherent,bureau where adherent.idAdherent=bureau.idAdherent and bureau.idAdherent=? and etatBureau='ACTIF'";
	private static String updateStatemen="update bureau set fonction=?,login=?,password=?,etatBureau='ACTIF' where idAdherent=?";
	private static String deleteStatement="update bureau set etatBureau='SUPPRIMER'  where idAdherent=?";
	private static String readlAll="select * from adherent,bureau where adherent.idAdherent=bureau.idAdherent and etatBureau='ACTIF'";
	private static String connexionMembrStatement="select * from bureau,adherent where adherent.idAdherent=bureau.idAdherent and login=? and password=? and etatBureau='ACTIF'";
	private static String readOneStatementSupprimer="select * from adherent,bureau where adherent.idAdherent=bureau.idAdherent and bureau.idAdherent=? and etatBureau='SUPPRIMER'";
	private static String verifExistLogin="select count(*) from bureau where login=?";
	private static String readOneByLogin="select * from adherent,bureau where adherent.idAdherent=bureau.idAdherent and login=? and etatBureau='ACTIF' limit 1";
	
	private static int executeQuery=-1;
	
	/**
	 * methode permettant de faire un enregistrement redefinie
	 */
	@Override
	public Boolean create(Bureau t) {
		
		ps=createStatement(createStatement);
		
		try {
			
			ps.setString(1, t.getEtatBureau());
			ps.setString(2, t.getFonction());
			ps.setString(3, t.getLogin());
			ps.setString(4, t.getPassword());
			ps.setLong(5, t.getId());
			
			executeQuery=ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return executeQuery>0;
	}

	/**
	 * methode permettant de lire un enregistrement redefinie
	 */
	@Override
	public Bureau readOne(Long pk) {
		
		Bureau bureau = null ;
		
		ps=createStatement(readOneStatement);
		
		try {
			
			ps.setLong(1, pk);
			
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				
				bureau=new Bureau();
				
				bureau.setId(rs.getLong("idAdherent"));
				bureau.setNom(rs.getString("nom"));
				bureau.setPrenom(rs.getString("prenom"));
				bureau.setDateNaissance(rs.getDate("dateNaissance").toLocalDate());
				bureau.setSexe(rs.getString("sexe"));
				bureau.setNationalite(rs.getString("nationalite"));
				bureau.setNumeroPiece(rs.getString("numeroPiece"));
				bureau.setTypePiece(rs.getString("typePiece"));
				bureau.setProfession(rs.getString("profession"));
				bureau.setResidence(rs.getString("residence"));
				bureau.setLieuDeNaissance(rs.getString("lieuDeNaissance"));
				bureau.setAdresse(rs.getString("adresse"));
				bureau.setTelephone(rs.getString("telephone"));
				bureau.setEmail(rs.getString("email"));
				bureau.setDateEntree(rs.getDate("dateEntree").toLocalDate());
				bureau.setMatricule(rs.getString("matricule"));
				bureau.setTypAdherent(rs.getString("typAdherent"));
				bureau.setImage(rs.getString("image"));
				bureau.setEtatAdherent(rs.getString("etatAdherent"));
				bureau.setEtatBureau(rs.getString("etatBureau"));
				bureau.setFonction(rs.getString("fonction"));
				bureau.setLogin(rs.getString("login"));
				bureau.setPassword(rs.getString("password"));
					
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bureau;
	}

	/**
	 * methode permettant de modifier un enregistrement
	 */
	@Override
	public Boolean update(Bureau t) {
		
		ps=createStatement(updateStatemen);
		
		try {
			
			ps.setString(1, t.getFonction());
			ps.setString(2, t.getLogin());
			ps.setString(3, t.getPassword());
			ps.setLong(4, t.getId());
			
			executeQuery=ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return executeQuery>0;
	}

	/**
	 * methode permettant de supprimer un enregistrement
	 */
	@Override
	public Boolean delete(Long pk) {
		
		ps=createStatement(deleteStatement);
		
		try {
			
			ps.setLong(1, pk);
			
			executeQuery=ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return executeQuery>0;
	}

	/**
	 * methode permettant de lire tous les enregistrements redefinie
	 */
	@Override
	public List<Bureau> readAll() {
		
		List<Bureau> listeBureau=new LinkedList<>();
		
		Bureau bureau;
		
		ps=createStatement(readlAll);
		
		try {
			
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				
				bureau=new Bureau();
				
				bureau.setId(rs.getLong("idAdherent"));
				bureau.setNom(rs.getString("nom"));
				bureau.setPrenom(rs.getString("prenom"));
				bureau.setDateNaissance(rs.getDate("dateNaissance").toLocalDate());
				bureau.setSexe(rs.getString("sexe"));
				bureau.setNationalite(rs.getString("nationalite"));
				bureau.setNumeroPiece(rs.getString("numeroPiece"));
				bureau.setTypePiece(rs.getString("typePiece"));
				bureau.setProfession(rs.getString("profession"));
				bureau.setResidence(rs.getString("residence"));
				bureau.setLieuDeNaissance(rs.getString("lieuDeNaissance"));
				bureau.setAdresse(rs.getString("adresse"));
				bureau.setTelephone(rs.getString("telephone"));
				bureau.setEmail(rs.getString("email"));
				bureau.setDateEntree(rs.getDate("dateEntree").toLocalDate());
				bureau.setMatricule(rs.getString("matricule"));
				bureau.setTypAdherent(rs.getString("typAdherent"));
				bureau.setImage(rs.getString("image"));
				bureau.setEtatAdherent(rs.getString("etatAdherent"));
				bureau.setEtatBureau(rs.getString("etatBureau"));
				bureau.setFonction(rs.getString("fonction"));
				bureau.setLogin(rs.getString("login"));
				bureau.setPassword(rs.getString("password"));
				
				listeBureau.add(bureau);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listeBureau;
	}
	
	/**
	 * methode permettant de retourner un membre en fonction du login et du mot de passe
	 * @param login
	 * @param password
	 * @return
	 */
	public Bureau mmbParLoginPassword(String login,String password) {
		
		Bureau bureau = null;
		
		ps=createStatement(connexionMembrStatement);
		
		try {
			
			ps.setString(1, login);
			ps.setString(2, password);
			
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				
				bureau=new Bureau();
				
				bureau.setId(rs.getLong("idAdherent"));
				bureau.setNom(rs.getString("nom"));
				bureau.setPrenom(rs.getString("prenom"));
				bureau.setDateNaissance(rs.getDate("dateNaissance").toLocalDate());
				bureau.setSexe(rs.getString("sexe"));
				bureau.setNationalite(rs.getString("nationalite"));
				bureau.setNumeroPiece(rs.getString("numeroPiece"));
				bureau.setTypePiece(rs.getString("typePiece"));
				bureau.setProfession(rs.getString("profession"));
				bureau.setResidence(rs.getString("residence"));
				bureau.setLieuDeNaissance(rs.getString("lieuDeNaissance"));
				bureau.setAdresse(rs.getString("adresse"));
				bureau.setTelephone(rs.getString("telephone"));
				bureau.setEmail(rs.getString("email"));
				bureau.setDateEntree(rs.getDate("dateEntree").toLocalDate());
				bureau.setMatricule(rs.getString("matricule"));
				bureau.setTypAdherent(rs.getString("typAdherent"));
				bureau.setImage(rs.getString("image"));
				bureau.setEtatAdherent(rs.getString("etatAdherent"));
				bureau.setEtatBureau(rs.getString("etatBureau"));
				bureau.setFonction(rs.getString("fonction"));
				bureau.setLogin(rs.getString("login"));
				bureau.setPassword(rs.getString("password"));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bureau;
		
	}
	
	/**
	 * methode permettant de retourner un membre du bureau supprimé
	 * @param id
	 * @return
	 */
	public Bureau readOneMembreSupprimer(Long id) {
		
		Bureau bureau=null;
		
		ps=createStatement(readOneStatementSupprimer);
		
		try {
			
			ps.setLong(1, id);
			
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				
				bureau=new Bureau();
				
				bureau.setId(rs.getLong("idAdherent"));
				bureau.setNom(rs.getString("nom"));
				bureau.setPrenom(rs.getString("prenom"));
				bureau.setDateNaissance(rs.getDate("dateNaissance").toLocalDate());
				bureau.setSexe(rs.getString("sexe"));
				bureau.setNationalite(rs.getString("nationalite"));
				bureau.setNumeroPiece(rs.getString("numeroPiece"));
				bureau.setTypePiece(rs.getString("typePiece"));
				bureau.setProfession(rs.getString("profession"));
				bureau.setResidence(rs.getString("residence"));
				bureau.setLieuDeNaissance(rs.getString("lieuDeNaissance"));
				bureau.setAdresse(rs.getString("adresse"));
				bureau.setTelephone(rs.getString("telephone"));
				bureau.setEmail(rs.getString("email"));
				bureau.setDateEntree(rs.getDate("dateEntree").toLocalDate());
				bureau.setMatricule(rs.getString("matricule"));
				bureau.setTypAdherent(rs.getString("typAdherent"));
				bureau.setImage(rs.getString("image"));
				bureau.setEtatAdherent(rs.getString("etatAdherent"));
				bureau.setEtatBureau(rs.getString("etatBureau"));
				bureau.setFonction(rs.getString("fonction"));
				bureau.setLogin(rs.getString("login"));
				bureau.setPassword(rs.getString("password"));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bureau;
		
	}
	
	/**
	 * methode permettant de verifier l'existance d'un login dans la base de données
	 * @param login
	 * @return
	 */
	public Boolean verifExistancLogin(String login) {
		
		int rep=-1;
		
		ps=createStatement(verifExistLogin);
		
		try {
			
			ps.setString(1, login);
			
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				
				rep=rs.getInt(1);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rep>0;
		
	}
	
	/**
	 * methode permettant de retourner les enregistrements par login
	 * @param login
	 * @return
	 */
	public Bureau readOneByLogin(String login) {
		
		Bureau bureau=null;
		
		ps=createStatement(readOneByLogin);
		
		try {
			
			ps.setString(1, login);
			
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				
				bureau=new Bureau();
				
				bureau.setId(rs.getLong("idAdherent"));
				bureau.setNom(rs.getString("nom"));
				bureau.setPrenom(rs.getString("prenom"));
				bureau.setDateNaissance(rs.getDate("dateNaissance").toLocalDate());
				bureau.setSexe(rs.getString("sexe"));
				bureau.setNationalite(rs.getString("nationalite"));
				bureau.setNumeroPiece(rs.getString("numeroPiece"));
				bureau.setTypePiece(rs.getString("typePiece"));
				bureau.setProfession(rs.getString("profession"));
				bureau.setResidence(rs.getString("residence"));
				bureau.setLieuDeNaissance(rs.getString("lieuDeNaissance"));
				bureau.setAdresse(rs.getString("adresse"));
				bureau.setTelephone(rs.getString("telephone"));
				bureau.setEmail(rs.getString("email"));
				bureau.setDateEntree(rs.getDate("dateEntree").toLocalDate());
				bureau.setMatricule(rs.getString("matricule"));
				bureau.setTypAdherent(rs.getString("typAdherent"));
				bureau.setImage(rs.getString("image"));
				bureau.setEtatAdherent(rs.getString("etatAdherent"));
				bureau.setEtatBureau(rs.getString("etatBureau"));
				bureau.setFonction(rs.getString("fonction"));
				bureau.setLogin(rs.getString("login"));
				bureau.setPassword(rs.getString("password"));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bureau;
		
	}

}
