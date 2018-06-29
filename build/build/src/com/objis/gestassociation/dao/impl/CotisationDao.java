package com.objis.gestassociation.dao.impl;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import com.objis.gestassociation.domaine.Adherent;
import com.objis.gestassociation.domaine.Cotisation;

/**
 * Classe Cotisation Dao
 * @author Seka Cannel Ulrich Evrard
 *
 */
public class CotisationDao extends Dao<Cotisation, Long> {
	
	//les proprietes
	private static String createStatement="insert into cotisation (idCotisation,idAdherent,date,etatCotisation,montant) values(?,?,?,?,?)";
	private static String updateStatement="update cotisation set idAdherent=?,date=?,etatCotisation=?,montant=? where idCotisation=?";
	private static String readOneStatement="select * from cotisation,adherent where adherent.idAdherent=cotisation.idAdherent and idCotisation=? and etatCotisation='ACTIF' and adherent.etatAdherent='ACTIF'";
	private static String readAllStatement="select * from cotisation,adherent where adherent.idAdherent=cotisation.idAdherent and etatCotisation='ACTIF' and adherent.etatAdherent='ACTIF'";
	private static String deleteStatement="update cotisation set etatCotisation='SUPPRIMER' where idCotisation=?";
	private static String lastElementStatement="select * from cotisation,adherent where adherent.idAdherent=cotisation.idAdherent and etatCotisation='ACTIF' order by idCotisation desc limit 1;";
	private static String listCotisationAnnuelleParId="select * from cotisation,adherent where adherent.idAdherent=cotisation.idAdherent and idAdherent=? and etatCotisation='ACTIF' and adherent.etatAdherent='ACTIF' and date between ? and ?";
	private static String listCotisationAnnuelle="select * from cotisation,adherent where adherent.idAdherent=cotisation.idAdherent and etatCotisation='ACTIF' and adherent.etatAdherent='ACTIF' and date between ? and ?";
	private static String sommeCotisation="select sum(montant) from cotisation where etatCotisation='ACTIF'";
	private static String cotisationParAnnee="select sum(montant) from cotisation where etatCotisation='ACTIF' and date between ? and ?";
	
	private static int executeQuery=-1;
	
	/**
	 * methode permettant de faire un enregistrement redefinie
	 */
	@Override
	public Boolean create(Cotisation t) {
		
		ps=createStatement(createStatement);
		
		try {
			
			ps.setLong(1, t.getIdCotisation());
			ps.setLong(2, t.getAdherent());
			ps.setDate(3, Date.valueOf(t.getDate()));
			ps.setString(4, t.getEtatCotisation());
			ps.setFloat(5, t.getMontant());
			
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
	public Cotisation readOne(Long pk) {
		
		Cotisation cotisation = null;
		
		ps=createStatement(readOneStatement);
		
		try {
			
			ps.setLong(1, pk);
			
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				
				cotisation=new Cotisation(rs.getFloat("montant"),rs.getDate("date").toLocalDate(),rs.getString("etatCotisation"),rs.getLong("idCotisation"),rs.getLong("idAdherent"));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cotisation;
	}
	
	/**
	 * methode permettant de modifier un enregistrement redefinie
	 */
	@Override
	public Boolean update(Cotisation t) {
		
		ps=createStatement(updateStatement);
		
		try {
			
			ps.setLong(1, t.getAdherent());
			ps.setDate(2, Date.valueOf(t.getDate()));
			ps.setString(3, t.getEtatCotisation());
			ps.setFloat(4, t.getMontant());
			ps.setLong(5, t.getIdCotisation());
			
			
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
	public List<Cotisation> readAll() {
		
		List<Cotisation> listCotisation=new LinkedList<>();
		
		ps=createStatement(readAllStatement);
		
		try {
			
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				
				listCotisation.add(new Cotisation(rs.getFloat("montant"),rs.getDate("date").toLocalDate(),rs.getString("etatCotisation"),rs.getLong("idCotisation"),rs.getLong("idAdherent")));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listCotisation;
		
	}
	
	/**
	 * methode permettant de retourner le dernier enregistrement
	 * @return
	 */
	public Cotisation lastElement() {
		
		Cotisation cotisation = null;
		
		ps=createStatement(lastElementStatement);
		
		try {
			
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				
				cotisation=new Cotisation(rs.getFloat("montant"),rs.getDate("date").toLocalDate(),rs.getString("etatCotisation"),rs.getLong("idCotisation"),rs.getLong("idAdherent"));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cotisation;
		
	}
	
	/**
	 * methode permettant de retourner la liste des cotisations par année et par Id
	 * @param id
	 * @param dateDebut
	 * @param dateFin
	 * @return
	 */
	public List<Cotisation> listAnnuelleParId(Long id,LocalDate dateDebut,LocalDate dateFin){
		
		List<Cotisation>listCotisation=new LinkedList<>();
		
		ps=createStatement(listCotisationAnnuelleParId);
		
		try {
			
			ps.setLong(1, id);
			ps.setDate(2, Date.valueOf(dateDebut));
			ps.setDate(3,Date.valueOf(dateFin));
			
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				
				listCotisation.add(new Cotisation(rs.getFloat("montant"),rs.getDate("date").toLocalDate(),rs.getString("etatCotisation"),rs.getLong("idCotisation"),rs.getLong("idAdherent")));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listCotisation;
		
	}
	
	/**
	 * methode permettant de retourner la liste des cotisations par année et par Id
	 * @param dateDebut
	 * @param dateFin
	 * @return
	 */
	public List<Cotisation> listAnnuelle(LocalDate dateDebut,LocalDate dateFin){
		
		List<Cotisation>listCotisation=new LinkedList<>();
		
		ps=createStatement(listCotisationAnnuelle);
		
		try {
			
			ps.setDate(1, Date.valueOf(dateDebut));
			ps.setDate(2, Date.valueOf(dateFin));
			
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				
				listCotisation.add(new Cotisation(rs.getFloat("montant"),rs.getDate("date").toLocalDate(),rs.getString("etatCotisation"),rs.getLong("idCotisation"),rs.getLong("idAdherent")));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listCotisation;
		
	}
	
	/**
	 * methode permettant de retourner la somme des cotisations
	 * @return
	 */
	public Float sommeCotisation() {
		
		Float somme=(float) 0;
		
		ps=createStatement(sommeCotisation);
		
		try {
			
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				
				somme=rs.getFloat(1);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return somme;
		
	}
	
	//methode permettant de retourner la somme des cotisations par annee
	public Double cotisationParAnnee(LocalDate dateDebbut,LocalDate dateFin) {
		
		Double cotisation=null;
		
		try {
			
			ps=createStatement(cotisationParAnnee);
			
			ps.setDate(1, Date.valueOf(dateDebbut));
			ps.setDate(2, Date.valueOf(dateFin));
			
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				
				cotisation=rs.getDouble(1);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return cotisation;
		
	}
	

}
