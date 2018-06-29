package com.objis.gestassociation.dao.impl;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import com.objis.gestassociation.domaine.Evenement;

public class EvenementDao extends Dao<Evenement, Long>{
	
	//les proprietes
	private static String createStatement="insert into evenement (id,achat,date,depenseTt,etatEvenement,lieu,objet,solde) values(?,?,?,?,?,?,?,?)";
	private static String readOneStatement="select * from evenement where id=? and etatEvenement='ACTIF'";
	private static String updateStatement="update evenement set achat=?,date=?,depenseTt=?,etatEvenement=?,lieu=?,objet=?,solde=? where id=?";
	private static String deleteStatement="update evenement set etatEvenement='SUPPRIMER' where id=?";
	private static String readAllStatement="select * from evenement where etatEvenement='ACTIF'";
	private static String lastElementStatement="select * from evenement order by id desc limit 1";
	private static String depensesParAnnee="select sum(depenseTt) from evenement where etatEvenement='ACTIF' and date between ? and ?";
	private static String depensesTotal="select sum(depenseTt) from evenement where etatEvenement='ACTIF'";
	private static int executeQuery=-1;
	
	
	@Override
	public Boolean create(Evenement t) {
		
		ps=createStatement(createStatement);
		
		try {
			
			ps.setLong(1, t.getId());
			ps.setString(2, t.getAchat());
			ps.setDate(3, Date.valueOf(t.getDate()));
			ps.setFloat(4, t.getDepenseTt());
			ps.setString(5, t.getEtatEvenement());
			ps.setString(6, t.getLieu());
			ps.setString(7, t.getObjet());
			ps.setString(8, t.getSolde());
			
			executeQuery=ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return executeQuery>0;
	}
	
	@Override
	public Evenement readOne(Long pk) {
		
		Evenement evenement = null;
		
		ps=createStatement(readOneStatement);
		
		try {
			
			ps.setLong(1, pk);
			
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				
				evenement=new Evenement(rs.getLong("id"),rs.getDate("date").toLocalDate(),rs.getString("lieu") ,rs.getString("objet"),rs.getString("achat"),rs.getFloat("depenseTt"),rs.getString("solde"),rs.getString("etatEvenement"));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return evenement;
	}
	@Override
	public Boolean update(Evenement t) {
		
		ps=createStatement(updateStatement);
		
		try {
			
			ps.setString(1, t.getAchat());
			ps.setDate(2, Date.valueOf(t.getDate()));
			ps.setFloat(3, t.getDepenseTt());
			ps.setString(4, t.getEtatEvenement());
			ps.setString(5, t.getLieu());
			ps.setString(6, t.getObjet());
			ps.setString(7, t.getSolde());
			ps.setLong(8, t.getId());
			
			executeQuery=ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return executeQuery>0;
	}
	
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
	
	@Override
	public List<Evenement> readAll() {
		
		List<Evenement> listEvenement=new LinkedList<>();
		
		ps=createStatement(readAllStatement);
		
		try {
			
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				
				listEvenement.add(new Evenement(rs.getLong("id"),rs.getDate("date").toLocalDate(),rs.getString("lieu") ,rs.getString("objet"),rs.getString("achat"),rs.getFloat("depenseTt"),rs.getString("solde"),rs.getString("etatEvenement")));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listEvenement;
	}
	
	//methode permettant de retourner le dernier enregistrement
	public Evenement lastElement() {
		
		Evenement evenement = null;
		
		ps=createStatement(lastElementStatement);
		
		try {
			
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				
				evenement=new Evenement(rs.getLong("id"),rs.getDate("date").toLocalDate(),rs.getString("lieu") ,rs.getString("objet"),rs.getString("achat"),rs.getFloat("depenseTt"),rs.getString("solde"),rs.getString("etatEvenement"));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return evenement;
		
	}
	
	//Methode permettant de retourner le total des depenses par année
	public Double depensesTtParAnnee(LocalDate dateDebut,LocalDate dateFin) {
		
		Double depensTt = null;
		
		try {
			ps=createStatement(depensesParAnnee);
			
			ps.setDate(1, Date.valueOf(dateDebut));
			ps.setDate(2, Date.valueOf(dateFin));
			
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				
				depensTt=rs.getDouble(1);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return depensTt;
	}
	
	//methode permettant de retouner les depenses totales
	public Double depensesTt() {
		
		Double depenstt=null;
		
		try {
			
			ps=createStatement(depensesTotal);
			
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				
				depenstt=rs.getDouble(1);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return depenstt;
		
	}
	
	
	
}
