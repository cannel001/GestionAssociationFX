package com.objis.gestassociation.dao.impl;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

import com.objis.gestassociation.domaine.Adherent;

/**
 * Classe Adherent Dao
 * @author Seka Cannel Ulrich Evrard
 *
 */
public class AdherentDao extends Dao<Adherent,Long>{
	
	//les requetes
	private static String createStatement="insert into adherent (idAdherent,nom,prenom,dateNaissance,sexe,nationalite,numeroPiece,typePiece,profession,residence,lieuDeNaissance,adresse,telephone,email,dateEntree,matricule,typAdherent,image,etatAdherent) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static String updateStatement="update adherent set nom=?,prenom=?,dateNaissance=?,sexe=?,nationalite=?,numeroPiece=?,typePiece=?,profession=?,residence=?,lieuDeNaissance=?,adresse=?,telephone=?,email=?,dateEntree=?,matricule=?,typAdherent=?,image=? where idAdherent=?";
	private static String deleteStatement="update adherent set etatAdherent='SUPPRIMER' where idAdherent=?";
	private static String readOneStatement="select * from adherent where idAdherent=? and etatAdherent='ACTIF'";
	private static String readAllStatement="select * from adherent where etatAdherent='ACTIF'";
	private static String lastElementStatement="select * from adherent order by idAdherent desc";
	private static String verifDoublonMatriculeStatement="select count(*) from adherent where matricule=? ";
	private static String getAllAdherentInterne="select * from adherent where typAdherent='INTERNE' and etatAdherent='ACTIF'";
	private static String getAllAdherentExterne="select * from adherent where typAdherent='EXTERNE' and etatAdherent='ACTIF'";
	private static String verifExistencADherenDansBureau="select count(*) from adherent where idAdherent=? and idAdherent in (select idAdherent from bureau where bureau.etatBureau='ACTIF')";
	private static String verifExistencADherenDansCotisation="select count(*) from adherent where idAdherent=? and idAdherent in (select idAdherent from cotisation where cotisation.etatCotisation='ACTIF')";
	private static String readAllADherentHorsBureau="select * from adherent where idAdherent not in (select idAdherent from bureau)";
	private static String readAllADherentDansBureau="select * from adherent where idAdherent in (select idAdherent from bureau)";
	
	
	private static int executeQuery=-1;
	

	/**
	 * Classe permettant de faire un enregistrement redefinie
	 */
	@Override
	public Boolean create(Adherent t) {
		
		try {
			
			ps=createStatement(createStatement);
			
			ps.setLong(1, t.getId());
			ps.setString(2, t.getNom());
			ps.setString(3, t.getPrenom());
			ps.setDate(4, Date.valueOf(t.getDateNaissance()));
			ps.setString(5, t.getSexe());
			ps.setString(6, t.getNationalite());
			ps.setString(7, t.getNumeroPiece());
			ps.setString(8, t.getTypePiece());
			ps.setString(9, t.getProfession());
			ps.setString(10, t.getResidence());
			ps.setString(11, t.getLieuDeNaissance());
			ps.setString(12, t.getAdresse());
			ps.setString(13, t.getTelephone());
			ps.setString(14, t.getEmail());
			ps.setDate(15,Date.valueOf(t.getDateEntree()));
			ps.setString(16, t.getMatricule());
			ps.setString(17, t.getTypAdherent());
			ps.setString(18, t.getImage());
			ps.setString(19, t.getEtatAdherent());
			
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
	public Adherent readOne(Long pk) {
		
		Adherent adherent = null;
		
		try {
			
			ps=createStatement(readOneStatement);
			
			ps.setLong(1, pk);
			
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				
				adherent=new Adherent(rs.getLong("idAdherent"),rs.getString("nom"),rs.getString("prenom"),rs.getDate("dateNaissance").toLocalDate(),rs.getString("sexe"),rs.getString("nationalite"),rs.getString("numeroPiece"),rs.getString("typePiece"),rs.getString("profession"),rs.getString("residence"),rs.getString("lieuDeNaissance"),rs.getString("adresse"),rs.getString("telephone"),rs.getString("email"),rs.getDate("dateEntree").toLocalDate(),rs.getString("matricule"),rs.getString("typAdherent"),rs.getString("image"),rs.getString("etatAdherent"));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return adherent;
	}

	/**
	 * methode permettant de modifier un enregistrement redefinie
	 */
	@Override
	public Boolean update(Adherent t) {
		
		ps=createStatement(updateStatement);
		
		try {
			ps.setString(1, t.getNom());
			ps.setString(2, t.getPrenom());
			ps.setDate(3, Date.valueOf(t.getDateNaissance()));
			ps.setString(4, t.getSexe());
			ps.setString(5, t.getNationalite());
			ps.setString(6, t.getNumeroPiece());
			ps.setString(7, t.getTypePiece());
			ps.setString(8, t.getProfession());
			ps.setString(9, t.getResidence());
			ps.setString(10, t.getLieuDeNaissance());
			ps.setString(11, t.getAdresse());
			ps.setString(12, t.getTelephone());
			ps.setString(13, t.getEmail());
			ps.setDate(14,Date.valueOf(t.getDateEntree()));
			ps.setString(15, t.getMatricule());
			ps.setString(16, t.getTypAdherent());
			ps.setString(17, t.getImage());
			ps.setLong(18, t.getId());
			
			executeQuery=ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return executeQuery>0;
	}

	/**
	 * methode permettant de supprimer un enregistrement redefinie
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
	public List<Adherent> readAll() {
		
		List<Adherent> listAdherent= new LinkedList<Adherent>();
		
		ps=createStatement(readAllStatement);
		
		try {
			
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				
				listAdherent.add(new Adherent(rs.getLong("idAdherent"),rs.getString("nom"),rs.getString("prenom"),rs.getDate("dateNaissance").toLocalDate(),rs.getString("sexe"),rs.getString("nationalite"),rs.getString("numeroPiece"),rs.getString("typePiece"),rs.getString("profession"),rs.getString("residence"),rs.getString("lieuDeNaissance"),rs.getString("adresse"),rs.getString("telephone"),rs.getString("email"),rs.getDate("dateEntree").toLocalDate(),rs.getString("matricule"),rs.getString("typAdherent"),rs.getString("image"),rs.getString("etatAdherent")));
				
			}	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listAdherent;
	}
	
	/**
	 * methode permettant de retourner la liste des adherents internes
	 * @return
	 */
	public List<Adherent> getAllAdherentInterne() {
		
		List<Adherent> listAdherent= new LinkedList<Adherent>();
		
		ps=createStatement(getAllAdherentInterne);
		
		try {
			
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				
				listAdherent.add(new Adherent(rs.getLong("idAdherent"),rs.getString("nom"),rs.getString("prenom"),rs.getDate("dateNaissance").toLocalDate(),rs.getString("sexe"),rs.getString("nationalite"),rs.getString("numeroPiece"),rs.getString("typePiece"),rs.getString("profession"),rs.getString("residence"),rs.getString("lieuDeNaissance"),rs.getString("adresse"),rs.getString("telephone"),rs.getString("email"),rs.getDate("dateEntree").toLocalDate(),rs.getString("matricule"),rs.getString("typAdherent"),rs.getString("image"),rs.getString("etatAdherent")));
				
			}	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listAdherent;
	}
	
	/**
	 * methode permettant de retourner la liste des adherents externes
	 * @return
	 */
	public List<Adherent> getAllAdherentExterne() {
		
		List<Adherent> listAdherent= new LinkedList<Adherent>();
		
		ps=createStatement(getAllAdherentExterne);
		
		try {
			
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				
				listAdherent.add(new Adherent(rs.getLong("idAdherent"),rs.getString("nom"),rs.getString("prenom"),rs.getDate("dateNaissance").toLocalDate(),rs.getString("sexe"),rs.getString("nationalite"),rs.getString("numeroPiece"),rs.getString("typePiece"),rs.getString("profession"),rs.getString("residence"),rs.getString("lieuDeNaissance"),rs.getString("adresse"),rs.getString("telephone"),rs.getString("email"),rs.getDate("dateEntree").toLocalDate(),rs.getString("matricule"),rs.getString("typAdherent"),rs.getString("image"),rs.getString("etatAdherent")));
				
			}	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listAdherent;
	}
	
	/**
	 * methode permettant de retourner le dernier enregistrement
	 * @return
	 */
	public Adherent lastElementStatement() {
			
			Adherent adherent = null;
			
			try {
				
				ps=createStatement(lastElementStatement);
				
				ResultSet rs=ps.executeQuery();
				
				while(rs.next()) {
					
					adherent=new Adherent(rs.getLong("idAdherent"),rs.getString("nom"),rs.getString("prenom"),rs.getDate("dateNaissance").toLocalDate(),rs.getString("sexe"),rs.getString("nationalite"),rs.getString("numeroPiece"),rs.getString("typePiece"),rs.getString("profession"),rs.getString("residence"),rs.getString("lieuDeNaissance"),rs.getString("adresse"),rs.getString("telephone"),rs.getString("email"),rs.getDate("dateEntree").toLocalDate(),rs.getString("matricule"),rs.getString("typAdherent"),rs.getString("image"),rs.getString("etatAdherent"));
					
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		return adherent;
	}
	
	/**
	 * methode permettant de retourner la liste des adherents
	 * @param matricule
	 * @return
	 */
	public Boolean verifDoublonMatriculeStatement(String matricule) {
		
		ps=createStatement(verifDoublonMatriculeStatement);
		
		Integer nombre=-1;
		
		try {
			
			ps.setString(1, matricule);
			
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				nombre=rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return nombre>0;
		
	}
	
	/**
	 * methode permettant de retourner l'image
	 * @param pk
	 * @return
	 */
	public java.awt.Image getPhoto(Long pk) {
		
		//les proprietes
		BufferedInputStream is = null;
		java.sql.Blob blob;
		java.awt.Image image = null;
		
		ps=createStatement(readOneStatement);
		
		try {
			ps.setLong(1, pk);
			
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				
				blob=rs.getBlob("photoIdentite");
				
				is=new BufferedInputStream(blob.getBinaryStream());
				
				image=ImageIO.read(is);
				
			}
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return image;
		
	}
	
	/**
	 * methode permettant de verifier l'existence d'un adherent dans la table bureau
	 * @param id
	 * @return
	 */
	public Boolean verifExistADherentDansBureau(Long id) {
		
		int rep=-1;
		
		ps=createStatement(verifExistencADherenDansBureau);
		
		try {
			
			ps.setLong(1, id);
			
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
	 * methode permettant de verifier l'existence d'un adherent dans la table cotisation
	 * @param id
	 * @return
	 */
	public Boolean verifExistencAdhDansCotisation(Long id) {
		
		int rep=-1;
		
		ps=createStatement(verifExistencADherenDansCotisation);
		
		try {
			
			ps.setLong(1, id);
			
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
	 * methode permettant de retourner des adherents qui ne sont pas dans le bureau
	 */
	public List<Adherent> readAllAdherentHorsBureau(){
		
		List<Adherent> maList=new LinkedList<>();
		Adherent adherent=null;
		
		
		try {
			
			ps=createStatement(readAllADherentHorsBureau);
			
			ResultSet rs;
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				adherent=new Adherent(rs.getLong("idAdherent"),rs.getString("nom"),rs.getString("prenom"),rs.getDate("dateNaissance").toLocalDate(),rs.getString("sexe"),rs.getString("nationalite"),rs.getString("numeroPiece"),rs.getString("typePiece"),rs.getString("profession"),rs.getString("residence"),rs.getString("lieuDeNaissance"),rs.getString("adresse"),rs.getString("telephone"),rs.getString("email"),rs.getDate("dateEntree").toLocalDate(),rs.getString("matricule"),rs.getString("typAdherent"),rs.getString("image"),rs.getString("etatAdherent"));
				
				maList.add(adherent);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return maList;
		
	}
	
	
	/**
	 * methode permettant de retourner des adherents qui sont dans le bureau
	 */
	public List<Adherent> readAllADherentDansBureau(){
		
		List<Adherent> maList=new LinkedList<>();
		Adherent adherent=null;
		
		
		try {
			
			ps=createStatement(readAllADherentDansBureau);
			
			ResultSet rs;
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				adherent=new Adherent(rs.getLong("idAdherent"),rs.getString("nom"),rs.getString("prenom"),rs.getDate("dateNaissance").toLocalDate(),rs.getString("sexe"),rs.getString("nationalite"),rs.getString("numeroPiece"),rs.getString("typePiece"),rs.getString("profession"),rs.getString("residence"),rs.getString("lieuDeNaissance"),rs.getString("adresse"),rs.getString("telephone"),rs.getString("email"),rs.getDate("dateEntree").toLocalDate(),rs.getString("matricule"),rs.getString("typAdherent"),rs.getString("image"),rs.getString("etatAdherent"));
				
				maList.add(adherent);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return maList;
		
	}
	
}
